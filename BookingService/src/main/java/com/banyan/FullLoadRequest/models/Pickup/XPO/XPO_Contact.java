package com.banyan.FullLoadRequest.models.Pickup.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class XPO_Contact {

	private String companyName;
	@Autowired
	private Email email;
	private String fullName;
	@Autowired
	private XPO_Phone xPO_Phone;

	public XPO_Contact() {

	}

	public XPO_Contact(Builder build) {

		this.companyName = build.companyName;
		this.email = build.email;
		this.fullName = build.fullName;
		this.xPO_Phone = build.xPO_Phone;
	}

	public static class Builder {

		private String companyName;
		@Autowired
		private Email email;
		private String fullName;
		@Autowired
		private XPO_Phone xPO_Phone;

		public Builder setCompanyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

		public Builder setEmail(Email email) {
			this.email = email;
			return this;
		}

		public Builder setFullName(String fullName) {
			this.fullName = fullName;
			return this;
		}

		public Builder setPhone(XPO_Phone xPO_Phone) {
			this.xPO_Phone = xPO_Phone;
			return this;
		}

		public XPO_Contact build() {
			return new XPO_Contact(this);
		}
	}

	@JsonProperty("companyName")
	public String getCompanyName() {
		return companyName;
	}

	@JsonProperty("email")
	public Email getEmail() {
		return email;
	}

	@JsonProperty("fullName")
	public String getFullName() {
		return fullName;
	}

	@JsonProperty("phone")
	public XPO_Phone getPhone() {
		return xPO_Phone;
	}
}
