package modelo.venta;

import modelo.cliente.Cliente;
import modelo.producto.Producto;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Date fechaVenta;
    private double montoTotal;
    private String metodoPago;
    private List<ItemVenta> itemVenta;

    public Venta(Cliente cliente, Date fechaVenta, double montoTotal, String metodoPago, List<ItemVenta> itemVenta) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.itemVenta = itemVenta;
    }
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
    public void agregarItem(ItemVenta itemVenta) {
        this.itemVenta.add(itemVenta);
    }
    public void eliminarItem(ItemVenta itemVenta) {
        this.itemVenta.remove(itemVenta);
    }
    public double calcularTotal() {
        double total = 0;
        for (ItemVenta itemVenta : itemVenta) {
            total += itemVenta.getPrecioUnitario();
        };
        return total;
    }
    public List<ItemVenta> getItems() {
        return this.itemVenta;
    }
    public double calcularTotalVenta() {
        double total = 0.0;
        for (ItemVenta item : itemVenta) {
            total += item.getSubtotal();
        }
        return total;
    }

    public static void guardarVentaArchivo(Venta venta) {
        String archivo = "src/datos/ventas.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            StringBuilder itemsTexto = new StringBuilder();
            for (ItemVenta item : venta.getItemVenta()) {
                itemsTexto.append(item.getProducto().getIdProducto()).append(",")
                        .append(item.getCantidad()).append(",")
                        .append(item.getSubtotal()).append("|");
            }
            if (itemsTexto.length() > 0) {
                itemsTexto.setLength(itemsTexto.length() - 1); // Quitar último "|"
            }

            String linea = venta.getIdVenta() + ";" +
                    (venta.getCliente() != null ? venta.getCliente().getNombre() : "SinCliente") + ";" +
                    sdf.format(venta.getFechaVenta()) + ";" +
                    venta.getMetodoPago() + ";" +
                    itemsTexto.toString();

            writer.write(linea);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar venta: " + e.getMessage());
        }
    }

    public static List<Venta> obtenerVentasArchivo(List<Producto> productos, List<Cliente> clientes) {
        List<Venta> ventas = new ArrayList<>();
        String archivo = "src/datos/ventas.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";", 5);
                if (partes.length < 5) continue;

                int idVenta = Integer.parseInt(partes[0]);
                String nombreCliente = partes[1];
                Date fecha = sdf.parse(partes[2]);
                String metodoPago = partes[3];
                String itemsTexto = partes[4];

                // Buscar cliente por nombre
                Cliente cliente = clientes.stream()
                        .filter(c -> c.getNombre().equalsIgnoreCase(nombreCliente))
                        .findFirst()
                        .orElse(null);

                List<ItemVenta> items = new ArrayList<>();
                for (String itemStr : itemsTexto.split("\\|")) {
                    String[] itemPartes = itemStr.split(",");
                    if (itemPartes.length < 3) continue;

                    int idProd = Integer.parseInt(itemPartes[0]);
                    int cantidad = Integer.parseInt(itemPartes[1]);
                    double subtotal = Double.parseDouble(itemPartes[2]);

                    Producto prod = productos.stream()
                            .filter(p -> p.getIdProducto()==(idProd))
                            .findFirst()
                            .orElse(null);

                    if (prod != null) {
                        items.add(new ItemVenta(prod, cantidad, prod.getPrecio(), subtotal));
                    }
                }

                double montoTotal = items.stream().mapToDouble(ItemVenta::getSubtotal).sum();
                ventas.add(new Venta(idVenta, cliente, fecha, montoTotal, metodoPago, items));
            }

        } catch (Exception e) {
            System.out.println("Error al leer ventas: " + e.getMessage());
        }

        return ventas;
    }

    // Sobrescribir archivo con todas las ventas
    public static void sobrescribirVentasArchivo(List<Venta> ventas) {
        String archivo = "src/datos/ventas.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Venta v : ventas) {
                StringBuilder itemsTexto = new StringBuilder();
                for (ItemVenta item : v.getItemVenta()) {
                    itemsTexto.append(item.getProducto().getIdProducto()).append(",")
                            .append(item.getCantidad()).append(",")
                            .append(item.getSubtotal()).append("|");
                }
                if (itemsTexto.length() > 0) {
                    itemsTexto.setLength(itemsTexto.length() - 1); // eliminar Ãºltimo "|"
                }

                String linea = v.getIdVenta() + ";" +
                        (v.getCliente() != null ? v.getCliente().getNombre() : "SinCliente") + ";" +
                        sdf.format(v.getFechaVenta()) + ";" +
                        v.getMetodoPago() + ";" +
                        itemsTexto.toString();

                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir ventas: " + e.getMessage());
        }
    }
    private int inicializarContadorId() {
        File archivo = new File("src/datos/ventas.txt");

        int maxId = 0;

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(";");
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

        return maxId + 1;
    }
}