package modelo.producto;

public abstract class Producto {
    private String idProducto;
    private String nombre;
    private double precio;
    private int stock;
    private String descripcion;

    public Producto(String idProducto, String nombre, double precio, int stock, String descripcion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
    }
    public String getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void anadirStock(int cantidad) {
        this.stock += cantidad;
    }
    public void restartStock(int cantidad) {
        this.stock -= cantidad;
    }
    public abstract String getProductDescription();
}
