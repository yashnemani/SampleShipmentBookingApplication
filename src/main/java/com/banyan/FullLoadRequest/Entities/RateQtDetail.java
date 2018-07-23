package com.banyan.FullLoadRequest.Entities;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "RATE_QUOTE_DETAIL", schema = "TBB")
public class RateQtDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "DOUBLE_SKID")
	private String doubleSkid;

	@Column(name = "NO_SKIDS")
	private Integer noSkids;

	@Column(name = "NO_PIECES")
	private Integer noPieces;

	@Column(name = "WEIGHT")
	private Integer weight;

	@Column(name = "HEIGHT")
	private Double height;

	@Column(name = "WIDTH")
	private Double width;

	@Column(name = "LENGTH")
	private Double length;

	@Column(name = "VALUE")
	private Double value;

	@Column(name = "INSURANCE")
	private String insurance;

	@Column(name = "COMMODITY")
	private String commodity;

	@Column(name = "NMFC_NUMBER")
	private String nmfcNumber;

	@Column(name = "CLASS")
	private Double classNo;

	@Column(name = "CUBIC_CAPACITY")
	private Double cubicCapacity;

	@Column(name = "RATE")
	private Double rate;

	@Column(name = "CHARGE")
	private Double charge;

	@Column(name = "TARIFF")
	private String tariff;

	@Column(name = "RT_QTE_ADD_ID")
	private Integer rtQteAddId;

	@Override
	public String toString() {
		return "RateQtDetail [id=" + id + ", doubleSkid=" + doubleSkid + ", noSkids=" + noSkids + ", noPieces="
				+ noPieces + ", weight=" + weight + ", height=" + height + ", width=" + width + ", length=" + length
				+ ", value=" + value + ", insurance=" + insurance + ", commodity=" + commodity + ", nmfcNumber="
				+ nmfcNumber + ", classNo=" + classNo + ", cubicCapacity=" + cubicCapacity + ", rate=" + rate
				+ ", charge=" + charge + ", tariff=" + tariff + ", rtQteAddId=" + rtQteAddId + ", userId=" + userId
				+ ", dtUpdated=" + dtUpdated + ", hazmat=" + hazmat + ", unnaNumber=" + unnaNumber + ", unitOfMeasure="
				+ unitOfMeasure + ", unitOfWeight=" + unitOfWeight + ", packageType=" + packageType + ", pieceType="
				+ pieceType + ", clientProductCode=" + clientProductCode + ", tbbRate=" + tbbRate + ", tbbCharge="
				+ tbbCharge + ", packageNumber=" + packageNumber + ", hazmatClass=" + hazmatClass
				+ ", hazmatPackageGroup=" + hazmatPackageGroup + ", fedexMarkupAmt=" + fedexMarkupAmt + ", volume="
				+ volume + ", unitOfVolume=" + unitOfVolume + ", hazmatSubclass=" + hazmatSubclass + ", hazmatAccess="
				+ hazmatAccess + ", sqYards=" + sqYards + ", cntryOfManuf=" + cntryOfManuf + ", packageContentType="
				+ packageContentType + ", htsCode=" + htsCode + ", hazmatNumContainers=" + hazmatNumContainers
				+ ", hazmatContainerType=" + hazmatContainerType + ", hazmatQty=" + hazmatQty + ", hazmatQtyUnits="
				+ hazmatQtyUnits + ", hazmatQtyType=" + hazmatQtyType + ", hazmatAircraft=" + hazmatAircraft
				+ ", linearFeet=" + linearFeet + ", fedexHazmatId=" + fedexHazmatId + ", pickupStopId=" + pickupStopId
				+ ", dropoffStopId=" + dropoffStopId + ", currencyCode=" + currencyCode + ", forRate=" + forRate
				+ ", forCharge=" + forCharge + ", forTbbRate=" + forTbbRate + ", forTbbCharge=" + forTbbCharge + "]";
	}

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "DT_UPDATED")
	private Timestamp dtUpdated;

	@Column(name = "HAZMAT")
	private String hazmat;

	@Column(name = "UNNA_NUMBER")
	private String unnaNumber;

	@Column(name = "UNIT_OF_MEASURE")
	private String unitOfMeasure;

	@Column(name = "UNIT_OF_WEIGHT")
	private String unitOfWeight;

	@Column(name = "PACKAGE_TYPE")
	private String packageType;

	@Column(name = "PIECE_TYPE")
	private String pieceType;

	@Column(name = "CLIENT_PRODUCT_CODE")
	private String clientProductCode;

	@Column(name = "TBB_RATE")
	private Double tbbRate;

	@Column(name = "TBB_CHARGE")
	private Double tbbCharge;

	@Column(name = "PACKAGE_NUMBER")
	private String packageNumber;

	@Column(name = "HAZMAT_CLASS")
	private String hazmatClass;

	@Column(name = "HAZMAT_PACKAGE_GROUP")
	private String hazmatPackageGroup;

	@Column(name = "FEDEX_MARKUP_AMT")
	private Double fedexMarkupAmt;

	@Column(name = "VOLUME")
	private Double volume;

	@Column(name = "UNIT_OF_VOLUME")
	private String unitOfVolume;

	@Column(name = "HAZMAT_SUBCLASS")
	private String hazmatSubclass;

	@Column(name = "HAZMAT_ACCESS")
	private String hazmatAccess;

	@Column(name = "SQ_YARDS")
	private Integer sqYards;

	@Column(name = "CNTRY_OF_MANUF")
	private String cntryOfManuf;

	@Column(name = "PACKAGE_CONTENT_TYPE")
	private String packageContentType;

	@Column(name = "HTS_CODE")
	private String htsCode;

	@Column(name = "HAZMAT_NUM_CONTAINERS")
	private Integer hazmatNumContainers;

	@Column(name = "HAZMAT_CONTAINER_TYPE")
	private String hazmatContainerType;

	@Column(name = "HAZMAT_QTY")
	private Integer hazmatQty;

	@Column(name = "HAZMAT_QTY_UNITS")
	private String hazmatQtyUnits;

	@Column(name = "HAZMAT_QTY_TYPE")
	private String hazmatQtyType;

	@Column(name = "HAZMAT_AIRCRAFT")
	private String hazmatAircraft;

	@Column(name = "LINEAR_FEET")
	private Double linearFeet;

	@Column(name = "FEDEX_HAZMAT_ID")
	private Integer fedexHazmatId;

	@Column(name = "PICKUP_STOP_ID")
	private Integer pickupStopId;

	@Column(name = "DROPOFF_STOP_ID")
	private Integer dropoffStopId;

	@Column(name = "CURRENCY_CODE")
	private String currencyCode;

	@Column(name = "FOR_RATE")
	private Double forRate;

	@Column(name = "FOR_CHARGE")
	private Double forCharge;

	@Column(name = "FOR_TBB_RATE")
	private Double forTbbRate;

	@Column(name = "FOR_TBB_CHARGE")
	private Double forTbbCharge;

	public RateQtDetail() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDoubleSkid() {
		return this.doubleSkid;
	}

	public void setDoubleSkid(String doubleSkid) {
		this.doubleSkid = doubleSkid;
	}

	public Integer getNoSkids() {
		return this.noSkids;
	}

	public void setNoSkids(Integer noSkids) {
		this.noSkids = noSkids;
	}

	public Integer getNoPieces() {
		return this.noPieces;
	}

	public void setNoPieces(Integer noPieces) {
		this.noPieces = noPieces;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return this.height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return this.width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getLength() {
		return this.length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getInsurance() {
		return this.insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getCommodity() {
		return this.commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getNmfcNumber() {
		return this.nmfcNumber;
	}

	public void setNmfcNumber(String nmfcNumber) {
		this.nmfcNumber = nmfcNumber;
	}

	public Double getClassNo() {
		return this.classNo;
	}

	public void setClassNo(Double classNo) {
		this.classNo = classNo;
	}

	public Double getCubicCapacity() {
		return this.cubicCapacity;
	}

	public void setCubicCapacity(Double cubicCapacity) {
		this.cubicCapacity = cubicCapacity;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public String getTariff() {
		return this.tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public Integer getRtQteAddId() {
		return this.rtQteAddId;
	}

	public void setRtQteAddId(Integer rtQteAddId) {
		this.rtQteAddId = rtQteAddId;
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

	public String getHazmat() {
		return this.hazmat;
	}

	public void setHazmat(String hazmat) {
		this.hazmat = hazmat;
	}

	public String getUnnaNumber() {
		return this.unnaNumber;
	}

	public void setUnnaNumber(String unnaNumber) {
		this.unnaNumber = unnaNumber;
	}

	public String getUnitOfMeasure() {
		return this.unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getUnitOfWeight() {
		return this.unitOfWeight;
	}

	public void setUnitOfWeight(String unitOfWeight) {
		this.unitOfWeight = unitOfWeight;
	}

	public String getPackageType() {
		return this.packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPieceType() {
		return this.pieceType;
	}

	public void setPieceType(String pieceType) {
		this.pieceType = pieceType;
	}

	public String getClientProductCode() {
		return this.clientProductCode;
	}

	public void setClientProductCode(String clientProductCode) {
		this.clientProductCode = clientProductCode;
	}

	public Double getTbbRate() {
		return this.tbbRate;
	}

	public void setTbbRate(Double tbbRate) {
		this.tbbRate = tbbRate;
	}

	public Double getTbbCharge() {
		return this.tbbCharge;
	}

	public void setTbbCharge(Double tbbCharge) {
		this.tbbCharge = tbbCharge;
	}

	public String getPackageNumber() {
		return this.packageNumber;
	}

	public void setPackageNumber(String packageNumber) {
		this.packageNumber = packageNumber;
	}

	public String getHazmatClass() {
		return this.hazmatClass;
	}

	public void setHazmatClass(String hazmatClass) {
		this.hazmatClass = hazmatClass;
	}

	public String getHazmatPackageGroup() {
		return this.hazmatPackageGroup;
	}

	public void setHazmatPackageGroup(String hazmatPackageGroup) {
		this.hazmatPackageGroup = hazmatPackageGroup;
	}

	public Double getFedexMarkupAmt() {
		return this.fedexMarkupAmt;
	}

	public void setFedexMarkupAmt(Double fedexMarkupAmt) {
		this.fedexMarkupAmt = fedexMarkupAmt;
	}

	public Double getVolume() {
		return this.volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getUnitOfVolume() {
		return this.unitOfVolume;
	}

	public void setUnitOfVolume(String unitOfVolume) {
		this.unitOfVolume = unitOfVolume;
	}

	public String getHazmatSubclass() {
		return this.hazmatSubclass;
	}

	public void setHazmatSubclass(String hazmatSubclass) {
		this.hazmatSubclass = hazmatSubclass;
	}

	public String getHazmatAccess() {
		return this.hazmatAccess;
	}

	public void setHazmatAccess(String hazmatAccess) {
		this.hazmatAccess = hazmatAccess;
	}

	public Integer getSqYards() {
		return this.sqYards;
	}

	public void setSqYards(Integer sqYards) {
		this.sqYards = sqYards;
	}

	public String getCntryOfManuf() {
		return this.cntryOfManuf;
	}

	public void setCntryOfManuf(String cntryOfManuf) {
		this.cntryOfManuf = cntryOfManuf;
	}

	public String getPackageContentType() {
		return this.packageContentType;
	}

	public void setPackageContentType(String packageContentType) {
		this.packageContentType = packageContentType;
	}

	public String getHtsCode() {
		return this.htsCode;
	}

	public void setHtsCode(String htsCode) {
		this.htsCode = htsCode;
	}

	public Integer getHazmatNumContainers() {
		return this.hazmatNumContainers;
	}

	public void setHazmatNumContainers(Integer hazmatNumContainers) {
		this.hazmatNumContainers = hazmatNumContainers;
	}

	public String getHazmatContainerType() {
		return this.hazmatContainerType;
	}

	public void setHazmatContainerType(String hazmatContainerType) {
		this.hazmatContainerType = hazmatContainerType;
	}

	public Integer getHazmatQty() {
		return this.hazmatQty;
	}

	public void setHazmatQty(Integer hazmatQty) {
		this.hazmatQty = hazmatQty;
	}

	public String getHazmatQtyUnits() {
		return this.hazmatQtyUnits;
	}

	public void setHazmatQtyUnits(String hazmatQtyUnits) {
		this.hazmatQtyUnits = hazmatQtyUnits;
	}

	public String getHazmatQtyType() {
		return this.hazmatQtyType;
	}

	public void setHazmatQtyType(String hazmatQtyType) {
		this.hazmatQtyType = hazmatQtyType;
	}

	public String getHazmatAircraft() {
		return this.hazmatAircraft;
	}

	public void setHazmatAircraft(String hazmatAircraft) {
		this.hazmatAircraft = hazmatAircraft;
	}

	public Double getLinearFeet() {
		return this.linearFeet;
	}

	public void setLinearFeet(Double linearFeet) {
		this.linearFeet = linearFeet;
	}

	public Integer getFedexHazmatId() {
		return this.fedexHazmatId;
	}

	public void setFedexHazmatId(Integer fedexHazmatId) {
		this.fedexHazmatId = fedexHazmatId;
	}

	public Integer getPickupStopId() {
		return this.pickupStopId;
	}

	public void setPickupStopId(Integer pickupStopId) {
		this.pickupStopId = pickupStopId;
	}

	public Integer getDropoffStopId() {
		return this.dropoffStopId;
	}

	public void setDropoffStopId(Integer dropoffStopId) {
		this.dropoffStopId = dropoffStopId;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Double getForRate() {
		return this.forRate;
	}

	public void setForRate(Double forRate) {
		this.forRate = forRate;
	}

	public Double getForCharge() {
		return this.forCharge;
	}

	public void setForCharge(Double forCharge) {
		this.forCharge = forCharge;
	}

	public Double getForTbbRate() {
		return this.forTbbRate;
	}

	public void setForTbbRate(Double forTbbRate) {
		this.forTbbRate = forTbbRate;
	}

	public Double getForTbbCharge() {
		return this.forTbbCharge;
	}

	public void setForTbbCharge(Double forTbbCharge) {
		this.forTbbCharge = forTbbCharge;
	}
}
