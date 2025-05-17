/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sianida;

import controllers.LoginController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import models.UserDAO;
import views.LoginPageView;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            LoginPageView view = new LoginPageView();
            UserDAO userDAO = new UserDAO();
            new LoginController(view, userDAO);
            view.setVisible(true);
        });
    }
}
