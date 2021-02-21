/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pi.esprit.utils.MyConnection;
import sun.security.provider.VerificationProvider;
/**
 *
 * @author Fakhri Argoubi
 */
public class MailService {
     Connection cnx;
    
    public MailService(){
                cnx = MyConnection.getInstance().getCnx();

    }
    
    
    public  void sendVerifMail(String recepient,int id_user) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        //Your gmail address
        String myAccountEmail = "hamza.argoubi@esprit.tn";
        //Your gmail password
        String password = "191SMT2377";
        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        String code = codeGenerator(5).toString();
        //Prepare email message
        Message message=verifCodeMsg(session, myAccountEmail, recepient,code);
        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
        VerificationCodeCRUD vc = new VerificationCodeCRUD();
        vc.insertCode(code, id_user);
    }
    
    
    
    public void sendBanMail(String recepient,String banEndingDate) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        //Your gmail address
        String myAccountEmail = "hamza.argoubi@esprit.tn";
        //Your gmail password
        String password = "191SMT2377";
        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        //Prepare email message
        Message message=banMessage(session, myAccountEmail, recepient,banEndingDate);
        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
    
    private  Message verifCodeMsg(Session session, String myAccountEmail, String recepient,String code) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Verfication code");
            String htmlCode = "your code is:  "+code;
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());        }
        return null;
    }
    private  Message banMessage(Session session, String myAccountEmail, String recepient,String banEndingDate) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Ban");
            String htmlCode = "you are temporarely banned from Mawhebty for violating our term of service /n "
                    + "you'll be able to use your account on"+banEndingDate;
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());        }
        return null;
    }
    
    public  char[] codeGenerator(int length) 
    { 
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String numbers = "0123456789"; 
        String values = Capital_chars +numbers ; 
  
        // Using random method 
        Random rndm_method = new Random(); 
  
        char[] password = new char[length]; 
  
        for (int i = 0; i < length; i++) 
        { 
            password[i] = values.charAt(rndm_method.nextInt(values.length()));
        } 
        return password; 
    } 
}
