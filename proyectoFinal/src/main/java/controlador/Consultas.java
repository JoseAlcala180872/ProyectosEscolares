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
    
    /**
     * Metodo que actualiza los datos de un cliente de la base de datos.
     * El método utiliza el nombre y apellidos, así como la contraseña para validar al cliente,
     * una vez validado actualiza sus datos poniendo nuevos nombres y apellidos,
     * correo y contraseña.
     * @param Nombre_ApellidoP_ApellidoM nombre y apellidos del cliente.
     * @param CorreoElectronico correo electronico del cliente.
     * @param contraseña contraseña del cliente.
     * @param nuevoNombre_ApellidoP_ApellidoM nombre y apellidos que se cambiará el clliente.
     * @param nuevoCorreoElectronico correo electronico al que se cambiará el cliente.
     * @param nuevaContraseña nueva contraseña a la que actualizará el cliente.
     * @return TRUE: Actualizá los datos del cliente. FALSE: Despliega un mensaje de
     * advertencia, ya sea que el metodo haya fallado o que el usuario haya añadido mal
     * los datos necesarios para validar al cliente.
     */
    public boolean actualizar(String Nombre_ApellidoP_ApellidoM, String CorreoElectronico, String contraseña,
            String nuevoNombre_ApellidoP_ApellidoM, String nuevoCorreoElectronico, String nuevaContraseña){
        PreparedStatement pst = null;
        
        try{
            String consulta = "UPDATE clientes SET Nombre_ApellidoP_ApellidoM=?, CorreoElectronico=?, Contraseña=? "
                    + "WHERE Nombre_ApellidoP_ApellidoM=? AND contraseña=?";
            pst = conex.getConexion().prepareCall(consulta);
            pst.setString(1, nuevoNombre_ApellidoP_ApellidoM); //nombre y apellidos actualizados.
            pst.setString(2, nuevoCorreoElectronico); //correo electronico actualizado.
            pst.setString(3, nuevaContraseña); //contraseña actualizada.
            pst.setString(4, Nombre_ApellidoP_ApellidoM); //nombre y apellidos que se utilizaron para la verificación de usuario.
            pst.setString(5, contraseña); //contraseña que se utilizó para la verificación del usuario.
            
            int filasAfectadas = pst.executeUpdate();
            
            if(filasAfectadas == 1){
                System.out.println("Se actualizó al cliente con exito");
                System.out.println("su nuevo usuario es:");
                System.out.println("nombre y apellido: " +nuevoNombre_ApellidoP_ApellidoM);
                System.out.println("nuevo correo " +nuevoCorreoElectronico);
                System.out.println("nueva contraseña: " +nuevaContraseña);
                return true;
            }else{
                System.out.println("Hubo un error al actualizar al cliente");
                System.out.println("Verifique haber añadido de forma correcta sus datos de usuario (nombre, apellido y contraseña)");
            }
            
        }catch(Exception e){
            System.out.println("Error en " +e);
        }finally{
            try{
                if(pst != null) pst.close();
            }catch(Exception e){
                System.out.println("Error en " +e);
            }
        }
        
        return false;
    }
}