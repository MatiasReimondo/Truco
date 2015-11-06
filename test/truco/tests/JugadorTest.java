package truco.tests;


import junit.framework.Assert;
import org.junit.Test;
import truco.modelo.*;

public class JugadorTest {
    @Test
    public void elJugadorRecibeUnaCarta(){
        Jugador jugador= new Jugador();
        Carta nuevaCarta= new Carta(Numero.UNO, Palo.BASTO);
        jugador.recibirCarta(nuevaCarta);
        Assert.assertEquals(1, jugador.getManoDeCartas().size());

    }
    @Test
    public void seTrataDeAgregarUnaCuartaCartaYnoSeLaAgrega(){
        Jugador jugador= new Jugador();
        Carta carta1= new Carta(Numero.UNO, Palo.BASTO);
        Carta carta2= new Carta(Numero.UNO, Palo.ESPADA);
        Carta carta3= new Carta(Numero.SIETE, Palo.ESPADA);
        Carta carta4= new Carta(Numero.SIETE, Palo.ORO);
        jugador.recibirCarta(carta1);
        jugador.recibirCarta(carta2);
        jugador.recibirCarta(carta3);
        jugador.recibirCarta(carta4);

        Assert.assertEquals(3, jugador.getManoDeCartas().size());
    }
}
