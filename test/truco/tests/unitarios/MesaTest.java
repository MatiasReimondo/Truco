package truco.tests.unitarios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Equipo;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.excepciones.ListaJugadoresVaciaException;

import java.util.ArrayList;
import java.util.List;

public class MesaTest {

    private Mesa mesaTester;
    private Equipo equipoPepe;
    private Equipo equipoJuan;
    private Jugador jugadorPepe;
    private Jugador jugadorJuan;
    private List<Jugador> listaJugadores;

    @Before
    public void setup() {
        mesaTester = new Mesa();

        equipoPepe = new Equipo("EquipoPepe");
        equipoJuan = new Equipo("EquipoJuan");

        jugadorJuan = new Jugador("Juan");
        jugadorPepe = new Jugador("Pepe");

        equipoPepe.agregarIntegrante(jugadorPepe);
        equipoJuan.agregarIntegrante(jugadorJuan);

        listaJugadores = new ArrayList<>();
        listaJugadores.add(jugadorJuan);
        listaJugadores.add(jugadorPepe);

        jugadorJuan.setMesa(mesaTester);
        jugadorPepe.setMesa(mesaTester);

        mesaTester.setJugadores(listaJugadores);
    }

    @Test
    public void testGetListaJugadores() {
        Assert.assertFalse(mesaTester.getJugadores() == null);
    }

    @Test
    public void testJugadorManoAlComenzar() {
        Assert.assertEquals(jugadorJuan, mesaTester.getJugadorMano());
    }

    @Test
    public void testJugadorPieAlComenzar() {
        Assert.assertEquals(jugadorPepe, mesaTester.getJugadorPie());
    }

    @Test
    public void testActualizarManoPieNoLanzaExcepcion() {
        mesaTester.actualizarJugadorManoPie();
        mesaTester.actualizarJugadorManoPie();
        mesaTester.actualizarJugadorManoPie();
        Assert.assertTrue(true);
    }

    @Test
    public void testActualizarManoPie() {
        Assert.assertEquals(jugadorJuan, mesaTester.getJugadorMano());
        Assert.assertEquals(jugadorPepe, mesaTester.getJugadorPie());
        mesaTester.actualizarJugadorManoPie();
        Assert.assertEquals(jugadorJuan, mesaTester.getJugadorMano());
        Assert.assertEquals(jugadorPepe, mesaTester.getJugadorPie());

    }

    @Test(expected = ListaJugadoresVaciaException.class)
    public void testActualizarManoPieConListaVacia() {
        List<Jugador> lista = new ArrayList<>();
        mesaTester.setJugadores(lista);
        mesaTester.actualizarJugadorManoPie();
    }

    @Test(expected = ListaJugadoresVaciaException.class)
    public void testSetJugadoresVaciaLanzaExcepcionSiEstaVacia() {
        List<Jugador> listaPrueba = new ArrayList<>();
        mesaTester.setJugadores(listaPrueba);
    }

    @Test
    public void testRepartirCartasA2Jugadores() {
        mesaTester.repartirCartas();
        Assert.assertEquals(jugadorJuan.getMano().size(),3);
        Assert.assertEquals(jugadorPepe.getMano().size(),3);
        Assert.assertEquals(mesaTester.getMazo().cantidadDeCartas(),34);
    }
}