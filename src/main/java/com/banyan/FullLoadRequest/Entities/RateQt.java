package com.banyan.FullLoadRequest.Entities;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;

/**
 * The persistent class for the RATE_QUOTE database table.
 * 
 */
@Entity
@Table(name = "RATE_QUOTE", schema = "TBB")
public class RateQt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CUST_SERV_ID")
	private Integer custServId;

	@Column(name = "INTERNATIONAL")
	private String international;

	@Column(name = "TIME_CUSTOMER_CALLED")
	private String timeCustomerCalled;

	@Column(name = "CLOSING_TIME")
	private String closingTime;

	@Column(name = "WT_UNIT")
	private String wtUnit;

	@Column(name = "SITE_LOC_NUMBER")
	private Integer siteLocNumber;

	@Column(name = "SITE_CLIENT_CODE")
	private Integer siteClientCode;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "DT_UPDATED")
	private Timestamp dtUpdated;

	@Column(name = "DT_ENTERED")
	private Timestamp dtEntered;

	@Column(name = "FUE")
	private String fue;

	@Column(name = "SSF")
	private String ssf;

	@Column(name = "RES")
	private String res;

	@Column(name = "HAZ")
	private String haz;

	@Column(name = "NFY")
	private String nfy;

	@Column(name = "IDL")
	private String idl;

	@Column(name = "ORIGIN_LFT")
	private String originLft;

	@Column(name = "DEST_LFT")
	private String destLft;

	@Column(name = "COD")
	private String cod;

	@Column(name = "LAD")
	private String lad;

	@Column(name = "LAP")
	private String lap;

	@Column(name = "PSH")
	private String psh;

	@Column(name = "INR")
	private String inr;

	@Column(name = "SHIPMENT_VALUE")
	private Double shipmentValue;

	@Column(name = "PICKUP_TYPE")
	private String pickupType;

	@Column(name = "FOB")
	private String fob;

	@Column(name = "CLIENT_REFERENCE")
	private String clientReference;

	@Column(name = "SATURDAY_DELIVERY")
	private String saturdayDelivery;

	@Column(name = "SIGNATURE_REQUIRED")
	private String signatureRequired;

	@Column(name = "MARKUP")
	private String markup;

	@Column(name = "RETURN_SHIPMENT")
	private String returnShipment;

	@Column(name = "CONFIRMATION_EMAIL")
	private String confirmationEmail;

	@Column(name = "ODA")
	private String oda;

	@Column(name = "DDA")
	private String dda;

	@Column(name = "DLO")
	private String dlo;

	@Column(name = "DUD")
	private String dud;

	@Column(name = "SOC")
	private String soc;

	@Column(name = "TAR")
	private String tar;

	@Column(name = "TARP_SIZE")
	private String tarpSize;

	@Column(name = "RURAL_PICKUP")
	private String ruralPickup;

	@Column(name = "RURAL_DELIVERY")
	private String ruralDelivery;

	@Column(name = "OBC_CARRIER_CODE")
	private String obcCarrierCode;

	@Column(name = "RATING_MESSAGE_CODE")
	private Integer ratingMessageCode;

	@Column(name = "OPTIMIZE")
	private String optimize;

	@Column(name = "MASTER_QTE_ID")
	private Integer masterQteId;

	@Column(name = "LOAD_ORDER")
	private Integer loadOrder;

	@Column(name = "APT")
	private String apt;

	public RateQt() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustServId() {
		return this.custServId;
	}

	public void setCustServId(Integer custServId) {
		this.custServId = custServId;
	}

	public String getInternational() {
		return this.international;
	}

	public void setInternational(String international) {
		this.international = international;
	}

	public String getTimeCustomerCalled() {
		return this.timeCustomerCalled;
	}

	public void setTimeCustomerCalled(String timeCustomerCalled) {
		this.timeCustomerCalled = timeCustomerCalled;
	}

	public String getClosingTime() {
		return this.closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public String getWtUnit() {
		return this.wtUnit;
	}

	public void setWtUnit(String wtUnit) {
		this.wtUnit = wtUnit;
	}

	public Integer getSiteLocNumber() {
		return this.siteLocNumber;
	}

	public void setSiteLocNumber(Integer siteLocNumber) {
		this.siteLocNumber = siteLocNumber;
	}

	public Integer getSiteClientCode() {
		return this.siteClientCode;
	}

	public void setSiteClientCode(Integer siteClientCode) {
		this.siteClientCode = siteClientCode;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getDtUpdated() {
		return this.dtUpdated;
	}

	public void setDtUpdated(Timestamp dtUpdated) {
		this.dtUpdated = dtUpdated;
	}

	public Timestamp getDtEntered() {
		return this.dtEntered;
	}

	public void setDtEntered(Timestamp dtEntered) {
		this.dtEntered = dtEntered;
	}

	public String getFue() {
		return this.fue;
	}

	public void setFue(String fue) {
		this.fue = fue;
	}

	public String getSsf() {
		return this.ssf;
	}

	public void setSsf(String ssf) {
		this.ssf = ssf;
	}

	public String getRes() {
		return this.res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getHaz() {
		return this.haz;
	}

	public void setHaz(String haz) {
		this.haz = haz;
	}

	public String getNfy() {
		return this.nfy;
	}

	public void setNfy(String nfy) {
		this.nfy = nfy;
	}

	public String getIdl() {
		return this.idl;
	}

	public void setIdl(String idl) {
		this.idl = idl;
	}

	public String getOriginLft() {
		return this.originLft;
	}

	public void setOriginLft(String originLft) {
		this.originLft = originLft;
	}

	public String getDestLft() {
		return this.destLft;
	}

	public void setDestLft(String destLft) {
		this.destLft = destLft;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getLad() {
		return this.lad;
	}

	public void setLad(String lad) {
		this.lad = lad;
	}

	public String getLap() {
		return this.lap;
	}

	public void setLap(String lap) {
		this.lap = lap;
	}

	public String getPsh() {
		return this.psh;
	}

	public void setPsh(String psh) {
		this.psh = psh;
	}

	public String getInr() {
		return this.inr;
	}

	public void setInr(String inr) {
		this.inr = inr;
	}

	public Double getShipmentValue() {
		return this.shipmentValue;
	}

	public void setShipmentValue(Double shipmentValue) {
		this.shipmentValue = shipmentValue;
	}

	public String getPickupType() {
		return this.pickupType;
	}

	public void setPickupType(String pickupType) {
		this.pickupType = pickupType;
	}

	public String getFob() {
		return this.fob;
	}

	public void setFob(String fob) {
		this.fob = fob;
	}

	public String getClientReference() {
		return this.clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	public String getSaturdayDelivery() {
		return this.saturdayDelivery;
	}

	public void setSaturdayDelivery(String saturdayDelivery) {
		this.saturdayDelivery = saturdayDelivery;
	}

	public String getSignatureRequired() {
		return this.signatureRequired;
	}

	public void setSignatureRequired(String signatureRequired) {
		this.signatureRequired = signatureRequired;
	}

	public String getMarkup() {
		return this.markup;
	}

	public void setMarkup(String markup) {
		this.markup = markup;
	}

	public String getReturnShipment() {
		return this.returnShipment;
	}

	public void setReturnShipment(String returnShipment) {
		this.returnShipment = returnShipment;
	}

	public String getConfirmationEmail() {
		return this.confirmationEmail;
	}

	public void setConfirmationEmail(String confirmationEmail) {
		this.confirmationEmail = confirmationEmail;
	}

	public String getOda() {
		return this.oda;
	}

	public void setOda(String oda) {
		this.oda = oda;
	}

	public String getDda() {
		return this.dda;
	}

	public void setDda(String dda) {
		this.dda = dda;
	}

	public String getDlo() {
		return this.dlo;
	}

	public void setDlo(String dlo) {
		this.dlo = dlo;
	}

	public String getDud() {
		return this.dud;
	}

	public void setDud(String dud) {
		this.dud = dud;
	}

	public String getSoc() {
		return this.soc;
	}

	public void setSoc(String soc) {
		this.soc = soc;
	}

	public String getTar() {
		return this.tar;
	}

	public void setTar(String tar) {
		this.tar = tar;
	}

	public String getTarpSize() {
		return this.tarpSize;
	}

	public void setTarpSize(String tarpSize) {
		this.tarpSize = tarpSize;
	}

	public String getRuralPickup() {
		return this.ruralPickup;
	}

	public void setRuralPickup(String ruralPickup) {
		this.ruralPickup = ruralPickup;
	}

	public String getRuralDelivery() {
		return this.ruralDelivery;
	}

	public void setRuralDelivery(String ruralDelivery) {
		this.ruralDelivery = ruralDelivery;
	}

	public String getObcCarrierCode() {
		return this.obcCarrierCode;
	}

	public void setObcCarrierCode(String obcCarrierCode) {
		this.obcCarrierCode = obcCarrierCode;
	}

	public Integer getRatingMessageCode() {
		return this.ratingMessageCode;
	}

	public void setRatingMessageCode(Integer ratingMessageCode) {
		this.ratingMessageCode = ratingMessageCode;
	}

	public String getOptimize() {
		return this.optimize;
	}

	public void setOptimize(String optimize) {
		this.optimize = optimize;
	}

	public Integer getMasterQteId() {
		return this.masterQteId;
	}

	public void setMasterQteId(Integer masterQteId) {
		this.masterQteId = masterQteId;
	}

	public Integer getLoadOrder() {
		return this.loadOrder;
	}

	public void setLoadOrder(Integer loadOrder) {
		this.loadOrder = loadOrder;
	}

	public String getApt() {
		return this.apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}
}
