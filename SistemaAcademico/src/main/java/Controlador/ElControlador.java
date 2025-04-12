package Controlador;

import java.util.ArrayList;

import Modelo.Asignatura;
import Modelo.Asistencia;
import Modelo.Departamento;
import Modelo.Estudiante;

public class ElControlador {

    private Departamento departamento = Departamento.singleton();

    // -------------------- CRUD DEPARTAMENTO --------------------
    public boolean crearDepartamento(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre del departamento no puede estar vacío.");
            return false;
        }
        departamento.setNombre(nombre);
        return true;
    }

    public String consultarDepartamento() {
        return departamento.getNombre();
    }

    public boolean actualizarDepartamento(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            System.out.println("Error: El nuevo nombre del departamento no puede estar vacío.");
            return false;
        }
        departamento.setNombre(nuevoNombre);
        return true;
    }

    // -------------------- CRUD ASIGNATURA --------------------
    public boolean agregarAsignatura(String nombre, int creditos, String codigo, String grupo, String semestre) {
        if (nombre == null || codigo == null || grupo == null || semestre == null || creditos <= 0) {
            System.out.println("Error: Datos inválidos para agregar la asignatura.");
            return false;
        }
        return departamento.agregarAsignatura(nombre, creditos, codigo, grupo, semestre);
    }

    public Asignatura consultarAsignatura(String codigo, String grupo, String semestre) {
        if (codigo == null || grupo == null || semestre == null) {
            System.out.println("Error: Datos inválidos para consultar la asignatura.");
            return null;
        }
        return departamento.consultarAsignatura(codigo, grupo, semestre);
    }

    public boolean modificarAsignatura(String codigo, String grupo, String semestre, String nombre, int creditos) {
        if (codigo == null || grupo == null || semestre == null || nombre == null || creditos <= 0) {
            System.out.println("Error: Datos inválidos para modificar la asignatura.");
            return false;
        }
        return departamento.modificarAsignatura(codigo, grupo, semestre, nombre, creditos);
    }

    public boolean eliminarAsignatura(String codigo, String grupo, String semestre) {
        if (codigo == null || grupo == null || semestre == null) {
            System.out.println("Error: Datos inválidos para eliminar la asignatura.");
            return false;
        }
        return departamento.eliminarAsignatura(codigo, grupo, semestre);
    }

    // -------------------- CRUD ESTUDIANTE EN DEPARTAMENTO --------------------
    public boolean registrarEstudianteEnDepartamento(String nombre, String documento, String tipoDocumento) {
        if (nombre == null || documento == null || tipoDocumento == null) {
            System.out.println("Error: Datos inválidos para registrar estudiante.");
            return false;
        }
        Estudiante estudiante = new Estudiante(nombre, tipoDocumento, documento);
        return departamento.agregarEstudiante(estudiante);
    }

    public Estudiante consultarEstudianteEnDepartamento(String tipoDocumento, String documento) {
        if (tipoDocumento == null || documento == null) {
            System.out.println("Error: Datos inválidos para consultar estudiante.");
            return null;
        }
        return departamento.consultarEstudiante(tipoDocumento, documento);
    }

    public boolean modificarEstudianteEnDepartamento(String tipoDocumento, String documento, String nuevoTipoDocumento, String nuevoNombre) {
        if (tipoDocumento == null || documento == null || nuevoTipoDocumento == null || nuevoNombre == null) {
            System.out.println("Error: Datos inválidos para modificar estudiante.");
            return false;
        }
        return departamento.modificarEstudiante(tipoDocumento, documento, nuevoTipoDocumento, nuevoNombre);
    }

    public boolean eliminarEstudianteDelDepartamento(String tipoDocumento, String documento) {
        if (tipoDocumento == null || documento == null) {
            System.out.println("Error: Datos inválidos para eliminar estudiante.");
            return false;
        }
        return departamento.eliminarEstudiante(tipoDocumento, documento);
    }

    // -------------------- CRUD ESTUDIANTE EN ASIGNATURA --------------------
    public boolean registrarEstudianteEnAsignatura(String codigo, String grupo, String semestre, String tipoDocumento, String numeroDocumento) {
        if (codigo == null || grupo == null || semestre == null || tipoDocumento == null || numeroDocumento == null) {
            System.out.println("Error: Datos inválidos para registrar estudiante en asignatura.");
            return false;
        }
        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        if (asignatura == null) {
            System.out.println("Error: Asignatura no encontrada.");
            return false;
        }
        return asignatura.registrarEstudiante(tipoDocumento, numeroDocumento);
    }

    public ArrayList<Estudiante> consultarEstudiantesEnAsignatura(String codigo, String grupo, String semestre) {
        if (codigo == null || grupo == null || semestre == null) {
            System.out.println("Error: Datos inválidos para consultar estudiantes en asignatura.");
            return null;
        }
        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        if (asignatura == null) {
            System.out.println("Error: Asignatura no encontrada.");
            return null;
        }
        return asignatura.consultarEstudiantes();
    }

    // -------------------- CRUD ASISTENCIA --------------------
    public boolean adicionarAsistencia(String codigo, String grupo, String semestre, String fecha,
                                       String horaInicio, String horaFinal,
                                       ArrayList<String> codigosAA, ArrayList<String> estados) {
        if (codigo == null || grupo == null || semestre == null || fecha == null ||
            horaInicio == null || horaFinal == null || codigosAA == null || estados == null) {
            System.out.println("Error: Datos inválidos para adicionar asistencia.");
            return false;
        }
        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        return (asignatura != null) && asignatura.adicionarAsistencia(fecha, horaInicio, horaFinal, codigosAA, estados);
    }

    public Asistencia consultarAsistencia(String codigo, String grupo, String semestre,
                                          String fecha, String horaInicio, String horaFinal) {
        if (codigo == null || grupo == null || semestre == null || fecha == null ||
            horaInicio == null || horaFinal == null) {
            System.out.println("Error: Datos inválidos para consultar asistencia.");
            return null;
        }
        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        return (asignatura != null) ? asignatura.consultarAsistencia(fecha, horaInicio, horaFinal) : null;
    }

    public boolean modificarAsistencia(String codigo, String grupo, String semestre,
                                       String fecha, String horaInicio, String horaFinal,
                                       String nuevaFecha, String nuevaHoraInicio, String nuevaHoraFinal,
                                       ArrayList<String> codigos, ArrayList<String> estados) {
        if (codigo == null || grupo == null || semestre == null || fecha == null ||
            horaInicio == null || horaFinal == null || nuevaFecha == null ||
            nuevaHoraInicio == null || nuevaHoraFinal == null || codigos == null || estados == null) {
            System.out.println("Error: Datos inválidos para modificar asistencia.");
            return false;
        }
        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        return (asignatura != null) && asignatura.modificarAsistencia(fecha, horaInicio, horaFinal,
                nuevaFecha, nuevaHoraInicio, nuevaHoraFinal, codigos, estados);
    }

    public boolean eliminarAsistencia(String codigo, String grupo, String semestre,
                                      String fecha, String horaInicio, String horaFinal) {
        if (codigo == null || grupo == null || semestre == null || fecha == null ||
            horaInicio == null || horaFinal == null) {
            System.out.println("Error: Datos inválidos para eliminar asistencia.");
            return false;
        }
        Asignatura asignatura = departamento.consultarAsignatura(codigo, grupo, semestre);
        return (asignatura != null) && asignatura.eliminarAsistencia(fecha, horaInicio, horaFinal);
    }
}