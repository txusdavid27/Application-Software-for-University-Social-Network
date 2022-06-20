package presentacion.general;

import capas.facade.UsuariosFacade;
import core.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class IngresoController implements Initializable {

    UsuariosFacade facade;

    @FXML
    private Button cancelarButton;

    @FXML
    private ChoiceBox<String> carreraBox;

    @FXML
    private TextField correoTextField;

    @FXML
    private Button registrarButton;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField password;

    @FXML
    private void volver() throws IOException {
        facade.guardarInfo();
        App.setRoot("principal");
    }

    @FXML
    void cargarCarreras(){
        carreraBox.getItems().add("Arquitectura");
        carreraBox.getItems().add("Administracion de Empresas");
        carreraBox.getItems().add("Artes");
        carreraBox.getItems().add("Biologia");
        carreraBox.getItems().add("Ciencias");
        carreraBox.getItems().add("Comunicacion Social");
        carreraBox.getItems().add("Contaduria");
        carreraBox.getItems().add("Derecho");
        carreraBox.getItems().add("Diseño");
        carreraBox.getItems().add("Economia");
        carreraBox.getItems().add("Filosofia");
        carreraBox.getItems().add("Ingenieria");
        carreraBox.getItems().add("Licenciatura");
        carreraBox.getItems().add("Medicina");
    }

    /*@FXML
    void cancelarButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();

    }*/

    @FXML
    void registrarButtonOnAction(ActionEvent event) throws IOException {
        if((facade.buscarUsuario(correoTextField.getText())!=null) ||
                (facade.buscarUsuario(usernameTextField.getText())!=null) ||
                !(correoTextField.getText().contains("@javeriana.edu.co"))
        ){
            correoTextField.setText("");
            usernameTextField.setText("");
            crearAlerta(Alert.AlertType.WARNING, "Login","Error", "Usuario Existente o correo NO javeriano");
        }
        else
        {
            Usuario nuevoUsuario = new Usuario(correoTextField.getText(), usernameTextField.getText());
            nuevoUsuario.setTelefono( Long.parseLong(telefonoTextField.getText()));
            nuevoUsuario.setCarrera(carreraBox.getValue());
            nuevoUsuario.setPassword(password.getText());
            facade.crearUsuario(nuevoUsuario);
            volver();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facade=UsuariosFacade.getInstance();
        cargarCarreras();

    }

    private void crearAlerta(Alert.AlertType tipo, String titulo, String tituloInterno, String mensaje ){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(tituloInterno);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

