package modelo.cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Mascota {
    private static int contadorId = 1;

    private int idMascota;
    private int idCliente;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private int edad;
    private double peso;

    // Constructor
    public Mascota(int idCliente, String nombre, String especie, String raza, String sexo, int edad, double peso) {
        this.idMascota = contadorId++;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.edad = edad;
        this.peso = peso;
    }

    // Getters y Setters
    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
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

    // Metodo para obtener todos los historiales médicos de esta mascota
    public List<HistorialMedico> obtenerHistoriales() {
        return HistorialMedico.obtenerHistorialesPorMascota(this.idMascota);
    }

    // Obtener todas las mascotas del archivo
    public static List<Mascota> obtenerMascotasArchivo() {
        List<Mascota> mascotas = new ArrayList<Mascota>();
        File archivo = new File("datos/mascotas.txt");

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
            // Crear directorio si no existe
            archivo.getParentFile().mkdirs();
            return mascotas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int maxId = 0;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) { // Validar que tenga todos los campos
                    int idMascotaLeida = Integer.parseInt(datos[0]);
                    int idClienteLeido = Integer.parseInt(datos[1]);

                    Mascota m = new Mascota(
                            idClienteLeido,
                            datos[2], // nombre
                            datos[3], // especie
                            datos[4], // raza
                            datos[5], // sexo
                            Integer.parseInt(datos[6]), // edad
                            Double.parseDouble(datos[7]) // peso
                    );
                    m.setIdMascota(idMascotaLeida);
                    mascotas.add(m);

                    // Actualizar el contador para mantener IDs únicos
                    if (idMascotaLeida > maxId) {
                        maxId = idMascotaLeida;
                    }
                }
            }

            // Actualizar el contador global
            contadorId = maxId + 1;

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer mascotas: " + e.getMessage());
        }

        return mascotas;
    }

    // Agregar una mascota nueva
    public static void agregarMascotaArchivo(Mascota mascota) {
        File archivo = new File("datos/mascotas.txt");
        // Crear directorio si no existe
        archivo.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(mascota.getIdMascota() + "," +
                    mascota.getIdCliente() + "," +
                    mascota.getNombre() + "," +
                    mascota.getEspecie() + "," +
                    mascota.getRaza() + "," +
                    mascota.getSexo() + "," +
                    mascota.getEdad() + "," +
                    mascota.getPeso() + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo mascotas: " + e.getMessage());
        }
    }

    // Obtener mascotas por cliente específico
    public static List<Mascota> obtenerMascotasPorCliente(int idCliente) {
        List<Mascota> mascotas = new ArrayList<Mascota>();
        File archivo = new File("datos/mascotas.txt");

        if (!archivo.exists()) {
            return mascotas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) {
                    int idMascotaLeida = Integer.parseInt(datos[0]);
                    int idClienteLeido = Integer.parseInt(datos[1]);

                    if (idClienteLeido == idCliente) {
                        Mascota m = new Mascota(
                                idClienteLeido,
                                datos[2], // nombre
                                datos[3], // especie
                                datos[4], // raza
                                datos[5], // sexo
                                Integer.parseInt(datos[6]), // edad
                                Double.parseDouble(datos[7]) // peso
                        );
                        m.setIdMascota(idMascotaLeida);
                        mascotas.add(m);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer mascotas: " + e.getMessage());
        }

        return mascotas;
    }

    // Buscar mascota por ID
    public static Mascota buscarMascotaPorId(int idMascota) {
        List<Mascota> mascotas = obtenerMascotasArchivo();
        for (Mascota mascota : mascotas) {
            if (mascota.getIdMascota() == idMascota) {
                return mascota;
            }
        }
        return null;
    }

    // Eliminar mascota por ID
    public static boolean eliminarMascotaArchivo(int idEliminar) {
        File inputFile = new File("datos/mascotas.txt");
        File tempFile = new File("datos/mascotas_temp.txt");

        if (!inputFile.exists()) {
            return false;
        }

        boolean eliminado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) {
                    int id = Integer.parseInt(datos[0]);
                    if (id != idEliminar) {
                        writer.write(linea + "\n");
                    } else {
                        eliminado = true;
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        // Reemplazar archivo original
        if (eliminado && inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else if (!eliminado) {
            tempFile.delete(); // Limpiar archivo temporal si no se eliminó nada
        }

        return eliminado;
    }

    // Eliminar todas las mascotas de un cliente específico
    public static boolean eliminarMascotasPorCliente(int idCliente) {
        File inputFile = new File("datos/mascotas.txt");
        File tempFile = new File("datos/mascotas_temp.txt");

        if (!inputFile.exists()) {
            return false;
        }

        boolean eliminado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) {
                    int idClienteLeido = Integer.parseInt(datos[1]);
                    if (idClienteLeido != idCliente) {
                        writer.write(linea + "\n");
                    } else {
                        eliminado = true;
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        // Reemplazar archivo original
        if (eliminado && inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else if (!eliminado) {
            tempFile.delete();
        }

        return eliminado;
    }

    // Actualizar mascota por ID
    public static boolean actualizarMascotaArchivo(Mascota mascotaActualizada) {
        File inputFile = new File("datos/mascotas.txt");
        File tempFile = new File("datos/mascotas_temp.txt");

        if (!inputFile.exists()) {
            return false;
        }

        boolean actualizado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) {
                    int id = Integer.parseInt(datos[0]);

                    if (id == mascotaActualizada.getIdMascota()) {
                        writer.write(mascotaActualizada.getIdMascota() + "," +
                                mascotaActualizada.getIdCliente() + "," +
                                mascotaActualizada.getNombre() + "," +
                                mascotaActualizada.getEspecie() + "," +
                                mascotaActualizada.getRaza() + "," +
                                mascotaActualizada.getSexo() + "," +
                                mascotaActualizada.getEdad() + "," +
                                mascotaActualizada.getPeso() + "\n");
                        actualizado = true;
                    } else {
                        writer.write(linea + "\n");
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        // Reemplazar archivo original
        if (actualizado && inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else if (!actualizado) {
            tempFile.delete();
        }

        return actualizado;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "idMascota=" + idMascota +
                ", idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", raza='" + raza + '\'' +
                ", sexo='" + sexo + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                '}';
    }
}
