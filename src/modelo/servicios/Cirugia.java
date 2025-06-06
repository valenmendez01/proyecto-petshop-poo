package modelo.servicios;

import modelo.agenda.Turno;

public class Cirugia extends Servicio {
    private boolean requiereAnestesia;

    public Cirugia(double precioBloque, String nombre, int cantidadBloques, boolean requiereAnestesia) {
        super(precioBloque, nombre, cantidadBloques);
        this.requiereAnestesia = requiereAnestesia;
    }

    public boolean getRequiereAnestesia() {
        return requiereAnestesia;
    }

    public void setRequiereAnestesia(boolean requiereAnestesia) {
        this.requiereAnestesia = requiereAnestesia;
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

        if (requiereAnestesia){
            precioBloque += 2000;
            System.out.println("Extra de $2000 por servicio de anestesia aplicado por bloque");
        }

        return precioBloque * cantidadBloques;
    };

    @Override
    public String toCSV() {
        return "Cirugia," + precioBloque + "," + nombre + "," + cantidadBloques + "," + requiereAnestesia;
    }

}
