package Vista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controlador.ElControlador;
import Modelo.Asignatura;
import Modelo.Asistencia;
import Modelo.Estudiante;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElControlador controlador = new ElControlador();
        String opcion = "";

        while (!opcion.equals("15")) {
            try {
                System.out.println("\n***** SISTEMA DE GESTIÓN ACADÉMICA *****");
                System.out.println("1) Consultar Departamento");
                System.out.println("2) Modificar Departamento");
                System.out.println("3) Registrar Estudiantes en el Departamento");
                System.out.println("4) Consultar Estudiantes en el Departamento");
                System.out.println("5) Modificar Estudiante en el Departamento");
                System.out.println("6) Agregar Asignatura");
                System.out.println("7) Consultar Asignatura");
                System.out.println("8) Modificar Asignatura");
                System.out.println("9) Registrar Estudiante en Asignatura");
                System.out.println("10) Consultar Estudiantes en Asignatura");
                System.out.println("11) Crear Lista de Asistencia Vacía");
                System.out.println("12) Llenar Asistencia");
                System.out.println("13) Modificar Asistencia");
                System.out.println("14) Consultar Asistencia");
                System.out.println("15) Salir");
                System.out.print("Digite la opción deseada: ");
                opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        consultarDepartamento(controlador);
                        break;
                    case "2":
                        modificarDepartamento(controlador, scanner);
                        break;
                    case "3":
                        registrarEstudianteEnDepartamento(controlador, scanner);
                        break;
                    case "4":
                        consultarEstudianteEnDepartamento(controlador, scanner);
                        break;
                    case "5":
                        modificarEstudianteEnDepartamento(controlador, scanner);
                        break;
                    case "6":
                        agregarAsignatura(controlador, scanner);
                        break;
                    case "7":
                        consultarAsignatura(controlador, scanner);
                        break;
                    case "8":
                        modificarAsignatura(controlador, scanner);
                        break;
                    case "9":
                        registrarEstudianteEnAsignatura(controlador, scanner);
                        break;
                    case "10":
                        consultarEstudiantesEnAsignatura(controlador, scanner);
                        break;
                    case "11":
                        crearListaAsistenciaVacia(controlador, scanner);
                        break;
                    case "12":
                        llenarAsistencia(controlador, scanner);
                        break;
                    case "13":
                        modificarAsistencia(controlador, scanner);
                        break;
                    case "14":
                        consultarAsistencia(controlador, scanner);
                        break;
                    case "15":
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, intente de nuevo.");
                scanner.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void consultarDepartamento(ElControlador controlador) {
        String nombreDepto = controlador.consultarDepartamento();
        if (nombreDepto != null && !nombreDepto.isEmpty()) {
            System.out.println("Nombre del Departamento: " + nombreDepto);
        } else {
            System.out.println("No se ha registrado ningún departamento.");
        }
    }

    private static void modificarDepartamento(ElControlador controlador, Scanner scanner) {
        System.out.print("Nuevo nombre del Departamento: ");
        String nuevoNombre = scanner.nextLine();
        if (controlador.actualizarDepartamento(nuevoNombre)) {
            System.out.println("Departamento actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el departamento.");
        }
    }

    private static void registrarEstudianteEnDepartamento(ElControlador controlador, Scanner scanner) {
        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.nextLine();
        System.out.print("Tipo de documento: ");
        String tipoDocumento = scanner.nextLine();
        System.out.print("Número de documento: ");
        String numeroDocumento = scanner.nextLine();

        if (controlador.registrarEstudianteEnDepartamento(nombre, numeroDocumento, tipoDocumento)) {
            System.out.println("Estudiante registrado en el departamento.");
        } else {
            System.out.println("Error al registrar el estudiante.");
        }
    }

    private static void consultarEstudianteEnDepartamento(ElControlador controlador, Scanner scanner) {
        System.out.print("Tipo de documento: ");
        String tipoDocumento = scanner.nextLine();
        System.out.print("Número de documento: ");
        String numeroDocumento = scanner.nextLine();

        Estudiante estudiante = controlador.consultarEstudianteEnDepartamento(tipoDocumento, numeroDocumento);
        if (estudiante != null) {
            System.out.println("Estudiante encontrado: " + estudiante);
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static void modificarEstudianteEnDepartamento(ElControlador controlador, Scanner scanner) {
        System.out.print("Tipo de documento actual: ");
        String tipoDocumento = scanner.nextLine();
        System.out.print("Número de documento actual: ");
        String numeroDocumento = scanner.nextLine();
        System.out.print("Nuevo tipo de documento: ");
        String nuevoTipoDocumento = scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();

        if (controlador.modificarEstudianteEnDepartamento(tipoDocumento, numeroDocumento, nuevoTipoDocumento, nuevoNombre)) {
            System.out.println("Estudiante modificado en el departamento.");
        } else {
            System.out.println("Error al modificar el estudiante.");
        }
    }

    private static void agregarAsignatura(ElControlador controlador, Scanner scanner) {
        try {
            System.out.print("Nombre de la Asignatura: ");
            String nombre = scanner.nextLine();
            System.out.print("Código de la Asignatura: ");
            String codigo = scanner.nextLine();
            System.out.print("Grupo (1 o 2): ");
            String grupo = scanner.nextLine();
            System.out.print("Semestre (2025-1): ");
            String semestre = scanner.nextLine();
            System.out.print("Créditos: ");
            int creditos = Integer.parseInt(scanner.nextLine());

            if (controlador.agregarAsignatura(nombre, creditos, codigo, grupo, semestre)) {
                System.out.println("Asignatura añadida correctamente.");
            } else {
                System.out.println("Error al agregar la asignatura.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los créditos deben ser un número entero.");
        }
    }

    private static void consultarAsignatura(ElControlador controlador, Scanner scanner) {
        System.out.print("Código de la Asignatura: ");
        String codigo = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.print("Semestre: ");
        String semestre = scanner.nextLine();

        Asignatura asignatura = controlador.consultarAsignatura(codigo, grupo, semestre);
        if (asignatura != null) {
            System.out.println("Asignatura encontrada: " + asignatura);
        } else {
            System.out.println("Asignatura no encontrada.");
        }
    }

    private static void modificarAsignatura(ElControlador controlador, Scanner scanner) {
        try {
            System.out.print("Código de la Asignatura a modificar: ");
            String codigo = scanner.nextLine();
            System.out.print("Grupo: ");
            String grupo = scanner.nextLine();
            System.out.print("Semestre: ");
            String semestre = scanner.nextLine();
            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevos créditos: ");
            int creditos = Integer.parseInt(scanner.nextLine());

            if (controlador.modificarAsignatura(codigo, grupo, semestre, nombre, creditos)) {
                System.out.println("Asignatura modificada correctamente.");
            } else {
                System.out.println("Error al modificar la asignatura.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Los créditos deben ser un número entero.");
        }
    }

    private static void registrarEstudianteEnAsignatura(ElControlador controlador, Scanner scanner) {
        System.out.print("Código de la Asignatura: ");
        String codigo = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.print("Semestre: ");
        String semestre = scanner.nextLine();
        System.out.print("Tipo de documento del estudiante: ");
        String tipoDocumento = scanner.nextLine();
        System.out.print("Número de documento del estudiante: ");
        String numeroDocumento = scanner.nextLine();

        if (controlador.registrarEstudianteEnAsignatura(codigo, grupo, semestre, tipoDocumento, numeroDocumento)) {
            System.out.println("Estudiante registrado en la asignatura correctamente.");
        } else {
            System.out.println("Error al registrar el estudiante en la asignatura.");
        }
    }

    private static void consultarEstudiantesEnAsignatura(ElControlador controlador, Scanner scanner) {
        System.out.print("Código de la Asignatura: ");
        String codigo = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.print("Semestre: ");
        String semestre = scanner.nextLine();

        ArrayList<Estudiante> estudiantes = controlador.consultarEstudiantesEnAsignatura(codigo, grupo, semestre);
        if (estudiantes != null && !estudiantes.isEmpty()) {
            System.out.println("Estudiantes inscritos en la asignatura:");
            for (Estudiante estudiante : estudiantes) {
                System.out.println(" - Tipo: " + estudiante.getIdentificacion() + ", Documento: " + estudiante.getDocumento());
            }
        } else {
            System.out.println("No hay estudiantes inscritos en la asignatura.");
        }
    }

    private static void crearListaAsistenciaVacia(ElControlador controlador, Scanner scanner) {
        System.out.print("Código de la Asignatura: ");
        String codigo = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.print("Semestre: ");
        String semestre = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Hora de Inicio (HH:MM): ");
        String horaInicio = scanner.nextLine();
        System.out.print("Hora Final (HH:MM): ");
        String horaFinal = scanner.nextLine();

        if (controlador.adicionarAsistencia(codigo, grupo, semestre, fecha, horaInicio, horaFinal, new ArrayList<>(), new ArrayList<>())) {
            System.out.println("Lista de asistencia vacía creada correctamente.");
        } else {
            System.out.println("Error al crear la lista de asistencia.");
        }
    }

    private static void llenarAsistencia(ElControlador controlador, Scanner scanner) {
        System.out.print("Código de la Asignatura: ");
        String codigo = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.print("Semestre: ");
        String semestre = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Hora de Inicio (HH:MM): ");
        String horaInicio = scanner.nextLine();
        System.out.print("Hora Final (HH:MM): ");
        String horaFinal = scanner.nextLine();

        ArrayList<String> codigos = new ArrayList<>();
        ArrayList<String> estados = new ArrayList<>();

        System.out.print("Número de estudiantes en la asistencia: ");
        int numEstudiantes = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numEstudiantes; i++) {
            System.out.print("Código del estudiante: ");
            codigos.add(scanner.nextLine());
            System.out.print("Estado del estudiante (A tiempo, Tarde, No llegó): ");
            estados.add(scanner.nextLine());
        }

        if (controlador.adicionarAsistencia(codigo, grupo, semestre, fecha, horaInicio, horaFinal, codigos, estados)) {
            System.out.println("Asistencia registrada correctamente.");
        } else {
            System.out.println("Error al registrar la asistencia.");
        }
    }

    private static void modificarAsistencia(ElControlador controlador, Scanner scanner) {
        System.out.print("Código de la Asignatura: ");
        String codigo = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.print("Semestre: ");
        String semestre = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Hora de Inicio (HH:MM): ");
        String horaInicio = scanner.nextLine();
        System.out.print("Hora Final (HH:MM): ");
        String horaFinal = scanner.nextLine();

        System.out.print("Nueva Fecha (YYYY-MM-DD): ");
        String nuevaFecha = scanner.nextLine();
        System.out.print("Nueva Hora de Inicio (HH:MM): ");
        String nuevaHoraInicio = scanner.nextLine();
        System.out.print("Nueva Hora Final (HH:MM): ");
        String nuevaHoraFinal = scanner.nextLine();

        ArrayList<String> codigos = new ArrayList<>();
        ArrayList<String> estados = new ArrayList<>();

        System.out.print("Número de estudiantes en la asistencia: ");
        int numEstudiantes = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numEstudiantes; i++) {
            System.out.print("Código del estudiante: ");
            codigos.add(scanner.nextLine());
            System.out.print("Estado del estudiante (A tiempo, Tarde, No llegó): ");
            estados.add(scanner.nextLine());
        }

        if (controlador.modificarAsistencia(codigo, grupo, semestre, fecha, horaInicio, horaFinal, nuevaFecha, nuevaHoraInicio, nuevaHoraFinal, codigos, estados)) {
            System.out.println("Asistencia modificada correctamente.");
        } else {
            System.out.println("Error al modificar la asistencia.");
        }
    }

    private static void consultarAsistencia(ElControlador controlador, Scanner scanner) {
        System.out.print("Código de la Asignatura: ");
        String codigo = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();
        System.out.print("Semestre: ");
        String semestre = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Hora de Inicio (HH:MM): ");
        String horaInicio = scanner.nextLine();
        System.out.print("Hora Final (HH:MM): ");
        String horaFinal = scanner.nextLine();

        Asistencia asistencia = controlador.consultarAsistencia(codigo, grupo, semestre, fecha, horaInicio, horaFinal);
        if (asistencia != null) {
            System.out.println("Asistencia encontrada: " + asistencia);
        } else {
            System.out.println("Asistencia no encontrada.");
        }
    }
}