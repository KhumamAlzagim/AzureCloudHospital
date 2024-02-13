package com.example.authentication;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
@Table(name = "Users")

public class Users {

	@Id
	@GeneratedValue
	private Integer userID;
	private String username;
	private String password;
	private String role;
	private boolean twoFactorEnabled;
	private String email;

	public Users ()
	{

	}
	public Users(String username, String password, String email, String role, boolean twoFactorEnabled) {
		//this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.twoFactorEnabled = twoFactorEnabled;
	}



	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String Password) {
		this.password = Password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isTwoFactorEnabled() {
		return twoFactorEnabled;
	}

	public void setTwoFactorEnabled(boolean twoFactorEnabled) {
		this.twoFactorEnabled = twoFactorEnabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

