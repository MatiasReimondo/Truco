package integracion;

import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class ResolverJugadas {

    private Mesa mesa;
    private Equipo equipoPepe;
    private Equipo equipoJuan;
    private Jugador jugadorPepe;
    private Jugador jugadorJuan;
    private List<Jugador> listaJugadores;

    @Before
    public void setup(){
        mesa=new Mesa();

        equipoPepe =new Equipo("EquipoPepe");
        equipoJuan=new Equipo("EquipoJuan");

        jugadorJuan=new Jugador("Juan");
        jugadorPepe=new Jugador("Pepe");

        equipoPepe.agregarIntegrante(jugadorPepe);
        equipoJuan.agregarIntegrante(jugadorJuan);

        listaJugadores=new ArrayList<>();
        listaJugadores.add(jugadorPepe);
        listaJugadores.add(jugadorJuan);

        jugadorJuan.setMesa(mesa);
        jugadorPepe.setMesa(mesa);

        mesa.setJugadores(listaJugadores);
    }

    @Test
    public void testCartaMasFuerteVenceACartaMasDebil(){
        mesa.nuevaRonda();
        jugadorPepe.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorJuan.levantarCarta(new Carta(5, Palo.BASTO));

        jugadorPepe.jugarCarta(4,Palo.BASTO);
        jugadorJuan.jugarCarta(5,Palo.BASTO);
    }
}
