package model;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Producto implements Serializable, Comparable<Producto> {
    private String nombre;
    private String categoria;
    private String nombreArchivo;
    private double precio;
    public static HashMap<String, ArrayList<Producto>> productos;
    
    private static final long serialVersionUID = 1L;
    
    /**
    Constructor
    */
    public Producto(String nombreArchivo, String categoria, String nombre, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.nombreArchivo = nombreArchivo;
        this.precio= precio;
    }
    
    //Métodos getters y setters 
    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public double getPrecio() {
        return precio;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
        
    /**
    Método para cargar los productos al HashMap
    */
    public static void cargarProductos(){
        
        productos = new HashMap<>();
        try {
            List<String> lineas= Files.readAllLines(Paths.get("src\\main\\resources\\recursos\\catalogoImagenes.csv"));
            lineas.remove(0);
            for (String l: lineas){
                String[] sep=l.split(",");              
                    productos.putIfAbsent(sep[1],new ArrayList<Producto>()); 
                    productos.get(sep[1]).add(new Producto(sep[0],sep[1], sep[2], Double.parseDouble(sep[3])));
                }
             System.out.println(productos);
             
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public int compareTo(Producto p) {
        return this.nombre.compareTo(p.nombre);
    }
    
    
}
