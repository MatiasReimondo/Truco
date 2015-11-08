package integracion;

import org.junit.Test;
import truco.modelo.Truco;
import truco.modelo.excepciones.ListaJugadoresVaciaException;


public class TrucoTest {

    private Truco truco;

    /*

    @Before
    public void setup(){
        truco=new Truco();
    }
    */

    @Test (expected = ListaJugadoresVaciaException.class)
    public void testCartaMasFuerteGana(){
        truco=new Truco();
        /*

        Carta cuatroDeCopas=new Carta(Numero.CUATRO, Palo.COPA);
        Carta cincoDeBastos=new Carta(Numero.CINCO,Palo.BASTO);

        truco.getJugador("Pepe").robarCarta(cuatroDeCopas);
        truco.getJugador("Juan").robarCarta(cincoDeBastos);

        truco.getJugador("Pepe").jugarCarta(Numero.CUATRO, Palo.COPA);
        truco.getJugador("Juan").jugarCarta(Numero.CINCO,Palo.BASTO);

        truco.resolverMano();


        Assert.assertEquals(truco.getRondaActual().getMano(Mano.PRIMERA).getGanador(), truco.getJugador(Juan));
        */

    }

}
