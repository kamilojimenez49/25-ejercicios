public class Alumno {
    public static final double NOTA_APROBATORIA = 6;

    private String nombre;
    private double notaParcial1;
    private double notaParcial2;
    private double examenFinal;
    private double notaFinal;

    public Alumno(String nombre, double notaParcial1, double notaParcial2, double examenFinal) {
        this.nombre = nombre;
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
        this.examenFinal = examenFinal;
        this.notaFinal = calcularNotaOriginal();
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public double calcularNotaOriginal() {
        return notaParcial1 * 0.30 + notaParcial2 * 0.30 + examenFinal * 0.40;
    }

    public void quitarCurva() {
        notaFinal = calcularNotaOriginal();
    }

    public void aplicarCurva(double puntos) {
        notaFinal = Math.min(10, calcularNotaOriginal() + puntos);
    }

    public void aplicarCurvaPorcentual(double porcentaje) {
        double original = calcularNotaOriginal();
        notaFinal = Math.min(10, original + original * porcentaje / 100);
    }

    public boolean aproboOriginal() {
        return calcularNotaOriginal() >= NOTA_APROBATORIA;
    }

    public boolean aproboFinal() {
        return notaFinal >= NOTA_APROBATORIA;
    }
}
