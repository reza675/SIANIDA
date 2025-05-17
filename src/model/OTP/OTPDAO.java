/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.OTP;
import Model.DBConnection;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class OTPDAO {

    public void deleteByEmail(String email) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM otpverification WHERE email = ?")) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            
        }
    }

    public void insert(OTPVerification otp){
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
               "INSERT INTO otpverification (email, token, expiry) VALUES (?,?,?)")) {
            ps.setString(1, otp.getEmail());
            ps.setString(2, otp.getToken());
            ps.setTimestamp(3, otp.getExpiry());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            
        }
    }

    public boolean verify(String email, String token) {
        String sql = "SELECT * FROM otpverification WHERE email=? AND token=? AND expiry> ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, token);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

   
    
}
