/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
/**
 *
 * @author ASUS
 */
public class EmailUtil {

    public static void sendOTPEmail(String fromEmail, String emailPassword, String toEmail, String otp)
            throws MessagingException {
        // Konfigurasi SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail, "SIANIDA"));
        } catch (UnsupportedEncodingException e) {
            message.setFrom(new InternetAddress(fromEmail));
        }
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject("Kode OTP Reset Password");
        message.setText("Kode OTP Anda adalah: " + otp + "\n\nBerlaku selama 5 menit.");

        Transport.send(message);
    }
}
