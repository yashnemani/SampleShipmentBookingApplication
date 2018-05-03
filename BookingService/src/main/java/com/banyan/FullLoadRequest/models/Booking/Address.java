package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	private String address1;
	private String address2;

	public Address() {
	}

	private String city;
	private String countryName;
	private String countryCode;
	private String state;
	private String zipcode;
	private String locationName;
	@JsonProperty("Address1")
	public String getAddress1() {
		return address1;
	}
	@JsonProperty("Address2")
	public String getAddress2() {
		return address2;
	}
	@JsonProperty("City")
	public String getCity() {
		return city;
	}
	@JsonProperty("CountryName")
	public String getCountryName() {
		return countryName;
	}
	@JsonProperty("CountryCode")
	public String getCountryCode() {
		return countryCode;
	}
	@JsonProperty("State")
	public String getState() {
		return state;
	}
	@JsonProperty("Zipcode")
	public String getZipcode() {
		return zipcode;
	}
	@JsonProperty("LocationName")
	public String getLocationName() {
		return locationName;
	}

	public Address(Builder build) {
		this.address1 = build.address1;
		this.address2 = build.address2;
		this.city = build.city;
		this.countryName = build.countryName;
		this.countryCode = build.countryCode;
		this.state = build.state;
		this.zipcode = build.zipcode;
		this.locationName = build.locationName;
	}

	private String email;

	public static class Builder {
		private String address1;
		private String address2;
		private String city;
		private String countryName;
		private String countryCode;
		private String state;
		private String zipcode;
		private String locationName;

		public Builder setAddress1(String address1) {
			this.address1 = address1;
			return this;
		}

		public Builder setAddress2(String address2) {
			this.address2 = address2;
			return this;
		}

		public Builder setCity(String city) {
			this.city = city;
			return this;
		}

		public Builder setCountryName(String countryName) {
			this.countryName = countryName;
			return this;
		}

		public Builder setCountryCode(String countryCode) {
			this.countryCode = countryCode;
			return this;
		}

		public Builder setState(String state) {
			this.state = state;
			return this;
		}

		public Builder setZipcode(String zipcode) {
			this.zipcode = zipcode;
			return this;
		}

		public Builder setLocationName(String locationName) {
			this.locationName = locationName;
			return this;
		}

		public Address build() {
			return new Address(this);
		}
	}

}
