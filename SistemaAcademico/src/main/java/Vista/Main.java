package Vista;

import Controlador.ElControlador;
import Modelo.Asignatura;
import Modelo.Asistencia;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElControlador controlador = new ElControlador();
        String opcion = "";
        String nombre, codigo, grupo, semestre, fecha, horaInicio, horaFinal;
        ArrayList<String> codigosEstudiantes = new ArrayList<>();
        ArrayList<String> estadosAsistencia = new ArrayList<>();

        while (!opcion.equalsIgnoreCase("4")) {
            System.out.println("***** SISTEMA DE GESTIÓN ACADÉMICA *****");
            System.out.println("1) Gestión de Facultad");
            System.out.println("2) Gestión de Asignaturas");
            System.out.println("3) Gestión de Asistencias");
            System.out.println("4) Salir");
            System.out.print("Digite la opción deseada: ");
            opcion = scanner.nextLine();

            if (opcion.equalsIgnoreCase("1")) {
                // Submenú Facultad
                String opcionFacultad = "";
                while (!opcionFacultad.equalsIgnoreCase("4")) {
                    System.out.println("\n***** GESTIÓN DE FACULTAD *****");
                    System.out.println("1) Crear Facultad");
                    System.out.println("2) Consultar Facultad");
                    System.out.println("3) Modificar Facultad");
                    System.out.println("4) Volver");
                    System.out.print("Digite la opción deseada: ");
                    opcionFacultad = scanner.nextLine();

                    if (opcionFacultad.equalsIgnoreCase("1")) {
                        System.out.print("Nombre de la facultad: ");
                        nombre = scanner.nextLine();
                        boolean resultado = controlador.crearDepartamento(nombre);
                        System.out.println(resultado ? "Facultad creada exitosamente" : "Error al crear facultad");
                    } else if (opcionFacultad.equalsIgnoreCase("2")) {
                        nombre = controlador.consultarDepartamento();
                        System.out.println("Nombre de la facultad: " + nombre);
                    } else if (opcionFacultad.equalsIgnoreCase("3")) {
                        System.out.print("Nuevo nombre de la facultad: ");
                        nombre = scanner.nextLine();
                        boolean resultado = controlador.actualizarDepartamento(nombre);
                        System.out.println(resultado ? "Facultad actualizada" : "Error al actualizar");
                    }
                }
            } else if (opcion.equalsIgnoreCase("2")) {
                // Submenú Asignaturas
                String opcionAsignatura = "";
                while (!opcionAsignatura.equalsIgnoreCase("5")) {
                    System.out.println("\n***** GESTIÓN DE ASIGNATURAS *****");
                    System.out.println("1) Crear Asignatura");
                    System.out.println("2) Consultar Asignatura");
                    System.out.println("3) Modificar Asignatura");
                    System.out.println("4) Eliminar Asignatura");
                    System.out.println("5) Volver");
                    System.out.print("Digite la opción deseada: ");
                    opcionAsignatura = scanner.nextLine();

                    if (opcionAsignatura.equalsIgnoreCase("1")) {
                        System.out.print("Nombre de la asignatura: ");
                        nombre = scanner.nextLine();
                        System.out.print("Créditos: ");
                        String creditosStr = scanner.nextLine();
                        System.out.print("Código: ");
                        codigo = scanner.nextLine();
                        System.out.print("Grupo: ");
                        grupo = scanner.nextLine();
                        System.out.print("Semestre: ");
                        semestre = scanner.nextLine();

                        controlador.agregarAsignatura(nombre, creditosStr, codigo, grupo, semestre);
                        System.out.println("Asignatura creada exitosamente");
                    } else if (opcionAsignatura.equalsIgnoreCase("2")) {
                        System.out.print("Código de la asignatura: ");
                        codigo = scanner.nextLine();
                        System.out.print("Grupo: ");
                        grupo = scanner.nextLine();
                        System.out.print("Semestre: ");
                        semestre = scanner.nextLine();

                        Asignatura asignatura = controlador.consultarAsignatura(codigo, grupo, semestre);
                        if (asignatura != null) {
                            System.out.println("\nInformación de la asignatura:");
                            System.out.println("Nombre: " + asignatura.getNombre());
                            System.out.println("Créditos: " + asignatura.getCreditos());
                            System.out.println("Código: " + asignatura.getCodigo());
                            System.out.println("Grupo: " + asignatura.getGrupo());
                            System.out.println("Semestre: " + asignatura.getSemestre());
                        } else {
                            System.out.println("Asignatura no encontrada");
                        }
                    } else if (opcionAsignatura.equalsIgnoreCase("3")) {
                        System.out.print("Código de la asignatura a modificar: ");
                        codigo = scanner.nextLine();
                        System.out.print("Grupo: ");
                        grupo = scanner.nextLine();
                        System.out.print("Semestre: ");
                        semestre = scanner.nextLine();

                        System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevos créditos (dejar vacío para no cambiar): ");
                        String nuevosCreditosStr = scanner.nextLine();

                        int nuevosCreditos = -1;
                        if (!nuevosCreditosStr.isEmpty()) {
                            nuevosCreditos = Integer.parseInt(nuevosCreditosStr);
                        }

                        boolean resultado = controlador.modificarAsignatura(codigo, grupo, semestre,
                                nuevoNombre.isEmpty() ? null : nuevoNombre,
                                nuevosCreditosStr.isEmpty() ? -1 : nuevosCreditos);

                        System.out.println(resultado ? "Asignatura modificada exitosamente" : "Error al modificar asignatura");
                    } else if (opcionAsignatura.equalsIgnoreCase("4")) {
                        System.out.print("Código de la asignatura a eliminar: ");
                        codigo = scanner.nextLine();
                        System.out.print("Grupo: ");
                        grupo = scanner.nextLine();
                        System.out.print("Semestre: ");
                        semestre = scanner.nextLine();

                        boolean resultado = controlador.eliminarAsignatura(codigo, grupo, semestre);
                        System.out.println(resultado ? "Asignatura eliminada exitosamente" : "Error al eliminar asignatura");
                    }
                }
            } else if (opcion.equalsIgnoreCase("3")) {
                // Submenú Asistencias
                String opcionAsistencia = "";
                while (!opcionAsistencia.equalsIgnoreCase("5")) {
                    System.out.println("\n***** GESTIÓN DE ASISTENCIAS *****");
                    System.out.println("1) Registrar Asistencia");
                    System.out.println("2) Consultar Asistencia");
                    System.out.println("3) Modificar Asistencia");
                    System.out.println("4) Eliminar Asistencia");
                    System.out.println("5) Volver");
                    System.out.print("Digite la opción deseada: ");
                    opcionAsistencia = scanner.nextLine();

                    if (opcionAsistencia.equals("1")) {
                        System.out.print("Código de la asignatura: ");
                        codigo = scanner.nextLine();
                        System.out.print("Grupo: ");
                        grupo = scanner.nextLine();
                        System.out.print("Semestre: ");
                        semestre = scanner.nextLine();
                        System.out.print("Fecha (AAAA/MM/DD): ");
                        fecha = scanner.nextLine();
                        System.out.print("Hora de inicio: ");
                        horaInicio = scanner.nextLine();
                        System.out.print("Hora final: ");
                        horaFinal = scanner.nextLine();

                        codigosEstudiantes.clear();
                        estadosAsistencia.clear();
                        System.out.println("Ingrese los datos de los estudiantes (deje vacío para terminar):");
                        while (true) {
                            System.out.print("Código del estudiante: ");
                            String codEst = scanner.nextLine();
                            if (codEst.isEmpty()) break;

                            System.out.print("Estado (0-A tiempo, 1-Tarde, 2-Ausente): ");
                            String estado = scanner.nextLine();

                            codigosEstudiantes.add(codEst);
                            estadosAsistencia.add(estado);
                        }

                        boolean resultado = controlador.adicionarAsistencia(
                                codigo, grupo, semestre, fecha, horaInicio, horaFinal,
                                codigosEstudiantes, estadosAsistencia);

                        System.out.println(resultado ? "Asistencia registrada exitosamente" : "Error al registrar asistencia");
                    } else if (opcionAsistencia.equals("2")) {
                        System.out.print("Código de la asignatura: ");
                        codigo = scanner.nextLine();
                        System.out.print("Grupo: ");
                        grupo = scanner.nextLine();
                        System.out.print("Semestre: ");
                        semestre = scanner.nextLine();
                        System.out.print("Fecha: ");
                        fecha = scanner.nextLine();
                        System.out.print("Hora de inicio: ");
                        horaInicio = scanner.nextLine();
                        System.out.print("Hora final: ");
                        horaFinal = scanner.nextLine();

                        Asistencia asistencia = controlador.consultarAsistencia(codigo, grupo, semestre, fecha, horaInicio, horaFinal);
                        if (asistencia != null) {
                            System.out.println("\nInformación de la Asistencia:");
                            System.out.println("Fecha: " + asistencia.getFecha());
                            System.out.println("Hora de inicio: " + asistencia.getHora_de_inicio());
                            System.out.println("Hora final: " + asistencia.getHora_final());
                        } else {
                            System.out.println("Asistencia no encontrada");
                        }
                    } else if (opcionAsistencia.equals("3")) {
                        System.out.print("Código de la asignatura: ");
                        codigo = scanner.nextLine();
                        System.out.print("Grupo: ");
                        grupo = scanner.nextLine();
                        System.out.print("Semestre: ");
                        semestre = scanner.nextLine();
                        System.out.print("Fecha actual (AAAA/MM/DD): ");
                        fecha = scanner.nextLine();
                        System.out.print("Hora de inicio actual: ");
                        horaInicio = scanner.nextLine();
                        System.out.print("Hora final actual: ");
                        horaFinal = scanner.nextLine();

                        System.out.print("Nueva fecha (dejar vacío para no cambiar): ");
                        String nuevaFecha = scanner.nextLine();
                        System.out.print("Nueva hora de inicio (dejar vacío para no cambiar): ");
                        String nuevaHoraInicio = scanner.nextLine();
                        System.out.print("Nueva hora final (dejar vacío para no cambiar): ");
                        String nuevaHoraFinal = scanner.nextLine();

                        // Leer nuevos datos de estudiantes si es necesario
                        codigosEstudiantes.clear();
                        estadosAsistencia.clear();
                        System.out.println("Ingrese los nuevos datos de los estudiantes (deje vacío para no cambiar):");
                        while (true) {
                            System.out.print("Código del estudiante: ");
                            String codEst = scanner.nextLine();
                            if (codEst.isEmpty()) break;

                            System.out.print("Estado (0-A tiempo, 1-Tarde, 2-Ausente): ");
                            String estado = scanner.nextLine();

                            codigosEstudiantes.add(codEst);
                            estadosAsistencia.add(estado);
                        }

                        boolean resultado = controlador.modificarAsistencia(
                                codigo, grupo, semestre,
                                fecha, horaInicio, horaFinal,
                                nuevaFecha.isEmpty() ? null : nuevaFecha,
                                nuevaHoraInicio.isEmpty() ? null : nuevaHoraInicio,
                                nuevaHoraFinal.isEmpty() ? null : nuevaHoraFinal,
                                codigosEstudiantes.isEmpty() ? null : codigosEstudiantes,
                                estadosAsistencia.isEmpty() ? null : estadosAsistencia);

                        System.out.println(resultado ? "Asistencia modificada exitosamente" : "Error al modificar asistencia");
                    } else if (opcionAsistencia.equals("4")) {
                        System.out.print("Código de la asignatura: ");
                        codigo = scanner.nextLine();
                        System.out.print("Grupo: ");
                        grupo = scanner.nextLine();
                        System.out.print("Semestre: ");
                        semestre = scanner.nextLine();
                        System.out.print("Fecha: ");
                        fecha = scanner.nextLine();
                        System.out.print("Hora de inicio: ");
                        horaInicio = scanner.nextLine();
                        System.out.print("Hora final: ");
                        horaFinal = scanner.nextLine();

                        boolean resultado = controlador.eliminarAsistencia(
                                codigo, grupo, semestre, fecha, horaInicio, horaFinal);

                        System.out.println(resultado ? "Asistencia eliminada exitosamente" : "Error al eliminar asistencia");
                    }
                }
            }
        }
        scanner.close();
        System.out.println("Saliendo del sistema...");
    }
}