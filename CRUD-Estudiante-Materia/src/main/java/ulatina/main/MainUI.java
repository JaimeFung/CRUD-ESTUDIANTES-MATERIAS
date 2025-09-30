/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ulatina.main;

import java.util.Scanner;
import ulatina.controller.EstudianteController;
import ulatina.controller.MateriasController;

import ulatina.model.Estudiante;
import ulatina.model.Materia;

/**
 *
 * @author jaime
 */
public class MainUI {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EstudianteController estudianteController = new EstudianteController();
            MateriasController materiaController = new MateriasController();

            
            int opcionPrincipal;
            
            do {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. CRUD Estudiantes");
                System.out.println("2. CRUD Materias");
                System.out.println("3. Matricular estudiante");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opcion: ");
                opcionPrincipal = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcionPrincipal) {
                    case 1:
                        crudEstudiantes(scanner, estudianteController);
                        break;
                    case 2:
                        crudMaterias(scanner, materiaController);
                        break;
                    case 3:
                        menuMatricula(scanner, estudianteController);
                        break;
                        
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcionPrincipal != 0);
        }
    }
    
    
    private static void crudEstudiantes(Scanner scanner, EstudianteController controller) {
        int opcion;
        do {
            System.out.println("\n=== CRUD Estudiantes ===");
            System.out.println("1. Listar estudiantes");
            System.out.println("2. Insertar estudiante");
            System.out.println("3. Actualizar estudiante");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Buscar estudiante por correo");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1:
                        for (Estudiante e : controller.listar()) {
                            System.out.println(e.getId() + " | " + e.getNombre() + " | " + e.getCorreo());
                        }
                        break;
                    case 2:

                        System.out.print("Nombre del estudiante: ");
                        String nombreEst = scanner.nextLine();
                        System.out.print("Correo del estudiante: ");
                        String correoEst = scanner.nextLine();
                        Estudiante estudiante = new Estudiante();
                        estudiante.setNombre(nombreEst);
                        estudiante.setCorreo(correoEst);

                        
                        System.out.print("Nombre de la materia: ");
                        String nombreMat = scanner.nextLine();
                        Materia materia = new Materia();
                        materia.setNombre(nombreMat);

                        try {
                            controller.insertarEstudianteYMateria(estudiante, materia);
                            System.out.println("Insercion completada correctamente.");
                        } catch (Exception e) {
                            System.out.println("Error al insertar: " + e.getMessage());
                        }
                        break;
                        

                    case 3:
                        System.out.print("ID: ");
                        int id = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String newNombre = scanner.nextLine();
                        System.out.print("Nuevo correo: ");
                        String newCorreo = scanner.nextLine();
                        controller.actualizar(id, newNombre, newCorreo);
                        
                        break;
                    case 4:
                        System.out.print("ID: ");
                        int idEliminar = scanner.nextInt(); scanner.nextLine();
                        controller.eliminar(idEliminar);
                        System.out.println("Estudiante eliminado.");
                        break;
                    case 5:
                        System.out.print("Correo: ");
                        String buscarCorreo = scanner.nextLine();
                        Estudiante e = controller.buscarPorCorreo(buscarCorreo);
                        if (e != null) {
                            System.out.println(e.getId() + " | " + e.getNombre() + " | " + e.getCorreo());
                        } else {
                            System.out.println("No encontrado.");
                        }
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }

        } while (opcion != 0);
    }


    private static void crudMaterias(Scanner scanner, MateriasController controller) {
        int opcion;
        do {
            System.out.println("\n=== CRUD Materias ===");
            System.out.println("1. Listar materias");
            System.out.println("2. Insertar materia");
            System.out.println("3. Actualizar materia");
            System.out.println("4. Eliminar materia");
            System.out.println("5. Buscar materia por nombre");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1:
                        for (Materia m : controller.listar()) {
                            System.out.println(m.getId() + " | " + m.getNombre());
                        }
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        controller.crear(nombre);
                        System.out.println("Materia insertada.");
                        break;
                    case 3:
                        System.out.print("ID: ");
                        int id = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        controller.actualizar(id, nuevoNombre);
                        System.out.println("Materia actualizada.");
                        break;
                    case 4:
                        System.out.print("ID: ");
                        int idEliminar = scanner.nextInt(); scanner.nextLine();
                        controller.eliminar(idEliminar);
                        System.out.println("Materia eliminada.");
                        break;
                    case 5:
                        System.out.print("Nombre: ");
                        String buscar = scanner.nextLine();
                        Materia m = controller.buscarPorNombre(buscar);
                        if (m != null) {
                            System.out.println(m.getId() + " | " + m.getNombre());
                        } else {
                            System.out.println("No se encontró esa materia.");
                        }
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }

        } while (opcion != 0);
    }
    private static void menuMatricula(Scanner scanner, EstudianteController controller) {
    int opcion;
    do {
        System.out.println("\n=== MATRICULACION ===");
        System.out.println("1. Asignar materia a estudiante");
        System.out.println("2. Ver materias de un estudiante");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opcion: ");
        opcion = scanner.nextInt(); scanner.nextLine();

        try {
            switch (opcion) {
                case 1:
                    System.out.println("\n--- Estudiantes ---");
                    for (Estudiante e : controller.listarEstudiantes()) {
                        System.out.println(e.getId() + " | " + e.getNombre());
                    }
                    System.out.print("ID del estudiante: ");
                    int idEstudiante = scanner.nextInt(); scanner.nextLine();

                    System.out.println("\n--- Materias ---");
                    for (Materia m : controller.listarMaterias()) {
                        System.out.println(m.getId() + " | " + m.getNombre());
                    }
                    System.out.print("ID de la materia: ");
                    int idMateria = scanner.nextInt(); scanner.nextLine();

                    controller.matricularEstudiante(idEstudiante, idMateria);
                    System.out.println("Materia asignada al estudiante.");
                    break;

                case 2:
                    System.out.println("\n--- Estudiantes ---");
                    for (Estudiante e : controller.listarEstudiantes()) {
                        System.out.println(e.getId() + " | " + e.getNombre());
                    }
                    System.out.print("ID del estudiante: ");
                    int id = scanner.nextInt(); scanner.nextLine();

                    System.out.println("Materias del estudiante:");
                    for (Materia m : controller.obtenerMateriasDelEstudiante(id)) {
                        System.out.println(m.getId() + " | " + m.getNombre());
                    }
                    break;

                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opción invalida.");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    } while (opcion != 0);
}
}
