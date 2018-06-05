package com.banyan.FullLoadRequest.Services.XPO;

import java.util.List;

import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookingBuilderService;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Pickup.XPO.PickupRqstInfo;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_PkupRequest;

@Service
public class XPO_PickupBuildService {

	@Autowired
	XPO_PkupRequest xpoPkup;
	@Autowired
	Booking book;
	@Autowired
	BookingBuilderService bookService;
	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	RateQtAddressRepository addressRep;
	@Autowired
	PkupRequestBuildService pkupReqService;
	@Autowired
	PickupRqstInfo pkupRqst;

	public XPO_PkupRequest buildXpoPkupReq(int id, boolean test) {

		book = bookService.getBooking(id);
		if (book == null) {
			System.out.println("booking with ID " + id + " does not exist in the DB! Try a valid ID");
			return null;
		}
		fullLoad = bookService.getFullLoad(book);
		if (fullLoad == null) {
			System.out.println("Error generating FullLoad from given Booking!");
			Logger.error("XPO Pickup Error generating FullLoad from given Booking! "+id);
			return null;
		}

		List<RateQtAddress> addresses = addressRep.FindAllByRtQteId(id);
		RateQtAddress rtAddress;
		if (addresses != null)
			rtAddress = addresses.get(0);
		else {
			System.out.println("RateQuoteAddress does not exist for ID " + id);
			Logger.error("XPO Pickup Error rateQuoteAddress doesn't exist "+id);
			return null;
		}

		pkupRqst = pkupReqService.buildPkupRqst(fullLoad, rtAddress, test);
		System.out.println("Timestamps ");
		System.out.println(pkupRqst.getPkupDate() + "  " + pkupRqst.getReadyTime() + "  " + pkupRqst.getCloseTime());
		xpoPkup = new XPO_PkupRequest(pkupRqst);
		return xpoPkup;
	}
}
