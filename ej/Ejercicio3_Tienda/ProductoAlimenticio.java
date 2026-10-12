public class ProductoAlimenticio extends Producto {
    private String fechaCaducidad;

    public ProductoAlimenticio(String nombre, double precio, int stock, String categoria, String fechaCaducidad) {
        super(nombre, precio, stock, categoria);
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public String descripcion() {
        return super.descripcion() + " | Caduca: " + fechaCaducidad;
    }
}
