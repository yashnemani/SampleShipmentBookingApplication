package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDefined implements Serializable{

	private static final long serialVersionUID = 1L;
private String Name;
private String Value;

public UserDefined(String name, String value) {
	super();
	Name = name;
	Value = value;
}
public UserDefined() {
}
@JsonProperty("Name")
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
@JsonProperty("Value")
public String getValue() {
	return Value;
}
public void setValue(String value) {
	Value = value;
}

}
