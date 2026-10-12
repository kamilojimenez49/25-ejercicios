import java.util.ArrayList;

public class Banco {
    private ArrayList<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public CuentaBancaria buscarCuenta(String titular) {
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getTitular().equalsIgnoreCase(titular)) {
                return cuentas.get(i);
            }
        }
        return null;
    }

    public boolean validarPin(String titular, String pin) {
        CuentaBancaria cuenta = buscarCuenta(titular);
        return cuenta != null && cuenta.validarPin(pin);
    }
}
