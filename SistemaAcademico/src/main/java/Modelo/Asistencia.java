package Modelo;

import java.util.ArrayList;

public class Asistencia {
    private String fecha;
    private String horaDeInicio;
    private String horaFinal;
    private ArrayList<String> codigos; // Lista de códigos de estudiantes
    private ArrayList<String> estados; // Lista de estados de asistencia (A tiempo, Tarde, No llegó)

    // Constantes para los estados de asistencia
    public static final String A_TIEMPO = "A tiempo";
    public static final String TARDE = "Tarde";
    public static final String NO_LLEGO = "No llegó";

    // Constructor
    public Asistencia(String fecha, String horaDeInicio, String horaFinal) {
        if (fecha == null || fecha.isEmpty() || horaDeInicio == null || horaDeInicio.isEmpty() || horaFinal == null || horaFinal.isEmpty()) {
            throw new IllegalArgumentException("Fecha, hora de inicio y hora final no pueden estar vacías.");
        }
        this.fecha = fecha;
        this.horaDeInicio = horaDeInicio;
        this.horaFinal = horaFinal;
        this.codigos = new ArrayList<>();
        this.estados = new ArrayList<>();
    }

    // Getters y Setters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        this.fecha = fecha;
    }

    public String getHoraDeInicio() {
        return horaDeInicio;
    }

    public void setHoraDeInicio(String horaDeInicio) {
        if (horaDeInicio == null || horaDeInicio.isEmpty()) {
            throw new IllegalArgumentException("La hora de inicio no puede estar vacía.");
        }
        this.horaDeInicio = horaDeInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        if (horaFinal == null || horaFinal.isEmpty()) {
            throw new IllegalArgumentException("La hora final no puede estar vacía.");
        }
        this.horaFinal = horaFinal;
    }

    public ArrayList<String> getCodigos() {
        return new ArrayList<>(codigos);
    }

    public void setCodigos(ArrayList<String> codigos) {
        if (codigos == null) {
            throw new IllegalArgumentException("La lista de códigos no puede ser nula.");
        }
        this.codigos = new ArrayList<>(codigos);
    }

    public ArrayList<String> getEstados() {
        return new ArrayList<>(estados);
    }

    public void setEstados(ArrayList<String> estados) {
        if (estados == null) {
            throw new IllegalArgumentException("La lista de estados no puede ser nula.");
        }
        this.estados = new ArrayList<>(estados);
    }

    // Método para adicionar un estudiante a la lista de asistencia
    public boolean adicionarAsistencia(String codigo, String estado) {
        if (codigo == null || codigo.isEmpty()) {
            System.out.println("Error: El código del estudiante no puede estar vacío.");
            return false;
        }
        if (!estado.equals(A_TIEMPO) && !estado.equals(TARDE) && !estado.equals(NO_LLEGO)) {
            System.out.println("Error: Estado de asistencia inválido.");
            return false;
        }
        if (codigos.contains(codigo)) {
            System.out.println("Error: El estudiante con código " + codigo + " ya está registrado en la asistencia.");
            return false;
        }
        codigos.add(codigo);
        estados.add(estado);
        return true;
    }

    // Método para eliminar un estudiante de la lista de asistencia
    public boolean eliminarEstudiante(String codigo) {
        int index = codigos.indexOf(codigo);
        if (index == -1) {
            System.out.println("Error: Estudiante con código " + codigo + " no encontrado en la lista de asistencia.");
            return false;
        }
        codigos.remove(index);
        estados.remove(index);
        return true;
    }

    // Método para modificar el estado de un estudiante en la lista de asistencia
    public boolean modificarEstado(String codigo, String nuevoEstado) {
        int index = codigos.indexOf(codigo);
        if (index == -1) {
            System.out.println("Error: Estudiante con código " + codigo + " no encontrado en la lista de asistencia.");
            return false;
        }
        if (!nuevoEstado.equals(A_TIEMPO) && !nuevoEstado.equals(TARDE) && !nuevoEstado.equals(NO_LLEGO)) {
            System.out.println("Error: Estado de asistencia inválido.");
            return false;
        }
        estados.set(index, nuevoEstado);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Hora de Inicio: ").append(horaDeInicio).append("\n");
        sb.append("Hora Final: ").append(horaFinal).append("\n");
        sb.append("Asistencias:\n");
        for (int i = 0; i < codigos.size(); i++) {
            sb.append(" - Estudiante: ").append(codigos.get(i))
              .append(", Estado: ").append(estados.get(i)).append("\n");
        }
        return sb.toString();
    }
}