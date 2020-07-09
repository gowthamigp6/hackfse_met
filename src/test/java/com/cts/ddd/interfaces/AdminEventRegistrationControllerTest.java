package com.cts.ddd.interfaces;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.ddd.application.EmailTemplateService;
import com.cts.ddd.infrastructure.EventSummaryDetailsRepository;
import com.cts.ddd.infrastructure.UserEventRegistrationRepository;
import com.cts.ddd.utils.FeedbackConstants;
import com.cts.feedback.event.EventSummaryDetails;
import com.cts.feedback.event.UserEventRegistration;
import com.cts.feedback.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest({ "server.port:0", "eureka.client.enabled:false" })
@AutoConfigureMockMvc
public class AdminEventRegistrationControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	protected static SpringApplication application;

	@MockBean
	private UserEventRegistrationRepository userEventRegistrationRepository;

	@MockBean
	private EventSummaryDetailsRepository eventRepository;

	@MockBean
	private EmailTemplateService emailTemplateService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetUserEventDetails() throws Exception {

		String URI = "/eventDetails/getUserEventDetails";
		Mockito.when(userEventRegistrationRepository.findAll()).thenReturn(getUserEventRegistration());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(getUserEventRegistration());
		String outputInJson = result.getResponse().getContentAsString();
		System.out.println("expected" + expectedJson);
		System.out.println("outputIn" + outputInJson);
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testGetSummaryEventDetails() throws Exception {

		String URI = "/eventDetails/getEventDetails";
		Mockito.when(eventRepository.getEventSummaryDetails(FeedbackConstants.REGISTERED))
				.thenReturn(getEventSummaryDetails());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(getEventSummaryDetails());
		String outputInJson = result.getResponse().getContentAsString();
		System.out.println("expected" + expectedJson);
		System.out.println("outputIn" + outputInJson);
		assertThat(outputInJson).isEqualTo(expectedJson);
	}


	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	private List<EventSummaryDetails> getEventSummaryDetails() {
		List<EventSummaryDetails> eventList = new ArrayList<>();
		EventSummaryDetails eventSummaryDetails = new EventSummaryDetails("event1", "eventName", "eventDesc", "Chennai",
				"chennai", null, FeedbackConstants.REGISTERED, "admin", "Gowthami");
		eventList.add(eventSummaryDetails);
		return eventList;
	}

	private List<UserEventRegistration> getUserEventRegistration() {
		List<UserEventRegistration> regList = new ArrayList<>();
		EventSummaryDetails eventSummaryDetails = new EventSummaryDetails("event1", "eventName", "eventDesc", "Chennai",
				"chennai", null, FeedbackConstants.REGISTERED, "admin", "Gowthami");
		User user = new User("admin", "Welcome", "admin", "Gowthami", "dummy@gmail.com");
		UserEventRegistration reg = new UserEventRegistration(user, eventSummaryDetails, FeedbackConstants.REGISTERED);
		regList.add(reg);
		return regList;
	}

}
