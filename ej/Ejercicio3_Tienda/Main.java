import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner sc;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        sc = new Scanner(System.in);

        Inventario inventario = new Inventario();
        inventario.agregarProducto(new ProductoElectronico("Laptop", 800, 5, "Electrónica", 24));
        inventario.agregarProducto(new ProductoElectronico("Celular", 500, 8, "Electrónica", 12));
        inventario.agregarProducto(new ProductoElectronico("Mouse", 25, 20, "Electrónica", 6));
        inventario.agregarProducto(new ProductoAlimenticio("Arroz", 4, 50, "Alimentos", "2027-03-01"));
        inventario.agregarProducto(new ProductoAlimenticio("Leche", 2, 30, "Alimentos", "2026-12-15"));
        inventario.agregarProducto(new Producto("Camiseta", 15, 25, "Ropa"));

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente("Ana", "ana@correo.com"));
        clientes.add(new Cliente("Luis", "luis@correo.com"));

        System.out.println("=== TIENDA ONLINE ===");
        Cliente cliente = null;
        while (cliente == null) {
            System.out.print("Email para iniciar sesión: ");
            String email = sc.nextLine().trim();
            if (!email.contains("@")) {
                System.out.println("Email inválido.");
            } else {
                for (int i = 0; i < clientes.size(); i++) {
                    if (clientes.get(i).getEmail().equalsIgnoreCase(email)) {
                        cliente = clientes.get(i);
                    }
                }
                if (cliente == null) {
                    System.out.print("Cliente nuevo. Nombre: ");
                    cliente = new Cliente(sc.nextLine().trim(), email);
                    clientes.add(cliente);
                }
            }
        }
        System.out.println("Bienvenido/a " + cliente.getNombre());

        Carrito carrito = new Carrito(cliente, inventario);

        int opcion = 0;
        while (opcion != 5) {
            System.out.println();
            System.out.println("1. Ver catálogo y agregar al carrito");
            System.out.println("2. Ver carrito");
            System.out.println("3. Vaciar carrito");
            System.out.println("4. Pagar");
            System.out.println("5. Salir");
            opcion = leerEntero("Opción: ");

            if (opcion == 1) {
                System.out.println("Categorías: " + inventario.getCategorias());
                System.out.print("Filtrar por categoría (Enter para ver todo): ");
                String categoria = sc.nextLine().trim();
                ArrayList<Producto> lista;
                if (categoria.isEmpty()) {
                    lista = inventario.getProductos();
                } else {
                    lista = inventario.filtrarPorCategoria(categoria);
                }
                if (lista.isEmpty()) {
                    System.out.println("No hay productos en esa categoría.");
                } else {
                    for (int i = 0; i < lista.size(); i++) {
                        System.out.println((i + 1) + ". " + lista.get(i).descripcion());
                    }
                    int numero = leerEntero("Número de producto (0 para volver): ");
                    if (numero >= 1 && numero <= lista.size()) {
                        int cantidad = leerEntero("Cantidad: ");
                        if (carrito.agregarProducto(lista.get(numero - 1), cantidad)) {
                            System.out.println("Producto agregado al carrito.");
                        } else {
                            System.out.println("Cantidad inválida o stock insuficiente.");
                        }
                    }
                }
            } else if (opcion == 2) {
                if (carrito.estaVacio()) {
                    System.out.println("El carrito está vacío.");
                } else {
                    carrito.mostrarResumen();
                }
            } else if (opcion == 3) {
                carrito.vaciar();
                System.out.println("Carrito vaciado.");
            } else if (opcion == 4) {
                if (carrito.estaVacio()) {
                    System.out.println("El carrito está vacío.");
                } else {
                    carrito.mostrarResumen();
                    carrito.finalizarCompra();
                    System.out.println("Compra realizada. ¡Gracias!");
                }
            } else if (opcion == 5) {
                System.out.println("Hasta luego.");
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        }
    }
}
