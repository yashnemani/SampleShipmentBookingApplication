package com.banyan.FullLoadRequest.Services.Booking;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.BookingCurrentStatus;
import com.banyan.FullLoadRequest.Entities.BookingReferences;
import com.banyan.FullLoadRequest.Entities.BookingStatus;
import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.Repos.BookingReferencesRepository;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Repos.RateQtRepository;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Booking.Loadinfo;

@Service
public class BookingBuilderService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(BookingBuilderService.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	Booking book;
	@Autowired
	Loadinfo load;

	// Repos
	@Autowired
	BookingRepository bookRepo;
	@Autowired
	BookingReferencesRepository bookRefRepo;
	@Autowired
	RateQtRepository qtRep;
	@Autowired
	RateQtAddressRepository addressRep;
	@Autowired
	BookRefSaveService refSaveService;

	Integer rtQtId;

	// Generate and save Booking from FullLoad_Request Object
	public Booking buildBooking(FullLoad_Request fullLoad1, int id) {

		Booking book = new Booking();
		fullLoad = fullLoad1;
		boolean bool = bookRepo.existsById(id);
		// Update Booking if it already exists
		if (bool == true) {
			log.info("The Booking with this id " + id + " already exists in the DB");
			Optional<Booking> book1 = bookRepo.findById(id);
			book = book1.get();
			book.setUpdate(true);
		}
		// Else Insert Booking
		else {
			book.setBooking_id(id);
		}

		// Save FullLoad Object as BLOB
		try {
			book.setFullLoad(fullLoad1);
		} catch (SQLException | IOException e) {
			nxtLogger.error(e.getMessage());
		}

		// Save Booking Carrier Code
		String scac = getCarrierCode(id);
		if (scac == null) {
			nxtLogger.error("Rate Quote with ID " + id + " does not have a Carrier Code!");
			return book;
		}
		book.setCARRIER_CODE(scac);

		if (fullLoad.getRateServices().get(0).getServiceCode().equals("Volume"))
			book.setPROVIDER_ID(3);
		else {
			// Set Booking ProviderID based on SCAC code
			if (scac.equals("UPGF"))
				book.setPROVIDER_ID(2);
			else if (scac.equals("CNWY"))
				book.setPROVIDER_ID(1);
			else
				book.setPROVIDER_ID(0);
		}

		// Generate and Save Booking Reference Numbers
		Set<BookingReferences> bookRefs = new HashSet<>();
		bookRefs = buildBookingRefs(book, fullLoad1);
		book.setReferences(bookRefs);

		if (book.getCurrentStatus() == null) {
			// Set Booking Status and Current Status
			Set<BookingStatus> statuses = new HashSet<>();
			BookingStatus bookingStatus = new BookingStatus();
			bookingStatus.setStatus("XB");
			bookingStatus.setLocation(fullLoad1.getShipper().getLocationName());
			bookingStatus.setMessage("Shipment Booked in Nexterus System");
			bookingStatus.setDate(new Timestamp(System.currentTimeMillis()));
			bookingStatus.setBooking(book);
			statuses.add(bookingStatus);

			BookingCurrentStatus currentStatus = new BookingCurrentStatus();
			currentStatus.setBooking(book);
			currentStatus.setLocation(bookingStatus.getLocation());
			currentStatus.setMessage(bookingStatus.getMessage());
			currentStatus.setStatus(bookingStatus);
			currentStatus.setShipStatus(bookingStatus.getStatus());
			currentStatus.setShipState("AP");
			currentStatus.setDate(bookingStatus.getDate());
			currentStatus.setLastUpdatedDt();

			book.setStatuses(statuses);
			book.setCurrentStatus(currentStatus);
		} else
			log.info("Booking already has a current status? " + book.getCurrentStatus().getShipStatus());

		try {
			bookRepo.save(book);
			bookRepo.refresh(book);
		} catch (RuntimeException ex) {
			nxtLogger.error(ex.getMessage()+" "+id);
			return null;
		}

		if (book.isNew()) {
			try {
				if (book.getPROVIDER_ID() != 0)
					bookRepo.saveToTrackingQueue(id, book.getPROVIDER_ID());
			} catch (RuntimeException ex) {
				nxtLogger.error(ex.getMessage()+" "+id);
			}
		}
		return book;
	}

	// Generate/Update List of BookingReferences from Loadinfo
	public Set<BookingReferences> buildBookingRefs(Booking book, FullLoad_Request fullLoad1) {

		Set<BookingReferences> bookRefs = new HashSet<>();
		load = fullLoad1.getLoadinfo();

		if (load.getManifestID() != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, load.getManifestID(), 0);
			bookRefs.add(bookRef1);
		}

		if (load.getBOLNumber() != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, load.getBOLNumber(), 1);
			bookRefs.add(bookRef1);
		}

		if (load.getCustomerPO() != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, load.getCustomerPO(), 2);
			bookRefs.add(bookRef1);
		}

		if (load.getLoadID() != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, load.getLoadID().toString(), 3);
			bookRefs.add(bookRef1);
		}

		if (load.getInvoiceID() != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, load.getInvoiceID(), 5);
			bookRefs.add(bookRef1);
		}

		if (load.getBillingID() != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, load.getBillingID(), 6);
			bookRefs.add(bookRef1);
		}

		if (load.getIncoTermID() != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, load.getIncoTermID().toString(), 7);
			bookRefs.add(bookRef1);
		}

		log.info("Reference Types: ");
		bookRefs.forEach(a -> log.info(" " + a.getRef_type() + ","));
		return bookRefs;
	}

	// Retrieve Booking Entity with given id from DB
	public Booking getBooking(int id) {

		Optional<Booking> book1 = bookRepo.findById(id);
		if (book1.isPresent()) {
			log.info(id + " Present");
			Booking books = book1.get();
			return books;
		}
		return null;
	}

	// Retrieve Deserialized FullLoad Object from Booking Entity
	public FullLoad_Request getFullLoad(Booking books) {
		FullLoad_Request fullLoad1 = new FullLoad_Request();
		fullLoad1 = null;
		if (books.getFullLoad() == null)
			log.warn("The fullload BLOB for the given booking is null");
		else
			try {
				ByteArrayInputStream in = new ByteArrayInputStream(books.getFullLoad());
				ObjectInputStream his = new ObjectInputStream(in);
				fullLoad1 = (FullLoad_Request) his.readObject();
			} catch (ClassNotFoundException | IOException e) {
				nxtLogger.error("FullLoad Blob Error " + e.getMessage());
			}
		return fullLoad1;
	}

	// Get Carrier Code from Rate Address
	public String getCarrierCode(int id) {
		List<RateQtAddress> addresses = new ArrayList<>();
		addresses = addressRep.FindAllByRtQteId(id);
		RateQtAddress address = new RateQtAddress();
		if (addresses.isEmpty())
			return null;
		address = addresses.get(0);
		String scac = address.getCarrierCode();
		return scac;
	}
}
