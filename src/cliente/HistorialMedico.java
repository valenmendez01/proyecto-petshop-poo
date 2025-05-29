package cliente;

public class HistorialMedico {
    private int idHistorial;
    private String fecha;
    private String descripcion;
    private String tratamiento;
    private int costo;

    public HistorialMedico(int idHistorial, String fecha, String descripcion, String tratamiento, int costo) {
        this.idHistorial = idHistorial;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tratamiento = tratamiento;
        this.costo = costo;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Historiales Medicos{" +
                "idHistorial=" + idHistorial +
                ", fecha='" + fecha + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", costo=" + costo +
                '}';
    }
}
