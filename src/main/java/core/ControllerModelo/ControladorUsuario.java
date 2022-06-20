package core.ControllerModelo;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

import capas.facade.AmigosFacade;
import core.modelo.Usuario;

import javax.imageio.IIOException;

public class ControladorUsuario {

    /**
     * Se permite una copia del arreglo de Usuarios.
     */
    protected ArrayList<Usuario> usuarios = new ArrayList<>();

    /**
     * Se identifíca el Usuario Actual.
     */
    protected Usuario usuario_actual;

    /**
     * Se permite agregar un Usuario.(funcion en desuso-implementada en otra clase)
     * @param usuario
     */
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    /**
     * Permite devolver el usuario actual, funcion redundante.
     * @return
     */
    public Usuario getUsuario() {
        return this.usuario_actual;
    }

    /**
     * Funcion redundante.
     * @param id
     * @return confirmacion.
     */
    public boolean nuevoAmigo(String id){
        boolean encontrado = false;
        for (int i = 0; i<this.usuarios.size(); i++) {
            if(usuarios.get(i).getId().equals(id)){
                AmigosFacade.asignar(usuario_actual, usuarios.get(i));
                encontrado = true;
            }
        }
        if(!encontrado)
            System.out.println("Usuario no encontrado");

        return encontrado;
    }

    /**
     * Funcion redundante.
     * @param id
     * @return confirmacion.
     */
    public boolean eliminarAmigo(String id){
        for (int i = 0; i<this.usuarios.size(); i++) {
            if(usuarios.get(i).getId().equals(id)){
                AmigosFacade.eliminar(usuario_actual, usuarios.get(i));
                break;
            }
        }
        return true;
    }

    /**
     * Funcion en desuso por redudancia.
     * @return
     */
    public ArrayList<Usuario> getListUsuarios(){
        return usuarios;
    }

    /**
     * Permite identificar el Usuario en Sesion Actual.
     * @return username
     */
    public String enSesion(){
        try(
                FileReader fr= new FileReader("archivos/sesion.txt");
                BufferedReader br= new BufferedReader(fr);
                ){
            String linea=null;
            while((linea=br.readLine())!=null){
                return linea;
            }
        } catch (FileNotFoundException e){
            return "admin";
        } catch (IOException e){
            return "admin";
        }
        return "admin";
    }

    /**
     * Escribe en un archivo el usuario de la sesion actual.
     * Cierra o Abre sesion.
     * @param user
     */
    public void OpenCloseSesion(String user){
        try (
                FileWriter fw=new FileWriter("archivos/sesion.txt");
                BufferedWriter bw= new BufferedWriter(fw)
                ){
            bw.write(user);
            bw.newLine();
        }catch (IOException ioe){
            System.out.println("Error al escribir archivo");
        }
    }
}