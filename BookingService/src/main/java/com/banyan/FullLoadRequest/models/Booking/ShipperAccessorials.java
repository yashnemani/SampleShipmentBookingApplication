package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class ShipperAccessorials implements Serializable{

	private static final long serialVersionUID = 1L;

	private boolean appointmentRequired;

	private boolean insidePickup;

	private boolean sortSegregate;

	private boolean palletJack;

	private boolean residentialPickup;

	private boolean liftgatePickup;

	private boolean markingTagging;

	private boolean tradeShowPickup;

	private boolean NYCMetro;

	private boolean nonBusinessHourPickup;

	private String limitedAccessType;

	public ShipperAccessorials() {
	}

	public ShipperAccessorials(boolean appointmentRequired, boolean insidePickup, boolean sortSegregate,
			boolean palletJack, boolean residentialPickup, boolean liftgatePickup, boolean markingTagging,
			boolean tradeShowPickup, boolean NYCMetro, boolean nonBusinessHourPickup, String limitedAccessType) {
		this.appointmentRequired = appointmentRequired;
		this.insidePickup = insidePickup;
		this.sortSegregate = sortSegregate;
		this.palletJack = palletJack;
		this.residentialPickup = residentialPickup;
		this.liftgatePickup = liftgatePickup;
		this.markingTagging = markingTagging;
		this.tradeShowPickup = tradeShowPickup;
		this.NYCMetro = NYCMetro;
		this.nonBusinessHourPickup = nonBusinessHourPickup;
		this.limitedAccessType = limitedAccessType;
	}

	@JsonProperty("AppointmentRequired")
	public boolean isAppointmentRequired() {
		return appointmentRequired;
	}

	
	public void setAppointmentRequired(boolean appointmentRequired) {
		this.appointmentRequired = appointmentRequired;
	}

	@JsonProperty("InsidePickup")
	public boolean isInsidePickup() {
		return insidePickup;
	}

	public void setInsidePickup(boolean insidePickup) {
		this.insidePickup = insidePickup;
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

	@JsonProperty("ResidentialPickup")
	public boolean isResidentialPickup() {
		return residentialPickup;
	}

	public void setResidentialPickup(boolean residentialPickup) {
		this.residentialPickup = residentialPickup;
	}

	@JsonProperty("LiftgatePickup")
	public boolean isLiftgatePickup() {
		return liftgatePickup;
	}

	public void setLiftgatePickup(boolean liftgatePickup) {
		this.liftgatePickup = liftgatePickup;
	}

	@JsonProperty("MarkingTagging")
	public boolean isMarkingTagging() {
		return markingTagging;
	}


	public void setMarkingTagging(boolean markingTagging) {
		this.markingTagging = markingTagging;
	}

	@JsonProperty("TradeShowPickup")
	public boolean isTradeShowPickup() {
		return tradeShowPickup;
	}

	public void setTradeShowPickup(boolean tradeShowPickup) {
		this.tradeShowPickup = tradeShowPickup;
	}

	@JsonProperty("NYCMetro")
	public boolean isNYCMetro() {
		return NYCMetro;
	}

	public void setNYCMetro(boolean NYCMetro) {
		this.NYCMetro = NYCMetro;
	}

	@JsonProperty("NonBusinessHoursPickup")
	public boolean isNonBusinessHourPickup() {
		return nonBusinessHourPickup;
	}

	public void setNonBusinessHourPickup(boolean nonBusinessHourPickup) {
		this.nonBusinessHourPickup = nonBusinessHourPickup;
	}

	@JsonProperty("LimitedAccessType")
	public String getLimitedAccessType() {
		return limitedAccessType;
	}

	public void setLimitedAccessType(String limitedAccessType) {
		this.limitedAccessType = limitedAccessType;
	}

}
