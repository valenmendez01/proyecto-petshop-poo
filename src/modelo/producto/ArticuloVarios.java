package modelo.producto;

public class ArticuloVarios extends Producto {
    private String categoria;
    private String marca;
    private String material;

    public ArticuloVarios(int idProducto, String nombre, double precio, int stock, String marca, String categoria, String material) {
        super(idProducto, nombre, precio, stock, "ArticuloVarios");
        this.marca = marca;
        this.categoria = categoria;
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

    @Override
    public String getProductDescription() {
        return "ID: " + getIdProducto() + "\n"
                + "Nombre: " + getNombre() + "\n"
                + "Descripción: " + getTipo() + "\n"
                + "Precio: $" + getPrecio() + "\n"
                + "Stock: " + getStock();
    }
    @Override
    public String toDataString() {
        return super.toDataString() + ";ArticuloVarios;" + marca + ";" + categoria + ";" + material;
    }

}
