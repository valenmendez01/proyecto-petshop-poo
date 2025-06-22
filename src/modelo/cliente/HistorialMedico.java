package modelo.cliente;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HistorialMedico {
    private static int contadorId = 1;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private int idHistorial;
    private int idMascota;
    private String fecha;
    private String observacion;
    private String diagnostico;
    private String tratamiento;
    private String veterinario;
    private double peso; // Peso al momento de la consulta

    // Constructor
    public HistorialMedico(int idMascota, String fecha, String observacion, String diagnostico,
                           String tratamiento, String veterinario, double peso) {
        this.idHistorial = contadorId++;
        this.idMascota = idMascota;
        this.fecha = fecha;
        this.observacion = observacion;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.veterinario = veterinario;
        this.peso = peso;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacionn(String observacion) {
        this.observacion = observacion;
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

    // Obtener todos los historiales médicos del archivo
    public static List<HistorialMedico> obtenerHistorialesArchivo() {
        List<HistorialMedico> historiales = new ArrayList<HistorialMedico>();
        File archivo = new File("src/datos/historiales_medicos.txt");

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
            return historiales;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) {
                    HistorialMedico h = new HistorialMedico(Integer.parseInt(datos[1]), datos[2], datos[3], datos[4], datos[5], datos[6], Double.parseDouble(datos[7]));
                    int id = Integer.parseInt(datos[0]);
                    h.setIdHistorial(id);
                    historiales.add(h);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer historiales: " + e.getMessage());
        }

        return historiales;
    }

    // Agregar un historial médico nuevo
    public static void agregarHistorialArchivo(Scanner scanner) {
        System.out.print("Ingrese el id de la mascota asociado al historial a agregar: ");
        int mascotaId = scanner.nextInt();
        scanner.nextLine();

        if (validarExistenciaMascota(mascotaId)){
            System.out.println("Mascota encontrada");

            System.out.println("Ingrese fecha: ");
            String fecha = scanner.nextLine();
            System.out.println("Ingrese observación: ");
            String observacion = scanner.nextLine();
            System.out.println("Ingrese diagnostico: ");
            String diagnostico = scanner.nextLine();
            System.out.println("Ingrese tratamiento: ");
            String tratamiento = scanner.nextLine();
            System.out.println("Indique el veterinario: ");
            String veterinario = scanner.nextLine();
            System.out.println("Ingrese el peso: ");
            double peso = scanner.nextDouble();

            inicializarContadorId();

            HistorialMedico historial = new HistorialMedico(mascotaId, fecha, observacion, diagnostico, tratamiento, veterinario, peso);

            File archivo = new File("src/datos/historiales.txt");

            try (FileWriter writer = new FileWriter(archivo, true)) {
                writer.write(historial.idHistorial + "," +
                        historial.getIdMascota() + "," +
                        historial.getFecha() + "," +
                        historial.getObservacion() + "," +
                        historial.getDiagnostico() + "," +
                        historial.getTratamiento() + "," +
                        historial.getVeterinario() + "," +
                        historial.getPeso() + "\n");
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo mascotas: " + e.getMessage());
            }

            System.out.println("Historial creado con ID: " + historial.getIdHistorial());

        } else {
            System.out.println("Mascota no encontrada o no existente");
        }
    }

    public static boolean validarExistenciaMascota(int mascotaId) {
        File inputFile = new File("src/datos/mascotas.txt");
        if (!inputFile.exists()) {
            return false;
        }

        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) {
                    if (mascotaId == Integer.parseInt(datos[0])) {
                        encontrado = true;
                        break;
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        return encontrado;
    };

    // Eliminar mascota por ID. Se usa desde el main, por lo que hay que buscar el cliente que corresponda
    public static boolean eliminarHistorialArchivo(Scanner scanner) {

        List<HistorialMedico> historiales = obtenerHistorialesPorMascota(scanner);

        // ver idHistoriales de la mascota deseada
        System.out.println("Fecha, observación e id de los historiales de la mascota: ");
        for (HistorialMedico historial : historiales) {
            System.out.println("Fecha: " + historial.getFecha() + "- Observación" + historial.getObservacion() +  "- Id" + historial.getIdHistorial());
        }

        // indicar el idHistorial a eliminar
        System.out.println("Ingrese el id del historial a eliminar:");
        int idEliminar = scanner.nextInt();

        File inputFile = new File("src/datos/historiales.txt");
        File tempFile = new File("src/datos/historiales_temp.txt");

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

    // Eliminar todos los historiales de una mascota específica.
    // Se usa en la clase Mascota (eliminarMascotaArchivo() y eliminarMascotasPorIdCliente())
    public static boolean eliminarHistorialesPorIdMascota(int idMascota) {
        File inputFile = new File("src/datos/historiales.txt");
        File tempFile = new File("src/datos/historiales_temp.txt");

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
                    int idMascotaLeida = Integer.parseInt(datos[1]);
                    if (idMascotaLeida != idMascota) {
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

    // Actualizar historial por ID
    public static boolean actualizarHistorialArchivo(Scanner scanner) {
        List<HistorialMedico> historiales = obtenerHistorialesPorMascota(scanner);

        // ver idHistoriales de la mascota deseada
        System.out.println("Fecha, observación e id de los historiales de la mascota: ");
        for (HistorialMedico historial : historiales) {
            System.out.println("Fecha: " + historial.getFecha() + "- Observación" + historial.getObservacion() +  "- Id" + historial.getIdHistorial());
        }

        // indicar el idHistorial a actualizar
        System.out.println("Ingrese el id del historial a actualizar: ");
        int historialId = scanner.nextInt();

        int mascotaId = historiales.get(0).getIdMascota();
        System.out.println("Nueva fecha: ");
        String fecha = scanner.nextLine();
        System.out.println("Nueva observación: ");
        String observacion = scanner.nextLine();
        System.out.println("Nuevo diagnostico: ");
        String diagnostico = scanner.nextLine();
        System.out.println("Nuevo tratamiento: ");
        String tratamiento = scanner.nextLine();
        System.out.println("Nuevo veterinario: ");
        String veterinario = scanner.nextLine();
        System.out.println("Nuevo peso: ");
        double peso = scanner.nextDouble();
        HistorialMedico historialActualizar = new HistorialMedico(mascotaId, fecha, observacion, diagnostico, tratamiento, veterinario, peso);

        File inputFile = new File("src/datos/historiales.txt");
        File tempFile = new File("src/datos/historiales_temp.txt");

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
                    if (historialId == Integer.parseInt(datos[0])) {
                        writer.write(historialId + "," +
                                historialActualizar.getIdMascota() + "," +
                                historialActualizar.getFecha() + "," +
                                historialActualizar.getObservacion() + "," +
                                historialActualizar.getDiagnostico() + "," +
                                historialActualizar.getTratamiento() + "," +
                                historialActualizar.getVeterinario() + "," +
                                historialActualizar.getPeso() + "\n");
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

    // Obtener historiales por mascota específica
    public static List<HistorialMedico> obtenerHistorialesPorMascota(Scanner scanner) {
        System.out.println("Ingrese el id de la mascota: ");
        int idMascota = scanner.nextInt();

        List<HistorialMedico> historiales = new ArrayList<HistorialMedico>();

        if (validarExistenciaMascota(idMascota)) {
            System.out.println("Mascota encontrada");

            File archivo = new File("src/datos/historiales.txt");

            if (!archivo.exists()) {
                return historiales;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 8) {
                        int idMascotaLeida = Integer.parseInt(datos[1]);

                        if (idMascotaLeida == idMascota) {
                            HistorialMedico h = new HistorialMedico(Integer.parseInt(datos[1]), datos[2], datos[3], datos[4], datos[5], datos[6], Double.parseDouble(datos[7]));
                            int id = Integer.parseInt(datos[0]);
                            h.setIdMascota(id);
                            historiales.add(h);
                        }
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer historiales: " + e.getMessage());
            }

        } else {
            System.out.println("Mascota no encontrada o no existente");
        }

        return historiales;
    }

    public static void inicializarContadorId() {
        File archivo = new File("src/datos/historiales.txt");

        int maxId = 0;

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length >= 1) {
                        int id = Integer.parseInt(partes[0]);
                        if (id > maxId) {
                            maxId = id;
                        }
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer IDs del archivo: " + e.getMessage());
            }
        }

        contadorId = maxId + 1;
    }

    @Override
    public String toString() {
        return "HistorialMedico{" +
                "idHistorial=" + idHistorial +
                ", idMascota=" + idMascota +
                ", fecha=" + fecha +
                ", observacion='" + observacion + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", veterinario='" + veterinario + '\'' +
                ", peso=" + peso + '\'' +
                '}';
    }
}