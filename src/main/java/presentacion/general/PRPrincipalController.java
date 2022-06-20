package presentacion.general;

import capas.facade.UsuariosFacade;
import core.ControllerModelo.ControladorUsuario;
import core.enums.Tipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PRPrincipalController implements Initializable {

    UsuariosFacade facade;
    ControladorUsuario control = new ControladorUsuario();

    @FXML
    private Button botonAmigos;

    @FXML
    private Button botonBorrar;

    @FXML
    private Button botonEditar;

    @FXML
    private Button botonMensajes;

    @FXML
    private Button botonVincular;

    @FXML
    private Button botonCerrarSesion;


    @FXML
    private PasswordField password;

    @FXML
    private TextField usuario;

    @FXML
    void modoAforo() throws IOException{
        App.setRoot("ventanasAFOROyCAPIS");
    }

    @FXML
    void modoChat() throws IOException{
        App.setRoot("ventanaChats");
    }

    @FXML
    void cambioContraseña() throws IOException{
        //App.setRoot("ventanasAFOROyCAPIS");
    }

    @FXML
    void CambiarACrearPerfil() throws IOException {
        App.setRoot("nuevoPerfil");
    }

    @FXML
    void CambiarAEditarPerfil() throws IOException {
        App.setRoot("ventanaEditarPerfil");
    }

    @FXML
    void cambiarAAgregarAmigo() throws IOException {
        App.setRoot("ventanaAmigos");
    }

    @FXML
    void cambiarABorrarUsuario() throws IOException {
        App.setRoot("ventanaBorrar");
    }

    @FXML
    void cambiarAVincularMedio() throws IOException {
        App.setRoot("ventanaPago");
    }

    @FXML
    void cambiarAPanelShouts() throws IOException {
        App.setRoot("ventanaShouts");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade= UsuariosFacade.getInstance();
        //facade.actualizar();
        botonAmigos.setDisable(true);
        botonBorrar.setDisable(true);
        botonEditar.setDisable(true);
        botonMensajes.setDisable(true);
        botonVincular.setDisable(true);
        if(control.enSesion().equals(""))
        {//No hay usuario
            botonAmigos.setDisable(true);
            botonBorrar.setDisable(true);
            botonEditar.setDisable(true);
            botonMensajes.setDisable(true);
            botonVincular.setDisable(true);

            botonCerrarSesion.setDisable(true);
        }
        else{//Hay un usuario
            if(control.enSesion().equals("admin")){
                botonBorrar.setDisable(false);
            }
            botonAmigos.setDisable(false);
            botonEditar.setDisable(false);
            botonMensajes.setDisable(false);
            botonVincular.setDisable(false);

            botonCerrarSesion.setDisable(false);
        }
    }

    @FXML
    void iniciarSesion(ActionEvent event) {
        if(facade.buscarUsuario(usuario.getText())!=null){
            if(facade.buscarUsuario(usuario.getText()).getPassword().equals(password.getText()))
            {
                cerrarSesion();
                control.OpenCloseSesion(usuario.getText());
                botonCerrarSesion.setDisable(false);
                botonAmigos.setDisable(false);
                if(facade.buscarUsuario(control.enSesion()).getUsername().equals("admin")){
                    botonBorrar.setDisable(false);
                }
                botonEditar.setDisable(false);
                botonMensajes.setDisable(false);
                botonVincular.setDisable(false);
            }else{
                crearAlerta(Alert.AlertType.WARNING, "Login","Error", "Contraseña Incorrecta");
            }
        }
        else{
            crearAlerta(Alert.AlertType.WARNING, "Login","Error", "Usuario o Inexistente");
        }

        password.setText("");
    }

    @FXML
    void cerrarSesion() {
        control.OpenCloseSesion("");
        botonAmigos.setDisable(true);
        botonBorrar.setDisable(true);
        botonEditar.setDisable(true);
        botonMensajes.setDisable(true);
        botonVincular.setDisable(true);
        botonCerrarSesion.setDisable(true);
    }

    private void crearAlerta(Alert.AlertType tipo, String titulo, String tituloInterno, String mensaje ){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(tituloInterno);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
