package modelo.cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    // Obtener todas las mascotas del archivo
    public static List<Mascota> obtenerMascotasArchivo() {
        List<Mascota> mascotas = new ArrayList<Mascota>();
        File archivo = new File("src/datos/mascotas.txt");

        // Si el archivo no existe, devolver lista vacía
        if (!archivo.exists()) {
            return mascotas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) {
                    Mascota m = new Mascota(Integer.parseInt(datos[1]), datos[2], datos[3], datos[4], datos[5], Integer.parseInt(datos[6]), Double.parseDouble(datos[7]));
                    int id = Integer.parseInt(datos[0]);
                    m.setIdMascota(id);
                    mascotas.add(m);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer mascotas: " + e.getMessage());
        }

        return mascotas;
    }

    // Agregar una mascota nueva
    public static void agregarMascotaArchivo(Scanner scanner) {
        System.out.print("Ingrese el id del cliente asociado a la mascota a agregar: ");
        int clienteId = scanner.nextInt();

        if (validarExistenciaCliente(clienteId)){
            System.out.print("Cliente encontrado");

            System.out.print("Ingrese el nombre de la mascota: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la especie de la mascota: ");
            String especie = scanner.nextLine();
            System.out.print("Ingrese la raza de la mascota: ");
            String raza = scanner.nextLine();
            System.out.print("Ingrese el sexo de la mascota: ");
            String sexo = scanner.nextLine();
            System.out.print("Ingrese la edad de la mascota: ");
            int edad = scanner.nextInt();
            System.out.print("Ingrese el peso de la mascota: ");
            double peso = scanner.nextDouble();

            inicializarContadorId();

            Mascota mascota = new Mascota(clienteId, nombre, especie, raza, sexo, edad, peso);

            File archivo = new File("src/datos/mascotas.txt");

            try (FileWriter writer = new FileWriter(archivo, true)) {
                writer.write(mascota.idMascota + "," +
                        mascota.idCliente + "," +
                        mascota.nombre + "," +
                        mascota.especie + "," +
                        mascota.raza + "," +
                        mascota.sexo + "," +
                        mascota.edad + "," +
                        mascota.peso + "\n");
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo mascotas: " + e.getMessage());
            }

            System.out.println("Mascota creada con ID: " + mascota.getIdMascota());

        } else {
            System.out.println("Cliente no encontrado o no existente");
        }
    }

    public static boolean validarExistenciaCliente(int clienteId) {
        File inputFile = new File("src/datos/clientes.txt");
        if (!inputFile.exists()) {
            return false;
        }

        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    if (clienteId == Integer.parseInt(datos[0])) {
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

    // Obtener mascotas por cliente específico
    public static List<Mascota> obtenerMascotasPorCliente(Scanner scanner) {
        System.out.print("Ingrese el id del cliente: ");
        int idCliente = scanner.nextInt();

        List<Mascota> mascotas = new ArrayList<Mascota>();

        if (validarExistenciaCliente(idCliente)) {
            System.out.print("Cliente encontrado");

            File archivo = new File("src/datos/mascotas.txt");

            if (!archivo.exists()) {
                return mascotas;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 8) {
                        int idClienteLeido = Integer.parseInt(datos[1]);

                        if (idClienteLeido == idCliente) {
                            Mascota m = new Mascota(Integer.parseInt(datos[1]), datos[2], datos[3], datos[4], datos[5], Integer.parseInt(datos[6]), Double.parseDouble(datos[7]));
                            int id = Integer.parseInt(datos[0]);
                            m.setIdMascota(id);
                            mascotas.add(m);
                        }
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer mascotas: " + e.getMessage());
            }

        } else {
            System.out.println("Cliente no encontrado o no existente");
        }

        return mascotas;
    }

    // Eliminar mascota por ID. Se usa desde el main, por lo que hay que buscar el cliente que corresponda
    public static boolean eliminarMascotaArchivo(Scanner scanner) {

        List<Mascota> mascotas = obtenerMascotasPorCliente(scanner);

        // ver idMascotas del cliente deseado
        System.out.print("Nombre e id de las mascotas del cliente: ");
        for (Mascota mascota : mascotas) {
            System.out.println("Nombre: " + mascota.getNombre() + " - Id" + mascota.getIdMascota());
        }

        // indicar el idMascota a eliminar
        System.out.print("Ingrese el id de la mascota a eliminar:");
        int idEliminar = scanner.nextInt();

        File inputFile = new File("src/datos/mascotas.txt");
        File tempFile = new File("src/datos/mascotas_temp.txt");

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

    // Eliminar todas las mascotas de un cliente específico. Se usa en la clase Cliente
    public static boolean eliminarMascotasPorIdCliente(int idCliente) {
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
    public static boolean actualizarMascotaArchivo(Scanner scanner) {
        List<Mascota> mascotas = obtenerMascotasPorCliente(scanner);

        // ver idMascotas del cliente deseado
        System.out.print("Nombre e id de las mascotas del cliente: ");
        for (Mascota mascota : mascotas) {
            System.out.println("Nombre: " + mascota.getNombre() + " - Id" + mascota.getIdMascota());
        }

        // indicar el idMascota a actualizar
        System.out.print("Ingrese el id de la mascota a actualizar: ");
        int mascotaId = scanner.nextInt();

        int clienteId = mascotas.get(0).getIdCliente();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva especie: ");
        String especie = scanner.nextLine();
        System.out.print("Nueva raza: ");
        String raza = scanner.nextLine();
        System.out.print("Nuevo sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("Nueva edad: ");
        int edad = scanner.nextInt();
        System.out.print("Nuevo peso: ");
        double peso = scanner.nextDouble();
        Mascota mascotaActualizar = new Mascota(clienteId, nombre, especie, raza, sexo, edad, peso);

        File inputFile = new File("src/datos/mascotas.txt");
        File tempFile = new File("src/datos/mascotas_temp.txt");

        if (!inputFile.exists()) {
            return false;
        }

        boolean actualizado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    if (mascotaId == Integer.parseInt(datos[0])) {
                        writer.write(mascotaId + "," +
                            mascotaActualizar.getIdCliente() + "," +
                            mascotaActualizar.getNombre() + "," +
                            mascotaActualizar.getEspecie() + "," +
                            mascotaActualizar.getRaza() + "," +
                            mascotaActualizar.getSexo() + "," +
                            mascotaActualizar.getEdad() + "," +
                            mascotaActualizar.getPeso() + "\n");
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

    public static void inicializarContadorId() {
        File archivo = new File("src/datos/mascotas.txt");

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
