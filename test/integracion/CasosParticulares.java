package integracion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;
import truco.modelo.envido.Envido;
import truco.modelo.envido.RealEnvido;
import truco.modelo.excepciones.*;

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

    @Test(expected = NoSePuedeQuererSinTenerFlorException.class)
    public void testQuererFlorSinTener(){
        mesaTester.nuevaRonda();
        mesaTester.getArbitro().florHabilitada();
        jugadorMano.levantarCarta(new Carta(7,Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(5,Palo.ESPADA));

        jugadorPie.levantarCarta(new Carta(1,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(10, Palo.ORO));

        jugadorMano.cantarFlor();
        jugadorPie.quieroFlor();
    }

    @Test(expected = CantidadDeJugadoresDebeSerNumeroParException.class)
    public void testSeIntentaJugadorCon3Jugadores(){
        listaJugadores.add(new Jugador("Ricardito"));
        mesaTester.setJugadores(listaJugadores);
    }

    @Test(expected = CantidadDeJugadoresInsuficienteException.class)
    public void testSeIntentaJugarCon1Jugador(){
        listaJugadores.remove(0);
        mesaTester.setJugadores(listaJugadores);
    }

    @Test(expected = MaximoDeJugadoresExcedidoException.class)
    public void testSeIntentaJugadorCon7Jugadores(){
        for(int i=0;i<8;i++)
            listaJugadores.add(new Jugador("Pepe"));
        mesaTester.setJugadores(listaJugadores);
    }

    @Test(expected = NoSePuedeCantarMasDeDosVecesEnvidoException.class)
    public void testSeIntentaCantarEnvido3Veces(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.quieroEnvido();
        jugadorPie.cantarEnvido(new Envido());
        jugadorMano.quieroEnvido();
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.quieroEnvido();
    }

    @Test(expected = NoSePuedeCantarEnvidoDespuesDeRealEnvidoException.class)
    public void testSeIntentaCantarEnvidoDespuesDeRealEnvido(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarEnvido(new RealEnvido());
        jugadorPie.quieroEnvido();
        jugadorPie.cantarEnvido(new Envido());
        jugadorMano.quieroEnvido();
    }

    @Test(expected = FlorSoloSeCantaEnLaPrimeraException.class)
    public void testSeIntentaCantarFlorEnLaSegundaMano(){
        mesaTester.nuevaRonda();
        mesaTester.getArbitro().florHabilitada();
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));

        jugadorMano.jugarCarta(5,Palo.BASTO);
        jugadorPie.jugarCarta(5,Palo.ESPADA);
        mesaTester.resolverMano();
        jugadorMano.cantarFlor();
    }

    @Test(expected = FlorSoloSeCantaEnLaPrimeraException.class)
    public void testSeIntentaCantarContraFlorEnLaSegundaMano(){
        mesaTester.nuevaRonda();
        mesaTester.getArbitro().florHabilitada();
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));

        jugadorMano.jugarCarta(5,Palo.BASTO);
        jugadorPie.jugarCarta(5,Palo.ESPADA);
        mesaTester.resolverMano();
        jugadorMano.cantarContraFlor();
    }

    @Test(expected = NoEsTurnoDelJugadorException.class)
    public void testJugadorCantaFlorPeroNoEsSuTurno(){
        mesaTester.nuevaRonda();
        mesaTester.getArbitro().florHabilitada();
        jugadorPie.cantarFlor();
    }
    @Test(expected = SoloSePuedeCantarFlorUnaVezException.class)
    public void testSeIntentaCantarFlor2Veces(){
        mesaTester.nuevaRonda();
        mesaTester.getArbitro().florHabilitada();

        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(7,Palo.ESPADA));

        jugadorMano.cantarFlor();
        jugadorPie.quieroFlor();
        jugadorPie.cantarFlor();
    }

    @Test(expected = ContraFlorSoloSePuedeCantarDespuesDeFlorException.class)
    public void testSeIntentaCantarContraflorAntesDeFlor(){
        mesaTester.nuevaRonda();
        mesaTester.getArbitro().florHabilitada();

        jugadorMano.cantarContraFlor();
    }

    @Test(expected = RondaTerminadaException.class)
    public void testSeIntentaJugarDespuesDeTerminadaLaRonda(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(7,Palo.ESPADA));

        jugadorMano.jugarCarta(7,Palo.BASTO);
        jugadorPie.jugarCarta(6,Palo.ESPADA);
        mesaTester.resolverMano();
        jugadorMano.jugarCarta(6,Palo.BASTO);
        jugadorPie.jugarCarta(5,Palo.ESPADA);
        mesaTester.resolverMano();
        jugadorMano.jugarCarta(5,Palo.BASTO);

    }

    @Test(expected = RondaTerminadaException.class)
    public void testResolverManoDespuesDeTerminadaLaRonda(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(7,Palo.ESPADA));

        jugadorMano.jugarCarta(7,Palo.BASTO);
        jugadorPie.jugarCarta(6,Palo.ESPADA);
        mesaTester.resolverMano();
        jugadorMano.jugarCarta(6,Palo.BASTO);
        jugadorPie.jugarCarta(5,Palo.ESPADA);
        mesaTester.resolverMano();
        mesaTester.resolverMano();
    }
}
