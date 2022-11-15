/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaInternacionObjects;

import SistemaInternacionConexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Franco
 */
public class Internacion {
    private int id;
    private String paciente;
    private String diagnostico;
    
    private Cama cama;
    
    public Internacion(){}
    
    public Internacion(String paciente, String diagnostico) {
        this.paciente = paciente;
       
        this.diagnostico = diagnostico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getPaciente() {
        return paciente;
    }

    
    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    
    
    
    
    //Metodos para conectarse con la BD
      public void mostrar (JTable parammodel) {
     
    Conexion objetoconexion = new Conexion();    
    
    DefaultTableModel modelo= new DefaultTableModel();
    
    String sql=" ";
    
    modelo.addColumn("Id");
    modelo.addColumn("Paciente");
    modelo.addColumn("Diagnostico");
    
    
    parammodel.setModel(modelo);
    
    sql ="select * from internacion;";
    
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
    
    public void insertar (JTextField paciente, JTextField diagnostico){
        
        setPaciente(paciente.getText());
        setDiagnostico(diagnostico.getText());
        
        Conexion conexion = new Conexion();
        
        String consulta = "insert into internacion (paciente,diagnostico) values (?,?);";
        
        try{
            CallableStatement cs = conexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getPaciente());
            cs.setString(2, getDiagnostico());
           
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se insertó correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se insertó correctamente");
        }
        
    }
    
    
    
    public void eliminar (JTextField id){
    
        setId(Integer.parseInt(id.getText()));
        
        String consulta = "delete from internacion where id=?;";
        
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
