package interfacesCapas.negocio;

import core.modelo.Usuario;
import interfacesCapas.persistencia.IPersistenciaUsuariosCRUD;

import java.util.List;

/**
 * Interfaz de gestión de Usuarios.
 */
public interface IGestionUsuariosCRUD {

    void crearUsuario(Usuario usuario);

    /**
     * Provisional.
     * @param implementacionPersistencia
     */
    void setImplementacionPersistencia(IPersistenciaUsuariosCRUD implementacionPersistencia);

    IPersistenciaUsuariosCRUD getPersistencia();

    Usuario buscarUsuario(String u);

    void eliminarUsuario(Usuario u);

    void leerDatos();

    void guardar();

    List<Usuario> verUsuarios();

}
