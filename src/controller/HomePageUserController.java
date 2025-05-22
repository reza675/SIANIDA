/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Buku.Buku;
import Model.Buku.BukuDAO;
import View.AksesUser.HomePageUserView;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class HomePageUserController {

    private BukuDAO dao;
    private HomePageUserView view;
    private String username;

    public HomePageUserController(HomePageUserView view, String username) {
        this.dao = new BukuDAO();
        this.view = view;
        this.username = username;
    }

    public List<Buku> fetchAllBuku() {
        return dao.getAllBuku();
    }

    public void loadTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblModel();
        tableModel.setRowCount(0);
        List<Buku> list = dao.getAllBuku();
        for (Buku bm : list) {
            Object[] row = {
                bm.getIdBuku(), bm.getNamaBuku(), bm.getPenulis(), bm.getJumlah(), bm.getJenisBuku()
            };
            tableModel.addRow(row);
        }
    }
    //ini buku Sianida
    public int getTotalBuku() {
        try {
            return dao.countAllBuku();
        } catch (SQLException ex) {
            view.showError("Error mengambil total buku: " + ex.getMessage());
            return 0;
        }
    }
    //ini buku pinjaman
    public int getTotalBukuPinjaman() {
        try {
            return dao.countAllBukuPinjaman(username);
        } catch (SQLException ex) {
            view.showError("Error mengambil total buku: " + ex.getMessage());
            return 0;
        }
    }
}
