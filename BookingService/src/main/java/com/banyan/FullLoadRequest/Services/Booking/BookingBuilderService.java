package com.banyan.FullLoadRequest.Services.Booking;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.banyan.FullLoadRequest.config.HackedObjectInputStream;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Booking.Loadinfo;

@Service
public class BookingBuilderService {

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

	// Generate Booking Entity and Booking_References from FullLoad_Request Object
	// and persist in DB Schema
	public Booking buildBooking(FullLoad_Request fullLoad1, int id) {
		Booking book = new Booking();
		fullLoad = fullLoad1;
		boolean bool = bookRepo.existsById(id);
		// Update Booking if it already exists
		if (bool == true) {
			System.out.println("The Booking with this id " + id + " already exists in the DB");
			Optional<Booking> book1 = bookRepo.findById(id);
			book = book1.get();
			book.setUpdate(true);
		}
		// Else Insert Booking
		else {
			book.setBooking_id(id);
		}

		List<RateQtAddress> addresses = new ArrayList<>();
		addresses = addressRep.FindAllByRtQteId(id);
		RateQtAddress address = new RateQtAddress();
		if (addresses.isEmpty())
			return book;
		address = addresses.get(0);
		String scac = address.getCarrierCode();
		if (scac == null) {
			System.out.println("Rate Quote with ID " + id + " does not have a Carrier Code!");
			return book;
		}
		book.setCARRIER_CODE(scac);
		// Set Booking ProviderID based on SCAC code
		if (scac.equals("UPGF"))
			book.setPROVIDER_ID(2);
		else if (scac.equals("CNWY"))
			book.setPROVIDER_ID(1);
		else
			book.setPROVIDER_ID(0);

		try {
			book.setFullLoad(fullLoad);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		Set<BookingReferences> bookRefs = new HashSet<>();
		bookRefs = buildBookingRefs(book);
		book.setReferences(bookRefs);

		Set<BookingStatus> statuses = new HashSet<>();
		BookingStatus bookingStatus = new BookingStatus();
		bookingStatus.setStatus("XB");
		bookingStatus.setLocation(fullLoad.getShipper().getLocationName());
		bookingStatus.setMessage("Shipment Booked in Nexterus Sysytem");
		bookingStatus.setDate(new Timestamp(System.currentTimeMillis()));
		bookingStatus.setBooking(book);
		statuses.add(bookingStatus);

		BookingCurrentStatus currentStatus = new BookingCurrentStatus();
		if (book.getCurrentStatus() != null) {
			System.out.println("Update Current Status");
			currentStatus = book.getCurrentStatus();
		}
		currentStatus.setBooking(book);
		currentStatus.setLocation(bookingStatus.getLocation());
		currentStatus.setMessage(bookingStatus.getMessage());
		currentStatus.setStatus(bookingStatus);
		currentStatus.setShipStatus(bookingStatus.getStatus());
		currentStatus.setShipState("AP");
		currentStatus.setDate(bookingStatus.getDate());

		book.setStatuses(statuses);
		book.setCurrentStatus(currentStatus);

		try {
			bookRepo.save(book);
		} catch (RuntimeException ex) {
			System.err.println("Save Book " + ex.getCause().getMessage());
			System.err.println(ex);
		}

		try {
			bookRepo.saveToTrackingQueue(id, book.getPROVIDER_ID());
		} catch (RuntimeException ex) {
			System.err.println("Save Book " + ex.getCause().getMessage());
			System.err.println(ex);
		}

		try {
			bookRepo.refresh(book);
		} catch (RuntimeException ex) {
			System.err.println("Save Book " + ex.getCause().getMessage());
			System.err.println(ex);
		}

		return book;
	}

	// Generate/Update List of BookingReferences from Loadinfo
	public Set<BookingReferences> buildBookingRefs(Booking book) {

		Set<BookingReferences> bookRefs = new HashSet<>();
		load = fullLoad.getLoadinfo();

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

		System.out.println();
		System.out.print("Reference Types: ");
		bookRefs.forEach(a -> System.out.print(" " + a.getRef_type() + ","));
		return bookRefs;
	}

	// Retrieve Booking Entity with given id from DB
	public Booking getBooking(int id) {

		Optional<Booking> book1 = bookRepo.findById(id);
		if (book1.isPresent()) {
			System.out.println(id + " Present");
			Booking books = book1.get();
			return books;
		}
		return null;
	}

	// Retrieve Deserialized FullLoad Object from Booking Entity
	public FullLoad_Request getFullLoad(Booking books) {
		fullLoad = null;
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(books.getFullLoad());
			HackedObjectInputStream his = new HackedObjectInputStream(in);
			fullLoad = (FullLoad_Request) his.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Deserialize FullLoad " + e.getCause().getMessage());
		}
		return fullLoad;
	}
}
