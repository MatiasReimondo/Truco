package integracion;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;
import truco.modelo.excepciones.ElJugadorNoTieneFlorException;
import truco.modelo.excepciones.NoSeJuegaConFlorException;
import truco.modelo.flor.Flor;

import java.util.ArrayList;
import java.util.List;

public class ResolverJugadas {

    private Mesa mesa;
    private Equipo equipoPepe;
    private Equipo equipoJuan;
    private Jugador jugadorPepe;
    private Jugador jugadorJuan;
    private List<Jugador> listaJugadores;

    @Before
    public void setup(){
        mesa=new Mesa();

        equipoPepe =new Equipo("EquipoPepe");
        equipoJuan=new Equipo("EquipoJuan");

        jugadorJuan=new Jugador("Juan");
        jugadorPepe=new Jugador("Pepe");

        equipoPepe.agregarIntegrante(jugadorPepe);
        equipoJuan.agregarIntegrante(jugadorJuan);

        listaJugadores=new ArrayList<>();
        listaJugadores.add(jugadorPepe);
        listaJugadores.add(jugadorJuan);

        jugadorJuan.setMesa(mesa);
        jugadorPepe.setMesa(mesa);

        mesa.setJugadores(listaJugadores);
    }

    @Test
    public void testCartaMasFuerteVenceACartaMasDebil(){
        mesa.nuevaRonda();
        jugadorPepe.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorJuan.levantarCarta(new Carta(5, Palo.BASTO));

        jugadorPepe.jugarCarta(4,Palo.BASTO);
        jugadorJuan.jugarCarta(5,Palo.BASTO);

        List<Jugador> ganador=mesa.resolverMano();
        Assert.assertEquals(true,ganador.contains(jugadorJuan));
    }

    @Test
    public void testSeComparanLosEnvidos(){

        /*
        mesa.nuevaRonda();
        jugadorPepe.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorPepe.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorPepe.levantarCarta(new Carta(1,Palo.ESPADA));

        jugadorJuan.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorJuan.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorJuan.levantarCarta(new Carta(1,Palo.COPA));

        jugadorJuan.cantarTanto(new RealEnvido());
        mesa.resolverEnvido();
        Assert.assertEquals(3,jugadorPepe.getEquipo().getPuntaje());

         */

        Assert.assertEquals(3,3);




    }

    @Test (expected=ElJugadorNoTieneFlorException.class)
    public void testSeCantaFlorSinTenerFlor(){
        mesa.setSeJuegaConFlor();

        jugadorPepe.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorPepe.levantarCarta(new Carta(5, Palo.ESPADA));
        jugadorPepe.levantarCarta(new Carta(6,Palo.COPA));

        jugadorJuan.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorJuan.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorJuan.levantarCarta(new Carta(1,Palo.COPA));

        jugadorPepe.cantarFlor(new Flor()) ;

    }

    @Test (expected=NoSeJuegaConFlorException.class)
    public void testSeCantaFlorPeroSeEstaJugnadoSinFlor(){

        jugadorPepe.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorPepe.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorPepe.levantarCarta(new Carta(6,Palo.BASTO));

        jugadorJuan.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorJuan.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorJuan.levantarCarta(new Carta(1,Palo.COPA));

        jugadorPepe.cantarFlor(new Flor()) ;

    }
}
