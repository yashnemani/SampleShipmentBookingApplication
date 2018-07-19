package com.banyan.FullLoadRequest.Services.UPS;

import java.sql.Timestamp;
import java.util.Calendar;
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
import com.banyan.FullLoadRequest.models.Pickup.UPS.FreightPickupRequest;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Request;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Requester;
import com.banyan.FullLoadRequest.models.Pickup.UPS.ShipFrom;
import com.banyan.FullLoadRequest.models.Pickup.UPS.ShipTo;
import com.banyan.FullLoadRequest.models.Pickup.UPS.ShipmentDetail;

@Service
public class FreightPickup_ReqBuilderService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(FreightPickup_ReqBuilderService.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	
	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	Booking book;
	@Autowired
	FreightPickupRequest freightPickup;
	@Autowired
	Requester requester;
	@Autowired
	ShipFrom shipFrom;
	@Autowired
	ShipTo shipTo;
	@Autowired
	ShipmentDetail shipDetail;
	@Autowired
	Request request;
	@Autowired
	RateQtAddressRepository addRepo;

	@Autowired
	ShipFromBuilderService shipFromBuildService;
	@Autowired
	RequesterBuildService requesterBuildService;
	@Autowired
	ShipToBuilderService shiptoBuildService;
	@Autowired
	ShipDetailBuilderService detailBuildService;
	@Autowired
	BookingBuilderService bookService;

	private Timestamp pkupDate = new Timestamp(System.currentTimeMillis());

	public Timestamp getPkupDate() {
		return pkupDate;
	}

	public FreightPickupRequest buildfreightPickup(int bookingId) {

		book = bookService.getBooking(bookingId);
		if (book == null) {
			log.info("booking with ID " + bookingId + " does not exist in the DB! Try a valid ID");
			return null;
		}
		fullLoad = bookService.getFullLoad(book);
		if (fullLoad == null) {
			nxtLogger.error("UPS Pickup Error generating FullLoad from given Booking! " + bookingId);
			return null;
		}

		requester = requesterBuildService.buildRequester(fullLoad.getConsignee());

		shipFrom = shipFromBuildService.buildShipFrom(fullLoad.getShipper());

		shipTo = shiptoBuildService.buildShipTo(fullLoad.getConsignee());

		shipDetail = detailBuildService.buildShipDetail(fullLoad.getProducts().get(0));

		String additionalComments = fullLoad.getRateServices().get(0).getSpecialInstructions();
		String destinationPostalCode = shipTo.getAddress().getPostalCode();
		String destinationCountryCode = shipTo.getAddress().getCountryCode();
		String deliveryInstructions = fullLoad.getConsignee().getNote();
		String pickupInstructions = fullLoad.getShipper().getNote();

		List<RateQtAddress> addresses = addRepo.FindAllByRtQteId(bookingId);
		RateQtAddress address = addresses.get(0);
		pkupDate = address.getDtProjectedPickup();
		String PkUpDate = null;
		if (pkupDate != null)
			PkUpDate = pkupDate.toString();

		Timestamp futureDay = new Timestamp(System.currentTimeMillis());
		Timestamp current = new Timestamp(System.currentTimeMillis());

		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.add(Calendar.HOUR_OF_DAY, 1);
		current.setTime(c.getTime().getTime());

		Calendar cal = Calendar.getInstance();
		cal.setTime(futureDay);
		// Needs Review
		if (cal.get(Calendar.DAY_OF_WEEK) == 6)
			cal.add(Calendar.DAY_OF_WEEK, 3);
		else if (cal.get(Calendar.DAY_OF_WEEK) == 7)
			cal.add(Calendar.DAY_OF_WEEK, 2);
		else
			cal.add(Calendar.DAY_OF_WEEK, 1);
		cal.set(Calendar.HOUR_OF_DAY, 9);
		futureDay.setTime(cal.getTime().getTime());

		if (PkUpDate != null) {
			log.info("PkUpDate Timestamp " + PkUpDate);
			if (!pkupDate.after(current))
				PkUpDate = futureDay.toString();
		} else
			PkUpDate = futureDay.toString();

		PkUpDate = PkUpDate.substring(0, 10);
		PkUpDate = PkUpDate.replace("-", "");
		log.info("PkUpDate Date " + PkUpDate);

		String earliestPkUpTime = address.getTimeFreightReady();
		if (earliestPkUpTime == null)
			earliestPkUpTime = "1200";
		else
			earliestPkUpTime = earliestPkUpTime.replace(":", "");

		String latestPkUpTime = address.getTimeShipperCloses();
		if (latestPkUpTime == null)
			latestPkUpTime = "1730";
		else
			latestPkUpTime = latestPkUpTime.replace(":", "");

		if ((Integer.parseInt(latestPkUpTime) - Integer.parseInt(earliestPkUpTime))<130) {
			earliestPkUpTime = "1200";
			latestPkUpTime = "1730";
		}
			

		log.info(PkUpDate + "  " + earliestPkUpTime + "  " + latestPkUpTime);
		freightPickup = new FreightPickupRequest.Builder().setAdditionalComments(additionalComments)
				.setDestinationCountryCode(destinationCountryCode).setDestinationPostalCode(destinationPostalCode)
				.setDeliveryInstructions(deliveryInstructions).setPickupInstructions(pickupInstructions)
				.setRequest(request).setRequester(requester).setShipDetail(shipDetail).setShipFrom(shipFrom)
				.setShipTo(shipTo).setSpecialInstructions(null).setPickupDate(PkUpDate)
				.setEarliestTimeReady(earliestPkUpTime).setLatestTimeReady(latestPkUpTime).build();

		return freightPickup;
	}
}
