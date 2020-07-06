package com.cts.ddd.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.ddd.application.UserEventRegistrationService;
import com.cts.ddd.infrastructure.EventSummaryDetailsRepository;
import com.cts.ddd.infrastructure.UserEventRegistrationRepository;
import com.cts.ddd.utils.FeedbackConstants;
import com.cts.feedback.event.EventSummaryDetails;
import com.cts.feedback.event.UserEventRegistration;

import com.cts.feedback.user.User;

@Service
@Transactional
public class UserEventRegistrationServiceImpl implements UserEventRegistrationService {

	@Autowired
	private UserEventRegistrationRepository userEventRegistrationRepository;

	@Autowired
	private EventSummaryDetailsRepository eventSummaryDetailsRepository;

	@Override
	public List<UserEventRegistration> getUserEventList() {
		return userEventRegistrationRepository.findAll();
	}

	@Override
	public void registerEventRegistration(String associateId, List<String> eventList) {
		UserEventRegistration reg = null;
		for (String eventId : eventList) {
			EventSummaryDetails eventSummaryDetails = eventSummaryDetailsRepository.getByEventId(eventId).get(0);
			User user = new User();
			user.setUserId(associateId);
			reg = new UserEventRegistration(user, eventSummaryDetails, FeedbackConstants.REGISTERED);
			userEventRegistrationRepository.save(reg);
		}
	}

	@Override
	public List<UserEventRegistration> getByEventId(String eventId) {
		return userEventRegistrationRepository.getByEventId(eventId);
	}

	@Override
	public List<UserEventRegistration> getUserRegisteredEventList(String associateId) {
		return userEventRegistrationRepository.getUserRegisteredEventList(associateId);
	}

}
