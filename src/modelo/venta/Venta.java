package Venta;

import cliente.Cliente;

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

}
