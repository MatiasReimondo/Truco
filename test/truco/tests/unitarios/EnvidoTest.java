package truco.tests.unitarios;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Equipo;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.envido.Envido;
import truco.modelo.envido.RealEnvido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaun on 22/11/2015.
 */
public class EnvidoTest {

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

    @Test
    public void elJugadorCantaEnvidoYseSeteaElEstado(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTanto(new Envido());
        Assert.assertEquals(true,mesa.getRonda().getTantoActivo().getClass().equals(new Envido().getClass()));
    }

    @Test
    public void elJugadorCantaRealEnvidoYseSeteaElEstado(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTanto(new RealEnvido());
        Assert.assertEquals(true,mesa.getRonda().getTantoActivo().getClass().equals(new RealEnvido().getClass()));
    }


}
