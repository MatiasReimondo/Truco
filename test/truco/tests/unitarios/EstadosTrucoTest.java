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

    @Test (expected = NoSePuedeCantarAhoraException.class)
    public void seQuiereCuandoNoHayNadaCantado(){
        mesa.nuevaRonda();
        jugadorPepe.quiero();




    }

    @Test (expected = NoSePuedeCantarAhoraException.class)
    public void seCantaReTrucoSinElTruco(){
        mesa.nuevaRonda();
        jugadorPepe.cantarRetruco();

    }

    @Test (expected = NoSePuedeCantarAhoraException.class)
    public void seCantaValeCuatroSinElTruco(){
        mesa.nuevaRonda();
        jugadorPepe.cantarValeCuatro();

    }

    @Test (expected = NoSePuedeCantarAhoraException.class)
    public void seCantaTrucoValeCuatro(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quiero();
        jugadorPepe.cantarValeCuatro();
    }


    @Test
    public void seCantaTrucoRetrucoNoSeQuiere(){
        mesa.nuevaRonda();
        jugadorJuan.cantarTruco();
        jugadorPepe.quiero();
        jugadorPepe.cantarRetruco();
        jugadorJuan.noQuiero();
        Assert.assertEquals(2, jugadorPepe.getEquipo().getPuntaje());

    }


}
