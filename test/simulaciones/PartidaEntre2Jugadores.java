package simulaciones;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Carta;
import truco.modelo.Palo;
import truco.modelo.Truco;
import truco.modelo.envido.Envido;
import truco.modelo.envido.FaltaEnvido;
import truco.modelo.envido.RealEnvido;

public class PartidaEntre2Jugadores {

    private Truco juego;

    @Before
    public void setup(){

        juego=new Truco();
        juego.nuevoEquipo("E1");
        juego.nuevoEquipo("E2");

        juego.nuevoJugador("J1","E1");
        juego.nuevoJugador("J2","E2");
    }

    @Test
    public void partidaCompletaEntre2Jugadores(){
        juego.empezarJuego();
        juego.seJuegaConFlor();

        /**RONDA 1**/
        juego.getMesa().nuevaRonda();

        juego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(2, Palo.ESPADA));
        juego.getJugador("J2").levantarCarta(new Carta(5, Palo.BASTO));
        juego.getJugador("J2").levantarCarta(new Carta(7, Palo.ORO));

        juego.getJugador("J1").cantarEnvido(new Envido());                  Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getJugador("J2").quieroEnvido();                              Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().resolverEnvido();
        Assert.assertEquals(2,juego.getEquipo("E1").getPuntaje());

        juego.getMesa().jugadorAnterior();                                  Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(7,Palo.ESPADA);       Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(2,Palo.ESPADA);       Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().resolverMano();

        juego.getMesa().getJugadorActivo().cantarTruco();                   Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().quieroTruco();
        juego.getMesa().jugadorAnterior();                                  Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(10,Palo.COPA);
        juego.getMesa().getJugadorActivo().jugarCarta(5,Palo.BASTO);
        juego.getMesa().resolverMano();
        Assert.assertEquals(4,juego.getEquipo("E1").getPuntaje());
        Assert.assertTrue(juego.getMesa().getRonda().termino());

        /**RONDA 2**/
        juego.getMesa().nuevaRonda();
        Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        Assert.assertTrue(juego.getMesa().getArbitro().jugadorEsMano(juego.getJugador("J2")));

        juego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(2, Palo.ESPADA));
        juego.getJugador("J2").levantarCarta(new Carta(5, Palo.BASTO));
        juego.getJugador("J2").levantarCarta(new Carta(7, Palo.ORO));

        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());
        Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J1"));
        juego.getMesa().getJugadorActivo().noQuieroEnvido();

        Assert.assertEquals(1,juego.getEquipo("E2").getPuntaje());
        Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J2"));
        juego.getMesa().getJugadorActivo().jugarCarta(2,Palo.ESPADA);
        Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J1"));
        juego.getMesa().getJugadorActivo().jugarCarta(10,Palo.COPA);
        juego.getMesa().resolverMano();

        Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J2"));
        juego.getMesa().getJugadorActivo().cantarTruco();
        juego.getMesa().getJugadorActivo().quieroTruco();
        Assert.assertEquals(juego.getMesa().getJugadorActivo(), juego.getJugador("J1"));
        juego.getMesa().getJugadorActivo().cantarRetruco();
        juego.getMesa().getJugadorActivo().quieroTruco();
        Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J2"));
        juego.getMesa().getJugadorActivo().jugarCarta(7,Palo.ORO);
        juego.getMesa().getJugadorActivo().jugarCarta(5,Palo.ESPADA);
        juego.getMesa().resolverMano();
        Assert.assertEquals(4,juego.getEquipo("E2").getPuntaje());
        Assert.assertTrue(juego.getMesa().getRonda().termino());

        /**RONDA 3**/
        juego.getMesa().nuevaRonda();
        juego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.BASTO));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(7, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(5, Palo.BASTO));
        juego.getJugador("J2").levantarCarta(new Carta(10, Palo.ORO));
        
        Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());
        juego.getMesa().getJugadorActivo().quieroEnvido();
        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());
        juego.getMesa().getJugadorActivo().quieroEnvido();
        juego.getMesa().getJugadorActivo().cantarEnvido(new RealEnvido());
        juego.getMesa().getJugadorActivo().noQuieroEnvido();
        Assert.assertEquals(8,juego.getEquipo("E1").getPuntaje());

        Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(5,Palo.ESPADA);
        juego.getMesa().getJugadorActivo().jugarCarta(5,Palo.BASTO);
        juego.getMesa().resolverMano();

        juego.getMesa().getJugadorActivo().jugarCarta(7,Palo.BASTO);
        juego.getMesa().getJugadorActivo().jugarCarta(7,Palo.COPA);
        juego.getMesa().resolverMano();

        Assert.assertFalse(juego.getMesa().getRonda().termino());
        juego.getMesa().getJugadorActivo().jugarCarta(10,Palo.COPA);
        juego.getMesa().getJugadorActivo().jugarCarta(10,Palo.ORO);
        juego.getMesa().resolverMano();

        Assert.assertEquals(9,juego.getEquipo("E1").getPuntaje());
        Assert.assertTrue(juego.getMesa().getRonda().termino());

        /**RONDA 4**/
        juego.getMesa().nuevaRonda();
        juego.getJugador("J1").levantarCarta(new Carta(6, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.ESPADA));
        juego.getJugador("J2").levantarCarta(new Carta(7, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(4, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(10, Palo.COPA));

        Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().cantarFlor();
        Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().quieroFlor();
        Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().cantarContraFlor();
        Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().quieroFlor();
        Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().resolverFlor();
        Assert.assertEquals(15,juego.getEquipo("E1").getPuntaje());
        Assert.assertEquals(juego.getJugador("J2"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().irseAlMazo();
        Assert.assertEquals(16,juego.getEquipo("E1").getPuntaje());
        Assert.assertTrue(juego.getMesa().getRonda().termino());

        /**RONDA 5**/
        juego.getMesa().nuevaRonda();
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.ESPADA));
        juego.getJugador("J2").levantarCarta(new Carta(7, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(6, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(10, Palo.COPA));

        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());
        juego.getMesa().getJugadorActivo().quieroEnvido();
        juego.getMesa().getJugadorActivo().cantarEnvido(new FaltaEnvido());
        Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J1"));
        juego.getMesa().getJugadorActivo().quieroEnvido();
        juego.getMesa().resolverEnvido();

        Assert.assertEquals(18,juego.getEquipo("E2").getPuntaje());


        juego.getMesa().getJugadorActivo().cantarTruco();               Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J2"));
        juego.getMesa().getJugadorActivo().quieroTruco();
        juego.getMesa().getJugadorActivo().cantarRetruco();             Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J1"));
        juego.getMesa().getJugadorActivo().quieroTruco();
        juego.getMesa().getJugadorActivo().cantarValeCuatro();          Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J2"));
        juego.getMesa().getJugadorActivo().quieroTruco();
        juego.getMesa().jugadorAnterior();
        Assert.assertEquals(juego.getJugador("J1"),juego.getMesa().getJugadorActivo());
        juego.getMesa().getJugadorActivo().jugarCarta(10,Palo.ESPADA);
        juego.getMesa().getJugadorActivo().jugarCarta(6,Palo.COPA);
        juego.getMesa().resolverMano();

        Assert.assertFalse(juego.getMesa().getRonda().termino());
        juego.getMesa().getJugadorActivo().jugarCarta(5,Palo.ESPADA);
        Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J2"));
        juego.getMesa().getJugadorActivo().jugarCarta(7,Palo.COPA);
        juego.getMesa().resolverMano();

        Assert.assertFalse(juego.getMesa().getRonda().termino());
        juego.getMesa().getJugadorActivo().jugarCarta(7,Palo.ESPADA);
        juego.getMesa().getJugadorActivo().jugarCarta(10,Palo.COPA);
        juego.getMesa().resolverMano();
        Assert.assertEquals(20,juego.getEquipo("E1").getPuntaje());
        Assert.assertTrue(juego.getMesa().getRonda().termino());

        /**RONDA 6**/
        juego.getMesa().nuevaRonda();
        Assert.assertFalse(juego.terminado());
        juego.getMesa().getJugadorActivo().irseAlMazo();
        Assert.assertEquals(22,juego.getEquipo("E1").getPuntaje());
        Assert.assertTrue(juego.getMesa().getRonda().termino());

        /**RONDA 7**/
        juego.getMesa().nuevaRonda();
        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());
        juego.getMesa().getJugadorActivo().noQuieroEnvido();
        Assert.assertEquals(juego.getMesa().getJugadorActivo(),juego.getJugador("J1"));
        Assert.assertEquals(23,juego.getEquipo("E1").getPuntaje());
        juego.getMesa().getJugadorActivo().irseAlMazo();
        Assert.assertEquals(19,juego.getEquipo("E2").getPuntaje());
        Assert.assertTrue(juego.getMesa().getRonda().termino());

        /**RONDA 8**/
        juego.getMesa().nuevaRonda();
        juego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(6, Palo.ESPADA));
        juego.getJugador("J1").levantarCarta(new Carta(10, Palo.ESPADA));
        juego.getJugador("J2").levantarCarta(new Carta(7, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(3, Palo.COPA));
        juego.getJugador("J2").levantarCarta(new Carta(10, Palo.COPA));

        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());
        juego.getMesa().getJugadorActivo().quieroEnvido();
        juego.getMesa().getJugadorActivo().cantarEnvido(new Envido());
        juego.getMesa().getJugadorActivo().quieroEnvido();
        juego.getMesa().getJugadorActivo().cantarEnvido(new RealEnvido());
        juego.getMesa().getJugadorActivo().quieroEnvido();
        juego.getMesa().resolverEnvido();

        Assert.assertEquals(30,juego.getEquipo("E1").getPuntaje());
        Assert.assertTrue(juego.terminado());

    }
}
