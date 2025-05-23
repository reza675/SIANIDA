/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Buku.Buku;
import Model.User.User;
import Model.User.UserDAO;
import View.AksesAdmin.HomePageAdminView;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class HomePageAdminController extends AbstractHomePageController{
    private HomePageAdminView view;
    private UserDAO userDao;

    public HomePageAdminController(HomePageAdminView view, String username) {
        super(username);
        this.view = view;
        this.userDao = new UserDAO();
        
    }
    @Override
    public List<Buku> fetchAllBuku() {
        return dao.getAllBuku();
    }
    @Override
    public int getTotalBuku() {
        try {
            return dao.countAllBuku();
        } catch (SQLException ex) {
            view.showError("Error mengambil total buku: " + ex.getMessage());
            return 0;
        }
    }
    @Override
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
    public List<User> fetchAllUser() {
        return userDao.getAllUser();
    }
    public int getTotalUser() {
        try {
            return userDao.countAllUser();
        } catch (SQLException ex) {
            view.showError("Error mengambil total User: " + ex.getMessage());
            return 0;
        }
    }
    public void loadTableUser() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblModelUser();
        tableModel.setRowCount(0);
        List<User> list = userDao.getAllUser();
        for (User u : list) {
            Object[] row = {
                u.getIdUser(), u.getNamaPengguna(), u.getnomorTelepon()
            };
            tableModel.addRow(row);
        }
    }
    
    
}
