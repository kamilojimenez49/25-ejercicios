import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<Producto>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<String> getCategorias() {
        ArrayList<String> categorias = new ArrayList<String>();
        for (int i = 0; i < productos.size(); i++) {
            String categoria = productos.get(i).getCategoria();
            if (!categorias.contains(categoria)) {
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    public ArrayList<Producto> filtrarPorCategoria(String categoria) {
        ArrayList<Producto> filtrados = new ArrayList<Producto>();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCategoria().equalsIgnoreCase(categoria)) {
                filtrados.add(productos.get(i));
            }
        }
        return filtrados;
    }

    public boolean hayStock(Producto producto, int cantidad) {
        return producto.getStock() >= cantidad;
    }

    public void descontarStock(Producto producto, int cantidad) {
        producto.setStock(producto.getStock() - cantidad);
    }

    public void devolverStock(Producto producto, int cantidad) {
        producto.setStock(producto.getStock() + cantidad);
    }
}
