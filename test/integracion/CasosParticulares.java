package integracion;

import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;
import truco.modelo.envido.Envido;
import truco.modelo.envido.FaltaEnvido;
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

    @Test(expected = CartaNoEncontradaException.class)
    public void testJugarUnaCartaQueNoEstaEnLaMano(){
        mesaTester.nuevaRonda();
        jugadorMano.jugarCarta(10, Palo.BASTO);
    }

    @Test(expected = JugadorNoHabilitadoParaCantarTanto.class)
    public void testJugadorNoPuedeCantarTanto(){
        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(4, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5, Palo.ESPADA));
        jugadorMano.jugarCarta(4,Palo.ESPADA);
        jugadorPie.jugarCarta(5,Palo.ESPADA);
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

        jugadorPie.jugarCarta(1,Palo.BASTO);
        jugadorMano.jugarCarta(2,Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.cantarTruco();
    }

    @Test(expected = JugadorNoTieneFlorException.class)
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

    @Test(expected = EnvidoEnvidoYaCantadoException.class)
    public void testSeIntentaCantarEnvido3Veces(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        jugadorPie.cantarEnvido(new Envido());
        jugadorMano.aceptarEnvido();
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
    }

    @Test(expected = SoloSePuedeCantarFaltaEnvidoDespuesDeRealEnvidoException.class)
    public void testSeIntentaCantarEnvidoDespuesDeRealEnvido(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarEnvido(new RealEnvido());
        jugadorPie.aceptarEnvido();
        jugadorPie.cantarEnvido(new Envido());
        jugadorMano.aceptarEnvido();
    }

    @Test(expected = FlorSeCantaEnLaPrimeraException.class)
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

    @Test(expected = FlorSeCantaEnLaPrimeraException.class)
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

    @Test(expected = FlorYaCantadaException.class)
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

    @Test(expected = FlorNoCantadaException.class)
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

    @Test(expected = FaltaEnvidoYaCantadoException.class)
    public void testSeIntentaCantarFaltaEnvido2VecesDespuesDeRealEnvido(){
        mesaTester.nuevaRonda();

        jugadorMano.cantarEnvido(new RealEnvido());
        jugadorPie.aceptarEnvido();
        jugadorPie.cantarEnvido(new FaltaEnvido());
        jugadorMano.aceptarEnvido();
        jugadorMano.cantarEnvido(new FaltaEnvido());
        jugadorPie.aceptarEnvido();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarRetruco2Veces(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarRetruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarTruco2Veces(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarTruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarValeCuatro2Veces(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarValeCuatro();
        jugadorPie.quieroTruco();
        jugadorPie.cantarValeCuatro();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarValeCuatroConValeCuatroCantado(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarValeCuatro();
        jugadorPie.cantarValeCuatro();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarValeCuatroConRetrucoCantado(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.cantarValeCuatro();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarValeCuatroConTrucoCantado(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.cantarValeCuatro();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarRetrucoConRetrucoCantado(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.cantarRetruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarTrucoConTrucoCantado(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.cantarTruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarTrucoDespuesDelRetruco(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarTruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarRetrucoConTrucoCantado(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.cantarRetruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeQuiereDosVecesElValeCuatro(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarValeCuatro();
        jugadorPie.quieroTruco();
        jugadorPie.quieroTruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeQuiereDosVecesElRetruco(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarRetruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeQuiereDosVecesElTruco(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.quieroTruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarTrucoConRetrucoCantado(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.cantarTruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarRetrucoDespuesDelValeCuatro(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarValeCuatro();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarRetrucoDespuesDelValeCuatroCantado(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarValeCuatro();
        jugadorPie.cantarRetruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarTrucoDespuesDelValeCuatroCantado(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarValeCuatro();
        jugadorPie.cantarTruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeIntentaCantarTrucoDespuesDelValeCuatro(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarValeCuatro();
        jugadorPie.quieroTruco();
        jugadorPie.cantarTruco();
    }

    @Test(expected = NoSePuedeCantarAhoraException.class)
    public void testSeQuiere2VecesElRetruco(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.quieroTruco();
    }

    @Test
    public void testElEnvidoEstaPrimero(){
        mesaTester.nuevaRonda();

        jugadorMano.cantarTruco();
        jugadorPie.cantarEnvido(new Envido());
    }


}
