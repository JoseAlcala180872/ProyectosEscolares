/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author yalam
 */
public class Productos {

    private int ID_Producto;
    private int Cantidad;
    private double Precio;
    private String Descripcion;
    private String NombreProducto;
    private int ID_Categoria;

    /**
     * @return the ID_Producto
     */
    public int getID_Producto() {
        return ID_Producto;
    }

    /**
     * @return the Cantidad
     */
    public int getCantidad() {
        return Cantidad;
    }

    /**
     * @return the Precio
     */
    public double getPrecio() {
        return Precio;
    }

    /**
     * @param Precio the Precio to set
     */
    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the NombreProducto
     */
    public String getNombreProducto() {
        return NombreProducto;
    }

    /**
     * @param NombreProducto the NombreProducto to set
     */
    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    /**
     * @return the ID_Categoria
     */
    public int getID_Categoria() {
        return ID_Categoria;
    }

    /**
     * @param ID_Categoria the ID_Categoria to set
     */
    public void setID_Categoria(int ID_Categoria) {
        this.ID_Categoria = ID_Categoria;
    }

    public void setID_Producto(int idProducto) {
     this.ID_Producto = idProducto;
    }

}
