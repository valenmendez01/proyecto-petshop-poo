package main;

import modelo.cliente.Cliente;
import modelo.cliente.HistorialMedico;
import modelo.cliente.Mascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

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

        JButton boton3 = new JButton("3. Registrar venta");

        JButton boton4 = new JButton("4. Gestionar proveedores y pedidos");

        // Asignar comandos
        boton1.setActionCommand("1");
        boton2.setActionCommand("2");
        boton3.setActionCommand("3");
        boton4.setActionCommand("4");

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
                        gestionarPedidos();
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

        panelCentral.add(boton1);
        panelCentral.add(boton2);
        panelCentral.add(boton3);
        panelCentral.add(boton4);

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
                    Cliente.actualizarClienteArchivo(scanner);
                    break;
                case 3:
                    Cliente.eliminarClienteArchivo(scanner);
                    break;
                case 4:
                    List<Cliente> clientes = Cliente.obtenerClientesArchivo();

                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                        return;
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
                    Mascota.actualizarMascotaArchivo(scanner);
                    break;
                case 7:
                    Mascota.eliminarMascotaArchivo(scanner);
                    break;
                case 8:
                    List<Mascota> mascotas = Mascota.obtenerMascotasPorCliente(scanner);
                    for (Mascota m : mascotas) {
                        System.out.println(m.toString());
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
                    // seleccionar cliente - mascota
                    // elegir servicio
                    // mostrar disponibilidad fecha-hora
                    // elegir fecha y hora
                    // validar
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    // ver todas las citas del día
                    // filtrar por cliente o mascota
                    // mostrar citas próximas
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

    public static void gestionarPedidos(){
        System.out.println("-> Gestión de pedidos seleccionada.");
        // Lógica: hacer pedidos, consultar estado, agregar proveedores
    }

}

