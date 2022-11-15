/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaInternacionObjects;

import SistemaInternacionConexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Franco
 */
public class Cama {
    int id;
    private int numero;
    private String estado;
    
    private Habitacion habitacion;
    private Internacion internacion;
    
    public Cama(){}
    
    public Cama(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    public void mostrar (JTable parammodel) {
     
    Conexion objetoconexion = new Conexion();    
    
    DefaultTableModel modelo= new DefaultTableModel();
    
    String sql=" ";
    
    modelo.addColumn("Id");
    modelo.addColumn("Estado");
    modelo.addColumn("Numero");
    
    
    parammodel.setModel(modelo);
    
    sql ="select * from cama;";
    
    //arreglo que recorre tabla para mostrar datos
    
    String [] datos = new String[3];
    //Ejecutar consulta
    Statement st;
    
    try {
        st = objetoconexion.establecerConexion().createStatement();
        
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){   
             datos[0] = rs.getString(1);
             datos[1] = rs.getString(2);
             datos[2] = rs.getString(3);
             
             modelo.addRow(datos);
        
        }
        
        parammodel.setModel(modelo);
        
    } catch (Exception e){
        
     JOptionPane.showMessageDialog(null, "error" + e.toString());
    }
    
    
    
    }
    
    public void insertar (JTextField numero, JTextField estado){
        
        setNumero(Integer.parseInt(numero.getText()));
        setEstado(estado.getText());
        
        Conexion conexion = new Conexion();
        
        String consulta = "insert into cama (numero,estado) values (?,?);";
        
        try{
            CallableStatement cs = conexion.establecerConexion().prepareCall(consulta);
            cs.setInt(1,getNumero());
            cs.setString(2, getEstado());
           
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se insertó correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se insertó correctamente");
        }
        
    }
    
    
    
    public void eliminar (JTextField id){
    
        setId(Integer.parseInt(id.getText()));
        
        String consulta = "delete from cama where id=?;";
        
        Conexion objeto = new Conexion();
        
        try{
            CallableStatement cs = objeto.establecerConexion().prepareCall(consulta);
            
            cs.setInt(1,getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se Elimino correctamente");
            } catch (Exception e){
            JOptionPane.showMessageDialog(null,"No se elimino");
            
            }
        
    }
    
    
 
    
    
    
    
}
