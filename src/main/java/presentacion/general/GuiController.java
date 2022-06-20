package presentacion.general;


import capas.facade.UsuariosFacade;
import capas.negocio.ControlAfoCa;
import core.ControllerModelo.ControladorUsuario;
import core.enums.Tipo;
import core.modelo.lugar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class GuiController implements Initializable{

	private ControlAfoCa control= new ControlAfoCa();
	ControladorUsuario controlUsers = new ControladorUsuario();
	UsuariosFacade facade;
	
    @FXML
    private Pane panel_3;

    @FXML
    private TableView<lugar> listaRegistro;
    
    @FXML
    private TableColumn<lugar, Integer> IDLugar_Registro;

    @FXML
    private TableColumn<lugar, String> NOMBRELugar_Registro;

    @FXML
    private TableColumn<lugar, Integer> CAMBIOLugar_Registro;

    @FXML
    private TableColumn<lugar, Calendar> Tiempo_Registro;

    @FXML
    private Button inicializarConteo;

    @FXML
    private Pane panel_2;

    @FXML
    private ListView<String> listaLugares;

    @FXML
    private Label IDLugar;

    @FXML
    private Label NombreLugar;

    @FXML
    private Spinner<Integer> spinner= new Spinner<Integer>();

    @FXML
    private Label aforoXLugar;

    @FXML
    private Button actualizarLugar;

    @FXML
    private Pane panel_1;

    @FXML
    private Label totalCampus;

	@FXML
	private Label maxLugar;

	@FXML
	private void volver() throws IOException {
		inicializarConteo.setDisable(false);
		facade.guardarInfo();
		App.setRoot("principal");
	}
    
    @Override 
    public void initialize(URL location, ResourceBundle resources) {
		facade=UsuariosFacade.getInstance();
    	control.leerDatos();
    	panel_2.setVisible(false);
    	panel_3.setVisible(false);
		spinner.setDisable(true);
    	renderVentana();
        panel_1.setVisible(true);
		if(controlUsers.enSesion().equals("")){
			inicializarConteo.setDisable(true);
		}else
		{
			if(controlUsers.enSesion().equals("admin")){
				inicializarConteo.setDisable(false);
			}
			else{
				inicializarConteo.setDisable(true);
			}
		}

    }
    
    @FXML
    void InicializarConteo(ActionEvent event) {
    	control.reinicio();
		inicializarConteo.setDisable(true);
    	renderVentana();
    }

    @FXML
    void ActivarPanel_1(ActionEvent event) {
    	panel_1.setVisible(true);
    	panel_2.setVisible(false);
    	panel_3.setVisible(false);
    	renderVentana();
    }

    @FXML
    void ActivarPanel_2(ActionEvent event) {
    	panel_1.setVisible(false);
    	panel_2.setVisible(true);
    	panel_3.setVisible(false);
    	renderVentana();
    }

    @FXML
    void ActivarPanel_3(ActionEvent event) {
    	panel_1.setVisible(false);
    	panel_2.setVisible(false);
    	panel_3.setVisible(true);
    	renderVentana();
    }
    
    @FXML
    void lugarSeleccionado(MouseEvent event) {
    	IDLugar.setText(control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getID().toString());
    	NombreLugar.setText(control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getNombreCompleto().toString());
    	aforoXLugar.setText(control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getAforo().toString());
    	maxLugar.setText(control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getMaximoPersonas().toString());

		/**
    	 * Si ya tiene seleccionado un lugar, lo puede modificar
    	 */
		if(!controlUsers.enSesion().equals("")){
			if(controlUsers.enSesion().equals("admin") ||
					facade.buscarUsuario(controlUsers.enSesion()).getTipo().equals(Tipo.CAPI) ){
				actualizarLugar.setDisable(false);
				spinner.setDisable(false);
			}
			else{
				spinner.setDisable(true);
				actualizarLugar.setDisable(true);
			}
		}
		else{
			spinner.setDisable(true);
			actualizarLugar.setDisable(true);
		}
    }

    /**
     * Actualiza (Suma o Resta) al Aforo del Lugar Específico seleccionado.
     * @obj spinner: Toma el valor de Entrada/salida de personas para el aforo.
     * @obj listaLugares: Muestra el listado navegable y seleccionable de instalaciones.
     * @obj control: el apuntador a la clase controlAfoCa.
     * @obj aforoXLugar:El valor del Label que presenta el aforo del lugar seleccionado.
     * @param event
     */
    @FXML
    void ActualizarLugar(ActionEvent event) {
    	if((spinner.getValue()+control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getAforo())>=0 )
    	{
    		control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).setAforo(
        			spinner.getValue()
        				+control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getAforo()
            );
        	aforoXLugar.setText(control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getAforo().toString());
    		inicializarConteo.setDisable(false);
		}
    	else
    	{
			inicializarConteo.setDisable(true);
    		control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).setAforo(0);
    		aforoXLugar.setText(control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getAforo().toString());
    	}
    	Calendar calendario = Calendar.getInstance();
    	int hora, minutos, segundos;
    	hora =calendario.get(Calendar.HOUR_OF_DAY);
    	minutos = calendario.get(Calendar.MINUTE);
    	segundos = calendario.get(Calendar.SECOND);
    	//Copia
    	lugar aux=control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem());
    	//Nuevo Registro
    	lugar nuevo= new lugar(aux.getNombreCompleto(),aux.getAforo() , aux.getID(),aux.getMaximoPersonas());
    	nuevo.setCapi(aux.getCapi());
    	//Si se suma
    	if((spinner.getValue()+control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getAforo()) >= control.buscarlugarXNombre(listaLugares.getSelectionModel().getSelectedItem()).getAforo())
    	{
    		control.agregarCambio(nuevo, //se envía el "nuevo" lugar(que es uno existente), pero es para otra linea en el registro
					"+"+spinner.getValue().toString(),
					hora+":"+minutos+":"+segundos);
    	}//Si se resta(para el registro)
    	else
    	{
    		control.agregarCambio(nuevo,
					""+spinner.getValue().toString(),
					hora+":"+minutos+":"+segundos);
    	}
    	control.registrar();
    	//crearAlerta(Alert.AlertType.WARNING, "","", "");    	    	
    }
    
    /**
     * Permite reescribir todos los datos de los paneles ConteoTotal.
     * @obj control: el apuntador a la clase controlAfoCa.
     * @obj totalCampus: label que presenta el Aforo total Aproximado de la Universidad.
     * @obj listaRegistro: Listado en el Panel Registro, contiene los datos para archivo registro.
     */
    public void renderVentana()
    {
    	limpiarVentana();
    	control.escribirDatos();
    	totalCampus.setText(control.ContarTotal().toString());
    	listaRegistro.getItems().addAll(control.getRegistro());    	
    	for(lugar lug: control.getlugaresList())
    	{
    		listaLugares.getItems().add(lug.getNombreCompleto());
    	}
    }
    
    /**
     * Permite limpiar los datos escritos en todos los paneles.
     * @obj actualizarLugar: botón que permite el evento ActualizarLugar
     * @obj IDLugar: label con el ID del lugar.
     * @obj NombreLugar: Label con el Nombre del Lugar
     */
    public void limpiarVentana()
    {
    	actualizarLugar.setDisable(true);
    	listaRegistro.getItems().clear();
    	listaLugares.getItems().clear();
    	totalCampus.setText("");
    	IDLugar.setText("");
    	NombreLugar.setText("");
    	aforoXLugar.setText("");
    }
}
