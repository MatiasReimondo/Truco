package truco.tests.unitarios;

import org.junit.Assert;
import truco.modelo.Carta;
import truco.modelo.Palo;
import org.junit.Test;
import truco.modelo.excepciones.NumeroCartaExcedeElRangoException;

import static junit.framework.Assert.assertEquals;

public class CartaTest {

    @Test
    public void testEqualsFalseConNull(){
        Carta carta=new Carta(4,Palo.BASTO);

        Assert.assertFalse(carta.equals(null));
    }

    @Test
    public void testEqualsConOtroObjeto(){
        Carta carta=new Carta(4,Palo.BASTO);
        Assert.assertFalse(carta.equals(true));
    }
    @Test
    public void testEquals2CartasIguales(){
        Carta AsDeEspadas1=new Carta(1,Palo.ESPADA);
        Carta AsDeEspadas2=new Carta(1,Palo.ESPADA);
        org.junit.Assert.assertTrue(AsDeEspadas1.equals(AsDeEspadas2));
    }

    @Test
    public void testEquals2CartasDistintoPaloIgualFuerza(){
        Carta carta1=new Carta(5,Palo.COPA);
        Carta carta2=new Carta(5,Palo.ORO);

        Assert.assertTrue(carta1.equals(carta2));
    }

    @Test
    public void testEquals2CartasIgualNumeroDistintoPaloDistintaFuerza(){
        Carta AsDeEspadas1=new Carta(1,Palo.ESPADA);
        Carta AsDeCopas=new Carta(1,Palo.COPA);
        Assert.assertFalse(AsDeEspadas1.equals(AsDeCopas));
    }

    @Test
    public void testValorEnvido(){
        Carta carta1=new Carta(4,Palo.BASTO);
        Carta carta2=new Carta(12,Palo.BASTO);
        Assert.assertEquals(carta1.getValorEnvido(),4);
        Assert.assertEquals(carta2.getValorEnvido(),0);
    }

    @Test(expected = NumeroCartaExcedeElRangoException.class)
    public void testIntentoDeCrearUnaCartaInvalida(){
        Carta carta=new Carta(8,Palo.BASTO);
        Assert.assertTrue(true);
    }

    @Test
    public void testGetFuerza1DeTodosLosPalos(){
        Carta cartaBasto= new Carta(1,Palo.BASTO);
        Carta cartaCopa= new Carta(1,Palo.COPA);
        Carta cartaEspada= new Carta(1,Palo.ESPADA);
        Carta cartaOro= new Carta(1,Palo.ORO);

        assertEquals(13, cartaBasto.getFuerza());
        assertEquals(8, cartaCopa.getFuerza());
        assertEquals(14, cartaEspada.getFuerza());
        assertEquals(8, cartaOro.getFuerza());
    }

    @Test
    public void testGetFuerza2DeTodosLosPalos(){
        Carta cartaBasto= new Carta(2,Palo.BASTO);
        Carta cartaCopa= new Carta(2,Palo.COPA);
        Carta cartaEspada= new Carta(2,Palo.ESPADA);
        Carta cartaOro= new Carta(2,Palo.ORO);

        assertEquals(9, cartaBasto.getFuerza());
        assertEquals(9, cartaCopa.getFuerza());
        assertEquals(9, cartaEspada.getFuerza());
        assertEquals(9, cartaOro.getFuerza());
    }

    @Test
    public void testGetFuerza3DeTodosLosPalos(){
        Carta cartaBasto= new Carta(3,Palo.BASTO);
        Carta cartaCopa= new Carta(3,Palo.COPA);
        Carta cartaEspada= new Carta(3,Palo.ESPADA);
        Carta cartaOro= new Carta(3,Palo.ORO);

        assertEquals(10, cartaBasto.getFuerza());
        assertEquals(10, cartaCopa.getFuerza());
        assertEquals(10, cartaEspada.getFuerza());
        assertEquals(10, cartaOro.getFuerza());
    }

    @Test
    public void testGetFuerza4DeTodosLosPalos(){
        Carta cartaBasto= new Carta(4,Palo.BASTO);
        Carta cartaCopa= new Carta(4,Palo.COPA);
        Carta cartaEspada= new Carta(4,Palo.ESPADA);
        Carta cartaOro= new Carta(4,Palo.ORO);

        assertEquals(1, cartaBasto.getFuerza());
        assertEquals(1, cartaCopa.getFuerza());
        assertEquals(1, cartaEspada.getFuerza());
        assertEquals(1, cartaOro.getFuerza());
    }

    @Test
    public void testGetFuerza5DeTodosLosPalos(){
        Carta cartaBasto= new Carta(5,Palo.BASTO);
        Carta cartaCopa= new Carta(5,Palo.COPA);
        Carta cartaEspada= new Carta(5,Palo.ESPADA);
        Carta cartaOro= new Carta(5,Palo.ORO);

        assertEquals(2, cartaBasto.getFuerza());
        assertEquals(2, cartaCopa.getFuerza());
        assertEquals(2, cartaEspada.getFuerza());
        assertEquals(2, cartaOro.getFuerza());
    }

    @Test
    public void testGetFuerza6DeTodosLosPalos(){
        Carta cartaBasto= new Carta(6,Palo.BASTO);
        Carta cartaCopa= new Carta(6,Palo.COPA);
        Carta cartaEspada= new Carta(6,Palo.ESPADA);
        Carta cartaOro= new Carta(6,Palo.ORO);

        assertEquals(3, cartaBasto.getFuerza());
        assertEquals(3, cartaCopa.getFuerza());
        assertEquals(3, cartaEspada.getFuerza());
        assertEquals(3, cartaOro.getFuerza());
    }

    @Test
    public void testGetFuerza7DeTodosLosPalos(){
        Carta cartaBasto= new Carta(7,Palo.BASTO);
        Carta cartaCopa= new Carta(7,Palo.COPA);
        Carta cartaEspada= new Carta(7,Palo.ESPADA);
        Carta cartaOro= new Carta(7,Palo.ORO);

        assertEquals(4, cartaBasto.getFuerza());
        assertEquals(4, cartaCopa.getFuerza());
        assertEquals(12, cartaEspada.getFuerza());
        assertEquals(11, cartaOro.getFuerza());
    }

    @Test
    public void testGetFuerza10DeTodosLosPalos(){
        Carta cartaBasto= new Carta(10,Palo.BASTO);
        Carta cartaCopa= new Carta(10,Palo.COPA);
        Carta cartaEspada= new Carta(10,Palo.ESPADA);
        Carta cartaOro= new Carta(10,Palo.ORO);

        assertEquals(5, cartaBasto.getFuerza());
        assertEquals(5, cartaCopa.getFuerza());
        assertEquals(5, cartaEspada.getFuerza());
        assertEquals(5, cartaOro.getFuerza());
    }

    @Test
    public void testGetFuerza11DeTodosLosPalos(){
        Carta cartaBasto= new Carta(11,Palo.BASTO);
        Carta cartaCopa= new Carta(11,Palo.COPA);
        Carta cartaEspada= new Carta(11,Palo.ESPADA);
        Carta cartaOro= new Carta(11,Palo.ORO);

        assertEquals(6, cartaBasto.getFuerza());
        assertEquals(6, cartaCopa.getFuerza());
        assertEquals(6, cartaEspada.getFuerza());
        assertEquals(6, cartaOro.getFuerza());
    }

    @Test
    public void testGetFuerza12DeTodosLosPalos(){
        Carta cartaBasto= new Carta(12,Palo.BASTO);
        Carta cartaCopa= new Carta(12,Palo.COPA);
        Carta cartaEspada= new Carta(12,Palo.ESPADA);
        Carta cartaOro= new Carta(12,Palo.ORO);

        assertEquals(7, cartaBasto.getFuerza());
        assertEquals(7, cartaCopa.getFuerza());
        assertEquals(7, cartaEspada.getFuerza());
        assertEquals(7, cartaOro.getFuerza());
    }

    @Test
    public void seCreaElUnoDeBastoConPuntosEnvido1(){
        Carta cartaPrueba= new Carta(1,Palo.BASTO);
        assertEquals(1, cartaPrueba.getValorEnvido());

    }

    @Test
    public void seCreaElCuatroDeOroConPuntosEnvido4() {
        Carta cartaPrueba = new Carta(4, Palo.ORO);
        assertEquals(4, cartaPrueba.getValorEnvido());
    }
}


