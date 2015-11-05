package truco.tests;

import truco.modelo.Carta;
import truco.modelo.Mazo;
import junit.framework.Assert;
import org.junit.Test;

public class MazoTest {

    @Test
    public void seCreasUnMazoCon40Cartas(){
        Mazo nuevoMazo= new Mazo();

        Assert.assertEquals(40, nuevoMazo.getCartas().size());

    }

    /*
    @Test
    public void seMezclaElMazo(){
        Mazo nuevoMazo= new Mazo();
        Carta nuevaCarta= nuevoMazo.getCartas().getFirst();

        nuevoMazo.mezclarMazo();

        Assert.assertEquals(false, nuevoMazo.getCartas().getFirst().equals(nuevaCarta));

    }
*/
}
