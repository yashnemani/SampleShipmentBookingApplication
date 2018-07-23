package com.banyan.FullLoadRequest.models.enums;

public enum IncoTermTypes {

	FreeAlongsideShip(0),
	FreeOnboard(1), 
	CostandFreight(2),
	CostInsuranceandFreight(3), CarriagePaidTo(4),
	CarriageandInsurancePaid(5), DeliveredatTerminal(6),
	DeliveredatPlace(7), DeliveredDutyPaid(8),
	DeliveredatFrontier(9), DeliveredExShip(10),
	DeliveredExQuay(11), DeliveredDutyUnpaid(12),
	FreeCarrier(13), ExWorks(14);
	
	private int value;
	
	private IncoTermTypes(int incoterm) {
		this.value = incoterm;
	}
	public int getValue() {
		return this.value;
	}
}
