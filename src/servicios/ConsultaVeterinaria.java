package servicios;

import cliente.Mascota;

public class ConsultaVeterinaria extends Servicio {
    private boolean esUrgencia;

    public ConsultaVeterinaria(double precioBloque, String nombre, int cantidadBloques, Mascota mascota, boolean esUrgencia) {
        super(precioBloque, nombre, cantidadBloques, mascota);
        this.esUrgencia = esUrgencia;
    }

    public boolean getEsUrgencia() {
        return esUrgencia;
    }

    public void setEsUrgencia(boolean esUrgencia) {
        this.esUrgencia = esUrgencia;
    }

    @Override
    public double calcularCosto(){

        String especie = mascota.getEspecie();

        if (especie.equals("gato")) {
            precioBloque += 1000;
            System.out.println("Extra de $1000 por especie gato aplicado por bloque");
        } else if (especie.equals("perro")) {
            precioBloque += 500;
            System.out.println("Extra de $500 por especie perro aplicado por bloque");
        }

        if (esUrgencia){
            precioBloque += 2000;
            System.out.println("Extra de $2000 por servicio de urgencia aplicado por bloque");
        }

        return precioBloque * cantidadBloques;
    };
}
