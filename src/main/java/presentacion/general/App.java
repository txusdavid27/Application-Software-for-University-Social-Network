package presentacion.general;

import capas.facade.UsuariosFacade;
import core.ControllerModelo.ControladorUsuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private final static String MAIN_FXML_NAME = "principal.fxml";
    private static ControladorUsuario control= new ControladorUsuario();
    private static UsuariosFacade facade;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principal"));
        stage.initStyle(StageStyle.UNDECORATED );
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args)
    {
        facade= UsuariosFacade.getInstance();
        facade.actualizar();
        launch();
        System.out.println("Fin del Programa");
        control.OpenCloseSesion("");
        System.out.println("Cerrando Sesiones");
    }
}