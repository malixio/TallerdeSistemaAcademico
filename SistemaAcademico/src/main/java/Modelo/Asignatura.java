package Modelo;

import java.util.ArrayList;

public class Asignatura {
    private String nombre;
    private String codigo;
    private String grupo;
    private String semestre;
    private int creditos;
    private ArrayList<Asistencia> asistencias; // Lista para almacenar las asistencias
    private ArrayList<Estudiante> estudiantes; // Lista para almacenar los estudiantes inscritos

    // Constructor
    public Asignatura(String nombre, String codigo, String grupo, String semestre, int creditos) {
        if (nombre == null || nombre.isEmpty() || codigo == null || codigo.isEmpty() ||
            grupo == null || grupo.isEmpty() || semestre == null || semestre.isEmpty() || creditos <= 0) {
            throw new IllegalArgumentException("Datos inválidos para crear la asignatura.");
        }
        this.nombre = nombre;
        this.codigo = codigo;
        this.grupo = grupo;
        this.semestre = semestre;
        this.creditos = creditos;
        this.asistencias = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        this.codigo = codigo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        if (grupo == null || grupo.isEmpty()) {
            throw new IllegalArgumentException("El grupo no puede estar vacío.");
        }
        this.grupo = grupo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        if (semestre == null || semestre.isEmpty()) {
            throw new IllegalArgumentException("El semestre no puede estar vacío.");
        }
        this.semestre = semestre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        if (creditos <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser mayores a 0.");
        }
        this.creditos = creditos;
    }

    // Método para registrar un estudiante en la asignatura
    public boolean registrarEstudiante(String tipoDocumento, String numeroDocumento) {
        if (tipoDocumento == null || tipoDocumento.isEmpty() || numeroDocumento == null || numeroDocumento.isEmpty()) {
            System.out.println("Error: Tipo de documento o número de documento no pueden estar vacíos.");
            return false;
        }
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getDocumento().equalsIgnoreCase(numeroDocumento)) {
                System.out.println("Error: El estudiante ya está registrado en la asignatura.");
                return false;
            }
        }
        Estudiante nuevoEstudiante = new Estudiante("", tipoDocumento, numeroDocumento);
        estudiantes.add(nuevoEstudiante);
        return true;
    }

    // Método para consultar los estudiantes inscritos en la asignatura
    public ArrayList<Estudiante> consultarEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    // Método para adicionar una asistencia
    public boolean adicionarAsistencia(String fecha, String horaInicio, String horaFinal, ArrayList<String> codigosAA,
                                       ArrayList<String> estados) {
        if (fecha == null || fecha.isEmpty() || horaInicio == null || horaInicio.isEmpty() || horaFinal == null || horaFinal.isEmpty()) {
            System.out.println("Error: Fecha, hora de inicio y hora final no pueden estar vacías.");
            return false;
        }
        for (Asistencia asistencia : asistencias) {
            if (asistencia.getFecha().equals(fecha) &&
                asistencia.getHoraDeInicio().equals(horaInicio) &&
                asistencia.getHoraFinal().equals(horaFinal)) {
                System.out.println("Error: Ya existe una asistencia con la misma fecha y hora.");
                return false;
            }
        }
        Asistencia nuevaAsistencia = new Asistencia(fecha, horaInicio, horaFinal);
        for (int i = 0; i < codigosAA.size(); i++) {
            nuevaAsistencia.adicionarAsistencia(codigosAA.get(i), estados.get(i));
        }
        asistencias.add(nuevaAsistencia);
        return true;
    }

    // Método para consultar una asistencia
    public Asistencia consultarAsistencia(String fecha, String horaInicio, String horaFinal) {
        if (fecha == null || fecha.isEmpty() || horaInicio == null || horaInicio.isEmpty() || horaFinal == null || horaFinal.isEmpty()) {
            System.out.println("Error: Fecha, hora de inicio y hora final no pueden estar vacías.");
            return null;
        }
        for (Asistencia asistencia : asistencias) {
            if (asistencia.getFecha().equals(fecha) &&
                asistencia.getHoraDeInicio().equals(horaInicio) &&
                asistencia.getHoraFinal().equals(horaFinal)) {
                return asistencia;
            }
        }
        System.out.println("Error: Asistencia no encontrada.");
        return null;
    }

    // Método para modificar una asistencia
    public boolean modificarAsistencia(String fecha, String horaInicio, String horaFinal,
                                       String nuevaFecha, String nuevaHoraInicio, String nuevaHoraFinal,
                                       ArrayList<String> codigos, ArrayList<String> estados) {
        Asistencia asistencia = consultarAsistencia(fecha, horaInicio, horaFinal);
        if (asistencia == null) {
            System.out.println("Error: Asistencia no encontrada para modificar.");
            return false;
        }
        if (codigos.size() != estados.size()) {
            System.out.println("Error: Las listas de códigos y estados deben tener el mismo tamaño.");
            return false;
        }
        asistencia.setFecha(nuevaFecha);
        asistencia.setHoraDeInicio(nuevaHoraInicio);
        asistencia.setHoraFinal(nuevaHoraFinal);
        asistencia.setCodigos(codigos);
        asistencia.setEstados(estados);
        return true;
    }

    // Método para eliminar una asistencia
    public boolean eliminarAsistencia(String fecha, String horaInicio, String horaFinal) {
        Asistencia asistencia = consultarAsistencia(fecha, horaInicio, horaFinal);
        if (asistencia == null) {
            System.out.println("Error: Asistencia no encontrada para eliminar.");
            return false;
        }
        asistencias.remove(asistencia);
        return true;
    }

    // Método para listar todas las asistencias
    public ArrayList<Asistencia> listarAsistencias() {
        return new ArrayList<>(asistencias);
    }

    @Override
    public String toString() {
        return "Asignatura{" +
               "nombre='" + nombre + '\'' +
               ", codigo='" + codigo + '\'' +
               ", grupo='" + grupo + '\'' +
               ", semestre='" + semestre + '\'' +
               ", creditos=" + creditos +
               '}';
    }
}