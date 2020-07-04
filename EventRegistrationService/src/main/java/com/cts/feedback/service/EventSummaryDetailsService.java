package com.cts.feedback.service;

import java.util.List;
import com.cts.feedback.event.EventSummaryDetails;

public interface EventSummaryDetailsService {

	public List<EventSummaryDetails> getEventSummaryDetails();

	public void saveEventSummaryDetails(EventSummaryDetails userEventRegistration);
}