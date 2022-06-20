package capas.facade;

import capas.negocio.GestionChats;
import capas.negocio.GestionUsuarios;

import capas.persistencia.ManejadorUsuarioConsultasCSV;
import core.modelo.Usuario;
import interfacesCapas.facade.IUsuariosFacade;
import interfacesCapas.negocio.IGestionChats;
import interfacesCapas.negocio.IGestionUsuariosCRUD;

import interfacesCapas.persistencia.IPersistenciaUsuariosCRUD;

import java.util.List;

public class UsuariosFacade implements IUsuariosFacade {

    /**
     * Se declara una instancia para la clase.
     */
    private static UsuariosFacade instancia =null;

    /**
     * Se solicita la interfaz de CRUD para Usuarios.
     */
    IGestionUsuariosCRUD CRUD;

    /**
     * Se solicita la interfaz para gestion de CHATs
     */
    IGestionChats CHAT;

    /**
     * Toma la instancia de la clase.
     * @return instancia de la clase.
     */
    public static UsuariosFacade getInstance()
    {
        if(instancia == null){
            instancia = new UsuariosFacade();
        }
        return instancia;
    }

    /**
     * Constructor de la clase.
     */
    private UsuariosFacade()
    {
        CRUD= new GestionUsuarios();
        CHAT= new GestionChats();

    }

    /**
     * Petición a la Interfaz de consulta de usuarios.
     * @return Listado
     */
    @Override
    public List<Usuario> consultarUsuarios() {
        return CRUD.getPersistencia().leer();
    }

    /**
     * Un usuario es Creado
     * @param u
     */
    public void crearUsuario(Usuario u){CRUD.crearUsuario(u);}

    /**
     * Se busca y encuentra o no un usuario.
     * @param u
     * @return El  objeto Usuario.
     */
    public Usuario buscarUsuario(String u){ return CRUD.buscarUsuario(u);}

    /**
     * Se elimina el Usuario.
     * @param u
     */
    public void eliminarUsuario(Usuario u){CRUD.eliminarUsuario(u);}

    /**
     * Se actualiza la persistencia.
     */
    public void actualizar(){CRUD.leerDatos();}

    /**
     * Se lee la lista de Usuarios.
     * @return Listado
     */
    public List<Usuario> verUsuarios(){return CRUD.verUsuarios();}

    /**
     * Se guarda la Info tratada durante la ejecución.
     */
    public void guardarInfo(){CRUD.guardar();}
}
