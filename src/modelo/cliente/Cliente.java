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
        File archivo = new File("datos/clientes.txt");

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
            // Crear directorio si no existe
            archivo.getParentFile().mkdirs();
            return clientes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int maxId = 0;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) { // Validar que tenga todos los campos
                    Cliente c = new Cliente(datos[1], datos[2], datos[3], datos[4]);
                    int id = Integer.parseInt(datos[0]);
                    c.setIdCliente(id);
                    clientes.add(c);

                    // Actualizar el contador para mantener IDs únicos
                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }

            // Actualizar el contador global
            contadorId = maxId + 1;

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer clientes: " + e.getMessage());
        }

        return clientes;
    }

    // Agregar un cliente nuevo
    public static void agregarClienteArchivo(Scanner scanner) {

        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = scanner.nextLine();

        Cliente cliente = new Cliente(nombreCliente, apellido, telefono, direccion);

        File archivo = new File("datos/clientes.txt");
        // Crear directorio si no existe
        archivo.getParentFile().mkdirs();

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
    }

    // Buscar cliente por ID
    public static Cliente buscarClientePorId(int idCliente) {
        List<Cliente> clientes = obtenerClientesArchivo();
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    // Eliminar cliente por ID
    public static boolean eliminarClienteArchivo(Scanner scanner) {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int idEliminar = scanner.nextInt();

        Cliente cliente3 = Cliente.buscarClientePorId(idEliminar);
        if (cliente3 == null) {
            System.out.println("Cliente no encontrado.");
        }

        System.out.println("Cliente a eliminar: " + cliente3);
        System.out.print("¿Está seguro? (s/n): ");
        scanner.nextLine(); // Limpiar buffer
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            // Eliminar también las mascotas y historiales asociados
            Mascota.eliminarMascotasPorCliente(idEliminar);
            // Los historiales se eliminan automáticamente al eliminar las mascotas

            File inputFile = new File("datos/clientes.txt");
            File tempFile = new File("datos/clientes_temp.txt");

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

            if (eliminado) {
                System.out.println("Cliente eliminado exitosamente (junto con sus mascotas e historiales).");
            }

            return eliminado;
        }
    };

    // Actualizar cliente por ID
    public static boolean actualizarClienteArchivo(Scanner scanner) {
        System.out.print("Ingrese el ID del cliente a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = Cliente.buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
        }

        System.out.println("Cliente actual: " + cliente);
        System.out.print("Nuevo nombre (actual: " + cliente.getNombre() + "): ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo apellido (actual: " + cliente.getApellido() + "): ");
        String apellido2 = scanner.nextLine();
        System.out.print("Nuevo teléfono (actual: " + cliente.getTelefono() + "): ");
        String telefono2 = scanner.nextLine();
        System.out.print("Nueva dirección (actual: " + cliente.getDireccion() + "): ");
        String direccion2 = scanner.nextLine();

        if (!nombre.isEmpty()) cliente.setNombre(nombre);
        if (!apellido2.isEmpty()) cliente.setApellido(apellido2);
        if (!telefono2.isEmpty()) cliente.setTelefono(telefono2);
        if (!direccion2.isEmpty()) cliente.setDireccion(direccion2);

        File inputFile = new File("datos/clientes.txt");
        File tempFile = new File("datos/clientes_temp.txt");

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
                    int id2 = Integer.parseInt(datos[0]);

                    if (id2 == cliente.getIdCliente()) {
                        writer.write(cliente.getIdCliente() + "," +
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
            System.out.println("Cliente actualizado exitosamente.");

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

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}