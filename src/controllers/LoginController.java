/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.*;
import javax.swing.JOptionPane;
import models.User;
import models.UserDAO;
import views.HomePageAdminView;
import views.HomePageUserView;
import views.LoginPageView;
import views.LupaPasswordPageView;
import views.RegisterPageView;
//import views.HomePageView;
//import util.LupaPassword;

/**
 *
 * @author ASUS
 */
public class LoginController {

    private LoginPageView view;
    private UserDAO userDAO;

    public LoginController(LoginPageView view, UserDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;

        //login event listeners
        this.view.addLoginButtonListener(new LoginButtonListener());
        this.view.addResetButtonListener(new ResetButtonListener());
        this.view.addExitButtonListener(new ExitButtonListener());
        this.view.addForgotPasswordListener(new ForgotPasswordListener());
        this.view.addRegisterLinkListener(new RegisterLinkListener());
    }

    // validasi login
    private boolean validateLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            view.showWarningMessage("Semua field harus diisi!", "Data Belum Lengkap");
            return false;
        }
        return true;
    }

    // Action listeners as inner classes
    class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (validateLogin()) {
                String username = view.getUsername();
                String password = view.getPassword();

                User user = userDAO.verifikasiUsers(username, password);
                if (user != null) {
                    if (user.getIdRole() == 1) {
                        new HomePageAdminView().setVisible(true);
                    } else if (user.getIdRole() == 2) {
                        new HomePageUserView().setVisible(true);
                    }
                    view.dispose();
                } else {
                    view.showErrorMessage("Username atau Password salah!");
                }
            }
        }
    }

    class ResetButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = view.showConfirmDialog("Yakin ingin menghapus input?", "Konfirmasi");
            if (confirm == JOptionPane.YES_OPTION) {
                view.clearFields();
            }
        }
    }

    class ExitButtonListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }

    class ForgotPasswordListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            {
                String email = view.showInputDialog("Masukkan email terdaftar:", "Lupa Password");
                if (email == null) {
                    return;
                }
                if (email.trim().isEmpty()) {
                    view.showErrorMessage("Email tidak boleh kosong!");
                    return;
                }
                if (!userDAO.checkEmailExists(email)) {
                    view.showErrorMessage("Email tidak terdaftar!");
                    return;
                }
                LupaPasswordPageView lupaPage = new LupaPasswordPageView(email);
                LupaPasswordController ctrl = new LupaPasswordController(lupaPage);
                boolean ok = ctrl.sendOTP(email);
                if (ok) {
                    view.dispose();
                }

            }
        }
    }

    class RegisterLinkListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            RegisterPageView registerView = new RegisterPageView();
            UserDAO userDAO = new UserDAO(); // atau bisa reuse dari LoginController
            new RegisterController(registerView, userDAO); // ini menghubungkan UI dan logic
            registerView.setVisible(true);
            view.dispose();
        }
    }
}
