package agenda;

import cliente.Mascota;
import servicios.Servicio;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Turno> turnos;

    public Agenda() {
        this.turnos = new ArrayList<>();
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    public void agendarTurno(Turno turno, Mascota mascota, Servicio servicio, String fecha, String horaInicio, String horaFin){
        turno.setMascota(mascota);
        turno.setServicio(servicio);
        turno.setFecha(fecha);
        turno.setHoraInicio(horaInicio);
        turno.setHoraFin(horaFin);
        turnos.add(turno);
        System.out.println("Turno agendado correctamente");
    }

    public void eliminarTurno(int idTurno){
        for (Turno turno : turnos) {
            if (turno.getIdTurno() == idTurno) {
                turnos.remove(turno);
                break;
            }
        }
    }

    public void actualizarTurno(int idTurno, Mascota mascota, Servicio servicio, String fecha, String horaInicio, String horaFin){
        for (Turno turno : turnos) {
            if (turno.getIdTurno() == idTurno) {
                turno.setMascota(mascota);
                turno.setServicio(servicio);
                turno.setFecha(fecha);
                turno.setHoraInicio(horaInicio);
                turno.setHoraFin(horaFin);
                break;
            }
        }
    }

    public void obtenerTurnosPorMascota(int idMascota){
        for (Turno turno : turnos) {
            if (turno.getMascota().getIdMascota() == idMascota) {
                System.out.println("Turno: " + turno.getIdTurno() + " - Servicio: " + turno.getServicio().getNombre() + " - Fecha: " + turno.getFecha() + " - Hora inicio: " + turno.getHoraInicio() + " - Hora fin: " + turno.getHoraFin());
            }
        }
    }

    public boolean verificarDisponibilidad(String fecha, String horaInicio, String horaFin){
        for (Turno turno : turnos) {
            if (turno.getFecha().equals(fecha) && turno.getHoraInicio().equals(horaInicio) && turno.getHoraFin().equals(horaFin)) {
                return false;
            }
        }
        return true;
    }

}
