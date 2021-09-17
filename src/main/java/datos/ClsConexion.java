
package datos;

import java.sql.*;

/**
 *
 * @author PC
 */
public class ClsConexion {
    
     //creamos constantes
     private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbempleados?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
     private static final String JDBC_USER = "root";
     private static final String JDBC_PWD = "Jesus21%";
     
     //crear metodo conexion y nos devuelve un connection
     public static Connection getConnection() throws SQLException{//throws si hay problema que tire el sqlexception
         return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PWD);//returnamos la conexion
         
     }
         
     //cerrar conexion
     public static void close(ResultSet rs){
         try {//lo colocamos dentro de un trycatch
             rs.close();
         } catch (SQLException ex) {
            ex.printStackTrace(System.out);
         }
         
     }
      public static void close(PreparedStatement stmt){
         try {
             stmt.close();
         } catch (SQLException ex) {
            ex.printStackTrace(System.out);
         }
         
     }
       public static void close(Connection cn){
         try {
             cn.close();
         } catch (SQLException ex) {
            ex.printStackTrace(System.out);
         }
         
     }
     
     
     
}
