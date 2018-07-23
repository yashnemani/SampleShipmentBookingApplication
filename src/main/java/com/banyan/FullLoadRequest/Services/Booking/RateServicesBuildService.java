package com.banyan.FullLoadRequest.Services.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.Entities.RateQtDetail;
import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Repos.RateQtDetailRepository;
import com.banyan.FullLoadRequest.models.Booking.RateServices;
import com.banyan.FullLoadRequest.models.enums.EquipmentTypes;
import com.banyan.FullLoadRequest.models.enums.PackageTypes;
import com.banyan.FullLoadRequest.models.enums.ServiceCodes;
import com.banyan.FullLoadRequest.models.enums.SizeUnitsofMeasure;
import com.banyan.FullLoadRequest.models.enums.WeightUnitsofMeasure;

@Service
public class RateServicesBuildService {

	@Autowired
	RateQtAddressRepository addressRep;
	@Autowired
	RateQtDetailRepository detailRep;

	ServiceCodes ServiceCode;

	PackageTypes PackageType;

	EquipmentTypes EquipmentType;

	WeightUnitsofMeasure WeightUom;

	SizeUnitsofMeasure SizeUom;

	public RateServices buildRateService(RateQtAddress rtQtAddress, RateQtDetail detail) {

		String shipmentType = rtQtAddress.getShipmentType();
		String specialRequirements = rtQtAddress.getSpecialRequirements();
		int addId = rtQtAddress.getId();

		Integer shippingQty = 1;
		Integer noSkids = detail.getNoSkids();
		Integer noPieces = detail.getNoPieces();
		if (noSkids != 0)
			shippingQty = noSkids;
		else if (noPieces != 0)
			shippingQty = noPieces;

		if (shipmentType.equals("LTL") || shipmentType.equals("LTE"))
			ServiceCode = ServiceCodes.LTL;
		else if (shipmentType.equals("PKG"))
			ServiceCode = ServiceCodes.Parcel;
		else if (shipmentType.startsWith("TL"))
			ServiceCode = ServiceCodes.Volume;
		else
			ServiceCode = ServiceCodes.LTL;

		Integer pkgType = null;
		if (detailRep.findBanPkgType(addId) != null) {
			pkgType = detailRep.findBanPkgType(addId);
			PackageType = PackageTypes.values()[pkgType];
			System.out.println();
			System.out.println("Package Type: " + PackageType);
		}

		EquipmentType = EquipmentTypes.Other;
		WeightUom = WeightUnitsofMeasure.LBS;
		SizeUom = SizeUnitsofMeasure.FT;

		RateServices rateService = new RateServices.Builder().setServiceCode(ServiceCode).setShippingQty(shippingQty)
				.setPackageType(pkgType).setEquipmentType(EquipmentType.getValue()).setAdditionalWeight(null)
				.setLength(null).setWidth(null).setHeight(null).setSpecialInstructions(specialRequirements)
				.setSizeUom(SizeUom.getValue()).setWeightUom(WeightUom.getValue()).build();

		return rateService;
	}
}
