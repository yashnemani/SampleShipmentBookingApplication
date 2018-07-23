package com.banyan.FullLoadRequest.models.Project44;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class GeoCoordinates {

	private Integer latitude;
	private Integer longitude;

	public GeoCoordinates() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeoCoordinates(Integer latitude, Integer longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@JsonProperty("latitude")
	public Integer getLatitude() {
		return latitude;
	}

	@JsonProperty("longitude")
	public Integer getLongitude() {
		return longitude;
	}
}
