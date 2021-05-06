package com.cantina.utility;


import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cantina.model.Order;
import com.cantina.model.User;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;
	
	@Autowired
	private TemplateEngine templateEngine;

	
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


	/*public MimeMessage constructOrderConfirmationEmail(User user, Order order, Locale english) {
		
	//instead of using the simplemailmessage that we can not write content in
	//we instead use the mimemessagepreparator to have the rich type of content we want
	//we are gonna have a template based on thymeleaf and we are gonna use
	Context context = new Context();
	context.setVariable("order", order);
	context.setVariable("user", user);
	context.setVariable("cartItemList", order.getCartItemList());
	String text = templateEngine.process("orderConfirmationEmailTemplate", context);

	MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
	
		@Override
		public void prepare(MimeMessage mimeMessage) throws Exception {
			MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
			email.setTo(user.getEmail());
			email.setSubject("Order Confirmation - " + order.getId());
			email.setText(text, true);
			email.setFrom(new InternetAddress("borzamircea1970@gmail.com"));
		}
	};
	
	return messagePreparator;
	}*/
	
	
}
