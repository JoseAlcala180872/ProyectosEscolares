/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author YeisiPC
 */
public class Consultas {
    
    public Consultas() {
    }
    Conexion conex=new Conexion();
    public boolean autenticacion(String CorreoElectronico, String Contraseña){
        PreparedStatement pst=null;
        ResultSet rs=null;
        try{
            String consulta="select * from clientes where CorreoElectronico=? and  Contraseña=?";
            System.out.println("Consulta es "+consulta);
            pst=conex.getConexion().prepareStatement(consulta);
            pst.setString(1,CorreoElectronico);
            pst.setString(2, Contraseña);
            
            rs=pst.executeQuery();
            if (rs.next()) {                
                System.out.println("Usuario valido");
                return true;
            }
        }catch(Exception e){
            System.out.println("Error en "+e);
            
        }finally{
            try{
                if (conex.getConexion()!=null) 
                    conex.getConexion().close();
                if (pst!=null)pst.close(); 
                if(rs!=null)rs.close();
                
                
            }catch(Exception e){
                System.out.println("Error en "+e);
                
            }
        }
        return false;
    
}
    public boolean registrar(String Nombre_ApellidoP_ApellidoM, String CorreoElectronico,String  Contraseña){
        PreparedStatement pst=null;
        try{
            String consulta="insert into clientes(Nombre_ApellidoP_ApellidoM, CorreoElectronico, Contraseña) values(?,?,?)";
            pst=conex.getConexion().prepareStatement(consulta);
            pst.setString(1,Nombre_ApellidoP_ApellidoM);
            pst.setString(2, CorreoElectronico);
            pst.setString(3,Contraseña);
           
                    
            if (pst.executeUpdate()==1) {
                return true;
            }            
            
        }catch(Exception e){
            System.out.println("Error en "+e);
        }finally{
            try{
            if (conex.getConexion()!=null) conex.getConexion().close(); 
            if(pst!=null)pst.close();;
            }catch(Exception e){
                System.out.println("Error en "+e);
            }    
            
        }
        return false;
    }
    
    public boolean eliminar(String Nombre_ApellidoP_ApellidoM,String  Contraseña) {
        PreparedStatement pst=null;
        
        try{
            String consulta="delete from clientes where Nombre_ApellidoP_ApellidoM=? and Contraseña=?";
            pst=conex.getConexion().prepareStatement(consulta);            
            pst.setString(1,Nombre_ApellidoP_ApellidoM);
            pst.setString(2,Contraseña);
                                
            int filasAfectadas = pst.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);            
            
        }catch(Exception e){
            System.out.println("Error en "+e);
        }finally{
            try{
            if (conex.getConexion()!=null) conex.getConexion().close(); 
            if(pst!=null)pst.close();;
            }catch(Exception e){
                System.out.println("Error en "+e);
            }    
            
        }
        return false;
    }
}