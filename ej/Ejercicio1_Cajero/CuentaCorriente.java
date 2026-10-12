public class CuentaCorriente extends CuentaBancaria {
    private double limiteSobregiro;

    public CuentaCorriente(String titular, String pin, double saldoInicial, double limiteSobregiro) {
        super(titular, pin, saldoInicial);
        this.limiteSobregiro = limiteSobregiro;
    }

    protected boolean tieneFondos(double monto) {
        return monto <= saldo + limiteSobregiro;
    }

    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    public String getTipo() {
        return "Cuenta Corriente";
    }
}
