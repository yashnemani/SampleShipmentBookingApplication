package com.banyan.FullLoadRequest.models.Pickup.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Requestor {

	@Autowired
	private XPO_Contact xPO_Contact;
	private String roleCd;

	public Requestor() {

	}

	public Requestor(XPO_Contact xPO_Contact, String roleCd) {
		super();
		this.xPO_Contact = xPO_Contact;
		this.roleCd = roleCd;
	}

	@JsonProperty("contact")
	public XPO_Contact getContact() {
		return xPO_Contact;
	}

	public void setContact(XPO_Contact xPO_Contact) {
		this.xPO_Contact = xPO_Contact;
	}

	@JsonProperty("roleCd")
	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

}
