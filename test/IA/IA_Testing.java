package IA;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Carta;
import truco.modelo.IA.EsperandoRespuestaTruco;
import truco.modelo.IA.PostEnvido;
import truco.modelo.Palo;
import truco.modelo.Truco;
import truco.modelo.envido.Envido;
import truco.modelo.envido.EnvidoNoCantado;
import truco.modelo.estadosTruco.TrucoCantado;

public class IA_Testing {

    private Truco juego;

    @Before
    public void setup() {

        juego = new Truco();
        juego.nuevoEquipo("E1");
        juego.nuevoEquipo("E2");

        juego.nuevoJugador("J1", "E1");
        juego.jugadorVsIA();
        juego.empezarJuego();
        juego.getJugador("Robotruco").setEquipo(juego.getEquipo("E2"));
    }

    @Test
    public void testIA_1() {            //TEST SUCCES RATE 100%
        juego.getMesa().nuevaRonda();
        juego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        juego.getJugador("Robotruco").levantarCarta(new Carta(2, Palo.ESPADA));
        juego.getJugador("Robotruco").levantarCarta(new Carta(5, Palo.BASTO));                                                          // IA no canta envido cuando tiene muy poco,
        juego.getJugador("Robotruco").levantarCarta(new Carta(7, Palo.ORO));                                                            // solo juega la carta mas fuerte


        Assert.assertEquals(juego.getJugador("J1"), juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(5, Palo.ESPADA);
        Assert.assertEquals(juego.getJugador("Robotruco"), juego.getMesa().getJugadorActivo());
        juego.getMesa().getIA().accionar();
        Assert.assertEquals(juego.getJugador("J1"), juego.getMesa().getJugadorActivo());
        Assert.assertEquals(juego.getMesa().getRonda().getManoEnJuego().get(juego.getJugador("Robotruco")).getPalo(), Palo.ORO);
        Assert.assertEquals(juego.getMesa().getRonda().getManoEnJuego().get(juego.getJugador("Robotruco")).getNumero(), 7);
    }

    @Test
    public void testIA_2() {                //TEST SUCCESS RATE 80%
        juego.getMesa().nuevaRonda();
        juego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        juego.getJugador("Robotruco").levantarCarta(new Carta(2, Palo.ESPADA));
        juego.getJugador("Robotruco").levantarCarta(new Carta(3, Palo.ORO));
        juego.getJugador("Robotruco").levantarCarta(new Carta(7, Palo.ORO));

        juego.getMesa().getJugadorActivo().jugarCarta(5, Palo.ESPADA);
        juego.getMesa().getIA().accionar();                     // IA tiene mucho envido, existe alta probabilidad de que cante el envido
        Assert.assertTrue(juego.getMesa().getRonda().getEnvido().getEnvidoCantado().getClass().equals(Envido.class));
        Assert.assertEquals(juego.getJugador("J1"), juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().quieroEnvido();
        juego.getMesa().resolverEnvido();
        Assert.assertEquals(2,juego.getEquipo("E1").getPuntaje());

    }

    @Test
    public void testIA_3() {                //TEST SUCCESS RATE 60%
        juego.getMesa().nuevaRonda();
        juego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        juego.getJugador("Robotruco").levantarCarta(new Carta(2, Palo.ESPADA));
        juego.getJugador("Robotruco").levantarCarta(new Carta(3, Palo.ORO));
        juego.getJugador("Robotruco").levantarCarta(new Carta(7, Palo.ORO));

        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());
        juego.getMesa().getIA().accionar();                                         // IA tiene mucho envido, existe alta probabilidad de que acepte o suba el envido.
        Assert.assertFalse(juego.getMesa().getRonda().getEnvido().getClass().equals(EnvidoNoCantado.class));
        Assert.assertEquals(juego.getJugador("J1"), juego.getMesa().getJugadorActivo());
        juego.getMesa().resolverEnvido();
        Assert.assertEquals(2,juego.getEquipo("E1").getPuntaje());

    }

    @Test
    public void testIA_4() {
        juego.getMesa().nuevaRonda();
        juego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        juego.getJugador("Robotruco").levantarCarta(new Carta(2, Palo.ESPADA));
        juego.getJugador("Robotruco").levantarCarta(new Carta(3, Palo.ORO));
        juego.getJugador("Robotruco").levantarCarta(new Carta(7, Palo.ORO));

        juego.getMesa().getJugadorActivo().jugarCarta(5, Palo.ESPADA);
        juego.getMesa().getJugadorActivo().jugarCarta(2,Palo.ESPADA);
        juego.getMesa().resolverMano();
        juego.getMesa().getIA().accionar();            //Si IA, gana la primera canta el truco
        Assert.assertTrue(juego.getMesa().getIA().getClass().equals(EsperandoRespuestaTruco.class));
        juego.getMesa().getJugadorActivo().quieroTruco();
        juego.getMesa().getIA().accionar();            //IA juega la carta mas fuerte que le quede.
        Assert.assertEquals(juego.getMesa().getRonda().getManoEnJuego().get(juego.getJugador("Robotruco")).getPalo(), Palo.ORO);
        Assert.assertEquals(juego.getMesa().getRonda().getManoEnJuego().get(juego.getJugador("Robotruco")).getNumero(), 7);

    }
}