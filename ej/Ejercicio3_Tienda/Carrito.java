import java.util.ArrayList;

public class Carrito {
    private static final double MONTO_DESCUENTO = 1000;
    private static final double PORCENTAJE_DESCUENTO = 0.10;

    private Cliente cliente;
    private Inventario inventario;
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private ArrayList<Integer> cantidades = new ArrayList<Integer>();

    public Carrito(Cliente cliente, Inventario inventario) {
        this.cliente = cliente;
        this.inventario = inventario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean estaVacio() {
        return productos.isEmpty();
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0 || !inventario.hayStock(producto, cantidad)) {
            return false;
        }
        inventario.descontarStock(producto, cantidad);
        int posicion = productos.indexOf(producto);
        if (posicion >= 0) {
            cantidades.set(posicion, cantidades.get(posicion) + cantidad);
        } else {
            productos.add(producto);
            cantidades.add(cantidad);
        }
        return true;
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < productos.size(); i++) {
            subtotal += productos.get(i).getPrecio() * cantidades.get(i);
        }
        return subtotal;
    }

    public double calcularDescuento() {
        double subtotal = calcularSubtotal();
        if (subtotal > MONTO_DESCUENTO) {
            return subtotal * PORCENTAJE_DESCUENTO;
        }
        return 0;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

    public void mostrarResumen() {
        System.out.println("Carrito de " + cliente.getNombre() + " (" + cliente.getEmail() + ")");
        for (int i = 0; i < productos.size(); i++) {
            double totalLinea = productos.get(i).getPrecio() * cantidades.get(i);
            System.out.println("- " + productos.get(i).getNombre() + " x" + cantidades.get(i) + " = $" + String.format("%.2f", totalLinea));
        }
        System.out.println("Subtotal: $" + String.format("%.2f", calcularSubtotal()));
        System.out.println("Descuento: -$" + String.format("%.2f", calcularDescuento()));
        System.out.println("Total: $" + String.format("%.2f", calcularTotal()));
    }

    public void vaciar() {
        for (int i = 0; i < productos.size(); i++) {
            inventario.devolverStock(productos.get(i), cantidades.get(i));
        }
        productos.clear();
        cantidades.clear();
    }

    public void finalizarCompra() {
        productos.clear();
        cantidades.clear();
    }
}
