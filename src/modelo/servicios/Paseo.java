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

    public double calcularCosto(Turno turno){

        double costoTotal = this.precio;

        String especie = "gato"; // turno.getMascota().getEspecie();

        if (especie.equals("gato")) {
            precio += 1000;
            System.out.println("Extra de $1000 por especie gato aplicado por bloque");
        } else if (especie.equals("perro")) {
            precio += 500;
            System.out.println("Extra de $500 por especie perro aplicado por bloque");
        }

        if (individual){
            precio += 2000;
            System.out.println("Extra de $2000 por servicio individual de paseo aplicado por bloque");
        }

        return costoTotal;
    };

    @Override
    public String toCSV() {
        return "Paseo," + precio + "," + nombre + "," + individual;
    }
}
