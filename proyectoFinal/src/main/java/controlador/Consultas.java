/**
 * La clase Consultas.java se encarga de realizar consultas y operaciones 
 * relacionadas con la autenticación y registro de clientes en la base de datos;
 * 
 * Fecha de terminación y ult. versión: 24 de Septiembre de 2023, 23:30 hrs.
 */
package controlador;

import entidades.Compras;
import entidades.Productos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author(s) Jesús Yalam Guzmán Villa 233350
 */
public class Consultas extends Conexion {

    /**
     * Constructor de la clase `Consultas`. Crea una instancia de la clase y establece una conexión a la base de datos.
     */
    public Consultas() {
    }
    Conexion conex = new Conexion();

    /**
     * Autentica a un usuario utilizando su dirección de correo electrónico y contraseña.
     *
     * @param CorreoElectronico La dirección de correo electrónico del usuario.
     * @param Contraseña La contraseña del usuario.
     * @return true si la autenticación es exitosa, false en caso contrario.
     */
    public boolean autenticacion(String CorreoElectronico, String Contraseña) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "select * from clientes where CorreoElectronico=? and  Contraseña=?";
            System.out.println("Consulta es " + consulta);
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, CorreoElectronico);
            pst.setString(2, Contraseña);

            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Usuario valido");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error en " + e);

        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }

            } catch (Exception e) {
                System.out.println("Error en " + e);

            }
        }
        return false;

    }

    /**
     * Registra a un nuevo cliente en la base de datos.
     *
     * @param Nombre_ApellidoP_ApellidoM El nombre y apellidos del nuevo cliente.
     * @param CorreoElectronico La dirección de correo electrónico del nuevo cliente.
     * @param Contraseña La contraseña del nuevo cliente.
     * @return true si el registro es exitoso, false en caso contrario.
     */
    public boolean registrar(String Nombre_ApellidoP_ApellidoM, String CorreoElectronico, String Contraseña) {
        PreparedStatement pst = null;
        try {
            String consulta = "insert into clientes(Nombre_ApellidoP_ApellidoM, CorreoElectronico, Contraseña) values(?,?,?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, Nombre_ApellidoP_ApellidoM);
            pst.setString(2, CorreoElectronico);
            pst.setString(3, Contraseña);

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error en " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    conex.getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                };
            } catch (Exception e) {
                System.out.println("Error en " + e);
            }

        }
        return false;
    }

    /**
     * Elimina a un cliente de la base de datos.
     *
     * @param Nombre_ApellidoP_ApellidoM El nombre y apellidos del cliente a eliminar.
     * @param Contraseña La contraseña del cliente a eliminar.
     * @return true si la eliminación es exitosa, false en caso contrario.
     */
    public boolean eliminar(String Nombre_ApellidoP_ApellidoM, String Contraseña) {
        PreparedStatement pst = null;

        try {
            String consulta = "delete from clientes where Nombre_ApellidoP_ApellidoM=? and Contraseña=?";
            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, Nombre_ApellidoP_ApellidoM);
            pst.setString(2, Contraseña);

            int filasAfectadas = pst.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (Exception e) {
            System.out.println("Error en " + e);
        } finally {
            try {
                if (conex.getConexion() != null) {
                    conex.getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                };
            } catch (Exception e) {
                System.out.println("Error en " + e);
            }

        }
        return false;
    }

    /**
     * Metodo que actualiza los datos de un cliente de la base de datos. El
     * método utiliza el nombre y apellidos, así como la contraseña para validar
     * al cliente, una vez validado actualiza sus datos poniendo nuevos nombres
     * y apellidos, correo y contraseña.
     *
     * @param Nombre_ApellidoP_ApellidoM nombre y apellidos del cliente.
     * @param contraseña contraseña del cliente.
     * @param nuevoNombre_ApellidoP_ApellidoM nombre y apellidos que se cambiará
     * el clliente.
     * @param nuevoCorreoElectronico correo electronico al que se cambiará el
     * cliente.
     * @param nuevaContraseña nueva contraseña a la que actualizará el cliente.
     * @return TRUE: Actualizá los datos del cliente. FALSE: Despliega un
     * mensaje de advertencia, ya sea que el metodo haya fallado o que el
     * usuario haya añadido mal los datos necesarios para validar al cliente.
     */
    public boolean actualizar(String Nombre_ApellidoP_ApellidoM, String contraseña,
            String nuevoNombre_ApellidoP_ApellidoM, String nuevoCorreoElectronico, String nuevaContraseña) {
        PreparedStatement pst = null;

        try {
            String consulta = "UPDATE clientes SET Nombre_ApellidoP_ApellidoM=?, CorreoElectronico=?, Contraseña=? "
                    + "WHERE Nombre_ApellidoP_ApellidoM=? AND contraseña=?";
            pst = getConexion().prepareCall(consulta);
            pst.setString(1, nuevoNombre_ApellidoP_ApellidoM); //nombre y apellidos actualizados.
            pst.setString(2, nuevoCorreoElectronico); //correo electronico actualizado.
            pst.setString(3, nuevaContraseña); //contraseña actualizada.
            pst.setString(4, Nombre_ApellidoP_ApellidoM); //nombre y apellidos que se utilizaron para la verificación de usuario.
            pst.setString(5, contraseña); //contraseña que se utilizó para la verificación del usuario.

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas == 1) {
                System.out.println("Se actualizó al cliente con exito");
                System.out.println("su nuevo usuario es:");
                System.out.println("nombre y apellido: " + nuevoNombre_ApellidoP_ApellidoM);
                System.out.println("nuevo correo " + nuevoCorreoElectronico);
                System.out.println("nueva contraseña: " + nuevaContraseña);
                return true;
            } else {
                System.out.println("Hubo un error al actualizar al cliente");
                System.out.println("Verifique haber añadido de forma correcta sus datos de usuario (nombre, apellido y contraseña)");
            }

        } catch (Exception e) {
            System.out.println("Error en " + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println("Error en " + e);
            }
        }

        return false;
    }

    /**
     * Obtiene una lista de productos desde la base de datos y los almacena en
     * un ArrayList.
     *
     * @return Una lista de productos como objetos Productos.
     */
    public ArrayList<Productos> getProductos() {
        Conexion con = new Conexion(); // Crear una instancia de la clase Conexion
        Connection conexion = con.getConexion(); // Obtener la conexión a la base de datos
        Statement stmt;
        ResultSet rs;

        ArrayList<Productos> listaProductos = new ArrayList<>();

        try {
            stmt = conexion.createStatement();
            String consultaSQL = "SELECT ID_Producto, NombreProducto, Precio FROM Productos"; // Modificar la consulta SQL
            rs = stmt.executeQuery(consultaSQL);

            // Ahora puedes iterar a través del ResultSet para obtener los productos y agregarlos a tu ArrayList
            while (rs.next()) {
                // Obtener los valores de las columnas ID_Producto, NombreProducto y Precio
                int idProducto = rs.getInt("ID_Producto");
                String nombreProducto = rs.getString("NombreProducto");
                double precio = rs.getDouble("Precio");

                // Crear un objeto Productos y agregarlo a la lista
                Productos producto = new Productos();
                producto.setID_Producto(idProducto);
                producto.setNombreProducto(nombreProducto);
                producto.setPrecio(precio);

                listaProductos.add(producto);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja la excepción apropiadamente
        } finally {
            // Cierra la conexión a la base de datos
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Maneja la excepción apropiadamente
            }
        }

        return listaProductos;
    }

    /**
     * Inserta una compra en la tabla TieneCarrito.
     *
     * @param compraID El identificador de la compra.
     * @param productoID El identificador del producto comprado.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarCompraEnTieneCarrito(int compraID, int productoID) {
        try (Connection conexion = getConexion(); PreparedStatement statement = conexion.prepareStatement("INSERT INTO TieneCarrito (Estado, CompraID, ID_Producto) VALUES (?, ?, ?)")) {

            statement.setString(1, "Pendiente"); // Cambia el estado según tus necesidades
            statement.setInt(2, compraID);
            statement.setInt(3, productoID);

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja la excepción apropiadamente
        }

        return false;
    }
// Función para insertar un registro en la tabla TieneCarrito

    /**
     * Inserta un registro en la tabla TieneCarrito para representar un producto
     * en el carrito de un cliente.
     *
     * @param clienteID El identificador del cliente.
     * @param productoID El identificador del producto.
     * @param cantidad La cantidad de productos en el carrito.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    private boolean insertarEnTieneCarrito(int clienteID, int productoID, int cantidad) {
        String estado = "Agregado"; // Definir el estado apropiado

        try {
            Connection conexion = getConexion(); // Reemplaza con tu lógica para obtener la conexión a la base de datos
            String sql = "INSERT INTO TieneCarrito (Estado, CompraID, ID_Producto) VALUES (?, ?, ?)";

            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, estado);
            statement.setInt(2, clienteID);
            statement.setInt(3, productoID);

            // Ejecutar la inserción
            int filasAfectadas = statement.executeUpdate();

            // Cerrar la conexión
            statement.close();
            conexion.close();

            // Devolver true si se insertó correctamente
            return filasAfectadas > 0;
        } catch (SQLException e) {
            // Manejar cualquier excepción de SQL aquí
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Registra un carrito en la base de datos.
     *
     * @param ID_Producto El identificador del producto en el carrito.
     * @param cantidadProductos La cantidad de productos en el carrito.
     * @param importe El importe total de la compra en formato de cadena.
     * @return true si el registro del carrito fue exitoso, false en caso
     * contrario.
     */
    public boolean registrarCarrito(int ID_Producto, int cantidadProductos, String importe) {
        PreparedStatement pst = null;
        try {
            String consulta = "insert into tienecarrito(ID_Producto, cantidadProductos, importe) values(?,?,?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, ID_Producto);
            pst.setInt(2, cantidadProductos);
            pst.setString(3, importe);

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error en " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    conex.getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                };
            } catch (Exception e) {
                System.out.println("Error en " + e);
            }

        }
        return false;
    }

    /**
     * Registra una compra en la base de datos.
     *
     * @param idCliente El identificador del cliente que realizó la compra.
     * @param idCarrito El identificador del carrito de compras asociado a la
     * compra.
     * @param montoTotal El monto total de la compra en formato de cadena (puede
     * contener decimales).
     * @return true si la compra se registró exitosamente en la base de datos,
     * false en caso contrario.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     */
    public boolean registrarCompra(int idCliente, int idCarrito, String montoTotal) {
        PreparedStatement pst = null;
        try {
            String consulta = "insert into compras(ID_cliente, idCarrito, montoTotal) values(?,?,?)";
            pst = getConexion().prepareStatement(consulta);
            pst.setInt(1, idCliente);
            pst.setInt(2, idCarrito);
            pst.setString(3, montoTotal);

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error en " + e);
        } finally {
            try {
                if (getConexion() != null) {
                    conex.getConexion().close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en " + e);
            }

        }
        return false;
    }
}
