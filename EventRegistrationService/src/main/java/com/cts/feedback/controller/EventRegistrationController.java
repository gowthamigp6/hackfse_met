package com.cts.feedback.controller;

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

import com.cts.feedback.event.EventSummaryDetails;
import com.cts.feedback.event.UserEventRegistration;
import com.cts.feedback.service.EmailTemplateService;
import com.cts.feedback.service.EventSummaryDetailsService;
import com.cts.feedback.service.UserEventRegistrationService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
public class EventRegistrationController {

	@Autowired
	private UserEventRegistrationService userEventRegistrationService;

	@Autowired
	private EventSummaryDetailsService eventSummaryDetailsService;

	@Autowired
	private EmailTemplateService emailTemplateService;

	@GetMapping(value = "/summaryDetails/getSummaryEventDetails", headers = "Accept=application/json")
	public @ResponseBody List<EventSummaryDetails> getSummaryEventDetails() {
		List<EventSummaryDetails> infoList = eventSummaryDetailsService.getEventSummaryDetails();
		return infoList;
	}

	@PostMapping(value = "/eventDetails/saveEvent/{associateId}")
	public ResponseEntity<String> createUser(@PathVariable("associateId") String associateId,
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

	@GetMapping(value = "/eventDetails/getUserEventDetails", headers = "Accept=application/json")
	public @ResponseBody List<UserEventRegistration> getUserEventList() {
		List<UserEventRegistration> infoList = userEventRegistrationService.getUserEventList();
		return infoList;
	}

	@GetMapping(value = "/emailTemplate/sendEmail", headers = "Accept=application/json")
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

}