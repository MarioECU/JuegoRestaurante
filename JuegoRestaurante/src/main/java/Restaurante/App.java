package Restaurante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import model.Producto;
import model.Usuario;
import model.Nivel;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static boolean salir;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainMenu"));
        salir = false;
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> salir = true);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Scene getScene() {
        return scene;
    }
    
    public static boolean getCloseRequest(){
        return salir;
    }
    
    public static void main(String[] args) {
        Producto.cargarProductos();
        Usuario.cargarUsuarios();
        Nivel.iniciarNiveles();
        System.out.println(Usuario.usuarios);
        launch();
        Usuario.actualizarUsuarios();
    }
}