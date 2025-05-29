package cliente;

public class HistorialMedico {
    private int historialId;
    private String fecha;
    private String descripcion;
    private String tratamiento;
    private int costo;

    public HistorialMedico(int historialId, String fecha, String descripcion, String tratamiento, int costo) {
        this.historialId = historialId;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tratamiento = tratamiento;
        this.costo = costo;
    }

    public int getHistorialId() {
        return historialId;
    }

    public void setHistorialId(int historialId) {
        this.historialId = historialId;
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
                "historialId=" + historialId +
                ", fecha='" + fecha + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", costo=" + costo +
                '}';
    }
}
