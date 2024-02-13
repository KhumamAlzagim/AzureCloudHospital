package com.example.authentication;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService{

	@Autowired
	private UsersRepository usersRepository;
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public String register(Users user) {
		if (usersRepository.getUser(user.getUsername()) == null) {
			usersRepository.save(user);

			return Jwts.builder()
					.setSubject(user.getUsername())
					.claim("UserID", user.getUserID())
					.signWith(key)
					.compact();
		} else {
			return "Username is already used...";
		}
	}

	public String login(String username, String Password) {
		if (!username.isEmpty() && !Password.isEmpty()) {
			Users temp = usersRepository.getUser(username);
			if (temp != null && temp.getPassword().equals(Password)) {
				return Jwts.builder()
						.setSubject(username)
						.claim("UserID", temp.getUserID())
						.signWith(key)
						.compact();
			} else {
				System.out.println("Incorrect pass/user");
				return "Incorrect pass/user";
			}
		} else {
			System.out.println("Missing fields");
			return "Missing fields";
		}
	}

	public boolean authorizeUser(String token) {
		try {
			Jws<Claims> jws = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			System.out.print("Invalid Token!");
			return false;
		}

	}

}