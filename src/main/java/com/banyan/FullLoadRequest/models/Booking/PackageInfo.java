package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PackageInfo implements Serializable{

	private static final long serialVersionUID = 1L;
private BigDecimal CODAmount;
private BigDecimal DeclaredLiability;
public PackageInfo(BigDecimal cODAmount, BigDecimal declaredLiability) {
	super();
	CODAmount = cODAmount;
	DeclaredLiability = declaredLiability;
}
@JsonProperty("CODAmount")
public BigDecimal getCODAmount() {
	return CODAmount;
}
public void setCODAmount(BigDecimal cODAmount) {
	CODAmount = cODAmount;
}@JsonProperty("DeclaredLiability")
public BigDecimal getDeclaredLiability() {
	return DeclaredLiability;
}
public void setDeclaredLiability(BigDecimal declaredLiability) {
	DeclaredLiability = declaredLiability;
}


}
