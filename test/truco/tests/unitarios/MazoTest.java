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
    @Test
    public void seReparteADosJugadores(){
        Mazo nuevoMazo= new Mazo();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        List<Jugador> jugadores= new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        nuevoMazo.repartirCartas(jugadores);

        Assert.assertEquals(3, jugadores.get(0).getMano().size());
        Assert.assertEquals(3, jugadores.get(1).getMano().size());

    }

    @Test
    public void seReparteACuatroJugadores(){
        Mazo nuevoMazo= new Mazo();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        Jugador jugador4 = new Jugador();
        List<Jugador> jugadores= new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);
        jugadores.add(jugador4);
        nuevoMazo.repartirCartas(jugadores);

        Assert.assertEquals(3, jugadores.get(0).getMano().size());
        Assert.assertEquals(3, jugadores.get(1).getMano().size());
        Assert.assertEquals(3, jugadores.get(2).getMano().size());
        Assert.assertEquals(3, jugadores.get(3).getMano().size());
    }
    /*
    @Test
    public void seAgreganCartasFaltantesAlMazo(){
        Mazo nuevoMazo= new Mazo();
        Jugador jugador1 = new Jugador();
        List<Jugador> jugadores= new ArrayList<>();
        jugadores.add(jugador1);
        nuevoMazo.repartirCartas(jugadores);
        nuevoMazo.juntarMazo(jugadores.get(0).getMano());

        Assert.assertEquals(40, nuevoMazo.getCartas().size());
    }



    @Test
    public void seMezclaElMazo(){
        Mazo nuevoMazo= new Mazo();
        Carta nuevaCarta= nuevoMazo.getCartas().getFirst();

        nuevoMazo.mezclarMazo();

        Assert.assertEquals(false, nuevoMazo.getCartas().getFirst().equals(nuevaCarta));

    }
*/
}
