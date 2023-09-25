/**
 * La clase frmTiendaF1.java es una ventana de la aplicación que
 * representa una tienda de productos relacionados con Fórmula 1;
 * Permite al usuario seleccionar y comprar productos de Fórmula 1.
 *
 * Fecha de terminación y ult. versión: 24 de Septiembre de 2023, 23:30 hrs.
 */
package interfaz;

import controlador.Conexion;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import entidades.Productos;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import controlador.Consultas;
import entidades.TieneCarrito;
import entidades.Compras;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author(s) Jesús Yalam Guzmán Villa 233350, 
 * Daniel Alejandro Castro Félix 235294, 
 * José Carlos Alcalá Ruíz 180872, 
 * Fernando Tadeo Zayas Bernal 228310.
 */
public class frmTiendaF1 extends javax.swing.JFrame {

    double precio = 0;
    int cantidad = 0;

    /**
     * Crea el formulario o frame de iniciar sesión, también le establece un
     * título.
     */
    public frmTiendaF1() {
        initComponents();
        this.setTitle("Tienda Merch Fórmula 1");
//        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/f1"));
//        this.setIconImage(icono);

//        lblLogo.setIcon(new ImageIcon(icono.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH)));
        this.setSize(1920, 1080);
        this.setLocationRelativeTo(null);
        // Llamar a la función getProductos y llenar el JComboBox
        cargarProductosEnComboBox();
    }

    /**
     * El método mostrar() se encarga de agregar un producto al carrito de
     * compras y mostrarlo en una tabla; Se obtiene el ID del producto
     * seleccionado desde un JComboBox y la cantidad ingresada desde un
     * JSpinner; Luego se realiza una consulta para obtener los detalles del
     * producto y se muestra en la tabla.
     *
     * @see Conexion
     * @see Consultas
     */
    private void mostrar() {
        // Supongamos que comboBoxProductos contiene los nombres de los productos y sus IDs corresponden al índice en el JComboBox.

        // Obtén el ID del producto seleccionado del JComboBox.
        int selectedProductID = cboProducto.getSelectedIndex(); // Asumiendo que los IDs son los índices.

        // Obtén la cantidad ingresada por el usuario desde un Jspinner.
        int selectedQuantity = (int) spnCantidad.getValue();

        if (selectedProductID >= 0 && selectedQuantity > 0) {
            // Crear un modelo de tabla si aún no lo has hecho.
            DefaultTableModel tableModel = new DefaultTableModel();

            // Si ya tienes datos en la tabla, puedes obtenerlos y agregarlos al modelo existente.
            if (tblProductos.getModel() instanceof DefaultTableModel) {
                tableModel = (DefaultTableModel) tblProductos.getModel();
            } else {
                // Si la tabla aún no tiene un modelo, asigna el nuevo modelo.
                // Agregar las columnas a la tabla
                tableModel.addColumn("ID Producto");
                tableModel.addColumn("Nombre Producto");
                tableModel.addColumn("Descripción");
                tableModel.addColumn("Cantidad Productos");
                tableModel.addColumn("Precio");
            }

            // Ahora, puedes agregar la fila correspondiente al producto seleccionado y la cantidad ingresada.
            // Debes realizar una consulta para obtener los datos del producto desde la base de datos.
            try {
                Conexion con = new Conexion();
                Connection conexion = con.getConexion();
                Statement statement = conexion.createStatement();

                String query = "SELECT p.ID_Producto, p.NombreProducto, p.Descripcion, c.cantidadProductos, c.importe "
                        + "FROM productos p "
                        + "JOIN tienecarrito c ON p.ID_Producto = c.ID_Producto "
                        + "WHERE p.ID_Producto = " + selectedProductID;
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    Object[] row = {
                        selectedProductID,
                        resultSet.getString("NombreProducto"),
                        resultSet.getString("Descripcion"),
                        selectedQuantity, // Usar la cantidad ingresada en lugar de la de la base de datos.
                        resultSet.getDouble("importe")
                    };
                    tableModel.insertRow(0, row);
                } else {
                    // Si no se encontraron registros, puedes mostrar un mensaje o realizar alguna otra acción.
                    JOptionPane.showMessageDialog(this, "No se encontraron registros para el producto seleccionado, por favor intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Asigna el modelo de tabla al JTable.
            tblProductos.setModel(tableModel);
        }
    }

    /**
     * El método obtenerPrecioDelProducto(String nombreProducto) recupera el
     * precio de un producto según su nombre.
     *
     * @param nombreProducto El nombre del producto del cual se desea obtener el
     * precio.
     * @return El precio del producto o 0.0 si no se encuentra.
     * @see Consultas
     */
    private double obtenerPrecioDelProducto(String nombreProducto) {
        Consultas consultas = new Consultas(); // Crea una instancia de la clase Consultas
        ArrayList<Productos> listaProductos = consultas.getProductos(); // Obtén la lista de productos

        // Itera a través de la lista de productos para encontrar el precio del producto seleccionado
        for (Productos producto : listaProductos) {
            if (producto.getNombreProducto().equals(nombreProducto)) {
                return producto.getPrecio();
            }
        }

        // Si el producto no se encuentra, devuelve 0.0 o maneja el error adecuadamente
        return 0.0;
    }

    /**
     * El método cargarProductosEnComboBox() llena un JComboBox con los nombres
     * de los productos.
     *
     * @see Consultas
     */
    private void cargarProductosEnComboBox() {
        Consultas consultas = new Consultas(); // Crear una instancia de la clase Consultas

        try {
            ArrayList<Productos> listaProductos = consultas.getProductos();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

            // Llenar el modelo del JComboBox con los nombres de los productos
            for (Productos producto : listaProductos) {
                model.addElement(producto.getNombreProducto());
            }

            cboProducto.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            // Maneja la excepción apropiadamente
            // Configurar el modelo del JSpinner para tener un valor mínimo de 0
            SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
            spnCantidad.setModel(spinnerModel);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        cboProducto = new javax.swing.JComboBox<>();
        spnCantidad = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lblPrecio = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblIva = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblImporte = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        botonConfirmarCompra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("IMPORTE:");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("PRECIO:");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("PRODUCTO:");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("CANTIDAD:");
        jLabel5.setToolTipText("");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        cboProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cboProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProducto.setToolTipText("");
        cboProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProductoActionPerformed(evt);
            }
        });

        spnCantidad.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        spnCantidad.setToolTipText("");
        spnCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnCantidadStateChanged(evt);
            }
        });

        tblProductos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Producto", "Nombre Producto", "Descripcion", "Cantidad", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setToolTipText("");
        tblProductos.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tblProductosComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                tblProductosComponentRemoved(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrecio.setText("$ 0.00 MXN");
        lblPrecio.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("SUBTOTAL:");
        jLabel7.setToolTipText("");

        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSubtotal.setText("$ 0.00 MXN");
        lblSubtotal.setToolTipText("");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("$ 0.00 MXN");
        lblTotal.setToolTipText("");

        lblIva.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblIva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIva.setText("$ 0.00 MXN");
        lblIva.setToolTipText("");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("TOTAL:");
        jLabel12.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("IVA:");
        jLabel13.setToolTipText("");

        lblImporte.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblImporte.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblImporte.setText("$ 0.00 MXN");
        lblImporte.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("Tienda F1 Merch");

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        botonConfirmarCompra.setText("Confirmar");
        botonConfirmarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(471, 471, 471)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel12)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRegresar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 574, Short.MAX_VALUE)
                                        .addComponent(jLabel13)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblIva)
                                    .addComponent(lblTotal)
                                    .addComponent(lblSubtotal)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2)))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblImporte)
                                    .addComponent(lblPrecio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregar))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonConfirmarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(lblPrecio))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(lblImporte)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblSubtotal))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblIva)
                    .addComponent(btnRegresar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblTotal))
                .addGap(18, 18, 18)
                .addComponent(botonConfirmarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * El método `cboProductoActionPerformed` se activa cuando se selecciona un
     * producto en el JComboBox. Calcula y muestra el importe, el IVA y el total
     * de la compra en etiquetas en función del producto seleccionado, la
     * cantidad ingresada y el precio del producto.
     *
     * @param evt El evento de acción que desencadena este método.
     * @see obtenerPrecioDelProducto
     */
    private void cboProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProductoActionPerformed
        int selectedIndex = cboProducto.getSelectedIndex(); // Obtiene el índice seleccionado

        if (selectedIndex != -1) {
            // Obtiene el nombre del producto seleccionado
            String selectedProductName = (String) cboProducto.getSelectedItem();

            // Obtiene el precio del producto por su nombre
            double selectedProductPrice = obtenerPrecioDelProducto(selectedProductName);

            // Obtiene la cantidad del JSpinner
            int selectedQuantity = (int) spnCantidad.getValue();

            // Calcula el importe multiplicando el precio por la cantidad
            double importe = selectedProductPrice * selectedQuantity;

            // Calcula el iva obteniendo el 16% del importe.
            double iva = importe * 0.16;

            // Calcula el total sumando el Iva y el Subtotal.
            double total = importe + iva;

            // Formatea los valores con dos decimales
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formattedImporte = "$ " + decimalFormat.format(importe) + " MXN";
            String formattedIva = "$ " + decimalFormat.format(iva) + " MXN";
            String formattedTotal = "$ " + decimalFormat.format(total) + " MXN";

            // Actualiza las etiquetas
            lblImporte.setText(formattedImporte);
            lblIva.setText(formattedIva);
            lblSubtotal.setText(formattedImporte);
            lblTotal.setText(formattedTotal);

            // Reinicia el JSpinner a 0
            spnCantidad.setValue(0);

        }
    }//GEN-LAST:event_cboProductoActionPerformed
    /**
     * El método spnCantidadStateChanged(javax.swing.event.ChangeEvent evt) se
     * activa cuando cambia el valor del JSpinner de cantidad; Calcula y muestra
     * el precio, el importe, el IVA y el total de la compra en etiquetas en
     * función del producto seleccionado y la cantidad ingresada.
     *
     * @param evt El evento de cambio de estado que desencadena este método.
     * @see obtenerPrecioDelProducto
     */
    private void spnCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnCantidadStateChanged

        int selectedIndex = cboProducto.getSelectedIndex(); // Obtiene el índice seleccionado

        if (selectedIndex != -1) {
            // Obtiene el nombre del producto seleccionado
            String selectedProductName = (String) cboProducto.getSelectedItem();

            // Obtiene el precio del producto por su nombre
            double selectedProductPrice = obtenerPrecioDelProducto(selectedProductName);

            // Obtiene la cantidad del JSpinner
            int selectedQuantity = (int) spnCantidad.getValue();

            // Calcula el importe multiplicando el precio por la cantidad
            double importe = selectedProductPrice * selectedQuantity;

            // Calcula el iva obteniendo el 16% del importe.
            double iva = importe * 0.16;

            // Calcula el total sumando el Iva y el Subtotal.
            double total = importe + iva;

            // Formatea los valores con dos decimales
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formattedPrice = decimalFormat.format(selectedProductPrice);
            String formattedImporte = decimalFormat.format(importe);
            String formattedIva = "$ " + decimalFormat.format(iva) + " MXN";
            String formattedTotal = "$ " + decimalFormat.format(total) + " MXN";
            // Actualiza la etiqueta lblPrecio con el precio del producto formateado
            lblPrecio.setText("$ " + formattedPrice + " MXN");

            // Actualiza la etiqueta lblImporte con el importe calculado y formateado
            lblImporte.setText("$ " + formattedImporte + " MXN");

            lblIva.setText(formattedIva);
            lblSubtotal.setText("$ " + formattedImporte + " MXN"); // Aquí puedes usar el mismo valor que lblImporte
            lblTotal.setText(formattedTotal);

            // Evita valores negativos en el JSpinner
            if (selectedQuantity < 0) {
                spnCantidad.setValue(0);
                selectedQuantity = 0;
            }
        }
    }//GEN-LAST:event_spnCantidadStateChanged
    /**
     * El método `btnRegresarActionPerformed` se activa cuando se presiona el
     * botón "Regresar"; Crea una instancia de la clase `frmLogin` (ventana de
     * inicio de sesión), la hace visible y oculta la ventana actual
     * (`frmTiendaF1`).
     *
     * @param evt El evento de acción que desencadena este método.
     * @see frmLogin
     */
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        frmLogin login = new frmLogin();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed
    /**
     * El método `obtenerIDProductoPorNombre` se utiliza para obtener el
     * ID_Producto de un producto dado su nombre; Itera a través de una lista de
     * productos y busca el producto por su nombre, devolviendo su ID_Producto
     * si se encuentra.
     *
     * @param nombreProducto El nombre del producto del cual se desea obtener el
     * ID_Producto.
     * @return El ID_Producto del producto o -1 si no se encuentra.
     * @see Consultas
     * @see Productos
     */
    private int obtenerIDProductoPorNombre(String nombreProducto) {
        Consultas consultas = new Consultas(); // Crea una instancia de la clase Consultas
        ArrayList<Productos> listaProductos = consultas.getProductos(); // Obtén la lista de productos

        // Itera a través de la lista de productos para encontrar el ID_Producto del producto seleccionado
        for (Productos producto : listaProductos) {
            if (producto.getNombreProducto().equals(nombreProducto)) {
                return producto.getID_Producto();
            }
        }

        // Si el producto no se encuentra, devuelve -1 o maneja el error adecuadamente
        return -1;
    }

    /**
     * El método `btnAgregarActionPerformed` se activa cuando se presiona el
     * botón "Agregar al carrito"; Calcula el importe de un producto en función
     * del producto seleccionado, la cantidad ingresada y el precio del
     * producto, formatea los valores y los muestra en etiquetas; También
     * registra el producto en el carrito y actualiza la tabla.
     *
     * @param evt El evento de acción que desencadena este método.
     * @see obtenerIDProductoPorNombre
     * @see obtenerPrecioDelProducto
     * @see mostrar
     * @see Consultas
     */
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        int selectedIndex = cboProducto.getSelectedIndex(); // Obtiene el índice seleccionado

        if (selectedIndex != -1) {
            // Obtiene el nombre del producto seleccionado
            String selectedProductName = (String) cboProducto.getSelectedItem();
            int selectedProductID = obtenerIDProductoPorNombre(selectedProductName);
            // Obtiene el precio del producto por su nombre
            double selectedProductPrice = obtenerPrecioDelProducto(selectedProductName);

            // Obtiene la cantidad del JSpinner
            int selectedQuantity = (int) spnCantidad.getValue();

            // Calcula el importe multiplicando el precio por la cantidad
            double importe = selectedProductPrice * selectedQuantity;

            // Formatea los valores con dos decimales
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formattedPrice = decimalFormat.format(selectedProductPrice);
            String formattedImporte = decimalFormat.format(importe);

            // Actualiza la etiqueta lblPrecio con el precio del producto formateado
            lblPrecio.setText("$ " + formattedPrice + " MXN");

            // Actualiza la etiqueta lblImporte con el importe calculado y formateado
            lblImporte.setText("$ " + formattedImporte + " MXN");
            // Evita valores negativos en el JSpinner
            if (selectedQuantity < 0) {
                spnCantidad.setValue(0);
                selectedQuantity = 0;
            }
            Consultas con = new Consultas();
            con.registrarCarrito(selectedProductID, selectedQuantity, formattedImporte);

            mostrar();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
    /**
     * Este botón no funciona.
     *
     * @param evt El evento de acción que desencadena este método.
     */
    private void botonConfirmarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarCompraActionPerformed
//
//        // Obtén el ID del producto seleccionado del JComboBox.
//        int selectedProductID = cboProducto.getSelectedIndex(); // Asumiendo que los IDs son los índices.
//
//        // Obtén la cantidad ingresada por el usuario desde un Jspinner.
//        int selectedQuantity = (int) spnCantidad.getValue();
//
//        if (selectedProductID >= 0 && selectedQuantity > 0) {
//            // Crear un modelo de tabla si aún no lo has hecho.
//            DefaultTableModel tableModel = new DefaultTableModel();
//            if (selectedProductID >= 0 && selectedQuantity > 0) {
//
//                // Si ya tienes datos en la tabla, puedes obtenerlos y agregarlos al modelo existente.
//                if (tblProductos.getModel() instanceof DefaultTableModel) {
//                    tableModel = (DefaultTableModel) tblProductos.getModel();
//                } else {
//                    // Si la tabla aún no tiene un modelo, asigna el nuevo modelo.
//                    // Agregar las columnas a la tabla
//                    tableModel.addColumn("ID Producto");
//                    tableModel.addColumn("Nombre Producto");
//                    tableModel.addColumn("Descripción");
//                    tableModel.addColumn("Cantidad Productos");
//                    tableModel.addColumn("Precio");
//                }
//
//                // Ahora, puedes agregar la fila correspondiente al producto seleccionado y la cantidad ingresada.
//                // Debes realizar una consulta para obtener los datos del producto desde la base de datos.
//                try {
//                    Conexion con = new Conexion();
//                    Connection conexion = con.getConexion();
//                    Statement statement = conexion.createStatement();
//
//                    String query = "SELECT p.ID_Producto, p.NombreProducto, p.Descripcion, c.cantidadProductos, c.importe "
//                            + "FROM productos p "
//                            + "JOIN tienecarrito c ON p.ID_Producto = c.ID_Producto "
//                            + "WHERE p.ID_Producto = " + selectedProductID;
//                    ResultSet resultSet = statement.executeQuery(query);
//
//                    if (resultSet.next()) {
//                        Object[] row = {
//                            selectedProductID,
//                            resultSet.getString("NombreProducto"),
//                            resultSet.getString("Descripcion"),
//                            selectedQuantity, // Usar la cantidad ingresada en lugar de la de la base de datos.
//                            resultSet.getDouble("importe")
//                        };
//                        tableModel.insertRow(0, row);
//                    } else {
//                        // Si no se encontraron registros, puedes mostrar un mensaje o realizar alguna otra acción.
//                        JOptionPane.showMessageDialog(this, "No se encontraron registros para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                // Crear una nueva tabla y asignarle el modelo de datos
//                JTable nuevaTabla = new JTable(tableModel);
//
//                // Luego, puedes agregar esta nueva tabla a un panel o un frame según tus necesidades.
//                // Por ejemplo, si estás usando un JFrame, puedes hacer algo como esto:
//                JFrame frame = new JFrame("Nueva Tabla");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.add(new JScrollPane(nuevaTabla)); // Agregar la tabla a un JScrollPane
//                frame.pack();
//                frame.setVisible(true);
//            }
//        }
    }//GEN-LAST:event_botonConfirmarCompraActionPerformed
    /**
     * Componente sin utilizar.
     *
     * @param evt El evento de acción que desencadena este método.
     */
    private void tblProductosComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tblProductosComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosComponentAdded
    /**
     * Componente sin utilizar.
     *
     * @param evt El evento de acción que desencadena este método.
     */
    private void tblProductosComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tblProductosComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosComponentRemoved

    /**
     * El método `main` es el punto de entrada principal del programa;
     * Inicializa la aplicación, configura el aspecto y comportamiento de la
     * interfaz de usuario, y muestra la ventana principal de la tienda Merch
     * Fórmula 1.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en este
     * caso).
     * @see frmTiendaF1
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmTiendaF1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTiendaF1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTiendaF1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTiendaF1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTiendaF1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonConfirmarCompra;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cboProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImporte;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
