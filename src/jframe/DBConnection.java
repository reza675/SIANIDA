/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class DBConnection {
    static Connection con = null;
    public static Connection getConnection (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sianida","root","");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Koneksi gagal: " + exception.getLocalizedMessage());
        }
        return con;
    }
}