package com.banyan.FullLoadRequest.Entities;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "RATE_QUOTE_ADDRESS", schema = "TBB")
public class RateQtAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "FREIGHT_DIRECTION")
	private String freightDirection;

	@Column(name = "PREPAID")
	private String prepaid;

	@Column(name = "SHIPMENT_TYPE")
	private String shipmentType;

	@Column(name = "ORIGIN_NAME")
	private String originName;

	@Column(name = "ORIGIN_ADDRESS")
	private String originAddress;

	@Column(name = "ORIGIN_CITY")
	private String originCity;

	@Column(name = "ORIGIN_STATE_CODE")
	private String originStateCode;

	@Column(name = "ORIGIN_ZIP_CODE")
	private String originZipCode;

	@Column(name = "ORIGIN_COUNTRY_CODE")
	private String originCountryCode;

	@Column(name = "ORIGIN_OTHER_CODE")
	private String originOtherCode;

	@Column(name = "ORIGIN_COUNTY_CODE")
	private String originCountyCode;

	@Column(name = "ORIGIN_CONTACT_NAME")
	private String originContactName;

	@Column(name = "ORIGIN_CONTACT_PHONE")
	private String originContactPhone;

	@Column(name = "ORIGIN_CONTACT_EXT")
	private String originContactExt;

	@Column(name = "DEST_NAME")
	private String destName;

	@Column(name = "DEST_ADDRESS")
	private String destAddress;

	@Column(name = "DEST_CITY")
	private String destCity;

	@Column(name = "DEST_STATE_CODE")
	private String destStateCode;

	@Column(name = "DEST_ZIP_CODE")
	private String destZipCode;

	@Column(name = "DEST_COUNTRY_CODE")
	private String destCountryCode;

	@Column(name = "DEST_OTHER_CODE")
	private String destOtherCode;

	@Column(name = "DEST_COUNTY_CODE")
	private String destCountyCode;

	@Column(name = "DEST_CONTACT_NAME")
	private String destContactName;

	@Column(name = "DEST_CONTACT_PHONE")
	private String destContactPhone;

	@Column(name = "DEST_CONTACT_EXT")
	private String destContactExt;

	@Column(name = "CALL_CARRIER_FOR_PICKUP")
	private String callCarrierForPickup;

	@Column(name = "COD_AMOUNT")
	private Double codAmount;

	@Column(name = "COD_FEE")
	private Double codFee;

	@Column(name = "DT_PROJECTED_PICKUP")
	private Timestamp dtProjectedPickup;

	@Column(name = "DT_REQUESTED_DELIVERY")
	private Timestamp dtRequestedDelivery;

	@Column(name = "NO_DAYS")
	private Integer noDays;

	@Column(name = "PO_NUMBER")
	private String poNumber;

	@Column(name = "SPECIAL_REQUIREMENTS")
	private String specialRequirements;

	@Column(name = "SPOT_CONFIRMED_DT")
	private Timestamp spotConfirmedDt;

	@Column(name = "TIME_CARRIER_CALLED")
	private String timeCarrierCalled;

	@Column(name = "TIME_CONSIGNEE_RECEIVES")
	private String timeConsigneeReceives;

	@Column(name = "TIME_FREIGHT_READY")
	private String timeFreightReady;

	@Column(name = "TIME_SHIPPER_CLOSES")
	private String timeShipperCloses;

	@Column(name = "RT_QTE_ID")
	private Integer rtQteId;

	@Column(name = "RT_QT_WRK_ID")
	private Integer rtQtWrkId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "DT_UPDATED")
	private Timestamp dtUpdated;

	@Column(name = "DISTANCE")
	private Integer distance;

	@Column(name = "CARRIER_CONTACT_CALLED")
	private String carrierContactCalled;

	@Column(name = "TIME_CLIENT_CALLED")
	private String timeClientCalled;

	@Column(name = "CLIENT_CONTACT_CALLED")
	private String clientContactCalled;

	@Column(name = "PRO_NO")
	private String proNo;

	@Column(name = "DT_ACTUAL_DELIVERY")
	private Timestamp dtActualDelivery;

	@Column(name = "SHIPMENT_STATUS")
	private String shipmentStatus;

	@Column(name = "STATUS_DESCRIPTION")
	private String statusDescription;

	@Column(name = "CARRIER_PHONE")
	private String carrierPhone;

	@Column(name = "SHIPPED_DT")
	private Timestamp shippedDt;

	@Column(name = "PARTIAL_TRUCKLOAD")
	private String partialTruckload;

	@Column(name = "CARRIER_CONF_ATTN")
	private String carrierConfAttn;

	@Column(name = "CARRIER_CONF_FAX_NBR")
	private String carrierConfFaxNbr;

	@Column(name = "CARRIER_CONF_DT_SENT")
	private Timestamp carrierConfDtSent;

	@Column(name = "TL_TRACK_CLIENT_CONTACT")
	private String tlTrackClientContact;

	@Column(name = "TL_TRACK_CLIENT_EMAIL")
	private String tlTrackClientEmail;

	@Column(name = "TL_TRACK_CARRIER_CONTACT")
	private String tlTrackCarrierContact;

	@Column(name = "TL_TRACK_FAX_NBR")
	private String tlTrackFaxNbr;

	@Column(name = "DT_ESTIMATED_DELIVERY")
	private Timestamp dtEstimatedDelivery;

	@Column(name = "CONTAINER_NO")
	private String containerNo;

	@Column(name = "BILL_LADING_NO")
	private String billLadingNo;

	@Column(name = "QUOTE_TYPE")
	private String quoteType;

	@Column(name = "GUARANTEED_SERVICE")
	private String guaranteedService;

	@Column(name = "QUALITY_OF_SERVICE")
	private String qualityOfService;

	@Column(name = "CARRIER_CODE")
	private String carrierCode;

	@Column(name = "SITE_CLIENT_CODE")
	private Integer siteClientCode;

	@Column(name = "HAZMAT_PHONE_NBR")
	private String hazmatPhoneNbr;

	@Column(name = "HAZMAT_CONTRACT_NBR")
	private String hazmatContractNbr;

	@Column(name = "APPOINTMENT_DT")
	private Timestamp appointmentDt;

	@Column(name = "INSURANCE_CERTIFICATE")
	private String insuranceCertificate;

	@Column(name = "COD_PREPAID")
	private String codPrepaid;

	@Column(name = "ORIGIN_CONTACT_EMAIL")
	private String originContactEmail;

	@Column(name = "DEST_CONTACT_EMAIL")
	private String destContactEmail;

	@Column(name = "BILLTO_ACCOUNT_NBR")
	private String billtoAccountNbr;

	@Column(name = "ACCOUNTING_CODE")
	private String accountingCode;

	@Column(name = "TIME_CONSIGNEE_CLOSES")
	private String timeConsigneeCloses;

	@Column(name = "EQUIPMENT_TYPE")
	private String equipmentType;

	@Column(name = "EQUIPMENT_SIZE")
	private String equipmentSize;

	@Column(name = "TL_QUOTE_TYPE")
	private String tlQuoteType;

	@Column(name = "TIME_DEFINITE_CODE")
	private String timeDefiniteCode;

	@Column(name = "CRC")
	private Long crc;

	@Column(name = "REASON_TEXT")
	private String reasonText;

	@Column(name = "EARLIEST_PICKUP_DT")
	private Timestamp earliestPickupDt;

	@Column(name = "LATEST_PICKUP_DT")
	private Timestamp latestPickupDt;

	@Column(name = "EARLIEST_DELIVERY_DT")
	private Timestamp earliestDeliveryDt;

	@Column(name = "LATEST_DELIVERY_DT")
	private Timestamp latestDeliveryDt;

	@Column(name = "PICKUP_LEAD_TIME")
	private Integer pickupLeadTime;

	@Column(name = "EARLIEST_PICKUP_TOD")
	private String earliestPickupTod;

	@Column(name = "LATEST_PICKUP_TOD")
	private String latestPickupTod;

	@Column(name = "DELIVERY_LEAD_TIME")
	private Integer deliveryLeadTime;

	@Column(name = "EARLIEST_DELIVERY_TOD")
	private String earliestDeliveryTod;

	@Column(name = "LATEST_DELIVERY_TOD")
	private String latestDeliveryTod;

	@Column(name = "OPT_WRK_ID")
	private Integer optWrkId;

	@Column(name = "SP_ACCOUNT")
	private String spAccount;

	public RateQtAddress() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFreightDirection() {
		return this.freightDirection;
	}

	public void setFreightDirection(String freightDirection) {
		this.freightDirection = freightDirection;
	}

	public String getPrepaid() {
		return this.prepaid;
	}

	public void setPrepaid(String prepaid) {
		this.prepaid = prepaid;
	}

	public String getShipmentType() {
		return this.shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

	public String getOriginName() {
		return this.originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getOriginAddress() {
		return this.originAddress;
	}

	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	public String getOriginCity() {
		return this.originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginStateCode() {
		return this.originStateCode;
	}

	public void setOriginStateCode(String originStateCode) {
		this.originStateCode = originStateCode;
	}

	public String getOriginZipCode() {
		return this.originZipCode;
	}

	public void setOriginZipCode(String originZipCode) {
		this.originZipCode = originZipCode;
	}

	public String getOriginCountryCode() {
		return this.originCountryCode;
	}

	public void setOriginCountryCode(String originCountryCode) {
		this.originCountryCode = originCountryCode;
	}

	public String getOriginOtherCode() {
		return this.originOtherCode;
	}

	public void setOriginOtherCode(String originOtherCode) {
		this.originOtherCode = originOtherCode;
	}

	public String getOriginCountyCode() {
		return this.originCountyCode;
	}

	public void setOriginCountyCode(String originCountyCode) {
		this.originCountyCode = originCountyCode;
	}

	public String getOriginContactName() {
		return this.originContactName;
	}

	public void setOriginContactName(String originContactName) {
		this.originContactName = originContactName;
	}

	public String getOriginContactPhone() {
		return this.originContactPhone;
	}

	public void setOriginContactPhone(String originContactPhone) {
		this.originContactPhone = originContactPhone;
	}

	public String getOriginContactExt() {
		return this.originContactExt;
	}

	public void setOriginContactExt(String originContactExt) {
		this.originContactExt = originContactExt;
	}

	public String getDestName() {
		return this.destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getDestAddress() {
		return this.destAddress;
	}

	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	public String getDestCity() {
		return this.destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public String getDestStateCode() {
		return this.destStateCode;
	}

	public void setDestStateCode(String destStateCode) {
		this.destStateCode = destStateCode;
	}

	public String getDestZipCode() {
		return this.destZipCode;
	}

	public void setDestZipCode(String destZipCode) {
		this.destZipCode = destZipCode;
	}

	public String getDestCountryCode() {
		return this.destCountryCode;
	}

	public void setDestCountryCode(String destCountryCode) {
		this.destCountryCode = destCountryCode;
	}

	public String getDestOtherCode() {
		return this.destOtherCode;
	}

	public void setDestOtherCode(String destOtherCode) {
		this.destOtherCode = destOtherCode;
	}

	public String getDestCountyCode() {
		return this.destCountyCode;
	}

	public void setDestCountyCode(String destCountyCode) {
		this.destCountyCode = destCountyCode;
	}

	public String getDestContactName() {
		return this.destContactName;
	}

	public void setDestContactName(String destContactName) {
		this.destContactName = destContactName;
	}

	public String getDestContactPhone() {
		return this.destContactPhone;
	}

	public void setDestContactPhone(String destContactPhone) {
		this.destContactPhone = destContactPhone;
	}

	public String getDestContactExt() {
		return this.destContactExt;
	}

	public void setDestContactExt(String destContactExt) {
		this.destContactExt = destContactExt;
	}

	public String getCallCarrierForPickup() {
		return this.callCarrierForPickup;
	}

	public void setCallCarrierForPickup(String callCarrierForPickup) {
		this.callCarrierForPickup = callCarrierForPickup;
	}

	public Double getCodAmount() {
		return this.codAmount;
	}

	public void setCodAmount(Double codAmount) {
		this.codAmount = codAmount;
	}

	public Double getCodFee() {
		return this.codFee;
	}

	public void setCodFee(Double codFee) {
		this.codFee = codFee;
	}

	public Timestamp getDtProjectedPickup() {
		return this.dtProjectedPickup;
	}

	public void setDtProjectedPickup(Timestamp dtProjectedPickup) {
		this.dtProjectedPickup = dtProjectedPickup;
	}

	public Timestamp getDtRequestedDelivery() {
		return this.dtRequestedDelivery;
	}

	public void setDtRequestedDelivery(Timestamp dtRequestedDelivery) {
		this.dtRequestedDelivery = dtRequestedDelivery;
	}

	public Integer getNoDays() {
		return this.noDays;
	}

	public void setNoDays(Integer noDays) {
		this.noDays = noDays;
	}

	public String getPoNumber() {
		return this.poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getSpecialRequirements() {
		return this.specialRequirements;
	}

	public void setSpecialRequirements(String specialRequirements) {
		this.specialRequirements = specialRequirements;
	}

	public Timestamp getSpotConfirmedDt() {
		return this.spotConfirmedDt;
	}

	public void setSpotConfirmedDt(Timestamp spotConfirmedDt) {
		this.spotConfirmedDt = spotConfirmedDt;
	}

	public String getTimeCarrierCalled() {
		return this.timeCarrierCalled;
	}

	public void setTimeCarrierCalled(String timeCarrierCalled) {
		this.timeCarrierCalled = timeCarrierCalled;
	}

	public String getTimeConsigneeReceives() {
		return this.timeConsigneeReceives;
	}

	public void setTimeConsigneeReceives(String timeConsigneeReceives) {
		this.timeConsigneeReceives = timeConsigneeReceives;
	}

	public String getTimeFreightReady() {
		return this.timeFreightReady;
	}

	public void setTimeFreightReady(String timeFreightReady) {
		this.timeFreightReady = timeFreightReady;
	}

	public String getTimeShipperCloses() {
		return this.timeShipperCloses;
	}

	public void setTimeShipperCloses(String timeShipperCloses) {
		this.timeShipperCloses = timeShipperCloses;
	}

	public Integer getRtQteId() {
		return this.rtQteId;
	}

	public void setRtQteId(Integer rtQteId) {
		this.rtQteId = rtQteId;
	}

	public Integer getRtQtWrkId() {
		return this.rtQtWrkId;
	}

	public void setRtQtWrkId(Integer rtQtWrkId) {
		this.rtQtWrkId = rtQtWrkId;
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

	public Integer getDistance() {
		return this.distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getCarrierContactCalled() {
		return this.carrierContactCalled;
	}

	public void setCarrierContactCalled(String carrierContactCalled) {
		this.carrierContactCalled = carrierContactCalled;
	}

	public String getTimeClientCalled() {
		return this.timeClientCalled;
	}

	public void setTimeClientCalled(String timeClientCalled) {
		this.timeClientCalled = timeClientCalled;
	}

	public String getClientContactCalled() {
		return this.clientContactCalled;
	}

	public void setClientContactCalled(String clientContactCalled) {
		this.clientContactCalled = clientContactCalled;
	}

	public String getProNo() {
		return this.proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public Timestamp getDtActualDelivery() {
		return this.dtActualDelivery;
	}

	public void setDtActualDelivery(Timestamp dtActualDelivery) {
		this.dtActualDelivery = dtActualDelivery;
	}

	public String getShipmentStatus() {
		return this.shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getCarrierPhone() {
		return this.carrierPhone;
	}

	public void setCarrierPhone(String carrierPhone) {
		this.carrierPhone = carrierPhone;
	}

	public Timestamp getShippedDt() {
		return this.shippedDt;
	}

	public void setShippedDt(Timestamp shippedDt) {
		this.shippedDt = shippedDt;
	}

	public String getPartialTruckload() {
		return this.partialTruckload;
	}

	public void setPartialTruckload(String partialTruckload) {
		this.partialTruckload = partialTruckload;
	}

	public String getCarrierConfAttn() {
		return this.carrierConfAttn;
	}

	public void setCarrierConfAttn(String carrierConfAttn) {
		this.carrierConfAttn = carrierConfAttn;
	}

	public String getCarrierConfFaxNbr() {
		return this.carrierConfFaxNbr;
	}

	public void setCarrierConfFaxNbr(String carrierConfFaxNbr) {
		this.carrierConfFaxNbr = carrierConfFaxNbr;
	}

	public Timestamp getCarrierConfDtSent() {
		return this.carrierConfDtSent;
	}

	public void setCarrierConfDtSent(Timestamp carrierConfDtSent) {
		this.carrierConfDtSent = carrierConfDtSent;
	}

	public String getTlTrackClientContact() {
		return this.tlTrackClientContact;
	}

	public void setTlTrackClientContact(String tlTrackClientContact) {
		this.tlTrackClientContact = tlTrackClientContact;
	}

	public String getTlTrackClientEmail() {
		return this.tlTrackClientEmail;
	}

	public void setTlTrackClientEmail(String tlTrackClientEmail) {
		this.tlTrackClientEmail = tlTrackClientEmail;
	}

	public String getTlTrackCarrierContact() {
		return this.tlTrackCarrierContact;
	}

	public void setTlTrackCarrierContact(String tlTrackCarrierContact) {
		this.tlTrackCarrierContact = tlTrackCarrierContact;
	}

	public String getTlTrackFaxNbr() {
		return this.tlTrackFaxNbr;
	}

	public void setTlTrackFaxNbr(String tlTrackFaxNbr) {
		this.tlTrackFaxNbr = tlTrackFaxNbr;
	}

	public Timestamp getDtEstimatedDelivery() {
		return this.dtEstimatedDelivery;
	}

	public void setDtEstimatedDelivery(Timestamp dtEstimatedDelivery) {
		this.dtEstimatedDelivery = dtEstimatedDelivery;
	}

	public String getContainerNo() {
		return this.containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	public String getBillLadingNo() {
		return this.billLadingNo;
	}

	public void setBillLadingNo(String billLadingNo) {
		this.billLadingNo = billLadingNo;
	}

	public String getQuoteType() {
		return this.quoteType;
	}

	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}

	public String getGuaranteedService() {
		return this.guaranteedService;
	}

	public void setGuaranteedService(String guaranteedService) {
		this.guaranteedService = guaranteedService;
	}

	public String getQualityOfService() {
		return this.qualityOfService;
	}

	public void setQualityOfService(String qualityOfService) {
		this.qualityOfService = qualityOfService;
	}

	public String getCarrierCode() {
		return this.carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public Integer getSiteClientCode() {
		return this.siteClientCode;
	}

	public void setSiteClientCode(Integer siteClientCode) {
		this.siteClientCode = siteClientCode;
	}

	public String getHazmatPhoneNbr() {
		return this.hazmatPhoneNbr;
	}

	public void setHazmatPhoneNbr(String hazmatPhoneNbr) {
		this.hazmatPhoneNbr = hazmatPhoneNbr;
	}

	public String getHazmatContractNbr() {
		return this.hazmatContractNbr;
	}

	public void setHazmatContractNbr(String hazmatContractNbr) {
		this.hazmatContractNbr = hazmatContractNbr;
	}

	public Timestamp getAppointmentDt() {
		return this.appointmentDt;
	}

	public void setAppointmentDt(Timestamp appointmentDt) {
		this.appointmentDt = appointmentDt;
	}

	public String getInsuranceCertificate() {
		return this.insuranceCertificate;
	}

	public void setInsuranceCertificate(String insuranceCertificate) {
		this.insuranceCertificate = insuranceCertificate;
	}

	public String getCodPrepaid() {
		return this.codPrepaid;
	}

	public void setCodPrepaid(String codPrepaid) {
		this.codPrepaid = codPrepaid;
	}

	public String getOriginContactEmail() {
		return this.originContactEmail;
	}

	public void setOriginContactEmail(String originContactEmail) {
		this.originContactEmail = originContactEmail;
	}

	public String getDestContactEmail() {
		return this.destContactEmail;
	}

	public void setDestContactEmail(String destContactEmail) {
		this.destContactEmail = destContactEmail;
	}

	public String getBilltoAccountNbr() {
		return this.billtoAccountNbr;
	}

	public void setBilltoAccountNbr(String billtoAccountNbr) {
		this.billtoAccountNbr = billtoAccountNbr;
	}

	public String getAccountingCode() {
		return this.accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getTimeConsigneeCloses() {
		return this.timeConsigneeCloses;
	}

	public void setTimeConsigneeCloses(String timeConsigneeCloses) {
		this.timeConsigneeCloses = timeConsigneeCloses;
	}

	public String getEquipmentType() {
		return this.equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getEquipmentSize() {
		return this.equipmentSize;
	}

	public void setEquipmentSize(String equipmentSize) {
		this.equipmentSize = equipmentSize;
	}

	public String getTlQuoteType() {
		return this.tlQuoteType;
	}

	public void setTlQuoteType(String tlQuoteType) {
		this.tlQuoteType = tlQuoteType;
	}

	public String getTimeDefiniteCode() {
		return this.timeDefiniteCode;
	}

	public void setTimeDefiniteCode(String timeDefiniteCode) {
		this.timeDefiniteCode = timeDefiniteCode;
	}

	public Long getCrc() {
		return this.crc;
	}

	public void setCrc(Long crc) {
		this.crc = crc;
	}

	public String getReasonText() {
		return this.reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	public Timestamp getEarliestPickupDt() {
		return this.earliestPickupDt;
	}

	public void setEarliestPickupDt(Timestamp earliestPickupDt) {
		this.earliestPickupDt = earliestPickupDt;
	}

	public Timestamp getLatestPickupDt() {
		return this.latestPickupDt;
	}

	public void setLatestPickupDt(Timestamp latestPickupDt) {
		this.latestPickupDt = latestPickupDt;
	}

	public Timestamp getEarliestDeliveryDt() {
		return this.earliestDeliveryDt;
	}

	public void setEarliestDeliveryDt(Timestamp earliestDeliveryDt) {
		this.earliestDeliveryDt = earliestDeliveryDt;
	}

	public Timestamp getLatestDeliveryDt() {
		return this.latestDeliveryDt;
	}

	public void setLatestDeliveryDt(Timestamp latestDeliveryDt) {
		this.latestDeliveryDt = latestDeliveryDt;
	}

	public Integer getPickupLeadTime() {
		return this.pickupLeadTime;
	}

	public void setPickupLeadTime(Integer pickupLeadTime) {
		this.pickupLeadTime = pickupLeadTime;
	}

	public String getEarliestPickupTod() {
		return this.earliestPickupTod;
	}

	public void setEarliestPickupTod(String earliestPickupTod) {
		this.earliestPickupTod = earliestPickupTod;
	}

	public String getLatestPickupTod() {
		return this.latestPickupTod;
	}

	public void setLatestPickupTod(String latestPickupTod) {
		this.latestPickupTod = latestPickupTod;
	}

	public Integer getDeliveryLeadTime() {
		return this.deliveryLeadTime;
	}

	public void setDeliveryLeadTime(Integer deliveryLeadTime) {
		this.deliveryLeadTime = deliveryLeadTime;
	}

	public String getEarliestDeliveryTod() {
		return this.earliestDeliveryTod;
	}

	public void setEarliestDeliveryTod(String earliestDeliveryTod) {
		this.earliestDeliveryTod = earliestDeliveryTod;
	}

	public String getLatestDeliveryTod() {
		return this.latestDeliveryTod;
	}

	public void setLatestDeliveryTod(String latestDeliveryTod) {
		this.latestDeliveryTod = latestDeliveryTod;
	}

	public Integer getOptWrkId() {
		return this.optWrkId;
	}

	public void setOptWrkId(Integer optWrkId) {
		this.optWrkId = optWrkId;
	}

	public String getSpAccount() {
		return this.spAccount;
	}

	public void setSpAccount(String spAccount) {
		this.spAccount = spAccount;
	}
}
