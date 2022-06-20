package capas.facade;

import capas.negocio.*;
import core.modelo.Usuario;



public class AmigosFacade {

    /**
     * Manipula los amigos de manera virtual y agrega uno.
     * @param usuario
     * @param aux
     * @return boolean con el valor de acción realizada, actualmente el facade no está en uso.
     */
    public static boolean asignar(Usuario usuario, Usuario aux){
        return Agregar_amigos.asignar(usuario, aux);
    }

    /**
     * Manipula los amigos de manera virtual y agrega uno.
     * @param usuario
     * @param aux
     * @return boolean con el valor de acción realizada, actualmente el facade no está en uso.
     */
    public static boolean eliminar(Usuario usuario, Usuario aux){
        return Eliminar_amigos.eliminar(usuario, aux);
    }

}