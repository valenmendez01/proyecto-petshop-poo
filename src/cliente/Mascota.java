package cliente;

import java.util.ArrayList;
import java.util.List;

public class Mascota {
    private static int contadorId = 1;

    private int idMascota;
    private List<HistorialMedico> historialMedico;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private int edad;
    private double peso;

    public Mascota(String nombre, String especie, String raza, String sexo, int edad, double peso) {
        this.idMascota = contadorId++;
        this.historialMedico = new ArrayList<>();
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.edad = edad;
        this.peso = peso;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public List<HistorialMedico> getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(List<HistorialMedico> historialMedico) {
        this.historialMedico = historialMedico;
    }

    public void agregarHistorial(HistorialMedico historial){
        historialMedico.add(historial);
    }

    public void eliminarHistorial(int idHistorial){
        for (HistorialMedico historial : historialMedico) {
            if (historial.getIdHistorial() == idHistorial) {
                historialMedico.remove(historial);
                break;
            }
        }
    }

    public void actualizarHistorial(int idHistorial, String nuevaFecha, String nuevaDescripcion,String nuevoTratamiento, int nuevoCosto){

        for (HistorialMedico historial : historialMedico) {

            if (historial.getIdHistorial() == idHistorial) {
                historial.setFecha(nuevaFecha);
                historial.setDescripcion(nuevaDescripcion);
                historial.setTratamiento(nuevoTratamiento);
                historial.setCosto(nuevoCosto);
                break;
            }
        }
    }
}
