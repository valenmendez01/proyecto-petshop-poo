package modelo.venta;

public class ItemPedido {
    private String idProducto;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidad;
    private double costoTotal;

    public ItemPedido(String idProducto, String nombreProducto, double precioUnitario, int cantidad) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.costoTotal = precioUnitario * cantidad;
    }

    public String getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    public String toDataString() {
        return idProducto + "," + nombreProducto + "," + precioUnitario + "," + cantidad + "," + costoTotal;
    }
}
