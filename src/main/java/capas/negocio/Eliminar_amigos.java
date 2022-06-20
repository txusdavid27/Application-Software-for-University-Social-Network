package capas.negocio;

import core.modelo.Usuario;

public class Eliminar_amigos {
    /**
     * Se elimina amigo de usuario específico.
     * @param usuario
     * @param aux
     * @return confirmación.
     */
    public static boolean eliminar(Usuario usuario, Usuario aux){
        if(usuario.getId().equals(aux.getId()))        {
            System.out.println("No se puede eliminar a si mismo");
            return false;
        }
        for(Usuario entry: usuario.getAmigos()){
            if(entry.equals(aux)){
                usuario.getAmigos().remove(aux);
                aux.getAmigos().remove(usuario);
                System.out.println("Usuario eliminado de la lista de amigos");
                return true;
            }
        }
        System.out.println("No se encontro al usuario en la lista de amigos");
        return false;
    }
}