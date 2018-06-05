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

	public XPO_Shipper buildShipper(Shipper shipper, boolean test) {

		xPO_Phone = phoneService.buildPhone(shipper.getContactInfo());
		String cntryCd = shipper.getAddressInfo().getCountryCode();

		String address1 = shipper.getAddressInfo().getAddress1();
		String address2 = shipper.getAddressInfo().getAddress2();

		if (address1 != null) {
			address1 = address1.replace("\n", " ").replace("\r", " ").replace("  ", " ");
			if (address1.length() >= 30) {
				String s1 = address1.substring(0, 29);
				String s2 = s1.substring(s1.lastIndexOf(" ") + 1, s1.length())
						+ address1.substring(29, address1.length());

				if (address2 != null)
					address2 = s2 + " " + address2;
				else
					address2 = s2;
				address1 = s1;
			}
		}
		if (address2 != null)
			address2 = address2.replace("\n", " ").replace("\r", " ").replace("  ", " ");

		if (cntryCd == null)
			cntryCd = "US";
		else if (cntryCd.equals("CA"))
			cntryCd = "CN";

		xpoShipper = new XPO_Shipper.Builder().setName(shipper.getContactInfo().getContactName())
				.setAddressLine1(address1).setAddressLine2(address2).setCityName(shipper.getAddressInfo().getCity())
				.setCountryCd(cntryCd).setPostalCd(shipper.getAddressInfo().getZipcode())
				.setStateCd(shipper.getAddressInfo().getState()).setPhone(xPO_Phone).build();

		return xpoShipper;
	}
}
