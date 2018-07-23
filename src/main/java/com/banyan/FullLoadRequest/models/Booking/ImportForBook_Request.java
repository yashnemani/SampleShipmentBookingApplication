package com.banyan.FullLoadRequest.models.Booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class ImportForBook_Request {

	private String PickupDateTime;
	private String BOLNumber;
	private boolean DispatchLoad;
	private boolean DispatchOverride;
	private boolean SubmitPickup;
	private String ProNumber;
	private String ActualCarrierName;
	private QuoteInformation QuoteInformation;
	private AuthenticationData AuthenticationData;
	@Autowired
	private Loadinfo Loadinfo;
	@Autowired
	private BillTo BillTo;
	@Autowired
	private List<RateServices> RateServices;
	@Autowired
	private Shipper Shipper;
	@Autowired
	private Consignee Consignee;
	private PackageInfo PackageInfo;
	@Autowired
	private List<Product> Products;
	@Autowired
	private ShipperAccessorials ShipperAccessorials;
	@Autowired
	private ConsigneeAccessorials ConsigneeAccessorials;
	@Autowired
	private LoadAccessorials LoadAccessorials;
	private List<UserDefined> UserDefined;
	private List<ReferenceField> ReferenceField;
	private InsuranceInfo insuranceInfo;

	public ImportForBook_Request() {

	}

	private ImportForBook_Request(Builder build) {
		this.AuthenticationData = build.AuthenticationData;
		this.PickupDateTime = build.PickupDateTime;
		this.BOLNumber = build.BOLNumber;
		this.DispatchLoad = build.DispatchLoad;
		this.DispatchOverride = build.DispatchOverride;
		this.SubmitPickup = build.SubmitPickup;
		this.ProNumber = build.ProNumber;
		this.ActualCarrierName = build.ActualCarrierName;
		this.QuoteInformation = build.QuoteInformation;
		this.Loadinfo = build.Loadinfo;
		this.BillTo = build.BillTo;
		this.RateServices = build.RateServices;
		this.Shipper = build.Shipper;
		this.Consignee = build.Consignee;
		this.PackageInfo = build.PackageInfo;
		this.Products = build.Products;
		this.ShipperAccessorials = build.ShipperAccessorials;
		this.ConsigneeAccessorials = build.ConsigneeAccessorials;
		this.LoadAccessorials = build.LoadAccessorials;
		this.UserDefined = build.UserDefined;
		this.ReferenceField = build.ReferenceField;
		this.insuranceInfo = build.insuranceInfo;
	}

	public static class Builder {

		private String PickupDateTime;
		private String BOLNumber;
		private boolean DispatchLoad;
		private boolean DispatchOverride;
		private boolean SubmitPickup;
		private String ProNumber;
		private String ActualCarrierName;
		private QuoteInformation QuoteInformation;
		private AuthenticationData AuthenticationData;

		private Loadinfo Loadinfo;

		private BillTo BillTo;

		private List<RateServices> RateServices;

		private Shipper Shipper;

		private Consignee Consignee;
		private PackageInfo PackageInfo;

		private List<Product> Products;

		private ShipperAccessorials ShipperAccessorials;

		private ConsigneeAccessorials ConsigneeAccessorials;

		public Builder setLoadinfo(Loadinfo Loadinfo) {
			this.Loadinfo = Loadinfo;
			return this;
		}

		public Builder setBillTo(BillTo BillTo) {
			this.BillTo = BillTo;
			return this;
		}

		public Builder setRateServices(List<RateServices> RateServices) {
			this.RateServices = RateServices;
			return this;
		}

		public Builder setShipper(Shipper Shipper) {
			this.Shipper = Shipper;
			return this;
		}

		public Builder setConsignee(Consignee Consignee) {
			this.Consignee = Consignee;
			return this;
		}

		public Builder setPackageInfo(PackageInfo PackageInfo) {
			this.PackageInfo = PackageInfo;
			return this;
		}

		public Builder setProducts(List<Product> Products) {
			this.Products = Products;
			return this;
		}

		public Builder setShipperAccessorials(ShipperAccessorials ShipperAccessorials) {
			this.ShipperAccessorials = ShipperAccessorials;
			return this;
		}

		public Builder setConsigneeAccessorials(ConsigneeAccessorials ConsigneeAccessorials) {
			this.ConsigneeAccessorials = ConsigneeAccessorials;
			return this;
		}

		public Builder setLoadAccessorials(LoadAccessorials LoadAccessorials) {
			this.LoadAccessorials = LoadAccessorials;
			return this;
		}

		public Builder setUserDefined(List<UserDefined> UserDefined) {
			this.UserDefined = UserDefined;
			return this;
		}

		public Builder setReferenceField(List<ReferenceField> ReferenceField) {
			this.ReferenceField = ReferenceField;
			return this;
		}

		private LoadAccessorials LoadAccessorials;
		private List<UserDefined> UserDefined;
		private List<ReferenceField> ReferenceField;
		private InsuranceInfo insuranceInfo;

		public Builder setAuthenticationData(AuthenticationData AuthenticationData) {
			this.AuthenticationData = AuthenticationData;
			return this;
		}

		public Builder setPickupDateTime(String PickupDateTime) {
			this.PickupDateTime = PickupDateTime;
			return this;
		}

		public Builder setBOLNumber(String BOLNumber) {
			this.BOLNumber = BOLNumber;
			return this;
		}

		public Builder setDispatchLoad(boolean DispatchLoad) {
			this.DispatchLoad = DispatchLoad;
			return this;
		}

		public Builder setDispatchOverride(boolean DispatchOverride) {
			this.DispatchOverride = DispatchOverride;
			return this;
		}

		public Builder setSubmitPickup(boolean SubmitPickup) {
			this.SubmitPickup = SubmitPickup;
			return this;
		}

		public Builder setProNumber(String ProNumber) {
			this.ProNumber = ProNumber;
			return this;
		}

		public Builder setActualCarrierName(String ActualCarrierName) {
			this.ActualCarrierName = ActualCarrierName;
			return this;
		}

		public Builder setQuoteInformation(QuoteInformation QuoteInformation) {
			this.QuoteInformation = QuoteInformation;
			return this;
		}

		public Builder setInsuranceInfo(InsuranceInfo insuranceInfo) {
			this.insuranceInfo = insuranceInfo;
			return this;
		}

		public ImportForBook_Request build() {
			return new ImportForBook_Request(this);
		}
	}

	@JsonProperty("Loadinfo")
	public Loadinfo getLoadinfo() {
		return Loadinfo;
	}

	@JsonProperty("BillTo")
	public BillTo getBillTo() {
		return BillTo;
	}

	@JsonProperty("RateServices")
	public List<RateServices> getRateServices() {
		return RateServices;
	}

	@JsonProperty("Shipper")
	public Shipper getShipper() {
		return Shipper;
	}

	@JsonProperty("Consignee")
	public Consignee getConsignee() {
		return Consignee;
	}

	@JsonProperty("PackageInfo")
	public PackageInfo getPackageInfo() {
		return PackageInfo;
	}

	@JsonProperty("Products")
	public List<Product> getProducts() {
		return Products;
	}

	@JsonProperty("ShipperAccessorials")
	public ShipperAccessorials getShipperAccessorials() {
		return ShipperAccessorials;
	}

	@JsonProperty("ConsigneeAccessorials")
	public ConsigneeAccessorials getConsigneeAccessorials() {
		return ConsigneeAccessorials;
	}

	@JsonProperty("LoadAccessorials")
	public LoadAccessorials getLoadAccessorials() {
		return LoadAccessorials;
	}

	@JsonProperty("UserDefined")
	public List<UserDefined> getUserDefined() {
		return UserDefined;
	}

	@JsonProperty("ReferenceField")
	public List<ReferenceField> getReferenceField() {
		return ReferenceField;
	}

	@JsonProperty("AuthenticationData")
	public AuthenticationData getAuthenticationData() {
		return AuthenticationData;
	}

	@JsonProperty("PickupDateTime")
	public String getPickupDateTime() {
		return PickupDateTime;
	}

	@JsonProperty("BOLNumber")
	public String getBOLNumber() {
		return BOLNumber;
	}

	@JsonProperty("DispatchLoad")
	public boolean isDispatchLoad() {
		return DispatchLoad;
	}

	@JsonProperty("DispatchOverride")
	public boolean isDispatchOverride() {
		return DispatchOverride;
	}

	@JsonProperty("SubmitPickup")
	public boolean isSubmitPickup() {
		return SubmitPickup;
	}

	@JsonProperty("ProNumber")
	public String getProNumber() {
		return ProNumber;
	}

	@JsonProperty("ActualCarrierName")
	public String getActualCarrierName() {
		return ActualCarrierName;
	}

	@JsonProperty("QuoteInformation")
	public QuoteInformation getQuoteInformation() {
		return QuoteInformation;
	}
	
	 @JsonProperty("InsuranceInfo") public InsuranceInfo getInsuranceInfo() {
	 return insuranceInfo; }
	 

}
