package modelo.servicios;

import modelo.agenda.Turno;

public abstract class Servicio {
    protected String nombre;
    protected double precio;

    public Servicio(double precio, String nombre) {
        this.precio = precio;
        this.nombre = nombre;
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

    public abstract void calcularCosto(Turno turno, String especie);

    public abstract String toCSV();

}
