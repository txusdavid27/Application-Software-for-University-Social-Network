package capas.persistencia;

import core.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase provisional en desuso.
 */
public class CacheManagerSingleton {
    private static CacheManagerSingleton instance = null;
    private List<Usuario> cacheUsuarios;
    private CacheManagerSingleton()
    {
        cacheUsuarios = new ArrayList<Usuario>();
    }
    public List<Usuario> getCacheUsuarios() {
        return cacheUsuarios;
    }
    public void cargarCacheUsuarios(List<Usuario> usuariosList)
    {
        cacheUsuarios = usuariosList;
    }
    public static CacheManagerSingleton getInstance()
    {
        if(instance==null)
            instance = new CacheManagerSingleton();
        return instance;
    }
}
