package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ReferenceField implements Serializable{
	
	private static final long serialVersionUID = 1L;
private String Value;
private String Type;
private boolean PrintOnBOL;
private boolean PrintonShippingLabel;
public ReferenceField() {
	super();
}
@JsonProperty("Value")
public String getValue() {
	return Value;
}
public void setValue(String value) {
	Value = value;
}
@JsonProperty("Type")
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
@JsonProperty("PrintOnBOL")
public boolean isPrintOnBOL() {
	return PrintOnBOL;
}
public void setPrintOnBOL(boolean printOnBOL) {
	PrintOnBOL = printOnBOL;
}
@JsonProperty("PrintonShippinglabel")
public boolean isPrintonShippingLabel() {
	return PrintonShippingLabel;
}
public void setPrintonShippingLabel(boolean printonShippingLabel) {
	PrintonShippingLabel = printonShippingLabel;
}


}
