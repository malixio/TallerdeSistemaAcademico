package Modelo;

import java.util.ArrayList;

public class Departamento {
    private String nombre;
    private ArrayList<Asignatura> asignaturas; // Lista para almacenar asignaturas
    private ArrayList<Estudiante> estudiantes; // Lista para almacenar estudiantes
    private static Departamento instancia = null; // Singleton

    // Constructor privado para Singleton
    private Departamento() {
        this.nombre = "";
        this.asignaturas = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    // Método para obtener la instancia única del Departamento
    public static Departamento singleton() {
        if (instancia == null) {
            instancia = new Departamento();
        }
        return instancia;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del departamento no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    // -------------------- CRUD ASIGNATURAS --------------------
    // CREATE
    public boolean agregarAsignatura(String nombre, int creditos, String codigo, String grupo, String semestre) {
        if (nombre == null || nombre.trim().isEmpty() || codigo == null || codigo.trim().isEmpty() ||
            grupo == null || grupo.trim().isEmpty() || semestre == null || semestre.trim().isEmpty() || creditos <= 0) {
            System.out.println("Error: Datos inválidos para agregar la asignatura.");
            return false;
        }
        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getCodigo().equalsIgnoreCase(codigo) &&
                asignatura.getGrupo().equalsIgnoreCase(grupo) &&
                asignatura.getSemestre().equalsIgnoreCase(semestre)) {
                System.out.println("Error: La asignatura ya existe.");
                return false;
            }
        }
        Asignatura nuevaAsignatura = new Asignatura(nombre, codigo, grupo, semestre, creditos);
        asignaturas.add(nuevaAsignatura);
        return true;
    }

    // READ
    public Asignatura consultarAsignatura(String codigo, String grupo, String semestre) {
        if (codigo == null || codigo.trim().isEmpty() || grupo == null || grupo.trim().isEmpty() || semestre == null || semestre.trim().isEmpty()) {
            System.out.println("Error: Datos inválidos para consultar la asignatura.");
            return null;
        }
        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getCodigo().equalsIgnoreCase(codigo) &&
                asignatura.getGrupo().equalsIgnoreCase(grupo) &&
                asignatura.getSemestre().equalsIgnoreCase(semestre)) {
                return asignatura;
            }
        }
        System.out.println("Error: Asignatura no encontrada.");
        return null;
    }

    // UPDATE
    public boolean modificarAsignatura(String codigo, String grupo, String semestre, String nombre, int creditos) {
        Asignatura asignatura = consultarAsignatura(codigo, grupo, semestre);
        if (asignatura != null) {
            if (nombre == null || nombre.trim().isEmpty() || creditos <= 0) {
                System.out.println("Error: Datos inválidos para modificar la asignatura.");
                return false;
            }
            asignatura.setNombre(nombre);
            asignatura.setCreditos(creditos);
            return true;
        }
        System.out.println("Error: No se pudo modificar la asignatura.");
        return false;
    }

    // DELETE
    public boolean eliminarAsignatura(String codigo, String grupo, String semestre) {
        Asignatura asignatura = consultarAsignatura(codigo, grupo, semestre);
        if (asignatura != null) {
            asignaturas.remove(asignatura);
            return true;
        }
        System.out.println("Error: No se pudo eliminar la asignatura.");
        return false;
    }

    public boolean eliminarTodasAsignaturas() {
        asignaturas.clear();
        return true;
    }

    // -------------------- CRUD ESTUDIANTES --------------------
    // CREATE
    public boolean agregarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            System.out.println("Error: El estudiante no puede ser nulo.");
            return false;
        }
        for (Estudiante e : estudiantes) {
            if (e.getDocumento().equalsIgnoreCase(estudiante.getDocumento())) {
                System.out.println("Error: El estudiante ya está registrado.");
                return false;
            }
        }
        estudiantes.add(estudiante);
        return true;
    }

    // READ
    public Estudiante consultarEstudiante(String tipoDocumento, String documento) {
        if (tipoDocumento == null || tipoDocumento.trim().isEmpty() || documento == null || documento.trim().isEmpty()) {
            System.out.println("Error: Datos inválidos para consultar el estudiante.");
            return null;
        }
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getIdentificacion().equalsIgnoreCase(tipoDocumento) &&
                estudiante.getDocumento().equalsIgnoreCase(documento)) {
                return estudiante;
            }
        }
        System.out.println("Error: Estudiante no encontrado.");
        return null;
    }

    // UPDATE
    public boolean modificarEstudiante(String tipoDocumento, String documento, String nuevoTipoDocumento, String nuevoNombre) {
        Estudiante estudiante = consultarEstudiante(tipoDocumento, documento);
        if (estudiante != null) {
            if (nuevoTipoDocumento == null || nuevoTipoDocumento.trim().isEmpty() || nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
                System.out.println("Error: Datos inválidos para modificar el estudiante.");
                return false;
            }
            estudiante.setIdentificacion(nuevoTipoDocumento);
            estudiante.setNombre(nuevoNombre);
            return true;
        }
        System.out.println("Error: No se pudo modificar el estudiante.");
        return false;
    }

    // DELETE
    public boolean eliminarEstudiante(String tipoDocumento, String documento) {
        Estudiante estudiante = consultarEstudiante(tipoDocumento, documento);
        if (estudiante != null) {
            estudiantes.remove(estudiante);
            return true;
        }
        System.out.println("Error: No se pudo eliminar el estudiante.");
        return false;
    }

    public boolean eliminarTodosEstudiantes() {
        estudiantes.clear();
        return true;
    }

    // -------------------- MÉTODOS ADICIONALES --------------------
    public ArrayList<Asignatura> listarAsignaturas() {
        return new ArrayList<>(asignaturas);
    }

    public ArrayList<Estudiante> listarEstudiantes() {
        return new ArrayList<>(estudiantes);
    }
}