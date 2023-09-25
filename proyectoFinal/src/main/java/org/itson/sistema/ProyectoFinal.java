/**
 * La clase ProyectoFinal.java es la clase principal de la aplicación
 * y se encarga de iniciar el programa;
 * 
 * Fecha de terminación y ult. versión: 24 de Septiembre de 2023, 23:30 hrs.
 */
package org.itson.sistema;

import controlador.Conexion;
import controlador.Consultas;
import interfaz.frmLogin;
/**
 * @author(s) Jesús Yalam Guzmán Villa 233350, 
 * Daniel Alejandro Castro Félix 235294, 
 * José Carlos Alcalá Ruíz 180872, 
 * Fernando Tadeo Zayas Bernal 228310.
 */
public class ProyectoFinal {

    /**
     * El método `main` es el punto de entrada de la aplicación. Inicia la conexión a la base de datos, realiza operaciones
     * de prueba y muestra la ventana de inicio de sesión.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en este contexto).
     */
    public static void main(String[] args) {
        Conexion conex = new Conexion();
        conex.getConexion();
        Consultas con = new Consultas();
        // Ejecutar operaciones de prueba:
        // con.registrar("Mariana Meza", "mari@gmail.com", "1234");
        // con.actualizar("Mariana Meza Echaverria", "mari3@gmail.com", "123456", "Mariana Meza Padilla", "mari@gmail.com", "asd");
        // con.autenticacion("carlos@gmail.com", "asd");

        frmLogin login = new frmLogin();
        login.setVisible(true);
    }
}
