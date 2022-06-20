package capas.facade;

import capas.negocio.*;
import core.modelo.Chat;
import core.modelo.Shout;
import core.modelo.Usuario;
import interfacesCapas.negocio.IGestionChats;
import interfacesCapas.negocio.IGestionShouts;
import interfacesCapas.negocio.IGestionUsuariosCRUD;

import java.util.List;


public class ShoutsFacade {

    /**
     * Se crea una instancia para la Gestion de los Shouts.
     */
    private static ShoutsFacade instancia =null;

    /**
     * Se solicita la Interfaz de gestión de Shouts.
     */
    IGestionShouts GESTOR;

    /**
     * Tomar instancia de la clase
     * @return la instancia general de la clase
     */
    public static ShoutsFacade getInstance()
    {
        if(instancia == null){
            instancia = new ShoutsFacade();
        }
        return instancia;
    }

    /**
     * Constructor de la clase.
     */
    private ShoutsFacade()
    {
        GESTOR= new GestionShouts();
    }


    /**
     * Pide a la Interfaz la lectura de los shouts.
     * @return Listado.
     */
    public List<Shout> leer(){
        return GESTOR.leerShouts();
    }

    /**
     * Pide a la Interfaz la impresión de un Shout.
     * @param autor
     * @param contenido
     */
    public void gritar(String autor, String contenido){
        GESTOR.escribirShout(autor,contenido);
    }

}