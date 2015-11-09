package truco.tests.unitarios;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Jugador;
import truco.modelo.Master;
import truco.modelo.excepciones.ListaJugadoresVaciaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eze Cruz Avila on 07/11/2015.
 */
public class MasterTest {

    private Master master;
    private Jugador jugadorJuan;
    private Jugador jugadorPepe;

    @Before
    public void setup(){
        master=new Master();
        jugadorJuan=new Jugador("Pepe");
        jugadorPepe=new Jugador("Juan");
        List<Jugador> jugadores=new ArrayList<>();
        jugadores.add(jugadorJuan);
        jugadores.add(jugadorPepe);
        master.setJugadores(jugadores);
    }

    @Test
    public void testListaJugadores(){
        Assert.assertFalse(master.getJugadores()==null);
    }

    @Test
    public void testJugadorManoAlComenzar(){
        Assert.assertEquals(jugadorJuan,master.getJugadorMano());
    }

    @Test
    public void testJugadorPieAlComenzar(){
        Assert.assertEquals(jugadorPepe,master.getJugadorPie());
    }

    @Test
    public void testActualizarManoPieNoLanzaExcepcion(){
        master.actualizarJugadorManoPie();
        master.actualizarJugadorManoPie();
        master.actualizarJugadorManoPie();
        Assert.assertTrue(true);
    }

    @Test
    public void testActualizarManoPie(){
        Assert.assertEquals(jugadorJuan,master.getJugadorMano());
        Assert.assertEquals(jugadorPepe,master.getJugadorPie());
        master.actualizarJugadorManoPie();
        Assert.assertEquals(jugadorPepe,master.getJugadorMano());
        Assert.assertEquals(jugadorJuan,master.getJugadorPie());

    }

    @Test(expected = ListaJugadoresVaciaException.class)
    public void testActualizarManoPieConListaVacia(){
        List<Jugador> lista=new ArrayList<>();
        master.setJugadores(lista);
        master.actualizarJugadorManoPie();
    }

    @Test(expected = ListaJugadoresVaciaException.class)
    public void testSetJugadoresVaciaLanzaExcepcionSiEstaVacia(){
        List<Jugador> listaPrueba=new ArrayList<>();
        master.setJugadores(listaPrueba);
    }
}
