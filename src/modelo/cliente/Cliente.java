package modelo.cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private static int contadorId = 1;

    private int idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

    // Constructor
    public Cliente(String nombre, String apellido, String telefono, String direccion) {
        this.idCliente = contadorId++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Metodo para obtener todos los clientes del archivo
    public static List<Cliente> obtenerClientesArchivo() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        File archivo = new File("src/datos/clientes.txt");

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
            return clientes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    Cliente c = new Cliente(datos[1], datos[2], datos[3], datos[4]);
                    int id = Integer.parseInt(datos[0]);
                    c.setIdCliente(id);
                    clientes.add(c);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer clientes: " + e.getMessage());
        }

        return clientes;
    }

    // Agregar un cliente nuevo
    public static Cliente agregarClienteArchivo(Scanner scanner) {
        inicializarContadorId();

        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, apellido, telefono, direccion);

        File archivo = new File("src/datos/clientes.txt");

        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(cliente.idCliente + "," +
                cliente.nombre + "," +
                cliente.apellido + "," +
                cliente.telefono + "," +
                cliente.direccion + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }

        System.out.println("Cliente creado con ID: " + cliente.getIdCliente());
        return cliente;
    }

    // Eliminar cliente por ID
    public static boolean eliminarClienteArchivo(Scanner scanner) {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int idEliminar = scanner.nextInt();

        System.out.print("¿Está seguro? (s/n): ");
        scanner.nextLine(); // Limpiar buffer
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            // Eliminar también las mascotas y historiales asociados
            if (Mascota.eliminarMascotasPorIdCliente(idEliminar)){
                System.out.println("Mascota asociada al cliente eliminada");
            } else {
                System.out.println("El cliente no posee mascotas para eliminar");
            }

            // Los historiales se eliminan automáticamente al eliminar las mascotas

            File inputFile = new File("src/datos/clientes.txt");
            File tempFile = new File("src/datos/clientes_temp.txt");

            if (!inputFile.exists()) {
                return false;
            }

            boolean eliminado = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 5) {
                        int id = Integer.parseInt(datos[0]);
                        if (id != idEliminar) {
                            writer.write(linea + "\n");
                        } else {
                            eliminado = true;
                        }
                    }
                }

            } catch (IOException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }

            // Reemplazar archivo original
            if (eliminado && inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else if (!eliminado) {
                tempFile.delete(); // Limpiar archivo temporal si no se eliminó nada
            }

            return eliminado;

        } else {
            System.out.println("Eliminación cancelada.");
            return false;
        }
    };

    // Actualizar cliente por ID
    public static boolean actualizarClienteArchivo(Scanner scanner) {

        System.out.print("Ingrese el ID del cliente a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Nuevo teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Nueva dirección: ");
        String direccion = scanner.nextLine();
        Cliente cliente = new Cliente(nombre, apellido, telefono, direccion);

        File inputFile = new File("src/datos/clientes.txt");
        File tempFile = new File("src/datos/clientes_temp.txt");

        if (!inputFile.exists()) {
            return false;
        }

        boolean actualizado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    if (id == Integer.parseInt(datos[0])) {
                        writer.write(id + "," +
                            cliente.getNombre() + "," +
                            cliente.getApellido() + "," +
                            cliente.getTelefono() + "," +
                            cliente.getDireccion() + "\n");
                        actualizado = true;
                    } else {
                        writer.write(linea + "\n");
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        // Reemplazar archivo original
        if (actualizado && inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else if (!actualizado) {
            tempFile.delete(); // Limpiar archivo temporal si no se actualizó nada
        }

        return actualizado;
    }

    public static void inicializarContadorId() {
        File archivo = new File("src/datos/clientes.txt");

        int maxId = 0;

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length >= 1) {
                        int id = Integer.parseInt(partes[0]);
                        if (id > maxId) {
                            maxId = id;
                        }
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer IDs del archivo: " + e.getMessage());
            }
        }

        contadorId = maxId + 1;
    }

    @Override
    public String toString() {
        return "-- ID: " + idCliente +
                "- Nombre: " + nombre +
                "- Apellido: " + apellido +
                "- Telefono: " + telefono +
                "- Direccion: " + direccion;
    }
}