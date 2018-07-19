package com.banyan.FullLoadRequest.controllers;

import java.util.Arrays;

import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Services.MailService;
import com.banyan.FullLoadRequest.Services.Banyan.ImportBuildService;
import com.banyan.FullLoadRequest.Services.Banyan.ImportResponseHandlerService;
import com.banyan.FullLoadRequest.Services.Booking.BookingBuilderService;
import com.banyan.FullLoadRequest.Services.Booking.BookingSchedulerService;
import com.banyan.FullLoadRequest.Services.Booking.FullLoadBuildService;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Booking.ImportForBook_Request;

@RestController
public class BookingController {

	// Components
	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	ImportForBook_Request importBook;
	@Autowired
	Booking book;

	// Services
	@Autowired
	FullLoadBuildService fullLoadService;
	@Autowired
	ImportBuildService importService;
	@Autowired
	BookingBuilderService bookService;
	@Autowired
	ImportResponseHandlerService importResponseHandler;
	@Autowired
	BookingSchedulerService bookingQueueService;
	@Autowired
	MailService mailService;

	// Generate FullLoadRequest Object from the given RateQuoteId
	@GetMapping("/getFullLoadRequest/{id}/{update}")
	public FullLoad_Request getFullLoad(@PathVariable Integer id, @PathVariable boolean update) {

		if (update == false) {
			Booking book = null;
			book = bookService.getBooking(id);
			if (book != null)
				return null;
		}

		fullLoad = fullLoadService.buildFullLoad(id);
		if (fullLoad == null) {
			System.out.println(id + " does not exist as a rate quote!");
			return null;
		}
		// Save FullLoadRequest as Booking in DB
		createBooking(id);
		return fullLoad;
	}

	// Generate ImportForBook_Request Object from the given RateQuoteId
	@GetMapping("/getImportBookRequest/{id}")
	public ImportForBook_Request getImportForBook(@PathVariable Integer id) {
		boolean autoDispatch = true;
		importBook = importService.buildImport(id, autoDispatch);
		return importBook;
	}

	// Generate Booking and BookingReferences Entities from RateQuoteId
	public Booking createBooking(Integer id) {
		book = bookService.buildBooking(fullLoad, id);
		return book;
	}

	// Retrieve Booking Entity with given ID from DB
	@GetMapping("/getBooking/{id}")
	public Object getBooking(@PathVariable Integer id) {

		Booking book = null;
		book = bookService.getBooking(id);
		if (book == null) {
			mailService.sendMail("Booking for ID " + id + " does not exist in DB! Try a valid BookingID");
			return "Booking for ID " + id + " does not exist in DB! Try a valid BookingID";}
		fullLoad = bookService.getFullLoad(book);
		return fullLoad;
	}

	// POST ImportBook Object to /ImportForBook Banyan API
	@PostMapping("/callBanyan/ImportForBook_Request/{id}")
	public Object callBanyan(@PathVariable Integer id) {

		importBook = getImportForBook(id);
		if (importBook == null)
			return "ImportBook is null for given ID " + id + ". Use a valid ID to call Banyan";
		System.out.println(importBook.getActualCarrierName());
		// Beta
		/*final String uri = "http://ws.beta.banyantechnology.com/services/api/rest/ImportForBook";*/
		// Production
		 final String uri = "https://ws.logistics.banyantechnology.com/services/api/rest/ImportForBook";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<ImportForBook_Request> entity = new HttpEntity<>(importBook, headers);
		ResponseEntity<Object> result = null;
		try {
			result = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
			importResponseHandler.handleResponseObject(result.getBody(), id, importService.getPkupDate());
		} catch (HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			Logger.error("Banyan ImportBook request Failed for ID " + id + " Error: " + e.getMessage());
			return e.getResponseBodyAsString();
		}

		return result.getBody();
	}
}
