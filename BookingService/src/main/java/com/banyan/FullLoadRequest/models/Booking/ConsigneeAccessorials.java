package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class ConsigneeAccessorials implements Serializable{

	private static final long serialVersionUID = 1L;

	private boolean appointmentRequired;

	private boolean insideDelivery;

	private boolean sortSegregate;

	private boolean palletJack;

	private boolean residentialDelivery;

	private boolean liftgateDelivery;

	private boolean markingTagging;

	private boolean tradeShowDelivery;

	private boolean NYCMetro;

	private boolean deliveryNotification;

	private boolean twoHourSpecialDelivery;

	private boolean nonBusinessHourDelivery;

	private java.lang.String limitedAccessType;

	public ConsigneeAccessorials() {
	}

	public ConsigneeAccessorials(boolean appointmentRequired, boolean insideDelivery, boolean sortSegregate,
			boolean palletJack, boolean residentialDelivery, boolean liftgateDelivery, boolean markingTagging,
			boolean tradeShowDelivery, boolean NYCMetro, boolean deliveryNotification, boolean twoHourSpecialDelivery,
			boolean nonBusinessHourDelivery, java.lang.String limitedAccessType) {
		this.appointmentRequired = appointmentRequired;
		this.insideDelivery = insideDelivery;
		this.sortSegregate = sortSegregate;
		this.palletJack = palletJack;
		this.residentialDelivery = residentialDelivery;
		this.liftgateDelivery = liftgateDelivery;
		this.markingTagging = markingTagging;
		this.tradeShowDelivery = tradeShowDelivery;
		this.NYCMetro = NYCMetro;
		this.deliveryNotification = deliveryNotification;
		this.twoHourSpecialDelivery = twoHourSpecialDelivery;
		this.nonBusinessHourDelivery = nonBusinessHourDelivery;
		this.limitedAccessType = limitedAccessType;
	}

	@JsonProperty("AppointmentRequired")
	public boolean isAppointmentRequired() {
		return appointmentRequired;
	}

	public void setAppointmentRequired(boolean appointmentRequired) {
		this.appointmentRequired = appointmentRequired;
	}
	@JsonProperty("InsideDelivery")
	public boolean isInsideDelivery() {
		return insideDelivery;
	}

	public void setInsideDelivery(boolean insideDelivery) {
		this.insideDelivery = insideDelivery;
	}

	@JsonProperty("SortSegregate")
	public boolean isSortSegregate() {
		return sortSegregate;
	}

	
	public void setSortSegregate(boolean sortSegregate) {
		this.sortSegregate = sortSegregate;
	}

	@JsonProperty("PalletJack")
	public boolean isPalletJack() {
		return palletJack;
	}


	public void setPalletJack(boolean palletJack) {
		this.palletJack = palletJack;
	}

	@JsonProperty("ResidentialDelivery")
	public boolean isResidentialDelivery() {
		return residentialDelivery;
	}


	public void setResidentialDelivery(boolean residentialDelivery) {
		this.residentialDelivery = residentialDelivery;
	}

	@JsonProperty("LiftgateDelivery")
	public boolean isLiftgateDelivery() {
		return liftgateDelivery;
	}


	public void setLiftgateDelivery(boolean liftgateDelivery) {
		this.liftgateDelivery = liftgateDelivery;
	}

	@JsonProperty("MarkingTagging")
	public boolean isMarkingTagging() {
		return markingTagging;
	}


	public void setMarkingTagging(boolean markingTagging) {
		this.markingTagging = markingTagging;
	}

	@JsonProperty("TradeShowDelivery")
	public boolean isTradeShowDelivery() {
		return tradeShowDelivery;
	}

	
	public void setTradeShowDelivery(boolean tradeShowDelivery) {
		this.tradeShowDelivery = tradeShowDelivery;
	}

	@JsonProperty("NYCMetro")
	public boolean isNYCMetro() {
		return NYCMetro;
	}


	public void setNYCMetro(boolean NYCMetro) {
		this.NYCMetro = NYCMetro;
	}

	@JsonProperty("DeliveryNotification")
	public boolean isDeliveryNotification() {
		return deliveryNotification;
	}

	public void setDeliveryNotification(boolean deliveryNotification) {
		this.deliveryNotification = deliveryNotification;
	}

	@JsonProperty("TwoHourSpecialDelivery")
	public boolean isTwoHourSpecialDelivery() {
		return twoHourSpecialDelivery;
	}

	
	public void setTwoHourSpecialDelivery(boolean twoHourSpecialDelivery) {
		this.twoHourSpecialDelivery = twoHourSpecialDelivery;
	}

	@JsonProperty("NonBusinessHoursDelivery")
	public boolean isNonBusinessHourDelivery() {
		return nonBusinessHourDelivery;
	}


	public void setNonBusinessHourDelivery(boolean nonBusinessHourDelivery) {
		this.nonBusinessHourDelivery = nonBusinessHourDelivery;
	}

	@JsonProperty("LimitedAccessType")
	public java.lang.String getLimitedAccessType() {
		return limitedAccessType;
	}

	public void setLimitedAccessType(java.lang.String limitedAccessType) {
		this.limitedAccessType = limitedAccessType;
	}
}
