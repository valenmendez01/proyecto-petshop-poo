package cliente;

import java.util.ArrayList;
import java.util.List;

public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private int edad;
    private double peso;
    private List<HistorialMedico> historialMedico;

    public Mascota(int id, String nombre, String especie, String raza, String sexo, int edad, double peso, List<HistorialMedico> historialMedico) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.edad = edad;
        this.peso = peso;
        this.historialMedico = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void eliminarHistorial(HistorialMedico historial){
        historialMedico.remove(historial);
    }

    public void actualizarHistorial(int historialId, String nuevaFecha, String nuevaDescripcion,String nuevoTratamiento, int nuevoCosto){

        for (HistorialMedico historial : historialMedico) {

            if (historial.getHistorialId() == historialId) {
                historial.setFecha(nuevaFecha);
                historial.setDescripcion(nuevaDescripcion);
                historial.setTratamiento(nuevoTratamiento);
                historial.setCosto(nuevoCosto);
                break;
            }
        }
    }
}
