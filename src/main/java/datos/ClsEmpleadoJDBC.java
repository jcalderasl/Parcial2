
package datos;

import dominio.*;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class ClsEmpleadoJDBC {
    //creamos las constatantes
    private static final String SQL_SELECT = "select * from tb_empleados";
    private static final String SQL_UPDATE = "update tb_empleados set nombre=?,apellido=?,enero=?,febrero=?, marzo=? where id=?";
    private static final String SQL_INSERT = "insert into tb_empleados(nombre,apellido,enero,febrero,marzo) values(?,?,?,?,?)";
    private static final String SQL_DELETE = "delete from tb_empleados where id=?";
   
    
    public List<ClsEmpleado> seleccion(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsEmpleado empleado = null;
        List <ClsEmpleado> empleados = new ArrayList<ClsEmpleado>();
        
        try {
            conn = ClsConexion.getConnection();//hacemos la conexion
            stmt = conn.prepareStatement(SQL_SELECT);//y mandamos a llamar a la instrucion select
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int enero = rs.getInt("enero");
                int febrero = rs.getInt("febrero");
                int marzo = rs.getInt("marzo");
                
                empleado = new ClsEmpleado();
                empleado.setId(id);
                empleado.setNombre(nombre);
                empleado.setApellido(apellido);
                empleado.setEnero(enero);
                empleado.setFebrero(febrero);
                empleado.setMarzo(marzo);
                
                empleados.add(empleado);
                                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return empleados; 
    }
    
    public int Update(ClsEmpleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();//realizamos la conexion
            stmt = conn.prepareStatement(SQL_UPDATE);//y mandamos a llamar a la instrucion
            //le mandamos los parametros 
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setDouble(3, empleado.getEnero());
            stmt.setDouble(4, empleado.getFebrero());
            stmt.setDouble(5, empleado.getMarzo());
            stmt.setDouble(6, empleado.getId());
       
            
            System.out.println("ejecutando query:" + SQL_UPDATE);//ejecutamos el query 
            rows = stmt.executeUpdate();//eje el update
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
    
    public int insert(ClsEmpleado empleado){//inserta empleados, creamos la parte de insert y le mandamos como parametro empleado
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();//realizamos la conexion
            stmt = conn.prepareStatement(SQL_INSERT);//y mandamos a llamar a la instrucion
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setDouble(3, empleado.getEnero());
            stmt.setDouble(4, empleado.getFebrero());
            stmt.setDouble(5, empleado.getMarzo());
            
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
    

    public int deleteDatos(ClsEmpleado empleado){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();//realizamos la conexion
            stmt = conn.prepareStatement(SQL_DELETE);//llamamos a la instruccion
            stmt.setInt(1, empleado.getId());
            System.out.println("Dato eleminado exitosamente:" + SQL_DELETE);//ejecutamos el query con delete
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
   
public DefaultTableModel verInformacion(){
    String [] nomColumnas = {"id","nombre","Apellido","Enero","Febrero","Marzo"};
    String [] registros = new String[6];
        
    DefaultTableModel modelo = new DefaultTableModel(null,nomColumnas);
        
    Connection conn = null;
    PreparedStatement stmt = null;        
    ResultSet rs = null;
    
    try{
        conn = ClsConexion.getConnection();//realizamos la conexion
        stmt = conn.prepareStatement(SQL_SELECT); //mandamos a llamar a la instruccion                       
        rs = stmt.executeQuery();
        
        while(rs.next()){
            registros[0] = rs.getString("id");
            registros[1] = rs.getString("nombre");
            registros[2] = rs.getString("Apellido");
            registros[3] = rs.getString("Enero");
            registros[4] = rs.getString("Febrero");
            registros[5] = rs.getString("Marzo");
                
            modelo.addRow(registros);
                
            }
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null,"Error al conectar");  
        }
        finally{
            
        try{
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
         return modelo;
    }
}
       
       
  