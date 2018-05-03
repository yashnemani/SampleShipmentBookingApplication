package com.banyan.FullLoadRequest.models.Pickup.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class XPO_Shipper {

	private String name;
	private String addressLine1;
	private String addressLine2;
	private String postOfficeBox;
	private String cityName;
	private String stateCd;
	private String countryCd;
	private String postalCd;
	private String usZip4;
	@Autowired
	private XPO_Phone xPO_Phone;

	public XPO_Shipper() {
	}

	public XPO_Shipper(Builder build) {

		this.name = build.name;
		this.addressLine1 = build.addressLine1;
		this.addressLine2 = build.addressLine2;
		this.postOfficeBox = build.postOfficeBox;
		this.cityName = build.cityName;
		this.stateCd = build.stateCd;
		this.countryCd = build.countryCd;
		this.postalCd = build.postalCd;
		this.usZip4 = build.usZip4;
		this.xPO_Phone = build.xPO_Phone;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("addressLine1")
	public String getAddressLine1() {
		return addressLine1;
	}

	@JsonProperty("addressLine2")
	public String getAddressLine2() {
		return addressLine2;
	}

	@JsonProperty("postOfficeBox")
	public String getPostOfficeBox() {
		return postOfficeBox;
	}

	@JsonProperty("cityName")
	public String getCityName() {
		return cityName;
	}

	@JsonProperty("stateCd")
	public String getStateCd() {
		return stateCd;
	}

	@JsonProperty("countryCd")
	public String getCountryCd() {
		return countryCd;
	}

	@JsonProperty("postalCd")
	public String getPostalCd() {
		return postalCd;
	}

	@JsonProperty("usZip4")
	public String getUsZip4() {
		return usZip4;
	}

	@JsonProperty("phone")
	public XPO_Phone getPhone() {
		return xPO_Phone;
	}

	public static class Builder {

		private String name;
		private String addressLine1;
		private String addressLine2;
		private String postOfficeBox;
		private String cityName;
		private String stateCd;
		private String countryCd;
		private String postalCd;
		private String usZip4;
		@Autowired
		private XPO_Phone xPO_Phone;

		public Builder setPhone(XPO_Phone xPO_Phone) {
			this.xPO_Phone = xPO_Phone;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
			return this;
		}

		public Builder setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
			return this;
		}

		public Builder setPostOfficeBox(String postOfficeBox) {
			this.postOfficeBox = postOfficeBox;
			return this;
		}

		public Builder setCityName(String cityName) {
			this.cityName = cityName;
			return this;
		}

		public Builder setStateCd(String stateCd) {
			this.stateCd = stateCd;
			return this;
		}

		public Builder setCountryCd(String countryCd) {
			this.countryCd = countryCd;
			return this;
		}

		public Builder setPostalCd(String postalCd) {
			this.postalCd = postalCd;
			return this;
		}

		public Builder setUsZip4(String usZip4) {
			this.usZip4 = usZip4;
			return this;
		}

		public XPO_Shipper build() {
			return new XPO_Shipper(this);
		}

	}
}
