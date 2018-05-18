package com.banyan.FullLoadRequest.Services.Booking;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQtDetail;
import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Repos.RateQtDetailRepository;
import com.banyan.FullLoadRequest.models.Booking.ParcelOptions;
import com.banyan.FullLoadRequest.models.Booking.Product;
import com.banyan.FullLoadRequest.models.enums.FreightClasses;
import com.banyan.FullLoadRequest.models.enums.PackageTypes;
import com.banyan.FullLoadRequest.models.enums.UnitsofMeasure;

@Service
public class ProductBuildService {

	@Autowired
	RateQtAddressRepository addressRep;
	@Autowired
	RateQtDetailRepository detailRep;

	PackageTypes PackageType;
	UnitsofMeasure uom;

	public Product buildProduct(RateQtDetail detail) {

		Integer shippingQty = 1;
		Integer noSkids = detail.getNoSkids();
		Integer noPieces = detail.getNoPieces();
		if (noSkids != 0)
			shippingQty = noSkids;
		else if (noPieces != 0)
			shippingQty = noPieces;

		int addId = detail.getRtQteAddId();

		Integer pkgType = null;
		if (detailRep.findBanPkgType(addId) != null) {
			pkgType = detailRep.findBanPkgType(addId);
			PackageType = PackageTypes.values()[pkgType];
		}

		FreightClasses freightClass;
		Integer c = 50;
		if (detail.getClassNo() != null)
			c = detail.getClassNo().intValue();
		String f = "c" + c;
		freightClass = FreightClasses.valueOf(f);
		System.out.println(freightClass.getValue());

		BigDecimal weight = null, height = null, width = null, length = null;
		double ff = 0;

		if (detail.getWeight() != null)
			ff = detail.getWeight();
		weight = new BigDecimal(ff);

		if (detail.getHeight() != null)
			ff = detail.getHeight();
		height = new BigDecimal(ff);

		if (detail.getWidth() != null)
			ff = detail.getWidth();
		width = new BigDecimal(ff);

		if (detail.getLength() != null)
			ff = detail.getLength();
		length = new BigDecimal(ff);

		boolean isHazmat = false;
		String haz = detail.getHazmat();
		if (haz.equals("Y"))
			isHazmat = true;
		else if (haz.equals("N"))
			isHazmat = false;

		String desc = null, nmfc = null;
		desc = detail.getCommodity();
		nmfc = detail.getNmfcNumber();

		uom = UnitsofMeasure.values()[0];
		ParcelOptions parcel = new ParcelOptions.Builder().setDeliveryConfirmation(0).setCOD(0).setCODAmount(null)
				.setInsuranceAmount(null).build();

		Product product = new Product.Builder().setQuantity(shippingQty).setPackageType(pkgType).setWeight(weight)
				.setClass(freightClass).setNMFC(nmfc).setSKU(null).setIsHazmat(isHazmat).setHazmatPhoneNumber(null)
				.setHazmatPhoneExt(null).setDescription(desc).setLength(length).setHeight(height).setWidth(width)
				.setSortOrder(1).setUOM(uom.getValue()).setParcelOptions(parcel).build();

		return product;
	}
}
