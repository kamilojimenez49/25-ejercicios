public class CuentaAhorro extends CuentaBancaria {
    private static final double INTERES = 0.01;

    public CuentaAhorro(String titular, String pin, double saldoInicial) {
        super(titular, pin, saldoInicial);
    }

    public boolean depositar(double monto) {
        if (monto <= 0) {
            return false;
        }
        double interes = monto * INTERES;
        saldo += monto + interes;
        registrarMovimiento("Depósito: +$" + String.format("%.2f", monto) + " (interés +$" + String.format("%.2f", interes) + ")");
        return true;
    }

    public String getTipo() {
        return "Cuenta de Ahorro";
    }
}
