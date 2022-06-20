package interfacesCapas.negocio;

import core.modelo.Chat;
import core.modelo.Usuario;
import interfacesCapas.persistencia.IPersistenciaUsuariosCRUD;

import java.util.List;

public interface IGestionChats {

    /**
     * Interfaz actualmente en desuso.
     * @param usuario
     */
    void escribirChat(String usuario);
    void leerChat(String usuario);
}
