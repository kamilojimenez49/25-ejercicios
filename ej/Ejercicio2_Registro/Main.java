import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner sc;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        sc = new Scanner(System.in);
        Curso curso = new Curso();

        Estudiante e1 = new Estudiante("Carlos", 2);
        e1.agregarAsignatura(new Asignatura("Matemáticas", 8));
        e1.agregarAsignatura(new Asignatura("Programación", 9));
        curso.agregarEstudiante(e1);

        Estudiante e2 = new Estudiante("Laura", 12);
        e2.agregarAsignatura(new Asignatura("Matemáticas", 9));
        e2.agregarAsignatura(new Asignatura("Programación", 10));
        curso.agregarEstudiante(e2);

        Estudiante e3 = new Estudiante("Pedro", 1);
        e3.agregarAsignatura(new Asignatura("Matemáticas", 4));
        e3.agregarAsignatura(new Asignatura("Programación", 5));
        curso.agregarEstudiante(e3);

        int opcion = 0;
        while (opcion != 7) {
            System.out.println();
            System.out.println("=== REGISTRO ACADÉMICO ===");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Estadísticas del curso");
            System.out.println("4. Buscar estudiante");
            System.out.println("5. Modificar nota");
            System.out.println("6. Guardar reporte en archivo");
            System.out.println("7. Salir");
            opcion = leerEntero("Opción: ");

            if (opcion == 1) {
                System.out.print("Nombre: ");
                String nombre = sc.nextLine().trim();
                int faltas = leerEntero("Faltas: ");
                Estudiante estudiante = new Estudiante(nombre, faltas);
                int cantidad = leerEntero("Cantidad de asignaturas: ");
                for (int i = 1; i <= cantidad; i++) {
                    System.out.print("Nombre de la asignatura " + i + ": ");
                    String nombreAsignatura = sc.nextLine().trim();
                    double nota = leerNota("Nota final: ");
                    estudiante.agregarAsignatura(new Asignatura(nombreAsignatura, nota));
                }
                curso.agregarEstudiante(estudiante);
                System.out.println("Estudiante agregado.");
            } else if (opcion == 2) {
                ArrayList<Estudiante> lista = curso.getEstudiantes();
                if (lista.isEmpty()) {
                    System.out.println("No hay estudiantes.");
                }
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println((i + 1) + ". " + lista.get(i).resumen());
                }
            } else if (opcion == 3) {
                if (curso.getEstudiantes().isEmpty()) {
                    System.out.println("No hay estudiantes.");
                } else {
                    System.out.println("Promedio general: " + String.format("%.2f", curso.promedioGeneral()));
                    System.out.println("Aprobados: " + curso.cantidadAprobados() + " de " + curso.getEstudiantes().size());
                    System.out.println("Mejor estudiante: " + curso.mejorEstudiante().resumen());
                    System.out.println("Peor estudiante: " + curso.peorEstudiante().resumen());
                }
            } else if (opcion == 4) {
                System.out.print("Nombre a buscar: ");
                Estudiante encontrado = curso.buscarEstudiante(sc.nextLine().trim());
                if (encontrado == null) {
                    System.out.println("Estudiante no encontrado.");
                } else {
                    System.out.println(encontrado.resumen());
                    ArrayList<Asignatura> asignaturas = encontrado.getAsignaturas();
                    for (int i = 0; i < asignaturas.size(); i++) {
                        System.out.println("  - " + asignaturas.get(i).getNombre() + ": " + asignaturas.get(i).getNotaFinal());
                    }
                }
            } else if (opcion == 5) {
                System.out.print("Nombre del estudiante: ");
                String nombreEstudiante = sc.nextLine().trim();
                System.out.print("Nombre de la asignatura: ");
                String nombreAsignatura = sc.nextLine().trim();
                double nuevaNota = leerNota("Nueva nota: ");
                if (curso.modificarNota(nombreEstudiante, nombreAsignatura, nuevaNota)) {
                    System.out.println("Nota modificada.");
                } else {
                    System.out.println("Estudiante o asignatura no encontrados.");
                }
            } else if (opcion == 6) {
                System.out.print("Nombre del archivo: ");
                String archivo = sc.nextLine().trim();
                if (curso.guardarReporte(archivo)) {
                    System.out.println("Reporte guardado.");
                } else {
                    System.out.println("No se pudo guardar el reporte.");
                }
            } else if (opcion == 7) {
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

    static double leerNota(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double nota = Double.parseDouble(sc.nextLine().trim());
                if (nota >= 0 && nota <= 10) {
                    return nota;
                }
                System.out.println("La nota debe estar entre 0 y 10.");
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        }
    }
}
