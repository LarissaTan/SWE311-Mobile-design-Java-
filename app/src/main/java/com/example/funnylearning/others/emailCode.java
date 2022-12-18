package com.example.funnylearning.others;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class emailCode {

    public static void sendEmail(String userEmail, int code) throws MessagingException {

        Properties p = new Properties();                                    // creates a Properties object
        System.setProperty("java.net.preferIPv4Stack", "true");

        /*********** set the information ***********/
        p.setProperty("mail.smtp.host", "smtp.163.com");                    // the outgoing SMTP server is smtp.163.com
        p.setProperty("mail.smtp.port", "25");                              // SMTP Port is 25
        p.setProperty("mail.smtp.socketFactory.port", "25");
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");

        /** set host email and authorization code **/
        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("13712685569@163.com", "AOTSZKAYMJAYRMTE");
            }
        });
        session.setDebug(true);

        /********** set the mail content **********/
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("13712685569@163.com"));
        message.setRecipients(Message.RecipientType.TO,userEmail);
        message.setSubject("Validation Code(Funny Learning)");
        message.setContent("<h4>Welcome to register FUNNY LEARNING!</h4>" +
                "<p>Copy the code here to start your journey with us!</p>"+
                "<p>You validation code is </p>"+code,
                "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();

        Transport.send(message);                                            // send the email
    }
}



