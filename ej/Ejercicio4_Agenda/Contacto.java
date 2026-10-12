public class Contacto {
    private String nombre;
    private String telefono;
    private String email;
    private String grupo;

    public Contacto(String nombre, String telefono, String email, String grupo) {
        this.nombre = nombre.replace(";", ",");
        this.telefono = telefono;
        this.email = email;
        this.grupo = grupo.replace(";", ",");
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getGrupo() {
        return grupo;
    }

    public String aLinea() {
        return nombre + ";" + telefono + ";" + email + ";" + grupo;
    }

    public String toString() {
        return nombre + " | Tel: " + telefono + " | " + email + " | Grupo: " + grupo;
    }
}
