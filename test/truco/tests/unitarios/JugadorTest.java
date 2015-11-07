package truco.tests.unitarios;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;
import truco.modelo.enumerables.Numero;
import truco.modelo.enumerables.Palo;
import truco.modelo.excepciones.LimiteDeCartasExcedidoException;

public class JugadorTest {

    private Jugador tester;

    @Before
    public void setup(){
        tester=new Jugador();
    }
    
    @Test
    public void elJugadorRecibeUnaCarta(){
        Carta nuevaCarta= new Carta(Numero.UNO, Palo.BASTO);
        tester.robarCarta(nuevaCarta);
        Assert.assertEquals(1,tester.getMano().size());
    }
    
    @Test(expected=LimiteDeCartasExcedidoException.class)
    public void seTrataDeAgregarUnaCuartaCartaYnoSeLaAgrega(){
        Carta carta1= new Carta(Numero.UNO, Palo.BASTO);
        Carta carta2= new Carta(Numero.UNO, Palo.ESPADA);
        Carta carta3= new Carta(Numero.SIETE, Palo.ESPADA);
        Carta carta4= new Carta(Numero.SIETE, Palo.ORO);
        tester.robarCarta(carta1);
        tester.robarCarta(carta2);
        tester.robarCarta(carta3);
        tester.robarCarta(carta4);


    }

}
