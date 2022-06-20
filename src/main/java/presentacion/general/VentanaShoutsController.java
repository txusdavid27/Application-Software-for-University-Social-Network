package presentacion.general;

import capas.facade.ShoutsFacade;
import core.ControllerModelo.ControladorUsuario;
import core.modelo.Shout;
import core.modelo.lugar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaShoutsController implements Initializable {

    ShoutsFacade facade;

    @FXML
    private TableView<Shout> panel;

    @FXML
    private TableColumn<Shout, String> autor;

    @FXML
    private Button botonShout;

    @FXML
    private TableColumn<Shout, String> contenido;

    @FXML
    private TableColumn<Shout, String> fecha;

    @FXML
    private Label seleccionado;

    @FXML
    private TextArea shout;

    ControladorUsuario control= new ControladorUsuario();

    @FXML
    void hacerShout(ActionEvent event) {
        if(shout.getText()!=null && !shout.getText().equals("")){
            facade.gritar(control.enSesion(), shout.getText());
            crearAlerta(Alert.AlertType.CONFIRMATION ,"Shouts","Tu mensaje ha sido publicado", "Shout exitoso");
            renderVentana();
        }
        else{
            crearAlerta(Alert.AlertType.WARNING ,"Shouts","Tu mensaje NO ha sido publicado", "Shout sin contenido");
        }
    }

    @FXML
    private void volver() throws IOException {
        App.setRoot("principal");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade= ShoutsFacade.getInstance();
        botonShout.setDisable(true);
        renderVentana();
        if(control.enSesion().equals("")){
            seleccionado.setText("Inivitado");
        }
        else{
            seleccionado.setText(control.enSesion());
            botonShout.setDisable(false);
        }

    }

    public void renderVentana()
    {
        limpiarVentana();
        panel.getItems().addAll(facade.leer());
    }

    /**
     * Permite limpiar los datos escritos en todos los paneles.
     * @obj actualizarLugar: botón que permite el evento ActualizarLugar
     * @obj IDLugar: label con el ID del lugar.
     * @obj NombreLugar: Label con el Nombre del Lugar
     */
    public void limpiarVentana()
    {
        panel.getItems().clear();
        seleccionado.setText("");
    }

    private void crearAlerta(Alert.AlertType tipo, String titulo, String tituloInterno, String mensaje ){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(tituloInterno);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
