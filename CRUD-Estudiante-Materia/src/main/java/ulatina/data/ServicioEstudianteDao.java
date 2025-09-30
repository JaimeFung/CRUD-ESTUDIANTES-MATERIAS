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
import ulatina.model.Estudiante;
import ulatina.model.Materia;

/**
 *
 * @author Esteban
 */
public class ServicioEstudianteDao extends Servicio implements Dao <Estudiante> {

    @Override
    public List<Estudiante> getAll() {
        try {
            return obtenerEstudiantes();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Estudiante t) {
        try {
            insertarEstudiante(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Estudiante t) {
        try {
            actualizarEstudiante(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Estudiante t) {
        try {
            eliminarEstudiante(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void insertarEstudiante(Estudiante estudiante) throws ClassNotFoundException, SQLException{
        
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "INSERT INTO estudiante (nombre, correo) VALUES (?, ?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getCorreo());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrarConexion();
            cerrarPreparedStatement(pstmt);
        }
    }
    
    private void actualizarEstudiante(Estudiante estudiante) throws ClassNotFoundException, SQLException{
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "UPDATE estudiante SET nombre = ?, correo = ? WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getCorreo());
            pstmt.setInt(3, estudiante.getId());
            pstmt.executeUpdate();
         
        } finally {
            cerrarConexion();
            cerrarPreparedStatement(pstmt);
        }
    }
    
    private void eliminarEstudiante(Estudiante estudiante) throws ClassNotFoundException, SQLException{
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "DELETE FROM estudiante WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, estudiante.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrarConexion();
            cerrarPreparedStatement(pstmt);
        }
    }
    
    private List<Estudiante> obtenerEstudiantes() throws ClassNotFoundException, SQLException{
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
      
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, correo FROM estudiante";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setCorreo(rs.getString("correo"));
                listaEstudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarResultSet(rs);
            cerrarConexion();
        }

        
        return listaEstudiantes;
    }
    
    public Estudiante validarEstudiante(String correo) {
        Estudiante estudiante = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, correo FROM estudiante WHERE correo = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, correo);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setCorreo(rs.getString("correo"));

            }

        } catch (Exception e) {
        } finally {

            cerrarPreparedStatement(pstmt);
            cerrarResultSet(rs);
            cerrarConexion();

        }
        return estudiante;

    }
    
    public List<Materia> obtenerMateriasEstudiante(int id) throws ClassNotFoundException, SQLException{
        List<Materia> listaMaterias = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            super.conectarBD();
            String sql = "SELECT M.id, M.nombre "
                        + "FROM materia M "
                        + "INNER JOIN estudiante_materia as EM ON M.id = EM.materia_id "
                        + "INNER JOIN estudiante as E ON E.id = EM.estudiante_id "
                        + "WHERE E.id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, id);
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
    
    public void matricularEstudiante(int idEstudiante, int idMateria) throws ClassNotFoundException, SQLException{
        
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "INSERT INTO estudiante_materia (estudiante_id, materia_id) VALUES (?, ?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, idEstudiante);
            pstmt.setInt(2, idMateria);
            pstmt.executeUpdate();
            
        
        } finally {
            cerrarConexion();
            cerrarPreparedStatement(pstmt);
        }
    }
     
    public void insertarEstudianteYMateria(Estudiante estudiante, Materia materia) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmtEst = null;
        PreparedStatement pstmtMat = null;
        PreparedStatement pstmtRel = null;

        try {
            super.conectarBD();

            getConexion().setAutoCommit(false);

            
            String sqlEst = "INSERT INTO estudiante (nombre, correo) VALUES (?, ?)";
            pstmtEst = getConexion().prepareStatement(sqlEst, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmtEst.setString(1, estudiante.getNombre());
            pstmtEst.setString(2, estudiante.getCorreo());
            pstmtEst.executeUpdate();


            ResultSet rsEst = pstmtEst.getGeneratedKeys();
            if (rsEst.next()) {
                estudiante.setId(rsEst.getInt(1));
            }
            cerrarResultSet(rsEst);

            
            
            String sqlMat = "INSERT INTO materia (nombre) VALUES (?)";
            pstmtMat = getConexion().prepareStatement(sqlMat, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmtMat.setString(1, materia.getNombre());
            pstmtMat.executeUpdate();


            ResultSet rsMat = pstmtMat.getGeneratedKeys();
            if (rsMat.next()) {
                materia.setId(rsMat.getInt(1));
            }
            cerrarResultSet(rsMat);


            String sqlRel = "INSERT INTO estudiante_materia (estudiante_id, materia_id) VALUES (?, ?)";
            pstmtRel = getConexion().prepareStatement(sqlRel);
            pstmtRel.setInt(1, estudiante.getId());
            pstmtRel.setInt(2, materia.getId());
            pstmtRel.executeUpdate();


            getConexion().commit();
            System.out.println("Estudiante, materia y relacion insertados correctamente.");

        } catch (SQLException e) {
            if (getConexion() != null) {
                getConexion().rollback(); 
                System.out.println("Error, se hizo rollback");
            }
            throw e;
        } finally {
            cerrarPreparedStatement(pstmtEst);
            cerrarPreparedStatement(pstmtMat);
            cerrarPreparedStatement(pstmtRel);
            if (getConexion() != null) {
                getConexion().setAutoCommit(true);
            }
            cerrarConexion();
        }
    }
    
}
