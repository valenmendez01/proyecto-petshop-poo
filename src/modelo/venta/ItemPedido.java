package modelo.venta;

import modelo.producto.Producto;

public class ItemPedido {
    private String id;
    private Producto producto;
    private int cantidad;
    private double costoTotal;

    public ItemPedido(String id, Producto producto, int cantidad, double costoTotal) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getCostoTotal() {
        return costoTotal;
    }
    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    public void mostrar() {
        System.out.println("Producto: " + producto.getIdProducto());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Costo: " + costoTotal);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


}