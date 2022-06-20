package SEM_4.PROYECTO;

import capas.facade.UsuariosFacade;
import capas.negocio.ControlAfoCa;
import core.ControllerModelo.ControladorUsuario;
import core.modelo.Chat;
import core.modelo.Usuario;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void pruebaControlAfoCa(){
        ControlAfoCa control= new ControlAfoCa();
        control.leerDatos();
        System.out.println("Prueba Borrado de Lugar");
        boolean result=control.borrarlugar(100);
        assertEquals(false,result);
    }

    @Test
    public void pruebaConteo(){
        ControlAfoCa control= new ControlAfoCa();
        control.leerDatos();
        int e=100;
        int result=control.ContarTotal();
        assertEquals(e,result);
    }

    @Test
    public void agregarAmigo(){
        ControladorUsuario control= new ControladorUsuario();
        boolean e=false;
        assertEquals(false,control.nuevoAmigo("123"));
    }

    @Test
    public void verUsuarioEnSesion(){
        ControladorUsuario control= new ControladorUsuario();
        control.OpenCloseSesion("Juan");
        assertEquals("Juan",control.enSesion());
    }

    @Test
    public void cerrarSesion(){
        ControladorUsuario control= new ControladorUsuario();
        control.OpenCloseSesion("");
        assertEquals("",control.enSesion());
    }

    @Test
    public void usuarioExistente(){
        Usuario user=new Usuario("abc","abc");
        UsuariosFacade facade= UsuariosFacade.getInstance();
        facade.crearUsuario(user);

        assertEquals(facade.buscarUsuario("abc"),user);
    }

    @Test
    public void eliminarUsuario(){
        Usuario user=new Usuario("abc","abc");
        UsuariosFacade facade= UsuariosFacade.getInstance();
        facade.crearUsuario(user);
        facade.eliminarUsuario(user);
        assertNotEquals(user,facade.buscarUsuario(user.getUsername()));
    }

    @Test
    public void leerChat(){
        ControladorUsuario control= new ControladorUsuario();
        Usuario user=new Usuario("abc","abc");
        control.OpenCloseSesion(user.getUsername());
        UsuariosFacade facade= UsuariosFacade.getInstance();
        Chat chat=new Chat("user2","hola");
        facade.buscarUsuario(control.enSesion()).getChats().add(chat);
        assertEquals(true,facade.buscarUsuario(control.enSesion()).buscarChat("user2").isVisto());
    }
}
