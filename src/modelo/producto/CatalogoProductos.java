package Producto;

import java.util.List;

public class CatalogoProductos {
    public List<Producto> productos;
    public CatalogoProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public void ver() {
        productos.forEach(producto -> {
            System.out.println("Id: " + producto.getIdProducto());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Precio: " + producto.getPrecio());
            System.out.println("Stock: " + producto.getStock());
            System.out.println("----------------------");
        });
    }
    public Producto obtenerPorId(String id) {
        return this.productos.stream()
                .filter(producto -> producto.getIdProducto().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void actualizarStock(String id, int cantidad) {
        Producto producto = this.obtenerPorId(id);
        producto.setStock(cantidad);
    }
    public void anadirStock(String id, int cantidad) {
        Producto producto = this.obtenerPorId(id);
        producto.setStock(cantidad + cantidad);
    }
    public void restarStock(String id, int cantidad) {
        Producto producto = this.obtenerPorId(id);
        producto.setStock(cantidad - producto.getStock());
    }



}
