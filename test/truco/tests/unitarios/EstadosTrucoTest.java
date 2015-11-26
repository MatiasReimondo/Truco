package truco.tests.unitarios;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Equipo;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

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
        mesa =new Mesa();

        equipoPepe =new Equipo("EquipoPepe");
        equipoJuan =new Equipo("EquipoJuan");

        jugadorJuan =new Jugador("Juan");
        jugadorPepe =new Jugador("Pepe");

        equipoPepe.agregarIntegrante(jugadorPepe);
        equipoJuan.agregarIntegrante(jugadorJuan);

        jugadorPepe.setEquipo(equipoPepe);
        jugadorJuan.setEquipo(equipoJuan);

        listaJugadores=new ArrayList<>();
        listaJugadores.add(jugadorJuan);
        listaJugadores.add(jugadorPepe);

        jugadorPepe.setMesa(mesa);
        jugadorJuan.setMesa(mesa);

        mesa.setJugadores(listaJugadores);
    }

    @Test
    public void seCantaTruco(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quieroTruco();


        Assert.assertEquals(2,mesa.getEstadoTruco().getPuntaje());

    }

    @Test
    public void seCantaTrucoRetruco(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quieroTruco();
        jugadorPepe.cantarRetruco();
        jugadorJuan.quieroTruco();

        Assert.assertEquals(3,mesa.getEstadoTruco().getPuntaje());

    }

    @Test
    public void seCantaTrucoRetrucoValeCuatro(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quieroTruco();
        jugadorPepe.cantarRetruco();
        jugadorJuan.quieroTruco();
        jugadorJuan.cantarValeCuatro();
        jugadorPepe.quieroTruco();

        Assert.assertEquals(4,mesa.getEstadoTruco().getPuntaje());

    }

    @Test (expected = NoSePuedeCantarAhoraException.class)
    public void seQuiereCuandoNoHayNadaCantado(){
        mesa.nuevaRonda();
        jugadorJuan.quieroTruco();




    }

    @Test (expected = NoSePuedeCantarAhoraException.class)
    public void seCantaReTrucoSinElTruco(){
        mesa.nuevaRonda();
        jugadorJuan.cantarRetruco();

    }

    @Test (expected = NoSePuedeCantarAhoraException.class)
    public void seCantaValeCuatroSinElTruco(){
        mesa.nuevaRonda();
        jugadorJuan.cantarValeCuatro();

    }

    @Test (expected = NoSePuedeCantarAhoraException.class)
    public void seCantaTrucoValeCuatro(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quieroTruco();
        jugadorPepe.cantarValeCuatro();
    }


    @Test
    public void seCantaTrucoRetrucoNoSeQuiere(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quieroTruco();
        jugadorPepe.cantarRetruco();
        jugadorJuan.noQuieroTruco();
        Assert.assertEquals(2, jugadorPepe.getEquipo().getPuntaje());

    }


}
