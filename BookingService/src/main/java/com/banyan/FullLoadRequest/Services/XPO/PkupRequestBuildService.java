package com.banyan.FullLoadRequest.Services.XPO;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Pickup.XPO.PickupRqstInfo;
import com.banyan.FullLoadRequest.models.Pickup.XPO.PkupItem;
import com.banyan.FullLoadRequest.models.Pickup.XPO.Requestor;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_Contact;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_Shipper;

@Service
public class PkupRequestBuildService {

	@Autowired
	PickupRqstInfo pkupRqst;
	@Autowired
	Requestor requestor;
	@Autowired
	XPO_Shipper shipper;
	@Autowired
	XPO_Contact contact;
	@Autowired
	PkupItem pkupItem;

	// Services
	@Autowired
	ContactBuilderService contactService;
	@Autowired
	RequestorBuildService requestorService;
	@Autowired
	ShipperBuilderService shipperService;
	@Autowired
	PkupItemBuildService pkupItemService;

	private Timestamp pickupDate = null;

	public Timestamp getPkupDate() {
		return pickupDate;
	}

	public PickupRqstInfo buildPkupRqst(FullLoad_Request fullLoad, RateQtAddress rtAddress, boolean test) {

		requestor = requestorService.buildRequestor(fullLoad.getShipper(), test);
		shipper = shipperService.buildShipper(fullLoad.getShipper(), test);
		contact = contactService.buildContact(fullLoad.getShipper(), test);
		pkupItem = pkupItemService.buildPkupItem(fullLoad, test);

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		pickupDate = rtAddress.getDtProjectedPickup();
		String freightReadyTime = rtAddress.getTimeFreightReady();
		String shipperCloseTime = rtAddress.getTimeShipperCloses();

		if (pickupDate == null)
			pickupDate = new Timestamp(System.currentTimeMillis());
		if (freightReadyTime == null)
			freightReadyTime = "10:00";
		if (shipperCloseTime == null)
			shipperCloseTime = "17:00";

		java.sql.Timestamp readyTime = null;
		java.sql.Timestamp closeTime = null;
		LocalDateTime pkupTime = null;
		LocalDateTime readyTimes = null;
		LocalDateTime closeTimes = null;

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

		if (!pickupDate.after(current))
			pickupDate = futureDay;

		try {
			readyTime = new java.sql.Timestamp(
					(dateTimeFormatter.parse(dateFormatter.format(pickupDate) + " " + freightReadyTime + ":00")).getTime());
			closeTime = new java.sql.Timestamp(
					(dateTimeFormatter.parse(dateFormatter.format(pickupDate) + " " + shipperCloseTime + ":00")).getTime());

			pkupTime = pickupDate.toLocalDateTime().withSecond(10);
			readyTimes = readyTime.toLocalDateTime().withMinute(15).withSecond(10);
			closeTimes = closeTime.toLocalDateTime().withMinute(30).withSecond(10);

			if (readyTimes.getHour() < 6)
				readyTimes = readyTimes.withHour(6);
			if (closeTimes.getHour() >=18)
				closeTimes = closeTimes.withHour(18).withMinute(00);
			System.out.println(pickupDate + "  " + readyTime + "  " + closeTime);
			System.out.println(pkupTime + "  " + readyTimes + "  " + closeTimes);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<PkupItem> pkupItems = new ArrayList<>();
		pkupItems.add(pkupItem);

		String remarks = fullLoad.getConsignee().getNote();
		String driverNote = fullLoad.getShipper().getNote();

		pkupRqst = new PickupRqstInfo.Builder().setPkupDate(pkupTime.toString()).setReadyTime(readyTimes.toString())
				.setCloseTime(closeTimes.toString()).setContact(contact).setShipper(shipper).setRequestor(requestor)
				.setPkupItem(pkupItems).setDriverNote(driverNote).setSpecialEquipmentCd("L").setRemarks(remarks)
				.build();
		return pkupRqst;
	}
}
