package modelo.producto;


import java.io.*;
import java.util.Scanner;

public abstract class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;
    private String tipo;

    public Producto(int idProducto, String nombre, double precio, int stock, String tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.tipo = tipo;
    }

    public int getIdProducto() {
        return idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return idProducto + ";" + nombre + ";" + precio + ";" + stock + ";" + tipo;
    }

    public String toDataString() {
        return idProducto + ";" + nombre + ";" + precio + ";" + stock;
    }

    public static void crearProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID del producto: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio unitario: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock inicial: ");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese '1' para Artículo Vario o '2' para Alimento de Mascota: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        Producto nuevoProducto;

        if (tipo == 1) {
            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine();

            System.out.print("Material: ");
            String material = scanner.nextLine();

            nuevoProducto = new ArticuloVarios(id, nombre, precio, stock, marca, categoria, material);
        } else if (tipo == 2) {
            System.out.print("Especie objetivo: ");
            String especie = scanner.nextLine();

            System.out.print("Etapa de vida: ");
            String etapa = scanner.nextLine();

            System.out.print("Información nutricional: ");
            String infoNutricional = scanner.nextLine();

            nuevoProducto = new AlimentoMascota(id, nombre, precio, stock, especie, etapa, infoNutricional);
        } else {
            System.out.println("Tipo no válido.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/datos/productos.txt", true))) {
            writer.write(nuevoProducto.toDataString());
            writer.newLine();
            System.out.println("Producto guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el producto: " + e.getMessage());
        }
    }

    public static void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del producto a eliminar: ");
        String idEliminar = scanner.nextLine();

        File inputFile = new File("src/datos/productos.txt");
        File tempFile = new File("src/datos/productos_temp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String linea;
            boolean encontrado = false;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (!datos[0].equals(idEliminar)) {
                    writer.write(linea);
                    writer.newLine();
                } else {
                    encontrado = true;
                }
            }

            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                if (encontrado)
                    System.out.println("Producto eliminado.");
                else
                    System.out.println("Producto no encontrado.");
            } else {
                System.out.println("Error al actualizar el archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void modificarStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del producto a modificar stock: ");
        String idModificar = scanner.nextLine();

        File inputFile = new File("src/datos/productos.txt");
        File tempFile = new File("src/datos/productos_temp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String linea;
            boolean encontrado = false;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(idModificar)) {
                    System.out.print("Ingrese nuevo stock: ");
                    int nuevoStock = Integer.parseInt(scanner.nextLine());
                    datos[3] = String.valueOf(nuevoStock);
                    writer.write(String.join(";", datos));
                    writer.newLine();
                    encontrado = true;
                } else {
                    writer.write(linea);
                    writer.newLine();
                }
            }

            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                if (encontrado)
                    System.out.println("Stock actualizado.");
                else
                    System.out.println("Producto no encontrado.");
            } else {
                System.out.println("Error al actualizar el archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void verProductos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/datos/productos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length < 8) continue;

                String tipo = partes[4];

                if (tipo.equals("ArticuloVarios")) {
                    System.out.println("🛠 ID: " + partes[0] + " | Nombre: " + partes[1] + " | Precio: $" + partes[2] +
                            " | Stock: " + partes[3] + " | Tipo: Artículo Varios | Marca: " + partes[5] +
                            " | Categoría: " + partes[6] + " | Material: " + partes[7]);
                } else if (tipo.equals("AlimentoMascota")) {
                    System.out.println("🐾 ID: " + partes[0] + " | Nombre: " + partes[1] + " | Precio: $" + partes[2] +
                            " | Stock: " + partes[3] + " | Tipo: Alimento Mascota | Especie: " + partes[5] +
                            " | Etapa: " + partes[6] + " | Info Nutricional: " + partes[7]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer productos: " + e.getMessage());
        }
    }
    public static Producto buscarProductoPorId(String idBuscado) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/datos/productos.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes[0].equals(idBuscado)) {
                    String tipo = partes[4];
                    if (tipo.equals("ArticuloVarios")) {
                        return new ArticuloVarios(Integer.parseInt(partes[0]), partes[1],
                                Double.parseDouble(partes[2]), Integer.parseInt(partes[3]),
                                partes[5], partes[6], partes[7]);
                    } else if (tipo.equals("AlimentoMascota")) {
                        return new AlimentoMascota(Integer.parseInt(partes[0]), partes[1],
                                Double.parseDouble(partes[2]), Integer.parseInt(partes[3]),
                                partes[5], partes[6], partes[7]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
        return null;
    }


}

