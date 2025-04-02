
package Modelo;

import java.util.ArrayList;

public class Asignatura {
    private String codigo = "";
    private String grupo = "";
    private String semestre = "";
    private String nombre = "";
    private int creditos = 0;
    private ArrayList<Asistencia> asistencias = new ArrayList<Asistencia>();

    public Asignatura(String codigo, String grupo, String semestre, String nombre, int creditos) {
        this.codigo = codigo;
        this.grupo = grupo;
        this.semestre = semestre;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public Asignatura() {
        // Constructor vacío
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public boolean adicionarAsistencia(String fecha, String hora_inicio, String hora_final,
                                       ArrayList<String> codigos, ArrayList<String> estados) {
        Asistencia asistencia = new Asistencia(fecha, hora_inicio, hora_final);
        for (int vc = 0; vc < codigos.size(); vc++) {
            String codigo = codigos.get(vc);
            String estado = estados.get(vc);
            asistencia.adicionarAsistencia(codigo, estado);
        }
        asistencias.add(asistencia);
        return true;
    }

    public Asistencia consultarAsistencia(String fecha, String hora_inicio, String hora_final) {
        for (int vc = 0; vc < asistencias.size(); vc++) {
            if (asistencias.get(vc).getFecha().equalsIgnoreCase(fecha) &&
                    asistencias.get(vc).getHora_de_inicio().equalsIgnoreCase(hora_inicio) &&
                    asistencias.get(vc).getHora_final().equalsIgnoreCase(hora_final)) {
                return asistencias.get(vc);
            }
        }
        return null;
    }
    public boolean modificarAsistencia(String fecha, String hora_inicio, String hora_final,
                                       String fechan, String hora_inicion, String hora_finaln,
                                       ArrayList<String> codigos, ArrayList<String> estados) {
        Asistencia laasistencia = this.consultarAsistencia(fecha, hora_inicio, hora_final);
        if (laasistencia != null) {
            laasistencia.setFecha(fechan);
            laasistencia.setHora_de_inicio(hora_inicion);
            laasistencia.setHora_final(hora_finaln);
            laasistencia.setCodigos(codigos);
            laasistencia.setEstados(estados);
            return true;
        }
        return false;
    }
    public boolean eliminarAsistencia(String fecha, String horaInicio, String horaFinal) {
        for (int i = 0; i < asistencias.size(); i++) {
            Asistencia a = asistencias.get(i);
            if (a.getFecha().equalsIgnoreCase(fecha) &&
                    a.getHora_de_inicio().equalsIgnoreCase(horaInicio) &&
                    a.getHora_final().equalsIgnoreCase(horaFinal)) {
                asistencias.remove(i);
                return true;
            }
        }
        return false;
    }
}