/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaInternacionObjects;

import SistemaInternacionConexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Franco
 */
public class Habitacion {
    private int id;
    private int numero;
    
    
    List <Cama> camas = new ArrayList <>();
    private Ubicacion ubicacion;
    
    public Habitacion(int numero) {
        this.numero = numero;
    }

    public Habitacion() {
       
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
    
    
     public void mostrar (JTable parammodel1) {
     
    Conexion objetoconexion1 = new Conexion();    
    
    DefaultTableModel modelo1= new DefaultTableModel();
    
    String sql="";
    
    
    modelo1.addColumn("ID");
    modelo1.addColumn("Numero");
    
    
    parammodel1.setModel(modelo1);
    
    sql ="select * from habitacion;";
    
    //arreglo que recorre tabla para mostrar datos
    
    String [] datos1 = new String[2];
    //Ejecutar consulta
    Statement st;
    
    try {
        st = objetoconexion1.establecerConexion().createStatement();
        
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){   
             datos1[0] = rs.getString(1);
             datos1[1] = rs.getString(2);
             
             modelo1.addRow(datos1);
        
        }
        
        parammodel1.setModel(modelo1);
        
    } catch (Exception e){
        
     JOptionPane.showMessageDialog(null, "error" + e.toString());
    }
       
}
     
     
     
     public void insertar (JTextField numero){
        
        //setNumero(numero.getText());
        //int entero = Integer.parseInt(numero.getText());
        setNumero(Integer.parseInt(numero.getText()));
        
        Conexion conexion = new Conexion();
        
        String consulta = "insert into habitacion (numero) values (?);";
        
        try{
            CallableStatement cs = conexion.establecerConexion().prepareCall(consulta);
            cs.setInt(1 , getNumero());
           
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se insertó correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se insertó correctamente");
        }
        
    }
    
 
     public void eliminar (JTextField id){
    
        setId(Integer.parseInt(id.getText()));
        
        String consulta = "delete from habitacion where id=?;";
        
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
