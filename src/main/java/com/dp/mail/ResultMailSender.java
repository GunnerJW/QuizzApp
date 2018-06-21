package com.dp.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("resultMailSender")
public class ResultMailSender {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String to, String subject, String body) {

		/*SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		javaMailSender.send(mail);*/
		
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(to);
			helper.setText(body);
			helper.setSubject(subject);
			javaMailSender.send(message);
		}
		catch(Exception e) {
			e.printStackTrace();;
		}
	}
}
