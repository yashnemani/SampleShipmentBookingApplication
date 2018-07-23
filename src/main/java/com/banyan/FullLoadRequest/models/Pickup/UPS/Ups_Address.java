package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Ups_Address {

	private String addressLine;
	private String city;
	private String stateCode;
	private String postalCode;
	private String countryCode;

	public Ups_Address() {
	}

	public Ups_Address(Builder build) {

		this.addressLine = build.addressLine;
		this.city = build.city;
		this.stateCode = build.stateCode;
		this.postalCode = build.postalCode;
		this.countryCode = build.countryCode;
	}

	public static class Builder {

		private String addressLine;
		private String city;
		private String stateCode;
		private String postalCode;
		private String countryCode;

		public Builder setAddressLine(String addressLine) {
			this.addressLine = addressLine;
			return this;
		}

		public Builder setCity(String city) {
			this.city = city;
			return this;
		}

		public Builder setStateCode(String stateCode) {
			this.stateCode = stateCode;
			return this;
		}

		public Builder setPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		public Builder setCountryCode(String countryCode) {
			this.countryCode = countryCode;
			return this;
		}

		public Ups_Address build() {
			return new Ups_Address(this);
		}

	}

	@JsonProperty("AddressLine")
	public String getAddressLine() {
		return addressLine;
	}

	@JsonProperty("City")
	public String getCity() {
		return city;
	}

	@JsonProperty("StateProvinceCode")
	public String getStateCode() {
		return stateCode;
	}

	@JsonProperty("PostalCode")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("CountryCode")
	public String getCountryCode() {
		return countryCode;
	}
}
