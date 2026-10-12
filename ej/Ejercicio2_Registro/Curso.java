import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Curso {
    private ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public double promedioGeneral() {
        if (estudiantes.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (int i = 0; i < estudiantes.size(); i++) {
            suma += estudiantes.get(i).promedio();
        }
        return suma / estudiantes.size();
    }

    public int cantidadAprobados() {
        int cantidad = 0;
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).aprobo()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public Estudiante mejorEstudiante() {
        if (estudiantes.isEmpty()) {
            return null;
        }
        Estudiante mejor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).promedio() > mejor.promedio()) {
                mejor = estudiantes.get(i);
            }
        }
        return mejor;
    }

    public Estudiante peorEstudiante() {
        if (estudiantes.isEmpty()) {
            return null;
        }
        Estudiante peor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).promedio() < peor.promedio()) {
                peor = estudiantes.get(i);
            }
        }
        return peor;
    }

    public Estudiante buscarEstudiante(String nombre) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return estudiantes.get(i);
            }
        }
        return null;
    }

    public boolean modificarNota(String nombreEstudiante, String nombreAsignatura, double nuevaNota) {
        Estudiante estudiante = buscarEstudiante(nombreEstudiante);
        if (estudiante == null) {
            return false;
        }
        Asignatura asignatura = estudiante.buscarAsignatura(nombreAsignatura);
        if (asignatura == null) {
            return false;
        }
        asignatura.setNotaFinal(nuevaNota);
        return true;
    }

    public boolean guardarReporte(String nombreArchivo) {
        if (!nombreArchivo.endsWith(".txt")) {
            nombreArchivo = nombreArchivo + ".txt";
        }
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo));
            escritor.println("REPORTE DEL CURSO");
            escritor.println("=================");
            for (int i = 0; i < estudiantes.size(); i++) {
                escritor.println(estudiantes.get(i).resumen());
            }
            escritor.println();
            escritor.println("ESTADÍSTICAS");
            escritor.println("Promedio general: " + String.format("%.2f", promedioGeneral()));
            escritor.println("Aprobados: " + cantidadAprobados() + " de " + estudiantes.size());
            if (!estudiantes.isEmpty()) {
                escritor.println("Mejor estudiante: " + mejorEstudiante().getNombre());
                escritor.println("Peor estudiante: " + peorEstudiante().getNombre());
            }
            escritor.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
