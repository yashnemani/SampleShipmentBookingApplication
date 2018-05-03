package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banyan.FullLoadRequest.models.Pickup.UPS.ShipFrom.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class ShipTo {

	private String attnName;
	private String name;
	@Autowired
	private Ups_Address ups_Address;
	@Autowired
	private Phone phone;
	private String email;
	
	public ShipTo() {
	}

	public ShipTo(Builder build) {

		this.attnName = build.attnName;
		this.name = build.name;
		this.ups_Address = build.ups_Address;
		this.phone = build.phone;
		this.email = build.email;
	}

	public static class Builder {

		private String attnName;
		private String name;
		@Autowired
		private Ups_Address ups_Address;
		@Autowired
		private Phone phone;
		private String email;

		public Builder setAttnName(String attnName) {
			this.attnName = attnName;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setAddress(Ups_Address ups_Address) {
			this.ups_Address = ups_Address;
			return this;
		}

		public Builder setPhone(Phone phone) {
			this.phone = phone;
			return this;
		}
		
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public ShipTo build() {
			return new ShipTo(this);
		}
	}

	@JsonProperty("AttentionName")
	public String getAttnName() {
		return attnName;
	}

	@JsonProperty("Name")
	public String getName() {
		return name;
	}

	@JsonProperty("Address")
	public Ups_Address getAddress() {
		return ups_Address;
	}

	@JsonProperty("Phone")
	public Phone getPhone() {
		return phone;
	}
	
	@JsonProperty("EMailAddress")
	public String getEmail() {
		return email;
	}
}
