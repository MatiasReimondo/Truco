package truco.tests.unitarios;

import junit.framework.Assert;
import org.junit.Test;
import truco.modelo.Jugador;
import truco.modelo.Mazo;

import java.util.ArrayList;
import java.util.List;

public class MazoTest {

    @Test
    public void seCreasUnMazoCon40Cartas(){
        Mazo nuevoMazo= new Mazo();

        Assert.assertEquals(40, nuevoMazo.getCartas().size());

    }

    /*
    @Test
    public void seAgreganCartasFaltantesAlMazo(){
        Mazo nuevoMazo= new Mazo();
        Jugador jugador1 = new Jugador();
        List<Jugador> jugadores= new ArrayList<>();
        jugadores.add(jugador1);
        nuevoMazo.repartirCartas(jugadores);
        nuevoMazo.rearmar(jugadores.get(0).getMano());

        Assert.assertEquals(40, nuevoMazo.getCartas().size());
    }



    @Test
    public void seMezclaElMazo(){
        Mazo nuevoMazo= new Mazo();
        Carta nuevaCarta= nuevoMazo.getCartas().getFirst();

        nuevoMazo.mezclar();

        Assert.assertEquals(false, nuevoMazo.getCartas().getFirst().equals(nuevaCarta));

    }
*/
}
