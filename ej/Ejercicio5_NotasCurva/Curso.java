import java.util.ArrayList;

public class Curso {
    private ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public double notaMasAlta() {
        double maxima = 0;
        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).calcularNotaOriginal() > maxima) {
                maxima = alumnos.get(i).calcularNotaOriginal();
            }
        }
        return maxima;
    }

    public void quitarCurva() {
        for (int i = 0; i < alumnos.size(); i++) {
            alumnos.get(i).quitarCurva();
        }
    }

    public void aplicarCurvaFija(double puntos) {
        for (int i = 0; i < alumnos.size(); i++) {
            alumnos.get(i).aplicarCurva(puntos);
        }
    }

    public void aplicarCurvaPorcentual(double porcentaje) {
        for (int i = 0; i < alumnos.size(); i++) {
            alumnos.get(i).aplicarCurvaPorcentual(porcentaje);
        }
    }

    public double aplicarCurvaMejorAlumno() {
        double puntos = 10 - notaMasAlta();
        aplicarCurvaFija(puntos);
        return puntos;
    }

    public double media() {
        if (alumnos.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (int i = 0; i < alumnos.size(); i++) {
            suma += alumnos.get(i).getNotaFinal();
        }
        return suma / alumnos.size();
    }

    public double desviacionEstandar() {
        if (alumnos.isEmpty()) {
            return 0;
        }
        double media = media();
        double suma = 0;
        for (int i = 0; i < alumnos.size(); i++) {
            double diferencia = alumnos.get(i).getNotaFinal() - media;
            suma += diferencia * diferencia;
        }
        return Math.sqrt(suma / alumnos.size());
    }

    public int pasaronAAprobado() {
        int cantidad = 0;
        for (int i = 0; i < alumnos.size(); i++) {
            if (!alumnos.get(i).aproboOriginal() && alumnos.get(i).aproboFinal()) {
                cantidad++;
            }
        }
        return cantidad;
    }
}
