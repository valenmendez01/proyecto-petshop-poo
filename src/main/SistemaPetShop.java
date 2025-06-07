package main;

import dataBase.dataBaseProductos;
import modelo.cliente.Cliente;
import modelo.cliente.Mascota;
import modelo.agenda.Turno;
import modelo.producto.CatalogoProductos;
import modelo.producto.Producto;
import modelo.venta.Pedido;

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

                switch (comando) {
                    case "1":
                        gestionarClientesYMascotas();
                        break;
                    case "2":
                        gestionarCitas();
                        break;
                    case "3":
                        registrarVentas();
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
                    if (Cliente.actualizarClienteArchivo(scanner)){
                        System.out.println("Cliente actualizado con éxito");
                    } else {
                        System.out.println("Cliente no encontrado");
                    }
                    break;
                case 3:
                    if (Cliente.eliminarClienteArchivo(scanner)){
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
                    if (Mascota.actualizarMascotaArchivo(scanner)){
                        System.out.println("Mascota actualizada con éxito");
                    } else {
                        System.out.println("Mascota no encontrada");
                    }
                    break;
                case 7:
                    if (Mascota.eliminarMascotaArchivo(scanner)){
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

    public static void gestionarCitas(){
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
                    if (Turno.actualizarTurnoArchivo(scanner)){
                        System.out.println("Turno actualizado con éxito");
                    } else {
                        System.out.println("Turno no encontrado");
                    }
                    break;
                case 3:
                    if (Turno.eliminarTurnoArchivo(scanner)){
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

    public static void registrarVentas(){
        System.out.println("-> Registro de ventas seleccionado.");
        // Lógica: registrar venta, ver historial de ventas
    }

    public static void gestionarPedidos(JFrame parentFrame, ArrayList<Pedido> pedidos){
        System.out.println("-> Gestión de pedidos seleccionada.");
        // Lógica: hacer pedidos, consultar estado, agregar proveedores
    }

    public static void verProductos(JFrame parentFrame, ArrayList<Producto> productos){
        String[] columnas = {"Id", "Nombre", "Precio","Stock"};
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
}

