package vista.comunidades;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Comunidad;
import modelo.ConnInyectable;

public class ListadoComunidadesController implements Initializable, ConnInyectable {

    private Set<Comunidad> comunidades = new HashSet<>();

    private Connection conn;
    @FXML
    private TableView<Comunidad> tvListado;
    @FXML
    private TableColumn<Comunidad, Integer> id;
    @FXML
    private TableColumn<Comunidad, String> nombre;

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void consultaInicial() {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM comunidad");
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                comunidades.add(new Comunidad(resultado.getInt("id"), resultado.getString("nombre")));
            }

            tvListado.setItems(FXCollections.observableArrayList(comunidades));

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta inicial");
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
