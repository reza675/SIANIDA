/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Buku.Buku;
import Model.Buku.BukuDAO;
import View.AksesAdmin.BukuPendingView;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class BukuPendingController {

    private BukuDAO dao;
    private BukuPendingView view;
    private String username;

    public BukuPendingController(BukuPendingView view, String username) {
        this.dao = new BukuDAO();
        this.view = view;
        this.username = username;
    }

    public void loadTableP() {
    DefaultTableModel tableModel = (DefaultTableModel) view.getTblModel();
    tableModel.setRowCount(0);
    try {
        List<Buku> list = dao.getByPending(username);
        int no = 1;
        for (Buku bm : list) {
            Object[] row = {
                no++,
                bm.getNamaBuku(),
                bm.getJumlah(),
                bm.getNamaPengguna(),
                bm.getTglPinjam(),
                bm.getTglKembali()
            };
            tableModel.addRow(row);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(view, 
            "Gagal memuat data buku pending:\n" + ex.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}
