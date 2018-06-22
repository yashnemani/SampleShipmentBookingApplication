package com.banyan.FullLoadRequest.Services.Project44;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.StopOff;
import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Repos.StopOffRepository;
import com.banyan.FullLoadRequest.models.Project44.Address44;
import com.banyan.FullLoadRequest.models.Project44.AppointmentWindow;
import com.banyan.FullLoadRequest.models.Project44.Contact44;
import com.banyan.FullLoadRequest.models.Project44.GeoCoordinates;
import com.banyan.FullLoadRequest.models.Project44.Location44;
import com.banyan.FullLoadRequest.models.Project44.ShipmentStops;

@Service
public class ShipmentStopService {

	@Autowired
	StopOffRepository stopoffRepo;
	@Autowired
	RateQtAddressRepository addressRepo;

	public List<ShipmentStops> buildShipmentStops(Integer rateId) {

		//Temporary
		rateId=4634522;
		
		List<StopOff> stopList = new ArrayList<>();
		List<Integer> addressList = new ArrayList<>();
		List<ShipmentStops> shipStops = new ArrayList<>();

		addressList = addressRepo.findIdByRtQteId(rateId);
		stopList = stopoffRepo.findAllByRtQteAddId(addressList.get(0));

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

			Location44 location = new Location44(address, contact, "id");

			AppointmentWindow appointment = new AppointmentWindow();
			appointment.setStartDateTime(stopoff.getEarliest_dt());
			appointment.setEndDateTime(stopoff.getLatest_dt());
			appointment.setLocalizedTimeZoneIdentifier("EST");

			GeoCoordinates coordinates = new GeoCoordinates(100, 100);

			shipStop = new ShipmentStops.Builder().setStopNumber(stopoff.getStop_order()).setStopName(stopoff.getName())
					.setAppointmentWindow(appointment).setGeoCoordinates(coordinates).setLocation(location).build();
			shipStops.add(shipStop);
		}
		return shipStops;
	}
}
