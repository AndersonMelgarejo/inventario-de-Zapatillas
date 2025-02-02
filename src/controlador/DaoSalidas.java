/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.conexion;
import modelo.salidas;

/**
 *
 * @author LENOVO
 */
public class DaoSalidas {
    Connection con;
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    public boolean insertar(salidas c) {
        String SQL = "INSERT INTO salidas(numSalida,idCliente,fecha,subtotal,igv,total) VALUES(?,?,?,?,?,?)";
        try {
            con = (Connection) cn.conectar();
            ps = (PreparedStatement) con.prepareStatement(SQL);
            
            ps.setString(1, c.getNumSalida());
            ps.setInt(2, c.getIdCliente());
            ps.setDate(3, (Date) c.getFecha());
            ps.setDouble(4, c.getSubtotal());
            ps.setDouble(5, c.getIgv());
            ps.setDouble(6, c.getTotal());

            int n = ps.executeUpdate();
            if (n!= 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }
    
    public String numSalida() {
        String numero="";
        String SQL = "select max(idSalida) from salidas";
        try {
            con = (Connection) cn.conectar();
            ps = (PreparedStatement) con.prepareStatement(SQL);
 
            rs = ps.executeQuery();
            
            while(rs.next()){
                numero=rs.getString(1);
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);

        }
        return numero;
    }
    
    
    public boolean restarStock  (int idSalida,int cant) {
        String SQL = "UPDATE entrada set stock=stock"+cant+ "WHERE identrada="+idSalida;
        try {
            con = (Connection) cn.conectar();
            ps = (PreparedStatement) con.prepareStatement(SQL);

            int n = ps.executeUpdate();
            if (n!= 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }
    
  
    
}
