package com.banyan.FullLoadRequest.models.enums;

public enum UnitsofMeasure {
	English(0), Metric(1);

	private final int value;

	private UnitsofMeasure(int unitCode) {
		this.value = unitCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
