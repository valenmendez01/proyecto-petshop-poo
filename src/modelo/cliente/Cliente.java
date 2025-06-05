package modelo.cliente;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        File archivo = new File("clientes.txt");

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
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

    // Agregar un modelo.cliente nuevo
    public static void agregarClienteArchivo(Cliente cliente) {
        try (FileWriter writer = new FileWriter("clientes.txt", true)) {
            writer.write(cliente.idCliente + "," +
                    cliente.nombre + "," +
                    cliente.apellido + "," +
                    cliente.telefono + "," +
                    cliente.direccion + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    // Buscar modelo.cliente por ID
    public static Cliente buscarClientePorId(int idCliente) {
        List<Cliente> clientes = obtenerClientesArchivo();
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    // Eliminar modelo.cliente por ID
    public static boolean eliminarClienteArchivo(int idEliminar) {
        File inputFile = new File("clientes.txt");
        File tempFile = new File("clientes_temp.txt");

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
    }

    // Actualizar modelo.cliente por ID
    public static boolean actualizarClienteArchivo(Cliente clienteActualizado) {
        File inputFile = new File("clientes.txt");
        File tempFile = new File("clientes_temp.txt");

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
                    int id = Integer.parseInt(datos[0]);

                    if (id == clienteActualizado.getIdCliente()) {
                        writer.write(clienteActualizado.getIdCliente() + "," +
                                clienteActualizado.getNombre() + "," +
                                clienteActualizado.getApellido() + "," +
                                clienteActualizado.getTelefono() + "," +
                                clienteActualizado.getDireccion() + "\n");
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
