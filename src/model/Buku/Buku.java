/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Buku;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Buku extends AbstractBuku {

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }
    private String jenisBuku;
    private String namaPengguna;

    //buat minjem buku
    public Buku(int idBuku, String namaBuku, String penulis, int jumlah, String jenisBuku) {
        super(idBuku, namaBuku, penulis, jumlah);
        this.jenisBuku = jenisBuku;
    }

    // Constructor lengkap (dengan tanggal pinjam/kembali/pengembalian)
    public Buku(int idBuku, String namaBuku, String penulis, int jumlah,
            Date tglPinjam, Date tglKembali, Date tglPengembalian, String jenisBuku) {
        super(idBuku, namaBuku, penulis, jumlah, tglPinjam, tglKembali, tglPengembalian);
        this.jenisBuku = jenisBuku;
    }

    //buat ngambil bukuPending
    public Buku(int idBuku, String namaBuku, int jumlah, String namaPengguna, Date tglPinjam, Date tglKembali) {
        super(idBuku, namaBuku, jumlah, tglPinjam, tglKembali);
        this.namaPengguna = namaPengguna;
    }

    @Override
    public String getJenisBuku() {
        return jenisBuku;
    }

    public void setJenisBuku(String jenisBuku) {
        this.jenisBuku = jenisBuku;
    }

}
