
package datos;

import dominio.ClsUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author PC
 */
public class UsuarioJDBC {
    
    private static final String SQL_SELECT = "select * from usuario";
    private static final String SQL_UPDATE = "update usuario set username=?,password=? where id_usuario=?";
    private static final String SQL_INSERT = "insert into usuario(username,password) values(?,?)";
    private static final String SQL_DELETE = "delete from usuario where id_usuario=?";
    private static final String SQL_SELECT_VALIDACION = "select * from usuario where username=?"+"and password=?";
    
    
    
    public List<ClsUsuario> select(){
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsUsuario usuario = null;
        List <ClsUsuario> usuarios = new ArrayList<ClsUsuario>();
        
        try {
            conn = ClsConexion.getConnection();//hacemos la conexion
            stmt = conn.prepareStatement(SQL_SELECT);//y mandamos a llamar a la instrucion select
            rs = stmt.executeQuery();//cuando ejecute el query devuelve un tipo de dato rs
            
            while(rs.next()){
                int id_usuario = rs.getInt("Id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");
 
                usuario = new ClsUsuario();
                usuario.setId_usuario(id_usuario);
                usuario.setUsername(username);
                usuario.setPassword(password);
   
                usuarios.add(usuario);
                                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return usuarios; 
    }
    public boolean select_validacion(ClsUsuario datos){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsUsuario usuario = new ClsUsuario();
        boolean tiene_permiso = false;
        
        try {
            conn = ClsConexion.getConnection();//hacemos la conexion
            String condicion = SQL_SELECT + " where username='"+datos.getUsername()+"'"+" and password='"+datos.getPassword()+"'";
                     
            stmt = conn.prepareStatement(condicion);//y mandamos a llamar a la instrucion select
            rs = stmt.executeQuery();//cuando ejecute el query devuelve un tipo de dato rs
            while(rs.next()){
                tiene_permiso = true;
                int id_usuario = rs.getInt("Id_usuario");//de
                String username = rs.getString("username");
                String password = rs.getString("password");
 
                usuario = new ClsUsuario();
                usuario.setId_usuario(id_usuario);
                usuario.setUsername(username);
                usuario.setPassword(password);
   
                                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return tiene_permiso; 
    }
    
    
    
    public int insert(ClsUsuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();//realizamos la conexion
            stmt = conn.prepareStatement(SQL_INSERT);//y mandamos a llamar a la instrucion
            stmt.setString(1, usuario.getUsername());//le mandamos los parametros en el orden
            stmt.setString(2, usuario.getPassword());
       
            
            System.out.println("ejecutando query:" + SQL_INSERT);//ejecutamos el query con insert
            rows = stmt.executeUpdate();//eje el udate
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }
        
  
     public int Update(ClsUsuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();//realizamos la conexion
            stmt = conn.prepareStatement(SQL_UPDATE);//y mandamos a llamar a la instrucion
            stmt.setString(1, usuario.getUsername());//le mandamos los parametros en el orden
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getId_usuario());
       
            
            System.out.println("ejecutando query:" + SQL_UPDATE);//ejecutamos el query con insert
            rows = stmt.executeUpdate();//eje el udate
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }
     public int deleteDatos(ClsUsuario usuario){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();//realizamos la conexion
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getId_usuario());
            System.out.println("Dato eleminado exitosamente:" + SQL_DELETE);//ejecutamos el query con insert
            rows = stmt.executeUpdate();//eje el udate
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return rows;
    }
    
    
}
