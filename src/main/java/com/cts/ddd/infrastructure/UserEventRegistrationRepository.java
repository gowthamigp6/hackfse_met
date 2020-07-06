package com.cts.ddd.infrastructure;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.feedback.event.UserEventRegistration;

@Repository
public interface UserEventRegistrationRepository extends MongoRepository<UserEventRegistration, String>{
	
	@Query("{eventSummaryDetails.eventId:'?0'}")
	public List<UserEventRegistration> getByEventId(String eventId);
	
	 @Query("{'user.userId' : ?0}")
	public List<UserEventRegistration> getUserRegisteredEventList(String userId);
}
