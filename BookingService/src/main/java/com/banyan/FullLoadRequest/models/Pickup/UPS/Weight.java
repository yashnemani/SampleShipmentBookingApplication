package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Weight {

	@Autowired
private UnitOfMeasurement uom;
	private String Value;
	
	public Weight() {

	}

	@JsonProperty("UnitOfMeasurement")
	public UnitOfMeasurement getUom() {
		return uom;
	}

	public void setUom(UnitOfMeasurement uom) {
		this.uom = uom;
	}
	@JsonProperty("Value")
	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}
	
	
}
