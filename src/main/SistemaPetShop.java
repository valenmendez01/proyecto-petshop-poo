package main;

import dataBase.dataBaseProductos;
import modelo.cliente.Cliente;
import modelo.cliente.Mascota;
import modelo.agenda.Turno;
import modelo.producto.CatalogoProductos;
import modelo.producto.Producto;
import modelo.venta.ItemPedido;
import modelo.venta.ItemVenta;
import modelo.venta.Pedido;
import modelo.venta.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class SistemaPetShop {
    public static void main(String[] args) {
        JFrame marco = new JFrame("Menu");
        ArrayList<Producto> productos = dataBaseProductos.obtenerProductos();
        // Panel superior con el título
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DEL PET SHOP", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        marco.add(titulo, BorderLayout.NORTH);

        // Panel central con los 4 botones (2x2)
        JPanel panelCentral = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // espacio interior

        JButton boton1 = new JButton("1. Gestionar clientes y mascotas");

        JButton boton2 = new JButton("2. Gestionar citas");

        JButton boton3 = new JButton("3. Registrar venta");

        JButton boton4 = new JButton("4. Gestionar proveedores y pedidos");

        JButton boton5 = new JButton("5. Ver Productos");

        // Asignar comandos
        boton1.setActionCommand("1");
        boton2.setActionCommand("2");
        boton3.setActionCommand("3");
        boton4.setActionCommand("4");
        boton5.setActionCommand("5");

        // Crear listener único para todos
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comando = e.getActionCommand();
                ArrayList<Venta> ventas = new ArrayList<>();

                switch (comando) {
                    case "1":
                        gestionarClientesYMascotas();
                        break;
                    case "2":
                        gestionarCitas();
                        break;
                    case "3":
                        gestionarVentas(marco, ventas);
                        break;
                    case "4":
                        gestionarPedidos(marco, new ArrayList<>());
                        break;
                    case "5":
                        verProductos(marco, productos);
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }
        };

        // Asignar listener
        boton1.addActionListener(listener);
        boton2.addActionListener(listener);
        boton3.addActionListener(listener);
        boton4.addActionListener(listener);
        boton5.addActionListener(listener);

        panelCentral.add(boton1);
        panelCentral.add(boton2);
        panelCentral.add(boton3);
        panelCentral.add(boton4);
        panelCentral.add(boton5);

        marco.add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con el botón salir
        JButton botonSalir = new JButton("Salir");
        botonSalir.addActionListener(e -> System.exit(0));

        JPanel panelInferior = new JPanel();
        panelInferior.add(botonSalir);
        marco.add(panelInferior, BorderLayout.SOUTH);

        marco.pack();
        marco.setVisible(true);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void gestionarClientesYMascotas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        System.out.println("-> Gestión de clientes y mascotas seleccionada.");
        do {
            System.out.println("1. Agregar un nuevo cliente");
            System.out.println("2. Modificar datos del cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Listar todos los clientes");
            System.out.println("----------------------");
            System.out.println("5. Registrar una nueva mascota para un cliente");
            System.out.println("6. Modificar datos de una mascota");
            System.out.println("7. Eliminar mascota de un cliente");
            System.out.println("8. Listar mascotas de un cliente específico");
            System.out.println("----------------------");
            System.out.println("9.  Crear historial médico asociado a una mascota");
            System.out.println("10. Actualizar historial médico asociado a una mascota");
            System.out.println("11. Eliminar historial médico asociado a una mascota");
            System.out.println("12. Ver historial médico de una mascota");
            System.out.println("----------------------");
            System.out.println("13. Volver atrás");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Cliente.agregarClienteArchivo(scanner);
                    break;
                case 2:
                    if (Cliente.actualizarClienteArchivo(scanner)) {
                        System.out.println("Cliente actualizado con éxito");
                    } else {
                        System.out.println("Cliente no encontrado");
                    }
                    break;
                case 3:
                    if (Cliente.eliminarClienteArchivo(scanner)) {
                        System.out.println("Cliente eliminado con éxito");
                    } else {
                        System.out.println("Cliente no encontrado");
                    }
                    break;
                case 4:
                    List<Cliente> clientes = Cliente.obtenerClientesArchivo();

                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                        return;
                    } else {
                        for (Cliente cliente : clientes) {
                            System.out.println(cliente.toString());
                        }
                    }
                    break;
                case 5:
                    int agregarOtra;
                    do {
                        Mascota.agregarMascotaArchivo(scanner);

                        System.out.print("Desea agregar otra mascota? 0 para salir: ");
                        agregarOtra = scanner.nextInt();
                    } while (agregarOtra != 0);
                    break;
                case 6:
                    if (Mascota.actualizarMascotaArchivo(scanner)) {
                        System.out.println("Mascota actualizada con éxito");
                    } else {
                        System.out.println("Mascota no encontrada");
                    }
                    break;
                case 7:
                    if (Mascota.eliminarMascotaArchivo(scanner)) {
                        System.out.println("Mascota eliminada con éxito");
                    } else {
                        System.out.println("Mascota no encontrada");
                    }
                    break;
                case 8:
                    List<Mascota> mascotas = Mascota.obtenerMascotasArchivo();

                    if (mascotas.isEmpty()) {
                        System.out.println("No hay mascotas registradas.");
                        return;
                    } else {
                        for (Mascota m : mascotas) {
                            System.out.println(m.toString());
                        }
                    }
                    break;
                case 9:
                    //HistorialMedico.agregarHistorialArchivo(scanner);
                    break;
                case 10:
                    //HistorialMedico.actualizarHistorialArchivo(scanner);
                    break;
                case 11:
                    //HistorialMedico.eliminarHistorialArchivo(scanner);
                    break;
                case 12:
                    //HistorialMedico.obtenerHistorialesPorMascota(scanner);
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 13);
    }

    public static void gestionarCitas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        System.out.println("-> Gestión de citas seleccionada.");
        do {
            System.out.println("1. Crear nueva cita");
            System.out.println("2. Modificar cita existente");
            System.out.println("3. Cancelar cita");
            System.out.println("4. Listar citas");
            System.out.println("----------------------");
            System.out.println("5. Volver atrás");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    int agregarOtra;
                    do {
                        Turno.agregarTurnoArchivo(scanner);

                        System.out.print("Desea agregar otro turno? 0 para salir: ");
                        agregarOtra = scanner.nextInt();
                    } while (agregarOtra != 0);
                    break;
                case 2:
                    if (Turno.actualizarTurnoArchivo(scanner)) {
                        System.out.println("Turno actualizado con éxito");
                    } else {
                        System.out.println("Turno no encontrado");
                    }
                    break;
                case 3:
                    if (Turno.eliminarTurnoArchivo(scanner)) {
                        System.out.println("Turno eliminado con éxito");
                    } else {
                        System.out.println("Turno no encontrado");
                    }
                    break;
                case 4:
                    List<Turno> turnos = Turno.obtenerTurnosArchivo();

                    if (turnos.isEmpty()) {
                        System.out.println("No hay turnos registrados.");
                        return;
                    } else {
                        for (Turno t : turnos) {
                            System.out.println(t.toString());
                        }
                    }
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    public static void registrarVentas() {
        System.out.println("-> Registro de ventas seleccionado.");
        // Lógica: registrar venta, ver historial de ventas
    }


    public static void verProductos(JFrame parentFrame, ArrayList<Producto> productos) {
        String[] columnas = {"Id", "Nombre", "Precio", "Stock"};
        Object[][] datos = new Object[productos.size()][4];

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            datos[i][0] = p.getIdProducto();
            datos[i][1] = p.getNombre();
            datos[i][2] = p.getPrecio();
            datos[i][3] = p.getStock();
        }

        JTable tabla = new JTable(new DefaultTableModel(datos, columnas));
        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton verDetalleBtn = new JButton("Ver Detalle");

        verDetalleBtn.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(parentFrame, "Seleccione un producto primero.");
                return;
            }

            // Buscar el producto correspondiente
            Producto productoSeleccionado = productos.get(filaSeleccionada);

            String detalle = productoSeleccionado.getDescripcion();

            JOptionPane.showMessageDialog(parentFrame, detalle, "Detalle del Producto", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton botonModificarStock = new JButton("Modificar Stock");
        botonModificarStock.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(parentFrame, "Seleccioná un producto primero.");
                return;
            }

            Producto productoSeleccionado = productos.get(filaSeleccionada);

            String input = JOptionPane.showInputDialog(parentFrame,
                    "Ingresá el nuevo stock para " + productoSeleccionado.getNombre() + ":",
                    productoSeleccionado.getStock());

            if (input != null && !input.isEmpty()) {
                try {
                    int nuevoStock = Integer.parseInt(input);
                    productoSeleccionado.setStock(nuevoStock);
                    tabla.setValueAt(nuevoStock, filaSeleccionada, 3);

                    productos.clear();
                    productos.addAll(dataBaseProductos.actualizarProducto(productoSeleccionado));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Stock inválido. Ingresá un número entero.");
                }
            }
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(verDetalleBtn);
        panelBoton.add(botonModificarStock);

        JDialog dialogo = new JDialog(parentFrame, "Productos", true);
        dialogo.setSize(500, 400);
        dialogo.setLayout(new BorderLayout());
        dialogo.add(scrollPane, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }

    public static void gestionarPedidos(JFrame parentFrame, ArrayList<Pedido> pedidos) {
        Pedido pedidoActual = null;

        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(parentFrame,
                    "Gestión de Pedidos\n" +
                            "1. Crear nuevo pedido\n" +
                            "2. Agregar producto al pedido\n" +
                            "3. Eliminar producto del pedido\n" +
                            "4. Guardar pedido\n" +
                            "5. Ver Pedidos guardados\n" +
                            "6. Volver",
                    "Gestión de Proveedores y Pedidos", JOptionPane.PLAIN_MESSAGE);

            if (opcion == null) break;  // Usuario canceló

            switch (opcion) {
                case "1":
                    String id = JOptionPane.showInputDialog("ID del proveedor:");
                    String nombre = JOptionPane.showInputDialog("Nombre del proveedor:");
                    String direccion = JOptionPane.showInputDialog("Dirección:");
                    String contacto = JOptionPane.showInputDialog("Contacto:");
                    pedidoActual = new Pedido(id, nombre, direccion, contacto, new ArrayList<>());
                    JOptionPane.showMessageDialog(null, "Pedido creado con éxito.");
                    break;

                case "2":
                    if (pedidoActual == null) {
                        JOptionPane.showMessageDialog(null, "Primero debés crear un pedido.");
                        break;
                    }

                    ArrayList<Producto> productos = dataBaseProductos.obtenerProductos();
                    if (productos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay productos cargados.");
                        break;
                    }

                    StringBuilder lista = new StringBuilder("Productos disponibles:\n");
                    for (Producto p : productos) {
                        lista.append(p.getIdProducto())
                                .append(" - ").append(p.getNombre())
                                .append(" ($").append(p.getPrecio()).append(")\n");
                    }

                    String idProd = JOptionPane.showInputDialog(lista + "\nIngrese el ID del producto:");
                    Producto seleccionado = null;
                    for (Producto p : productos) {
                        if (p.getIdProducto().equalsIgnoreCase(idProd)) {
                            seleccionado = p;
                            break;
                        }
                    }

                    if (seleccionado == null) {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                        break;
                    }

                    int cantidad;
                    try {
                        cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad:"));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Cantidad inválida.");
                        break;
                    }

                    double total = cantidad * seleccionado.getPrecio();
                    String idItem = "item" + System.currentTimeMillis();
                    ItemPedido nuevoItem = new ItemPedido(idItem, seleccionado, cantidad, total);
                    pedidoActual.getListadoPedidos().add(nuevoItem);
                    JOptionPane.showMessageDialog(null, "Producto agregado al pedido.");
                    break;

                case "3":
                    if (pedidoActual == null || pedidoActual.getListadoPedidos().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay productos en el pedido.");
                        break;
                    }

                    StringBuilder resumen = new StringBuilder("Ítems del pedido:\n");
                    for (int i = 0; i < pedidoActual.getListadoPedidos().size(); i++) {
                        ItemPedido item = pedidoActual.getListadoPedidos().get(i);
                        resumen.append(i).append(". ")
                                .append(item.getProducto().getNombre())
                                .append(" - Cantidad: ").append(item.getCantidad()).append("\n");
                    }

                    int indexEliminar;
                    try {
                        indexEliminar = Integer.parseInt(JOptionPane.showInputDialog(resumen + "\nIngrese el número del producto a eliminar:"));
                        pedidoActual.getListadoPedidos().remove(indexEliminar);
                        JOptionPane.showMessageDialog(null, "Producto eliminado.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Entrada inválida.");
                    }
                    break;

                case "4":
                    if (pedidoActual == null || pedidoActual.getListadoPedidos().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pedido para guardar.");
                        break;
                    }

                    Pedido.guardarPedidoArchivo(pedidoActual);
                    pedidos.add(pedidoActual);  // Agregamos a la lista general
                    pedidoActual = null;        // Limpiamos el pedido actual
                    JOptionPane.showMessageDialog(null, "Pedido guardado con éxito.");
                    break;
                case "5":
                    List<Pedido> pedidosGuardados = Pedido.obtenerPedidoArchivo();
                    if (pedidosGuardados.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pedidos guardados.");
                        break;
                    }

                    StringBuilder salida = new StringBuilder();
                    for (Pedido p : pedidosGuardados) {
                        salida.append("Proveedor: ").append(p.getNombre()).append("\n");
                        salida.append("Dirección: ").append(p.getDireccion()).append("\n");
                        salida.append("Contacto: ").append(p.getContacto()).append("\n");
                        salida.append("Ítems:\n");
                        for (ItemPedido item : p.getListadoPedidos()) {
                            salida.append(" - ").append(item.getProducto().getNombre())
                                    .append(" (x").append(item.getCantidad()).append(")")
                                    .append(" - $").append(item.getProducto().getPrecio()).append(" c/u\n");
                        }
                        salida.append("Total del pedido: $").append(p.calcularTotalPedido()).append("\n");
                        salida.append("--------\n");
                    }

                    JOptionPane.showMessageDialog(null, salida.toString(), "Pedidos guardados", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }

        } while (!"6".equals(opcion));
    }

    public static void gestionarVentas(JFrame parentFrame, ArrayList<Venta> ventas) {
        Venta ventaActual = null;
        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(parentFrame,
                    "Gestión de Ventas\n" +
                            "1. Crear nueva venta\n" +
                            "2. Agregar producto a la venta\n" +
                            "3. Eliminar producto de la venta\n" +
                            "4. Eliminar una venta\n" +
                            "5. Ver ventas\n" +
                            "6. Volver");

            switch (opcion) {
                case "1":
                    if (ventaActual != null) {
                        JOptionPane.showMessageDialog(parentFrame, "Ya hay una venta en curso. Finalizala antes de crear otra.");
                        break;
                    }

                    String idVenta = JOptionPane.showInputDialog(parentFrame, "Ingrese un ID para la nueva venta:");
                    if (idVenta == null || idVenta.isEmpty()) {
                        JOptionPane.showMessageDialog(parentFrame, "ID inválido.");
                        break;
                    }

                    String nombreCliente = JOptionPane.showInputDialog(parentFrame, "Nombre del cliente:");
                    String apellidoCliente = JOptionPane.showInputDialog(parentFrame, "Apellido del cliente:");
                    String telefonoCliente = JOptionPane.showInputDialog(parentFrame, "Teléfono del cliente:");
                    String direccionCliente = JOptionPane.showInputDialog(parentFrame, "Dirección del cliente:");

                    Cliente cliente = new Cliente(nombreCliente, apellidoCliente, telefonoCliente, direccionCliente);

                    // Parámetros restantes para Venta
                    Date fechaVenta = new Date(); // Fecha actual
                    double montoTotal = 0.0; // Se calcula al final
                    String metodoPago = JOptionPane.showInputDialog(parentFrame, "Método de pago:");

                    ventaActual = new Venta(idVenta, cliente, fechaVenta, montoTotal, metodoPago, new ArrayList<>());
                    JOptionPane.showMessageDialog(parentFrame, "Venta creada correctamente.");
                    break;
                case "2":
                    if (ventaActual == null) {
                        JOptionPane.showMessageDialog(parentFrame, "No hay una venta en curso. Creá una primero.");
                        break;
                    }

                    ArrayList<Producto> productosDisponibles = dataBaseProductos.obtenerProductos();

                    String nombreProducto = JOptionPane.showInputDialog(parentFrame, "Ingrese el nombre del producto:");

                    Producto productoSeleccionado = null;
                    for (Producto p : productosDisponibles) {
                        if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                            productoSeleccionado = p;
                            break;
                        }
                    }

                    if (productoSeleccionado == null) {
                        JOptionPane.showMessageDialog(parentFrame, "Producto no encontrado.");
                        break;
                    }

                    String cantidadStr = JOptionPane.showInputDialog(parentFrame, "Ingrese la cantidad:");
                    int cantidad = Integer.parseInt(cantidadStr);

                    double precioUnitario = productoSeleccionado.getPrecio();
                    double subtotal = precioUnitario * cantidad;

                    ItemVenta item = new ItemVenta(productoSeleccionado, cantidad, precioUnitario, subtotal);
                    ventaActual.getItemVenta().add(item); // Asumiendo que tenés este getter

                    JOptionPane.showMessageDialog(parentFrame, "Producto agregado correctamente.");
                    break;
                case "3":
                    if (ventaActual == null) {
                        JOptionPane.showMessageDialog(parentFrame, "No hay una venta en curso.");
                        break;
                    }

                    List<ItemVenta> itemsVenta = ventaActual.getItemVenta();
                    if (itemsVenta.isEmpty()) {
                        JOptionPane.showMessageDialog(parentFrame, "La venta no tiene productos cargados.");
                        break;
                    }

                    String productos = "";
                    for (ItemVenta i : itemsVenta) {
                        productos += "- " + i.getProducto().getNombre() + " (x" + i.getCantidad() + ")\n";
                    }
                    JOptionPane.showMessageDialog(parentFrame, "Productos cargados en la venta:\n" + productos);

                    String nombreEliminar = JOptionPane.showInputDialog(parentFrame, "Ingrese el nombre del producto a eliminar:");
                    ItemVenta itemAEliminar = null;

                    for (ItemVenta it : itemsVenta) {
                        if (it.getProducto().getNombre().equalsIgnoreCase(nombreEliminar)) {
                            itemAEliminar = it;
                            break;
                        }
                    }

                    if (itemAEliminar != null) {
                        itemsVenta.remove(itemAEliminar);
                        JOptionPane.showMessageDialog(parentFrame, "Producto eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(parentFrame, "Producto no encontrado en la venta.");
                    }
                    break;
                case "4":
                    if (ventaActual == null) {
                        JOptionPane.showMessageDialog(parentFrame, "No hay una venta en curso para eliminar.");
                        break;
                    }

                    int confirmacion = JOptionPane.showConfirmDialog(parentFrame,
                            "¿Estás seguro de que querés eliminar esta venta completa?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        ventas.remove(ventaActual);
                        ventaActual = null;
                        JOptionPane.showMessageDialog(parentFrame, "Venta eliminada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(parentFrame, "Eliminación cancelada.");
                    }
                    break;
                case "5":
                    if (ventas.isEmpty()) {
                        JOptionPane.showMessageDialog(parentFrame, "No hay ventas registradas.");
                        break;
                    }

                    StringBuilder salida = new StringBuilder();
                    for (Venta venta : ventas) {
                        salida.append("ID Venta: ").append(venta.getIdVenta()).append("\n");
                        salida.append("Cliente: ").append(venta.getCliente().getNombre()).append(" ")
                                .append(venta.getCliente().getApellido()).append("\n");
                        salida.append("Fecha: ").append(venta.getFechaVenta()).append("\n");
                        salida.append("Método de pago: ").append(venta.getMetodoPago()).append("\n");
                        salida.append("Items:\n");

                        for (ItemVenta itt : venta.getItemVenta()) {
                            salida.append(" - ").append(itt.getProducto().getNombre())
                                    .append(" (x").append(itt.getCantidad()).append(") - $")
                                    .append(itt.getPrecioUnitario()).append(" c/u\n");
                        }

                        salida.append("Total: $").append(venta.calcularTotalVenta()).append("\n");
                        salida.append("---------------\n");
                    }

                    JOptionPane.showMessageDialog(parentFrame, salida.toString(), "Ventas registradas", JOptionPane.INFORMATION_MESSAGE);
                    break;

            }
        } while (!"6".equals(opcion));
    }
}

