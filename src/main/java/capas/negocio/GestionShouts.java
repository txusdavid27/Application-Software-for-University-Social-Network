package capas.negocio;

import capas.facade.UsuariosFacade;
import core.ControllerModelo.ControladorUsuario;
import core.modelo.Chat;
import core.modelo.Shout;
import interfacesCapas.negocio.IGestionChats;
import interfacesCapas.negocio.IGestionShouts;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GestionShouts implements IGestionShouts {

    /**
     * Se crea una lista de Gritos.
     */
    List<Shout> shouts= new ArrayList<>();

    /**
     * Se permite escribir un Shout.
     * @param usuario
     * @param contenido
     */
    @Override
    public void escribirShout(String usuario,String contenido) {
        Shout shout= new Shout(usuario,contenido);
        shouts.add(shout);
    }

    /**
     * Se lee el listado general de shouts
     * @return Listado
     */
    @Override
    public List<Shout> leerShouts() {
        return shouts;
    }
}
