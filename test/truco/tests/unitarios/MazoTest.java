package truco.tests.unitarios;

import org.junit.Assert;
import org.junit.Test;

import truco.modelo.Mazo;
public class MazoTest {

    @Test
    public void seCreasUnMazoCon40Cartas(){
        Mazo nuevoMazo= new Mazo();

        Assert.assertEquals(40, nuevoMazo.getCartas().size());

    }
}
