package interfacesCapas.persistencia;

import core.modelo.Usuario;

import java.util.List;

/**
 * Control de Persistencia.Interfaz.
 */
public interface IPersistenciaUsuariosCRUD
{
    List<Usuario> leer();
    void escribir(List<Usuario> usuarioList);
}
