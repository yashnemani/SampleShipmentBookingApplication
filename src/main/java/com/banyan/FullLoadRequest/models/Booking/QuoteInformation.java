package com.banyan.FullLoadRequest.models.Booking;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class QuoteInformation {
private Integer QuoteID;
private String SCAC;
private Integer TransitTime;
private BigDecimal TotalCharge;
private BigDecimal FreightCharge;
private BigDecimal FuelSurcharge;
private BigDecimal DiscountPercentage;
private BigDecimal DiscountAmount;
private BigDecimal AccessorialFees;
private BigDecimal Minimum;
private BigDecimal GrossCharge;
private BigDecimal OtherCharges;
private String Tariff;
private boolean Interline;
private Integer Miles;
private String QuoteNumber;
//Required??
private String AccountNumber;
private BigDecimal CarrierPrice;
private BigDecimal CustomerPrice;
private String Note;
private Integer CurrencyID;
private QuoteInformation() {}

private QuoteInformation(Builder build) {
	this.QuoteID = build.QuoteID;
	this.SCAC =build.SCAC;
	this.TransitTime = build.TransitTime;
	this.TotalCharge = build.TotalCharge;
	this.FreightCharge = build.FreightCharge;
	this.FuelSurcharge = build.FuelSurcharge;
	this.DiscountPercentage = build.DiscountPercentage;
	this.DiscountAmount = build.DiscountAmount;
	this.AccessorialFees = build.AccessorialFees;
	this.Minimum = build.Minimum;
	this.GrossCharge = build.GrossCharge;
	this.OtherCharges = build.OtherCharges;
	this.Tariff = build.Tariff;
	this.Interline = build.Interline;
	this.Miles = build.Miles;
	this.QuoteNumber = build.QuoteNumber;
	this.AccountNumber = build.AccountNumber;
	this.CarrierPrice = build.CarrierPrice;
	this.CustomerPrice = build.CustomerPrice;
	this.Note = build.Note;
	this.CurrencyID = build.CurrencyID;
}

public static class Builder{
	private Integer QuoteID;
	private String SCAC;
	private Integer TransitTime;
	private BigDecimal TotalCharge;
	private BigDecimal FreightCharge;
	private BigDecimal FuelSurcharge;
	private BigDecimal DiscountPercentage;
	private BigDecimal DiscountAmount;
	private BigDecimal AccessorialFees;
	private BigDecimal Minimum;
	private BigDecimal GrossCharge;
	private BigDecimal OtherCharges;
	private String Tariff;
	private boolean Interline;
	private Integer Miles;
	private String QuoteNumber;
	private String AccountNumber;
	private BigDecimal CarrierPrice;
	private BigDecimal CustomerPrice;
	private String Note;
	private Integer CurrencyID;
	public Builder setQuoteID(Integer QuoteID) {
		this.QuoteID = QuoteID;
		return this;
	}
	public Builder setSCAC(String SCAC) {
		this.SCAC = SCAC;
		return this;
	}
	public Builder setTransitTime(Integer TransitTime) {
		this.TransitTime = TransitTime;
		return this;
	}
	public Builder setTotalCharge(BigDecimal TotalCharge) {
		this.TotalCharge = TotalCharge;
		return this;
	}
	public Builder setFreightCharge(BigDecimal FreightCharge) {
		this.FreightCharge = FreightCharge;
		return this;
	}
	public Builder setFuelSurcharge(BigDecimal FuelSurcharge) {
		this.FuelSurcharge = FuelSurcharge;
		return this;
	}
	public Builder setDiscountPercentage(BigDecimal DiscountPercentage) {
		this.DiscountPercentage = DiscountPercentage;
		return this;
	}
	public Builder setDiscountAmount(BigDecimal DiscountAmount) {
		this.DiscountAmount = DiscountAmount;
		return this;
	}
	public Builder setAccessorialFees(BigDecimal AccessorialFees) {
		this.AccessorialFees = AccessorialFees;
		return this;
	}
	public Builder setMinimum(BigDecimal Minimum) {
		this.Minimum = Minimum;
		return this;
	}
	public Builder setGrossCharge(BigDecimal grossCharge) {
		GrossCharge = grossCharge;
		return this;
	}
	public Builder setOtherCharges(BigDecimal otherCharges) {
		OtherCharges = otherCharges;
		return this;
	}
	public Builder setTariff(String tariff) {
		Tariff = tariff;
		return this;
	}
	public Builder setInterline(boolean interline) {
		Interline = interline;
		return this;
	}
	public Builder setMiles(Integer miles) {
		Miles = miles;
		return this;
	}
	public Builder setQuoteNumber(String quoteNumber) {
		QuoteNumber = quoteNumber;
		return this;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public Builder setCarrierPrice(BigDecimal carrierPrice) {
		CarrierPrice = carrierPrice;
		return this;
	}
	public Builder setCustomerPrice(BigDecimal customerPrice) {
		CustomerPrice = customerPrice;
		return this;
	}
	public Builder setNote(String note) {
		Note = note;
		return this;
	}
	public Builder setCurrencyID(Integer currencyID) {
		CurrencyID = currencyID;
		return this;
	}
	public QuoteInformation build() {
		return new QuoteInformation(this);
	}
}
@JsonProperty("QuoteID")
public Integer getQuoteID() {
	return QuoteID;
}
@JsonProperty("SCAC")
public String getSCAC() {
	return SCAC;
}
@JsonProperty("TransitTime")
public Integer getTransitTime() {
	return TransitTime;
}
@JsonProperty("TotalCharge")
public BigDecimal getTotalCharge() {
	return TotalCharge;
}
@JsonProperty("FreightCharge")
public BigDecimal getFreightCharge() {
	return FreightCharge;
}
@JsonProperty("FuelSurcharge")
public BigDecimal getFuelSurcharge() {
	return FuelSurcharge;
}
@JsonProperty("DiscountPercentage")
public BigDecimal getDiscountPercentage() {
	return DiscountPercentage;
}
@JsonProperty("DiscountAmount")
public BigDecimal getDiscountAmount() {
	return DiscountAmount;
}
@JsonProperty("AccessorialFees")
public BigDecimal getAccessorialFees() {
	return AccessorialFees;
}
@JsonProperty("Minimum")
public BigDecimal getMinimum() {
	return Minimum;
}
@JsonProperty("GrossCharge")
public BigDecimal getGrossCharge() {
	return GrossCharge;
}
@JsonProperty("OtherCharges")
public BigDecimal getOtherCharges() {
	return OtherCharges;
}
@JsonProperty("Tariff")
public String getTariff() {
	return Tariff;
}
@JsonProperty("Interline")
public boolean isInterline() {
	return Interline;
}
@JsonProperty("Miles")
public Integer getMiles() {
	return Miles;
}
@JsonProperty("QuoteNumber")
public String getQuoteNumber() {
	return QuoteNumber;
}
@JsonProperty("AccountNumber")
public String getAccountNumber() {
	return AccountNumber;
}

@JsonProperty("CarrierPrice")
public BigDecimal getCarrierPrice() {
	return CarrierPrice;
}
@JsonProperty("CustomerPrice")
public BigDecimal getCustomerPrice() {
	return CustomerPrice;
}
@JsonProperty("Note")
public String getNote() {
	return Note;
}
@JsonProperty("CurrencyID")
public Integer getCurrencyID() {
	return CurrencyID;
}
}
