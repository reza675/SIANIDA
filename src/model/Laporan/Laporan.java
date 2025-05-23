/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Laporan;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Laporan {

    private int idLaporan;
    private String namaBuku;
    private String namaPengguna;
    private Date tglPinjam;
    private Date tglKembali;
    private Date pengembalian;

    public Laporan() {
    }

    public Laporan(int idLaporan, String namaBuku, String namaPengguna,
            Date tglPinjam, Date tglKembali, Date pengembalian) {
        this.idLaporan = idLaporan;
        this.namaBuku = namaBuku;
        this.namaPengguna = namaPengguna;
        this.tglPinjam = tglPinjam;
        this.tglKembali = tglKembali;
        this.pengembalian = pengembalian;
    }

    public int getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(int idLaporan) {
        this.idLaporan = idLaporan;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
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

    public Date getPengembalian() {
        return pengembalian;
    }

    public void setPengembalian(Date pengembalian) {
        this.pengembalian = pengembalian;
    }

}
