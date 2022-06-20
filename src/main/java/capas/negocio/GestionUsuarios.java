package capas.negocio;

import capas.persistencia.ManejadorUsuarioConsultasCSV;
import core.modelo.Usuario;
import interfacesCapas.negocio.IGestionUsuariosCRUD;
import interfacesCapas.persistencia.IPersistenciaUsuariosCRUD;

import java.util.ArrayList;
import java.util.List;


public class GestionUsuarios implements IGestionUsuariosCRUD {

    /**
     * Se implementa el manejador de Archivos.
     */
    ManejadorUsuarioConsultasCSV implementacionPersistencia= new ManejadorUsuarioConsultasCSV();

    /**
     * Creación de listado de Usuarios.
     */
    List<Usuario> users=new ArrayList<>();

    /**
     * Se agrega un Usuario a la lista.
     * @param user
     */
    public void crearUsuario(Usuario user){
        users.add(user);
        System.out.println("NUEVO");
    }

    /**
     * Se elimina un Usuario de la Lista.
     * @param user
     */
    public void eliminarUsuario(Usuario user){
        users.remove(user);
    }

    /**
     * Busqueda de Usuario en la lista Por Nombre.
     * @param u
     * @return Objeto Usuario.
     */
    public Usuario buscarUsuario(String u){
        System.out.println("BUSCANDO");
        for(Usuario usuario:users)
        {
            if(usuario.getUsername().equals(u) || usuario.getCorreo().equals(u))
            {
                System.out.println("Encontrado: "+usuario.getUsername());
                return usuario;
            }
        }
        return null;
    }

    /**
     * Se actualizan los datos.
     */
    public void leerDatos(){
        System.out.println("Actualizando...");
        users=implementacionPersistencia.leer();
    }

    /**
     * Se implementa la Persistencia.
     */
    public void guardar(){
        System.out.println("Guardando...");
        implementacionPersistencia.escribir(users);
    }

    /**
     * Función provisional en Desuso.
     * @param per
     */
    @Override
    public void setImplementacionPersistencia(IPersistenciaUsuariosCRUD per) {
        //this.implementacionPersistencia = per;
    }

    /**
     * Función provisional en desuso.
     * @return null.
     */
    public IPersistenciaUsuariosCRUD getPersistencia(){return null;}

    /**
     * Peticion de Listado
     * @return Listado.
     */
    public List<Usuario> verUsuarios(){
        return users;
    }
}
