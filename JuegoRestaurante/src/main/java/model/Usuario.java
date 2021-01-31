package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Usuario implements Serializable {

    private String nombre;
    private String contrasena;
    private double valorAcumulado;
    private ArrayList<Nivel> nivelesCursados;
    public static HashMap<String, Usuario> usuarios;
    
    private static final long serialVersionUID = 1L;
    
    /**
    Constructor
     * @param nombre
     * @param contrasena
    */
    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena= contrasena;
        this.valorAcumulado = 0;
        nivelesCursados = new ArrayList<>();
        nivelesCursados.add(Nivel.niveles.get(0));
    }
    
    //Métodos getters y setters 
    public String getNombre() {
        return nombre;
    }

    public double getValorAcumulado() {
        return valorAcumulado;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    public ArrayList<Nivel> getNiveles() {
        return nivelesCursados;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addNivel(int numeroNivel) {
        if (numeroNivel > nivelesCursados.size()){
            nivelesCursados.add(Nivel.niveles.get(numeroNivel-1));
        }
    }
    
    /**
    Método para deserializar a los usuarios
    */
    public static void cargarUsuarios(){
        try (ObjectInputStream lectorArchivo = new ObjectInputStream(new FileInputStream("src\\main\\resources\\recursos\\usuarios.dat"));){
            usuarios = (HashMap<String, Usuario>) lectorArchivo.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
    Método para serializar a los usuarios
    */
    public static void actualizarUsuarios(){
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("src\\main\\resources\\recursos\\usuarios.dat"));){
            escritor.writeObject(usuarios);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void aumentarValor(double valor){
        this.valorAcumulado += valor;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", contrasena=" + contrasena + '}';
    }
    
    
}
