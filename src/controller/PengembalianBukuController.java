/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Buku.Buku;
import Model.Buku.BukuDAO;
import View.AksesUser.PengembalianBukuUserView;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class PengembalianBukuController {
    private BukuDAO dao;
    private PengembalianBukuUserView view;
    private String username;
    public PengembalianBukuController(PengembalianBukuUserView view, String username) {
        this.dao = new BukuDAO();
        this.view = view;
        this.username = username;
    }
    public void loadTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblModel();
        tableModel.setRowCount(0);
        List<Buku> list = dao.getUserBooks(username);
        for (Buku bm : list) {
            Object[] row = {
                bm.getIdBuku(), bm.getNamaBuku(), bm.getPenulis(), bm.getJumlah(), bm.getTglPinjam(), bm.getTglKembali()
            };
            tableModel.addRow(row);
        }
    }
    public void pengembalianBuku(int idBuku, String nama, String penulis, int jumlah) {
        try {
            dao.returnBook(username, idBuku, nama,penulis,jumlah);  
            loadTable();
            view.showMessage("Berhasil mengembalikan buku!");
        } catch (SQLException ex) {
            view.showError("Gagal mengembalikan buku: " + ex.getMessage());
        }
    }
}
