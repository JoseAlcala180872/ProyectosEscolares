/**
 * La clase TieneCarrito.java representa la relación entre un carrito de compras
 * y sus elementos en la tienda Merch Fórmula 1; Contiene detalles como el ID
 * del carrito, el estado del carrito, el ID de compra y el ID del producto
 * relacionado.
 *
 * Fecha de terminación y ult. versión: 24 de Septiembre de 2023, 23:30 hrs.
 */
package entidades;

/**
 * @author(s) Jesús Yalam Guzmán Villa 233350, 
 * Daniel Alejandro Castro Félix 235294, 
 * José Carlos Alcalá Ruíz 180872, 
 * Fernando Tadeo Zayas Bernal 228310.
 */
public class TieneCarrito {

    private int ID_Carrito;    // Almacena el ID del carrito.
    private String Estado;     // Almacena el estado del carrito.
    private int CompraID;      // Almacena el ID de la compra relacionada.
    private int ID_Producto;   // Almacena el ID del producto relacionado.

    /**
     * Constructor de la clase `TieneCarrito` que inicializa el valor de sus
     * variables.
     *
     * @param ID_Carrito El ID del carrito.
     * @param Estado El estado del carrito.
     * @param CompraID El ID de la compra relacionada.
     * @param ID_Producto El ID del producto relacionado.
     */
    public TieneCarrito(int ID_Carrito, String Estado, int CompraID, int ID_Producto) {
        this.ID_Carrito = ID_Carrito;
        this.Estado = Estado;
        this.CompraID = CompraID;
        this.ID_Producto = ID_Producto;
    }

    /**
     * Obtiene el ID del carrito.
     *
     * @return El ID del carrito.
     */
    public int getID_Carrito() {
        return ID_Carrito;
    }

    /**
     * Establece el ID del carrito.
     *
     * @param ID_Carrito El nuevo ID del carrito.
     */
    public void setID_Carrito(int ID_Carrito) {
        this.ID_Carrito = ID_Carrito;
    }

    /**
     * Obtiene el estado del carrito.
     *
     * @return El estado del carrito.
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * Establece el estado del carrito.
     *
     * @param Estado El nuevo estado del carrito.
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    /**
     * Obtiene el ID de la compra relacionada.
     *
     * @return El ID de la compra relacionada.
     */
    public int getCompraID() {
        return CompraID;
    }

    /**
     * Establece el ID de la compra relacionada.
     *
     * @param CompraID El nuevo ID de la compra relacionada.
     */
    public void setCompraID(int CompraID) {
        this.CompraID = CompraID;
    }

    /**
     * Obtiene el ID del producto relacionado.
     *
     * @return El ID del producto relacionado.
     */
    public int getID_Producto() {
        return ID_Producto;
    }

    /**
     * Establece el ID del producto relacionado.
     *
     * @param ID_Producto El nuevo ID del producto relacionado.
     */
    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }
}
