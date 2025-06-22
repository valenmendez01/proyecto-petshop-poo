package modelo.servicios;

import modelo.agenda.Turno;

public class Veterinaria extends Servicio {
    private boolean esUrgencia;

    public Veterinaria(double precio, String nombre, boolean esUrgencia) {
        super(precio, nombre);
        this.esUrgencia = esUrgencia;
    }

    public boolean getEsUrgencia() {
        return esUrgencia;
    }

    public void setEsUrgencia(boolean esUrgencia) {
        this.esUrgencia = esUrgencia;
    }

    @Override
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

        if (esUrgencia){
            precio += 2000;
            System.out.println("Extra de $2000 por servicio de urgencia aplicado por bloque");
        }

        return costoTotal;
    };

    @Override
    public String toCSV() {
        return "Veterinaria," + precio + "," + nombre + "," + esUrgencia;
    }

}
