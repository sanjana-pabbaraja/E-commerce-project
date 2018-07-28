package com.amazon.market.controller;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailMail
{
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String dear, String subject,String content,String email) {
		System.out.println("sendMail try");
		SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
		message.setSubject(subject);
		message.setTo(email);
		//message.set
		message.setText(String.format(
				simpleMailMessage.getText(), dear, content));

		mailSender.send(message);
		
	}

	public void sendBulkMail(String dear, String subject,int content,String[] email) throws MessagingException {
		
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "utf-8");
		String htmlMsg = "This is to inform you that the seller you are following is just added a product. <br/>Please have a look by visiting our site <a href='#'>Marche</a>";
		mimeMessage.setContent(htmlMsg, "text/html");
		message.setSubject(subject);
		message.setTo(email[0]);
		message.setBcc(email);

		mailSender.send(mimeMessage);
		
		
		
				
				
			}
	
	
}
