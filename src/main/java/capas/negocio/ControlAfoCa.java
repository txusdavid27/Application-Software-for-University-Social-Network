package capas.negocio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.modelo.lugar;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;



public class ControlAfoCa {
	/*Creación de los listados en Arreglos y un Mapa que no es utilizado por esta
	 * entrega para la exposisión de datos en alguna ventana*/
	protected Map<Integer, lugar> lugares= new HashMap<Integer, lugar>();
	//lugaresList será una copia de lugares y viceversa.
	protected ArrayList<lugar> lugaresList= new ArrayList<lugar>();
	protected ArrayList<lugar> registro= new ArrayList<lugar>();

	/**
	 *Agrega un nuevo objeto al arreglo y Mapa de lugares y lugaresList.
	 * @param nuevo: objeto de la clase lugar.
	 */
	public void agregarlugar(lugar nuevo)
	{
		//  Se le asigna un Capi a cada Lugar		 
		int capiId = (int) Math.floor(Math.random()*(99999-10000+1)+10000);
		nuevo.setCapi(capiId);
		lugares.put(nuevo.getID(), nuevo);
		lugaresList.add(nuevo);
	}

	/**
	 * Agrega un nuevo objeto lugar a la lista de registro.
	 * @param nuevo: objeto de la clase lugar
	 * @param cambio: valor de suma o resta para el aforo, realizado por un Capi
	 * @param tiempo: Texto que muestra el tiempo real en que se llama la función.
	 */
	public void agregarCambio(lugar nuevo,String cambio, String tiempo )
	{
		nuevo.setCambio(cambio);
		nuevo.setTiempo(tiempo);
//		int capiId = (int) Math.floor(Math.random()*(99999-10000+1)+10000);
//		nuevo.setCapi(capiId);
		registro.add(0, nuevo);
	}

	/**
	 * Busca un lugar por su ID asignado por la Univeridad Javeriana.
	 * @param ID: ID del lugar para veríficar su existencia.
	 * @return lugar: el objeto 'lugar' si se encontró, de lo contrario 'null'.
	 */
	public lugar buscarlugar(Integer ID)
	{
		if(this.lugares.containsKey(ID))
		{
			return this.lugares.get(ID);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Busca un lugar por su Nombre asignado.
	 * @param Nombre del lugar para veríficar su existencia.
	 * @return lugar: el objeto 'lugar' si se encontró, de lo contrario 'null'.
	 */
	public lugar buscarlugarXNombre(String Nombre)
	{
		for(lugar l:lugaresList)
		{
			if(l.getNombreCompleto().equals(Nombre))
			{
				return l;
			}
		}
		return null;
	}

	/**
	 * Elimina de lugares y de lista lugares un objeto específico.(NO SE IMPLEMENTA 24/09/2021)
	 * @param ID: ID del lugar para veríficar su existencia.
	 * @return boolean: se borra correctamente(true) o no(false).
	 */
	public boolean borrarlugar(Integer ID)
	{
		if(buscarlugar(ID)!=null)
		{
			lugares.remove(ID);
			for(lugar l: lugaresList)
			{
				if(l.getID().equals(ID))
				{
					lugaresList.remove(l);
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Suma todos los valores de Aforo por lugar.
	 * @return Integer: El numero que representa el Aforo de toda la Sede.
	 */
	public Integer ContarTotal()
	{
		Integer c=0;
		for(lugar l: lugaresList )
		{
			c+=l.getAforo();
		}
		return c;
	}

	/**
	 * Lectura del archivo base_datos.csv para llenar los listados lugar y lugaresList
	 */
	public void leerDatos()
	{
		try
		(
			FileReader in = new FileReader("archivos/base_datos.csv");
			CSVParser parser = new CSVParser(in,CSVFormat.DEFAULT.withFirstRecordAsHeader())){			
			List<CSVRecord> filas=parser.getRecords();
			for(CSVRecord fila: filas)
			{
				String ID= fila.get("ID");
				String nombre=fila.get("NOMBRE");  
				String aforo= fila.get("AFORO");
				String maximo= fila.get("MAX");
				lugar nuevo=new lugar(nombre, Integer.parseInt(aforo), Integer.parseInt(ID),Integer.parseInt(maximo));
				agregarlugar(nuevo);
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

	/**
	 * Reescritura del archivo base_datos.csv para actualizar los atributos modificados de uno o varios lugares.
	 */
	public void escribirDatos()
	{
		String[] HEADERS= {"ID","NOMBRE","AFORO","MAX"};
		try
		(
			FileWriter out = new FileWriter("archivos/base_datos.csv");
			CSVPrinter printer = new CSVPrinter(out,CSVFormat.DEFAULT.withHeader(HEADERS))){			
			for(lugar l: lugaresList)
			{
				printer.printRecord(l.getID(),l.getNombreCompleto(),l.getAforo(),l.getMaximoPersonas());
			}	    				
		}catch(IOException ioe) {
			System.err.println("Error al procesar el archivo: "+ioe.getMessage());
			ioe.printStackTrace();			
		}
		System.out.println("ArchivoCreado");
	}

	/**
	 * Escritura del archivo registro.csv que almacena en lista los cambios realizados por cada Capi.
	 */
	public void registrar()
	{
		String[] HEADERS= {"ID","NOMBRE","AFORO","CAMBIO","TIEMPO","CAPI"};
		try
		(
			FileWriter out = new FileWriter("archivos/registro.csv");
			CSVPrinter printer = new CSVPrinter(out,CSVFormat.DEFAULT.withHeader(HEADERS))){			
			for(lugar c: registro)
			{
				printer.printRecord(c.getID(),c.getNombreCompleto(),c.getAforo(),c.getCambio(),c.getTiempo(),c.getCapi());
			}	    				
		}catch(IOException ioe) {
			System.err.println("Error al procesar el archivo: "+ioe.getMessage());
			ioe.printStackTrace();
			
		}
		System.out.println("RegistroCreado");
	}

	/**
	 * Inicializa el conteo al iniciar el día(según regla de Negocio redefinible).
	 */
	public void reinicio()
	{
		for(lugar l: lugaresList )
		{
			//Se parte del hecho de que hay mínimo dos personas en todo momento por cada instalación.
			l.setAforo(2);
		}
		escribirDatos();
	}
	
	public Map<Integer,lugar> getlugares()
	{
		return lugares;
	}
	
	public ArrayList<lugar> getlugaresList()
	{
		return lugaresList;
		
	}

	public ArrayList<lugar> getRegistro()
	{
		return registro;
		
	}
}