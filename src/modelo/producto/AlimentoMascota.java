package modelo.producto;

import java.util.Date;
import java.util.List;

public class AlimentoMascota extends Producto {
    private String especieObjetivo;
    private String etapaVida;
    private List<String> ingredientes;
    private String infoNutricional;
    private Date fechaVencimiento;

    public AlimentoMascota(String nombre, double precio, int stock, String descripcion, String especieObjetivo, String etapaVida, Date fechaVencimiento, String infoNutricional) {
        super(nombre, precio, stock, descripcion);
        this.especieObjetivo = especieObjetivo;
        this.etapaVida = etapaVida;
        this.fechaVencimiento = fechaVencimiento;
        this.infoNutricional = infoNutricional;
    }

    public AlimentoMascota(int idProducto, String nombre, double precio, int stock, String descripcion, String especieObjetivo, String etapaVida, Date fechaVencimiento, String infoNutricional) {
        super(idProducto, nombre, precio, stock, descripcion);
        this.especieObjetivo = especieObjetivo;
        this.etapaVida = etapaVida;
        this.fechaVencimiento = fechaVencimiento;
        this.infoNutricional = infoNutricional;
    }

    public String getEspecieObjetivo() {
        return especieObjetivo;
    }
    public void setEspecieObjetivo(String especieObjetivo) {
        this.especieObjetivo = especieObjetivo;
    }
    public String getEtapaVida() {
        return etapaVida;
    }
    public void setEtapaVida(String etapaVida) {
        this.etapaVida = etapaVida;
    }
    public String getInfoNutricional() {
        return infoNutricional;
    }
    public void setInfoNutricional(String infoNutricional) {
        this.infoNutricional = infoNutricional;
    }
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String getProductDescription() {
        return "ID: " + getIdProducto() + "\n"
                + "Nombre: " + getNombre() + "\n"
                + "Descripción: " + getDescripcion() + "\n"
                + "Precio: $" + getPrecio() + "\n"
                + "Stock: " + getStock();
    }
}
