package presentacion.general;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import capas.facade.UsuariosFacade;
import core.ControllerModelo.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import core.modelo.Usuario;



public class EditarController implements Initializable{

	UsuariosFacade facade;
	ControladorUsuario control = new ControladorUsuario();
    //@FXML
    //private TableView<Usuario> listaRegistro;

	@FXML
	private TextField ContraLogin;

	@FXML
	private Label NombreLugar;

	@FXML
	private Button actualizarUsuario;

	@FXML
	private TextField carrera;

	@FXML
	private Label correo;

	@FXML
	private TextArea descripcion;

	@FXML
	private TextField info;

	@FXML
	private TextField user;

	@FXML
	private Label nombre;

	@FXML
	private Label telefono;

	@FXML
	private AnchorPane panelEditar;

	@FXML
	private Pane panel_2;

	@FXML
	private Label usuario;

	@FXML
	private TextField userLogin;

	@FXML
	private void volver() throws IOException {
		facade.guardarInfo();
		App.setRoot("principal");
	}

	@FXML
	void actualizarUsuario(ActionEvent event) {
		if(user.getText() !=null){
			facade.buscarUsuario(control.enSesion()).setUsername(user.getText());
		}
		if(carrera.getText() != null){
			facade.buscarUsuario(control.enSesion()).setCarrera(carrera.getText());
		}
		if(info.getText()!= null){
			facade.buscarUsuario(control.enSesion()).setEstado(info.getText());
		}
		if(descripcion.getText()!=null){
			facade.buscarUsuario(control.enSesion()).setDescripcion(descripcion.getText());
		}
	}

	@FXML
	void iniciarSesion(ActionEvent event) {
		if(facade.buscarUsuario(control.enSesion())!=null){
			panelEditar.setVisible(true);
			crearAlerta(Alert.AlertType.CONFIRMATION, "Login","Correct", "Aceptado");
			renderVentana();
		}
		else
		{
			crearAlerta(Alert.AlertType.WARNING, "Login","Error", "Inexistente");
		}

	}

    @Override 
    public void initialize(URL location, ResourceBundle resources) {
		facade=UsuariosFacade.getInstance();
		panelEditar.setVisible(true);
		renderVentana();

    }

    /**
     * Permite reescribir todos los datos
     */
    public void renderVentana()
    {
    	limpiarVentana();
		user.setText(facade.buscarUsuario(control.enSesion()).getUsername());
		usuario.setText(facade.buscarUsuario(control.enSesion()).getCorreo());
		telefono.setText(facade.buscarUsuario(control.enSesion()).getTelefono().toString());
		carrera.setText(facade.buscarUsuario(control.enSesion()).getCarrera());
		info.setText(facade.buscarUsuario(control.enSesion()).getEstado());
		descripcion.setText(facade.buscarUsuario(control.enSesion()).getDescripcion());

    }
    
    /**
     * Permite limpiar los datos escritos en todos los paneles.
     */
    public void limpiarVentana()
    {
		panelEditar.setVisible(false);
		user.setText("");
		carrera.setText("");
		info.setText("");
		descripcion.setText("");
		panelEditar.setVisible(true);
    }

    private void crearAlerta(Alert.AlertType tipo, String titulo, String tituloInterno, String mensaje ){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(tituloInterno);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
