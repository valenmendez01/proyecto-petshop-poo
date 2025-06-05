package agenda;

import cliente.Mascota;
import servicios.Servicio;

import java.util.List;

public class Turno {
    private static int contadorId = 1;

    private int idTurno;
    private Mascota mascota;
    private Servicio servicio;
    private String fecha;
    private String horaInicio;
    private String horaFin;

    public Turno(Mascota mascota, Servicio servicio, String fecha, String horaInicio, String horaFin) {
        this.idTurno = contadorId++;
        this.mascota = mascota;
        this.servicio = servicio;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public List<Turno> obtenerTurnosPorMascota(int idMascota){
        return null; // completar
    }

    public void agregarTurnoArchivo(Turno turno, Mascota mascota, Servicio servicio, String fecha, String horaInicio, String horaFin){
        // completar
    }

    public void eliminarTurnoArchivo(int idTurno) {
        // completar
    }

    public void actualizarTurnoArchivo(int idTurno, Servicio servicio, String fechaNueva, String horaInicioNueva, String horaFinNueva){
        // completar
    }

    public boolean verificarDisponibilidad(String fecha, String horaInicio, String horaFin){
        // completar
        return false;
    }

    public void actualizarServicio(Servicio servicio){
        servicio.setNombre(this.servicio.getNombre());
        servicio.setPrecioBloque(this.servicio.getPrecioBloque());
        servicio.setCantidadBloques(this.servicio.getCantidadBloques());
    }
}
