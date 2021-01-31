package Restaurante;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import objetos.Producto;
import static objetos.Producto.cargarProductos;
import objetos.Usuario;
import static objetos.Usuario.cargarUsuarios;
import java.util.HashMap;
import objetos.Nivel;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainMenu"));
        stage.setScene(scene);
        stage.show();
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
    
    public static void main(String[] args) {
        Producto.cargarProductos();
        Usuario.cargarUsuarios();
        Nivel.iniciarNiveles();
        System.out.println(Usuario.usuarios);
        launch();
    }

    
}