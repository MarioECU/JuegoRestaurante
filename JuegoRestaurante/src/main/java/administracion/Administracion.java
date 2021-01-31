package administracion;

import java.util.ArrayList;
import java.util.HashMap;
import objetos.Nivel;
import objetos.Producto;
import objetos.Usuario;

public class Administracion {
    
    public static Usuario usuario; //objeto clase usuario global
    public static Nivel nivel;// = usuario.getNiveles().get(usuario.getNiveles().size()-1);//óbjeto clase nivel global
    public static int fallos = 3; //variable global que define objetos
    public static HashMap<String, ArrayList<Producto>> disponibles;
    
    public void cargaObj(){
        
    }
   
}