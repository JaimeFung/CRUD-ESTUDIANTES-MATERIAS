/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ulatina.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ulatina.model.Materia;

/**
 *
 * @author Esteban
 */
public class ServicioMateriaDao extends Servicio implements Dao <Materia> {
    
    @Override
    public List<Materia> getAll() {
        try {
            return obtenerMaterias();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Materia t) {
        try {
            insertarMateria(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Materia t) {
        try {
            actualizarMateria(t);
        } catch (Exception e) {
        }
    }

    @Override
    public void delete(Materia t) {
        try {
            eliminarMateria(t);
        } catch (Exception e) {
        }
    }
    
    
    public Materia validarMateria(String nombre) {
        Materia materia = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = "SELECT id, nombre FROM materia WHERE nombre = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, nombre);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarResultSet(rs);
            cerrarConexion();
        }
        return materia;
    }
    
    private void insertarMateria(Materia materia) throws ClassNotFoundException, SQLException{
        
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "INSERT INTO materia (nombre) VALUES (?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, materia.getNombre());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrarConexion();
            cerrarPreparedStatement(pstmt);
        }
    }
    
    private void actualizarMateria(Materia materia) throws ClassNotFoundException, SQLException{
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "UPDATE materia SET nombre = ? WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, materia.getNombre());
            pstmt.setInt(2, materia.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrarConexion();
            cerrarPreparedStatement(pstmt);
        }
    }
    
    private void eliminarMateria(Materia materia) throws ClassNotFoundException, SQLException{
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "DELETE FROM materia WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, materia.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrarConexion();
            cerrarPreparedStatement(pstmt);
        }
    }
    
    private List<Materia> obtenerMaterias() throws ClassNotFoundException, SQLException{
        List<Materia> listaMaterias = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre FROM materia";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Materia materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
                listaMaterias.add(materia);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarResultSet(rs);
            cerrarConexion();
        }
        
        return listaMaterias;
    }
    
}
