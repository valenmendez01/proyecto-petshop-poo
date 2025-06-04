package cliente;

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

    public Cliente(String nombre, String apellido, String telefono, String direccion) {
        this.idCliente = contadorId++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

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

    // Leer todos los clientes del archivo y ponerlos en una lista
    public static ArrayList<String> cargarClientesArchivo(String ruta, String nombre) throws IOException {
        ArrayList<String> clientes = new ArrayList<>();
        File archivo = new File(ruta + nombre);

        if (archivo.exists()) {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                clientes.add(br.readLine());
            }
            br.close();
            fr.close();
        }
        return clientes;
    }

    // Guardar toda la lista de clientes al archivo
    public static void guardarClienteArchivo(ArrayList<String> clientes, String ruta, String nombre) throws IOException {
        File archivo = new File(ruta + nombre);
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("" + clientes.size());
        bw.newLine();
        for (int i = 0; i < clientes.size(); i++) {
            bw.write(clientes.get(i));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        fw.close();
    }

    // Mostrar todos los clientes
    public static void obtenerClientesArchivo(ArrayList<String> clientes) {
        System.out.println("Clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i));
        }
    }

    // Agregar un cliente nuevo
    public static void agregarClienteArchivo(ArrayList<String> clientes, String id, String nombre, String email) {
        String nuevoCliente = id + "," + nombre + "," + email;
        clientes.add(nuevoCliente);
    }

    // Eliminar cliente por ID
    public static boolean eliminarClienteArchivo(ArrayList<String> clientes, String id) {
        for (int i = 0; i < clientes.size(); i++) {
            String cliente = clientes.get(i);
            String[] datos = cliente.split(",");
            if (datos[0].equals(id)) {
                clientes.remove(i);
                return true;
            }
        }
        return false;
    }

    // Actualizar cliente por ID
    public static boolean actualizarClienteArchivo(ArrayList<String> clientes, String id, String nuevoNombre, String nuevoEmail) {
        for (int i = 0; i < clientes.size(); i++) {
            String cliente = clientes.get(i);
            String[] datos = cliente.split(",");
            if (datos[0].equals(id)) {
                String clienteActualizado = id + "," + nuevoNombre + "," + nuevoEmail;
                clientes.set(i, clienteActualizado);
                return true;
            }
        }
        return false;
    }
    // Leer del archivo
    // ArrayList<String> clientes = leerClientes("c:/cosas/", "clientes.txt");

    // Hacer operaciones
    // agregarCliente(clientes, "3", "Carlos", "carlos@email.com");
    // eliminarCliente(clientes, "1");
    // actualizarCliente(clientes, "2", "Pedro López", "pedro@email.com");

    // Guardar de vuelta
    // guardarClientes(clientes, "c:/cosas/", "clientes.txt");
}
