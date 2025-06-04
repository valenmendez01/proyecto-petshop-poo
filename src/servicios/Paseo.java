package servicios;

import agenda.Turno;

public class Paseo extends Servicio {
    private boolean individual;

    public Paseo(double precioBloque, String nombre, int cantidadBloques, boolean individual) {
        super(precioBloque, nombre, cantidadBloques);
        this.individual = individual;
    }

    public boolean getIndividual() {
        return individual;
    }

    public void setIndividual(boolean individual) {
        this.individual = individual;
    }

    public double calcularCosto(Turno turno){

        String especie = turno.getMascota().getEspecie();

        if (especie.equals("gato")) {
            precioBloque += 1000;
            System.out.println("Extra de $1000 por especie gato aplicado por bloque");
        } else if (especie.equals("perro")) {
            precioBloque += 500;
            System.out.println("Extra de $500 por especie perro aplicado por bloque");
        }

        if (individual){
            precioBloque += 2000;
            System.out.println("Extra de $2000 por servicio individual de paseo aplicado por bloque");
        }

        return precioBloque * cantidadBloques;
    };
}
