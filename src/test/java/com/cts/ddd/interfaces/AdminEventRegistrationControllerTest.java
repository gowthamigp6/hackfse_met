//package com.cts.ddd.interfaces;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.cts.ddd.infrastructure.EventSummaryDetailsRepository;
//import com.cts.ddd.infrastructure.UserEventRegistrationRepository;
//import com.cts.ddd.utils.FeedbackConstants;
//import com.cts.feedback.event.EventSummaryDetails;
//import com.cts.feedback.event.UserEventRegistration;
//import com.cts.feedback.user.User;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest({ "server.port:0", "eureka.client.enabled:false" })
//@AutoConfigureMockMvc
//public class AdminEventRegistrationControllerTest {
//
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	private MockMvc mockMvc;
//
//	protected static SpringApplication application;
//
//	@MockBean
//	private UserEventRegistrationRepository userEventRegistrationRepository;
//
//	@MockBean
//	private EventSummaryDetailsRepository eventSummaryDetailsRepository;
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
//
//	@Test
//	public void testGetRegisteredEventDetails() throws Exception {
//
//		String URI = "/registerDetails/get/user01";
//		Mockito.when(userEventRegistrationRepository.getUserRegisteredEventList("user01"))
//				.thenReturn(getUserEventRegistration());
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		String expectedJson = this.mapToJson(getUserEventRegistration());
//		String outputInJson = result.getResponse().getContentAsString();
//		System.out.println("expected" + expectedJson);
//		System.out.println("outputIn" + outputInJson);
//		assertThat(outputInJson).isEqualTo(expectedJson);
//	}
//
//	public List<UserEventRegistration> getUserEventRegistration() {
//		List<UserEventRegistration> regList = new ArrayList<>();
//		EventSummaryDetails eventSummaryDetails = new EventSummaryDetails("event1", "eventName", "eventDesc", "Chennai",
//				"chennai", null, FeedbackConstants.REGISTERED, "admin", "Gowthami");
//		User user = new User("admin", "Welcome", "admin", "Gowthami", "dummy@gmail.com");
//		UserEventRegistration reg = new UserEventRegistration(user, eventSummaryDetails, FeedbackConstants.REGISTERED);
//		regList.add(reg);
//		return regList;
//	}
//
//	private String mapToJson(Object object) throws JsonProcessingException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		return objectMapper.writeValueAsString(object);
//	}
//
//	@Test
//	public void testSaveEvent() throws Exception {
//		List<String> eventList = new ArrayList<>();
//		eventList.add("event1");
//		String inputInJson = this.mapToJson(eventList);
//		String URI = "/registerDetails/saveEvent/user01";
//		Mockito.when(eventSummaryDetailsRepository.getByEventId("event1")).thenReturn(getEventSummaryDetails());
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
//				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
//		;
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		MockHttpServletResponse response = result.getResponse();
//		System.out.println("expected" + response.getStatus());
//
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//	}
//
//	/**
//	 * Create the event in the system
//	 * @param eventSummaryDetails
//	 * @return
//	 */
//	@PostMapping(value = "/eventDetails/create")
//	public ResponseEntity<String> createEvent(@RequestBody EventSummaryDetails eventSummaryDetails) {
//		String message = "";
//		EventSummaryDetails eventData = eventSummaryDetailsService.saveEventSummaryDetails(eventSummaryDetails);
//		if (eventData != null) {
//			message = "User Details Saved Successfully";
//			return ResponseEntity.status(HttpStatus.OK).body(message);
//		} else {
//			message = "Error in saving the details";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//		}
//
//	}
//	
//	
//	public List<EventSummaryDetails> getEventSummaryDetails() {
//		List<EventSummaryDetails> eventList = new ArrayList<>();
//		EventSummaryDetails eventSummaryDetails = new EventSummaryDetails("event1", "eventName", "eventDesc", "Chennai",
//				"chennai", null, FeedbackConstants.REGISTERED, "admin", "Gowthami");
//		eventList.add(eventSummaryDetails);
//		return eventList;
//	}
//
//}
