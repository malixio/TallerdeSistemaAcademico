package Modelo;

public class Estudiante {
    private String nombre;
    private String identificacion; // Tipo de documento (Cédula, Pasaporte, etc.)
    private String documento; // Número de documento

    // Constructor vacío
    public Estudiante() {
    }

    // Constructor con parámetros
    public Estudiante(String nombre, String identificacion, String documento) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (identificacion == null || identificacion.isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede estar vacío.");
        }
        if (documento == null || documento.isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.documento = documento;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede estar vacío.");
        }
        this.identificacion = identificacion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }
        this.documento = documento;
    }

    // Método para representar al estudiante como una cadena de texto
    @Override
    public String toString() {
        return "Estudiante{" +
               "nombre='" + nombre + '\'' +
               ", tipoDocumento='" + identificacion + '\'' +
               ", documento='" + documento + '\'' +
               '}';
    }
}