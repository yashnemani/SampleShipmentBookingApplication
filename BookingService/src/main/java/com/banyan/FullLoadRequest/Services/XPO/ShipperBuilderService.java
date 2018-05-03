package com.banyan.FullLoadRequest.Services.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Shipper;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_Phone;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_Shipper;

@Service
public class ShipperBuilderService {

	@Autowired
	XPO_Shipper xpoShipper;
	@Autowired
	private XPO_Phone xPO_Phone;
	@Autowired
	PhoneBuildService phoneService;

	public XPO_Shipper buildShipper(Shipper shipper) {

		xPO_Phone = phoneService.buildPhone(shipper.getContactInfo());
		String cntryCd = shipper.getAddressInfo().getCountryCode();
		if(cntryCd==null)
			cntryCd="US";
		else if(cntryCd.equals("CA"))
			cntryCd="CN";
		xpoShipper = new XPO_Shipper.Builder().setName(shipper.getContactInfo().getContactName())
				.setAddressLine1(shipper.getAddressInfo().getAddress1())
				.setAddressLine2(shipper.getAddressInfo().getAddress2()).setCityName(shipper.getAddressInfo().getCity())
				.setCountryCd(cntryCd)
				.setPostalCd(shipper.getAddressInfo().getZipcode()).setStateCd(shipper.getAddressInfo().getState())
				.setPhone(xPO_Phone).build();

		return xpoShipper;
	}
}
