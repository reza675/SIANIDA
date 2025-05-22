/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Buku;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceBukuDAO {
    public boolean insert(Buku b);  
    public boolean edit(Buku bukuTerpilih);   
    public boolean delete(int id);
    public List<Buku> getAllBuku();
}
