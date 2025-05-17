/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.OTP;
import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class OTPVerification {
    private String email;
    private String token;
    private Timestamp expiry;
    
    public OTPVerification() {}
    public OTPVerification(String email, String token, Timestamp expiry) {
        this.email = email;
        this.token = token;
        this.expiry = expiry;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiry() {
        return expiry;
    }
    public void setExpiry(Timestamp expiry) {
        this.expiry = expiry;
    }
}
