import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner sc;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        sc = new Scanner(System.in);
        Curso curso = new Curso();

        System.out.println("=== SISTEMA DE NOTAS CON CURVA ===");
        System.out.println("Ponderación: Parcial 1 (30%), Parcial 2 (30%), Examen final (40%)");
        int cantidad = leerEntero("Cantidad de alumnos: ");
        for (int i = 1; i <= cantidad; i++) {
            System.out.println();
            System.out.print("Nombre del alumno " + i + ": ");
            String nombre = sc.nextLine().trim();
            double parcial1 = leerNota("Nota parcial 1: ");
            double parcial2 = leerNota("Nota parcial 2: ");
            double examen = leerNota("Nota examen final: ");
            curso.agregarAlumno(new Alumno(nombre, parcial1, parcial2, examen));
        }

        int opcion = 0;
        while (opcion != 5) {
            System.out.println();
            System.out.println("1. Aplicar curva");
            System.out.println("2. Ver notas");
            System.out.println("3. Estadísticas");
            System.out.println("4. Quitar curva");
            System.out.println("5. Salir");
            opcion = leerEntero("Opción: ");

            if (opcion == 1) {
                System.out.println("a. Curva fija en puntos");
                System.out.println("b. Curva porcentual");
                System.out.println("c. Curva basada en el mejor alumno");
                System.out.print("Tipo de curva: ");
                String tipo = sc.nextLine().trim().toLowerCase();
                if (tipo.equals("a")) {
                    double puntos = leerDouble("Puntos a sumar: ");
                    curso.aplicarCurvaFija(puntos);
                    System.out.println("Curva aplicada.");
                } else if (tipo.equals("b")) {
                    double porcentaje = leerDouble("Porcentaje: ");
                    curso.aplicarCurvaPorcentual(porcentaje);
                    System.out.println("Curva aplicada.");
                } else if (tipo.equals("c")) {
                    double puntos = curso.aplicarCurvaMejorAlumno();
                    System.out.println("Curva aplicada: +" + String.format("%.2f", puntos) + " puntos.");
                } else {
                    System.out.println("Tipo inválido.");
                }
            } else if (opcion == 2) {
                System.out.println();
                System.out.println(String.format("%-15s %10s %10s", "Alumno", "Original", "Final"));
                for (int i = 0; i < curso.getAlumnos().size(); i++) {
                    Alumno a = curso.getAlumnos().get(i);
                    System.out.println(String.format("%-15s %10.2f %10.2f", a.getNombre(), a.calcularNotaOriginal(), a.getNotaFinal()));
                }
            } else if (opcion == 3) {
                System.out.println("Media del curso: " + String.format("%.2f", curso.media()));
                System.out.println("Desviación estándar: " + String.format("%.2f", curso.desviacionEstandar()));
                System.out.println("Alumnos que pasaron de reprobado a aprobado: " + curso.pasaronAAprobado());
            } else if (opcion == 4) {
                curso.quitarCurva();
                System.out.println("Curva eliminada.");
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
                int numero = sc.nextInt();
                sc.nextLine();
                return numero;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Debes ingresar un número entero.");
            }
        }
    }

    static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double numero = sc.nextDouble();
                sc.nextLine();
                return numero;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Debes ingresar un número.");
            }
        }
    }

    static double leerNota(String mensaje) {
        while (true) {
            double nota = leerDouble(mensaje);
            if (nota >= 0 && nota <= 10) {
                return nota;
            }
            System.out.println("La nota debe estar entre 0 y 10.");
        }
    }
}
