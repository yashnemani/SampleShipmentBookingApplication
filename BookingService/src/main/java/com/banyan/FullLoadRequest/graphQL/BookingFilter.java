package com.banyan.FullLoadRequest.graphQL;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.types.GraphQLType;
@GraphQLType(name = "BookingFilter")
public class BookingFilter {

	 @GraphQLInputField
	private Integer id;
	 @GraphQLInputField
	private Integer provider;
	 @GraphQLInputField
	private String status;
	 @GraphQLInputField
	private String state;

	public BookingFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("BookingId")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("Provider")
	public Integer getProvider() {
		return provider;
	}

	public void setProvider(Integer provider) {
		this.provider = provider;
	}

	@JsonProperty("Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("State")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
