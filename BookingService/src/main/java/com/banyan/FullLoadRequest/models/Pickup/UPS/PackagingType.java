package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class PackagingType {

	private String code;
	private String desc;

	public PackagingType() {

	}

	@JsonProperty("Code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("Description")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
