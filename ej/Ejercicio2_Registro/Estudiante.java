import java.util.ArrayList;

public class Estudiante {
    private String nombre;
    private int faltas;
    private ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();

    public Estudiante(String nombre, int faltas) {
        this.nombre = nombre;
        this.faltas = faltas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }

    public Asignatura buscarAsignatura(String nombreAsignatura) {
        for (int i = 0; i < asignaturas.size(); i++) {
            if (asignaturas.get(i).getNombre().equalsIgnoreCase(nombreAsignatura)) {
                return asignaturas.get(i);
            }
        }
        return null;
    }

    public double promedio() {
        if (asignaturas.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (int i = 0; i < asignaturas.size(); i++) {
            suma += asignaturas.get(i).getNotaFinal();
        }
        return suma / asignaturas.size();
    }

    public boolean aprobo() {
        return promedio() >= 6 && faltas < 10;
    }

    public String resumen() {
        String estado = aprobo() ? "APROBADO" : "REPROBADO";
        return nombre + " | Promedio: " + String.format("%.2f", promedio()) + " | Faltas: " + faltas + " | " + estado;
    }
}
