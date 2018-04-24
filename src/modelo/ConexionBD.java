package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static Connection conn;
    private final static String URL_SERVIDOR = "jdbc:mysql://localhost:3306/";
    private final static String USUARIO = "root";
    private final static String CONTRASEÑA = "root";
    private final static String NOMBRE_BD = "parques";

    public static void conectar() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL_SERVIDOR + NOMBRE_BD, USUARIO, CONTRASEÑA);
            } catch (SQLException ex) {
                System.err.println("Error al conectar. \n" + ex);
            }
        } else {
            System.err.println("Intentado conectar habiendo ya una conexión activa.");
        }
    }

    public static void desconectar() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println("Error al desconectar. \n" + ex);
            }
        } else {
            System.err.println("Intentado desconectar sin haber una conexión activa.");
        }
    }
}
