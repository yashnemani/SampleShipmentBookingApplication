package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Consignee implements Serializable{

	private static final long serialVersionUID = 1L;
	private Contact contactInfo;
	private Address addressInfo;
	private String companyName;
	private String note;
	private String companyId;
	private String locationName;
	private String vendorId;
	private String DCRefNum;
	@JsonProperty("ContactInfo")
	public Contact getContactInfo() {
		return contactInfo;
	}
	@JsonProperty("AddressInfo")
	public Address getAddressInfo() {
		return addressInfo;
	}
	@JsonProperty("CompanyName")
	public String getCompanyName() {
		return companyName;
	}
	@JsonProperty("Note")
	public String getNote() {
		return note;
	}
	@JsonProperty("CompanyID")
	public String getCompanyId() {
		return companyId;
	}
	@JsonProperty("LocationName")
	public String getLocationName() {
		return locationName;
	}
	@JsonProperty("VendorID")
	public String getVendorId() {
		return vendorId;
	}
	@JsonProperty("DCRefNum")
	public String getDCRefNum() {
		return DCRefNum;
	}
	@JsonProperty("Dock")
	public Dock getDock() {
		return dock;
	}

	private Dock dock;

	public Consignee() {
	}

	public Consignee(Builder build) {
		this.contactInfo = build.contactInfo;
		this.addressInfo = build.addressInfo;
		this.companyName = build.companyName;
		this.companyId = build.companyId;
		this.note = build.note;
		this.locationName = build.locationName;
		this.vendorId = build.vendorId;
		this.DCRefNum = build.DCRefNum;
		this.dock = build.dock;
	}

	public static class Builder {

		private Contact contactInfo;
		private Address addressInfo;
		private String companyName;
		private String note;
		private String companyId;
		private String locationName;
		private String vendorId;
		private String DCRefNum;
		private Dock dock;

		public Builder setContactInfo(Contact contactInfo) {
			this.contactInfo = contactInfo;
			return this;
		}

		public Builder setAddressInfo(Address addressInfo) {
			this.addressInfo = addressInfo;
			return this;
		}

		public Builder setCompanyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

		public Builder setNote(String note) {
			this.note = note;
			return this;
		}

		public Builder setCompanyId(String companyId) {
			this.companyId = companyId;
			return this;
		}

		public Builder setLocationName(String locationName) {
			this.locationName = locationName;
			return this;
		}

		public Builder setVendorId(String vendorId) {
			this.vendorId = vendorId;
			return this;
		}

		public Builder setDCRefNum(String dCRefNum) {
			DCRefNum = dCRefNum;
			return this;
		}

		public Builder setDock(Dock dock) {
			this.dock = dock;
			return this;
		}

		public Consignee build() {
			return new Consignee(this);
		}
	}

}
