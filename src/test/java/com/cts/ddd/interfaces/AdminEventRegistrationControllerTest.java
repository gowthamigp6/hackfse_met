//package com.cts.ddd.interfaces;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
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
//import org.springframework.boot.test.context.SpringBootContextLoader;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.cts.ddd.domain.employee.EmployeeRegistration;
//import com.cts.ddd.domain.employee.Location;
//import com.cts.ddd.domain.employee.Vehicle;
//import com.cts.ddd.domain.trip.TravelDetails;
//import com.cts.ddd.domain.trip.TripDetails;
//import com.cts.ddd.domain.user.Address;
//import com.cts.ddd.domain.user.ContactInformation;
//import com.cts.ddd.domain.user.FullName;
//import com.cts.ddd.domain.user.User;
//import com.cts.ddd.infrastructure.TripDetailsRepository;
//import com.cts.ddd.interfaces.dto.TripDetailsConvertor;
//import com.cts.ddd.interfaces.dto.TripDetailsDTO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
////@SpringBootTest(classes = DddApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//
//@Profile("test")
////@RunWith(SpringRunner.class)
////@SpringBootTest(classes = {ServletWebServerFactoryAutoConfiguration.class,DddApplication.class}, 
////webEnvironment = WebEnvironment.RANDOM_PORT)
////@AutoConfigureMockMvc
//////@WebMvcTest(TripDetails.class)
////@DataMongoTest
////@ComponentScan("org.springframework.test.web.servlet.MockMvc")
////@TestPropertySource(locations = "classpath:application-test.properties")
////@ContextConfiguration(classes = SpringBootControllerTest.CustomLoader.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest({ "server.port:0", "eureka.client.enabled:false" })
//@AutoConfigureMockMvc
//public class SpringBootControllerTest {
//
////	 @Bean 
////	  ServletWebServerFactory servletWebServerFactory(){
////	  return new TomcatServletWebServerFactory();
////	  }
////	
//
////	@LocalServerPort
////	protected int port;
//
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	private MockMvc mockMvc;
//
//	@Autowired
//	private TripDetailsConvertor tripDetailsConvertor;
//
////	@Autowired
////	protected ApplicationContext context;
//
//	@MockBean
//	private TripDetailsRepository tripDetailsRepository;
//
//	protected static SpringApplication application;
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
//
//	@Test
//	public void testGetAllTripDetails() throws Exception {
//
//		String URI = "/tripDetails/get";
//		Mockito.when(tripDetailsRepository.getAllTripDetails()).thenReturn(getTripDetailsList());
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		String expectedJson = this.mapToJson(getConverterList());
//		String outputInJson = result.getResponse().getContentAsString();
//		System.out.println("expected"+expectedJson);
//		System.out.println("outputIn"+outputInJson);
//		assertThat(outputInJson).isEqualTo(expectedJson);
//	}
//	
//	@Test
//	public void TestGetCustomerTripDetails() throws Exception {
//		String userId="1";
//		String URI = "/tripDetails/customer/1";
//		Mockito.when(tripDetailsRepository.getCustomerTripDetails(userId)).thenReturn(getTripDetailsList());
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		String expectedJson = this.mapToJson(getConverterList());
//		String outputInJson = result.getResponse().getContentAsString();
//		System.out.println("expected"+expectedJson);
//		System.out.println("outputIn"+outputInJson);
//		assertThat(outputInJson).isEqualTo(expectedJson);
//	}
//
//	@Test
//	public void testGetEmployeeTripDetails() throws Exception {
//		String userId="1";
//		String URI = "/tripDetails/employee/1";
//		Mockito.when(tripDetailsRepository.getEmployeeTripDetails(userId)).thenReturn(getTripDetailsList());
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		String expectedJson = this.mapToJson(getConverterList());
//		String outputInJson = result.getResponse().getContentAsString();
//		System.out.println("expected"+expectedJson);
//		System.out.println("outputIn"+outputInJson);
//		assertThat(outputInJson).isEqualTo(expectedJson);
//	}
//
//	private String mapToJson(Object object) throws JsonProcessingException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		return objectMapper.writeValueAsString(object);
//	}
//
//	public static class CustomLoader extends SpringBootContextLoader {
//
//		protected SpringApplication getSpringApplication() {
//			application = super.getSpringApplication();
//			return application;
//
//		}
//	}
//
//	private List<TripDetailsDTO> getConverterList() {
//		List<TripDetailsDTO> tripDetailsDTOList = new ArrayList<TripDetailsDTO>();
//		for (TripDetails tripDetails : getTripDetailsList()) {
//			System.out.println(tripDetailsConvertor.convert(tripDetails));
//			tripDetailsDTOList.add((TripDetailsDTO) tripDetailsConvertor.convert(tripDetails));
//		}
//		return tripDetailsDTOList;
//	}
//
//	private List<TripDetails> getTripDetailsList() {
//		Location location = new Location();
//		location.setFromLocation("Chennai");
//		location.setToLocation("Bangalore");
//
//		Vehicle vehicle = new Vehicle();
//		vehicle.setSeater(Integer.valueOf("4"));
//		vehicle.setVehicleType("Car");
//
//		vehicle.setTravelCost(new BigDecimal("5000.00"));
//
//		EmployeeRegistration emp = new EmployeeRegistration();
//		emp.setVehicleNo("TN1234");
//		emp.setVehicle(vehicle);
//		emp.setLocation(location);
//
//		FullName fullName = new FullName("AdminUser", "AdminUser");
//		ContactInformation contactInformation = new ContactInformation();
//		contactInformation.setEmailId("gow.cute@gmail.com");
//		contactInformation.setTelephoneNo("1234567890");
//
//		Address address = new Address();
//		address.setCity("Vellore");
//		address.setDoorNo("123");
//		address.setPinCode("645678");
//		address.setPlotNo("No.11");
//		address.setStreetName("street 1");
//		User user = new User("emp01", "password", "employee", fullName, contactInformation, address);
//		emp.setUser(user);
//
//		TripDetails tripDetails = new TripDetails();
//		User tripUser = new User("user01", "password", "customer", fullName, contactInformation, address);
//
//		tripDetails.setUser(tripUser);
//		tripDetails.setRegistration(emp);
//		
//
//		TravelDetails travelDetails = new TravelDetails();
//
//		//tripDetails.setTravelDate(getTravelDate());
//		travelDetails.setTravelStatus("Booked");
//		travelDetails.setTravelTime("2:00 PM");
//		tripDetails.setTravelDetails(travelDetails);
//		List<TripDetails> tripDetailsList = new ArrayList<TripDetails>();
//		tripDetailsList.add(tripDetails);
//		return tripDetailsList;
//	}
//
//	private Date getTravelDate() {
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = null;
//		try {
//			date = formatter.parse("31/03/2015");
//			System.out.println("Date is: " + date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return date;
//	}
//}
