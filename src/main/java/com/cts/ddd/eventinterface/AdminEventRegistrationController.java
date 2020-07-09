package com.cts.ddd.eventinterface;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.ddd.application.EmailTemplateService;
import com.cts.ddd.application.EventSummaryDetailsService;
import com.cts.ddd.application.UserEventRegistrationService;
import com.cts.feedback.event.EventSummaryDetails;
import com.cts.feedback.event.UserEventRegistration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
public class AdminEventRegistrationController {
	
	@Autowired
	private UserEventRegistrationService userEventRegistrationService;
	
	@Autowired
	private EmailTemplateService emailTemplateService;
	
	@Autowired
	private EventSummaryDetailsService eventSummaryDetailsService;

	/**
	 * Create the event in the system
	 * @param eventSummaryDetails
	 * @return
	 */
	@PostMapping(value = "/eventDetails/create")
	public ResponseEntity<String> createEvent(@RequestBody EventSummaryDetails eventSummaryDetails) {
		String message = "";
		EventSummaryDetails eventData = eventSummaryDetailsService.saveEventSummaryDetails(eventSummaryDetails);
		if (eventData != null) {
			message = "User Details Saved Successfully";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else {
			message = "Error in saving the details";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

	}
	
	/**
	 * Trigger email to the users for the created events
	 * @param eventId
	 * @return
	 */
	@PostMapping(value = "/emailTemplate/sendEmail/{eventId}", headers = "Accept=application/json")
	public ResponseEntity<String> sendMailToUserEvent(@PathVariable("eventId") String eventId) {
		String message = "";
		try {
			emailTemplateService.sendMailToUserEvent(eventId);
			message = "You successfully uploaded ";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Fail to upload Profile Picture";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	public List<EventSummaryDetails> fallback() {
		return new ArrayList<>();
	}
	
	/**
	 * View all the created event details
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping(value = "/eventDetails/getEventDetails", headers = "Accept=application/json")
	public @ResponseBody List<EventSummaryDetails> getSummaryEventDetails() {
		return eventSummaryDetailsService.getEventSummaryDetails();
	}

	/**
	 * View user details who have registered for the events
	 * @return
	 */
	@GetMapping(value = "/eventDetails/getUserEventDetails", headers = "Accept=application/json")
	public @ResponseBody List<UserEventRegistration> getUserEventList() {
		return userEventRegistrationService.getUserEventList();
	}

	
}
