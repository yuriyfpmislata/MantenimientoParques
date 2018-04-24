package vista.parques;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.ConnInyectable;

public class EditarParquesController implements Initializable, ConnInyectable {

    @FXML
    private TextField tfId;
    private TextField tfNumeroTotal;
    @FXML
    private Button btPrimero;
    @FXML
    private Button btAnterior;
    @FXML
    private Button btSiguiente;
    @FXML
    private Button btUltimo;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfIdComunidad;
    @FXML
    private TextField tfExtension;

    private ResultSet parques;
    private Integer numeroTotal;
    @FXML
    private Button btGuardar;

    private Connection conn;

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM parque");
            parques = ps.executeQuery();

            rellenarNumeroTotal();
            rellenarCampos();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta inicial de InsertController");
        }
    }

    @FXML
    private void clickBtPrimero(ActionEvent event) {
        try {
            parques.first();
            rellenarCampos();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void clickBtAnterior(ActionEvent event) {

        try {
            // se puede ir a anterior si no se esta en el row#1
            if (parques.getRow() > 1) {
                parques.previous();
            } else {
                parques.last();
            }
            rellenarCampos();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void clickbtSiguiente(ActionEvent event) {
        try {
            // se puede ir a siguiente si no se esta en el ultimo row
            if (parques.getRow() != numeroTotal) {
                parques.next();
            } else {
                parques.first();
            }
            rellenarCampos();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void clickbtUltimo(ActionEvent event) {
        try {
            parques.last();
            rellenarCampos();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void rellenarCampos() {
        try {
            Integer id = parques.getInt("id");
            String nombre = parques.getString("nombre");
            Double extension = parques.getDouble("extension");
            Integer idComunidad = parques.getInt("idComunidad");

            tfId.setText(id.toString());
            tfNombre.setText(nombre);
            tfExtension.setText(extension.toString());
            tfIdComunidad.setText(idComunidad.toString());
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private void rellenarNumeroTotal() {
        try {
            // sacar el numero de filas pasando a la ultima y sacando su indice
            parques.last();

            Integer n = parques.getRow();
            numeroTotal = n;

            // dejar el ResultSet apuntando a la primera fila
            parques.first();
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    @FXML
    private void clickBtGuardar(ActionEvent event) {
        Integer id = Integer.parseInt(tfId.getText());
        String nombre = tfNombre.getText();
        Double extension = Double.parseDouble(tfExtension.getText());
        Integer idComunidad = Integer.parseInt(tfIdComunidad.getText());

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE parque "
                    + "SET "
                    + "nombre = ?, "
                    + "extension = ?, "
                    + "idComunidad = ? "
                    + "WHERE id = ?;"
            );

            ps.setString(1, nombre);
            ps.setDouble(2, extension);
            ps.setInt(3, idComunidad);
            ps.setInt(4, id);

            ps.executeUpdate();

            // actualizar interfaz (rehaciendo el SELECT)
            this.initialize(null, null);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Registro");
            alert.setHeaderText("Registro editado ");

            alert.show();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Error");
            alert.setHeaderText("Error al editar el registro");

            System.out.println(ex.getErrorCode());

            alert.setContentText("Codigo desconocido [" + ex.getErrorCode() + "]" + "\n" + ex);

            alert.show();
        }
    }

}
