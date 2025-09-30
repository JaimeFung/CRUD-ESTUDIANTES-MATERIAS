/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ulatina.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import ulatina.data.ServicioMateria;
import ulatina.model.Materia;


public class MateriasController implements Serializable{
   private ServicioMateria servicio;

    public MateriasController() {
        servicio = new ServicioMateria();
    }

    public List<Materia> listar() throws SQLException, ClassNotFoundException {
        return servicio.obtenerMaterias();
    }

    public void crear(String nombre) throws SQLException, ClassNotFoundException {
        Materia m = new Materia();
        m.setNombre(nombre);
        servicio.insertarMateria(m);
    }

    public void actualizar(int id, String nombre) throws SQLException, ClassNotFoundException {
        Materia m = new Materia();
        m.setId(id);
        m.setNombre(nombre);
        servicio.actualizarMateria(m);
    }

    public void eliminar(int id) throws SQLException, ClassNotFoundException {
        Materia m = new Materia();
        m.setId(id);
        servicio.eliminarMateria(m);
    }

    public Materia buscarPorNombre(String nombre) throws SQLException, ClassNotFoundException {
        return servicio.validarMateria(nombre);
    }
}