package dataBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelo.producto.AlimentoMascota;
import modelo.producto.Producto;
import modelo.producto.ArticuloVarios;

public class dataBaseProductos {
    public static ArrayList<Producto> cargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/datos/productos.txt"));) {
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
                    } else if (partes.length == 11) {
                        List<String> ingredientes = new ArrayList<>(Arrays.asList(partes[9].split("\\|")));

                        Date fechaVencimiento = Date.valueOf(partes[7]);

                        AlimentoMascota alimento = new AlimentoMascota(
                                partes[0], partes[1],
                                Double.parseDouble(partes[2]),
                                Integer.parseInt(partes[3]),
                                partes[4], partes[5], partes[6],
                                fechaVencimiento, partes[8], ingredientes
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
}
