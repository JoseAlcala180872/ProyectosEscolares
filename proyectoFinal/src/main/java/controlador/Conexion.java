/**
 * La clase Conexion.java proporciona una conexión a una base de datos MySQL
 * utilizando JDBC (Java Database Connectivity);
 * 
 * Fecha de terminación y ult. versión: 24 de Septiembre de 2023, 23:30 hrs.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author(s) Jesús Yalam Guzmán Villa 233350, 

 */
public class Conexion {

    private String USERNAME = "root";
    private String PASSWORD = "12345";
    private String HOST = "localhost";
    private String PORT = "3306";
    private String DATABASE = "tienda";
    private String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost:3306/tienda";
    private Connection con;

    /**
     * Constructor de la clase `Conexion`. Establece una conexión con la base de
     * datos utilizando los parámetros definidos en esta clase.
     */
    public Conexion() {
        try {
            Class.forName(CLASSNAME);  // Carga la clase del controlador JDBC.
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);  // Establece la conexión a la base de datos.
        } catch (ClassNotFoundException e) {
            System.err.println("Error en " + e);  // Manejo de errores en caso de no encontrar la clase del controlador.
        } catch (SQLException e) {
            System.err.println("Error en " + e);  // Manejo de errores en caso de problemas de conexión.
        }
    }

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return Objeto de conexión a la base de datos.
     */
    public Connection getConexion() {
        return con;
    }
}
