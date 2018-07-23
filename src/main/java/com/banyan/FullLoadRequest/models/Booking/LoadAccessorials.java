package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class LoadAccessorials implements Serializable{

	private static final long serialVersionUID = 1L;

	private boolean guaranteed;

	private boolean timeDefinite;

	private boolean expedited;

	private boolean holidayPickup;

	private boolean holidayDelivery;

	private boolean weightDetermination;

	private boolean blindShipment;

	private boolean blanketService;

	private boolean singleShipment;

	private boolean customsInBond;

	private boolean overDimension;

	private boolean stackable;

	private boolean turnkey;

	private boolean foodGradeProducts;

	private boolean TSA;

	private boolean bulkhead;

	private boolean signatureRequired;

	private boolean blanketServiceChilled;

	private boolean blanketServiceFrozen;

	private boolean saturdayDelivery;

	private boolean secondMan;

	private boolean returnReceipt;

	private boolean shipmentHold;

	private boolean proactiveResponse;

	private boolean shipperRelease;

	private boolean whiteGlove;

	private boolean restrictedDelivery;

	public LoadAccessorials() {
	}

	public LoadAccessorials(boolean guaranteed, boolean timeDefinite, boolean expedited, boolean holidayPickup,
			boolean holidayDelivery, boolean weightDetermination, boolean blindShipment, boolean blanketService,
			boolean singleShipment, boolean customsInBond, boolean overDimension, boolean stackable, boolean turnkey,
			boolean foodGradeProducts, boolean TSA, boolean bulkhead, boolean signatureRequired,
			boolean blanketServiceChilled, boolean blanketServiceFrozen, boolean saturdayDelivery, boolean secondMan,
			boolean returnReceipt, boolean shipmentHold, boolean proactiveResponse, boolean shipperRelease,
			boolean whiteGlove, boolean restrictedDelivery) {
		this.guaranteed = guaranteed;
		this.timeDefinite = timeDefinite;
		this.expedited = expedited;
		this.holidayPickup = holidayPickup;
		this.holidayDelivery = holidayDelivery;
		this.weightDetermination = weightDetermination;
		this.blindShipment = blindShipment;
		this.blanketService = blanketService;
		this.singleShipment = singleShipment;
		this.customsInBond = customsInBond;
		this.overDimension = overDimension;
		this.stackable = stackable;
		this.turnkey = turnkey;
		this.foodGradeProducts = foodGradeProducts;
		this.TSA = TSA;
		this.bulkhead = bulkhead;
		this.signatureRequired = signatureRequired;
		this.blanketServiceChilled = blanketServiceChilled;
		this.blanketServiceFrozen = blanketServiceFrozen;
		this.saturdayDelivery = saturdayDelivery;
		this.secondMan = secondMan;
		this.returnReceipt = returnReceipt;
		this.shipmentHold = shipmentHold;
		this.proactiveResponse = proactiveResponse;
		this.shipperRelease = shipperRelease;
		this.whiteGlove = whiteGlove;
		this.restrictedDelivery = restrictedDelivery;
	}

	@JsonProperty("Guaranteed")
	public boolean isGuaranteed() {
		return guaranteed;
	}


	public void setGuaranteed(boolean guaranteed) {
		this.guaranteed = guaranteed;
	}

	@JsonProperty("TimeDefinite")
	public boolean isTimeDefinite() {
		return timeDefinite;
	}


	public void setTimeDefinite(boolean timeDefinite) {
		this.timeDefinite = timeDefinite;
	}

	@JsonProperty("Expedited")
	public boolean isExpedited() {
		return expedited;
	}

	public void setExpedited(boolean expedited) {
		this.expedited = expedited;
	}

	@JsonProperty("HolidayPickup")
	public boolean isHolidayPickup() {
		return holidayPickup;
	}

	public void setHolidayPickup(boolean holidayPickup) {
		this.holidayPickup = holidayPickup;
	}

	@JsonProperty("HolidayDelivery")
	public boolean isHolidayDelivery() {
		return holidayDelivery;
	}

	public void setHolidayDelivery(boolean holidayDelivery) {
		this.holidayDelivery = holidayDelivery;
	}
	@JsonProperty("WeightDetermination")
	public boolean isWeightDetermination() {
		return weightDetermination;
	}

	public void setWeightDetermination(boolean weightDetermination) {
		this.weightDetermination = weightDetermination;
	}
	@JsonProperty("BlindShipment")
	public boolean isBlindShipment() {
		return blindShipment;
	}

	public void setBlindShipment(boolean blindShipment) {
		this.blindShipment = blindShipment;
	}

	@JsonProperty("BlanketService")
	public boolean isBlanketService() {
		return blanketService;
	}

	public void setBlanketService(boolean blanketService) {
		this.blanketService = blanketService;
	}

	@JsonProperty("SingleShipment")
	public boolean isSingleShipment() {
		return singleShipment;
	}

	public void setSingleShipment(boolean singleShipment) {
		this.singleShipment = singleShipment;
	}

	@JsonProperty("CustomsInBond")
	public boolean isCustomsInBond() {
		return customsInBond;
	}

	public void setCustomsInBond(boolean customsInBond) {
		this.customsInBond = customsInBond;
	}

	@JsonProperty("OverDimension")
	public boolean isOverDimension() {
		return overDimension;
	}

	public void setOverDimension(boolean overDimension) {
		this.overDimension = overDimension;
	}

	@JsonProperty("Stackable")
	public boolean isStackable() {
		return stackable;
	}

	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}

	@JsonProperty("Turnkey")
	public boolean isTurnkey() {
		return turnkey;
	}

	public void setTurnkey(boolean turnkey) {
		this.turnkey = turnkey;
	}

	@JsonProperty("FoodGradeProducts")
	public boolean isFoodGradeProducts() {
		return foodGradeProducts;
	}


	public void setFoodGradeProducts(boolean foodGradeProducts) {
		this.foodGradeProducts = foodGradeProducts;
	}

	@JsonProperty("TSA")
	public boolean isTSA() {
		return TSA;
	}

	public void setTSA(boolean TSA) {
		this.TSA = TSA;
	}
	@JsonProperty("Bulkhead")
	public boolean isBulkhead() {
		return bulkhead;
	}

	public void setBulkhead(boolean bulkhead) {
		this.bulkhead = bulkhead;
	}

	@JsonProperty("SignatureRequired")
	public boolean isSignatureRequired() {
		return signatureRequired;
	}


	public void setSignatureRequired(boolean signatureRequired) {
		this.signatureRequired = signatureRequired;
	}

	@JsonProperty("BlanketServiceChilled")
	public boolean isBlanketServiceChilled() {
		return blanketServiceChilled;
	}

	public void setBlanketServiceChilled(boolean blanketServiceChilled) {
		this.blanketServiceChilled = blanketServiceChilled;
	}
	@JsonProperty("BlanketServiceFrozen")
	public boolean isBlanketServiceFrozen() {
		return blanketServiceFrozen;
	}

	public void setBlanketServiceFrozen(boolean blanketServiceFrozen) {
		this.blanketServiceFrozen = blanketServiceFrozen;
	}

	@JsonProperty("SaturdayDelivery")
	public boolean isSaturdayDelivery() {
		return saturdayDelivery;
	}

	public void setSaturdayDelivery(boolean saturdayDelivery) {
		this.saturdayDelivery = saturdayDelivery;
	}

	@JsonProperty("SecondMan")
	public boolean isSecondMan() {
		return secondMan;
	}

	public void setSecondMan(boolean secondMan) {
		this.secondMan = secondMan;
	}

	@JsonProperty("ReturnReceipt")
	public boolean isReturnReceipt() {
		return returnReceipt;
	}

	public void setReturnReceipt(boolean returnReceipt) {
		this.returnReceipt = returnReceipt;
	}

	@JsonProperty("ShipmentHold")
	public boolean isShipmentHold() {
		return shipmentHold;
	}

	public void setShipmentHold(boolean shipmentHold) {
		this.shipmentHold = shipmentHold;
	}

	@JsonProperty("ProactiveResponse")
	public boolean isProactiveResponse() {
		return proactiveResponse;
	}

	public void setProactiveResponse(boolean proactiveResponse) {
		this.proactiveResponse = proactiveResponse;
	}

	@JsonProperty("ShipperRelease")
	public boolean isShipperRelease() {
		return shipperRelease;
	}


	public void setShipperRelease(boolean shipperRelease) {
		this.shipperRelease = shipperRelease;
	}

	@JsonProperty("WhiteGlove")
	public boolean isWhiteGlove() {
		return whiteGlove;
	}

	public void setWhiteGlove(boolean whiteGlove) {
		this.whiteGlove = whiteGlove;
	}

	@JsonProperty("RestrictedDelivery")
	public boolean isRestrictedDelivery() {
		return restrictedDelivery;
	}

	public void setRestrictedDelivery(boolean restrictedDelivery) {
		this.restrictedDelivery = restrictedDelivery;
	}
}
