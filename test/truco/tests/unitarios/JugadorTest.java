package truco.tests.unitarios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;
import truco.modelo.Palo;
import truco.modelo.excepciones.CartaNoEstaEnLaManoException;
import truco.modelo.excepciones.LimiteDeCartasExcedidoException;

public class JugadorTest {

    private Jugador tester;

    @Before
    public void setup(){
        tester=new Jugador("Pepe");
    }
    
    @Test
    public void elJugadorRecibeUnaCarta(){
        Carta nuevaCarta= new Carta(1, Palo.BASTO);
        tester.robarCarta(nuevaCarta);
        Assert.assertEquals(1,tester.getMano().size());
    }
    
    @Test(expected=LimiteDeCartasExcedidoException.class)
    public void testJugadorIntentaRobar4Cartas(){
        Carta carta1= new Carta(1, Palo.BASTO);
        Carta carta2= new Carta(1, Palo.ESPADA);
        Carta carta3= new Carta(7, Palo.ESPADA);
        Carta carta4= new Carta(7, Palo.ORO);
        tester.robarCarta(carta1);
        tester.robarCarta(carta2);
        tester.robarCarta(carta3);
        tester.robarCarta(carta4);
    }

    @Test(expected = CartaNoEstaEnLaManoException.class)
    public void testJugarCartaInvalida(){
        tester.jugarCarta(5,Palo.BASTO);
    }

    @Test
    public void testSeReparteUn765YDevuelveEnvido33(){
        Carta nuevaCarta1= new Carta(7, Palo.BASTO);
        tester.robarCarta(nuevaCarta1);
        Carta nuevaCarta2= new Carta(6, Palo.BASTO);
        tester.robarCarta(nuevaCarta2);
        Carta nuevaCarta3= new Carta(5, Palo.BASTO);
        tester.robarCarta(nuevaCarta3);
        Assert.assertEquals(33,tester.getEnvido());

    }
    @Test
    public void testSeReparteUn567YDevuelveEnvido33(){
        Carta nuevaCarta1= new Carta(5, Palo.BASTO);
        tester.robarCarta(nuevaCarta1);
        Carta nuevaCarta2= new Carta(6, Palo.BASTO);
        tester.robarCarta(nuevaCarta2);
        Carta nuevaCarta3= new Carta(7, Palo.BASTO);
        tester.robarCarta(nuevaCarta3);
        Assert.assertEquals(33,tester.getEnvido());

    }

    @Test
    public void testSeReparteUn657YDevuelveEnvido33(){
        Carta nuevaCarta1= new Carta(6, Palo.BASTO);
        tester.robarCarta(nuevaCarta1);
        Carta nuevaCarta2= new Carta(5, Palo.BASTO);
        tester.robarCarta(nuevaCarta2);
        Carta nuevaCarta3= new Carta(7, Palo.BASTO);
        tester.robarCarta(nuevaCarta3);
        Assert.assertEquals(33,tester.getEnvido());

    }

    @Test
    public void testSeReparteTresCartasDelMismoPaloYHayFlor(){
        Carta nuevaCarta1= new Carta(6, Palo.BASTO);
        tester.robarCarta(nuevaCarta1);
        Carta nuevaCarta2= new Carta(5, Palo.BASTO);
        tester.robarCarta(nuevaCarta2);
        Carta nuevaCarta3= new Carta(7, Palo.BASTO);
        tester.robarCarta(nuevaCarta3);
        Assert.assertEquals(true,tester.tieneFlor());

    }
    @Test
    public void testSeReparteTresCartasDelDiferentePaloYHayFlor(){
        Carta nuevaCarta1= new Carta(6, Palo.BASTO);
        tester.robarCarta(nuevaCarta1);
        Carta nuevaCarta2= new Carta(5, Palo.BASTO);
        tester.robarCarta(nuevaCarta2);
        Carta nuevaCarta3= new Carta(7, Palo.ESPADA);
        tester.robarCarta(nuevaCarta3);
        Assert.assertEquals(false,tester.tieneFlor());

    }
    @Test
    public void testSeReparteTresCartasDelMismoPaloYDevuelveLaFlor(){
        Carta nuevaCarta1= new Carta(6, Palo.BASTO);
        tester.robarCarta(nuevaCarta1);
        Carta nuevaCarta2= new Carta(5, Palo.BASTO);
        tester.robarCarta(nuevaCarta2);
        Carta nuevaCarta3= new Carta(7, Palo.BASTO);
        tester.robarCarta(nuevaCarta3);
        Assert.assertEquals(38,tester.getFlor());

    }




}
