/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User.User;
import Model.User.UserDAO;
import View.AkunUserView;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class AkunUserController {

    private AkunUserView view;
    private UserDAO userDAO;
    private String username;
    private User currentUser;

    public AkunUserController(AkunUserView view, String username) {
        this.view = view;
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
        // baca nilai terbaru dari form
        String passBaru  = view.getTextPassword().getText();
        String emailBaru = view.getTextEmail().getText();
        String telpBaru  = view.getTextNomorTelepon().getText();

        // set ke objek model
        currentUser.setPasswordPengguna(passBaru);
        currentUser.setEmail(emailBaru);
        currentUser.setNomorTelepon(telpBaru);

        // simpan ke database
        boolean sukses = userDAO.updateUser(currentUser);
        if (sukses) {
            JOptionPane.showMessageDialog(view, "Data berhasil diperbarui");
        } else {
            JOptionPane.showMessageDialog(view, "Gagal memperbarui data");
        }
    }
}