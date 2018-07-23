package com.banyan.FullLoadRequest.Services.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Services.Booking.EmailVerification;
import com.banyan.FullLoadRequest.models.Pickup.XPO.Email;

@Service
public class EmailBuildService {

	@Autowired
	Email email;
	@Autowired
	EmailVerification verifyEmailService;

	public Email buildEmail(String mail, boolean test) {

		if (test == true)
			return new Email("ynemanii@nexterus.com");

		if (mail != null) {
			if (verifyEmailService.isValidEmail(mail))
				email = new Email(mail);
			else
				email = new Email("ynemanii@nexterus.com");
		} else
			email = new Email("ynemanii@nexterus.com");
		return email;
	}
}
