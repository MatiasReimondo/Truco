package simulaciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Carta;
import truco.modelo.Palo;
import truco.modelo.Truco;
import truco.modelo.envido.Envido;

public class PartidaEntre4Jugadores {

    private Truco juego;

    @Before
    public void setup(){

        juego=new Truco();
        juego.nuevoEquipo("E1");
        juego.nuevoEquipo("E2");

        juego.nuevoJugador("J1","E1");
        juego.nuevoJugador("J2","E2");
        juego.nuevoJugador("J3","E1");
        juego.nuevoJugador("J4","E2");
    }

    @Test
    public void PartidaCompletaEntre4Jugadores(){
        juego.empezarJuego();

        /**RONDA 1**/
        juego.getMesa().nuevaRonda();

        juego.getJugador("J1").levantarCarta(new Carta(3, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        juego.getJugador("J2").levantarCarta(new Carta(2, Palo.ESPADA));
        juego.getJugador("J2").levantarCarta(new Carta(5, Palo.BASTO));
        juego.getJugador("J2").levantarCarta(new Carta(7, Palo.ORO));

        juego.getJugador("J3").levantarCarta(new Carta(6,Palo.ESPADA));
        juego.getJugador("J3").levantarCarta(new Carta(4,Palo.ESPADA));
        juego.getJugador("J3").levantarCarta(new Carta(3,Palo.COPA));

        juego.getJugador("J4").levantarCarta(new Carta(10,Palo.ORO));
        juego.getJugador("J4").levantarCarta(new Carta(4,Palo.COPA));
        juego.getJugador("J4").levantarCarta(new Carta(7,Palo.COPA));


        juego.getMesa().getJugadorActivo().jugarCarta(7,Palo.ESPADA);       Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(5,Palo.BASTO);        Assert.assertEquals(juego.getJugador("J3"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());      Assert.assertEquals(juego.getJugador("J4"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().aceptarEnvido();

        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());      Assert.assertEquals(juego.getJugador("J3"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().aceptarEnvido();
        juego.getMesa().resolverEnvido();
        Assert.assertEquals(4,juego.getEquipo("E2").getPuntaje());

        juego.getMesa().getJugadorActivo().jugarCarta(4,Palo.ESPADA);       Assert.assertEquals(juego.getJugador("J4"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(4,Palo.COPA);         Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().resolverMano();

        juego.getMesa().getJugadorActivo().cantarTruco();                   Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().quieroTruco();
        juego.getMesa().getJugadorActivo().cantarRetruco();                 Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().quieroTruco();
        juego.getMesa().getJugadorActivo().jugarCarta(3,Palo.ESPADA);       Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(7,Palo.ORO);          Assert.assertEquals(juego.getJugador("J3"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(3,Palo.COPA);         Assert.assertEquals(juego.getJugador("J4"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(10,Palo.ORO);         Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().resolverMano();

        Assert.assertFalse(juego.getMesa().getRonda().termino());


        juego.getMesa().getJugadorActivo().jugarCarta(2,Palo.ESPADA);
        juego.getMesa().getJugadorActivo().jugarCarta(6,Palo.ESPADA);
        juego.getMesa().getJugadorActivo().jugarCarta(7,Palo.COPA);
        juego.getMesa().getJugadorActivo().jugarCarta(10,Palo.COPA);
        juego.getMesa().resolverMano();
        Assert.assertTrue(juego.getMesa().getRonda().termino());
        Assert.assertEquals(7,juego.getEquipo("E2").getPuntaje());

        /**RONDA 2**/
        juego.getMesa().nuevaRonda();
        Assert.assertTrue(juego.getMesa().getArbitro().jugadorEsMano(juego.getJugador("J2")));
        Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J2"));
    }
}
