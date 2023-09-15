/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author YeisiPC
 */
public class Conexion {
    private String USERNAME="";
    private String PASSWORD="";
    private String HOST="localhost";
    private String PORT="3306";
    private String DATABASE="tienda";
    private String CLASSNAME="com.mysql.cj.jdbc.Driver";
    private String URL="jdbc:mysql://localhost:3306/sistema";
    private Connection con;
    
    
    public Conexion(){
        try{
            Class.forName(CLASSNAME);
            con=DriverManager.getConnection(URL,USERNAME, PASSWORD);
        }catch(ClassNotFoundException e){
            System.err.println("Error en "+e);
        }catch(SQLException e){
             System.err.println("Error en "+e);
        }
    }
    
    public Connection getConexion(){
        return con;
    }
}