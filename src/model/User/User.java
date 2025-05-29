/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

/**
 *
 * @author ASUS
 */
public class User {

    private int idUser;
    private String namaPengguna;
    private String passwordPengguna;
    private String email;
    private String nomorTelepon;
    private int idRole;
    private int jumlahPeminjaman;

    //buat login
    public User(int idUser, String namaPengguna, String passwordPengguna, String emailPengguna, String nomorTeleponPengguna, int idRole) {
        this.idUser = idUser;
        this.namaPengguna = namaPengguna;
        this.passwordPengguna = passwordPengguna;
        this.email = emailPengguna;
        this.nomorTelepon = nomorTeleponPengguna;
        this.idRole = idRole;
    }

    //buat register
    public User(String namaPengguna, String passwordPengguna, String emailPengguna, String nomorTeleponPengguna) {
        this.idUser = 0; //placeholder doang
        this.namaPengguna = namaPengguna;
        this.passwordPengguna = passwordPengguna;
        this.email = emailPengguna;
        this.nomorTelepon = nomorTeleponPengguna;
        this.idRole = 2;
    }

    //buat akun user
    public User(int idUser, String namaPengguna, String passwordPengguna, String emailPengguna, String nomorTeleponPengguna) {
        this.idUser = idUser;
        this.namaPengguna = namaPengguna;
        this.passwordPengguna = passwordPengguna;
        this.email = emailPengguna;
        this.nomorTelepon = nomorTeleponPengguna;
    }
    //edit akun user

    public User(int idUser, String namaPengguna, String emailPengguna, String nomorTeleponPengguna) {
        this.idUser = idUser;
        this.namaPengguna = namaPengguna;
        this.email = emailPengguna;
        this.nomorTelepon = nomorTeleponPengguna;
    }

    public User(String namaPengguna, int jumlahPeminjaman) {
        this.namaPengguna = namaPengguna;
        this.jumlahPeminjaman = jumlahPeminjaman;
    }

    public int getJumlahPeminjaman() {
        return jumlahPeminjaman;
    }

    public void setJumlahPeminjaman(int jumlahPeminjaman) {
        this.jumlahPeminjaman = jumlahPeminjaman;
    }

    public void setidUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getPasswordPengguna() {
        return passwordPengguna;
    }

    public void setPasswordPengguna(String passwordPengguna) {
        this.passwordPengguna = passwordPengguna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getnomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

}
