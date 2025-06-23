package modelo.servicios;

import modelo.agenda.Turno;

public class Peluqueria extends Servicio {
    private boolean incluyeCorteUnas;

    public Peluqueria(double precioBloque, String nombre, boolean incluyeCorteUnas) {
        super(precioBloque, nombre);
        this.incluyeCorteUnas = incluyeCorteUnas;
    }

    public boolean getIncluyeCorteUnas() {
        return incluyeCorteUnas;
    }

    public void setIncluyeCorteUnas(boolean incluyeCorteUnas) {
        this.incluyeCorteUnas = incluyeCorteUnas;
    }

    public void calcularCosto(Turno turno, String especie){

        double costoTotal = this.precio;

        if (especie.equals("gato")) {
            precio += 1000;
            System.out.println("Extra de $1000 por especie gato aplicado");
        } else if (especie.equals("perro")) {
            precio += 500;
            System.out.println("Extra de $500 por especie perro aplicado");
        }

        if (incluyeCorteUnas){
            precio += 2000;
            System.out.println("Extra de $2000 por servicio de corte de uñas aplicado");
        }

        System.out.println("Costo total de la cita: " + costoTotal);

        turno.setPrecioTotal(costoTotal);
    };

    @Override
    public String toCSV() {
        return "Peluqueria," + precio + "," + nombre + "," + incluyeCorteUnas;
    }

}
