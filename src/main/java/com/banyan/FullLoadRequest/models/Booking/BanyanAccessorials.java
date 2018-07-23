package com.banyan.FullLoadRequest.models.Booking;

import org.springframework.stereotype.Component;

@Component
public class BanyanAccessorials {
	
	private ConsigneeAccessorials consignee;
	
	private LoadAccessorials load;
	
	private ShipperAccessorials ship;

	public ConsigneeAccessorials getConsignee() {
		return consignee;
	}

	public void setConsignee(ConsigneeAccessorials consignee) {
		this.consignee = consignee;
	}

	public LoadAccessorials getLoad() {
		return load;
	}

	public void setLoad(LoadAccessorials load) {
		this.load = load;
	}

	public ShipperAccessorials getShip() {
		return ship;
	}

	public void setShip(ShipperAccessorials ship) {
		this.ship = ship;
	}
}
