/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

import java.sql.*;
import Model.DBConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    //daripada close banyak mending make try-with-resources bang
    //cek login 
    public User verifikasiUsers(String username, String password) {
        String sql = "SELECT id, namaPengguna, passwordPengguna, emailPengguna, nomorTeleponPengguna, idRole FROM users WHERE namaPengguna = ? AND passwordPengguna = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("namaPengguna"),
                            rs.getString("passwordPengguna"),
                            rs.getString("emailPengguna"),
                            rs.getString("nomorTeleponPengguna"),
                            rs.getInt("idRole")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // method password reset
    public boolean checkEmailExists(String email) {
        String sql = "SELECT 1 FROM users WHERE emailPengguna = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //cek duplikasi register
    public boolean checkDuplicate(String username, String email) {
        String sql = "SELECT 1 FROM users WHERE namaPengguna = ? OR emailPengguna = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean isUserExist(User user) {
        String sql = "SELECT COUNT(*) FROM users WHERE namaPengguna = ? OR emailPengguna = ? OR nomorTeleponPengguna = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getNamaPengguna());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getnomorTelepon());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //insert user
    public boolean insertUser(User user) {
        String sql = "INSERT INTO users(namaPengguna, passwordPengguna, emailPengguna, nomorTeleponPengguna, idRole) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getNamaPengguna());
            ps.setString(2, user.getPasswordPengguna());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getnomorTelepon());
            ps.setInt(5, user.getIdRole());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //update password dari lupa password
    public boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE users SET passwordPengguna = ? WHERE emailPengguna = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            int updated = ps.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //buat nampilin informasi akun user
    public User getUserByUsername(String namaPengguna) {
        String sql = "SELECT id AS idUser, namaPengguna, passwordPengguna, emailPengguna, nomorTeleponPengguna FROM users WHERE namaPengguna = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, namaPengguna);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("idUser"),
                            rs.getString("namaPengguna"),
                            rs.getString("passwordPengguna"),
                            rs.getString("emailPengguna"),
                            rs.getString("nomorTeleponPengguna")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //buat update akun user
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET passwordPengguna = ?, emailPengguna = ?, nomorTeleponPengguna = ? WHERE namaPengguna = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getPasswordPengguna());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getnomorTelepon());
            ps.setString(4, user.getNamaPengguna());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //buat update akun user
    public boolean updateUserFromAdmin(User user) {
        String sql = "UPDATE users SET namaPengguna = ?, emailPengguna = ?, nomorTeleponPengguna = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getNamaPengguna());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getnomorTelepon());
            ps.setInt(4, user.getIdUser());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int idUser) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Hapus error: " + e.getLocalizedMessage());
            return false;
        }
    }

    //ngambil dataUser
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE idRole != 1";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("id"),
                        rs.getString("namaPengguna"),
                        rs.getString("passwordPengguna"),
                        rs.getString("emailPengguna"),
                        rs.getString("nomorTeleponPengguna")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //ngitung total user
    public int countAllUser() throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE idRole !=1";
        try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    //ubah akun admin
    public boolean updateAdmin(User user, String username) {
        String sql = "UPDATE users SET namaPengguna = ?, passwordPengguna = ?, emailPengguna = ?, nomorTeleponPengguna = ? WHERE namaPengguna = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getNamaPengguna());
            ps.setString(2, user.getPasswordPengguna());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getnomorTelepon());
            ps.setString(5, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
     public List<User> getPeminjamAktif() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT u.namaPengguna, SUM(pb.jumlah) AS banyak_peminjaman FROM peminjamanbuku pb JOIN users u ON pb.id = u.id WHERE pb.tgl_pengembalian IS NULL GROUP BY u.id, u.namaPengguna";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User u = new User(
                            rs.getString("namaPengguna"),
                            rs.getInt("banyak_peminjaman")
                    );
                    list.add(u);
                }
            }
        }
        return list;
    }

}
