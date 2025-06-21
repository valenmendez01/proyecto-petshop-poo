package modelo.venta;

import dataBase.dataBaseProductos;
import modelo.producto.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String idProveedor;
    private String nombre;
    private String direccion;
    private String contacto;
    private List<ItemPedido> listadoPedidos;

    public Pedido(String idProveedor, String nombre, String direccion, String contacto, List<ItemPedido> listadoPedidos) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.listadoPedidos = listadoPedidos;
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
    public void realizarPedido(String id, Producto producto, int cantidad, double costoTotal) {
        var PedidoProveedor = new ItemPedido(id, producto, cantidad, costoTotal);
        listadoPedidos.add(PedidoProveedor);
        producto.anadirStock(cantidad);
    }
    public List<ItemPedido> getListadoPedidos() {
        return listadoPedidos;
    }
    public void setListadoPedidos(List<ItemPedido> listadoPedidos) {
        this.listadoPedidos = listadoPedidos;
    }
    public void verPedidos() {
        listadoPedidos.forEach(pedidoProveedor -> {
            pedidoProveedor.mostrar();
        });
    }
    public ItemPedido ObtenerPedido(String id) {
        return this.listadoPedidos.stream()
                .filter(pedidoProveedor -> pedidoProveedor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public static void guardarPedidoArchivo(Pedido pedido) {
        String archivo = "src/datos/pedido.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(pedido.getIdProveedor()).append(";")
                    .append(pedido.getNombre()).append(";")
                    .append(pedido.getDireccion()).append(";")
                    .append(pedido.getContacto()).append(";");

            for (ItemPedido item : pedido.getListadoPedidos()) {
                sb.append(item.getId()).append(",")
                        .append(item.getProducto().getIdProducto()).append(",")
                        .append(item.getCantidad()).append(",")
                        .append(item.getCostoTotal()).append("|");
            }

            if (!pedido.getListadoPedidos().isEmpty()) {
                sb.setLength(sb.length() - 1); // Eliminar último '|'
            }

            writer.write(sb.toString());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar el pedido: " + e.getMessage());
        }
    }
    public static List<Pedido> obtenerPedidoArchivo() {
        List<Pedido> pedidos = new ArrayList<>();
        File archivo = new File("src/datos/pedido.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Cargar todos los productos una vez
            List<Producto> productos = dataBaseProductos.obtenerProductos();

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";", 5);  // Dividimos en 5 partes (los primeros 4 son fijos)

                if (partes.length < 5) continue;

                String idProveedor = partes[0];
                String nombre = partes[1];
                String direccion = partes[2];
                String contacto = partes[3];
                String itemsTexto = partes[4];

                List<ItemPedido> items = new ArrayList<>();

                for (String itemStr : itemsTexto.split("\\|")) {
                    String[] itemPartes = itemStr.split(",");

                    if (itemPartes.length < 4) continue;

                    String idItem = itemPartes[0];
                    String idProducto = itemPartes[1];
                    int cantidad = Integer.parseInt(itemPartes[2]);
                    double costoTotal = Double.parseDouble(itemPartes[3]);

                    // Buscar el producto por ID
                    Producto producto = null;
                    for (Producto p : productos) {
                        if (p.getIdProducto()==Integer.parseInt(idProducto)) {
                            producto = p;
                            break;
                        }
                    }

                    if (producto != null) {
                        ItemPedido item = new ItemPedido(idItem, producto, cantidad, costoTotal);
                        items.add(item);
                    }
                }

                Pedido pedido = new Pedido(idProveedor, nombre, direccion, contacto, items);
                pedidos.add(pedido);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo de pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    public double calcularTotalPedido() {
        double total = 0.0;
        for (ItemPedido item : listadoPedidos) {
            total += item.getCostoTotal();
        }
        return total;
    }


}

