/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ulatina.controller.MateriasController;
import ulatina.model.Materia;

/**
 *
 * @author jaime
 */
public class MateriasControllerTest {

    private MateriasController controller;
    
    @BeforeEach
    void limpiarBD() throws Exception {
        controller = new MateriasController();
        controller.eliminarTodasMaterias();
        
    }


    @Test
    void testCrearYBuscar() throws SQLException, ClassNotFoundException {
        System.out.println("=== Test Crear y Buscar ===");
        controller.crear("Matemáticas");
        Materia m = controller.buscarPorNombre("Matemáticas");
        System.out.println("Materia creada: " + (m.getNombre()));
        assertNotNull(m);
        assertEquals("Matemáticas", m.getNombre());
    }

    @Test
    void testListar() throws SQLException, ClassNotFoundException {
        System.out.println("=== Test Listar ===");
        controller.crear("Historia");
        controller.crear("Geografía");
        List<Materia> materias = controller.listar();
        System.out.println("Materias encontradas: " + materias.size());
        materias.forEach(mat -> System.out.println(" - " + mat.getNombre()));
        assertTrue(materias.size() >= 2);
    }

    @Test
    void testActualizar() throws SQLException, ClassNotFoundException {
        System.out.println("=== Test Actualizar ===");
        controller.crear("Biología");
        controller.crear("Matematicas");
        Materia m = controller.buscarPorNombre("Biología");
        System.out.println("Materia antes de actualizar: " + m.getNombre());
        controller.actualizar(m.getId(), "Ciencias Naturales");
        Materia actualizado = controller.buscarPorNombre("Ciencias Naturales");
        System.out.println("Materia después de actualizar: " + (actualizado.getNombre()));
        assertNotNull(actualizado);
        assertEquals("Ciencias Naturales", actualizado.getNombre());
    }

    @Test
    void testEliminar() throws SQLException, ClassNotFoundException {
        System.out.println("=== Test Eliminar ===");
        controller.crear("Física");
        Materia m = controller.buscarPorNombre("Física");
        System.out.println("Materia antes de eliminar: " + (m.getNombre()));
        controller.eliminar(m.getId());
        Materia eliminado = controller.buscarPorNombre("Física");
        System.out.println("Materia después de eliminar: " + (eliminado != null ? eliminado.getNombre() : "null"));
        assertNull(eliminado);
    }
}