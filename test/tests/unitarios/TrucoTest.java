package tests.unitarios;

import org.junit.Before;
import org.junit.Test;
import truco.modelo.Truco;
import truco.modelo.excepciones.EquipoPreExistenteException;
import truco.modelo.excepciones.EquipoInexistenteException;
import truco.modelo.excepciones.JugadorPreExistenteException;

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

    @Test(expected = EquipoPreExistenteException.class)
    public void testLanzarExcepcionCuandoEquipoYaExiste(){
        trucoTester.nuevoEquipo("Pikachu");
    }

    @Test(expected = JugadorPreExistenteException.class)
    public void testLanzaExcepcionCuandoJugadorYaExiste(){
        trucoTester.nuevoJugador("Juan","Pikachu");
    }

    @Test(expected = EquipoInexistenteException.class)
    public void testLanzarExcepcionCuandoJugadorNoExistePeroEquipoEsInvalido(){
        trucoTester.nuevoJugador("Ricardo","Charizard");
    }
}

