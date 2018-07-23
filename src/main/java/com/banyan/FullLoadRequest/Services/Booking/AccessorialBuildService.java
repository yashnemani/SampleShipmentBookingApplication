package com.banyan.FullLoadRequest.Services.Booking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Repos.RateQtRepository;
import com.banyan.FullLoadRequest.models.Booking.BanyanAccessorials;
import com.banyan.FullLoadRequest.models.Booking.ConsigneeAccessorials;
import com.banyan.FullLoadRequest.models.Booking.LoadAccessorials;
import com.banyan.FullLoadRequest.models.Booking.ShipperAccessorials;

@Service
public class AccessorialBuildService {

	// Components
	@Autowired
	ConsigneeAccessorials consigneeAcc;
	@Autowired
	LoadAccessorials loadAcc;
	@Autowired
	ShipperAccessorials shipAcc;
	@Autowired
	BanyanAccessorials banAcc;

	// Repos
	@Autowired
	RateQtRepository qtRep;

	public BanyanAccessorials buildBanayanAccessorials(Integer addId) {

		// Find all FusionCenter Accessorial Charge Codes for the Rate Quote address ID
		List<String> acc_FC_codes = new ArrayList<>();
		acc_FC_codes = qtRep.findAllAccCodes(addId);

		// Find the Banyan charge codes for the retrieved FusionCenter Charge codes
		List<String> banyanAccCodes = new ArrayList<>();
		banyanAccCodes = qtRep.findBanyanAccCodes(acc_FC_codes);

		shipAcc = buildShipperAcc(banyanAccCodes);
		consigneeAcc = buildConsigneeAcc(banyanAccCodes);
		loadAcc = buildLoadAcc(banyanAccCodes);

		banAcc.setShip(shipAcc);
		banAcc.setConsignee(consigneeAcc);
		banAcc.setLoad(loadAcc);

		return banAcc;
	}

	// Build Banyan ShipperAccessorials Object
	public ShipperAccessorials buildShipperAcc(List<String> accCodes) {

		ShipperAccessorials ship = new ShipperAccessorials();

		if (accCodes.contains("SAPPT") || accCodes.contains("GAPT"))
			ship.setAppointmentRequired(true);

		if (accCodes.contains("SIPCK") || accCodes.contains("GINS"))
			ship.setInsidePickup(true);

		if (accCodes.contains("SSORT") || accCodes.contains("GSAS"))
			ship.setSortSegregate(true);

		if (accCodes.contains("SJACK") || accCodes.contains("GPAL"))
			ship.setPalletJack(true);

		if (accCodes.contains("SRPECK") || accCodes.contains("GRES"))
			ship.setResidentialPickup(true);

		if (accCodes.contains("SLFTG") || accCodes.contains("GLFT"))
			ship.setLiftgatePickup(true);

		if (accCodes.contains("GMTG") || accCodes.contains("SMARK"))
			ship.setMarkingTagging(true);

		if (accCodes.contains("LTPCK") || accCodes.contains("GTSH"))
			ship.setTradeShowPickup(true);

		if (accCodes.contains("SNYCM") || accCodes.contains("GNYC"))
			ship.setNYCMetro(true);

		if (accCodes.contains("SABH") || accCodes.contains("GABH"))
			ship.setNonBusinessHourPickup(true);

		if (accCodes.contains("GLAC") || accCodes.contains("SLAP"))
			ship.setLimitedAccessType("SLAP");

		return ship;
	}

	// Build Banyan LoadAccessorials Object
	public LoadAccessorials buildLoadAcc(List<String> accCodes) {

		LoadAccessorials load = new LoadAccessorials();

		if (accCodes.contains("LGUAR") || accCodes.contains("GGUA"))
			load.setGuaranteed(true);

		if (accCodes.contains("LTIME"))
			load.setTimeDefinite(true);

		if (accCodes.contains("LEXPD"))
			load.setExpedited(true);

		if (accCodes.contains("SHOR") || accCodes.contains("LHPCK") || accCodes.contains("GHOL"))
			load.setHolidayPickup(true);

		if (accCodes.contains("CHOL") || accCodes.contains("LHDEL") || accCodes.contains("GHOL"))
			load.setHolidayDelivery(true);

		if (accCodes.contains("LWDET") || accCodes.contains("WTVER"))
			load.setWeightDetermination(true);

		if (accCodes.contains("LBSHP"))
			load.setBlindShipment(true);

		if (accCodes.contains("BLKHD"))
			load.setBulkhead(true);

		if (accCodes.contains("LBSCH"))
			load.setBlanketServiceChilled(true);

		if (accCodes.contains("LBSRV") || accCodes.contains("LBSFZ"))
			load.setBlanketServiceFrozen(true);

		if (accCodes.contains("LBBBB") || accCodes.contains("LKKKK"))
			load.setBlanketService(true);

		if (accCodes.contains("LSING"))
			load.setSingleShipment(true);

		if (accCodes.contains("SIGRQ"))
			load.setSignatureRequired(true);

		if (accCodes.contains("SHIRE"))
			load.setShipperRelease(true);

		if (accCodes.contains("LSTAC"))
			load.setStackable(true);

		if (accCodes.contains("SATDE"))
			load.setSaturdayDelivery(true);

		if (accCodes.contains("SHLD"))
			load.setShipmentHold(true);

		if (accCodes.contains("SMAN"))
			load.setSecondMan(true);

		if (accCodes.contains("LBOND"))
			load.setCustomsInBond(true);

		if (accCodes.contains("RSEDE"))
			load.setRestrictedDelivery(true);

		if (accCodes.contains("RETRE"))
			load.setReturnReceipt(true);

		if (accCodes.contains("LODIM"))
			load.setOverDimension(true);

		if (accCodes.contains("LTSA"))
			load.setTSA(true);

		if (accCodes.contains("LTKEY"))
			load.setTurnkey(true);

		if (accCodes.contains("WGLV"))
			load.setWhiteGlove(true);

		if (accCodes.contains("PRORE"))
			load.setProactiveResponse(true);

		if (accCodes.contains("LFGP"))
			load.setFoodGradeProducts(true);

		return load;
	}

	// Build Banyan ConsigneeAccessorials Object
	public ConsigneeAccessorials buildConsigneeAcc(List<String> accCodes) {

		ConsigneeAccessorials consignee = new ConsigneeAccessorials();

		if (accCodes.contains("CAPPT") || accCodes.contains("GAPT"))
			consignee.setAppointmentRequired(true);

		if (accCodes.contains("GINS") || accCodes.contains("CIDEL"))
			consignee.setInsideDelivery(true);

		if (accCodes.contains("CSORT") || accCodes.contains("GSAS"))
			consignee.setSortSegregate(true);

		if (accCodes.contains("CJACK") || accCodes.contains("GPAL"))
			consignee.setPalletJack(true);

		if (accCodes.contains("CRDEL") || accCodes.contains("GRES"))
			consignee.setResidentialDelivery(true);

		if (accCodes.contains("CLFTG") || accCodes.contains("GLFT"))
			consignee.setLiftgateDelivery(true);

		if (accCodes.contains("GMTG") || accCodes.contains("CMARK"))
			consignee.setMarkingTagging(true);

		if (accCodes.contains("LTDEL") || accCodes.contains("GTSH"))
			consignee.setTradeShowDelivery(true);
		
		if (accCodes.contains("CNYCM") || accCodes.contains("GNYC"))
			consignee.setNYCMetro(true);

		if (accCodes.contains("CABH") || accCodes.contains("GABH"))
			consignee.setNonBusinessHourDelivery(true);

		if (accCodes.contains("GLAC") || accCodes.contains("SLAD"))
			consignee.setLimitedAccessType("SLAD");
		
		if (accCodes.contains("DELNO"))
			consignee.setDeliveryNotification(true);
		
		return consignee;
	}
}
