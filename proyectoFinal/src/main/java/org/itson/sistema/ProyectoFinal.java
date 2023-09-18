/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.sistema;

import controlador.Conexion;
import controlador.Consultas;

/**
 *
 * @author YeisiPC
 */
public class ProyectoFinal {

    public static void main(String[] args) {
        Conexion conex=new Conexion();
        conex.getConexion();
        Consultas con=new Consultas();
        con.registrar("Mariana Meza", "mari@gmail.com", "1234");
    }
}
