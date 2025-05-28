import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN DEL PET SHOP =====");
            System.out.println("1. Gestionar clientes y mascotas");
            System.out.println("2. Gestionar citas");
            System.out.println("3. Registrar venta");
            System.out.println("4. Gestionar proveedores y pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("-> Gestión de clientes y mascotas seleccionada.");
                    // Lógica: agregar cliente, editar, ver mascotas, etc.
                    break;
                case 2:
                    System.out.println("-> Gestión de citas seleccionada.");
                    // Lógica: agendar, cancelar, ver citas, etc.
                    break;
                case 3:
                    System.out.println("-> Registro de ventas seleccionado.");
                    // Lógica: registrar venta, ver historial de ventas
                    break;
                case 4:
                    System.out.println("-> Gestión de proveedores y pedidos seleccionada.");
                    // Lógica: hacer pedidos, consultar estado, agregar proveedores
                    break;
                case 0:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();

    }
}

/*
case x:
    System.out.println("-> Gestión de servicios seleccionada.");
    // Lógica: agregar servicios (peluquería, consulta, cirugía, paseo)
    break;
case x:
    System.out.println("-> Gestión de productos e inventario seleccionada.");
    // Lógica: ver stock, agregar productos, actualizar stock, etc.
    break;
 */