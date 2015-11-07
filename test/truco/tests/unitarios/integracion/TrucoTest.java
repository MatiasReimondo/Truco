package truco.tests.unitarios.integracion;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;


public class TrucoTest {

    private Truco truco;



    @Before
    public void setup(){
        truco=new Truco();
    }
/*
    @Test
    public void testCartaMasFuerteGana(){

        Carta cuatroDeCopas=new Carta(Numero.CUATRO, Palo.COPA);
        Carta cincoDeBastos=new Carta(Numero.CINCO,Palo.BASTO);

        truco.getJugador("Pepe").robarCarta(cuatroDeCopas);
        truco.getJugador("Juan"").robarCarta(cincoDeBastos);

        truco.getJugador("Pepe").jugarCarta(Numero.CUATRO, Palo.COPA);
        truco.getJugador("Juan").jugarCarta(Numero.CINCO,Palo.BASTO);

        truco.resolverMano();

        Assert.assertEquals(truco.getRondaActual().getMano(Mano.PRIMERA).getGanador(),truco.getJugador(Juan));
    }
*/
}
