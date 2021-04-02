package com.cantina.utility;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.cantina.model.User;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;

	
	public SimpleMailMessage constructResetPassword(
			String contextPath, Locale locale, User user, String password
			) {
		String url = contextPath + "/api/updateUserInformation";
		String message = "\nPlease click on this link to change your password. Your current password is: \n" + password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Cantina UPT - Forget Password");
		email.setText(url + message);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}
	
	
}
