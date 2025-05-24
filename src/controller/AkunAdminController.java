/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User.User;
import Model.User.UserDAO;
import View.AksesAdmin.AkunAdminView;
import View.AksesAdmin.HomePageAdminView;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class AkunAdminController {

    private AkunAdminView view;
    private UserDAO userDAO;
    private String username;
    private User currentUser;

    public AkunAdminController(AkunAdminView view, String username) {
        this.view = view;
        this.username = username;
        userDAO = new UserDAO();
        currentUser = userDAO.getUserByUsername(username);
        if (currentUser != null) {
            view.getTextUsername().setText(currentUser.getNamaPengguna());
            view.getTextPassword().setText(currentUser.getPasswordPengguna());
            view.getTextEmail().setText(currentUser.getEmail());
            view.getTextNomorTelepon().setText(currentUser.getnomorTelepon());
        } else {
            JOptionPane.showMessageDialog(view, "User tidak ditemukan");
        }
    }

    public void onUpdate() {
        currentUser.setNamaPengguna(view.getTextUsername().getText());
        currentUser.setPasswordPengguna(String.valueOf(view.getTextPassword().getPassword()));
        currentUser.setEmail(view.getTextEmail().getText());
        currentUser.setNomorTelepon(view.getTextNomorTelepon().getText());

        boolean sukses = userDAO.updateAdmin(currentUser, username);
        if (sukses) {
            JOptionPane.showMessageDialog(view, "Data berhasil diperbarui.");
            String usernameBaru = currentUser.getNamaPengguna();
            HomePageAdminView homepage = new HomePageAdminView(usernameBaru);
            homepage.setVisible(true);
            view.dispose();

        } else {
            JOptionPane.showMessageDialog(view, "Gagal memperbarui data.");

        }
    }
}
