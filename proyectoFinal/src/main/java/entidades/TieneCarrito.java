/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author yalam
 */
public class TieneCarrito {
    private int ID_Carrito;
    private String Estado;
    private int CompraID;
    private int ID_Producto;

    // Constructor
    public TieneCarrito(int ID_Carrito, String Estado, int CompraID, int ID_Producto) {
        this.ID_Carrito = ID_Carrito;
        this.Estado = Estado;
        this.CompraID = CompraID;
        this.ID_Producto = ID_Producto;
    }

    // Métodos get y set para ID_Carrito
    public int getID_Carrito() {
        return ID_Carrito;
    }

    public void setID_Carrito(int ID_Carrito) {
        this.ID_Carrito = ID_Carrito;
    }

    // Métodos get y set para Estado
    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    // Métodos get y set para CompraID
    public int getCompraID() {
        return CompraID;
    }

    public void setCompraID(int CompraID) {
        this.CompraID = CompraID;
    }

    // Métodos get y set para ID_Producto
    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }
}