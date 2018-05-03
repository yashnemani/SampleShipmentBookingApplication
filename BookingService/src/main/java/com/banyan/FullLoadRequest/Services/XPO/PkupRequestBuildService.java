package com.banyan.FullLoadRequest.Services.XPO;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

	public PickupRqstInfo buildPkupRqst(FullLoad_Request fullLoad, RateQtAddress rtAddress) {

		requestor = requestorService.buildRequestor(fullLoad.getShipper());
		shipper = shipperService.buildShipper(fullLoad.getShipper());
		contact = contactService.buildContact(fullLoad.getShipper());
		pkupItem = pkupItemService.buildPkupItem(fullLoad);

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = new Date();
		java.sql.Timestamp pickupDate = new java.sql.Timestamp(dt.getTime());
		String freightReadyTime = "10:00";
		String shipperCloseTime = "17:00";
		if (rtAddress.getDtProjectedPickup() != null)
			pickupDate = rtAddress.getDtProjectedPickup();
		if (rtAddress.getTimeFreightReady() != null)
			freightReadyTime = rtAddress.getTimeFreightReady();
		if (rtAddress.getTimeFreightReady() != null)
			shipperCloseTime = rtAddress.getTimeFreightReady();
		java.sql.Timestamp readyTime = null;
		java.sql.Timestamp closeTime = null;
		String pkupDateStr = dateFormatter.format(pickupDate);
		LocalDateTime pkupTime = null;
		LocalDateTime readyTimes = null;
		LocalDateTime closeTimes = null;

		int year = LocalDateTime.now().getYear();
		int month = LocalDateTime.now().getMonth().getValue() + 1;
		int day = LocalDateTime.now().getDayOfMonth();
		try {
			pickupDate = new java.sql.Timestamp((dateTimeFormatter.parse(pkupDateStr + " " + "07:00:00").getTime()));
			readyTime = new java.sql.Timestamp(
					(dateTimeFormatter.parse(pkupDateStr + " " + freightReadyTime + ":00")).getTime());
			closeTime = new java.sql.Timestamp(
					(dateTimeFormatter.parse(pkupDateStr + " " + shipperCloseTime + ":00")).getTime());

			if (pickupDate.toLocalDateTime().isBefore(LocalDateTime.now())) {
				pkupTime = pickupDate.toLocalDateTime().withYear(year).withMonth(month).withDayOfMonth(day)
						.withSecond(10);
				readyTimes = readyTime.toLocalDateTime().withYear(year).withMonth(month).withDayOfMonth(day).withMinute(15)
						.withSecond(10);
				closeTimes = closeTime.toLocalDateTime().withYear(year).withMonth(month).withDayOfMonth(day).withMinute(30)
						.withSecond(10);
			} else {
				pkupTime = pickupDate.toLocalDateTime().withSecond(10);
				readyTimes = readyTime.toLocalDateTime().withMinute(15).withSecond(10);
				closeTimes = closeTime.toLocalDateTime().withMinute(30).withSecond(10);
			}
			if (readyTimes.getHour() < 6)
				readyTimes.withHour(6);
			if (closeTimes.getHour() > 18)
				closeTimes = closeTime.toLocalDateTime().withYear(year).withMonth(month).withDayOfMonth(day)
						.withHour(18).withSecond(10);
			System.out.println(pickupDate + "  " + readyTime + "  " + closeTime);
			System.out.println(pkupTime + "  " + readyTimes + "  " + closeTimes);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<PkupItem> pkupItems = new ArrayList<>();
		pkupItems.add(pkupItem);

		String remarks = fullLoad.getConsignee().getNote();
		String driverNote = fullLoad.getShipper().getNote();

		pkupRqst = new PickupRqstInfo.Builder().setPkupDate(pkupTime.toString()).setReadyTime(readyTimes.toString())
				.setCloseTime(closeTimes.toString()).setContact(contact).setShipper(shipper).setRequestor(requestor)
				.setPkupItem(pkupItems).setDriverNote(driverNote).setSpecialEquipmentCd("L").build();
		return pkupRqst;
	}
}
