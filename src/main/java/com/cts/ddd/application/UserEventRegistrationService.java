package com.cts.ddd.application;

import java.util.List;

import com.cts.feedback.event.UserEventRegistration;

public interface UserEventRegistrationService {
	
	public List<UserEventRegistration> getUserEventList();

	public void registerEventRegistration(String associateId,List<String> eventList);
	
	public List<UserEventRegistration> getByEventId(String eventId);
	
	public List<UserEventRegistration> getUserRegisteredEventList(String associateId);
}
