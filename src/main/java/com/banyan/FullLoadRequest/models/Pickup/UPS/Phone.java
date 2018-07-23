package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Phone {

	private String number;
	private String ext;

	public Phone() {}
	
	public Phone(String number) {
		this.number = number;
	}

	@JsonProperty("Number")
	public String getNumber() {
		return number;
	}
	@JsonProperty("Extension")
	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

}
