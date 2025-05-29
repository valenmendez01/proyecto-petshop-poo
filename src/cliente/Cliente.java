package cliente;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String fechaRegistro;
    private List<Mascota> mascotas;

    public Cliente(int idCliente, String nombre, String apellido, String telefono, String direccion, String fechaRegistro, List<Mascota> mascotas) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.mascotas = new ArrayList<>();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void agregarMascota(Mascota mascota){
        mascotas.add(mascota);
    }

    public void eliminarMascota(int idMascota){
        for (Mascota mascota : mascotas) {
            if (mascota.getIdMascota() == idMascota) {
                mascotas.remove(mascota);
                break;
            }
        }
    }

    public void actualizarMascota(int idMascota, String nuevoNombre, String nuevaEspecie, String nuevaRaza, String nuevoSexo, int nuevaEdad, double nuevoPeso){

        for (Mascota mascota : mascotas) {

            if (mascota.getIdMascota() == idMascota) {
                mascota.setNombre(nuevoNombre);
                mascota.setEspecie(nuevaEspecie);
                mascota.setRaza(nuevaRaza);
                mascota.setSexo(nuevoSexo);
                mascota.setEdad(nuevaEdad);
                mascota.setPeso(nuevoPeso);
                break;
            }

        }
    }
}
