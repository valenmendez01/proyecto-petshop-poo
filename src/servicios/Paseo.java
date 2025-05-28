package servicios;

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

    @Override
    public double calcularCosto(){
        if (individual){
            precioBloque =+ 1000;
        } else {
            precioBloque =+ 500;
        }
        return precioBloque * cantidadBloques;
    };
}
