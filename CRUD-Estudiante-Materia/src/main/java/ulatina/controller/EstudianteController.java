package ulatina.controller;


import java.io.Serializable;


import java.sql.SQLException;
import java.util.List;
import ulatina.data.ServicioEstudiante;
import ulatina.data.ServicioMateria;
import ulatina.model.Estudiante;
import ulatina.model.Materia;
/**
 *
 * @author Jaime
 */

public class EstudianteController implements Serializable {

    private ServicioEstudiante servicioEstudiante;
    private ServicioMateria servicioMateria;
    
    public EstudianteController() {
        servicioEstudiante = new ServicioEstudiante();
        servicioMateria = new ServicioMateria();
    }

    public List<Estudiante> listar() throws SQLException, ClassNotFoundException {
        return servicioEstudiante.obtenerEstudiantes();
    }

    public void crear(String nombre, String correo) throws SQLException, ClassNotFoundException {
        Estudiante e = new Estudiante();
        e.setNombre(nombre);
        e.setCorreo(correo);
        if ((nombre == null || nombre.isEmpty()) || (correo == null || correo.isEmpty())) {
            System.out.println("No se puede ingresar datos vacios");  
        }else{
           servicioEstudiante.insertarEstudiante(e);
           System.out.println("Estudiante insertado.");
        }
    }
    
    public void actualizar(int id, String nombre, String correo) throws SQLException, ClassNotFoundException {
        Estudiante e = new Estudiante();
        e.setId(id);
        e.setNombre(nombre);
        e.setCorreo(correo);
        if ((nombre == null || nombre.isEmpty()) || (correo == null || correo.isEmpty())) {
             System.out.println("No se puede ingresar datos vacios");}
        else{
           servicioEstudiante.actualizarEstudiante(e); 
           System.out.println("Estudiante actualizado.");
        }
    }
    public void eliminar(int id) throws SQLException, ClassNotFoundException {
        Estudiante e = new Estudiante();
        e.setId(id);
        servicioEstudiante.eliminarEstudiante(e);
    }

    public Estudiante buscarPorCorreo(String correo) throws SQLException, ClassNotFoundException {
        return servicioEstudiante.validarEstudiante(correo);
    }
    
    public void matricularEstudiante(int idEstudiante, int idMateria) throws SQLException, ClassNotFoundException {
        servicioEstudiante.matricularEstudiante(idEstudiante, idMateria);
    }

    public List<Estudiante> listarEstudiantes() throws SQLException, ClassNotFoundException {
        return servicioEstudiante.obtenerEstudiantes();
    }

    public List<Materia> listarMaterias() throws SQLException, ClassNotFoundException {
        return servicioMateria.obtenerMaterias();
    }

    public List<Materia> obtenerMateriasDelEstudiante(int idEstudiante) throws SQLException, ClassNotFoundException {
        return servicioEstudiante.obtenerMateriasEstudiante(idEstudiante);
    }
    
    public void insertarEstudianteYMateria(Estudiante estudiante, Materia materia) throws Exception {
        servicioEstudiante.insertarEstudianteYMateria(estudiante, materia);
    }

}
