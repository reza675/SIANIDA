/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Laporan.Laporan;
import Model.Laporan.LaporanDAO;
import View.AksesAdmin.RekapPeminjamanView;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class RekapPeminjamanController {

    private LaporanDAO dao;
    private RekapPeminjamanView view;
    private String username;

    public RekapPeminjamanController(RekapPeminjamanView view, String username) {
        this.dao = new LaporanDAO();
        this.view = view;
        this.username = username;
    }

    public void loadTableAll() {
        DefaultTableModel m = view.getTblModel();
        m.setRowCount(0);
        List<Laporan> semua = dao.getBukuAdmin();
        for (Laporan rpt : semua) {
            Object[] row = {
                rpt.getIdLaporan(),
                rpt.getNamaBuku(),
                rpt.getNamaPengguna(),
                rpt.getTglPinjam(),
                rpt.getTglKembali(),
                rpt.getPengembalian()
            };
            m.addRow(row);
        }
    }
     public void loadTableByDate(Date from, Date to) {
        try {
            DefaultTableModel m = view.getTblModel();
            m.setRowCount(0);
            List<Laporan> list = dao.getByDateRange(from, to);
            for (Laporan rpt : list) {
                m.addRow(new Object[]{
                    rpt.getIdLaporan(),
                    rpt.getNamaBuku(),
                    rpt.getNamaPengguna(),
                    rpt.getTglPinjam(),
                    rpt.getTglKembali(),
                    rpt.getPengembalian()
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
