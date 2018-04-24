package vista.comunidades;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static modelo.ConexionBD.conn;

public class InsertarComunidadesController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private Button btGuardar;

    @FXML
    private TextField tfId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void clickBtGuardar(ActionEvent event) {
        String consulta = "INSERT INTO comunidad "
                + "VALUES (?,?);";

        Integer id = Integer.parseInt(tfId.getText());
        String nombre = tfNombre.getText();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);

            ps.setInt(1, id);
            ps.setString(2, nombre);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Registro");
            alert.setHeaderText("Registro introducido ");

            alert.show();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Error");
            alert.setHeaderText("Error al introducir el registro");

            System.out.println(ex.getErrorCode());

            switch (ex.getErrorCode()) {
                case 1062:
                    alert.setContentText("Clave primaria duplicada");
                    break;
                case 1452:
                    alert.setContentText("No se ha encontrado el ID de comunidad. Imposible relacionar");
                    break;
                default:
                    alert.setContentText("Codigo desconocido [" + ex.getErrorCode() + "]" + "\n" + ex);
            }

            alert.show();
        }
    }
}
