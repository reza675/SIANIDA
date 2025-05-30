/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Model.Buku.Buku;
import Model.Buku.BukuDAO;
import View.AksesUser.PinjamBukuUserView;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class PinjamBukuController {

    private BukuDAO dao;
    private PinjamBukuUserView view;
    private String username;

    public PinjamBukuController(PinjamBukuUserView view, String username) {
        this.dao = new BukuDAO();
        this.view = view;
        this.username = username;
    }

    public void loadTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblModel();
        tableModel.setRowCount(0);
        List<Buku> list = dao.getAllBuku();
        for (Buku bm : list) {
            Object[] row = {
                bm.getIdBuku(), bm.getNamaBuku(), bm.getPenulis(), bm.getJumlah(),bm.getJenisBuku()
            };
            tableModel.addRow(row);
        }
    }

    public void pinjamBuku(int idBuku, String nama, String penulis, int jumlah) {
        try {
            dao.recordBorrow(username, idBuku, nama, penulis, jumlah);  
            loadTable();
            view.showMessage("Berhasil meminjam buku!");
            
        } catch (SQLException ex) {
            view.showError("Gagal meminjam: " + ex.getMessage());
        }
    }

}
