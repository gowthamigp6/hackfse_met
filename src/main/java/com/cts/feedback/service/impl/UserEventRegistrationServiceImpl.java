package com.cts.feedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.feedback.event.EventSummaryDetails;
import com.cts.feedback.event.UserEventRegistration;
import com.cts.feedback.repository.EventSummaryDetailsRepository;
import com.cts.feedback.repository.UserEventRegistrationRepository;
import com.cts.feedback.service.UserEventRegistrationService;
import com.cts.feedback.user.User;
import com.cts.feedback.utils.FeedbackConstants;

@Service
@Transactional
public class UserEventRegistrationServiceImpl implements UserEventRegistrationService {

	@Autowired
	private UserEventRegistrationRepository eventEmployeeInfoRepository;
	
	@Autowired
	private EventSummaryDetailsRepository eventSummaryDetailsRepository;

	@Override
	public List<UserEventRegistration> getUserEventList() {
		return eventEmployeeInfoRepository.findAll();
	}

	@Override
	public void registerEventRegistration(String associateId, List<String> eventList) throws Exception {
		UserEventRegistration reg = null;
		for (String eventId : eventList) {
			EventSummaryDetails eventSummaryDetails = eventSummaryDetailsRepository.getByEventId(eventId).get(0);
			User user = new User();
			user.setUserId(associateId);
			reg = new UserEventRegistration(user, eventSummaryDetails, FeedbackConstants.REGISTERED);
			eventEmployeeInfoRepository.save(reg);
		}
	}

}
