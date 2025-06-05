package modelo.cliente;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistorialMedico {
    private static int contadorId = 1;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private int idHistorial;
    private int idMascota;
    private LocalDate fecha;
    private String descripcion;
    private String diagnostico;
    private String tratamiento;
    private String veterinario;
    private double peso; // Peso al momento de la consulta
    private String observaciones;

    // Constructor
    public HistorialMedico(int idMascota, LocalDate fecha, String descripcion, String diagnostico,
                           String tratamiento, String veterinario, double peso, String observaciones) {
        this.idHistorial = contadorId++;
        this.idMascota = idMascota;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.veterinario = veterinario;
        this.peso = peso;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // Obtener todos los historiales médicos del archivo
    public static List<HistorialMedico> obtenerHistorialesArchivo() {
        List<HistorialMedico> historiales = new ArrayList<HistorialMedico>();
        File archivo = new File("datos/historiales_medicos.txt");

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
            // Crear directorio si no existe
            archivo.getParentFile().mkdirs();
            return historiales;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int maxId = 0;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 8) { // Validar que tenga al menos los campos básicos
                    int idHistorialLeido = Integer.parseInt(datos[0]);
                    int idMascotaLeido = Integer.parseInt(datos[1]);
                    LocalDate fechaLeida = LocalDate.parse(datos[2], FORMATO_FECHA);

                    // Crear el historial
                    HistorialMedico h = new HistorialMedico(
                            idMascotaLeido,
                            fechaLeida,
                            datos[3], // descripcion
                            datos[4], // diagnostico
                            datos[5], // tratamiento
                            datos[6], // veterinario
                            Double.parseDouble(datos[7]), // peso
                            datos.length > 8 ? datos[8] : "" // observaciones (opcional)
                    );
                    h.setIdHistorial(idHistorialLeido);
                    historiales.add(h);

                    // Actualizar el contador para mantener IDs únicos
                    if (idHistorialLeido > maxId) {
                        maxId = idHistorialLeido;
                    }
                }
            }

            // Actualizar el contador global
            contadorId = maxId + 1;

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer historiales médicos: " + e.getMessage());
        }

        return historiales;
    }

    // Agregar un historial médico nuevo
    public static void agregarHistorialArchivo(HistorialMedico historial) {
        File archivo = new File("datos/historiales_medicos.txt");
        // Crear directorio si no existe
        archivo.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(historial.getIdHistorial() + "," +
                    historial.getIdMascota() + "," +
                    historial.getFecha().format(FORMATO_FECHA) + "," +
                    historial.getDescripcion() + "," +
                    historial.getDiagnostico() + "," +
                    historial.getTratamiento() + "," +
                    historial.getVeterinario() + "," +
                    historial.getPeso() + "," +
                    historial.getObservaciones() + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de historiales: " + e.getMessage());
        }
    }

    // Obtener historiales por mascota específica
    public static List<HistorialMedico> obtenerHistorialesPorMascota(int idMascota) {
        List<HistorialMedico> historiales = new ArrayList<HistorialMedico>();
        File archivo = new File("datos/historiales_medicos.txt");

        if (!archivo.exists()) {
            return historiales;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 8) {
                    int idHistorialLeido = Integer.parseInt(datos[0]);
                    int idMascotaLeido = Integer.parseInt(datos[1]);

                    if (idMascotaLeido == idMascota) {
                        LocalDate fechaLeida = LocalDate.parse(datos[2], FORMATO_FECHA);

                        HistorialMedico h = new HistorialMedico(
                                idMascotaLeido,
                                fechaLeida,
                                datos[3], // descripcion
                                datos[4], // diagnostico
                                datos[5], // tratamiento
                                datos[6], // veterinario
                                Double.parseDouble(datos[7]), // peso
                                datos.length > 8 ? datos[8] : "" // observaciones
                        );
                        h.setIdHistorial(idHistorialLeido);
                        historiales.add(h);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer historiales médicos: " + e.getMessage());
        }

        return historiales;
    }

    // Buscar historial por ID
    public static HistorialMedico buscarHistorialPorId(int idHistorial) {
        List<HistorialMedico> historiales = obtenerHistorialesArchivo();
        for (HistorialMedico historial : historiales) {
            if (historial.getIdHistorial() == idHistorial) {
                return historial;
            }
        }
        return null;
    }

    // Eliminar historial por ID
    public static boolean eliminarHistorialArchivo(int idEliminar) {
        File inputFile = new File("datos/historiales_medicos.txt");
        File tempFile = new File("datos/historiales_temp.txt");

        if (!inputFile.exists()) {
            return false;
        }

        boolean eliminado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 8) {
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

    // Actualizar historial por ID
    public static boolean actualizarHistorialArchivo(HistorialMedico historialActualizado) {
        File inputFile = new File("datos/historiales_medicos.txt");
        File tempFile = new File("datos/historiales_temp.txt");

        if (!inputFile.exists()) {
            return false;
        }

        boolean actualizado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 8) {
                    int id = Integer.parseInt(datos[0]);

                    if (id == historialActualizado.getIdHistorial()) {
                        writer.write(historialActualizado.getIdHistorial() + "," +
                                historialActualizado.getIdMascota() + "," +
                                historialActualizado.getFecha().format(FORMATO_FECHA) + "," +
                                historialActualizado.getDescripcion() + "," +
                                historialActualizado.getDiagnostico() + "," +
                                historialActualizado.getTratamiento() + "," +
                                historialActualizado.getVeterinario() + "," +
                                historialActualizado.getPeso() + "," +
                                historialActualizado.getObservaciones() + "\n");
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
        return "HistorialMedico{" +
                "idHistorial=" + idHistorial +
                ", idMascota=" + idMascota +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", veterinario='" + veterinario + '\'' +
                ", peso=" + peso +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}