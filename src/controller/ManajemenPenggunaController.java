package Controller;

import Model.User.User;
import Model.User.UserDAO;
import View.AksesAdmin.ManajemenPenggunaView;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ManajemenPenggunaController{

    private UserDAO dao;
    private ManajemenPenggunaView view;
    private String username;

    public ManajemenPenggunaController(ManajemenPenggunaView view, String username) {
        this.dao = new UserDAO();
        this.view = view;
        this.username = username;
    }

    public void loadTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblModel();
        tableModel.setRowCount(0);
        List<User> list = dao.getAllUser();
        for (User p : list) {
            Object[] row = {
                p.getIdUser(), p.getNamaPengguna(), p.getEmail(), p.getnomorTelepon()
            };
            tableModel.addRow(row);
        }
    }

    public void addUser(User p) {
    try {
        if (p.getNamaPengguna().isEmpty() || p.getEmail().isEmpty() || p.getnomorTelepon().isEmpty()) {
            throw new Exception("Semua field wajib diisi.");
        }

        if (dao.isUserExist(p)) {
            view.showError("User sudah ada.");
            return;
        }

        boolean sukses = dao.insertUser(p);
        if (sukses) {
            view.showMessage("User berhasil ditambahkan.");
            loadTable();
        } else {
            view.showError("Gagal menambahkan pengguna.");
        }
    } catch (Exception e) {
        view.showError("Error saat tambah: " + e.getMessage());
    }
}


    public void editUser(User p) {
        try {
            boolean sukses = dao.updateUserFromAdmin(p);
            if (sukses) {
                view.showMessage("User berhasil diperbarui.");
                loadTable();
            } else {
                view.showError("Gagal memperbarui pengguna.");
            }
        } catch (Exception e) {
            view.showError("Error saat edit: " + e.getMessage());
        }
    }

    public void deleteUser(User p) {
        try {
            boolean sukses = dao.deleteUser(p.getIdUser());
            if (sukses) {
                view.showMessage("User berhasil dihapus.");
                loadTable();
            } else {
                view.showError("Gagal menghapus pengguna.");
            }
        } catch (Exception e) {
            view.showError("Error saat hapus: " + e.getMessage());
        }
    }
}
