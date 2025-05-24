/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Buku;

import Model.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BukuDAO implements InterfaceBukuDAO {

    //nampilin buku milik SIANIDA
    @Override
    public List<Buku> getAllBuku() {
        List<Buku> list = new ArrayList<>();
        String sql = "SELECT * FROM detailbuku";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Buku(
                        rs.getInt("idBuku"),
                        rs.getString("namaBuku"),
                        rs.getString("penulis"),
                        rs.getInt("jumlah"),
                        rs.getString("kategori")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //nampilinTotalBuku
    public int countAllBuku() throws SQLException {
        String sql = "SELECT COUNT(*) FROM detailbuku";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    public int countAllRekap() throws SQLException {
        String sql = "SELECT COUNT(*) FROM laporanpengembalian";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    public void recordBorrow(String username, int idBuku, String namaBuku, String penulis, int jumlah) throws SQLException {
        if (jumlah <= 0) {
            throw new SQLException("Jumlah peminjaman harus lebih dari 0.");
        }

        String getIdUserSQL = "SELECT id FROM users WHERE namaPengguna = ?";
        String getBukuDetailSQL = "SELECT namaBuku, penulis, jumlah FROM detailbuku WHERE idBuku = ?";
        String insertSQL = "INSERT INTO peminjamanbuku (id, idBuku, jumlah, total_pinjam, tgl_pinjam) VALUES (?, ?, ?, ?, NOW())";
        String reduceStockSQL = "UPDATE detailbuku SET jumlah = jumlah - ? WHERE idBuku = ? AND jumlah >= ?";

        try (Connection con = DBConnection.getConnection()) {
            int idUser = -1;

            // Ambil ID user
            try (PreparedStatement ps = con.prepareStatement(getIdUserSQL)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        idUser = rs.getInt("id");
                    } else {
                        throw new SQLException("Username tidak ditemukan.");
                    }
                }
            }

            // Ambil detail buku
            String dbNamaBuku = null;
            String dbPenulis = null;
            int stokTersedia = 0;
            try (PreparedStatement ps = con.prepareStatement(getBukuDetailSQL)) {
                ps.setInt(1, idBuku);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        dbNamaBuku = rs.getString("namaBuku");
                        dbPenulis = rs.getString("penulis");
                        stokTersedia = rs.getInt("jumlah");
                    } else {
                        throw new SQLException("Buku tidak ditemukan.");
                    }
                }
            }

            // Validasi nama dan penulis
            if (!dbNamaBuku.equalsIgnoreCase(namaBuku.trim()) || !dbPenulis.equalsIgnoreCase(penulis.trim())) {
                throw new SQLException("Nama buku atau penulis tidak sesuai dengan ID Buku.");
            }

            // Cek stok
            if (stokTersedia < jumlah) {
                throw new SQLException("Stok tidak mencukupi untuk peminjaman. Stok tersedia: " + stokTersedia);
            }

            // Simpan peminjaman
            try (PreparedStatement ps = con.prepareStatement(insertSQL)) {
                ps.setInt(1, idUser);
                ps.setInt(2, idBuku);
                ps.setInt(3, jumlah);
                ps.setInt(4, jumlah);
                ps.executeUpdate();
            }

            // Kurangi stok
            try (PreparedStatement ps = con.prepareStatement(reduceStockSQL)) {
                ps.setInt(1, jumlah);
                ps.setInt(2, idBuku);
                ps.setInt(3, jumlah);
                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated == 0) {
                    con.rollback();
                    throw new SQLException("Gagal mengurangi stok. Mungkin stok sudah tidak cukup karena transaksi lain.");
                }
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    //nampilin buku yang dipinjem user
    public List<Buku> getUserBooks(String username) {
        List<Buku> list = new ArrayList<>();

        String getIdUserSQL = "SELECT id FROM users WHERE namaPengguna = ?";
        String sql = "SELECT b.idBuku, b.namaBuku, b.penulis, p.jumlah AS jumlahPinjam, b.kategori, p.tgl_pinjam AS tglPinjam, p.tgl_kembali AS tglKembali, p.tgl_pengembalian AS tglPengembalian FROM peminjamanbuku p JOIN detailbuku b ON p.idBuku = b.idBuku WHERE p.id = ?";

        try (Connection con = DBConnection.getConnection()) {
            // 1) Cari idUser
            int idUser;
            try (PreparedStatement ps1 = con.prepareStatement(getIdUserSQL)) {
                ps1.setString(1, username);
                try (ResultSet rs1 = ps1.executeQuery()) {
                    if (!rs1.next()) {
                        // username tidak ada
                        throw new SQLException("Username tidak ditemukan: " + username);
                    }
                    idUser = rs1.getInt("id");
                }
            }

            // 2) Ambil semua buku yang dipinjam user tersebut
            try (PreparedStatement ps2 = con.prepareStatement(sql)) {
                ps2.setInt(1, idUser);
                try (ResultSet rs2 = ps2.executeQuery()) {
                    while (rs2.next()) {
                        Buku b = new Buku(
                                rs2.getInt("idBuku"),
                                rs2.getString("namaBuku"),
                                rs2.getString("penulis"),
                                rs2.getInt("jumlahPinjam"),
                                rs2.getDate("tglPinjam"),
                                rs2.getDate("tglKembali"),
                                rs2.getDate("tglPengembalian"),
                                rs2.getString("kategori")
                        );
                        list.add(b);
                    }
                }
            }
        } catch (SQLException ex) {
            // kalau perlu, ganti dengan view.showError(...) atau logging
            ex.printStackTrace();
        }

        return list;
    }

    public int countAllBukuPinjaman(String username) throws SQLException {
        String getIdUserSql = "SELECT id FROM users WHERE namaPengguna = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst1 = con.prepareStatement(getIdUserSql)) {
            pst1.setString(1, username);
            try (ResultSet rs1 = pst1.executeQuery()) {
                if (!rs1.next()) {
                    return 0;
                }
                int userId = rs1.getInt("id");

                String countSql = "SELECT COUNT(*) FROM peminjamanbuku WHERE id = ?";
                try (PreparedStatement pst2 = con.prepareStatement(countSql)) {
                    pst2.setInt(1, userId);
                    try (ResultSet rs2 = pst2.executeQuery()) {
                        if (rs2.next()) {
                            return rs2.getInt(1);
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }
    }

    // Mengembalikan buku
    public void returnBook(String username, int idBuku, String nama, String penulis, int jumlahKembali) throws SQLException {
        if (jumlahKembali <= 0) {
            throw new SQLException("Jumlah pengembalian harus lebih dari 0.");
        }

        String sqlGetUser = "SELECT id FROM users WHERE namaPengguna = ?";
        String sqlGetBuku = "SELECT namaBuku, penulis FROM detailbuku WHERE idBuku = ?";
        String sqlGetLoan = "SELECT jumlah AS sisa, total_pinjam, tgl_pinjam, tgl_kembali FROM peminjamanbuku WHERE id = ? AND idBuku = ?";
        String sqlPartialReturn = "UPDATE peminjamanbuku SET jumlah = jumlah - ? WHERE id = ? AND idBuku = ?";
        String sqlFullReturn = "UPDATE peminjamanbuku SET jumlah = 0, tgl_pengembalian = NOW() WHERE id = ? AND idBuku = ?";
        String sqlDeleteIfZero = "DELETE FROM peminjamanbuku WHERE id = ? AND idBuku = ?";
        String sqlInsertLaporan = "INSERT INTO laporanpengembalian (id, idBuku, jumlahTotal, tglPinjam, tglKembali, pengembalian) VALUES (?, ?, ?, ?, ?, NOW())";
        String sqlAddStock = "UPDATE detailbuku SET jumlah = jumlah + ? WHERE idBuku = ?";

        try (Connection con = DBConnection.getConnection()) {
            int idUser;

            // Ambil ID user
            try (PreparedStatement ps = con.prepareStatement(sqlGetUser)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("User tidak ditemukan.");
                    }
                    idUser = rs.getInt("id");
                }
            }

            // Ambil data buku (untuk validasi nama dan penulis)
            String dbNamaBuku = null;
            String dbPenulis = null;
            try (PreparedStatement ps = con.prepareStatement(sqlGetBuku)) {
                ps.setInt(1, idBuku);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        dbNamaBuku = rs.getString("namaBuku");
                        dbPenulis = rs.getString("penulis");
                    } else {
                        throw new SQLException("Buku tidak ditemukan.");
                    }
                }
            }

            // Validasi nama dan penulis
            if (!dbNamaBuku.equalsIgnoreCase(nama.trim()) || !dbPenulis.equalsIgnoreCase(penulis.trim())) {
                throw new SQLException("Nama buku atau penulis tidak sesuai dengan ID Buku.");
            }

            // Ambil sisa pinjam + total pinjam + tanggal
            int sisaPinjam, totalPinjamAwal;
            Date tglPinjam, tglKembali;
            try (PreparedStatement ps = con.prepareStatement(sqlGetLoan)) {
                ps.setInt(1, idUser);
                ps.setInt(2, idBuku);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Tidak ada peminjaman untuk buku ini.");
                    }
                    sisaPinjam = rs.getInt("sisa");
                    totalPinjamAwal = rs.getInt("total_pinjam");
                    tglPinjam = rs.getDate("tgl_pinjam");
                    tglKembali = rs.getDate("tgl_kembali");
                }
            }

            // Validasi jumlah pengembalian
            if (jumlahKembali > sisaPinjam) {
                throw new SQLException("Anda hanya meminjam " + sisaPinjam + " buku.");
            }

            // Partial vs Full return
            if (jumlahKembali < sisaPinjam) {
                try (PreparedStatement ps = con.prepareStatement(sqlPartialReturn)) {
                    ps.setInt(1, jumlahKembali);
                    ps.setInt(2, idUser);
                    ps.setInt(3, idBuku);
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = con.prepareStatement(sqlFullReturn)) {
                    ps.setInt(1, idUser);
                    ps.setInt(2, idBuku);
                    ps.executeUpdate();
                }
                try (PreparedStatement ps = con.prepareStatement(sqlDeleteIfZero)) {
                    ps.setInt(1, idUser);
                    ps.setInt(2, idBuku);
                    ps.executeUpdate();
                }
            }

            // Insert laporan
            try (PreparedStatement ps = con.prepareStatement(sqlInsertLaporan)) {
                ps.setInt(1, idUser);
                ps.setInt(2, idBuku);
                ps.setInt(3, jumlahKembali);
                ps.setDate(4, tglPinjam);
                ps.setDate(5, tglKembali);
                ps.executeUpdate();
            }

            // Tambah stok
            try (PreparedStatement ps = con.prepareStatement(sqlAddStock)) {
                ps.setInt(1, jumlahKembali);
                ps.setInt(2, idBuku);
                ps.executeUpdate();
            }

        }
    }

    //tambah buku
    @Override
    public boolean insert(Buku b) {
        String sql = "INSERT INTO detailbuku (idBuku, namaBuku, penulis, jumlah, kategori) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, b.getIdBuku());
            stmt.setString(2, b.getNamaBuku());
            stmt.setString(3, b.getPenulis());
            stmt.setInt(4, b.getJumlah());
            stmt.setString(5, b.getJenisBuku());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Edit error: " + e.getLocalizedMessage());
            return false;
        }
    }

    //edit buku
    @Override
    public boolean edit(Buku bukuTerpilih) {
        String sql = "UPDATE detailbuku SET namaBuku = ?, penulis = ?, jumlah = ?, kategori = ? WHERE idBuku = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, bukuTerpilih.getNamaBuku());
            stmt.setString(2, bukuTerpilih.getPenulis());
            stmt.setInt(3, bukuTerpilih.getJumlah());
            stmt.setString(4, bukuTerpilih.getJenisBuku());
            stmt.setInt(5, bukuTerpilih.getIdBuku());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Edit error: " + e.getLocalizedMessage());
            return false;
        }
    }

    //hapus buku
    @Override
    public boolean delete(int idBuku) {
        String sql = "DELETE FROM detailbuku WHERE idBuku = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idBuku);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Hapus error: " + e.getLocalizedMessage());
            return false;
        }
    }

    public Buku findById(int idBuku) throws SQLException {
        String sql = "SELECT * FROM detailbuku WHERE idBuku = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idBuku);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Gunakan constructor dengan parameter
                    Buku b = new Buku(
                            rs.getInt("idBuku"),
                            rs.getString("namaBuku"),
                            rs.getString("penulis"),
                            rs.getInt("jumlah"),
                            rs.getString("kategori")
                    );
                    return b;
                }
            }
        }
        return null;
    }

    public List<Buku> getByPending(String username) throws SQLException {
        List<Buku> list = new ArrayList<>();
        String sql = "SELECT pb.idBuku, pb.jumlah, b.namaBuku, u.namaPengguna, pb.tgl_Pinjam, pb.tgl_Kembali FROM peminjamanbuku pb JOIN detailBuku b On pb.idBuku = b.idBuku JOIN users u ON pb.id = u.id WHERE pb.tgl_Pengembalian is NULL";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Buku bkP = new Buku(
                            rs.getInt("idBuku"),
                            rs.getString("namaBuku"),
                            rs.getInt("jumlah"),
                            rs.getString("namaPengguna"),
                            rs.getDate("tgl_Pinjam"),
                            rs.getDate("tgl_Kembali")
                    );
                    list.add(bkP);
                }
            }
        }
        return list;
    }

}
