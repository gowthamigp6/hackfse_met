package com.cts.ddd.application;

import java.util.List;
import com.cts.feedback.event.EventSummaryDetails;

public interface EventSummaryDetailsService {

	public List<EventSummaryDetails> getEventSummaryDetails();

	public EventSummaryDetails saveEventSummaryDetails(EventSummaryDetails userEventRegistration);
}