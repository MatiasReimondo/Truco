package truco.tests.unitarios;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;
import truco.modelo.enumerables.Numero;
import truco.modelo.enumerables.Palo;
import truco.modelo.excepciones.CartaNoEstaEnLaManoException;
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

    @Test
    public void testJugarCartaDeLaMano(){
        Carta carta=new Carta(Numero.CINCO,Palo.BASTO);
        tester.robarCarta(carta);
        Assert.assertEquals(tester.jugarCarta(Numero.CINCO,Palo.BASTO),carta);
    }

    @Test(expected = CartaNoEstaEnLaManoException.class)
    public void testJugarCartaInvalida(){
        tester.jugarCarta(Numero.CINCO,Palo.BASTO);
    }

    @Test
    public void testSeReparteUn765YDevuelveEnvido33(){
        Carta nuevaCarta1= new Carta(Numero.SIETE, Palo.BASTO);
        tester.robarCarta(nuevaCarta1);
        Carta nuevaCarta2= new Carta(Numero.SEIS, Palo.BASTO);
        tester.robarCarta(nuevaCarta2);
        Carta nuevaCarta3= new Carta(Numero.CINCO, Palo.BASTO);
        tester.robarCarta(nuevaCarta3);
        Assert.assertEquals(33,tester.getEnvido());

    }
    @Test
    public void testSeReparteUn567YDevuelveEnvido33(){
        Carta nuevaCarta1= new Carta(Numero.CINCO, Palo.BASTO);
        tester.robarCarta(nuevaCarta1);
        Carta nuevaCarta2= new Carta(Numero.SEIS, Palo.BASTO);
        tester.robarCarta(nuevaCarta2);
        Carta nuevaCarta3= new Carta(Numero.SIETE, Palo.BASTO);
        tester.robarCarta(nuevaCarta3);
        Assert.assertEquals(33,tester.getEnvido());

    }

    @Test
    public void testSeReparteUn657YDevuelveEnvido33(){
        Carta nuevaCarta1= new Carta(Numero.SEIS, Palo.BASTO);
        tester.robarCarta(nuevaCarta1);
        Carta nuevaCarta2= new Carta(Numero.CINCO, Palo.BASTO);
        tester.robarCarta(nuevaCarta2);
        Carta nuevaCarta3= new Carta(Numero.SIETE, Palo.BASTO);
        tester.robarCarta(nuevaCarta3);
        Assert.assertEquals(33,tester.getEnvido());

    }
    

}
