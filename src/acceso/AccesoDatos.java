package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manugallegomanuel88
 */
public class AccesoDatos {
    Connection conexion = null;
    /**
     * Hay que darle de valor la direccion de nuestro host.
     */
    String host;
    /**
     * Hay que darle de valor el nombre de la BD.
     */
    String baseDatos;
    /**
     * Hay que darle de valor un nombre de usuario de la BD.
     */
    String user;
    /**
     * Haay que darle de valor el passwor del usuario.
     */
    String pass;

    /**
     * Hay que introducir los parametros que vamos necesarios para hacer la conexión con la base de datos.
     * @param host Hay que darle de valor la direccion de nuestro host.
     * @param baseDatos  Hay que darle de valor el nombre de la BD.
     * @param user Hay que darle de valor un nombre de usuario de la BD.
     * @param pass  Haay que darle de valor el passwor del usuario.
     */
    public AccesoDatos(String host, String baseDatos, String user, String pass) {
        this.host = host;
        this.baseDatos = baseDatos;
        this.user = user;
        this.pass = pass;
    }

    /**
     * Crea una consulta para hacer un delete con los parametros que se le introduce.
     * @param deleteFrom Nombre de la tabla en la BD donde queremos hacer el delete.
     * @param condicion Campo por el que buscamos para borrar una fila. Preferible que sea una primary key.
     * @param vCondicion Valor que le daremos a "condicion".
     */
    public void delete(String deleteFrom, String condicion, String vCondicion){
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://"+host+"/"+baseDatos, user, pass);
            String query = ("delete from "+deleteFrom+" where "+condicion+"='"+vCondicion+"'");
            System.out.println(query);
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.execute();
            System.out.println("Borrado realizado");
        } catch (SQLException ex) {
            System.out.println("Error de borrado: " + ex.getMessage());
        }
    }
    
    public void select (JTable tabla1, JTextField etiqueta1){
        DefaultTableModel modelo = (DefaultTableModel) tabla1.getModel();
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://"+host+"/"+baseDatos, user, pass);
            Statement s = conexion.createStatement();
            System.out.println("select titulo, anho, duracion from peliculas where titulo like '%"+etiqueta1.getText()+"%'");
            ResultSet consulta = s.executeQuery("select titulo, anho, duracion from peliculas where titulo like '%"+etiqueta1.getText()+"%'");
            while (consulta.next()) {
                Object [] filaTabla = new Object [5];
                filaTabla [0] = consulta.getString(1);
                filaTabla [1] = consulta.getString(2);
                filaTabla [2] = consulta.getString(3);
                modelo.addRow(filaTabla);
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }   
}
