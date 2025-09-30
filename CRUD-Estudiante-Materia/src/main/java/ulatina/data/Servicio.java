package ulatina.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;


public class Servicio {
   
   protected Connection conexion = null;
   private String host;
   private String puerto;
   private String dbname;
   private String usuario;
   private String clave;

   public Servicio(){
       cargarConfiguracion();
   }
   
   private void cargarConfiguracion(){
       Properties properties = new Properties();
       
       try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
           if(input == null){
               throw new RuntimeException("No se encontro el archivo db.properties");
           }
           
           properties.load(input);
           host = properties.getProperty("db.host");
           puerto = properties.getProperty("db.puerto");
           dbname = properties.getProperty("db.nombre");
           usuario = properties.getProperty("db.usuario");
           clave = properties.getProperty("db.clave");
       } 
       catch (IOException e){
           throw new RuntimeException("Error cargando la configuracion de la base de datos", e);
       }
       
       
   }
   
   public void conectarBD() throws ClassNotFoundException, SQLException {
       
       Class.forName("com.mysql.cj.jdbc.Driver");
       
       String url = "jdbc:mysql://" + host + ":" + puerto + "/" + dbname + "?serverTimezone=UTC";
       conexion = DriverManager.getConnection(url, usuario, clave);
   }
 
 
   public void cerrarPreparedStatement(PreparedStatement ps) {
       if (ps != null) {
           try {
               if (!ps.isClosed()) {
                   ps.close();
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }
 
 
   public void cerrarResultSet(ResultSet rs) {
       if (rs != null) {
           try {
               if (!rs.isClosed()) {
                   rs.close();
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }
 
  
   public void cerrarConexion() {
       if (conexion != null) {
           try {
               if (!conexion.isClosed()) {
                   conexion.close();
               }
               conexion = null;
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }
 
   protected Connection getConexion() {
       return conexion;
   }
 
  
   protected void setConexion(Connection conexion) {
       this.conexion = conexion;
   }
    
}
