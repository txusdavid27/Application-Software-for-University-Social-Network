package interfacesCapas.facade;

import core.modelo.Usuario;

import java.util.List;

/**
 * @author aabg
 * Facade de periodistas
 * */
public interface IUsuariosFacade {

    /**
     * Consultar lista de periodistas, ordenados alfabeticamente
     * Provisional en desuso.
     * @return*/
    List<Usuario> consultarUsuarios();
}
