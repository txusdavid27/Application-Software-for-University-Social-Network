package interfacesCapas.negocio;

import core.modelo.Shout;

import java.util.List;

public interface IGestionShouts {

    /**
     * Escritura/publicación de Shouts.
     * @param usuario
     * @param contenido
     */
    void escribirShout(String usuario,String contenido);

    /**
     * Petición de Shouts para lectura.
     * @return Listado.
     */
    List<Shout> leerShouts();


}
