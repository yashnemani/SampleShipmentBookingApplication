package com.banyan.FullLoadRequest.Services.XPO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(XPO_PickupBuildService.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	
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
			log.info("booking with ID " + id + " does not exist in the DB! Try a valid ID");
			return null;
		}
		fullLoad = bookService.getFullLoad(book);
		if (fullLoad == null) {
			nxtLogger.error("XPO Pickup Error generating FullLoad from given Booking! "+id);
			return null;
		}

		List<RateQtAddress> addresses = addressRep.FindAllByRtQteId(id);
		RateQtAddress rtAddress;
		if (addresses != null)
			rtAddress = addresses.get(0);
		else {
			nxtLogger.error("XPO Pickup Error rateQuoteAddress doesn't exist "+id);
			return null;
		}

		pkupRqst = pkupReqService.buildPkupRqst(fullLoad, rtAddress, test);
		log.info("Timestamps ");
		log.info(pkupRqst.getPkupDate() + "  " + pkupRqst.getReadyTime() + "  " + pkupRqst.getCloseTime());
		xpoPkup = new XPO_PkupRequest(pkupRqst);
		return xpoPkup;
	}
}
