package truco.tests.unitarios;

import org.junit.Before;
import org.junit.Test;
import truco.modelo.Truco;
import truco.modelo.excepciones.EquipoExistenteException;
import truco.modelo.excepciones.EquipoInexistenteException;
import truco.modelo.excepciones.JugadorExistenteException;

/**
 * Created by Eze Cruz Avila on 15/11/2015.
 */
public class TrucoTest {

    private Truco trucoTester;

    @Before
    public void setup(){
        trucoTester=new Truco();
        trucoTester.nuevoEquipo("Pikachu");
        trucoTester.nuevoJugador("Juan","Pikachu");
    }

    @Test(expected = EquipoExistenteException.class)
    public void testLanzarExcepcionCuandoEquipoYaExiste(){
        trucoTester.nuevoEquipo("Pikachu");
    }

    @Test(expected = JugadorExistenteException.class)
    public void testLanzaExcepcionCuandoJugadorYaExiste(){
        System.out.println("TEST");
        trucoTester.nuevoJugador("Juan","Pikachu");
    }

    @Test(expected = EquipoInexistenteException.class)
    public void testLanzarExcepcionCuandoJugadorNoExistePeroEquipoEsInvalido(){
        trucoTester.nuevoJugador("Ricardo","Charizard");
    }
}

