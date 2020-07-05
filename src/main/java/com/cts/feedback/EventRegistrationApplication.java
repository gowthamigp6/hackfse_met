package com.cts.feedback;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import com.cts.feedback.event.EventSummaryDetails;
import com.cts.feedback.service.EventSummaryDetailsService;
import com.cts.feedback.utils.FeedbackConstants;

@SpringBootApplication
@EnableCircuitBreaker
public class EventRegistrationApplication implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(EventRegistrationApplication.class.getName());

	@Autowired
	private EventSummaryDetailsService eventSummaryDetailsService;

	public static void main(String... args) {
		SpringApplication.run(EventRegistrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		EventSummaryDetails eventSummaryDetails = new EventSummaryDetails("event1", "eventName", "eventDesc", "Chennai",
				"chennai", new Date(),FeedbackConstants.REGISTERED, "admin", "Gowthami");
		eventSummaryDetailsService.saveEventSummaryDetails(eventSummaryDetails);
		
	}

}
