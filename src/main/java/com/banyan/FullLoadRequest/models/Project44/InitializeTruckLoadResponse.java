package com.banyan.FullLoadRequest.models.Project44;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class InitializeTruckLoadResponse {

	@Autowired
	private InitializeTruckLoad shipment;
	private List<InfoMessage> infoMessages;

	public InitializeTruckLoadResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("shipment")
	public InitializeTruckLoad getShipment() {
		return shipment;
	}

	public void setShipment(InitializeTruckLoad shipment) {
		this.shipment = shipment;
	}

	@JsonProperty("infoMessages")
	public List<InfoMessage> getInfoMessages() {
		return infoMessages;
	}

	public void setInfoMessages(List<InfoMessage> infoMessages) {
		this.infoMessages = infoMessages;
	}
}
