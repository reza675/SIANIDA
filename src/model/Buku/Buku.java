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

    //buat minjem buku
    public Buku(int idBuku, String namaBuku, String penulis, int jumlah) {
        super(idBuku, namaBuku, penulis, jumlah);
    }

    // Constructor lengkap (dengan tanggal pinjam/kembali/pengembalian)
    public Buku(int idBuku, String namaBuku, String penulis, int jumlah,
            Date tglPinjam, Date tglKembali, Date tglPengembalian) {
        super(idBuku, namaBuku, penulis, jumlah, tglPinjam, tglKembali, tglPengembalian);
    }
}
