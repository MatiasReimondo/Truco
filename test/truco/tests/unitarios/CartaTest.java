package truco.tests.unitarios;

import truco.modelo.Carta;
import truco.modelo.enumerables.Numero;
import truco.modelo.enumerables.Palo;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CartaTest {

    @Test
    public void seCreaElUnoDeEspadaConFuerza14(){
        Carta cartaPrueba= new Carta(Numero.UNO, Palo.ESPADA);
        assertEquals(14, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElUnoDeBastoConFuerza13(){
        Carta cartaPrueba= new Carta(Numero.UNO,Palo.BASTO);
        assertEquals(13, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElCuatroDeOroConFuerza1(){
        Carta cartaPrueba= new Carta(Numero.CUATRO,Palo.ORO);
        assertEquals(1, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElTresDeCopaConFuerza10(){
        Carta cartaPrueba= new Carta(Numero.TRES,Palo.COPA);
        assertEquals(10, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElDoceDeEspadaConFuerza7(){
        Carta cartaPrueba= new Carta(Numero.DOCE,Palo.ESPADA);
        assertEquals(7, cartaPrueba.getFuerza());

    }
    @Test
    public void seCreaElUnoDeBastoConPuntosEnvido1(){
        Carta cartaPrueba= new Carta(Numero.UNO,Palo.BASTO);
        assertEquals(1, cartaPrueba.getPuntosEnvido());

    }
    @Test
    public void seCreaElCuatroDeOroConPuntosEnvido4() {
        Carta cartaPrueba = new Carta(Numero.CUATRO, Palo.ORO);
        assertEquals(4, cartaPrueba.getPuntosEnvido());
    }




}


