package Modelo;

import java.util.ArrayList;

public class Asistencia {
    private String fecha = ""; // formato aaaa/mm/dd
    private String hora_de_inicio = "";
    private String hora_final = "";
    private ArrayList<String> codigos = new ArrayList<String>();
    private ArrayList<String> estados = new ArrayList<String>(); // 0: A tiempo, 1: tarde, 2: no llegó

    public Asistencia() {
        // Constructor vacío
    }

    public Asistencia(String fecha, String hora_de_inicio, String hora_final) {
        this.fecha = fecha;
        this.hora_de_inicio = hora_de_inicio;
        this.hora_final = hora_final;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora_de_inicio(String hora_de_inicio) {
        this.hora_de_inicio = hora_de_inicio;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora_de_inicio() {
        return hora_de_inicio;
    }

    public String getHora_final() {
        return hora_final;
    }

    public boolean adicionarAsistencia(String codigo, String estado) {
        codigos.add(codigo);
        estados.add(estado);
        return true;
    }

    public String consultarAsistencia(String codigo) {
        for (int vc = 0; vc < codigos.size(); vc++) {
            if (codigos.get(vc).equalsIgnoreCase(codigo)) {
                return estados.get(vc);
            }
        }
        return null;
    }

    public boolean modificarAsistencia(String codigo, String estado) {
        for (int vc = 0; vc < codigos.size(); vc++) {
            if (codigos.get(vc).equalsIgnoreCase(codigo)) {
                estados.set(vc, estado);
                return true;
            }
        }
        return false;
    }
    public boolean setEstados(ArrayList<String> estados) {
        this.estados = estados;
        return true;
    }
    public boolean setCodigos(ArrayList<String> codigos) {
        this.codigos = codigos;
        return true;
    }

}
