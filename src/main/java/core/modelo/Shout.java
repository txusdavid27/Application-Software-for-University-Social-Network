package core.modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Calendar;

/**
 * Clase que permite la manipulación y verificación de "Gritos".
 */
public class Shout {
    private String autor;
    private String contenido;
    private String fecha;
    private ImageView i;

    public Shout(String autor, String contenido){
        this.autor=autor;
        this.contenido=contenido;
        Calendar c = Calendar.getInstance();
        String dia, mes, hora, minuto;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH));
        hora = Integer.toString((c.get(Calendar.HOUR_OF_DAY)));
        minuto = Integer.toString((c.get(Calendar.MINUTE)));
        this.fecha= hora+":"+minuto+" - "+dia+"/"+mes;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
