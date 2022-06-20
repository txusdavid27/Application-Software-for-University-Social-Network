/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.general;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import capas.facade.UsuariosFacade;
import core.ControllerModelo.ControladorUsuario;
import core.modelo.Chat;
import core.modelo.Usuario;

import core.modelo.lugar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


public class VentanaAmigosController implements Initializable {

    @FXML
    private Button botonAgregar;

    @FXML
    private Button botonELiminar;

    @FXML
    private ListView<String> listaUsers;

    @FXML
    private ListView<String> misAmigos;

    @FXML
    private Label seleccionado;



    @FXML
    void amigoSeleccionado(MouseEvent event) {
        botonAgregar.setDisable(true);
        botonELiminar.setDisable(false);
    }



    @FXML
    void nuevoSeleccionado(MouseEvent event) {
        botonAgregar.setDisable(false);
        botonELiminar.setDisable(true);
    }

    @FXML
    private void volver() throws IOException {
        facade.guardarInfo();
        App.setRoot("principal");
    }

    /**
     * Initializes the controller class.
     */
    UsuariosFacade facade;
    ControladorUsuario control = new ControladorUsuario();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade= UsuariosFacade.getInstance();
        renderVentana();
        botonAgregar.setDisable(false);
        botonELiminar.setDisable(false);

    }

    @FXML
    private void eliminar_amigo(ActionEvent event) throws IOException {
        String aux = misAmigos.getSelectionModel().getSelectedItem();

        facade.buscarUsuario(control.enSesion()).getAmigos().remove(facade.buscarUsuario(aux));
            crearAlerta(Alert.AlertType.CONFIRMATION , "Amigo","Correcto", "Eliminado");

        botonELiminar.setDisable(true);
        //volver();
        renderVentana();
    }

    @FXML
    private void agregar_amigo(ActionEvent event) throws IOException {
        String aux = listaUsers.getSelectionModel().getSelectedItem();
        System.out.println(aux);
            if(!aux.equals(control.enSesion())  &&
                    !facade.buscarUsuario(control.enSesion()).getAmigos().contains(facade.buscarUsuario(aux))    ){
                facade.buscarUsuario(control.enSesion()).getAmigos().add(facade.buscarUsuario(aux));
                Chat chat=new Chat(aux,"");
                Chat chatA= new Chat(control.enSesion(),"");
                facade.buscarUsuario(control.enSesion()).getChats().add(chat);
                facade.buscarUsuario(aux).getChats().add(chatA);
                crearAlerta(Alert.AlertType.CONFIRMATION , "Amigo","Correcto", "Agregado");
            }else{
                crearAlerta(Alert.AlertType.WARNING, "Id","Error", "Este usuario no se puede agregar");
            }
        botonAgregar.setDisable(true);
        renderVentana();
            //volver();
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
       for(Usuario us:  facade.verUsuarios() )
        {
            listaUsers.getItems().add(us.getUsername());
        }

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
        listaUsers.getItems().clear();
        misAmigos.getItems().clear();
    }


}