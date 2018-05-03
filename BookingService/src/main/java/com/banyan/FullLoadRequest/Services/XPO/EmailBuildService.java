package com.banyan.FullLoadRequest.Services.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Pickup.XPO.Email;

@Service
public class EmailBuildService {

	@Autowired
	Email email;

	public Email buildEmail(String mail) {
		
		if (mail == null)
			email = new Email("ynemanii@nexterus.com");
		else
			email = new Email(mail);
		return email;
	}
}
