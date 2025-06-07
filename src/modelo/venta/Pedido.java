package modelo.venta;

import modelo.producto.Producto;

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
}
