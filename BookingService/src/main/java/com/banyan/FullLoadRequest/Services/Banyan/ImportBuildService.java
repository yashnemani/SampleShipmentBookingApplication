package com.banyan.FullLoadRequest.Services.Banyan;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Repos.RateQtRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookingBuilderService;
import com.banyan.FullLoadRequest.models.Booking.AuthenticationData;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Booking.ImportForBook_Request;
import com.banyan.FullLoadRequest.models.Booking.QuoteInformation;
import com.banyan.FullLoadRequest.models.enums.CurrencyTypes;

@Service
public class ImportBuildService {

	@Autowired
	QuoteInformation quoteInfo;
	@Autowired
	RateQtRepository qtRep;
	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	Booking books;
	@Autowired
	AuthenticationData authData;
	@Autowired
	RateQtAddressRepository addRepo;
	@Autowired
	BookingBuilderService bookService;

	private BigDecimal total = new BigDecimal(15);
	private Timestamp pkupDate = new Timestamp(System.currentTimeMillis());

	// ImportBookRequest without FullLoad
	public ImportForBook_Request buildImport(int id, boolean dispatch) {

		Booking books = bookService.getBooking(id);
		if (books == null) {
			System.out
					.println("ImportBook Request cannot be generated, Booking for id " + id + " does not exist in DB!");
			return null;
		}

		fullLoad = bookService.getFullLoad(books);
		if (fullLoad == null) {
			System.out.println("ImportBook Request cannot be generated, Booking for id " + id
					+ " does not have required details!");
			Logger.error("ImportBook Request cannot be generated for " + id + " does not have required details!");
			return null;
		}

		authData = fullLoad.getAuthenticationData();
		String SCAC = books.getCARRIER_CODE();
		if (SCAC != null)
			if (SCAC.equals("RNLO"))
				SCAC = "RLCA";
		String carrierName = addRepo.findCarrierNameByCode(SCAC);
		String pro = fullLoad.getLoadinfo().getManifestID();
		String bol = fullLoad.getLoadinfo().getBOLNumber();
		if (bol != null)
			bol = bol.replaceAll("-", "");

		List<RateQtAddress> addresses = addRepo.FindAllByRtQteId(id);
		RateQtAddress address = addresses.get(0);
		pkupDate = address.getDtProjectedPickup();
		String PkUpDate = null;
		if (pkupDate != null)
			PkUpDate = pkupDate.toString();

		DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
		LocalDateTime pkupTime = null;

		if (PkUpDate != null) {
			System.out.println("PkUpDate Timestamp " + PkUpDate);
			if (!pkupDate.after(current))
				PkUpDate = futureDay.toString();
		} else
			PkUpDate = futureDay.toString();

		try {
			pkupDate = new java.sql.Timestamp((dateTimeFormatter.parse(PkUpDate).getTime()));
			pkupTime = pkupDate.toLocalDateTime();
			PkUpDate = pkupTime.toString();
		} catch (ParseException e) {
			Logger.error("Date formatter Exception " + e.getMessage());
			e.printStackTrace();
		}
		CurrencyTypes currency = CurrencyTypes.US_Dollar;

		quoteInfo = new QuoteInformation.Builder().setQuoteID(0).setSCAC(SCAC).setTransitTime(0).setTotalCharge(total)
				.setFreightCharge(null).setFuelSurcharge(null).setDiscountPercentage(null).setDiscountAmount(null)
				.setAccessorialFees(null).setMinimum(null).setGrossCharge(null).setOtherCharges(null).setTariff(null)
				.setInterline(false).setMiles(0).setQuoteNumber(null).setCarrierPrice(null).setCustomerPrice(null)
				.setNote(null).setCurrencyID(currency.getValue()).build();

		ImportForBook_Request importBook = new ImportForBook_Request.Builder().setAuthenticationData(authData)
				.setQuoteInformation(quoteInfo).setPickupDateTime(PkUpDate).setBOLNumber(bol).setDispatchLoad(dispatch)
				.setDispatchOverride(dispatch).setSubmitPickup(dispatch).setProNumber(pro)
				.setActualCarrierName(carrierName).setBillTo(fullLoad.getBillTo()).setLoadinfo(fullLoad.getLoadinfo())
				.setRateServices(fullLoad.getRateServices()).setProducts(fullLoad.getProducts())
				.setPackageInfo(fullLoad.getPackageInfo()).setShipper(fullLoad.getShipper())
				.setConsignee(fullLoad.getConsignee()).setShipperAccessorials(fullLoad.getShipperAccessorials())
				.setConsigneeAccessorials(fullLoad.getConsigneeAccessorials())
				.setLoadAccessorials(fullLoad.getLoadAccessorials()).setUserDefined(fullLoad.getUserDefined())
				.setReferenceField(fullLoad.getReferenceField()).build();

		return importBook;
	}

	public Timestamp getPkupDate() {
		return pkupDate;
	}
}
