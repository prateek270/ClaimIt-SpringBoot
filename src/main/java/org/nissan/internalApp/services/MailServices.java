package org.nissan.internalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage; 

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper; 

@Service
@Scope("prototype")
public class MailServices {

		@Autowired
		private JavaMailSender sender;
		private MimeMessage message;
		private MimeMessageHelper helper;
		

		public void sendMail(String recieverEmail, String body, String Subject) throws MessagingException {
			
			System.out.println(recieverEmail);
			message = sender.createMimeMessage();
			helper = new MimeMessageHelper(message, true);

			helper.setTo(recieverEmail); // email id of the reciever
			helper.setText(body); // Body
			helper.setSubject(Subject); // Subject

			sender.send(message);
			
		}
	} 
