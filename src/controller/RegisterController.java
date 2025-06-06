/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User.User;
import Model.User.UserDAO;
import View.LoginRegister.LoginPageView;
import View.LoginRegister.RegisterPageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ASUS
 */
public class RegisterController {

    private RegisterPageView view;
    private UserDAO userDAO;

    public RegisterController(RegisterPageView view, UserDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;
    }

    public void doRegister() {
        String username = view.getUsername();
        String password = view.getPassword();
        String email = view.getEmail();
        String phone = view.getPhone();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            view.showWarning("Semua field harus diisi!", "Data Belum Lengkap");
            return;
        }
        if (!email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            view.showWarning("Email harus valid dan berakhiran @gmail.com", "Format Email Salah");
            return;
        }
        if (!phone.matches("\\d{10,13}")) {
            view.showWarning("Nomor telepon harus 10–13 digit angka.", "Format Nomor Salah");
            return;
        }
        if (userDAO.checkDuplicate(username, email)) {
            view.showWarning("Username atau email sudah digunakan.", "Username Duplikat");
            return;
        }
        boolean success = userDAO.insertUser(new User(username, password, email, phone));
        if (success) {
            view.showInfo("Registrasi berhasil!");
            view.dispose();
            LoginPageView loginPage = new LoginPageView();
            loginPage.setVisible(true);
        } else {
            view.showError("Terjadi kesalahan saat menyimpan data.");
        }
    }
}
