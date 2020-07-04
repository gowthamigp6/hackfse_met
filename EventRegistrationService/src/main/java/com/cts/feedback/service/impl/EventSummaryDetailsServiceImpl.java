package com.cts.feedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.feedback.event.EventSummaryDetails;
import com.cts.feedback.repository.EventSummaryDetailsRepository;
import com.cts.feedback.repository.UserEventRegistrationRepository;
import com.cts.feedback.service.EventSummaryDetailsService;
import com.cts.feedback.utils.FeedbackConstants;

@Service
@Transactional
public class EventSummaryDetailsServiceImpl implements EventSummaryDetailsService {

	@Autowired
	private EventSummaryDetailsRepository eventRepository;
	
	@Autowired
	private UserEventRegistrationRepository userEventRegistrationRepository;
	
	@Override
	public List<EventSummaryDetails> getEventSummaryDetails() {
		return eventRepository.getEventSummaryDetails(FeedbackConstants.REGISTERED);
	}

	@Override
	public void saveEventSummaryDetails(EventSummaryDetails userEventRegistration) {
		eventRepository.save(userEventRegistration);
	}

}
