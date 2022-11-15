/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaInternacionConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


/**
 *
 * @author Franco
 */
public class Conexion {
    
   Connection conectar = null;
    
    
    String usuario = "postgres";
    String pass = ""; //agregar contraseña
    //String pass = (String) enviromentsVariables.get("POSTGRES_PASSWORD");
    
    String bd = "sistemaInternacion";
    String ip = "localhost";
    String puerto = "5432";
    
    //cadena de conexion
    String url = "jdbc:postgresql://localhost:5432/"+bd;
    String jdbc_driver = "org.postgresql.Driver"; 
    
    public Connection establecerConexion() {
     
            try{
                //Setterar el driver a la bd
                Class.forName(jdbc_driver);
                
                //establecer conexion
                conectar = DriverManager.getConnection(url,usuario,pass);
                JOptionPane.showMessageDialog(null, "Se conecto correctamente a la BD");
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conecto a la BD");
            }
            
    return conectar;
    }

}
     
      
       
