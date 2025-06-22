package modelo.agenda;

import modelo.cliente.Mascota;
import modelo.servicios.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Turno {
    private static int contadorId = 1;

    private int idTurno;
    private int idMascota;
    private static Servicio servicio;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private double precioTotal;

    public Turno(int idMascota, Servicio servicio, String fecha, String horaInicio, String horaFin, double precioTotal) {
        this.idTurno = contadorId++;
        this.idMascota = idMascota;
        this.servicio = servicio;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precioTotal = precioTotal;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
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

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public static void agregarTurnoArchivo(Scanner scanner){
        inicializarContadorId();

        List<Mascota> listaMascota = Mascota.obtenerMascotasPorCliente(scanner);
        // ver idMascotas del cliente deseado
        System.out.print("Nombre e id de las mascotas del cliente: " + "\n");
        for (Mascota mascota : listaMascota) {
            System.out.println("Nombre: " + mascota.getNombre() + " - Id " + mascota.getIdMascota());
        }
        // indicar el idMascota a asignar turno
        System.out.print("Ingrese el id de la mascota a asignar turno: ");
        int idMascota = scanner.nextInt();
        scanner.nextLine();

        servicio = elegirServicio(scanner);

        System.out.print("Ingrese fecha (dd/MM/yyyy): ");
        String fecha = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Ingrese el horario de inicio (hh:mm): ");
        String horaInicio = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Ingrese el horario de fin (hh:mm): ");
        String horaFin = scanner.nextLine();
        scanner.nextLine();

        double precioTotal = 0;

        Turno turno = new Turno(idMascota, servicio, fecha, horaInicio, horaFin, precioTotal);

        // Calcula al precio total y setea al precio del turno antes de agregarlo al archivo
        double precioTotalCalculado = servicio.calcularCosto(turno);
        turno.setPrecioTotal(precioTotalCalculado);

        File archivo = new File("src/datos/turnos.txt");

        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(turno.idTurno + "," +
                turno.idMascota + "," +
                turno.servicio.toCSV() + "," +
                turno.fecha + "," +
                turno.horaInicio + "," +
                turno.horaFin + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }

        System.out.println("Turno creado con ID: " + turno.getIdTurno());
    }

    public static Servicio elegirServicio(Scanner scanner) {

        System.out.println("Seleccione un servicio: ");
        System.out.println("1. Cirugía");
        System.out.println("2. Paseo");
        System.out.println("3. Veterinaria");
        System.out.println("4. Peluquería");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el precio: ");
                double precioBloque = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Ingrese el nombre del cirujano: ");
                String nombre = scanner.nextLine();

                System.out.print("¿Requiere anestesia? (Y/N): ");
                String requiere = scanner.nextLine();

                boolean requiereAnestesia = false;
                if (requiere.equals("Y")) {
                    requiereAnestesia = true;
                }

                return new Cirugia(precioBloque, nombre, requiereAnestesia);
            case 2:
                System.out.print("Ingrese el precio: ");
                double precioBloque2 = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Ingrese el nombre del paseador: ");
                String nombre2 = scanner.nextLine();

                System.out.print("¿Será un paseo individual? (Y/N): ");
                String esIndividual = scanner.nextLine();

                boolean individual = false;
                if (esIndividual.equals("Y")) {
                    individual = true;
                }
                return new Paseo(precioBloque2, nombre2, individual);
            case 3:
                System.out.print("Ingrese el precio: ");
                double precioBloque3 = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Ingrese el nombre del veterinario: ");
                String nombre3 = scanner.nextLine();

                System.out.print("¿Es una urgencia? (Y/N): ");
                String urgencia = scanner.nextLine();

                boolean esUrgencial = false;
                if (urgencia.equals("Y")) {
                    esUrgencial = true;
                }
                return new Veterinaria(precioBloque3, nombre3, esUrgencial);
            case 4:
                System.out.print("Ingrese el precio: ");
                double precioBloque4 = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Ingrese el nombre del peluquero: ");
                String nombre4 = scanner.nextLine();

                System.out.print("¿Será un paseo individual? (Y/N): ");
                String incluye = scanner.nextLine();

                boolean incluyeCorteUnas = false;
                if (incluye.equals("Y")) {
                    incluyeCorteUnas = true;
                }
                return new Peluqueria(precioBloque4, nombre4, incluyeCorteUnas);
            default:
                System.out.println("Opción inválida.");
                return null;
        }
    }

    // Obtener todas los turnos del archivo
    public static List<Turno> obtenerTurnosArchivo() {
        List<Turno> turnos = new ArrayList<Turno>();
        File archivo = new File("src/datos/turnos.txt");

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
            return turnos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 8) {
                    String tipoServicio = datos[2];
                    int idMascota = Integer.parseInt(datos[1]);
                    Servicio servicio;

                    switch (tipoServicio) {
                        case "Cirugia":
                            // ejemplo de campos: precio, nombre, requiereAnestesia
                            double precioC = Double.parseDouble(datos[3]);
                            String nombreC = datos[4];
                            boolean anestesia = Boolean.parseBoolean(datos[5]);
                            servicio = new Cirugia(precioC, nombreC, anestesia);
                            break;
                        case "Paseo":
                            double precioP = Double.parseDouble(datos[3]);
                            String nombreP = datos[4];
                            boolean individual = Boolean.parseBoolean(datos[5]);
                            servicio = new Paseo(precioP, nombreP, individual);
                            break;
                        case "Veterinaria":
                            double precioV = Double.parseDouble(datos[3]);
                            String nombreV = datos[4];
                            boolean urgencia = Boolean.parseBoolean(datos[5]);
                            servicio = new Veterinaria(precioV, nombreV, urgencia);
                            break;
                        case "Peluqueria":
                            double precioPel = Double.parseDouble(datos[3]);
                            String nombrePel = datos[4];
                            boolean corte = Boolean.parseBoolean(datos[5]);
                            servicio = new Peluqueria(precioPel, nombrePel, corte);
                            break;
                        default:
                            System.out.println("Servicio no reconocido: " + tipoServicio);
                            continue;
                    }
                        String fecha = datos[7];
                        String horaInicio = datos[8];
                        String horaFin = datos[9];
                        String precioTotal = datos[9];

                        Turno t = new Turno(idMascota, servicio, fecha, horaInicio, horaFin, Double.parseDouble(precioTotal));
                        t.setIdTurno(Integer.parseInt(datos[0]));
                        turnos.add(t);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer turnos: " + e.getMessage());
        }

        return turnos;
    }

    public static boolean eliminarTurnoArchivo(Scanner scanner) {
        System.out.print("Ingrese el id del turno a eliminar: ");
        int idTurno = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Turno encontrado");

        File inputFile = new File("src/datos/turnos.txt");
        File tempFile = new File("src/datos/turnos_temp.txt");

        if (!inputFile.exists()) {
            return false;
        }

        boolean eliminado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 10) {
                    int id = Integer.parseInt(datos[0]);
                    if (id != idTurno) {
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

    public static boolean actualizarTurnoArchivo(Scanner scanner) {
        System.out.print("Ingrese el ID del turno a actualizar: ");
        int idTurno = scanner.nextInt();
        scanner.nextLine();

        File inputFile = new File("src/datos/turnos.txt");
        File tempFile = new File("src/datos/turnos_temp.txt");

        boolean actualizado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 10) {
                    int id = Integer.parseInt(datos[0]);

                    if (id == idTurno) {
                        // Solicitar nuevos datos

                        int idMascota = Integer.parseInt(datos[1]);

                        servicio = elegirServicio(scanner);

                        System.out.print("Ingrese nueva fecha (dd/MM/yyyy): ");
                        String fecha = scanner.nextLine();
                        scanner.nextLine();

                        System.out.print("Ingrese nuevo horario de inicio (hh:mm): ");
                        String horaInicio = scanner.nextLine();
                        scanner.nextLine();
                        System.out.print("Ingrese nuevo horario de fin (hh:mm): ");
                        String horaFin = scanner.nextLine();
                        scanner.nextLine();

                        double precioTotal = 0;

                        Turno turno = new Turno(idMascota, servicio, fecha, horaInicio, horaFin, precioTotal);

                        // Calcula al precio total y setea al precio del turno antes de agregarlo al archivo
                        double precioTotalCalculado = servicio.calcularCosto(turno);
                        turno.setPrecioTotal(precioTotalCalculado);

                        writer.write(id + "," +
                                turno.idMascota + "," +
                                turno.servicio.toCSV() + "," +
                                turno.fecha + "," +
                                turno.horaInicio + "," +
                                turno.horaFin + "\n"
                        );
                        actualizado = true;
                    } else {
                        writer.write(linea);
                        writer.newLine();
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }

        if (actualizado) {
            if (inputFile.delete()) {
                if (tempFile.renameTo(inputFile)) {
                    return true;
                }
            }
        } else {
            tempFile.delete();
        }

        return false;
    }

    public static void inicializarContadorId() {
        File archivo = new File("src/datos/turnos.txt");

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
        return "Turno{" +
                "idTurno=" + idTurno +
                ", idMascota=" + idMascota +
                ", fecha='" + fecha + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                ", precioTotal='" + precioTotal + '\'' +
                '}';
    }
}
