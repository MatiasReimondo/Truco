package integracion;

import org.junit.Before;
import org.junit.Test;
import truco.modelo.Equipo;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.Palo;
import truco.modelo.excepciones.CartaNoEstaEnLaManoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eze Cruz Avila on 19/11/2015.
 */
public class CasosParticulares {
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

        jugadorJuan.setEquipo(equipoJuan);
        jugadorPepe.setEquipo(equipoPepe);

        listaJugadores=new ArrayList<>();
        listaJugadores.add(jugadorPepe);
        listaJugadores.add(jugadorJuan);

        jugadorJuan.setMesa(mesa);
        jugadorPepe.setMesa(mesa);

        mesa.setJugadores(listaJugadores);
    }

    @Test(expected = CartaNoEstaEnLaManoException.class)
    public void testJugarUnaCartaQueNoEstaEnLaMano(){
        mesa.nuevaRonda();
        jugadorPepe.jugarCarta(10, Palo.BASTO);
    }
}
