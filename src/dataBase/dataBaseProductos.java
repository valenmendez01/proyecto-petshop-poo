package dataBase;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelo.producto.AlimentoMascota;
import modelo.producto.Producto;
import modelo.producto.ArticuloVarios;

public class dataBaseProductos {
    private static String archivo = "src/datos/productos.txt";
    public static ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));) {
            String linea;
            int numeroLinea = 0;
            while ((linea = reader.readLine()) != null) {
                numeroLinea++;
                String[] partes = linea.split(";");
                try {
                    if (partes.length == 8) {
                        ArticuloVarios articulo = new ArticuloVarios(
                                Integer.parseInt(partes[0]), partes[1],
                                Double.parseDouble(partes[2]),
                                Integer.parseInt(partes[3]),
                                partes[4], partes[5], partes[6], partes[7]
                        );
                        productos.add(articulo);
                    } else if (partes.length == 10) {
                        Date fechaVencimiento = Date.valueOf(partes[7]);

                        AlimentoMascota alimento = new AlimentoMascota(
                                Integer.parseInt(partes[0]), partes[1],
                                Double.parseDouble(partes[2]),
                                Integer.parseInt(partes[3]),
                                partes[4], partes[5], partes[6],
                                fechaVencimiento, partes[8]
                        );
                        productos.add(alimento);
                    } else {
                        System.out.println("Línea " + numeroLinea + " ignorada: cantidad de campos inválida.");
                    }
                } catch (Exception ex) {
                    System.out.println("Error en la línea " + numeroLinea + ": " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los productos: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return productos;
    }

    private static String lineaProducto(ArticuloVarios articulo) {
        return articulo.getIdProducto() + ";" +
                articulo.getNombre() + ";" +
                articulo.getPrecio() + ";" +
                articulo.getStock() + ";" +
                articulo.getDescripcion() + ";" +
                articulo.getCategoria() + ";" +
                articulo.getMarca() + ";" +
                articulo.getMaterial() + ";";
    }

    private static String lineaProducto(AlimentoMascota alimento) {
        return alimento.getIdProducto() + ";" +
                alimento.getNombre() + ";" +
                alimento.getPrecio() + ";" +
                alimento.getStock() + ";" +
                alimento.getDescripcion() + ";" +
                alimento.getEspecieObjetivo() + ";" +
                alimento.getEtapaVida() + ";" +
                alimento.getFechaVencimiento() + ";" +
                alimento.getInfoNutricional() + ";";
    }

    public static void guardarProducto(ArticuloVarios articulo) {
        String linea = lineaProducto(articulo);
        escribirArchivo(linea);
    }

    public static void guardarProducto(AlimentoMascota alimento) {
        String linea = lineaProducto(alimento);
        escribirArchivo(linea);
    }
    private static void escribirArchivo(String producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(producto);
            writer.newLine();

            System.out.println("Producto guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
        }
    }

    public static ArrayList<Producto> actualizarProducto(Producto producto) {
        ArrayList<Producto> productos = obtenerProductos();

        boolean encontrado = false;

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getIdProducto()==(producto.getIdProducto())) {
                productos.set(i, producto);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Producto con ID " + producto.getIdProducto() + " no encontrado.");
            throw new RuntimeException("Producto no encontrado.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Producto p : productos) {
                String linea;
                if (p instanceof ArticuloVarios) {
                    linea = lineaProducto((ArticuloVarios) p);
                } else if (p instanceof AlimentoMascota) {
                    linea = lineaProducto((AlimentoMascota) p);
                } else {
                    continue;
                }

                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Producto actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }

        return productos;
    }

}