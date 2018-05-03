package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class FullLoad_Request implements Serializable{

	private static final long serialVersionUID = 1L;
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

	private FullLoad_Request() {

	}

	private FullLoad_Request(Builder build) {
		this.AuthenticationData = build.AuthenticationData;
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
	}

	public static class Builder {

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

		public Builder setAuthenticationData(AuthenticationData AuthenticationData) {
			this.AuthenticationData = AuthenticationData;
			return this;
		}

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

		public FullLoad_Request build() {
			return new FullLoad_Request(this);
		}
	}
	@JsonProperty("AuthenticationData")
	public AuthenticationData getAuthenticationData() {
		return AuthenticationData;
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
}
