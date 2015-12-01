package simulaciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Carta;
import truco.modelo.Palo;
import truco.modelo.Truco;
import truco.modelo.envido.Envido;
import truco.modelo.envido.RealEnvido;

public class PartidaEntre4Jugadores {

    private Truco truco;

    @Before
    public void setup(){

        truco =new Truco();
        truco.nuevoEquipo("E1");
        truco.nuevoEquipo("E2");

        truco.nuevoJugador("J1", "E1");
        truco.nuevoJugador("J2", "E2");
        truco.nuevoJugador("J3", "E1");
        truco.nuevoJugador("J4", "E2");
    }

    @Test
    public void PartidaCompletaEntre4Jugadores(){
        truco.empezarJuego();

        /**RONDA 1**/
        truco.getMesa().nuevaRonda();

        truco.getJugador("J1").levantarCarta(new Carta(3, Palo.ESPADA));
        truco.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        truco.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        truco.getJugador("J2").levantarCarta(new Carta(2, Palo.ESPADA));
        truco.getJugador("J2").levantarCarta(new Carta(5, Palo.BASTO));
        truco.getJugador("J2").levantarCarta(new Carta(7, Palo.ORO));

        truco.getJugador("J3").levantarCarta(new Carta(6,Palo.ESPADA));
        truco.getJugador("J3").levantarCarta(new Carta(4,Palo.ESPADA));
        truco.getJugador("J3").levantarCarta(new Carta(3,Palo.COPA));

        truco.getJugador("J4").levantarCarta(new Carta(10,Palo.ORO));
        truco.getJugador("J4").levantarCarta(new Carta(4,Palo.COPA));
        truco.getJugador("J4").levantarCarta(new Carta(7,Palo.COPA));


        truco.getMesa().getJugadorActivo().jugarCarta(7,Palo.ESPADA);       Assert.assertEquals(truco.getJugador("J2"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().jugarCarta(5,Palo.BASTO);        Assert.assertEquals(truco.getJugador("J3"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().cantarEnvido(new Envido());      Assert.assertEquals(truco.getJugador("J4"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().aceptarEnvido();

        truco.getMesa().getJugadorActivo().cantarEnvido(new Envido());      Assert.assertEquals(truco.getJugador("J3"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().aceptarEnvido();
        truco.getMesa().resolverEnvido();
        Assert.assertEquals(4, truco.getEquipo("E2").getPuntaje());

        truco.getMesa().getJugadorActivo().jugarCarta(4,Palo.ESPADA);       Assert.assertEquals(truco.getJugador("J4"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().jugarCarta(4,Palo.COPA);         Assert.assertEquals(truco.getJugador("J1"), truco.getMesa().getJugadorActivo());
        truco.getMesa().resolverMano();

        truco.getMesa().getJugadorActivo().cantarTruco();                   Assert.assertEquals(truco.getJugador("J2"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().aceptarTruco();
        truco.getMesa().getJugadorActivo().cantarRetruco();                 Assert.assertEquals(truco.getJugador("J1"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().aceptarTruco();
        truco.getMesa().getJugadorActivo().jugarCarta(3,Palo.ESPADA);       Assert.assertEquals(truco.getJugador("J2"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().jugarCarta(7,Palo.ORO);          Assert.assertEquals(truco.getJugador("J3"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().jugarCarta(3,Palo.COPA);         Assert.assertEquals(truco.getJugador("J4"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().jugarCarta(10,Palo.ORO);         Assert.assertEquals(truco.getJugador("J1"), truco.getMesa().getJugadorActivo());
        truco.getMesa().resolverMano();

        Assert.assertFalse(truco.getMesa().getRonda().termino());


        truco.getMesa().getJugadorActivo().jugarCarta(2,Palo.ESPADA);
        truco.getMesa().getJugadorActivo().jugarCarta(6,Palo.ESPADA);
        truco.getMesa().getJugadorActivo().jugarCarta(7,Palo.COPA);
        truco.getMesa().getJugadorActivo().jugarCarta(10,Palo.COPA);
        truco.getMesa().resolverMano();
        Assert.assertTrue(truco.getMesa().getRonda().termino());
        Assert.assertEquals(7, truco.getEquipo("E2").getPuntaje());

        /**RONDA 2**/
        truco.getMesa().nuevaRonda();
        Assert.assertTrue(truco.getMesa().getArbitro().jugadorEsMano(truco.getJugador("J2")));
        Assert.assertEquals(truco.getMesa().getJugadorActivo(), truco.getJugador("J2"));

        truco.getJugador("J1").levantarCarta(new Carta(3, Palo.ESPADA));
        truco.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        truco.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        truco.getJugador("J2").levantarCarta(new Carta(2, Palo.ESPADA));
        truco.getJugador("J2").levantarCarta(new Carta(5, Palo.BASTO));
        truco.getJugador("J2").levantarCarta(new Carta(7, Palo.ORO));

        truco.getJugador("J3").levantarCarta(new Carta(6,Palo.ESPADA));
        truco.getJugador("J3").levantarCarta(new Carta(4,Palo.ESPADA));
        truco.getJugador("J3").levantarCarta(new Carta(3,Palo.COPA));

        truco.getJugador("J4").levantarCarta(new Carta(10,Palo.ORO));
        truco.getJugador("J4").levantarCarta(new Carta(4,Palo.COPA));
        truco.getJugador("J4").levantarCarta(new Carta(7,Palo.COPA));

        truco.getMesa().getJugadorActivo().jugarCarta(7,Palo.ORO);
        truco.getMesa().getJugadorActivo().jugarCarta(6,Palo.ESPADA);               Assert.assertEquals(truco.getJugador("J4"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().cantarEnvido(new Envido());              Assert.assertEquals(truco.getJugador("J1"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().aceptarEnvido();
        truco.getMesa().getJugadorActivo().cantarEnvido(new Envido());              Assert.assertEquals(truco.getJugador("J4"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().aceptarEnvido();
        truco.getMesa().getJugadorActivo().cantarEnvido(new RealEnvido());          Assert.assertEquals(truco.getJugador("J1"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().quieroEnvido();                          Assert.assertEquals(truco.getJugador("J4"), truco.getMesa().getJugadorActivo());
        truco.getMesa().resolverEnvido();
        Assert.assertEquals(14,truco.getEquipo("E2").getPuntaje());
        Assert.assertEquals(truco.getJugador("J4"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().jugarCarta(10,Palo.ORO);
        truco.getMesa().getJugadorActivo().cantarTruco();
        truco.getMesa().getJugadorActivo().aceptarTruco();
        truco.getMesa().getJugadorActivo().cantarRetruco();                      Assert.assertEquals(truco.getJugador("J1"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().noQuieroTruco();
        Assert.assertEquals(16,truco.getEquipo("E2").getPuntaje());
        Assert.assertTrue(truco.getMesa().getRonda().termino());

        /** RONDA 3 **/
        truco.getMesa().nuevaRonda();
        Assert.assertTrue(truco.getMesa().getArbitro().jugadorEsMano(truco.getJugador("J3")));
        Assert.assertEquals(truco.getMesa().getJugadorActivo(), truco.getJugador("J3"));

        truco.getJugador("J1").levantarCarta(new Carta(3, Palo.ESPADA));
        truco.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        truco.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        truco.getJugador("J2").levantarCarta(new Carta(3, Palo.ORO));
        truco.getJugador("J2").levantarCarta(new Carta(5, Palo.BASTO));
        truco.getJugador("J2").levantarCarta(new Carta(7, Palo.ORO));

        truco.getJugador("J3").levantarCarta(new Carta(6,Palo.ESPADA));
        truco.getJugador("J3").levantarCarta(new Carta(4,Palo.ESPADA));
        truco.getJugador("J3").levantarCarta(new Carta(3,Palo.COPA));

        truco.getJugador("J4").levantarCarta(new Carta(10,Palo.ORO));
        truco.getJugador("J4").levantarCarta(new Carta(4,Palo.COPA));
        truco.getJugador("J4").levantarCarta(new Carta(7,Palo.COPA));

        truco.getMesa().getJugadorActivo().cantarTruco();                       Assert.assertEquals(truco.getJugador("J4"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().cantarEnvido(new Envido());          //El envido esta primero
        truco.getMesa().getJugadorActivo().quieroEnvido();
        truco.getMesa().resolverEnvido();
        Assert.assertEquals(truco.getJugador("J4"), truco.getMesa().getJugadorActivo());
        truco.getMesa().getJugadorActivo().aceptarTruco();
        truco.getMesa().jugadorAnterior();
        Assert.assertEquals(18,truco.getEquipo("E2").getPuntaje());
        truco.getMesa().getJugadorActivo().jugarCarta(3,Palo.COPA);
        truco.getMesa().getJugadorActivo().jugarCarta(4,Palo.COPA);
        truco.getMesa().getJugadorActivo().jugarCarta(3,Palo.ESPADA);
        truco.getMesa().getJugadorActivo().jugarCarta(3,Palo.ORO);
        truco.getMesa().resolverMano();
        Assert.assertFalse(truco.getMesa().getRonda().termino());
        Assert.assertEquals(truco.getJugador("J3"), truco.getMesa().getJugadorActivo());        //Parda
        truco.getMesa().getJugadorActivo().jugarCarta(6,Palo.ESPADA);
        truco.getMesa().getJugadorActivo().jugarCarta(10,Palo.ORO);
        truco.getMesa().getJugadorActivo().jugarCarta(10,Palo.COPA);
        truco.getMesa().getJugadorActivo().jugarCarta(7,Palo.ORO);

        truco.getMesa().resolverMano();
        Assert.assertTrue(truco.getMesa().getRonda().termino());
        Assert.assertEquals(20,truco.getEquipo("E2").getPuntaje());



    }
}
