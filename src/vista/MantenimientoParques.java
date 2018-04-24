package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MantenimientoParques extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MantenimientoParques.fxml"));
        Image icono = new Image(this.getClass().getResource("/images/pine_tree.png").toString());
        
        stage.getIcons().add(icono);
        stage.setTitle("Mantenimiento de Parques");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
