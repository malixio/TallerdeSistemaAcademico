package Modelo;
import java.util.ArrayList;
public class Estudiante {
    private String nombre;
    private String identificacion;
    private String documento;
    private static ArrayList<Estudiante> estudiantes = new ArrayList<>();

    //Contructor vacio
    public Estudiante() {
    }
    //constructor con parametros


    public Estudiante(String nombre, String identificacion, String documento) {
        this.nombre = nombre;
        this.documento=documento;
        this.identificacion=identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

}