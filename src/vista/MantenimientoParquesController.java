package vista;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.ConnInyectable;

public class MantenimientoParquesController implements Initializable {

    @FXML
    private Label labelParques;
    @FXML
    private Button btListarComunidades;
    @FXML
    private Label labelComunidades;
    @FXML
    private Button btListarParques;
    @FXML
    private Button btInsertarParques;
    @FXML
    private Button btEditarParques;
    @FXML
    private Button btEliminarParques;
    @FXML
    private Button btInsertarComunidades;
    @FXML
    private Button btEditarComunidades;
    @FXML
    private Button btEliminarComunidades;

    private Connection conn;

    private final String URL_SERVIDOR = "jdbc:mysql://localhost:3306/";
    private final String USUARIO = "root";
    private final String CONTRASEÑA = "root";
    private final String NOMBRE_BD = "parques";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = DriverManager.getConnection(URL_SERVIDOR + NOMBRE_BD, USUARIO, CONTRASEÑA);
        } catch (SQLException ex) {
            System.err.println("Error al conectar. \n" + ex);
        }
    }

    private void cargarVentana_inyectarConexionBD(String url, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));

        Stage escenario = new Stage();
        escenario.setTitle(titulo);
        escenario.initModality(Modality.APPLICATION_MODAL);
        escenario.setScene(new Scene(loader.load()));
        escenario.show();
        
        ConnInyectable controlador = loader.getController();
        controlador.setConn(conn);
        controlador.consultaInicial();
    }

    @FXML
    private void clickBtListarComunidades(ActionEvent event) {
        try {
            this.cargarVentana_inyectarConexionBD("comunidades/ListarComunidades.fxml", "Listar Comunidades");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML");
        }
    }

    @FXML
    private void clickBtListarParques(ActionEvent event) {
        try {
            this.cargarVentana_inyectarConexionBD("parques/ListarParques.fxml", "Listar Parques");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtInsertarParques(ActionEvent event) {
        try {
            this.cargarVentana_inyectarConexionBD("parques/InsertarParques.fxml", "Insertar Parques");
        } catch (Exception e) {
            System.out.println("Fallo al cargar el FXML");
        }
    }

    @FXML
    private void clickBtEditarParques(ActionEvent event) {
        try {
            this.cargarVentana_inyectarConexionBD("parques/EditarParques.fxml", "Editar Parques");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtEliminarParques(ActionEvent event) {
        try {
            this.cargarVentana_inyectarConexionBD("parques/EliminarParques.fxml", "Eliminar Parques");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtInsertarComunidades(ActionEvent event) {
        try {
            this.cargarVentana_inyectarConexionBD("comunidades/InsertarComunidades.fxml", "Insertar Comunidades");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtEditarComunidades(ActionEvent event) {
        try {
            this.cargarVentana_inyectarConexionBD("comunidades/EditarComunidades.fxml", "Editar Comunidades");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtEliminarComunidades(ActionEvent event) {
        try {
            this.cargarVentana_inyectarConexionBD("comunidades/EliminarComunidades.fxml", "Eliminar Comunidades");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }
}
