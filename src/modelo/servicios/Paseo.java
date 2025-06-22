package modelo.servicios;

import modelo.agenda.Turno;

public class Paseo extends Servicio {
    private boolean individual;

    public Paseo(double precioBloque, String nombre, boolean individual) {
        super(precioBloque, nombre);
        this.individual = individual;
    }

    public boolean getIndividual() {
        return individual;
    }

    public void setIndividual(boolean individual) {
        this.individual = individual;
    }

    public void calcularCosto(Turno turno, String especie){

        double costoTotal = this.precio;

        if (especie.equals("gato")) {
            costoTotal += 1000;
            System.out.println("Extra de $1000 por especie gato aplicado");
        } else if (especie.equals("perro")) {
            costoTotal += 500;
            System.out.println("Extra de $500 por especie perro aplicado");
        }

        if (individual){
            costoTotal += 2000;
            System.out.println("Extra de $2000 por servicio individual de paseo aplicado");
        }

        System.out.println("Costo total de la cita: " + costoTotal);

        turno.setPrecioTotal(costoTotal);
    };

    @Override
    public String toCSV() {
        return "Paseo," + precio + "," + nombre + "," + individual;
    }
}
