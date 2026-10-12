import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Agenda {
    private static final int DIGITOS_TELEFONO = 10;
    private static final String ARCHIVO = "agenda.txt";

    private ArrayList<Contacto> contactos = new ArrayList<Contacto>();

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public String agregarContacto(Contacto contacto) {
        if (contacto.getNombre().trim().isEmpty()) {
            return "El nombre no puede estar vacío.";
        }
        String telefono = contacto.getTelefono();
        if (telefono.length() != DIGITOS_TELEFONO) {
            return "El teléfono debe tener " + DIGITOS_TELEFONO + " dígitos.";
        }
        for (int i = 0; i < telefono.length(); i++) {
            if (!Character.isDigit(telefono.charAt(i))) {
                return "El teléfono solo debe contener números.";
            }
        }
        String email = contacto.getEmail();
        if (!email.contains("@") || !email.contains(".")) {
            return "El email debe contener '@' y '.'.";
        }
        contactos.add(contacto);
        return null;
    }

    public ArrayList<Contacto> buscarContacto(String nombre) {
        ArrayList<Contacto> encontrados = new ArrayList<Contacto>();
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                encontrados.add(contactos.get(i));
            }
        }
        return encontrados;
    }

    public void mostrarContactosPorGrupo(String grupo) {
        boolean hayContactos = false;
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getGrupo().equalsIgnoreCase(grupo)) {
                System.out.println(contactos.get(i));
                hayContactos = true;
            }
        }
        if (!hayContactos) {
            System.out.println("No hay contactos en el grupo " + grupo + ".");
        }
    }

    public boolean guardarEnArchivo() {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO));
            for (int i = 0; i < contactos.size(); i++) {
                escritor.println(contactos.get(i).aLinea());
            }
            escritor.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public int cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return 0;
        }
        contactos.clear();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea = lector.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    contactos.add(new Contacto(partes[0], partes[1], partes[2], partes[3]));
                }
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        return contactos.size();
    }
}
