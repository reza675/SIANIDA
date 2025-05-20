/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User.User;
import Model.User.UserDAO;
import View.HomePageAdminView;
import View.HomePageUserView;
import View.LoginPageView;
import View.LupaPasswordPageView;
import View.RegisterPageView;
import javax.swing.JOptionPane;

public class LoginController {

    private final LoginPageView view;
    private final UserDAO userDAO;

    public LoginController(LoginPageView view, UserDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;
    }

    public boolean validateLogin() {
        if (view.getUsername().isEmpty() || view.getPassword().isEmpty()) {
            view.showWarningMessage("Semua field harus diisi!", "Data Belum Lengkap");
            return false;
        }
        return true;
    }

    public void doLogin() {
        if (!validateLogin()) {
            return;
        }
        User u = userDAO.verifikasiUsers(view.getUsername(), view.getPassword());
        if (u == null) {
            view.showErrorMessage("Username atau Password salah!");
        } else {
            if (u.getIdRole() == 1) {
                new HomePageAdminView().setVisible(true);
            } else if (u.getIdRole() == 2) {
                new HomePageUserView(u.getNamaPengguna()).setVisible(true);
            }
            view.dispose();
        }
    }

    public void doReset() {
        if (view.showConfirmDialog("Yakin ingin menghapus input?", "Konfirmasi")
                == JOptionPane.YES_OPTION) {
            view.clearFields();
        }
    }

    public void doForgotPassword() {
        String email = view.showInputDialog("Masukkan email terdaftar:", "Lupa Password");
        if (email == null){
            return;
        }
        else if (email.trim().isEmpty()) {
            view.showErrorMessage("Email tidak boleh kosong!");
            return;
        }
        if (!userDAO.checkEmailExists(email)) {
            view.showErrorMessage("Email tidak terdaftar!");
            return;
        }
        LupaPasswordPageView lupaPage = new LupaPasswordPageView(email);
        LupaPasswordController ctrl = new LupaPasswordController(lupaPage,userDAO);
        if (ctrl.sendOTP(email)) {
            view.dispose();
        }
    }

    public void doRegister() {
        RegisterPageView registerView = new RegisterPageView();
        new RegisterController(registerView, userDAO);
        registerView.setVisible(true);
        view.dispose();
    }

}
