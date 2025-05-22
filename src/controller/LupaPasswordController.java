/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// controllers/LupaPasswordController.java
package Controller;

import Model.OTP.OTPDAO;
import Model.OTP.OTPVerification;
import Model.User.UserDAO;
import View.LoginRegister.LupaPasswordPageView;
import jakarta.mail.MessagingException;
import java.sql.Timestamp;
import java.util.Random;
import javax.swing.JOptionPane;
import Model.EmailUtil;
import View.LoginRegister.LoginPageView;

/**
 *
 * @author ASUS
 */
public class LupaPasswordController {

    private LupaPasswordPageView view;
    private OTPDAO otpDAO;
    private UserDAO userDAO;
    private static final String FROM_EMAIL = "sianidadev@gmail.com";
    private static final String EMAIL_PWD = "qyxjcrxzeyegqnht";

    public LupaPasswordController(LupaPasswordPageView view,UserDAO userDAO) {
        this.view = view;
        this.otpDAO = new OTPDAO();
        this.userDAO = new UserDAO();
    }

    public void handleSubmit() {
        String newPwd = view.getNewPassword();
        String conf = view.getConfirmPassword();
        String email = view.getEmail();

        if (newPwd.isEmpty() || conf.isEmpty()) {
            view.showError("Semua field harus diisi!");
            return;
        }
        if (!newPwd.equals(conf)) {
            view.showError("Password tidak cocok!");
            return;
        }
        try {
            if (userDAO.updatePassword(email, newPwd)) {
                view.showInfo("Password berhasil diperbarui!");
                LoginPageView loginView = new LoginPageView();
                loginView.setVisible(true);
                view.dispose();
            } else {
                view.showError("Gagal update password!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            view.showError("Kesalahan saat mengganti password.");
        }
    }

    public boolean sendOTP(String email) {
        try {
            // 1. Hapus & simpan token baru
            otpDAO.deleteByEmail(email);
            String token = String.format("%06d", new Random().nextInt(999999));
            Timestamp expiry = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);
            OTPVerification otp = new OTPVerification();
            otp.setEmail(email);
            otp.setToken(token);
            otp.setExpiry(expiry);
            otpDAO.insert(otp);

            // 2. Kirim email
            EmailUtil.sendOTPEmail(FROM_EMAIL, EMAIL_PWD, email, token);

            // 3. Verifikasi input user via dialog
            String userInput = JOptionPane.showInputDialog(
                    null, "Masukkan 6 digit OTP:", "Verifikasi OTP", JOptionPane.PLAIN_MESSAGE);
            if (userInput != null && otpDAO.verify(email, userInput)) {
                view.setVisible(true);
                return true;
            } else {
                JOptionPane.showMessageDialog(
                        null, "OTP salah atau kadaluarsa", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (MessagingException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null, "Gagal mengirim OTP!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
