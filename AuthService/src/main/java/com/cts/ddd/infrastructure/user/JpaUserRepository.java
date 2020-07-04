package com.cts.ddd.infrastructure.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cts.feedback.user.User;



@Repository
public interface JpaUserRepository extends MongoRepository<User, String>{

}
