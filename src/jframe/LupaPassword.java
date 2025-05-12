/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;
import java.sql.*;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author ASUS
 */
public class LupaPassword {
    // Konfigurasi email pengirim
    private static final String FROM_EMAIL = "sianidadev@gmail.com";
    private static final String EMAIL_PASSWORD = "qyxjcrxzeyegqnht";
    
    public static void sendResetOTP(String email) {
        if (!isEmailRegistered(email)) {
            JOptionPane.showMessageDialog(null, 
                "Email tidak terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Generate OTP
        String otp = generateOTP();
        Timestamp expiry = new Timestamp(System.currentTimeMillis() + 300000);
        
        try (Connection con = DBConnection.getConnection()) {
            //Hapus OTP lama 
            String deleteSql = "DELETE FROM otpverification WHERE email = ?";
            PreparedStatement deletePs = con.prepareStatement(deleteSql);
            deletePs.setString(1, email);
            deletePs.executeUpdate();
            
            //Simpan OTP baru
            String insertSql = "INSERT INTO otpverification (email, token, expiry) VALUES (?, ?, ?)";
            PreparedStatement insertPs = con.prepareStatement(insertSql);
            insertPs.setString(1, email);
            insertPs.setString(2, otp);
            insertPs.setTimestamp(3, expiry);
            insertPs.executeUpdate();
            
            sendOTPEmail(email, otp);
            verifyOTP(email);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Gagal mengirim OTP!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static boolean isEmailRegistered(String email) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT emailPengguna FROM users WHERE emailPengguna = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private static String generateOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }
    
    private static void sendOTPEmail(String toEmail, String otp) throws MessagingException {
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
                return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
            }
        });
        
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(FROM_EMAIL, "SIANIDA"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LupaPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        msg.setSubject("Kode OTP Reset Password");
        msg.setText("Kode OTP Anda: " + otp + "\nBerlaku selama 5 menit.");
        Transport.send(msg);
    }
    
    private static void verifyOTP(String email) {
        //Input OTP
        String userOTP = JOptionPane.showInputDialog(
            null, 
            "Masukkan 6 digit OTP yang dikirim ke email Anda:", 
            "Verifikasi OTP", 
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (userOTP == null || userOTP.isEmpty()) {
            return;
        }
        
        // Verifikasi OTP
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM otpverification WHERE email = ? AND token = ? AND expiry > ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, userOTP);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                // OTP valid, tampilkan form reset password
                resetPassword(email);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "OTP tidak valid atau telah kadaluarsa!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Gagal verifikasi OTP!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void resetPassword(String email) {
        // Dialog input password baru
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmField = new JPasswordField();
        
        Object[] message = {
            "Password Baru:", passwordField,
            "Konfirmasi Password:", confirmField
        };
        
        int option = JOptionPane.showConfirmDialog(
            null, 
            message, 
            "Reset Password", 
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (option == JOptionPane.OK_OPTION) {
            String newPassword = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmField.getPassword());
            
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, 
                    "Password tidak cocok!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Update password di database (sebaiknya di-hash)
            try (Connection con = DBConnection.getConnection()) {
                String sql = "UPDATE users SET passwordPengguna = ? WHERE emailPengguna = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, newPassword); // Dalam produksi, gunakan hash password
                ps.setString(2, email);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Password berhasil direset!");
                
                // Hapus OTP yang sudah digunakan
                String deleteSql = "DELETE FROM otpverification WHERE email = ?";
                PreparedStatement deletePs = con.prepareStatement(deleteSql);
                deletePs.setString(1, email);
                deletePs.executeUpdate();
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Gagal reset password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

