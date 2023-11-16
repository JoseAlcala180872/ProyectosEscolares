/**
 * La clase Productos.java representa la información de un producto en la tienda
 * Merch Fórmula 1; Contiene detalles como el ID del producto, la cantidad en 
 * stock, el precio, la descripción, el nombre del producto y el ID de la 
 * categoría a la que pertenece.
 * 
 * Fecha de terminación y ult. versión: 24 de Septiembre de 2023, 23:30 hrs.
 */
package entidades;

/**
 * @author(s) Jesús Yalam Guzmán Villa 233350, 
 */
public class Productos {

    private int ID_Producto; // Almacena el ID del producto.
    private int Cantidad; // Almacena la cantidad del producto.
    private double Precio; // Almacena el precio del producto.
    private String Descripcion; // Almacena la descripció del producto.
    private String NombreProducto; // Almacena el nombre del producto.
    private int ID_Categoria; // Almacena el ID de la categoría del producto.

    /**
     * Obtiene el ID del producto.
     *
     * @return El ID del producto.
     */
    public int getID_Producto() {
        return ID_Producto;
    }

    /**
     * Obtiene la cantidad en stock del producto.
     *
     * @return La cantidad en stock del producto.
     */
    public int getCantidad() {
        return Cantidad;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public double getPrecio() {
        return Precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param Precio El nuevo precio del producto.
     */
    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return La descripción del producto.
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param Descripcion La nueva descripción del producto.
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombreProducto() {
        return NombreProducto;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param NombreProducto El nuevo nombre del producto.
     */
    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    /**
     * Obtiene el ID de la categoría a la que pertenece el producto.
     *
     * @return El ID de la categoría del producto.
     */
    public int getID_Categoria() {
        return ID_Categoria;
    }

    /**
     * Establece el ID de la categoría del producto.
     *
     * @param ID_Categoria El nuevo ID de la categoría del producto.
     */
    public void setID_Categoria(int ID_Categoria) {
        this.ID_Categoria = ID_Categoria;
    }

    /**
     * Establece el ID del producto.
     *
     * @param idProducto El nuevo ID del producto.
     */
    public void setID_Producto(int idProducto) {
        this.ID_Producto = idProducto;
    }
}
