package objetos;

import java.io.Serializable;
import java.util.ArrayList;

public class Orden implements Serializable {

    private double valor ;
    private ArrayList<Producto> itemsProductos;
    
    private static final long serialVersionUID = 1L;

    
    /**
    Constructor
    */
    public Orden(ArrayList<Producto> itemsProductos) {
        this.itemsProductos = itemsProductos;
        determinarValor();
    }
    
    // Métodos getters y setters 
    public double getValor() {
        return valor;
    }

    public ArrayList<Producto> getItemsProductos() {
        return itemsProductos;
    }

    public void determinarValor() {
        for(Producto p:itemsProductos){
            valor += p.getPrecio();
        }
    }
    
}
