package truco.tests.unitarios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.Equipo;
import truco.modelo.Jugador;

public class EquipoTest {

    private Equipo equipoTester;

    @Before
    public void setup(){
        equipoTester=new Equipo("Pikachu");
    }

    @Test
    public void testSetNombre(){
        equipoTester.setNombre("Charizard");
        Assert.assertEquals(equipoTester.getNombre(),"Charizard");
    }

    @Test
    public void testGetPuntajeInicialmenteCero(){
        Assert.assertEquals(equipoTester.getPuntaje(),0);
    }

    @Test
    public void testAgregarIntegrante(){
        equipoTester.agregarIntegrante(new Jugador("Pepe"));
        Assert.assertTrue(equipoTester.getIntegrantes().containsKey("Pepe"));
    }

    @Test
    public void testJugadorPertenece(){
        equipoTester.agregarIntegrante(new Jugador("Pepe"));
        Assert.assertTrue(equipoTester.jugadorPertenece("Pepe"));
    }

    @Test
    public void testSumarPuntos(){
        equipoTester.sumarPuntos(8);
        Assert.assertEquals(equipoTester.getPuntaje(),8);
    }
}
