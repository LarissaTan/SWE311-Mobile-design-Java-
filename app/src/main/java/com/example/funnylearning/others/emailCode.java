package com.example.funnylearning.others;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class emailCode {
    public static final String MY_EMAIL_ACCOUNT = "13712685569@163.com";
    public static final String MY_EMAIL_PASSWORD = "AOTSZKAYMJAYRMTE";// 密码,是你自己的设置的授权码

    public static final String MEAIL_163_SMTP_HOST = "smtp.163.com";
    public static final String SMTP_163_PORT = "25";


    //public static final String RECEIVE_EMAIL_ACCOUNT = userEmail;

    public static void sendEmail(String userEmail, int code) throws MessagingException {
        Properties p = new Properties();
        System.setProperty("java.net.preferIPv4Stack", "true");
        p.setProperty("mail.smtp.host", MEAIL_163_SMTP_HOST);
        p.setProperty("mail.smtp.port", SMTP_163_PORT);
        p.setProperty("mail.smtp.socketFactory.port", SMTP_163_PORT);
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");

        Session session = Session.getInstance(p, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MY_EMAIL_ACCOUNT, MY_EMAIL_PASSWORD);
            }
        });
        session.setDebug(true);
        System.out.println("mail is created");
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MY_EMAIL_ACCOUNT));
        message.setRecipients(Message.RecipientType.TO,userEmail);

        // 内容
        message.setSubject("Validation Code(HEALTH)");
        message.setContent("<h4>Welcome to register FUNNY LEARNING!</h4>" +
                "<p>Copy the code here to start your journey with us!</p>"+"<p>You validation code is </p>"+code, "text/html;charset=UTF-8");

        message.setSentDate(new Date());
        message.saveChanges();
        System.out.println("准备发送");
        System.out.println("code:" + code);
        Transport.send(message);
    }
}



