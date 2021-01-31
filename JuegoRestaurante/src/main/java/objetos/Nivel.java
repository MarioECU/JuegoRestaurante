package objetos;

import java.io.Serializable;
import java.util.ArrayList;

public class Nivel implements Serializable{

    private int numeroNivel;
    private int minimoClientes;
    private double minimoDinero;
    public static ArrayList<Nivel> niveles;
    
    private static final long serialVersionUID = 1L;
    
    /**
    Constructor
     * @param numeroNivel
     * @param minimoClientes
     * @param minimoDinero
    */
    public Nivel(int numeroNivel, int minimoClientes, double minimoDinero) {
        this.numeroNivel = numeroNivel;
        this.minimoClientes = minimoClientes;
        this.minimoDinero = minimoDinero;
    }
    
    
    /**
    Método para iniciar los niveles al ArrayList 
    */
    public static void iniciarNiveles(){
        niveles = new ArrayList<>();
        niveles.add(new Nivel(1, 3, 50));
        niveles.add(new Nivel(2, 5, 75));
        niveles.add(new Nivel(3, 8, 100));
        niveles.add(new Nivel(4, 11, 125));
        niveles.add(new Nivel(5, 14, 150));
    }

    public double getMinimoDinero() {
        return minimoDinero;
    }

    public int getMinimoClientes() {
        return minimoClientes;
    }
    
}
