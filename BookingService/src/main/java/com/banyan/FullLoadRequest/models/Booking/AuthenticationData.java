package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.leangen.graphql.annotations.GraphQLQuery;

@Component
public class AuthenticationData implements Serializable {

	private static final long serialVersionUID = 1L;
	@GraphQLQuery(name = "username")
	private String Username;
	@GraphQLQuery(name = "password")
	private String Password;
	@GraphQLQuery(name = "clientRefNum")
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
		// Beta
		/* Username = "NexterusWS"; this.Password = "N3Xt3Ru5W5";*/

		// Production
		Username = "WSPROD";
		this.Password = "Nexterus";
	}
	
	public AuthenticationData(Integer type) {
		if(type==0) {
		// Beta
		 Username = "NexterusWS"; this.Password = "N3Xt3Ru5W5";}
		if(type==1) {
		// Production
		Username = "WSPROD";
		this.Password = "Nexterus";}
	}

}
