package ulatina.controller;


import java.io.Serializable;


import java.sql.SQLException;
import java.util.List;
import ulatina.data.ServicioEstudianteDao;
import ulatina.data.ServicioMateriaDao;
import ulatina.model.Estudiante;
import ulatina.model.Materia;
/**
 *
 * @author Jaime
 */

public class EstudianteController implements Serializable {

    private final ServicioEstudianteDao sDE = new ServicioEstudianteDao();
    private final ServicioMateriaDao sDM = new ServicioMateriaDao();
    

    public List<Estudiante> listar() throws SQLException, ClassNotFoundException {
        return sDE.getAll();
    }

    public void crear(String nombre, String correo) throws SQLException, ClassNotFoundException {
        Estudiante e = new Estudiante();
        e.setNombre(nombre);
        e.setCorreo(correo);
        if ((nombre == null || nombre.isEmpty()) || (correo == null || correo.isEmpty())) {
            System.out.println("No se puede ingresar datos vacios");  
        }else{
           sDE.save(e);
           System.out.println("Estudiante insertado.");
        }
    }
    
    public void actualizar(int id, String nombre, String correo) throws SQLException, ClassNotFoundException {
        Estudiante e = new Estudiante();
        e.setId(id);
        e.setNombre(nombre);
        e.setCorreo(correo);
        sDE.update(e);
    }
    
    public void eliminar(int id) throws SQLException, ClassNotFoundException {
        Estudiante e = new Estudiante();
        e.setId(id);
        sDE.delete(e);
    }

    public Estudiante buscarPorCorreo(String correo) throws SQLException, ClassNotFoundException {
        return sDE.validarEstudiante(correo);
    }
    
    public void matricularEstudiante(int idEstudiante, int idMateria) throws SQLException, ClassNotFoundException {
        sDE.matricularEstudiante(idEstudiante, idMateria);
    }

    public List<Estudiante> listarEstudiantes() throws SQLException, ClassNotFoundException {
        return sDE.getAll();
    }

    public List<Materia> listarMaterias() throws SQLException, ClassNotFoundException {
        return sDM.getAll();
    }

    public List<Materia> obtenerMateriasDelEstudiante(int idEstudiante) throws SQLException, ClassNotFoundException {
        return sDE.obtenerMateriasEstudiante(idEstudiante);
    }
    
    public void insertarEstudianteYMateria(Estudiante estudiante, Materia materia) throws Exception {
        sDE.insertarEstudianteYMateria(estudiante, materia);
    }

}

