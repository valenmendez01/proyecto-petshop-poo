package main;

import modelo.cliente.Cliente;
import modelo.cliente.HistorialMedico;
import modelo.cliente.Mascota;
import modelo.agenda.Turno;
import modelo.producto.Producto;
import modelo.venta.Pedido;
import modelo.venta.Venta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class SistemaPetShop {
    public static void main(String[] args) {
        JFrame marco = new JFrame("Menu");
        // Panel superior con el título
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DEL PET SHOP", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        marco.add(titulo, BorderLayout.NORTH);

        // Panel central con los 4 botones (2x2)
        JPanel panelCentral = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // espacio interior

        JButton boton1 = new JButton("1. Gestionar clientes y mascotas");

        JButton boton2 = new JButton("2. Gestionar citas");

        JButton boton3 = new JButton("3. Gestionar ventas");

        JButton boton4 = new JButton("4. Gestionar proveedores y pedidos");

        JButton boton5 = new JButton("5. Gestionar Productos");

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
                List<Cliente> clientes = Cliente.obtenerClientesArchivo();

                switch (comando) {
                    case "1":
                        gestionarClientesYMascotas();
                        break;
                    case "2":
                        gestionarCitas();
                        break;
                    case "3":
                        gestionarVentas();
                        break;
                    case "4":
                        gestionarPedidos();
                        break;
                    case "5":
                        gestionarProductos();
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
                    System.out.println("------- LISTA DE CLIENTES -------");
                    List<Cliente> clientes = Cliente.obtenerClientesArchivo();

                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        for (Cliente cliente : clientes) {
                            System.out.println(cliente.toString());
                        }
                    }
                    System.out.println("--------------");
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
                    List<Mascota> mascotas = Mascota.obtenerMascotasPorCliente(scanner);

                    if (mascotas.isEmpty()) {
                        System.out.println("No hay mascotas registradas.");
                    } else {
                        for (Mascota m : mascotas) {
                            System.out.println(m.toString());
                        }
                    }
                    System.out.println("--------------");
                    break;
                case 9:
                    HistorialMedico.agregarHistorialArchivo(scanner);
                    break;
                case 10:
                    if (HistorialMedico.actualizarHistorialArchivo(scanner)) {
                        System.out.println("Historial actualizado con éxito");
                    } else {
                        System.out.println("Historial no encontrado");
                    }
                    break;
                case 11:
                    if (HistorialMedico.eliminarHistorialArchivo(scanner)) {
                        System.out.println("Historial eliminado con éxito");
                    } else {
                        System.out.println("Historial no encontrado");
                    }
                    break;
                case 12:
                    List<HistorialMedico> historiales = HistorialMedico.obtenerHistorialesPorMascota(scanner);

                    if (historiales.isEmpty()) {
                        System.out.println("No hay historiales registrados.");
                    } else {
                        for (HistorialMedico h : historiales) {
                            System.out.println(h.toString());
                        }
                    }
                    break;
                case 13:
                    System.out.println("Volviendo al menú anterior...");
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
            System.out.println("3. Eliminar cita");
            System.out.println("4. Listar todas las citas");
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
                    Turno.obtenerTurnosArchivo();
                    System.out.println("----------------------");
                    break;
                case 5:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    public static void gestionarProductos() {
        Scanner scanner = new Scanner(System.in);
        int opcionProducto;

        do {
            System.out.println("\n GESTIONAR PRODUCTOS ");
            System.out.println("1. Crear producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Modificar stock");
            System.out.println("4. Ver productos");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            opcionProducto = Integer.parseInt(scanner.nextLine());

            switch (opcionProducto) {
                case 1:
                    Producto.crearProducto();
                    break;
                case 2:
                    Producto.eliminarProducto();
                    break;
                case 3:
                    Producto.modificarStock();
                    break;
                case 4:
                    Producto.verProductos();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcionProducto != 5);
    }


    public static void gestionarPedidos() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n GESTIONAR PEDIDOS:");
            System.out.println("1. Crear pedido");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Eliminar pedido");
            System.out.println("4. Ver pedidos");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    Pedido.crearPedido();
                    break;
                case 2:
                    Pedido.agregarProductoAPedido();
                    break;
                case 3:
                    Pedido.eliminarPedido();
                    break;
                case 4:
                    Pedido.verPedidos();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("⚠Opción no válida.");
            }
        } while (opcion != 5);
    }


    public static void gestionarVentas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nGESTION DE VENTAS:");
            System.out.println("1. Registrar venta");
            System.out.println("2. Agregar producto a una venta");
            System.out.println("3. Eliminar venta");
            System.out.println("4. Ver ventas");
            System.out.println("5. Volver");

            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    Venta.crearVenta();
                    break;
                case 2:
                    Venta.agregarProductoAVenta();
                    break;
                case 3:
                    Venta.eliminarVenta();
                    break;
                case 4:
                    Venta.verVentas();
                    break;
                case 5:
                    System.out.println("↩ Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);
    }
}

