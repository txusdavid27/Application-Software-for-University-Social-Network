/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.general;

import capas.facade.UsuariosFacade;
import core.ControllerModelo.ControladorUsuario;
import core.modelo.Chat;
import core.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class VentanaChatsController implements Initializable {

    @FXML
    private Button botonAgregar;

    @FXML
    private Button botonELiminar;

    @FXML
    private Button botonEnviar;

    @FXML
    private TextArea mensajes;

    @FXML
    private TextArea miMensaje;

    @FXML
    private ListView<String> misAmigos;

    @FXML
    private Label seleccionado;

    /**
     * Initializes the controller class.
     */
    UsuariosFacade facade;
    ControladorUsuario control = new ControladorUsuario();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade= UsuariosFacade.getInstance();
        renderVentana();
        botonEnviar.setDisable(true);

        for(Chat chats: facade.buscarUsuario(control.enSesion()).getChats())
        {
            if(chats.isVisto()==false){
                if(!(facade.buscarUsuario(control.enSesion()).getAmigos().contains(facade.buscarUsuario(chats.getEmisor())))){
                    crearAlerta(Alert.AlertType.INFORMATION ,"Mensaje nuevo", chats.getEmisor()+" quiere comunicarse contigo", "Para comunicarte con él, Agregalo como amigo primero");
                }
                else{
                    crearAlerta(Alert.AlertType.CONFIRMATION,"Mensaje nuevo","Tienes un mensaje nuevo de:",chats.getEmisor());
                }
            }
        }


    }


    @FXML
    void amigoSeleccionado() {
        botonEnviar.setDisable(false);
        seleccionado.setText(misAmigos.getSelectionModel().getSelectedItem());
        mensajes.setText(facade.buscarUsuario(control.enSesion()).buscarChat(misAmigos.getSelectionModel().getSelectedItem()).getMensaje());
        System.out.println("Mensaje de :"+ facade.buscarUsuario(control.enSesion()).buscarChat(misAmigos.getSelectionModel().getSelectedItem()).getEmisor() );
        facade.buscarUsuario(control.enSesion()).buscarChat(misAmigos.getSelectionModel().getSelectedItem()).setVisto(true);
    }

    /**
     * Permite la entrada de datos en el chat de un usuario seleccionado por el usuario en Sesion.
     * @param event
     */
    @FXML
    void enviar(ActionEvent event) {

        String aux,aux2;
        aux=facade.buscarUsuario(misAmigos.getSelectionModel().getSelectedItem()).buscarChat(control.enSesion()).getMensaje();
        aux2=facade.buscarUsuario(control.enSesion()).buscarChat(misAmigos.getSelectionModel().getSelectedItem()).getMensaje();
        String jump;
        jump="\n**************";
        facade.buscarUsuario(misAmigos.getSelectionModel().getSelectedItem()).buscarChat(control.enSesion()).setMensaje(aux+"\n"+miMensaje.getText());

        facade.buscarUsuario(control.enSesion()).buscarChat(misAmigos.getSelectionModel().getSelectedItem()).setMensaje(aux2+jump+miMensaje.getText());

        facade.buscarUsuario(misAmigos.getSelectionModel().getSelectedItem()).buscarChat(control.enSesion()).setVisto(false);
        crearAlerta(Alert.AlertType.CONFIRMATION,"Correcto","Confirmando envío","Enviando...");

        amigoSeleccionado();

    }

    @FXML
    private void volver() throws IOException {
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

    public void renderVentana()
    {
        limpiarVentana();
        seleccionado.setText(control.enSesion());

       if(!facade.buscarUsuario(control.enSesion()).getAmigos().isEmpty()){
           for(Usuario usu : facade.buscarUsuario(control.enSesion()).getAmigos() ){
               misAmigos.getItems().add(usu.getUsername());
           }
       }
        //Aqui me vota nulo porque puede que no tenga ni un amigo
    }

    /**
     * Permite limpiar los datos escritos en todos los paneles.
     * @obj actualizarLugar: botón que permite el evento ActualizarLugar
     * @obj IDLugar: label con el ID del lugar.
     * @obj NombreLugar: Label con el Nombre del Lugar
     */
    public void limpiarVentana()
    {
        seleccionado.setText("");
        misAmigos.getItems().clear();
    }


}