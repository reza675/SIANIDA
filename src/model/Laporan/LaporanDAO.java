/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Laporan;

import Model.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class LaporanDAO {

    public List<Laporan> getBukuAdmin() {
        List<Laporan> list = new ArrayList<>();
        String sql = "SELECT lp.idLaporan,db.namaBuku,u.namaPengguna,lp.tglPinjam,lp.tglKembali,lp.pengembalian FROM laporanpengembalian lp JOIN detailbuku db ON lp.idBuku = db.idBuku JOIN users u ON lp.id = u.id";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Laporan rpt = new Laporan();
                rpt.setIdLaporan(rs.getInt("idLaporan"));
                rpt.setNamaBuku(rs.getString("namaBuku"));
                rpt.setNamaPengguna(rs.getString("namaPengguna"));
                rpt.setTglPinjam(rs.getDate("tglPinjam"));
                rpt.setTglKembali(rs.getDate("tglKembali"));
                rpt.setPengembalian(rs.getDate("pengembalian"));
                
                list.add(rpt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
     public List<Laporan> getByDateRange(Date fromDate, Date toDate) throws SQLException {
        String sql = "SELECT lp.idLaporan, db.namaBuku, u.namaPengguna, lp.tglPinjam, lp.tglKembali, lp.pengembalian FROM laporanpengembalian lp JOIN detailbuku db ON lp.idBuku  = db.idBuku JOIN users u ON lp.id = u.id WHERE lp.tglPinjam BETWEEN ? AND ?";
        List<Laporan> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, fromDate);
            ps.setDate(2, toDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Laporan rpt = new Laporan();
                    rpt.setIdLaporan(   rs.getInt("idLaporan")     );
                    rpt.setNamaBuku(    rs.getString("namaBuku")   );
                    rpt.setNamaPengguna(rs.getString("namaPengguna"));
                    rpt.setTglPinjam(   rs.getDate("tglPinjam")    );
                    rpt.setTglKembali(  rs.getDate("tglKembali")   );
                    rpt.setPengembalian(rs.getDate("pengembalian"));
                    list.add(rpt);
                }
            }
        }
        return list;
    }
     
}
