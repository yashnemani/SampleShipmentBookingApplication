package com.banyan.FullLoadRequest.models.Project44;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Location44 {
	@Autowired
	private Address44 address44;
	@Autowired
	private Contact44 contact44;
	private String id;

	public Location44() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location44(Address44 address44, Contact44 contact44, String id) {
		super();
		this.address44 = address44;
		this.contact44 = contact44;
		this.id = id;
	}

	@JsonProperty("address")
	public Address44 getAddress() {
		return address44;
	}
	@JsonProperty("contact")
	public Contact44 getContact() {
		return contact44;
	}
	@JsonProperty("id")
	public String getId() {
		return id;
	}
	
	

}
