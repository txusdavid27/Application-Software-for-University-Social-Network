package capas.negocio;

import capas.facade.UsuariosFacade;
import core.ControllerModelo.ControladorUsuario;
import core.enums.Tipo;
import junit.framework.TestCase;

public class GestionUsuariosTest extends TestCase {
    UsuariosFacade facade;

    public void testBuscarUsuario() {
        facade= UsuariosFacade.getInstance();
        ControladorUsuario users= new ControladorUsuario();
        facade.actualizar();
        facade=UsuariosFacade.getInstance();
        facade.buscarUsuario("admin");
        assertEquals(Tipo.ADMIN,facade.buscarUsuario("admin").getTipo());
    }

    public void testVerUsuarios() {

    }
}