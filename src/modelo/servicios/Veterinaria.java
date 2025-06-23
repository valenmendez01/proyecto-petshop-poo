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
    public void calcularCosto(Turno turno, String especie){

        double costoTotal = this.precio;

        if (especie.equals("gato")) {
            precio += 1000;
            System.out.println("Extra de $1000 por especie gato aplicado");
        } else if (especie.equals("perro")) {
            precio += 500;
            System.out.println("Extra de $500 por especie perro aplicado");
        }

        if (esUrgencia){
            precio += 2000;
            System.out.println("Extra de $2000 por servicio de urgencia aplicado");
        }

        System.out.println("Costo total de la cita: " + costoTotal);

        turno.setPrecioTotal(costoTotal);
    };

    @Override
    public String toCSV() {
        return "Veterinaria," + precio + "," + nombre + "," + esUrgencia;
    }

}
