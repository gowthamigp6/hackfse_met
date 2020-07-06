package com.cts.ddd;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import com.cts.ddd.application.EventSummaryDetailsService;
import com.cts.feedback.event.EventSummaryDetails;
import com.cts.ddd.utils.FeedbackConstants;

@SpringBootApplication
@EnableCircuitBreaker
public class EventRegistrationApplication implements CommandLineRunner {

	@Autowired
	private EventSummaryDetailsService eventSummaryDetailsService;

	public static void main(String... args) {
		SpringApplication.run(EventRegistrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		EventSummaryDetails eventSummaryDetails = new EventSummaryDetails("event1", "eventName", "eventDesc", "Chennai",
				"chennai", new Date(), FeedbackConstants.REGISTERED, "admin", "Gowthami");
		eventSummaryDetailsService.saveEventSummaryDetails(eventSummaryDetails);
		eventSummaryDetails = new EventSummaryDetails("event2", "eventName", "eventDesc", "Chennai", "chennai",
				new Date(), FeedbackConstants.REGISTERED, "admin", "Gowthami");
		eventSummaryDetailsService.saveEventSummaryDetails(eventSummaryDetails);

	}

}
