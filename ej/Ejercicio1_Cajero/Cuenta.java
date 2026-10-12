public abstract class Cuenta {
    protected String titular;
    protected String pin;
    protected double saldo;

    public Cuenta(String titular, String pin, double saldoInicial) {
        this.titular = titular;
        this.pin = pin;
        this.saldo = saldoInicial;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean validarPin(String pinIngresado) {
        return pin.equals(pinIngresado);
    }

    public abstract boolean depositar(double monto);

    public abstract boolean retirar(double monto);

    public abstract String getTipo();
}
