package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Cliente implements Serializable{
    
    private String nombre;
    private int paciencia;
    private Orden orden;
    
    private static final long serialVersionUID = 1L;
    
    /**
    Constructor
     * @param nombre
    */
    public Cliente(String nombre) {
        this.nombre = nombre;
       paciencia = generarPaciencia();
    }

    public int getPaciencia() {
        return paciencia;
    }

    public Orden getOrden() {
        return orden;
    }
    
    private int generarPaciencia(){
        Random r = new Random();
        int valor= r.nextInt((6 -3)+1)+3;
        return valor;
    }
    
    public void disminuirPaciencia(){
        paciencia--;
    }
    
    /**
    Método que se encarga de generar una orden 
     * @param productosDisponibles
    */
    public void generarOrden(ArrayList<Producto> productosDisponibles){
        ArrayList<Producto> itemsProductos = new ArrayList<>();
        Random nume= new Random();
        int numero= nume.nextInt(4)+1;
        for (int i=0;i<numero;i++){
            int a = nume.nextInt(productosDisponibles.size());
            Producto prod= productosDisponibles.get(a);
            itemsProductos.add(prod);
        }
        orden= new Orden(itemsProductos);
    }
    
}
