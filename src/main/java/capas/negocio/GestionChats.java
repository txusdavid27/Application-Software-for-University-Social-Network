package capas.negocio;

import capas.facade.UsuariosFacade;
import capas.persistencia.ManejadorUsuarioConsultasCSV;
import core.ControllerModelo.ControladorUsuario;
import core.enums.Tipo;
import core.modelo.Chat;
import core.modelo.Usuario;
import interfacesCapas.negocio.IGestionChats;
import interfacesCapas.negocio.IGestionUsuariosCRUD;
import interfacesCapas.persistencia.IPersistenciaUsuariosCRUD;
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
import java.util.UUID;


public class GestionChats implements IGestionChats {
    UsuariosFacade facade;
    ControladorUsuario control= new ControladorUsuario();

    /**
     * Código Provisional para la persistencia de los chats entre Usuarios.
     * @state En desuso.
     * @param usuario
     */
    @Override
    public void escribirChat(String usuario) {
        String[] HEADERS= {"EMISOR","MENSAJE"};
        try
                (
                        FileWriter out = new FileWriter("archivos/"+usuario+".csv");
                        CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))){
            for(Chat c: facade.buscarUsuario(control.enSesion()).getChats())
            {
                //CORREO,USERNAME,CONTRA,TELEFONO,CARRERA,ESTADO,DESCRIPCION,ID,TIPO,MEDIO
                printer.printRecord(c.getEmisor(),c.getMensaje());
            }
        }catch(IOException ioe) {
            System.err.println("Error al procesar el archivo: "+ioe.getMessage());
            ioe.printStackTrace();
        }
        System.out.println("ArchivoCreado");
    }

    /**
     * Código Provisional para la persistencia de los chats entre Usuarios.
     * @state En desuso
     * @param usuario
     */
    @Override
    public void leerChat(String usuario) {
        List<Chat> chatsAux = new ArrayList<>();

        try (FileReader in = new FileReader("archivos/"+usuario+".csv");
             //FileReader in = new FileReader("archivos/usuarios.csv");
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader())){
            List<CSVRecord> filas=parser.getRecords();
            for(CSVRecord fila: filas)
            {
                //CORREO,USERNAME,CONTRA,TELEFONO,CARRERA,ESTADO,DESCRIPCION,ID,TIPO,MEDIO
                String emisor= fila.get("EMISOR");
                String mensaje=fila.get("MENSAJE");

                Chat nuevo=new Chat(emisor,mensaje);
                facade.buscarUsuario(usuario).getChats().add(nuevo);
                facade.buscarUsuario(control.enSesion()).getChats().add(nuevo);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("El archivo no se encontró: "+e.getMessage());
        }catch(IOException ioe) {
            System.err.println("Error al procesar el archivo: "+ioe.getMessage());
            ioe.printStackTrace();
        }
        System.out.println("ArchivoCreado");

    }
}
