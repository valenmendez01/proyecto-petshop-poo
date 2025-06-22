package modelo.venta;

import modelo.producto.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pedido {
    private String idProveedor;
    private String nombre;
    private String direccion;
    private String contacto;
    private List<ItemPedido> listadoPedidos;

    public Pedido(String idProveedor, String nombre, String direccion, String contacto) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.listadoPedidos = new ArrayList<>();
    }


    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public static void crearPedido() {
        Scanner scanner = new Scanner(System.in);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/datos/pedidos.txt", true))) {
            System.out.print("ID del proveedor: ");
            String id = scanner.nextLine();
            System.out.print("Nombre del proveedor: ");
            String nombre = scanner.nextLine();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            System.out.print("Contacto: ");
            String contacto = scanner.nextLine();

            writer.write(id + ";" + nombre + ";" + direccion + ";" + contacto + ";[]");
            writer.newLine();
            System.out.println("Pedido creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar pedido: " + e.getMessage());
        }
    }

    public static void agregarProductoAPedido() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del pedido: ");
        String idPedido = scanner.nextLine();
        System.out.print("ID del producto: ");
        String idProducto = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/datos/pedidos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes[0].equals(idPedido)) {
                    encontrado = true;
                    Producto producto = Producto.buscarProductoPorId(idProducto);
                    if (producto == null) {
                        System.out.println("Producto no encontrado.");
                        return;
                    }

                    String nuevosItems = partes.length >= 5 ? partes[4].replace("]", "") : "";
                    nuevosItems += idProducto + "," + cantidad + "," + (producto.getPrecio() * cantidad) + ",";
                    linea = partes[0] + ";" + partes[1] + ";" + partes[2] + ";" + partes[3] + ";" + nuevosItems + "]";
                }
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer pedidos: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/datos/pedidos.txt"))) {
            for (String l : lineas) {
                writer.write(l);
                writer.newLine();
            }
            if (encontrado) System.out.println("Producto agregado al pedido.");
            else System.out.println("Pedido no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al guardar pedido: " + e.getMessage());
        }
    }

    public static void eliminarPedido() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del pedido a eliminar: ");
        String idEliminar = scanner.nextLine();

        File inputFile = new File("src/datos/pedidos.txt");
        File tempFile = new File("src/datos/pedidos_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            boolean encontrado = false;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (!datos[0].equals(idEliminar)) {
                    writer.write(linea);
                    writer.newLine();
                } else {
                    encontrado = true;
                }
            }

            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                if (encontrado)
                    System.out.println("Pedido eliminado.");
                else
                    System.out.println("Pedido no encontrado.");
            } else {
                System.out.println("Error al actualizar el archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void verPedidos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/datos/pedidos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                System.out.println("ID: " + partes[0] + " | Nombre: " + partes[1] +
                        " | Dirección: " + partes[2] + " | Contacto: " + partes[3]);
                if (partes.length > 4 && !partes[4].equals("[]")) {
                    System.out.println("   Productos:");
                    String[] items = partes[4].replace("]", "").split(",");
                    for (int i = 0; i < items.length - 2; i += 3) {
                        System.out.println("   - ID: " + items[i] + ", Cantidad: " + items[i + 1] + ", Total: $" + items[i + 2]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer pedidos: " + e.getMessage());
        }
    }
}