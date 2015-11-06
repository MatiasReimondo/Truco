package truco.tests.unitarios;


import junit.framework.Assert;
import org.junit.Test;
import truco.modelo.*;

public class JugadorTest {
    @Test
    public void elJugadorRecibeUnaCarta(){
        Jugador jugador= new Jugador();
        Carta nuevaCarta= new Carta(Numero.UNO, Palo.BASTO);
        jugador.robarCarta(nuevaCarta);
        Assert.assertEquals(1, jugador.getMano().size());

    }
    @Test
    public void seTrataDeAgregarUnaCuartaCartaYnoSeLaAgrega(){
        Jugador jugador= new Jugador();
        Carta carta1= new Carta(Numero.UNO, Palo.BASTO);
        Carta carta2= new Carta(Numero.UNO, Palo.ESPADA);
        Carta carta3= new Carta(Numero.SIETE, Palo.ESPADA);
        Carta carta4= new Carta(Numero.SIETE, Palo.ORO);
        jugador.robarCarta(carta1);
        jugador.robarCarta(carta2);
        jugador.robarCarta(carta3);
        jugador.robarCarta(carta4);

        Assert.assertEquals(3, jugador.getMano().size());
    }
}
