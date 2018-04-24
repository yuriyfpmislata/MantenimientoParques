package vista.parques;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static modelo.ConexionBD.conn;

public class InsertarParquesController implements Initializable {

    @FXML
    private TextField tfIdParque;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfExtension;
    @FXML
    private Button btGuardar;
    @FXML
    private ComboBox<String> comboIdComunidad;

    private Map<String, Integer> comunidades = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM comunidad");
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                comunidades.put(res.getString("nombre"), res.getInt("id"));
            }

            comboIdComunidad.setItems(FXCollections.observableArrayList(comunidades.keySet()).sorted());
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta inicial de InsertController");
        }

    }

    @FXML
    private void clickBtGuardar(ActionEvent event) {
        String consulta = "INSERT INTO parque "
                + "VALUES (?,?,?,?);";

        Integer idComunidad = comunidades.get(comboIdComunidad.getValue());

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);

            ps.setInt(1, Integer.parseInt(tfIdParque.getText()));
            ps.setString(2, tfNombre.getText());
            ps.setDouble(3, Double.parseDouble(tfExtension.getText()));
            ps.setInt(4, idComunidad);

            System.out.println(ps.executeUpdate() + " filas actualizadas.");

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
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Error");
            alert.setContentText("Error al introducir el registro." + "\n" + ex);

            alert.show();
        }
    }
}
