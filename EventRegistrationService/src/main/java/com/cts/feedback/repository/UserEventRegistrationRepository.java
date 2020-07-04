package com.cts.feedback.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.cts.feedback.event.UserEventRegistration;

@Repository
public interface UserEventRegistrationRepository extends MongoRepository<UserEventRegistration, String>{
	
	
}
