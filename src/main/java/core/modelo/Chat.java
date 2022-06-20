package core.modelo;

/**
 * Clase Chat que permite la manipulación y verificación de pasos en la comunicacion.
 */
public class Chat {
    private String emisor;
    private String mensaje;

    /**
     * Permite saber si un mensaje nuevo ha sido leído o no.
     */
    private boolean visto;

    public Chat(String emisor, String mensaje){
        this.emisor=emisor;
        this.mensaje=mensaje;
        this.visto=true;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }
}
