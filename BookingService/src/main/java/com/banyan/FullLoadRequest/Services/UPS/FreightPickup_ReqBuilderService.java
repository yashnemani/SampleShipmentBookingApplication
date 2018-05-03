package com.banyan.FullLoadRequest.Services.UPS;

import java.util.List;

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

	public FreightPickupRequest buildfreightPickup(int bookingId) {

		book = bookService.getBooking(bookingId);
		if (book == null) {
			System.out.println("booking with ID " + bookingId + " does not exist in the DB! Try a valid ID");
			return null;
		}
		fullLoad = bookService.getFullLoad(book);
		if (fullLoad == null) {
			System.out.println("Error generating FullLoad from given Booking!");
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

		String PkUpDate = address.getDtProjectedPickup().toString();
		if (PkUpDate == null)
			PkUpDate = "20180501";
		else {
			/*
			 * PkUpDate = PkUpDate.substring(0, 10); PkUpDate = PkUpDate.replace("-", "");
			 */
			PkUpDate = "20180501";
		}

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

		System.out.println(PkUpDate + "  " + earliestPkUpTime + "  " + latestPkUpTime);
		freightPickup = new FreightPickupRequest.Builder().setAdditionalComments(additionalComments)
				.setDestinationCountryCode(destinationCountryCode).setDestinationPostalCode(destinationPostalCode)
				.setDeliveryInstructions(deliveryInstructions).setPickupInstructions(pickupInstructions)
				.setRequest(request).setRequester(requester).setShipDetail(shipDetail).setShipFrom(shipFrom)
				.setShipTo(shipTo).setSpecialInstructions(null).setPickupDate(PkUpDate)
				.setEarliestTimeReady(earliestPkUpTime).setLatestTimeReady(latestPkUpTime).build();

		return freightPickup;
	}
}