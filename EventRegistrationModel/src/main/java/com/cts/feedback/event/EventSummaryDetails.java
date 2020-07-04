package com.cts.feedback.event;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "event_summary_details")
@Data
public class EventSummaryDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    private String eventId;
	private String eventName;
	private String eventDesc;
	
	private String eventLocation;
	private String venueAddress;
	
	private Date eventDate;
    private String status;
    private String pocId;
    private String pocName;
    
	public EventSummaryDetails(String eventId, String eventName, String eventDesc, String eventLocation,
			String venueAddress, Date eventDate, String status, String pocId, String pocName) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDesc = eventDesc;
		this.eventLocation = eventLocation;
		this.venueAddress = venueAddress;
		this.eventDate = eventDate;
		this.status = status;
		this.pocId = pocId;
		this.pocName = pocName;
	}
   

}
