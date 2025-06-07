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
                                partes[0], partes[1],
                                Double.parseDouble(partes[2]),
                                Integer.parseInt(partes[3]),
                                partes[4], partes[5], partes[6], partes[7]
                        );
                        productos.add(articulo);
                    } else if (partes.length == 10) {
                        List<String> ingredientes = new ArrayList<>(Arrays.asList(partes[9].split("\\|")));

                        Date fechaVencimiento = Date.valueOf(partes[7]);

                        AlimentoMascota alimento = new AlimentoMascota(
                                partes[0], partes[1],
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
    public static void guardarArticuloVarios(ArticuloVarios articulo) {
        StringBuilder linea = new StringBuilder();
        linea.append(articulo.getIdProducto()).append(";")
                .append(articulo.getNombre()).append(";")
                .append(articulo.getPrecio()).append(";")
                .append(articulo.getStock()).append(";")
                .append(articulo.getDescripcion()).append(";")
                .append(articulo.getCategoria()).append(";")
                .append(articulo.getMarca()).append(";")
                .append(articulo.getMaterial()).append(";");

        escribirArchivo(linea.toString());
    }
    public static void guardarAlimentoMascota(AlimentoMascota articulo) {
        StringBuilder linea = new StringBuilder();
        linea.append(articulo.getIdProducto()).append(";")
                .append(articulo.getNombre()).append(";")
                .append(articulo.getPrecio()).append(";")
                .append(articulo.getStock()).append(";")
                .append(articulo.getDescripcion()).append(";")
                .append(articulo.getEspecieObjetivo()).append(";")
                .append(articulo.getEtapaVida()).append(";")
                .append(articulo.getFechaVencimiento()).append(";")
                .append(articulo.getInfoNutricional()).append(";");

        escribirArchivo(linea.toString());
    }
    private static void escribirArchivo(String producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(producto);
            writer.newLine();

            System.out.println("Producto guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
        }
    }
}
