import java.util.ArrayList;

public class CuentaBancaria extends Cuenta {
    private static final int MAX_MOVIMIENTOS = 5;
    private ArrayList<String> movimientos = new ArrayList<String>();

    public CuentaBancaria(String titular, String pin, double saldoInicial) {
        super(titular, pin, saldoInicial);
    }

    protected boolean tieneFondos(double monto) {
        return monto <= saldo;
    }

    protected void registrarMovimiento(String texto) {
        movimientos.add(texto);
        if (movimientos.size() > MAX_MOVIMIENTOS) {
            movimientos.remove(0);
        }
    }

    public boolean depositar(double monto) {
        if (monto <= 0) {
            return false;
        }
        saldo += monto;
        registrarMovimiento("Depósito: +$" + String.format("%.2f", monto));
        return true;
    }

    public boolean retirar(double monto) {
        if (monto <= 0 || !tieneFondos(monto)) {
            return false;
        }
        saldo -= monto;
        registrarMovimiento("Retiro: -$" + String.format("%.2f", monto));
        return true;
    }

    public boolean transferir(CuentaBancaria cuentaDestino, double monto) {
        if (cuentaDestino == null || cuentaDestino == this) {
            return false;
        }
        if (monto <= 0 || !tieneFondos(monto)) {
            return false;
        }
        saldo -= monto;
        cuentaDestino.saldo += monto;
        registrarMovimiento("Transferencia a " + cuentaDestino.getTitular() + ": -$" + String.format("%.2f", monto));
        cuentaDestino.registrarMovimiento("Transferencia de " + titular + ": +$" + String.format("%.2f", monto));
        return true;
    }

    public ArrayList<String> getMovimientos() {
        return movimientos;
    }

    public String getTipo() {
        return "Cuenta Bancaria";
    }
}
