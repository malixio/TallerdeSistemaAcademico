package Controlador;
import Modelo.Estudiante;
import Modelo.Asistencia;
import Modelo.Departamento;
import Modelo.Asignatura;
import java.util.ArrayList;

public class ElControlador {

    public Departamento departamento = Departamento.singleton();
    // -------------------- CRUD DEPARTAMENTO --------------------
    public boolean crearDepartamento(String nombre) {
        departamento.setNombre(nombre);
        return true;
    }

    public String consultarDepartamento() {
        return departamento.getNombre();
    }

    public boolean actualizarDepartamento(String nuevoNombre) {
        departamento.setNombre(nuevoNombre);
        return true;
    }


    // -------------------- CRUD ASIGNATURA --------------------
    public void agregarAsignatura(String nombre, String creditos, String codigo, String grupo, String semestre){
        departamento.agregarAsignatura(nombre,Integer.parseInt(creditos), codigo, grupo, semestre);
    }

    public Asignatura consultarAsignatura(String codigo, String grupo, String semestre){
        return departamento.consultarAsignatura(codigo, grupo, semestre);
    }

    public boolean modificarAsignatura(String codigo, String grupo, String semestre, String nombre, int creditos){

        departamento.modificarAsignatura(codigo, grupo, semestre, nombre, creditos);
        return true;
    }

    public boolean eliminarAsignatura(String codigo, String grupo, String semestre){
        departamento.eliminarAsignatura(codigo, grupo, semestre);
        return true;
    }

    // -------------------- CRUD ASISTENCIA --------------------
    public boolean adicionarAsistencia(String codigo, String grupo, String semestre, String fecha,
                                       String horaInicio, String horaFinal,
                                       ArrayList<String> codigosAA, ArrayList<String> estados){
        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        if(asignatura != null){
            return asignatura.adicionarAsistencia(fecha, horaInicio, horaFinal, codigosAA, estados);
        }
        return false;
    }

    public Asistencia consultarAsistencia(String codigo, String grupo, String semestre,
                                          String fecha, String horaInicio, String horaFinal){
        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        if(asignatura != null){
            return asignatura.consultarAsistencia(fecha, horaInicio, horaFinal);
        }
        return null;
    }

    public boolean modificarAsistencia(String codigo, String grupo, String semestre,
                                       String fecha, String hora_de_inicio, String hora_final,
                                       String nuevaFecha, String nuevaHora_de_inicio, String nuevaHora_final,
                                       ArrayList<String> codigos, ArrayList<String> estados){

        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        if(asignatura != null){
            boolean resultado = asignatura.modificarAsistencia(fecha, hora_de_inicio, hora_final,
                    nuevaFecha, nuevaHora_de_inicio, nuevaHora_final,
                    codigos, estados);
            return resultado;
        }
        return false;
    }

    public boolean eliminarAsistencia(String codigo, String grupo, String semestre,
                                      String fecha, String horaInicio, String horaFinal){
        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        if(asignatura != null){
            return asignatura.eliminarAsistencia(fecha, horaInicio, horaFinal);
        }
        return false;
    }
}
