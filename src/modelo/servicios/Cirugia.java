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

    public void calcularCosto(Turno turno, String especie){

        double costoTotal = this.precio;

        if (especie.equals("gato")) {
            precio += 1000;
            System.out.println("Extra de $1000 por especie gato aplicado");
        } else if (especie.equals("perro")) {
            precio += 500;
            System.out.println("Extra de $500 por especie perro aplicado");
        }

        if (requiereAnestesia){
            precio += 2000;
            System.out.println("Extra de $2000 por servicio de anestesia aplicado");
        }

        System.out.println("Costo total de la cita: " + costoTotal);

        turno.setPrecioTotal(costoTotal);
    };

    @Override
    public String toCSV() {
        return "Cirugia," + precio + "," + nombre + "," + requiereAnestesia;
    }

}