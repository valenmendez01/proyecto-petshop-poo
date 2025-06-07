package modelo.producto;

public class ArticuloVarios extends Producto {
    private String categoria;
    private String marca;
    private String material;

    public ArticuloVarios(String idProducto, String nombre, double precio, int stock, String descripcion, String categoria, String marca, String material) {
        super(idProducto, nombre, precio, stock, descripcion);
        this.categoria = categoria;
        this.marca = marca;
        this.material = material;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
}
