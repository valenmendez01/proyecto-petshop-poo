package modelo.servicios;

import modelo.agenda.Turno;

public class Peluqueria extends Servicio {
    private boolean incluyeCorteUnas;

    public Peluqueria(double precioBloque, String nombre, int cantidadBloques, boolean incluyeCorteUnas) {
        super(precioBloque, nombre, cantidadBloques);
        this.incluyeCorteUnas = incluyeCorteUnas;
    }

    public boolean getIncluyeCorteUnas() {
        return incluyeCorteUnas;
    }

    public void setIncluyeCorteUnas(boolean incluyeCorteUnas) {
        this.incluyeCorteUnas = incluyeCorteUnas;
    }

    public double calcularCosto(Turno turno){

        String especie = "gato"; // turno.getMascota().getEspecie();

        if (especie.equals("gato")) {
            precioBloque += 1000;
            System.out.println("Extra de $1000 por especie gato aplicado por bloque");
        } else if (especie.equals("perro")) {
            precioBloque += 500;
            System.out.println("Extra de $500 por especie perro aplicado por bloque");
        }

        if (incluyeCorteUnas){
            precioBloque += 2000;
            System.out.println("Extra de $2000 por servicio de corte de uñas aplicado por bloque");
        }

        return precioBloque * cantidadBloques;
    };

    @Override
    public String toCSV() {
        return "Peluqueria," + precioBloque + "," + nombre + "," + cantidadBloques + "," + incluyeCorteUnas;
    }

}
