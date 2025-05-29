package servicios;

import cliente.Mascota;

public class Peluqueria extends Servicio {
    private boolean incluyeCorteUnas;

    public Peluqueria(double precioBloque, String nombre, int cantidadBloques, Mascota mascota, boolean incluyeCorteUnas) {
        super(precioBloque, nombre, cantidadBloques, mascota);
        this.incluyeCorteUnas = incluyeCorteUnas;
    }

    public boolean getIncluyeCorteUnas() {
        return incluyeCorteUnas;
    }

    public void setIncluyeCorteUnas(boolean incluyeCorteUnas) {
        this.incluyeCorteUnas = incluyeCorteUnas;
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

        if (incluyeCorteUnas){
            precioBloque += 2000;
            System.out.println("Extra de $2000 por servicio de corte de uñas aplicado por bloque");
        }

        return precioBloque * cantidadBloques;
    };
}
