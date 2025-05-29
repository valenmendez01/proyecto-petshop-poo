package servicios;

import cliente.Mascota;

public abstract class Servicio {
    protected String nombre;
    protected double precioBloque;
    protected int cantidadBloques;
    protected Mascota mascota;

    public Servicio(double precioBloque, String nombre, int cantidadBloques, Mascota mascota) {
        this.precioBloque = precioBloque;
        this.nombre = nombre;
        this.cantidadBloques = cantidadBloques;
        this.mascota = mascota;
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

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public abstract double calcularCosto();
}
