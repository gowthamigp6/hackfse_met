package com.cts.feedback.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.feedback.event.EventSummaryDetails;

@Repository
public interface EventSummaryDetailsRepository extends MongoRepository<EventSummaryDetails, String> {

//	 @Query("{ 'status=' : ?0 }")
	@Query("{status:'?0'}")
	public List<EventSummaryDetails> getEventSummaryDetails(String status);

	@Query("{eventId:'?0'}")
	public List<EventSummaryDetails> getByEventId(String eventId);
}
