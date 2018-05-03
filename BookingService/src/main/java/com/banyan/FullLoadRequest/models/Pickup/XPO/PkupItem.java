package com.banyan.FullLoadRequest.models.Pickup.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class PkupItem {

	private String destZip;
	@Autowired
	private TotWeight totweight;
	private int loosePcsCount;
	private int palletCnt;
	private boolean garntInd;
	private boolean hazmatInd;
	private boolean frzbleInd;
	private boolean holDlvrInd;
	private boolean bulkLiquidInd;
	private boolean foodInd;
	private boolean earlyCutoff;
	private String remarks;

	public PkupItem() {

	}

	public PkupItem(Builder build) {

		this.destZip = build.destZip;
		this.totweight = build.totweight;
		this.loosePcsCount = build.loosePcsCount;
		this.palletCnt = build.palletCnt;
		this.garntInd = build.garntInd;
		this.hazmatInd = build.hazmatInd;
		this.frzbleInd = build.frzbleInd;
		this.holDlvrInd = build.holDlvrInd;
		this.bulkLiquidInd = build.bulkLiquidInd;
		this.foodInd = build.foodInd;
		this.earlyCutoff = build.earlyCutoff;
		this.remarks = build.remarks;
	}

	public static class Builder {

		private String destZip;
		@Autowired
		private TotWeight totweight;
		private int loosePcsCount;
		private int palletCnt;
		private boolean garntInd;
		private boolean hazmatInd;
		private boolean frzbleInd;
		private boolean holDlvrInd;
		private boolean bulkLiquidInd;
		private boolean foodInd;
		private boolean earlyCutoff;
		private String remarks;

		public Builder setDestZip(String destZip) {
			this.destZip = destZip;
			return this;
		}

		public Builder setTotweight(TotWeight totweight) {
			this.totweight = totweight;
			return this;
		}

		public Builder setLoosePcsCount(int loosePcsCount) {
			this.loosePcsCount = loosePcsCount;
			return this;
		}

		public Builder setPalletCnt(int palletCnt) {
			this.palletCnt = palletCnt;
			return this;
		}

		public Builder setGarntInd(boolean garntInd) {
			this.garntInd = garntInd;
			return this;
		}

		public Builder setHazmatInd(boolean hazmatInd) {
			this.hazmatInd = hazmatInd;
			return this;
		}

		public Builder setFrzbleInd(boolean frzbleInd) {
			this.frzbleInd = frzbleInd;
			return this;
		}

		public Builder setHolDlvrInd(boolean holDlvrInd) {
			this.holDlvrInd = holDlvrInd;
			return this;
		}

		public Builder setBulkLiquidInd(boolean bulkLiquidInd) {
			this.bulkLiquidInd = bulkLiquidInd;
			return this;
		}

		public Builder setFoodInd(boolean foodInd) {
			this.foodInd = foodInd;
			return this;
		}

		public Builder setEarlyCutoff(boolean earlyCutoff) {
			this.earlyCutoff = earlyCutoff;
			return this;
		}

		public Builder setRemarks(String remarks) {
			this.remarks = remarks;
			return this;
		}

		public PkupItem build() {
			return new PkupItem(this);
		}
	}

	@JsonProperty("destZip6")
	public String getDestZip() {
		return destZip;
	}

	@JsonProperty("totWeight")
	public TotWeight getTotweight() {
		return totweight;
	}

	@JsonProperty("loosePiecesCnt")
	public int getLoosePcsCount() {
		return loosePcsCount;
	}

	@JsonProperty("palletCnt")
	public int getPalletCnt() {
		return palletCnt;
	}

	@JsonProperty("garntInd")
	public boolean isGarntInd() {
		return garntInd;
	}

	@JsonProperty("hazmatInd")
	public boolean isHazmatInd() {
		return hazmatInd;
	}

	@JsonProperty("frzbleInd")
	public boolean isFrzbleInd() {
		return frzbleInd;
	}

	@JsonProperty("holDlvrInd")
	public boolean isHolDlvrInd() {
		return holDlvrInd;
	}

	@JsonProperty("bulkLiquidInd")
	public boolean isBulkLiquidInd() {
		return bulkLiquidInd;
	}

	@JsonProperty("foodInd")
	public boolean isFoodInd() {
		return foodInd;
	}

	@JsonProperty("earlyCutOffTimeInd")
	public boolean isEarlyCutoff() {
		return earlyCutoff;
	}

	@JsonProperty("remarks")
	public String getRemarks() {
		return remarks;
	}
}
