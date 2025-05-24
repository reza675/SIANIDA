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
public abstract class AbstractBuku {

    private int idBuku;
    private String namaBuku;
    private String penulis;
    private int jumlah;
    private Date tglPinjam;
    private Date tglKembali;
    private Date tglPengembalian;

    //buat minjem buku
    public AbstractBuku(int idBuku, String namaBuku, String penulis, int jumlah) {
        this.idBuku = idBuku;
        this.namaBuku = namaBuku;
        this.penulis = penulis;
        this.jumlah = jumlah;
    }
    //ini lengkap buat laporan
    public AbstractBuku(int idBuku, String namaBuku, String penulis, int jumlah, Date tglPinjam, Date tglKembali, Date tglPengembalian) {
        this.idBuku = idBuku;
        this.namaBuku = namaBuku;
        this.penulis = penulis;
        this.jumlah = jumlah;
        this.tglPinjam = tglPinjam;
        this.tglKembali = tglKembali;
        this.tglPengembalian = tglPengembalian;
    }
    //ini buat nampilin bukuPending
     public AbstractBuku(int idBuku, String namaBuku,int jumlah, Date tglPinjam, Date tglKembali) {
        this.idBuku = idBuku;
        this.namaBuku = namaBuku;
        this.jumlah = jumlah;
        this.tglPinjam = tglPinjam;
        this.tglKembali = tglKembali;
    }
    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public Date getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(Date tglKembali) {
        this.tglKembali = tglKembali;
    }

    public Date getTglPengembalian() {
        return tglPengembalian;
    }

    public void setTglPengembalian(Date tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }    
    public abstract String getJenisBuku();

}
