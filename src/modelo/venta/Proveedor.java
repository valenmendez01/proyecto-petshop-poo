/*
package modelo.venta;

import Producto.Producto;

import java.util.List;

public class Proveedor {
    private String idProveedor;
    private String nombre;
    private String direccion;
    private String contacto;
    private List<PedidoProveedor> listadoPedidos;

    public Proveedor(String idProveedor, String nombre, String direccion, String contacto, List<PedidoProveedor> listadoPedidos) {
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
        var PedidoProveedor = new PedidoProveedor(id, producto, cantidad, costoTotal);
        listadoPedidos.add(PedidoProveedor);
        producto.anadirStock(cantidad);
    }
    public List<PedidoProveedor> getListadoPedidos() {
        return listadoPedidos;
    }
    public void setListadoPedidos(List<PedidoProveedor> listadoPedidos) {
        this.listadoPedidos = listadoPedidos;
    }
    public void verPedidos() {
        listadoPedidos.forEach(pedidoProveedor -> {
            pedidoProveedor.mostrar();
        });
    }
    public PedidoProveedor ObtenerPedido(String id) {
        return this.listadoPedidos.stream()
                .filter(pedidoProveedor -> pedidoProveedor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
 */
