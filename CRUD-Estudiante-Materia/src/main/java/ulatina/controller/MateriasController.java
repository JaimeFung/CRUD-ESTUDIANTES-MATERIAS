/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ulatina.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import ulatina.data.ServicioMateriaDao;
import ulatina.model.Materia;


public class MateriasController implements Serializable{
   private final ServicioMateriaDao sD = new ServicioMateriaDao();


    public List<Materia> listar() throws SQLException, ClassNotFoundException {
        return sD.getAll();
    }

    public void crear(String nombre) throws SQLException, ClassNotFoundException {
        Materia m = new Materia();
        m.setNombre(nombre);
        sD.save(m);
    }

    public void actualizar(int id, String nombre) throws SQLException, ClassNotFoundException {
        Materia m = new Materia();
        m.setId(id);
        m.setNombre(nombre);
        sD.update(m);
    }

    public void eliminar(int id) throws SQLException, ClassNotFoundException {
        Materia m = new Materia();
        m.setId(id);
        sD.delete(m);
    }

    public Materia buscarPorNombre(String nombre) throws SQLException, ClassNotFoundException {
        return sD.validarMateria(nombre);
    }
   
    public void eliminarTodasMaterias() throws SQLException, ClassNotFoundException {
        
        sD.eliminarTodasMaterias();
        
    }

}
