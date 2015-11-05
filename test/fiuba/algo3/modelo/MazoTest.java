package fiuba.algo3.modelo;

import junit.framework.Assert;
import org.junit.Test;

public class MazoTest {

    @Test
    public void seCreasUnMazoCon40Cartas(){
        Mazo nuevoMazo= new Mazo();

        Assert.assertEquals(40, nuevoMazo.getMazoDeCartas().size());

    }

}
