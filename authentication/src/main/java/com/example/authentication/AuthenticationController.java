package com.example.authentication;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/accounts")
public class AuthenticationController {

	private static final String AUTH_MICROSERVICE_URL = "https://cloudpoolauth.azurewebsites.net";
	private static final String VALIDATION_ENDPOINT = "/authorize";

	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers;

	@Autowired
	private AuthenticationService authService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Users user) {
		try {
			String response = authService.register(user);
			if (!response.equals("Username is already used...")) {
				return ResponseEntity.ok("{\"status\": \"Success\", \"userID\": \"" + user.getUserID() + "\"}");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("{\"status\": \"Failure\", \"Error\": \"Username or Email are already used...\"}");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"status\": \"Error\", \"message\": \"" + e.getMessage() + "\"}");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users user) {
		try {
			String response = authService.login(user.getUsername(), user.getPassword());
			if (!response.equals("Incorrect pass/user") && !response.equals("Missing fields")) {
				return ResponseEntity.ok("{\"status\": \"Success\", \"JWT Token\": \"" + response + "\"}");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("{\"status\": \"Failure\", \"Error\": \"" + response + "\"}");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"status\": \"Error\", \"message\": \"" + e.getMessage() + "\"}");
		}
	}

	@PostMapping("/authorize")
	public ResponseEntity<Boolean> authorize(@RequestHeader("Authorization") String authorizationHeader) {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			System.out.println("Authorization header is missing or not properly formatted");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

		String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
		boolean isAuthorized = authService.authorizeUser(jwtToken);

		return isAuthorized ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
	}


}
