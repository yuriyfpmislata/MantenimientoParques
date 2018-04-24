package vista;

import java.io.IOException;
import java.net.URL;
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
import modelo.ConexionBD;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConexionBD.conectar();
    }

    private void cargarVentana(String url, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));

        Stage escenario = new Stage();
        escenario.setTitle(titulo);
        escenario.initModality(Modality.APPLICATION_MODAL);
        escenario.setScene(new Scene(loader.load()));
        escenario.showAndWait();
    }

    @FXML
    private void clickBtListarComunidades(ActionEvent event) {
        try {
            this.cargarVentana("comunidades/ListarComunidades.fxml", "Listar Comunidades");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML");
        }
    }

    @FXML
    private void clickBtListarParques(ActionEvent event) {
        try {
            this.cargarVentana("parques/ListarParques.fxml", "Listar Parques");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtInsertarParques(ActionEvent event) {
        try {
            this.cargarVentana("parques/InsertarParques.fxml", "Insertar Parques");
        } catch (Exception e) {
            System.out.println("Fallo al cargar el FXML");
        }
    }

    @FXML
    private void clickBtEditarParques(ActionEvent event) {
        try {
            this.cargarVentana("parques/EditarParques.fxml", "Editar Parques");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtEliminarParques(ActionEvent event) {
        try {
            this.cargarVentana("parques/EliminarParques.fxml", "Eliminar Parques");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtInsertarComunidades(ActionEvent event) {
        try {
            this.cargarVentana("comunidades/InsertarComunidades.fxml", "Insertar Comunidades");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtEditarComunidades(ActionEvent event) {
        try {
            this.cargarVentana("comunidades/EditarComunidades.fxml", "Editar Comunidades");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }

    @FXML
    private void clickBtEliminarComunidades(ActionEvent event) {
        try {
            this.cargarVentana("comunidades/EliminarComunidades.fxml", "Eliminar Comunidades");
        } catch (IOException e) {
            System.out.println("Fallo al cargar el FXML -> " + e);
        }
    }
}
