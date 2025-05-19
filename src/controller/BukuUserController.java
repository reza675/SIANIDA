/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Buku.Buku;
import Model.Buku.BukuDAO;
import View.BukuUserView;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class BukuUserController {
    private BukuDAO dao;
    private BukuUserView view;
    private String username;
    
    public BukuUserController(BukuUserView view, String username) {
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
                bm.getIdBuku(), bm.getNamaBuku(), bm.getPenulis(), bm.getJumlah(), bm.getTglPinjam(),bm.getTglKembali()
            };
            tableModel.addRow(row);
        }
    }

}
