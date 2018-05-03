package com.banyan.FullLoadRequest.models.enums;

public enum FreightClasses {
	Unspecified(0), c50(1), c55(2), c60(3), c65(4), c70(5),
	// 77.5
	c77(6), c85(7),
	// 92.5
	c92(8), c100(9), c110(10), c125(11), c150(12), c175(13), c200(14), c250(15), c300(16), c400(17), c500(18);

	private final int value;

	private FreightClasses(int freightCode) {
		this.value = freightCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
