package Restaurante;

import administracion.Administracion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import objetos.Usuario;

public class MainMenuController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContrasena;
    @FXML
    private Button btnJugar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnJugar.setOnAction(e -> validarUsuario(txtUsuario.getText(), txtContrasena.getText()));
    }    
    
    public void validarUsuario(String usuario, String contrasena){
        if (Usuario.usuarios.containsKey(usuario)){
            Usuario u = Usuario.usuarios.get(usuario);
            if (u.getContrasena().equals(contrasena)){
                Administracion.usuario = u;
                Administracion.nivel = u.getNiveles().get(u.getNiveles().size() -1);
                startPlaying();
            }
            else {
                mostrarAlerta("Contraseña incorrecta", AlertType.ERROR);
                txtContrasena.clear();
            }
        } else {
            Usuario.usuarios.put(txtUsuario.getText(), new Usuario(txtUsuario.getText(), txtContrasena.getText()));
            mostrarAlerta("Usuario creado\nAhora puedes iniciar sesión", AlertType.CONFIRMATION);
            txtContrasena.clear();
        }
    }
    
    private void startPlaying() {
        try {
            App.setRoot("Game");
            App.getScene().setOnKeyPressed((KeyEvent t) -> {
                if (t.getCode().toString().equals("ESCAPE")){
                    try {
                        new GameController().stopPlaying();
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                }                
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void mostrarAlerta(String mensaje, AlertType e){
        Alert alert = new Alert (e);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
