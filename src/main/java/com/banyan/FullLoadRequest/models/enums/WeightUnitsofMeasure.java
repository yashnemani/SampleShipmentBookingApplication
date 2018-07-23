package com.banyan.FullLoadRequest.models.enums;

public enum WeightUnitsofMeasure {
	LBS(0), KG(1);

	private final int value;

	private WeightUnitsofMeasure(int weightCode) {
		this.value = weightCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
