package truco.tests.unitarios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Equipo;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.excepciones.LimiteDeCartasEnLaManoExcedidoException;
import truco.modelo.excepciones.ListaJugadoresVaciaException;
import truco.modelo.excepciones.MazoSinCartasException;

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

    @Test(expected = LimiteDeCartasEnLaManoExcedidoException.class)
    public void testRobarCartaLanzaExcepcion(){
        for(int i=0;i<5;i++)
            jugadorJuan.robarCartaDelMazo();
    }

    @Test
    public void testJugadorManoAlComenzar() {
        Assert.assertEquals(jugadorJuan, mesaTester.getJugadorMano());
    }

    @Test
    public void testJugadorEsPieVerdadero(){
        Assert.assertTrue(mesaTester.jugadorEsPie(jugadorPepe));
    }

    @Test
    public void testJugadorEsPieFalso(){
        Assert.assertFalse(mesaTester.jugadorEsPie(jugadorJuan));
    }

    @Test
    public void testJugadorEsManoVerdadero(){
        Assert.assertTrue(mesaTester.jugadorEsMano(jugadorJuan));
    }

    @Test
    public void testJugadorEsManoFalso(){
        Assert.assertFalse(mesaTester.jugadorEsMano(jugadorPepe));
    }
    @Test
    public void testJugadorPieAlComenzar() {
        Assert.assertTrue(mesaTester.jugadorEsPie(jugadorPepe));
    }

    /*
    @Test
    public void testActualizarManoPieNoLanzaExcepcion() {
        mesaTester.actualizarJugadorManoPie();
        mesaTester.actualizarJugadorManoPie();
        mesaTester.actualizarJugadorManoPie();
        Assert.assertTrue(true);
    }
*/
    @Test
    public void testActualizarManoPie() {
        Assert.assertEquals(jugadorJuan, mesaTester.getJugadorMano());
        Assert.assertTrue(mesaTester.jugadorEsPie(jugadorPepe));
        System.out.println("TEST");
        mesaTester.actualizarJugadorManoPie();
        Assert.assertTrue(mesaTester.jugadorEsPie(jugadorJuan));
        Assert.assertEquals(jugadorPepe, mesaTester.getJugadorMano());

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

    @Test(expected = MazoSinCartasException.class)
    public void testLanzarExcepcionCuandoElMazoNoTieneCartas(){
       for(int i=0;i<40;i++)
         mesaTester.getMazo().getCartas().removeFirst();

        jugadorJuan.robarCartaDelMazo();
    }
}