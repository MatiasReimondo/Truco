package truco.tests.unitarios;

import junit.framework.Assert;
import truco.modelo.Carta;
import truco.modelo.TablaFuerzaCarta;
import truco.modelo.enumerables.Palo;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CartaTest {

    @Test
    public void testAsignarTablaFuerza(){
        TablaFuerzaCarta tabla=new TablaFuerzaCarta();
        Assert.assertFalse(tabla.getTabla()==null);
        Carta carta=new Carta(1,Palo.ESPADA);

        Assert.assertTrue(tabla.getTabla().get(carta)==14);
    }

    @Test
    public void testTablaFuerzaNoEsNull(){
        Carta carta=new Carta(1,Palo.ORO);
        Assert.assertFalse(carta.getTablaFuerza()==null);
    }

    @Test
    public void testOverrideDeEqualsTrue1(){
        Carta AsDeEspadas1=new Carta(1,Palo.ESPADA);
        Carta AsDeEspadas2=new Carta(1,Palo.ESPADA);
        Assert.assertTrue(AsDeEspadas1.equals(AsDeEspadas2));
    }

    @Test
    public void testOverrideDeEqualsTrue2(){
        Carta carta1=new Carta(5,Palo.COPA);
        Carta carta2=new Carta(5,Palo.ORO);

        Assert.assertTrue(carta1.equals(carta2));
    }

    @Test
    public void testOverrideDeEqualsFalse(){
        Carta AsDeEspadas1=new Carta(1,Palo.ESPADA);
        Carta AsDeCopas=new Carta(1,Palo.COPA);
        Assert.assertFalse(AsDeEspadas1.equals(AsDeCopas));
    }

    @Test
    public void seCreaElUnoDeEspadaConFuerza14(){
        Carta cartaPrueba= new Carta(1, Palo.ESPADA);
        assertEquals(14, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElUnoDeBastoConFuerza13(){
        Carta cartaPrueba= new Carta(1,Palo.BASTO);
        assertEquals(13, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElCuatroDeOroConFuerza1(){
        Carta cartaPrueba= new Carta(4,Palo.ORO);
        assertEquals(1, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElTresDeCopaConFuerza10(){
        Carta cartaPrueba= new Carta(3,Palo.COPA);
        assertEquals(10, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElDoceDeEspadaConFuerza7(){
        Carta cartaPrueba= new Carta(12,Palo.ESPADA);
        assertEquals(7, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElUnoDeBastoConPuntosEnvido1(){
        Carta cartaPrueba= new Carta(1,Palo.BASTO);
        assertEquals(1, cartaPrueba.getPuntosEnvido());

    }
    @Test
    public void seCreaElCuatroDeOroConPuntosEnvido4() {
        Carta cartaPrueba = new Carta(4, Palo.ORO);
        assertEquals(4, cartaPrueba.getPuntosEnvido());
    }




}


