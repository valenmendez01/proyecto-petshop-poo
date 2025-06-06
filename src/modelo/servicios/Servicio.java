package modelo.servicios;

import modelo.agenda.Turno;

public abstract class Servicio {
    protected String nombre;
    protected double precioBloque;
    protected int cantidadBloques;

    public Servicio(double precioBloque, String nombre, int cantidadBloques) {
        this.precioBloque = precioBloque;
        this.nombre = nombre;
        this.cantidadBloques = cantidadBloques;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBloque() {
        return precioBloque;
    }

    public void setPrecioBloque(double precioBloque) {
        this.precioBloque = precioBloque;
    }

    public int getCantidadBloques() {
        return cantidadBloques;
    }

    public void setCantidadBloques(int cantidadBloques) {
        this.cantidadBloques = cantidadBloques;
    }

    public abstract double calcularCosto(Turno turno);

    public abstract String toCSV();

}
