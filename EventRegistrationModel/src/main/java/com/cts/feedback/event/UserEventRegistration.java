package com.cts.feedback.event;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cts.feedback.user.User;

import lombok.Data;

@Document(collection = "user_registration")
@Data
public class UserEventRegistration {

	@Id
    private String id;
	
	@DBRef(lazy = true)
    private User user;
	
    @DBRef(lazy = true)
    private EventSummaryDetails eventSummaryDetails;
    
    private String status;

    public UserEventRegistration() {	
    }
    
	public UserEventRegistration(User user, EventSummaryDetails eventSummaryDetails, String status) {
		this.user = user;
		this.eventSummaryDetails = eventSummaryDetails;
		this.status = status;
	}
    
    
}
