/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Buku.Buku;
import Model.Buku.BukuDAO;
import View.AksesAdmin.ManajemenBukuView;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ManajemenBukuController {

    private BukuDAO dao;
    private ManajemenBukuView view;
    private String username;

    public ManajemenBukuController(ManajemenBukuView view, String username) {
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
                bm.getIdBuku(), bm.getNamaBuku(), bm.getPenulis(), bm.getJumlah(), bm.getJenisBuku()
            };
            tableModel.addRow(row);
        }
    }

    public void editBuku(Buku bukuTerpilih) {
        try {
            boolean sukses = dao.edit(bukuTerpilih);
            if (sukses) {
                view.showMessage("Data buku berhasil di-update.");
                loadTable();   // reload data tabel agar perubahan langsung terlihat
            } else {
                view.showError("Gagal meng-update data buku.");
            }
        } catch (Exception ex) {
            view.showError("Error saat update: " + ex.getMessage());
        }
    }

    public void addBuku(Buku b) {

        try {
            if (b.getNamaBuku() == null || b.getNamaBuku().trim().isEmpty()
                    || b.getPenulis() == null || b.getPenulis().trim().isEmpty()
                    || b.getJenisBuku() == null || b.getJenisBuku().trim().isEmpty()) {
                throw new Exception("Semua field (Nama, Penulis, Kategori) wajib diisi.");

            }
            boolean sukses = dao.insert(b);
            if (sukses) {
                view.showMessage("Data buku berhasil ditambahkan.");
                loadTable();
            } else {
                view.showError("Gagal menambahkan data buku.");
            }
        } catch (Exception ex) {
            view.showError("Error saat tambah: " + ex.getMessage());
        }
    }

    public void deleteBuku(Buku bukuForm) {
        try {
            // Ambil data dari DB untuk valikadasi
            Buku bukuDB = dao.findById(bukuForm.getIdBuku());
            if (bukuDB == null) {
                view.showError("ID buku tidak ditemukan.");
                return;
            }
            // Validasi field nama dan penulis
            if (!bukuDB.getNamaBuku().equals(bukuForm.getNamaBuku())
                    || !bukuDB.getPenulis().equals(bukuForm.getPenulis())) {
                view.showError("Data buku tidak cocok. Proses hapus dibatalkan.");
                return;
            }
            boolean sukses = dao.delete(bukuForm.getIdBuku());
            if (sukses) {
                view.showMessage("Data buku berhasil dihapus.");
                loadTable();
            } else {
                view.showError("Gagal menghapus data buku.");
            }
        } catch (Exception ex) {
            view.showError("Error saat hapus: " + ex.getMessage());
        }
    }

}
