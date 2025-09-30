/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ulatina.controller.EstudianteController;
import ulatina.controller.MateriasController;
import ulatina.model.Estudiante;
import ulatina.model.Materia;

/**
 *
 * @author Esteban
 */
public class EstudianteControllerTest {
    
    private EstudianteController controller;
    private MateriasController controllerMaterias;
    
    @BeforeEach
    void limpiarBD() throws Exception {
        controller = new EstudianteController();
        controllerMaterias = new MateriasController();
        controller.eliminarTodosEstudiantes();
        controllerMaterias.eliminarTodasMaterias();
    }
    
    @Test
    void testCrearYBuscar() throws SQLException, ClassNotFoundException {
        System.out.println("=== Test Crear y Buscar ===");
        controller.crear("Esteban" , "esteban@gmail");
        Estudiante e = controller.buscarPorCorreo("esteban@gmail");
        System.out.println("Estudiante creado: " + e.getNombre() + "" + e.getCorreo());
        assertNotNull(e);
        assertEquals("Esteban", e.getNombre());
    }

    @Test
    void testListar() throws SQLException, ClassNotFoundException {
        System.out.println("=== Test Listar ===");
        controller.crear("Jaime" , "jaime@gmail");
        controller.crear("Esteban" , "esteban@gmail");
        List<Estudiante> estudiantes = controller.listar();
        System.out.println("Estudiantes encontrados: " + estudiantes.size());
        estudiantes.forEach(est -> System.out.println(" - " + est.getNombre()));
        assertTrue(estudiantes.size() >= 2);
    }

    @Test
    void testActualizar() throws SQLException, ClassNotFoundException {
        System.out.println("=== Test Actualizar ===");
        controller.crear("Jaime" , "jaime@gmail");
        controller.crear("Esteban" , "esteban@gmail");
        Estudiante e = controller.buscarPorCorreo("esteban@gmail");
        System.out.println("Estudiante antes de actualizar: " + e.getCorreo());
        controller.actualizar(e.getId(), "Esteban" , "correoActualizado@gmail");
        Estudiante actualizado = controller.buscarPorCorreo("correoActualizado@gmail");
        System.out.println("Estudiante después de actualizar: " + (actualizado.getCorreo()));
        assertNotNull(actualizado);
        assertEquals("correoActualizado@gmail", actualizado.getCorreo());
    }

    @Test
    void testEliminar() throws SQLException, ClassNotFoundException {
        System.out.println("=== Test Eliminar ===");
        controller.crear("Jaime" , "jaime@gmail");
        Estudiante e = controller.buscarPorCorreo("jaime@gmail");
        System.out.println("Estudiante antes de eliminar: " + (e.getCorreo()));
        controller.eliminar(e.getId());
        Estudiante eliminado = controller.buscarPorCorreo("jaime@gmail");
        System.out.println("Estudiante después de eliminar: " + (eliminado != null ? eliminado.getCorreo(): "null"));
        assertNull(eliminado);
    }
    
    @Test
    void testMatricularEstudiante() throws SQLException, ClassNotFoundException {
        System.out.println("=== Test Matricular Al Estudiante ===");
        controller.crear("Jaime" , "jaime@gmail");
        controllerMaterias.crear("Matematica");
        Estudiante e = controller.buscarPorCorreo("jaime@gmail");
        Materia m = controllerMaterias.buscarPorNombre("Matematica");
        List<Materia> materiasEstudiante = controller.obtenerMateriasDelEstudiante(e.getId());
        System.out.println("Materias del estudiante antes de matricular: " + materiasEstudiante.size());
        controller.matricularEstudiante(e.getId(), m.getId());
        System.err.println("\nSe ha matriculado al estudiante " + e.getNombre() + " en la materia de " + m.getNombre());
        materiasEstudiante = controller.obtenerMateriasDelEstudiante(e.getId());
        System.out.println("=== Materias del estudiante despues de matricular: " + materiasEstudiante.size() + " ===");
        
    }
    
    @Test
    void testInsertarEstudianteYMateria() throws SQLException, ClassNotFoundException, Exception {
        System.out.println("=== Test: Insertar Estudiante, Materia y Relación ===");
        Estudiante e = new Estudiante();
        e.setNombre("Juan Perez");
        e.setCorreo("juanperez@email.com");
        Materia m = new Materia();
        m.setNombre("Matemáticas");
        controller.insertarEstudianteYMateria(e, m);
        List<Materia> materiasAsignadas = controller.obtenerMateriasDelEstudiante(e.getId());
        System.out.println("Materias asignadas al estudiante " + e.getNombre() + ":");
        for (Materia mat : materiasAsignadas) {
            System.out.println(mat.getId() + " | " + mat.getNombre());
        }
    }
}
