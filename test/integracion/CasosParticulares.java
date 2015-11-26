package integracion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;
import truco.modelo.envido.Envido;
import truco.modelo.excepciones.CartaNoEstaEnLaManoException;
import truco.modelo.excepciones.JugadorNoHabilitadoParaCantarTanto;
import truco.modelo.excepciones.RondaTerminadaException;

import java.util.ArrayList;
import java.util.List;

public class CasosParticulares {
    private Mesa mesaTester;
    private Equipo equipoMano;
    private Equipo equipoPie;
    private Jugador jugadorMano;
    private Jugador jugadorPie;
    private List<Jugador> listaJugadores;

    @Before
    public void setup(){
        mesaTester =new Mesa();

        equipoMano =new Equipo("EquipoPepe");
        equipoPie =new Equipo("EquipoJuan");

        jugadorPie =new Jugador("Juan");
        jugadorMano =new Jugador("Pepe");

        equipoMano.agregarIntegrante(jugadorMano);
        equipoPie.agregarIntegrante(jugadorPie);

        jugadorPie.setEquipo(equipoPie);
        jugadorMano.setEquipo(equipoMano);

        listaJugadores=new ArrayList<>();
        listaJugadores.add(jugadorMano);
        listaJugadores.add(jugadorPie);

        jugadorPie.setMesa(mesaTester);
        jugadorMano.setMesa(mesaTester);

        mesaTester.setJugadores(listaJugadores);
    }

    @Test(expected = CartaNoEstaEnLaManoException.class)
    public void testJugarUnaCartaQueNoEstaEnLaMano(){
        mesaTester.nuevaRonda();
        jugadorMano.jugarCarta(10, Palo.BASTO);
    }

    @Test(expected = JugadorNoHabilitadoParaCantarTanto.class)
    public void testJugadorNoPuedeCantarTanto(){
        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(4, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5, Palo.ESPADA));
        mesaTester.resolverMano();
        jugadorMano.cantarEnvido(new Envido());
    }

    @Test(expected = RondaTerminadaException.class)
    public void testIntentarJugarDespuesDeTerminadaLaRonda(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(2, Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(1,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.jugarCarta(4,Palo.BASTO);
        jugadorPie.jugarCarta(3,Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(2,Palo.BASTO);
        jugadorPie.jugarCarta(1,Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.cantarTruco();
    }
}
