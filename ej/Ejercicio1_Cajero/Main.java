import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner sc;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        sc = new Scanner(System.in);

        Banco banco = new Banco();
        banco.agregarCuenta(new CuentaBancaria("Ana", "1234", 1000));
        banco.agregarCuenta(new CuentaAhorro("Luis", "4321", 500));
        banco.agregarCuenta(new CuentaCorriente("Marta", "1111", 200, 300));

        System.out.println("=== CAJERO AUTOMÁTICO ===");
        System.out.print("Titular: ");
        String titular = sc.nextLine().trim();
        CuentaBancaria cuenta = banco.buscarCuenta(titular);

        if (cuenta == null) {
            System.out.println("Titular no encontrado.");
            return;
        }

        int intentos = 0;
        boolean acceso = false;
        while (intentos < 3 && !acceso) {
            System.out.print("PIN: ");
            String pin = sc.nextLine().trim();
            if (banco.validarPin(titular, pin)) {
                acceso = true;
            } else {
                intentos++;
                System.out.println("PIN incorrecto.");
            }
        }

        if (!acceso) {
            System.out.println("Cuenta bloqueada.");
            return;
        }

        System.out.println("Bienvenido/a " + cuenta.getTitular() + " (" + cuenta.getTipo() + ")");

        int opcion = 0;
        while (opcion != 6) {
            System.out.println();
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Transferir");
            System.out.println("5. Consultar movimientos");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            String texto = sc.nextLine().trim();

            if (texto.equals("1")) {
                System.out.println("Saldo: $" + String.format("%.2f", cuenta.getSaldo()));
            } else if (texto.equals("2")) {
                double monto = leerMonto("Monto a depositar: ");
                if (cuenta.depositar(monto)) {
                    System.out.println("Depósito exitoso. Saldo: $" + String.format("%.2f", cuenta.getSaldo()));
                } else {
                    System.out.println("No se pudo depositar.");
                }
            } else if (texto.equals("3")) {
                double monto = leerMonto("Monto a retirar: ");
                if (cuenta.retirar(monto)) {
                    System.out.println("Retiro exitoso. Saldo: $" + String.format("%.2f", cuenta.getSaldo()));
                } else {
                    System.out.println("Fondos insuficientes.");
                }
            } else if (texto.equals("4")) {
                System.out.print("Titular de la cuenta destino: ");
                String nombreDestino = sc.nextLine().trim();
                CuentaBancaria destino = banco.buscarCuenta(nombreDestino);
                if (destino == null) {
                    System.out.println("La cuenta destino no existe.");
                } else if (destino == cuenta) {
                    System.out.println("No puedes transferirte a ti mismo.");
                } else {
                    double monto = leerMonto("Monto a transferir: ");
                    if (cuenta.transferir(destino, monto)) {
                        System.out.println("Transferencia exitosa. Saldo: $" + String.format("%.2f", cuenta.getSaldo()));
                    } else {
                        System.out.println("Fondos insuficientes.");
                    }
                }
            } else if (texto.equals("5")) {
                ArrayList<String> movimientos = cuenta.getMovimientos();
                if (movimientos.isEmpty()) {
                    System.out.println("Sin movimientos.");
                } else {
                    System.out.println("Últimos movimientos:");
                    for (int i = 0; i < movimientos.size(); i++) {
                        System.out.println((i + 1) + ". " + movimientos.get(i));
                    }
                }
            } else if (texto.equals("6")) {
                opcion = 6;
                System.out.println("Gracias por usar el cajero.");
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    static double leerMonto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double monto = Double.parseDouble(sc.nextLine().trim());
                if (monto > 0) {
                    return monto;
                }
                System.out.println("El monto debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        }
    }
}
