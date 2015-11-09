package truco.tests.unitarios;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.CircularList;
import truco.modelo.Jugador;
import truco.modelo.Master;
import truco.modelo.excepciones.ListaJugadoresVaciaException;

/**
 * Created by Eze Cruz Avila on 07/11/2015.
 */
public class MasterTest {

    private Master master;

    @Before
    public void setup(){
        master=new Master();
        Jugador jugador1=new Jugador("Pepe");
        Jugador jugador2=new Jugador("Juan");
        CircularList<Jugador> jugadores=new CircularList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        master.setJugadores(jugadores);
    }

    @Test
    public void testListaJugadores(){
        Assert.assertFalse(master.getJugadores()==null);
    }

    @Test
    public void testActualizarManoPieLegal(){

        master=new Master();
        Jugador jugador1=new Jugador("Pepe");
        Jugador jugador2=new Jugador("Juan");
        CircularList<Jugador> jugadores=new CircularList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        master.setJugadores(jugadores);
        master.actualizarJugadorManoPie();
        //si se actualiza una vez mas falla


        Assert.assertTrue(master.jugadorEsPie(jugador2));
    }

    @Test(expected = ListaJugadoresVaciaException.class)
    public void testActualizarManoPieSinJugadoresLanzaExcepcionSiJugadoresEstaVacio(){
        CircularList<Jugador> lista=new CircularList<>();
        master.setJugadores(lista);
        master.actualizarJugadorManoPie();
    }

    @Test(expected = ListaJugadoresVaciaException.class)
    public void testSetJugadoresVaciaLanzaExcepcionSiEstaVacia(){
        CircularList<Jugador> listaPrueba=new CircularList<>();
        master.setJugadores(listaPrueba);
    }
}
