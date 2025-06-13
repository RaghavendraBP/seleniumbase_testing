package com.bmw.sch.email;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SMTPNotificationEmailService {

	public static void sendMail(String fileContent,String fileName) throws MessagingException {

		//	  public void sendMail(byte[] fileContent, String fileName) {

		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.host", "mail.bmwgroup.net");
		mailProps.put("mail.smtp.port", 587);
		mailProps.put("mail.from", "regnext-emailcomms.App-119693@bmw.co.uk");
		mailProps.put("mail.smtp.timeout", 60000);
		mailProps.put("mail.smtp.connectiontimeout", 60000);
		mailProps.put("mail.smtp.auth", true);
		mailProps.put("mail.smtp.auth.protocol", "ssl");
		mailProps.put("mail.smtp.starttls.enable", true);
		mailProps.put("mail.smtp.ssl.trust", 587);
		mailProps.put("mail.smtp.starttls.required", true);
		mailProps.put("mail.smtp.ssl.protocol", "TLSv1.2");

		String qqpassword = "yd6lWbtnpbxedc_";
		String qqaccount = "qqrgni2@europe.bmw.corp";

		Session session = Session.getInstance(mailProps, new Authenticator() {
			@Override

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(qqaccount,qqpassword);
			}

		});

		String recipients = "sangeetha.s@partner.bmw.co.uk,jeevan.jagadeesh@partner.bmw.co.uk";

		MimeMessage message = new MimeMessage(session);
		message.setSubject("Test Automation Execution Result", "UTF-8");
		message.setText("AutomationResults");
		Arrays.asList(recipients).forEach(r -> {
			//try {
			try {
				message.addRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(r));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		String mimetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		MimeMultipart msgBody = new MimeMultipart("alternative");

		MimeBodyPart wrap = new MimeBodyPart();

		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(fileContent, "text/html; charset=UTF-8");

		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(fileName, "text/html; charset=UTF-8");
		
		msgBody.addBodyPart(textPart);
		msgBody.addBodyPart(htmlPart);

		wrap.setContent(msgBody);
		
		
		
		try {
			htmlPart.attachFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MimeMultipart msg = new MimeMultipart("mixed");

		message.setContent(msg);
		msg.addBodyPart(wrap);

		MimeBodyPart att = new MimeBodyPart();

		//********************
		DataSource source = new FileDataSource(fileName);
		att.setDataHandler(new DataHandler(source));
	
		att.setFileName(fileName);

		//************

		Transport.send(message);
	}
}
