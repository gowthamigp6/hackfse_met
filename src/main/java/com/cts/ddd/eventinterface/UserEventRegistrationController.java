package com.cts.ddd.eventinterface;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.ddd.application.UserEventRegistrationService;
import com.cts.feedback.event.UserEventRegistration;


@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
public class UserEventRegistrationController {

	@Autowired
	private UserEventRegistrationService userEventRegistrationService;
	
	/**
	 * Register the user to the event
	 * @param associateId
	 * @param eventList
	 * @return
	 */
	@PostMapping(value = "/registerDetails/saveEvent/{associateId}")
	public ResponseEntity<String> saveEvent(@PathVariable("associateId") String associateId,
			@RequestBody List<String> eventList) {
		String message = "";
		try {
			userEventRegistrationService.registerEventRegistration(associateId, eventList);
			message = "User Details Saved Successfully";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Error in saving the details";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

	}

	/**
	 * View the registered event to the user
	 * @return
	 */
	@PostMapping(value = "/registerDetails/get/{associateId}", headers = "Accept=application/json")
	public @ResponseBody List<UserEventRegistration> getRegisteredEventDetails(@PathVariable("associateId") String associateId) {
		return userEventRegistrationService.getUserRegisteredEventList(associateId);
	}
}