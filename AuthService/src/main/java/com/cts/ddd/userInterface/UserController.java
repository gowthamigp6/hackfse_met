package com.cts.ddd.userInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.ddd.application.UserService;
import com.cts.ddd.security.jwt.message.response.JwtResponse;
import com.cts.ddd.security.jwt.request.LoginForm;
import com.cts.ddd.security.jwt.security.JwtProvider;
import com.cts.ddd.security.jwt.security.UserPrinciple;
import com.cts.feedback.user.User;

//https://dzone.com/refcardz/getting-started-domain-driven?chapter=6

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/userDetails/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
		if (userDetails != null) {
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getRole()));
		} else {
			return ResponseEntity.ok("{status: \"Confirmation Pending\"}");
		}
	}

	@PostMapping(value = "/userDetails/create")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		String message = "";
		User userData = userService.addUser(user);
		if (userData != null) {
			message = "User Details Saved Successfully";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else {
			message = "Error in saving the details";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

	}

	@GetMapping(value = "/userDetails/get", headers = "Accept=application/json")
	public @ResponseBody Iterable<User> getAllUser() {
		return userService.getAllUser();
	}

	@DeleteMapping(value = "/userDetails/{id}", headers = "Accept=application/json")
	public ResponseEntity<User> deleteUser(@PathVariable("id") String userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
