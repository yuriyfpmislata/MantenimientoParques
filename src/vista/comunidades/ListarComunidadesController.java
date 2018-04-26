package vista.comunidades;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import modelo.ConnInyectable;

public class ListarComunidadesController implements Initializable, ConnInyectable {

    @FXML
    private Label lbId;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNumeroTotal;
    @FXML
    private Separator separator;
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

    private ResultSet comunidades;
    private Integer numeroTotal;

    private Connection conn;

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void consultaInicial() {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM comunidad");
            comunidades = ps.executeQuery();

            rellenarNumeroTotal();
            rellenarCampos();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta inicial");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void clickBtPrimero(ActionEvent event) {
        try {
            comunidades.first();
            rellenarCampos();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void clickBtAnterior(ActionEvent event) {

        try {
            // se puede ir a anterior si no se esta en el row#1
            if (comunidades.getRow() > 1) {
                comunidades.previous();
            } else {
                comunidades.last();
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
            if (comunidades.getRow() != numeroTotal) {
                comunidades.next();
            } else {
                comunidades.first();
            }
            rellenarCampos();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void clickbtUltimo(ActionEvent event) {
        try {
            comunidades.last();
            rellenarCampos();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void rellenarCampos() {
        try {
            Integer id = comunidades.getInt("id");
            String nombre = comunidades.getString("nombre");

            tfId.setText(id.toString());
            tfNombre.setText(nombre);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private void rellenarNumeroTotal() {
        try {
            // sacar el numero de filas pasando a la ultima y sacando su indice
            comunidades.last();

            Integer n = comunidades.getRow();

            numeroTotal = n;

            // dejar el ResultSet apuntando a la primera fila
            comunidades.first();
        } catch (SQLException e) {
            //
        }

    }

    @FXML
    private void actionTfNombre(ActionEvent event) {
    }

    @FXML
    private void actionTfId(ActionEvent event) {
    }
}
