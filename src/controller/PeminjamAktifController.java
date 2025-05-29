/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Model.User.UserDAO;
import Model.User.User;
import View.AksesAdmin.PeminjamAktifView;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M S I
 */
public class PeminjamAktifController {

    private UserDAO dao;
    private PeminjamAktifView view;
    private String username;

    public PeminjamAktifController(PeminjamAktifView view, String username) {
        this.dao = new UserDAO();
        this.view = view;
        this.username = username;
    }

    public void loadTableP() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblModel();
        tableModel.setRowCount(0);
        try {   
            int no = 1;
            List<User> list = dao.getPeminjamAktif();
            for (User u : list) {
                Object[] row = {
                    no++,
                    u.getNamaPengguna(),
                    u.getJumlahPeminjaman() // asumsi di User ada field banyakPeminjaman
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Gagal memuat data user aktif:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
