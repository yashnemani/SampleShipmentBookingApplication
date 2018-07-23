package com.banyan.FullLoadRequest.models.Pickup.XPO;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class XPO_Phone {

	private String phoneNum;
	private String ext;
	private Integer countryCode;

	@JsonProperty("extension")
	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@JsonProperty("countryCd")
	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public XPO_Phone() {
	}

	@JsonProperty("phoneNbr")
	public String getPhoneNum() {
		return phoneNum;
	}

}
