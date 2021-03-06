package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class UPS_Security {
	
	@Autowired
	private UsernameToken userToken;
	@Autowired
	private ServiceAccessToken accessToken;

	public UPS_Security() {
	}

	@JsonProperty("UsernameToken")
	public UsernameToken getUserToken() {
		return userToken;
	}

	@JsonProperty("UPSServiceAccessToken")
	public ServiceAccessToken getAccessToken() {
		return accessToken;
	}

}
