package modelo.venta;

import modelo.cliente.Cliente;
import modelo.producto.Producto;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venta {
    private String idVenta;
    private Cliente cliente;
    private Date fechaVenta;
    private double montoTotal;
    private String metodoPago;
    private List<ItemVenta> itemVenta;

    public Venta(String idVenta, Cliente cliente, Date fechaVenta, double montoTotal, String metodoPago, List<ItemVenta> itemVenta) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.itemVenta = itemVenta;
    }


    public String getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
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
        String archivo = "src/datos/venta.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/datos/ventas.txt", true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(venta.getIdVenta()).append(";")
                    .append(venta.getCliente().getNombre()).append(";")
                    .append(venta.getFechaVenta()).append(";")
                    .append(venta.getMontoTotal()).append(";");
            for (ItemVenta item : venta.getItemVenta()) {
                sb.append(item.getProducto().getIdProducto()).append(";")
                        .append(item.getCantidad()).append(";")
                        .append(item.getSubtotal()).append(";");
            }
            if (!venta.getItems().isEmpty()){
                sb.setLength(sb.length()-1);
            }
            writer.write(sb.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar venta" + e.getMessage());
        }
    }
    public static List<Venta> obtenerVentasArchivo(List<Producto> productos) {
        List<Venta> ventas = new ArrayList<>();
        String archivo = "src/datos/ventas.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";", 5);
                if (partes.length < 5) continue;

                String idVenta = partes[0];
                String nombreCliente = partes[1]; // Se ignora en esta versión
                Date fecha = sdf.parse(partes[2]);
                String metodoPago = partes[3];
                String itemsTexto = partes[4];

                List<ItemVenta> items = new ArrayList<>();
                for (String itemStr : itemsTexto.split("\\|")) {
                    String[] itemPartes = itemStr.split(",");
                    if (itemPartes.length < 3) continue;

                    String idProd = itemPartes[0];
                    int cantidad = Integer.parseInt(itemPartes[1]);
                    double subtotal = Double.parseDouble(itemPartes[2]);

                    Producto prod = null;
                    for (Producto p : productos) {
                        if (p.getIdProducto().equals(idProd)) {
                            prod = p;
                            break;
                        }
                    }

                    if (prod != null) {
                        double precioUnitario = prod.getPrecio();
                        items.add(new ItemVenta(prod, cantidad, precioUnitario, subtotal));
                    }
                }

                double montoTotal = 0;
                for (ItemVenta item : items) {
                    montoTotal += item.getSubtotal();
                }

                // En vez de pasar un cliente real, solo usamos el nombre como string o null
                ventas.add(new Venta(idVenta, null, fecha, montoTotal, metodoPago, items));
            }

        } catch (Exception e) {
            System.out.println("Error al leer ventas: " + e.getMessage());
        }

        return ventas;
    }

    public static void sobrescribirVentasArchivo(List<Venta> ventas) {
        String archivo = "src/datos/venta.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Venta v : ventas) {
                StringBuilder itemsTexto = new StringBuilder();
                for (ItemVenta item : v.getItemVenta()) {
                    itemsTexto.append(item.getProducto().getIdProducto()).append(",")
                            .append(item.getCantidad()).append(",")
                            .append(item.getSubtotal()).append("|");
                }
                // Eliminar el último "|"
                if (itemsTexto.length() > 0) {
                    itemsTexto.setLength(itemsTexto.length() - 1);
                }

                String linea = v.getIdVenta() + ";" +
                        v.getCliente().getNombre() + ";" +
                        v.getFechaVenta() + ";" +
                        v.getMetodoPago() + ";" +
                        itemsTexto.toString();

                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir ventas: " + e.getMessage());
        }
    }



}
