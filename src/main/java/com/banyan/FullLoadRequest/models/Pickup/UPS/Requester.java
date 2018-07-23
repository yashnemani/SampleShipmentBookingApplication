package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Requester {

	private String attnName;
	private String email;
	private String name;
	private Phone phone;
	private String thirdParty;

	public Requester() {
	}

	public Requester(Builder build) {

		this.attnName = build.attnName;
		this.email = build.email;
		this.name = build.name;
		this.phone = build.phone;
		this.thirdParty = build.thirdParty;
	}

	public static class Builder {

		private String attnName;
		private String email;
		private String name;
		private Phone phone;
		private String thirdParty;

		public Builder setAttnName(String attnName) {
			this.attnName = attnName;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setPhone(Phone phone) {
			this.phone = phone;
			return this;
		}

		public Builder setThirdParty(String thirdParty) {
			this.thirdParty = thirdParty;
			return this;
		}

		public Requester build() {
			return new Requester(this);
		}
	}

	@JsonProperty("AttentionName")
	public String getAttnName() {
		return attnName;
	}

	@JsonProperty("EMailAddress")
	public String getEmail() {
		return email;
	}

	@JsonProperty("Name")
	public String getName() {
		return name;
	}

	@JsonProperty("Phone")
	public Phone getPhone() {
		return phone;
	}
	@JsonProperty("ThirdPartyIndicator")
	public String getThirdParty() {
		return thirdParty;
	}
}
