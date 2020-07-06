package com.cts.ddd.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.ddd.application.EventSummaryDetailsService;
import com.cts.ddd.infrastructure.EventSummaryDetailsRepository;
import com.cts.ddd.utils.FeedbackConstants;
import com.cts.feedback.event.EventSummaryDetails;


@Service
@Transactional
public class EventSummaryDetailsServiceImpl implements EventSummaryDetailsService {

	@Autowired
	private EventSummaryDetailsRepository eventRepository;
	
	
	@Override
	public List<EventSummaryDetails> getEventSummaryDetails() {
		return eventRepository.getEventSummaryDetails(FeedbackConstants.REGISTERED);
	}

	@Override
	public EventSummaryDetails saveEventSummaryDetails(EventSummaryDetails userEventRegistration) {
		return eventRepository.save(userEventRegistration);
	}

}
