/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Laporan;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceLaporanDAO {
    public List<Laporan> getBukuAdmin();
    public List<Laporan> getByDateRange(Date fromDate, Date toDate) throws SQLException;
    public List<Laporan> getByPending(Date fromDate, Date toDate)throws SQLException;
    
    
    
    
}
