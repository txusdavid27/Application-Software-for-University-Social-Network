package presentacion.model;

import core.modelo.Usuario;
import javafx.beans.property.SimpleStringProperty;

/**
 * Clase Provisional actualmente no está en Uso.
 * Si se elimina n afecta funcionamiento.
 */
public class UsuarioView {
    private Usuario usuario;
    public UsuarioView(Usuario u)
    {
        this.usuario = u;
        this.username = new SimpleStringProperty(u.getUsername());

    }
    private SimpleStringProperty username;
    public String getUsername() {
        return username.getValue();
    }
    public void setUsername(SimpleStringProperty nombres) {
        this.username = nombres;
    }
}