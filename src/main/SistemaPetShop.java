package main;

import modelo.cliente.Cliente;
import modelo.cliente.Mascota;

import java.util.List;
import java.util.Scanner;

public class SistemaPetShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN DEL PET SHOP =====");
            System.out.println("1. Gestionar clientes y mascotas");
            System.out.println("2. Gestionar citas");
            System.out.println("3. Registrar venta");
            System.out.println("4. Gestionar proveedores y pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("-> Gestión de clientes y mascotas seleccionada.");
                    do {
                        System.out.println("1. Agregar un nuevo cliente");
                        System.out.println("2. Modificar datos del cliente");
                        System.out.println("3. Eliminar cliente");
                        System.out.println("4. Listar todos los clientes");
                        System.out.println("----------------------");
                        System.out.println("5. Registrar una nueva mascota para un cliente");
                        System.out.println("6. Modificar datos de una mascota");
                        System.out.println("7. Eliminar mascota");
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
                                    System.out.print("Ingrese el id del cliente asociado a la mascota a agregar: ");
                                    int clienteId = scanner.nextInt();

                                    System.out.print("Ingrese el nombre de la mascota: ");
                                    String nombreMascota = scanner.nextLine();
                                    System.out.print("Ingrese la especie de la mascota: ");
                                    String especie = scanner.nextLine();
                                    System.out.print("Ingrese la raza de la mascota: ");
                                    String raza = scanner.nextLine();
                                    System.out.print("Ingrese el sexo de la mascota: ");
                                    String sexo = scanner.nextLine();
                                    System.out.print("Ingrese la edad de la mascota: ");
                                    int edad = scanner.nextInt();
                                    System.out.print("Ingrese el peso de la mascota: ");
                                    double peso = scanner.nextDouble();

                                    //Mascota mascota = new Mascota(nombreMascota, especie, raza, sexo, edad, peso);
                                    //System.out.println("Mascota creada con ID: " + mascota.getIdMascota());
                                    //cliente.agregarMascota(mascota);

                                    System.out.print("Desea agregar otra mascota? 0 para salir: ");
                                    agregarOtra = scanner.nextInt();
                                } while (agregarOtra != 0);
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;
                            case 9:
                                break;
                            case 10:
                                break;
                            case 11:
                                break;
                            case 12:
                                break;
                            default:
                                System.out.println("Opción inválida. Intente nuevamente.");
                        }
                    } while (opcion != 13);
                    break;
                case 2:
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
                    break;
                case 3:
                    System.out.println("-> Registro de ventas seleccionado.");
                    // Lógica: registrar venta, ver historial de ventas
                    break;
                case 4:
                    System.out.println("-> Gestión de proveedores y pedidos seleccionada.");
                    // Lógica: hacer pedidos, consultar estado, agregar proveedores
                    break;
                case 0:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();

    }
}

/*
case x:
    System.out.println("-> Gestión de servicios seleccionada.");
    // Lógica: agregar servicios (peluquería, consulta, cirugía, paseo)
    break;
case x:
    System.out.println("-> Gestión de productos e inventario seleccionada.");
    // Lógica: ver stock, agregar productos, actualizar stock, etc.
    break;
 */