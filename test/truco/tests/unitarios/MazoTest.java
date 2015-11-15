package truco.tests.unitarios;

import org.junit.Assert;
import org.junit.Test;

import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mazo;
import truco.modelo.excepciones.MazoSinCartasException;

public class MazoTest {

    @Test
    public void seCreasUnMazoCon40Cartas(){
        Mazo nuevoMazo= new Mazo();

        Assert.assertEquals(40, nuevoMazo.getCartas().size());
    }

    @Test
    public void testMezclarMazo(){ //Este test tiene un 2.5% de probabilidad de fallar, pero no existe otra forma
        Mazo mazo=new Mazo();

        Carta carta=mazo.getCartas().removeFirst();
        mazo.getCartas().add(carta);
        mazo.mezclar();
        Assert.assertFalse(carta == mazo.getCartas().removeFirst());
    }
}
