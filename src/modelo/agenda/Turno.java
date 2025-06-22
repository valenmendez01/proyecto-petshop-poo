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
        int idMascota;
        Mascota mascotaSeleccionada = null;

        while (true) {
            System.out.print("Ingrese el id de la mascota a asignar turno: ");
            idMascota = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            for (Mascota mascota : listaMascota) {
                if (mascota.getIdMascota() == idMascota) {
                    mascotaSeleccionada = mascota;
                    break;
                }
            }

            if (mascotaSeleccionada != null) {
                break; // ID válido, salimos del bucle
            } else {
                System.out.println("ID inválido. Intente nuevamente.");
            }
        }
        System.out.println("Mascota seleccionada: " + mascotaSeleccionada.getNombre());

        String especie = mascotaSeleccionada.getEspecie(); // se envia a Servicio para calcular costos extras por especie
        servicio = elegirServicio(scanner);

        System.out.print("Ingrese fecha (dd/MM/yyyy): ");
        String fecha = scanner.nextLine();

        System.out.print("Ingrese el horario de inicio (hh:mm): ");
        String horaInicio = scanner.nextLine();

        System.out.print("Ingrese el horario de fin (hh:mm): ");
        String horaFin = scanner.nextLine();
        System.out.println("---------");

        double precioTotal = 0;

        Turno turno = new Turno(idMascota, servicio, fecha, horaInicio, horaFin, precioTotal);

        // Calcula al precio total y setea al precio del turno antes de agregarlo al archivo
        servicio.calcularCosto(turno, especie);

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
                double precio = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Ingrese el nombre del cirujano: ");
                String nombre = scanner.nextLine();

                System.out.print("¿Requiere anestesia? (Y/N): ");
                String requiere = scanner.nextLine();

                boolean requiereAnestesia = false;
                if (requiere.equals("Y")) {
                    requiereAnestesia = true;
                }

                return new Cirugia(precio, nombre, requiereAnestesia);
            case 2:
                System.out.print("Ingrese el precio: ");
                double precio2 = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Ingrese el nombre del paseador: ");
                String nombre2 = scanner.nextLine();

                System.out.print("¿Será un paseo individual? (Y/N): ");
                String esIndividual = scanner.nextLine();

                boolean individual = false;
                if (esIndividual.equals("Y")) {
                    individual = true;
                }
                return new Paseo(precio2, nombre2, individual);
            case 3:
                System.out.print("Ingrese el precio: ");
                double precio3 = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Ingrese el nombre del veterinario: ");
                String nombre3 = scanner.nextLine();

                System.out.print("¿Es una urgencia? (Y/N): ");
                String urgencia = scanner.nextLine();

                boolean esUrgencial = false;
                if (urgencia.equals("Y")) {
                    esUrgencial = true;
                }
                return new Veterinaria(precio3, nombre3, esUrgencial);
            case 4:
                System.out.print("Ingrese el precio: ");
                double precio4 = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Ingrese el nombre del peluquero: ");
                String nombre4 = scanner.nextLine();

                System.out.print("¿Será un paseo individual? (Y/N): ");
                String incluye = scanner.nextLine();

                boolean incluyeCorteUnas = false;
                if (incluye.equals("Y")) {
                    incluyeCorteUnas = true;
                }
                return new Peluqueria(precio4, nombre4, incluyeCorteUnas);
            default:
                System.out.println("Opción inválida.");
                return null;
        }
    }

    // Obtener todas los turnos del archivo
    public static void obtenerTurnosArchivo() {

        File archivo = new File("src/datos/turnos.txt");

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
            System.out.println("No hay turnos para mostrar");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");

                String conEspecial = "No";
                if (datos[5].equals("true")) {
                    conEspecial = "Si";
                }

                System.out.println(
                    "-- Turno: " +
                            "idTurno= " + datos[0] +
                            ", idMascota= " + datos[1] +
                            ", Servicio= " + datos[2] +
                            ", Precio total= " + datos[3] +
                            ", Especialista= " + datos[4] +
                            ", Servicio especial= " + conEspecial +
                            ", fecha= " + datos[6] +
                            ", horaInicio= " + datos[7] +
                            ", horaFin= " + datos[8] +
                            '}'
                );
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer turnos: " + e.getMessage());
        }
    }

    public static boolean eliminarTurnoArchivo(Scanner scanner) {
        System.out.print("Ingrese el id del turno a eliminar: ");
        int idTurno = scanner.nextInt();
        scanner.nextLine();

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
                int id = Integer.parseInt(datos[0]);
                if (id != idTurno) {
                    writer.write(linea + "\n");
                } else {
                    eliminado = true;
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

                int id = Integer.parseInt(datos[0]);

                if (id == idTurno) {
                    // Solicitar nuevos datos

                    int idMascota = Integer.parseInt(datos[1]);

                    String especie = obtenerEspeciePorIdMascota(idMascota);

                    servicio = elegirServicio(scanner);

                    System.out.print("Ingrese nueva fecha (dd/MM/yyyy): ");
                    String fecha = scanner.nextLine();

                    System.out.print("Ingrese nuevo horario de inicio (hh:mm): ");
                    String horaInicio = scanner.nextLine();

                    System.out.print("Ingrese nuevo horario de fin (hh:mm): ");
                    String horaFin = scanner.nextLine();

                    double precioTotal = 0;

                    Turno turno = new Turno(idMascota, servicio, fecha, horaInicio, horaFin, precioTotal);

                    // Calcula al precio total y setea al precio del turno antes de agregarlo al archivo
                    servicio.calcularCosto(turno, especie);

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

    public static String obtenerEspeciePorIdMascota(int idMascota) {
        File archivo = new File("src/datos/mascotas.txt");

        String especie = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int idMascotaLeida = Integer.parseInt(datos[0]);

                if (idMascotaLeida == idMascota) {
                    especie = datos[3];
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer mascotas: " + e.getMessage());
        }

        return especie;
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
