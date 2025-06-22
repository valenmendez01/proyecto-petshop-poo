package modelo.servicios;

import modelo.agenda.Turno;

public class Cirugia extends Servicio {
    private boolean requiereAnestesia;

    public Cirugia(double precioBloque, String nombre, boolean requiereAnestesia) {
        super(precioBloque, nombre);
        this.requiereAnestesia = requiereAnestesia;
    }

    public boolean getRequiereAnestesia() {
        return requiereAnestesia;
    }

    public void setRequiereAnestesia(boolean requiereAnestesia) {
        this.requiereAnestesia = requiereAnestesia;
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

        if (requiereAnestesia){
            precio += 2000;
            System.out.println("Extra de $2000 por servicio de anestesia aplicado por bloque");
        }

        return costoTotal;
    };

    @Override
    public String toCSV() {
        return "Cirugia," + precio + "," + nombre + "," + requiereAnestesia;
    }

}
