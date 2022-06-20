package capas.persistencia;

import capas.facade.UsuariosFacade;
import core.enums.Tipo;
import core.modelo.Usuario;
import core.modelo.lugar;
import interfacesCapas.persistencia.IPersistenciaUsuariosCRUD;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import utils.PropertiesReader;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManejadorUsuarioConsultasCSV implements IPersistenciaUsuariosCRUD {

    UsuariosFacade facade;
    @Override
    public List<Usuario> leer()
    {
        facade= UsuariosFacade.getInstance();
        //PropertiesReader pr = new PropertiesReader();
        List<Usuario> misUsuarios = new ArrayList<Usuario>();

        try (FileReader in = new FileReader("archivos/usuarios.csv");
             //FileReader in = new FileReader("archivos/usuarios.csv");
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader())){
            List<CSVRecord> filas=parser.getRecords();
            for(CSVRecord fila: filas)
            {
                //CORREO,USERNAME,CONTRA,TELEFONO,CARRERA,ESTADO,DESCRIPCION,ID,TIPO,MEDIO
                String correo= fila.get("CORREO");
                String username=fila.get("USERNAME");
                String pass= fila.get("CONTRA");
                String tel= fila.get("TELEFONO");
                String carrera= fila.get("CARRERA");
                String estado= fila.get("ESTADO");
                String descripcion=fila.get("DESCRIPCION");
                String id = fila.get("ID");
                UUID ide;
                if(id.equals("")){
                    ide=null;
                }
                else
                {
                    ide= UUID.fromString(id);
                }
                String tipo = fila.get("TIPO");
                Tipo type= Tipo.NORMAL;
                if(tipo.equals(Tipo.ADMIN.toString())){
                    type=Tipo.ADMIN;
                }
                if(tipo.equals(Tipo.CAPI.toString())){
                    type=Tipo.CAPI;
                }
                if(tipo.equals(Tipo.NORMAL.toString())){
                    type=Tipo.NORMAL;
                }
                String medio= fila.get("MEDIO");
                Usuario nuevo=new Usuario(correo,username,pass,Long.parseLong(tel),carrera,estado,descripcion,ide,type,medio);

                try {
                    //Ponemos a "Dormir" el programa durante los ms que queremos
                    Thread.sleep(50);
                } catch (Exception e) {
                    System.out.println(e);
                }
                /*
                FileReader inA = new FileReader("archivos/"+username+".csv");
                CSVParser parserA = new CSVParser(inA, CSVFormat.DEFAULT.withFirstRecordAsHeader());
                List<CSVRecord> filasA=parserA.getRecords();
                for(CSVRecord filaA: filasA){
                    String amigo= filaA.get("AMIGOS");
                    nuevo.getAmigos().add(facade.buscarUsuario(amigo));
                }
                 */
                misUsuarios.add(nuevo);
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
        return misUsuarios;
    }

    @Override
    public void escribir(List<Usuario> usuarioList) {
        //PropertiesReader pr = new PropertiesReader();
        String[] HEADERS= {"CORREO","USERNAME","CONTRA","TELEFONO","CARRERA","ESTADO","DESCRIPCION","ID","TIPO","MEDIO"};
        //String[] HEAD= {"AMIGOS"};

        try
                (
                        FileWriter out = new FileWriter("archivos/usuarios.csv");
                        CSVPrinter printer = new CSVPrinter(out,CSVFormat.DEFAULT.withHeader(HEADERS))){
            for(Usuario u: usuarioList)
            {
                //CORREO,USERNAME,CONTRA,TELEFONO,CARRERA,ESTADO,DESCRIPCION,ID,TIPO,MEDIO
                printer.printRecord(u.getCorreo(),u.getUsername(),u.getPassword(),u.getTelefono(),u.getCarrera(),u.getEstado(),u.getDescripcion(),u.getId(),u.getTipo(),u.getMedio());
                /*
                FileWriter amigosOut= new FileWriter("archivos/"+u.getUsername()+".csv");
                CSVPrinter printAmigos = new CSVPrinter(amigosOut,CSVFormat.DEFAULT.withHeader(HEAD));
                for(Usuario us: u.getAmigos()){
                    printAmigos.printRecord(us.getUsername());
                }
                //amigosOut.close();
                 */
            }
        }catch(IOException ioe) {
            System.err.println("Error al procesar el archivo: "+ioe.getMessage());
            ioe.printStackTrace();
        }
        System.out.println("ArchivoCreado");
    }


}
