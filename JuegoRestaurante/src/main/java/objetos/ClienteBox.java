package objetos;

import Restaurante.GameController;
import administracion.Administracion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author William
 */
public class ClienteBox  {
    private VBox cajaVent;
    private Cliente cliente;
    public static String ruta = "src/main/resources/imagenes/";
    private Label pacien;
    private String tx = "Paciencia: ";

    public VBox getCajaVent() {
        return cajaVent;
    }
    private HBox hb;
    
        public ClienteBox(Cliente c){
        cajaVent = new VBox();
//        cajaVent.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,                                                        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        hb = new HBox();
        cliente = c;
        generarOrden();
        createvbox();
    }
 
    private void generarOrden(){
        ArrayList<Producto> a = new ArrayList<>();
        Administracion.disponibles.values().forEach((e) -> {
            e.forEach((p) -> {
                a.add(p);
            });
        });
        cliente.generarOrden(a);  
    }

    private void createvbox(){
        try {
            Random r = new Random();
            int numeroCliente = r.nextInt(8)+1;
            FileInputStream ip = new FileInputStream(ruta+"cliente"+numeroCliente+".png");
            Image m = new Image(ip,60,60,false,false);
            ImageView perfil = new ImageView(m);
            for(Producto p : cliente.getOrden().getItemsProductos()){
                ip = new FileInputStream(ruta + p.getNombreArchivo());
                m = new Image(ip,40,40,false,false);
                ImageView iv = new ImageView(m);
                hb.getChildren().add(iv);
            }
            Button bn = new Button("Servir");
            pacien = new Label(tx + String.valueOf(cliente.getPaciencia()));
            pacien.setTextFill(Color.WHITE);
            pacien.setStyle("-fx-font-weight: bold;");
            hb.setSpacing(10);
            hb.setAlignment(Pos.CENTER);
            cajaVent.getChildren().addAll(hb,perfil,pacien,bn);
            cajaVent.setSpacing(10);
            cajaVent.setAlignment(Pos.CENTER);
            
            // codigo de configuracion de boton
            bn.setOnAction(null);
//            () -> {
//                if(comprobarVenta(GameController.seleccionado)){
//                    Administracion.usuario.aumentarValor(cliente.getOrden().getValor());
//                }
//            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void actualizarPaciencia(){
        pacien.setText(tx + String.valueOf(cliente.getPaciencia()+1));
    }
    
    public boolean comprobarVenta(ArrayList<Producto> productos){
        ArrayList orden =cliente.getOrden().getItemsProductos();
        Collections.sort(productos);
        Collections.sort(orden);
        return orden.equals(productos);
    }
    
   
}
