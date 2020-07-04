package com.cts.feedback.service;

import java.util.List;

import com.cts.feedback.event.UserEventRegistration;

public interface UserEventRegistrationService {
	
	public List<UserEventRegistration> getUserEventList();

	public void registerEventRegistration(String associateId,List<String> eventList) throws Exception;
}
