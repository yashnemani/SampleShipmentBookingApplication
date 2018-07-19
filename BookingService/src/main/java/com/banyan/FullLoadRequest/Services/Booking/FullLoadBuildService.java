package com.banyan.FullLoadRequest.Services.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQt;
import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.Entities.RateQtDetail;
import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Repos.RateQtDetailRepository;
import com.banyan.FullLoadRequest.Repos.RateQtRepository;
import com.banyan.FullLoadRequest.Services.Banyan.ImportBuildService;
import com.banyan.FullLoadRequest.models.Booking.AuthenticationData;
import com.banyan.FullLoadRequest.models.Booking.BanyanAccessorials;
import com.banyan.FullLoadRequest.models.Booking.BillTo;
import com.banyan.FullLoadRequest.models.Booking.Consignee;
import com.banyan.FullLoadRequest.models.Booking.ConsigneeAccessorials;
import com.banyan.FullLoadRequest.models.Booking.Contact;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Booking.InsuranceInfo;
import com.banyan.FullLoadRequest.models.Booking.LoadAccessorials;
import com.banyan.FullLoadRequest.models.Booking.Loadinfo;
import com.banyan.FullLoadRequest.models.Booking.PackageInfo;
import com.banyan.FullLoadRequest.models.Booking.Product;
import com.banyan.FullLoadRequest.models.Booking.RateServices;
import com.banyan.FullLoadRequest.models.Booking.ReferenceField;
import com.banyan.FullLoadRequest.models.Booking.Shipper;
import com.banyan.FullLoadRequest.models.Booking.ShipperAccessorials;
import com.banyan.FullLoadRequest.models.Booking.UserDefined;

@Service
public class FullLoadBuildService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(FullLoadBuildService.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	
	// Components
	@Autowired
	ShipperAccessorials shipperAccessorials;
	@Autowired
	ConsigneeAccessorials consigneeAccessorials;
	@Autowired
	LoadAccessorials loadAccessorials;
	@Autowired
	ConsigneeAccessorials consigneeAcc;
	@Autowired
	LoadAccessorials loadAcc;
	@Autowired
	ShipperAccessorials shipAcc;
	@Autowired
	BanyanAccessorials banAcc;
	@Autowired
	Contact contact;
	@Autowired
	Shipper shipper;
	@Autowired
	Consignee consignee;
	@Autowired
	BillTo bill;
	@Autowired
	RateServices rateService;
	@Autowired
	Product product;
	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	Loadinfo load;
	@Autowired
	InsuranceInfo insuranceInfo;

	// Services
	@Autowired
	AccessorialBuildService accService;
	@Autowired
	AccessorialBuildService banyanAccService;
	@Autowired
	ShipperBuildService shipperService;
	@Autowired
	ConsigneeBuildService consigneeService;
	@Autowired
	BillToBuildService billService;
	@Autowired
	RateServicesBuildService rateBuildService;
	@Autowired
	ProductBuildService productService;
	@Autowired
	FullLoadBuildService fullLoadService;
	@Autowired
	ImportBuildService importService;
	@Autowired
	LoadinfoBuilderService loadService;

	// Repositories
	@Autowired
	RateQtDetailRepository detailRep;
	@Autowired
	RateQtRepository qtRep;
	@Autowired
	RateQtAddressRepository addressRep;

	public FullLoad_Request buildFullLoad(int id) {

		Optional<RateQt> qt;

		if (qtRep.existsById(id))
			qt = qtRep.findById(id);
		else
			return null;

		RateQt qte = qt.get();

		List<RateQtAddress> addresses = new ArrayList<>();
		RateQtAddress rtQtAdd = new RateQtAddress();
		addresses = addressRep.FindAllByRtQteId(qte.getId());
		if (addresses.isEmpty()) {
			nxtLogger.error("Rate Quote Address does not exist for "+id);
			return null;
		}
		rtQtAdd = addresses.get(0);

		List<RateQtDetail> details = new ArrayList<>();
		RateQtDetail detail = new RateQtDetail();
		details = detailRep.findByRtQteAddId(rtQtAdd.getId());
		if (details.isEmpty()) {
			nxtLogger.error("Rate Quote Detail does not exist for "+id);
			return null;
		}

		detail = details.get(0);

		shipper = shipperService.buildShipper(rtQtAdd);
		consignee = consigneeService.buildConsignee(rtQtAdd);

		bill = billService.buildBillTo(rtQtAdd, qte.getSiteLocNumber());

		banAcc = accService.buildBanayanAccessorials(rtQtAdd.getId());

		rateService = rateBuildService.buildRateService(rtQtAdd, detail);
		List<RateServices> rateServices = new ArrayList<>();
		rateServices.add(rateService);

		load = loadService.buildLoad(rtQtAdd);

		
		List<Product> products = new ArrayList<>();
		products = productService.buildProduct(details);

		AuthenticationData authData = new AuthenticationData();
		Integer clientCode = qte.getSiteClientCode();
		Integer locNumber = qte.getSiteLocNumber();
		String clientRefNum  = null;
		
		if (locNumber != null && clientCode != null)
			clientRefNum = clientCode.toString() + "-" + locNumber.toString();
		else if (clientCode != null)
			clientRefNum = clientCode.toString();
		else
			log.info("ClientRefNum cannot be assigned for ..... " + clientCode + "  " + locNumber);
		
		authData.setClientRefNum(clientRefNum);
		PackageInfo packageInfo = new PackageInfo(null, null);

		List<UserDefined> userdefs = new ArrayList<>();
		List<ReferenceField> refs = new ArrayList<>();

		fullLoad = new FullLoad_Request.Builder().setAuthenticationData(authData).setLoadinfo(load).setBillTo(bill)
				.setRateServices(rateServices).setProducts(products).setShipper(shipper).setConsignee(consignee)
				.setPackageInfo(packageInfo).setShipperAccessorials(banAcc.getShip())
				.setConsigneeAccessorials(banAcc.getConsignee()).setLoadAccessorials(banAcc.getLoad())
				.setUserDefined(userdefs).setReferenceField(refs).build();

		return fullLoad;
	}
}
