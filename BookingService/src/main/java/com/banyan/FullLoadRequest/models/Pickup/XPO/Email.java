package com.banyan.FullLoadRequest.models.Pickup.XPO;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Email {

	private String email;

	public Email() {

	}

	public Email(String email) {
		super();
		this.email = email;
	}

	@JsonProperty("emailAddr")
	public String getEmail() {
		return email;
	}

}
