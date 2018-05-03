package com.banyan.FullLoadRequest.models.Pickup.XPO;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class TotWeight {

	private String weight;
	private String weightUom;

	public TotWeight() {
	}

	public TotWeight(String weight, String weightUom) {
		super();
		this.weight = weight;
		this.weightUom = weightUom;
	}

	@JsonProperty("weight")
	public String getWeight() {
		return weight;
	}

	@JsonProperty("weightUom")
	public String getWeightUom() {
		return weightUom;
	}

	public void setWeightUom(String weightUom) {
		this.weightUom = weightUom;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}
