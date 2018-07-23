package com.banyan.FullLoadRequest.Services.Booking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailVerification {

	public boolean isValidEmail(String email) {

		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher match = pattern.matcher(email);
		return match.matches();
	}
}
