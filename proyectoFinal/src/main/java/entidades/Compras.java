/**
 * La clase Compras.java representa información sobre compras de productos en la
 * tienda Merch Fórmula 1; Contiene detalles como el ID de compra, la fecha y 
 * hora de la compra, el ID del cliente, el ID del producto y la cantidad 
 * de productos comprados.
 * 
 * Fecha de terminación y ult. versión: 24 de Septiembre de 2023, 23:30 hrs.
 */
package entidades;

import java.sql.Timestamp;

/**
 * @author(s) Jesús Yalam Guzmán Villa 233350, 
 * Daniel Alejandro Castro Félix 235294, 
 * José Carlos Alcalá Ruíz 180872, 
 * Fernando Tadeo Zayas Bernal 228310.
 */
public class Compras {
    
    private int compraID;       // Almacena el ID de la compra.
    private Timestamp fechaHora; // Almacena la fecha y hora de la compra.
    private int IDCliente;      // Almacena el ID del cliente que realizó la compra.
    private int IDProducto;     // Almacena el ID del producto comprado.
    private int cantidad;       // Almacena la cantidad de productos comprados en esta compra.

    /**
     * Obtiene el ID de la compra.
     *
     * @return El ID de la compra.
     */
    public int getCompraID() {
        return compraID;
    }

    /**
     * Establece el ID de la compra.
     *
     * @param compraID El nuevo ID de la compra.
     */
    public void setCompraID(int compraID) {
        this.compraID = compraID;
    }

    /**
     * Obtiene la fecha y hora de la compra.
     *
     * @return La fecha y hora de la compra.
     */
    public Timestamp getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la compra.
     *
     * @param fechaHora La nueva fecha y hora de la compra.
     */
    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el ID del cliente que realizó la compra.
     *
     * @return El ID del cliente.
     */
    public int getIDCliente() {
        return IDCliente;
    }

    /**
     * Establece el ID del cliente que realizó la compra.
     *
     * @param IDCliente El nuevo ID del cliente.
     */
    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    /**
     * Obtiene el ID del producto comprado.
     *
     * @return El ID del producto comprado.
     */
    public int getIDProducto() {
        return IDProducto;
    }

    /**
     * Establece el ID del producto comprado.
     *
     * @param IDProducto El nuevo ID del producto comprado.
     */
    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }

    /**
     * Obtiene la cantidad de productos comprados en esta compra.
     *
     * @return La cantidad de productos comprados.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de productos comprados en esta compra.
     *
     * @param cantidad La nueva cantidad de productos comprados.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

