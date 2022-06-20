package presentacion.general;

import capas.facade.UsuariosFacade;
import core.ControllerModelo.ControladorUsuario;
import core.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VincularController implements Initializable {

    UsuariosFacade facade;
    ControladorUsuario control = new ControladorUsuario();

    @FXML
    private ChoiceBox<String> medio;


    @FXML
    private TextField userLog;

    @FXML
    private void volver() throws IOException {
        facade.guardarInfo();
        App.setRoot("principal");
    }

    @FXML
    void cargarMedios(){
        medio.getItems().add("PayPal");
        medio.getItems().add("Neki");
        medio.getItems().add("DaviPlata");
        medio.getItems().add("PSE");
        medio.getItems().add("VISA");
        medio.getItems().add("PayU");

    }

    @FXML
    void guardar(ActionEvent event) throws IOException {

        if(facade.buscarUsuario(control.enSesion() )!=null){
            facade.buscarUsuario(control.enSesion() ).setMedio(medio.getValue());
        }else{
            crearAlerta(Alert.AlertType.WARNING, "Login","Error", "Inexistente");
        }
        App.setRoot("principal");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facade=UsuariosFacade.getInstance();
        cargarMedios();
    }

    private void crearAlerta(Alert.AlertType tipo, String titulo, String tituloInterno, String mensaje ){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(tituloInterno);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

