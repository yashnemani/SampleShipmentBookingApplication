package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String contactName;
	private String phone;
	private String phoneExt;
	private String fax;

	public Contact(Builder build) {
		this.firstName = build.firstName;
		this.lastName = build.lastName;
		this.contactName = build.contactName;
		this.phone = build.phone;
		this.phoneExt = build.phoneExt;
		this.email = build.email;
		this.fax = build.fax;
	}

	public Contact() {
	}

	private String email;

	public static class Builder {
		private String firstName;
		private String lastName;
		private String contactName;
		private String phone;
		private String phoneExt;
		private String fax;
		private String email;

		public Builder firstNameIs(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastNameIs(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder contactNameIs(String contactName) {
			this.contactName = contactName;
			return this;
		}

		public Builder phoneIs(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder phoneExtIs(String phoneExt) {
			this.phoneExt = phoneExt;
			return this;
		}

		public Builder faxIs(String fax) {
			this.fax = fax;
			return this;
		}

		public Builder emailIs(String email) {
			this.email = email;
			return this;
		}

		public Contact build() {
			return new Contact(this);
		}
	}
	@JsonProperty("FirstName")
	public String getFirstName() {
		return firstName;
	}
	@JsonProperty("LastName")
	public String getLastName() {
		return lastName;
	}
	@JsonProperty("ContactName")
	public String getContactName() {
		return contactName;
	}
	@JsonProperty("Phone")
	public String getPhone() {
		return phone;
	}
	@JsonProperty("PhoneExt")
	public String getPhoneExt() {
		return phoneExt;
	}
	@JsonProperty("Fax")
	public String getFax() {
		return fax;
	}
	@JsonProperty("Email")
	public String getEmail() {
		return email;
	}

}
