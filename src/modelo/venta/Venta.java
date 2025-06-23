package modelo.venta;

import modelo.cliente.Cliente;
import modelo.producto.Producto;

import java.io.*;
import java.util.*;

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Date fechaVenta;
    private double montoTotal;
    private String metodoPago;
    private List<ItemVenta> itemVenta;


    private Venta(int idVenta, Cliente cliente, Date fechaVenta, double montoTotal, String metodoPago, List<ItemVenta> itemVenta) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.itemVenta = itemVenta;
    }


    public int getIdVenta() {
        return idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<ItemVenta> getItemVenta() {
        return itemVenta;
    }

    public void setItemVenta(List<ItemVenta> itemVenta) {
        this.itemVenta = itemVenta;
    }

    public static void crearVenta() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID de la venta: ");
        String idVenta = scanner.nextLine();

        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();

        System.out.print("Fecha de la venta (dd/mm/aaaa): ");
        String fecha = scanner.nextLine();

        System.out.print("Método de pago: ");
        String metodoPago = scanner.nextLine();

        // Se guarda una línea base con los datos de la venta y lista de items vacía
        String linea = idVenta + ";" + cliente + ";" + fecha + ";" + metodoPago + ";[]";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/datos/ventas.txt", true))) {
            writer.write(linea);
            writer.newLine();
            System.out.println("Venta registrada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la venta: " + e.getMessage());
        }
    }
    public static void agregarProductoAVenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID de la venta: ");
        String idVenta = scanner.nextLine();
        System.out.print("ID del producto: ");
        String idProducto = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/datos/ventas.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes[0].equals(idVenta)) {
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
            System.out.println("Error al leer ventas: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/datos/ventas.txt"))) {
            for (String l : lineas) {
                writer.write(l);
                writer.newLine();
            }
            if (encontrado) System.out.println("Producto agregado a la venta.");
            else System.out.println("Venta no encontrada.");
        } catch (IOException e) {
            System.out.println("Error al guardar venta: " + e.getMessage());
        }
    }
    public static void eliminarVenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID de la venta a eliminar: ");
        String idEliminar = scanner.nextLine();

        File archivoOriginal = new File("src/datos/ventas.txt");
        File archivoTemporal = new File("src/datos/ventas_temp.txt");

        boolean eliminado = false;

        try (
                BufferedReader reader = new BufferedReader(new FileReader(archivoOriginal));
                BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal))
        ) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (!partes[0].equals(idEliminar)) {
                    writer.write(linea);
                    writer.newLine();
                } else {
                    eliminado = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
            return;
        }

        if (archivoOriginal.delete() && archivoTemporal.renameTo(archivoOriginal)) {
            if (eliminado) {
                System.out.println("Venta eliminada con éxito.");
            } else {
                System.out.println("No se encontró una venta con ese ID.");
            }
        } else {
            System.out.println("Error al actualizar el archivo.");
        }
    }
    public static void verVentas() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/datos/ventas.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                System.out.println("ID: " + partes[0] + " | Cliente: " + partes[1] + " | Fecha: " + partes[2] + " | Pago: " + partes[3]);

                if (partes.length > 4 && !partes[4].equals("[]")) {
                    System.out.println("   Productos:");
                    String[] items = partes[4].replace("[", "").replace("]", "").split(",");

                    double totalVenta = 0;
                    for (int i = 0; i < items.length - 2; i += 3) {
                        String idProducto = items[i];
                        String cantidad = items[i + 1];
                        String totalItem = items[i + 2];
                        Producto p = Producto.buscarProductoPorId(idProducto);

                        if (p != null) {
                            System.out.println("   - " + p.getNombre() + " (ID: " + idProducto + ") | Precio unitario: $" + p.getPrecio()
                                    + " | Cantidad: " + cantidad + " | Total: $" + totalItem);
                            totalVenta += Double.parseDouble(totalItem);
                        }
                    }
                    System.out.println("Total venta: $" + totalVenta);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer ventas: " + e.getMessage());
        }
    }








}
