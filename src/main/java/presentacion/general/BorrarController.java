package presentacion.general;

import capas.facade.UsuariosFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import presentacion.general.App;

import java.io.IOException;

public class BorrarController {

    private UsuariosFacade facade;

    @FXML
    private TextField correo_user;

    @FXML
    private void initialize(){
        facade=UsuariosFacade.getInstance();
    }

    @FXML
    void eliminar(ActionEvent event) throws IOException {
        if(facade.buscarUsuario(correo_user.getText())!= null && !correo_user.getText().equals("admin")){
            facade.eliminarUsuario(facade.buscarUsuario(correo_user.getText()));
            crearAlerta(Alert.AlertType.WARNING, "Borrado","Exitoso", "Continuar");
            volver();
        }
        else
        {
           crearAlerta(Alert.AlertType.WARNING, "Borrado","Error", "No es posible eliminar el Usuario");
        }
    }

    @FXML
    void volver() throws IOException {
        facade.guardarInfo();
        App.setRoot("principal");
    }

    private void crearAlerta(Alert.AlertType tipo, String titulo, String tituloInterno, String mensaje ){

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(tituloInterno);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



}



