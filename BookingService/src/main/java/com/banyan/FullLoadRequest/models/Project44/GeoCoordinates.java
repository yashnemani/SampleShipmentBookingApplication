package com.banyan.FullLoadRequest.models.Project44;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class GeoCoordinates {

	private double latitude;
	private double longitude;

	public GeoCoordinates() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeoCoordinates(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@JsonProperty("latitude")
	public double getLatitude() {
		return latitude;
	}

	@JsonProperty("longitude")
	public double getLongitude() {
		return longitude;
	}
}
