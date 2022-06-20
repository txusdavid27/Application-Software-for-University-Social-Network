package capas.negocio;

import core.modelo.Usuario;


public class Agregar_amigos {

    /**
     * Se agrega un Amigo a un usuario específico.
     * @param usuario
     * @param aux
     * @return confirmación.
     */
    public static boolean asignar(Usuario usuario, Usuario aux){
        if(usuario.getId().equals(aux.getId()))
        {
            System.out.println("No se puede agregar a si mismo");
            return false;
        }

        for (Usuario entry: usuario.getAmigos()){
            if(entry.getId().equals(aux.getId())){
                System.out.println("Usuarios ya son amigos");
                return false;
            }
        }
        usuario.getAmigos().add(aux);
        aux.getAmigos().add(usuario);
        System.out.println("Usuario agregado en la lista de amigos");
        return true;
    }
}