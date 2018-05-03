package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class AuthenticationData implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Username;
	private String Password;
	private String ClientRefNum;

	@JsonProperty("Username")
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	@JsonProperty("Password")
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	@JsonProperty("ClientRefNum")
	public String getClientRefNum() {
		return ClientRefNum;
	}

	public void setClientRefNum(String clientRefNum) {
		ClientRefNum = clientRefNum;
	}

	public AuthenticationData() {
		Username = "NexterusWS";
		this.Password = "N3Xt3Ru5W5";
	}

}
