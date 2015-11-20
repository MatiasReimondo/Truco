package truco.tests.unitarios;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Equipo;
import truco.modelo.Jugador;
import truco.modelo.Mesa;

import java.util.ArrayList;
import java.util.List;

public class EstadosTrucoTest {
    private Mesa mesa;
    private Equipo equipoPepe;
    private Equipo equipoJuan;
    private Jugador jugadorPepe;
    private Jugador jugadorJuan;
    private List<Jugador> listaJugadores;

    @Before
    public void setup(){
        mesa=new Mesa();

        jugadorJuan=new Jugador("Juan");
        jugadorPepe=new Jugador("Pepe");

        listaJugadores=new ArrayList<>();
        listaJugadores.add(jugadorPepe);
        listaJugadores.add(jugadorJuan);

        jugadorJuan.setMesa(mesa);
        jugadorPepe.setMesa(mesa);

        mesa.setJugadores(listaJugadores);
    }
    @Test
    public void seCantaTruco(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quiero();


        Assert.assertEquals(2,mesa.getEstadoTruco().getPuntaje());

    }

    @Test
    public void seCantaTrucoRetruco(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quiero();
        jugadorPepe.cantarRetruco();
        jugadorJuan.quiero();

        Assert.assertEquals(3,mesa.getEstadoTruco().getPuntaje());

    }

    @Test
    public void seCantaTrucoRetrucoValeCuatro(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quiero();
        jugadorPepe.cantarRetruco();
        jugadorJuan.quiero();
        jugadorJuan.cantarValeCuatro();
        jugadorPepe.quiero();

        Assert.assertEquals(4,mesa.getEstadoTruco().getPuntaje());

    }
}
