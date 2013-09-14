package bd;

/**
 *
 * @author Rubens
 */
import java.sql.*;

public class OracleBD{

    private Connection conexion;

    public OracleBD abrir() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@192.168.1.3:1521:XE";

            conexion = DriverManager.getConnection(BaseDeDatos, "agencia", "sistemas");
            if (conexion != null) {
                System.out.println("Conexion exitosa!");
            } else {
                System.out.println("Conexion fallida!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
     public void cerrar()
    {
        this.conexion = null;
    }

    public boolean ejecutar(String sql) {
        try {
            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql);
            getConexion().commit();
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ResultSet consultar(String sql) {
        ResultSet resultado = null;
        try {
            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
            getConexion().commit();
        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }
    
    public Connection getConexion() {
        return conexion;
    }
    
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
}