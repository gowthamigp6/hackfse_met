package com.cts.ddd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.ddd.infrastructure.user.JpaUserRepository;
import com.cts.ddd.security.jwt.request.LoginForm;
import com.cts.feedback.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest({ "server.port:0", "eureka.client.enabled:false" })
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class UserServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;


	@MockBean
	private JpaUserRepository jpaUserRepository;

	protected static SpringApplication application;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	
	@Test
	public void testGetUser() throws Exception {

		String URI = "/userDetails/get";
		Mockito.when(jpaUserRepository.findAll()).thenReturn(getUsersList());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(getUsersList());
		String outputInJson = result.getResponse().getContentAsString();
		System.out.println("expected"+expectedJson);
		System.out.println("outputIn"+outputInJson);
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void  testAuthenticateUser() throws Exception {
		LoginForm loginRequest = new LoginForm();
		loginRequest.setUsername("admin");
		loginRequest.setPassword("password");
		String inputInJson = this.mapToJson(loginRequest);
		String URI = "/userDetails/signin"
				+ "";
		Mockito.when(jpaUserRepository.findById(Mockito.anyString())).thenReturn(getUsersLiOptional());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("expected"+response.getStatus());
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	
	@Test
	public void testCreateUser() throws Exception {
		String inputInJson = this.mapToJson(getUsersList().get(0));
		String URI = "/userDetails/create";
		Mockito.when(jpaUserRepository.save(Mockito.any(User.class))).thenReturn(getUsersList().get(0));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("expected"+response.getStatus());
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testDeleteUser() throws Exception{
		//Mockito.doNothing().when(userRepository).deleteUser(552);
		String URI = "/userDetails/552";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}
 
	
	
	private List<User> getUsersList(){
		List<User> userDetailList= new ArrayList<>();

		

		User user = new User("admin", "password", "admin", "admin");
		userDetailList.add(user);
		return userDetailList;
	}
	
	
	private Optional<User> getUsersLiOptional(){
		return Optional.of(getUsersList().get(0));
	}
	
	private Optional<User> getUsersLiOptionalEmpty(){
		return Optional.of(new User());
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}


}
