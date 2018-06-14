package com.banyan.FullLoadRequest.Services.Banyan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookingBuilderService;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Booking.Loadinfo;

@Service
public class UpdateLoadService {

	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	BookingRepository bookRepo;
	@Autowired
	BookingBuilderService bookingService;

	@Transactional
	public FullLoad_Request updateLoad(int bookingId) {

		System.out.println("Booking ID: " + bookingId);

		Booking booking = new Booking();
		booking = bookRepo.findById(bookingId).get();

		fullLoad = bookingService.getFullLoad(booking);
		if (fullLoad == null)
			return null;
		String pro = null;
		String loadID = null;
		if (!booking.getReferences().stream().filter(a -> a.getRef_type() == 3).findFirst().isPresent()
				|| !booking.getReferences().stream().filter(a -> a.getRef_type() == 0).findFirst().isPresent()) {
			System.out.println("Load ID or PRO is not available for the following Booking " + bookingId);
			return null;
		} else {
			loadID = booking.getReferences().stream().filter(a -> a.getRef_type() == 3).findFirst().get()
					.getReference();
			pro = booking.getReferences().stream().filter(a -> a.getRef_type() == 0).findFirst().get().getReference();
		}

		Loadinfo loadinfo = new Loadinfo();
		loadinfo = fullLoad.getLoadinfo();
		loadinfo = new Loadinfo.Builder().setBillingID(loadinfo.getBillingID()).setBOLNumber(loadinfo.getBOLNumber())
				.setCustomerPO(loadinfo.getCustomerPO()).setIncoTermID(loadinfo.getIncoTermID())
				.setInvoiceID(loadinfo.getInvoiceID()).setLoadID(Integer.parseInt(loadID)).setManifestID(pro).build();

		fullLoad = new FullLoad_Request.Builder().setAuthenticationData(fullLoad.getAuthenticationData())
				.setLoadinfo(loadinfo).setBillTo(fullLoad.getBillTo()).setRateServices(fullLoad.getRateServices())
				.setProducts(fullLoad.getProducts()).setShipper(fullLoad.getShipper())
				.setConsignee(fullLoad.getConsignee()).setPackageInfo(fullLoad.getPackageInfo())
				.setShipperAccessorials(fullLoad.getShipperAccessorials())
				.setConsigneeAccessorials(fullLoad.getConsigneeAccessorials())
				.setLoadAccessorials(fullLoad.getLoadAccessorials()).setUserDefined(fullLoad.getUserDefined())
				.setReferenceField(fullLoad.getReferenceField()).build();

		return fullLoad;
	}
}
