package Restaurante;

import administracion.Administracion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import objetos.Cliente;
import objetos.ClienteBox;
import objetos.Nivel;
import objetos.Producto;
import objetos.Usuario;

public class GameController implements Initializable{
    
    @FXML
    private HBox hboxCategorias;
    @FXML
    private Label lblNivel;
    @FXML
    private VBox vboxPanelDerecho;
    @FXML
    private Label lblDinero;
    @FXML
    private HBox hboxClientes;
    
    private int fallos = 0;
    
    private boolean falla=false;
    
    private int clientesActivos=0;
    
    public static ArrayList<Producto> seleccionado = new ArrayList(); //listado que contieen productos actualemente seleccionados por el usuario

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lblNivel.setText(""+Administracion.usuario.getNiveles().size());
        this.lblDinero.setText(""+Administracion.usuario.getValorAcumulado());
        //Nivel.iniciarNiveles();
        cargarMenu();
        controlNivel();
    }
    
    public void stopPlaying() {
        try {
            App.setRoot("MainMenu");
            App.getScene().setOnKeyPressed(null);
            Administracion.usuario.addNivel(Integer.parseInt(this.lblNivel.getText()));
            Administracion.usuario.aumentarValor(Double.parseDouble(this.lblDinero.getText()));
            Usuario.usuarios.put(Administracion.usuario.getNombre(), Administracion.usuario);
            Usuario.actualizarUsuarios();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void generarCliente(){
        Cliente c = new Cliente("cliente");
        ClienteBox cl = new ClienteBox(c);
        hboxClientes.getChildren().add(cl.getCajaVent());
        Thread th = new Thread(() -> {
            Runnable updater = () -> {
                c.disminuirPaciencia();
                cl.actualizarPaciencia();
            };
            while(c.getPaciencia()>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                Platform.runLater(updater);
                
                if(c.getPaciencia()==0){
                    Platform.runLater(() -> {
                        hboxClientes.getChildren().remove(cl.getCajaVent());
                        clientesActivos--;
                    });
                }
            }
        });
        th.setDaemon(true);
        th.start();   
    }  
    
    private void controlNivel(){
        Thread th = new Thread(() -> {
            Runnable updater = () -> {
                generarCliente();
            };
            boolean c = true;
            while(c){
                Random r = new Random();
                int espera = r.nextInt(6); // int de esperar debe ser atributo de nivel
                try {
                    Thread.sleep(espera*1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                if(clientesActivos < Administracion.nivel.getMinimoClientes()){
                    clientesActivos++;
                    Platform.runLater(updater);
                }
                c = controlJuego();
                
                
            }
        });
        th.start();
    }
    
    private boolean controlJuego(){
        if(Administracion.usuario.getValorAcumulado()< Administracion.nivel.getMinimoDinero()){
            if(fallos<Administracion.fallos){
                return true;
            }
        }
        if(fallos>=Administracion.fallos){
                falla=false;
        }
        return false;
    }

    /**
     * Carga 3 categorías para cada nivel
    */
    public void cargarMenu() {
        Random r= new Random();
        Administracion.disponibles= new HashMap<>();
        ArrayList<String> categorias = new ArrayList<>();
        categorias.addAll(Producto.productos.keySet());
        categorias.remove(r.nextInt(categorias.size()));
        
        categorias.forEach((cat) -> {
            for (int i=0;i<3;i++){
                int n= r.nextInt(Producto.productos.get(cat).size());
                Administracion.disponibles.putIfAbsent(cat,new ArrayList<>());
                Administracion.disponibles.get(cat).add(Producto.productos.get(cat).get(n));
                Producto.productos.get(cat).remove(n);
            }
        });
        Administracion.disponibles.keySet().stream().map((s) -> {
            Label lbl= new Label(s.toUpperCase());
            VBox vb= new VBox();
            HBox hb= new HBox();
            Administracion.disponibles.get(s).forEach((p) -> {
                try(FileInputStream ip = new FileInputStream(ClienteBox.ruta + p.getNombreArchivo())) {
                    Image m = new Image(ip,40,40,false,false);
                    ImageView iv = new ImageView(m);
                    ImageView iv1 = new ImageView(m);
                    iv.setOnMouseClicked((MouseEvent e) -> {
                        try {
                            GameController.this.vboxPanelDerecho.getChildren().addAll(iv1);}
                        catch (IllegalArgumentException ile){
                            
                        }
                    });
                    hb.getChildren().add(iv);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            vb.getChildren().add(hb);
            vb.getChildren().add(lbl);
            return vb;
        }).forEachOrdered((vb) -> {
            this.hboxCategorias.getChildren().add(vb);
        });
    }

}

