package com.banyan.FullLoadRequest.Services.Project44;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.Entities.StopOff;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Repos.StopOffRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookingBuilderService;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Project44.Address44;
import com.banyan.FullLoadRequest.models.Project44.AppointmentWindow;
import com.banyan.FullLoadRequest.models.Project44.Contact44;
import com.banyan.FullLoadRequest.models.Project44.Location44;
import com.banyan.FullLoadRequest.models.Project44.ShipmentStops;

@Service
public class ShipmentStopService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(ShipmentStopService.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	
	@Autowired
	StopOffRepository stopoffRepo;
	@Autowired
	RateQtAddressRepository addressRepo;
	@Autowired
	BookingRepository bookRepo;
	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	BookingBuilderService bookBuildService;

	public List<ShipmentStops> buildShipmentStops(Integer rateId) {

		List<StopOff> stopList = new ArrayList<>();
		List<Integer> addressList = new ArrayList<>();
		List<ShipmentStops> shipStops = new ArrayList<>();
		addressList = addressRepo.findIdByRtQteId(rateId);
		if(addressList.isEmpty()) {
			nxtLogger.error("No RateQuoteAddress for given BookingId: "+rateId);
		return null;
		}
		stopList = stopoffRepo.findAllByRtQteAddId(addressList.get(0));

		if (stopList.size() == 0) {
			return getOriginDestinationStops(rateId, addressList.get(0));
		}

		for (int i = 0; i < stopList.size(); i++) {
			StopOff stopoff = new StopOff();
			stopoff = stopList.get(i);
			ShipmentStops shipStop = new ShipmentStops();

			Address44 address = new Address44();
			List<String> addressLines = new ArrayList<>();
			addressLines.add(stopoff.getAddress());
			address = new Address44.Builder().setCity(stopoff.getCity()).setCountry(stopoff.getCountry())
					.setPostalCode(stopoff.getZip()).setState(stopoff.getState()).setAddressLines(addressLines).build();

			Contact44 contact = new Contact44();
			contact = new Contact44.Builder().setCompanyName(stopoff.getName())
					.setContactName(stopoff.getContact_name()).setEmail(stopoff.getEmail())
					.setPhoneNumber(stopoff.getPhone()).setFaxNumber(null).setPhoneNumber2(stopoff.getExtension())
					.build();

			Location44 location = new Location44(address, contact, null);

			AppointmentWindow appointment = new AppointmentWindow();
			appointment.setStartDateTime(stopoff.getEarliest_dt());
			appointment.setEndDateTime(stopoff.getLatest_dt());

			shipStop = new ShipmentStops.Builder().setStopNumber(stopoff.getStop_order()).setStopName(stopoff.getName())
					.setAppointmentWindow(appointment).setGeoCoordinates(null).setLocation(location).build();
			shipStops.add(shipStop);

			shipStops.add(shipStop);
		}
		return shipStops;
	}

	private List<ShipmentStops> getOriginDestinationStops(Integer rateId, Integer addId) {

		Booking book = new Booking();
		book = bookRepo.findById(rateId).get();
		fullLoad =bookBuildService.getFullLoad(book);
		ShipmentStops shipStop = new ShipmentStops();
		List<ShipmentStops> shipStops = new ArrayList<>();
		RateQtAddress add = new RateQtAddress();
		add = addressRepo.findById(addId).get();

		// Origin
		// FullLoad Shipper Address as First Stop Address
		Address44 address = new Address44();

		List<String> addressLines = new ArrayList<>();
		addressLines.add(fullLoad.getShipper().getAddressInfo().getAddress1());
		if (fullLoad.getShipper().getAddressInfo().getAddress2() != null)
			addressLines.add(fullLoad.getShipper().getAddressInfo().getAddress2());

		address = new Address44.Builder().setCity(fullLoad.getShipper().getAddressInfo().getCity())
				.setCountry(fullLoad.getShipper().getAddressInfo().getCountryCode())
				.setPostalCode(fullLoad.getShipper().getAddressInfo().getZipcode())
				.setState(fullLoad.getShipper().getAddressInfo().getState()).setAddressLines(addressLines).build();

		// FullLoad Shipper Contact as First Stop Contact
		Contact44 contact = new Contact44();
		contact = new Contact44.Builder().setCompanyName(fullLoad.getShipper().getCompanyName())
				.setContactName(fullLoad.getShipper().getContactInfo().getContactName())
				.setEmail(fullLoad.getShipper().getContactInfo().getEmail())
				.setPhoneNumber(fullLoad.getShipper().getContactInfo().getPhone())
				.setFaxNumber(fullLoad.getShipper().getContactInfo().getFax())
				.setPhoneNumber2(null).build();

		Location44 location = new Location44(address, contact, null);
		AppointmentWindow appointment = new AppointmentWindow();

		if (add.getEarliestPickupDt() != null)
			appointment.setStartDateTime(add.getEarliestPickupDt().toLocalDateTime().toString()+":00");
		else {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			c.add(Calendar.HOUR_OF_DAY, 1);
			c.add(Calendar.SECOND, 00);
			time.setTime(c.getTime().getTime());
			appointment.setStartDateTime(time.toLocalDateTime().toString()+":00");
		}
		if (add.getLatestPickupDt() != null)
			appointment.setEndDateTime(add.getLatestPickupDt().toLocalDateTime().toString()+":00");
		else {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			c.add(Calendar.HOUR_OF_DAY, 7);
			c.add(Calendar.SECOND, 00);
			time.setTime(c.getTime().getTime());
			appointment.setEndDateTime(time.toLocalDateTime().toString()+":00");
		}

		// FullLoad Shipper Information as Origin|First Stop
		shipStop = new ShipmentStops.Builder().setStopNumber(1).setStopName("Shipper Origin")
				.setAppointmentWindow(appointment).setGeoCoordinates(null).setLocation(location).build();
		shipStops.add(shipStop);

		// Destination
		// Booking Consignee Information as Destination ShipmentStop
		List<String> addressLines1 = new ArrayList<>();
		addressLines1.add(fullLoad.getConsignee().getAddressInfo().getAddress1());
		if (fullLoad.getConsignee().getAddressInfo().getAddress2() != null)
			addressLines1.add(fullLoad.getConsignee().getAddressInfo().getAddress2());

		address = new Address44.Builder().setCity(fullLoad.getConsignee().getAddressInfo().getCity())
				.setCountry(fullLoad.getConsignee().getAddressInfo().getCountryCode())
				.setPostalCode(fullLoad.getConsignee().getAddressInfo().getZipcode())
				.setState(fullLoad.getConsignee().getAddressInfo().getState()).setAddressLines(addressLines1).build();

		contact = new Contact44.Builder().setCompanyName(fullLoad.getConsignee().getCompanyName())
				.setContactName(fullLoad.getConsignee().getContactInfo().getContactName())
				.setEmail(fullLoad.getConsignee().getContactInfo().getEmail())
				.setPhoneNumber(fullLoad.getConsignee().getContactInfo().getPhone())
				.setFaxNumber(fullLoad.getConsignee().getContactInfo().getFax())
				.setPhoneNumber2(null).build();

		Location44 location1 = new Location44(address, contact, null);
		AppointmentWindow appointment1 = new AppointmentWindow();

		if (add.getEarliestDeliveryDt() != null)
			appointment1.setStartDateTime(add.getEarliestDeliveryDt().toLocalDateTime().toString()+":00");
		else {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			c.add(Calendar.DAY_OF_WEEK, 1);
			c.add(Calendar.HOUR_OF_DAY, -7);
			c.add(Calendar.SECOND, 00);
			time.setTime(c.getTime().getTime());
			appointment1.setStartDateTime(time.toLocalDateTime().toString());
		}
		if (add.getLatestDeliveryDt() != null)
			appointment1.setEndDateTime(add.getLatestDeliveryDt().toLocalDateTime().toString()+":00");
		else {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			c.add(Calendar.DAY_OF_WEEK, 1);
			c.add(Calendar.HOUR_OF_DAY, 7);
			c.add(Calendar.SECOND, 00);
			time.setTime(c.getTime().getTime());
			appointment1.setEndDateTime(time.toLocalDateTime().toString()+":00");
		}
		ShipmentStops shipStop1 = new ShipmentStops();
		shipStop1 = new ShipmentStops.Builder().setStopNumber(2).setStopName("Consignee Destination")
				.setAppointmentWindow(appointment1).setGeoCoordinates(null).setLocation(location1).build();
		shipStops.add(shipStop1);
		return shipStops;
	}
}
