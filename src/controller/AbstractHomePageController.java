/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Buku.Buku;
import Model.Buku.BukuDAO;
import View.AksesAdmin.HomePageAdminView;
import View.AksesUser.HomePageUserView;
import java.util.List;

/**
 *
 * @author ASUS
 */
public abstract class AbstractHomePageController {

    protected BukuDAO dao;
    protected String username;

    public AbstractHomePageController(String username) {
        this.dao = new BukuDAO();
        this.username = username;
    }
    public abstract List<Buku> fetchAllBuku();
    public abstract int getTotalBuku();
    public abstract void loadTable();
}
