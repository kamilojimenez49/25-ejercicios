import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();

        int cargados = agenda.cargarDesdeArchivo();
        System.out.println("Contactos cargados: " + cargados);

        String opcion = "";
        while (!opcion.equals("6")) {
            System.out.println();
            System.out.println("=== AGENDA DE CONTACTOS ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Mostrar contactos por grupo");
            System.out.println("5. Guardar agenda");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextLine().trim();

            if (opcion.equals("1")) {
                System.out.print("Nombre: ");
                String nombre = sc.nextLine().trim();
                System.out.print("Teléfono: ");
                String telefono = sc.nextLine().trim();
                System.out.print("Email: ");
                String email = sc.nextLine().trim();
                System.out.print("Grupo (Familia, Trabajo, Amigos...): ");
                String grupo = sc.nextLine().trim();
                if (grupo.isEmpty()) {
                    grupo = "Otros";
                }
                String error = agenda.agregarContacto(new Contacto(nombre, telefono, email, grupo));
                if (error == null) {
                    System.out.println("Contacto agregado.");
                } else {
                    System.out.println(error);
                }
            } else if (opcion.equals("2")) {
                ArrayList<Contacto> lista = agenda.getContactos();
                if (lista.isEmpty()) {
                    System.out.println("La agenda está vacía.");
                }
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println((i + 1) + ". " + lista.get(i));
                }
            } else if (opcion.equals("3")) {
                System.out.print("Texto a buscar en el nombre: ");
                ArrayList<Contacto> encontrados = agenda.buscarContacto(sc.nextLine().trim());
                if (encontrados.isEmpty()) {
                    System.out.println("No se encontraron contactos.");
                }
                for (int i = 0; i < encontrados.size(); i++) {
                    System.out.println(encontrados.get(i));
                }
            } else if (opcion.equals("4")) {
                System.out.print("Grupo: ");
                agenda.mostrarContactosPorGrupo(sc.nextLine().trim());
            } else if (opcion.equals("5")) {
                if (agenda.guardarEnArchivo()) {
                    System.out.println("Agenda guardada.");
                } else {
                    System.out.println("No se pudo guardar la agenda.");
                }
            } else if (opcion.equals("6")) {
                agenda.guardarEnArchivo();
                System.out.println("Agenda guardada. Hasta luego.");
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }
}
