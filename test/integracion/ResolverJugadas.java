package integracion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import truco.modelo.*;
import truco.modelo.envido.Envido;
import truco.modelo.envido.FaltaEnvido;
import truco.modelo.envido.RealEnvido;
import truco.modelo.estadosTruco.RetrucoCantado;
import truco.modelo.estadosTruco.RetrucoQuerido;
import truco.modelo.estadosTruco.TrucoCantado;
import truco.modelo.estadosTruco.TrucoQuerido;
import truco.modelo.excepciones.JugadorNoTieneFlorException;
import truco.modelo.excepciones.FlorNoHabilitadaException;

import java.util.ArrayList;
import java.util.List;

public class ResolverJugadas {

    private Mesa mesaTester;
    private Equipo equipoMano;
    private Equipo equipoPie;
    private Jugador jugadorMano;
    private Jugador jugadorPie;
    private List<Jugador> listaJugadores;

    @Before
    public void setup(){
        mesaTester =new Mesa();

        equipoMano =new Equipo("EquipoPepe");
        equipoPie =new Equipo("EquipoJuan");

        jugadorPie =new Jugador("Pie");
        jugadorMano =new Jugador("Mano");

        equipoMano.agregarIntegrante(jugadorMano);
        equipoPie.agregarIntegrante(jugadorPie);

        jugadorPie.setEquipo(equipoPie);
        jugadorMano.setEquipo(equipoMano);

        listaJugadores=new ArrayList<>();
        listaJugadores.add(jugadorMano);
        listaJugadores.add(jugadorPie);

        jugadorPie.setMesa(mesaTester);
        jugadorMano.setMesa(mesaTester);

        mesaTester.setJugadores(listaJugadores);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Test
    public void testCartasDeMismaFuerzaEmpatan(){
        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(3, Palo.COPA));

        jugadorMano.jugarCarta(3, Palo.BASTO);
        jugadorPie.jugarCarta(3, Palo.COPA);
        mesaTester.resolverMano();


        Assert.assertFalse(mesaTester.getRonda().getResultados().contains(jugadorPie));
        Assert.assertFalse(mesaTester.getRonda().getResultados().contains(jugadorMano));
    }

    @Test (expected=JugadorNoTieneFlorException.class)
    public void testSeCantaFlorSinTenerFlor(){
        mesaTester.nuevaRonda();
        mesaTester.getArbitro().florHabilitada();

        jugadorMano.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(6, Palo.COPA));

        jugadorPie.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(1, Palo.COPA));

        jugadorMano.cantarFlor() ;

    }

    @Test (expected=FlorNoHabilitadaException.class)
    public void testSeCantaFlorPeroSeEstaJugnadoSinFlor(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6, Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(1, Palo.COPA));

        jugadorMano.cantarFlor() ;

    }

    @Test
    public void testSeCantaFlorYelJugadorTiene28(){

        mesaTester.getArbitro().florHabilitada();
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(1, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(7, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(10, Palo.ESPADA));

        jugadorPie.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(1, Palo.COPA));

        jugadorMano.cantarFlor() ;
        Assert.assertEquals(28, jugadorMano.getFlor());

    }

    @Test
    public void testResolverFlor(){
        mesaTester.getArbitro().florHabilitada();
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(1, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(7, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(10, Palo.ESPADA));

        jugadorPie.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(1, Palo.BASTO));

        jugadorMano.cantarFlor();
        jugadorPie.quieroFlor();
        mesaTester.resolverFlor();

        Assert.assertEquals(3,equipoMano.getPuntaje());
    }

    @Test
    public void testResolverFlorConContraflor(){
        mesaTester.getArbitro().florHabilitada();
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(1, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(7, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(10, Palo.ESPADA));

        jugadorPie.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(1, Palo.BASTO));

        jugadorMano.cantarFlor();
        jugadorPie.quieroFlor();
        jugadorPie.cantarContraFlor();
        jugadorMano.quieroFlor();
        mesaTester.resolverFlor();

        Assert.assertEquals(6,equipoMano.getPuntaje());
    }

    @Test
    public void testResolverFlorConContraflorAlResto(){
        mesaTester.getArbitro().florHabilitada();
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(1, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(7, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(10, Palo.ESPADA));

        jugadorPie.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(1, Palo.BASTO));

        jugadorMano.cantarFlor();
        jugadorPie.quieroFlor();
        jugadorPie.cantarContraflorAlResto();
        jugadorMano.quieroFlor();
        mesaTester.resolverFlor();

        Assert.assertEquals(30,equipoMano.getPuntaje());
    }
    @Test
    public void testSeCantaEnvidoYelJugadorTiene28(){

        mesaTester.nuevaRonda();
        jugadorPie.levantarCarta(new Carta(1, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(7, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(2, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(1, Palo.COPA));

        jugadorPie.cantarEnvido(new Envido());
        Assert.assertEquals(25, jugadorMano.getEnvido());



    }

    @Test
    public void testResolverUnaMano(){

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6, Palo.BASTO));

        jugadorMano.jugarCarta(5, Palo.BASTO);
        jugadorPie.jugarCarta(6, Palo.BASTO);

        mesaTester.resolverMano();

        Assert.assertTrue(mesaTester.getRonda().getResultados().contains(equipoPie));
    }

    @Test
    public void testRondaPardaGanaEquipoMano(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(5, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(6, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(6, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(10, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.jugarCarta(5, Palo.BASTO);
        jugadorPie.jugarCarta(5, Palo.ESPADA);
        mesaTester.resolverMano();
        Assert.assertFalse(mesaTester.getRonda().getResultados().contains(equipoPie));
        Assert.assertFalse(mesaTester.getRonda().getResultados().contains(equipoMano));

        jugadorMano.jugarCarta(6, Palo.ESPADA);
        jugadorPie.jugarCarta(6, Palo.BASTO);
        mesaTester.resolverMano();
        Assert.assertFalse(mesaTester.getRonda().getResultados().contains(equipoPie));
        Assert.assertFalse(mesaTester.getRonda().getResultados().contains(equipoMano));

        jugadorMano.jugarCarta(10, Palo.ESPADA);
        jugadorPie.jugarCarta(10, Palo.BASTO);
        mesaTester.resolverMano();
        Assert.assertFalse(mesaTester.getRonda().getResultados().contains(equipoPie));
        Assert.assertFalse(mesaTester.getRonda().getResultados().contains(equipoMano));

        Assert.assertEquals(1, equipoMano.getPuntaje());

    }

    @Test
    public void testManoGanaPrimeraYSegunda(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(2, Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(7, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.jugarCarta(3,Palo.BASTO);
        jugadorPie.jugarCarta(5,Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(2,Palo.BASTO);
        jugadorPie.jugarCarta(7,Palo.BASTO);
        mesaTester.resolverMano();

        Assert.assertEquals(1,equipoMano.getPuntaje());
        Assert.assertEquals(0,equipoPie.getPuntaje());
    }

    @Test
    public void testPieGanaPrimeraYSegunda(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(2, Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(1,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.jugarCarta(4,Palo.BASTO);
        jugadorPie.jugarCarta(3,Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(2,Palo.BASTO);
        jugadorPie.jugarCarta(1,Palo.BASTO);
        mesaTester.resolverMano();

        Assert.assertEquals(0,equipoMano.getPuntaje());
        Assert.assertEquals(1,equipoPie.getPuntaje());
    }

    @Test
    public void testManoGanaPrimeraPierdeSegundaGanaTercera(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(2, Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(7, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.jugarCarta(3,Palo.BASTO);
        jugadorPie.jugarCarta(5,Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(4,Palo.BASTO);
        jugadorPie.jugarCarta(7,Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(2,Palo.BASTO);
        jugadorPie.jugarCarta(10,Palo.BASTO);
        mesaTester.resolverMano();

        Assert.assertEquals(1,equipoMano.getPuntaje());
        Assert.assertEquals(0,equipoPie.getPuntaje());
    }

    @Test
    public void testPardaPrimeraYSegundaPieGanaLaTercera(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(5, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(6, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(6, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(10, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(11, Palo.BASTO));

        jugadorMano.jugarCarta(5, Palo.BASTO);
        jugadorPie.jugarCarta(5, Palo.ESPADA);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(6, Palo.ESPADA);
        jugadorPie.jugarCarta(6, Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(10, Palo.ESPADA);
        jugadorPie.jugarCarta(11, Palo.BASTO);
        mesaTester.resolverMano();

        Assert.assertEquals(1, equipoPie.getPuntaje());

    }

    @Test
    public void testPardaPrimeraYSegundaManoGanaLaTercera(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(5, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(6, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(6, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(11, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.jugarCarta(5, Palo.BASTO);
        jugadorPie.jugarCarta(5, Palo.ESPADA);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(6, Palo.ESPADA);
        jugadorPie.jugarCarta(6, Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(11, Palo.ESPADA);
        jugadorPie.jugarCarta(10, Palo.BASTO);
        mesaTester.resolverMano();

        Assert.assertEquals(1, equipoMano.getPuntaje());
        Assert.assertEquals(0,equipoPie.getPuntaje());

    }

    @Test
    public void testPardaLaPrimeraGanaManoLaSegunda(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(5, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(6, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(6, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(11, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.jugarCarta(5, Palo.BASTO);
        jugadorPie.jugarCarta(5, Palo.ESPADA);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(11, Palo.ESPADA);
        jugadorPie.jugarCarta(6, Palo.BASTO);
        mesaTester.resolverMano();

        Assert.assertEquals(1, equipoMano.getPuntaje());
        Assert.assertEquals(0,equipoPie.getPuntaje());

    }

    @Test
    public void testPardaLaPrimeraGanaPieLaSegunda(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(5, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(5, Palo.ESPADA));
        jugadorMano.levantarCarta(new Carta(6, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(6, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(11, Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.jugarCarta(5, Palo.BASTO);
        jugadorPie.jugarCarta(5, Palo.ESPADA);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(6, Palo.ESPADA);
        jugadorPie.jugarCarta(10, Palo.BASTO);
        mesaTester.resolverMano();

        Assert.assertEquals(0, equipoMano.getPuntaje());
        Assert.assertEquals(1,equipoPie.getPuntaje());

    }

    @Test
    public void testManoGanaPrimeraYSegundaConTruco(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(2, Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(7, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        mesaTester.jugadorAnterior();
        jugadorMano.jugarCarta(3,Palo.BASTO);
        jugadorPie.jugarCarta(5,Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(2,Palo.BASTO);
        jugadorPie.jugarCarta(7,Palo.BASTO);
        mesaTester.resolverMano();

        Assert.assertEquals(2,equipoMano.getPuntaje());
        Assert.assertEquals(0,equipoPie.getPuntaje());
    }

    @Test
    public void testTrucoNoQueridoAsigna1Punto(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.noQuieroTruco();
        Assert.assertEquals(1,equipoMano.getPuntaje());
    }

    @Test
    public void testValeCuatroNoQueridoSon3Puntos(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarTruco();
        jugadorPie.quieroTruco();
        jugadorPie.cantarRetruco();
        jugadorMano.quieroTruco();
        jugadorMano.cantarValeCuatro();
        jugadorPie.noQuieroTruco();
        Assert.assertEquals(3,equipoMano.getPuntaje());
    }
    @Test
    public void testManoGanaPrimeraYSegundaConRetruco(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(4, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(3, Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(2, Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(7, Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(10, Palo.BASTO));

        jugadorMano.cantarTruco();
        Assert.assertTrue(mesaTester.getRonda().getTrucoEnJuego().getClass().equals(TrucoCantado.class));
        jugadorPie.quieroTruco();
        Assert.assertTrue(mesaTester.getRonda().getTrucoEnJuego().getClass().equals(TrucoQuerido.class));
        jugadorPie.cantarRetruco();
        Assert.assertTrue(mesaTester.getRonda().getTrucoEnJuego().getClass().equals(RetrucoCantado.class));
        jugadorMano.quieroTruco();
        Assert.assertTrue(mesaTester.getRonda().getTrucoEnJuego().getClass().equals(RetrucoQuerido.class));
        jugadorMano.jugarCarta(3,Palo.BASTO);
        jugadorPie.jugarCarta(5,Palo.BASTO);
        mesaTester.resolverMano();

        jugadorMano.jugarCarta(2,Palo.BASTO);
        jugadorPie.jugarCarta(7,Palo.BASTO);
        mesaTester.resolverMano();

        Assert.assertEquals(3,equipoMano.getPuntaje());
        Assert.assertEquals(0,equipoPie.getPuntaje());
    }

    @Test
    public void testEnvidoNoQueridoSuma1(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.noQuieroEnvido();

        Assert.assertEquals(1,equipoMano.getPuntaje());
    }

    @Test
    public void testEnvidoEnvidoNoQueridoSuma2(){
        mesaTester.nuevaRonda();
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        jugadorPie.cantarEnvido(new Envido());
        jugadorMano.noQuieroEnvido();
        Assert.assertEquals(2,equipoPie.getPuntaje());
    }

    @Test
    public void testManoGanaConEnvidoMasGrande(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));

        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();

        Assert.assertEquals(2,equipoMano.getPuntaje());
    }

    @Test
    public void testManoGanaEnvidoEnvidoRealEnvido(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));

        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        jugadorPie.cantarEnvido(new Envido());
        jugadorMano.aceptarEnvido();
        jugadorMano.cantarEnvido(new RealEnvido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();

        Assert.assertEquals(7,equipoMano.getPuntaje());
    }

    @Test
    public void testPieGanaConEnvidoMasGrande(){
        mesaTester.nuevaRonda();

        jugadorMano.levantarCarta(new Carta(4,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));

        jugadorPie.levantarCarta(new Carta(7,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));

        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();

        Assert.assertEquals(2,equipoPie.getPuntaje());
    }

    @Test
    public void testManoGanaAlgunasCombinacionesDeTanto(){

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(2,equipoMano.getPuntaje());
        equipoMano.resetPuntos();

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new RealEnvido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(3,equipoMano.getPuntaje());
        equipoMano.resetPuntos();

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(4,equipoMano.getPuntaje());
        equipoMano.resetPuntos();

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        jugadorMano.cantarEnvido(new RealEnvido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(5,equipoMano.getPuntaje());
        equipoMano.resetPuntos();

    }

    @Test
    public void testManoGanaElEnvidoPorSerMano(){
        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(4,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new Envido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(2,equipoMano.getPuntaje());

    }

    @Test
    public void testFaltaEnvidoLos2EquiposEnCeroPuntos(){
            mesaTester.nuevaRonda();
            jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
            jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
            jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
            jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
            jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
            jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
            jugadorMano.cantarEnvido(new FaltaEnvido());
            jugadorPie.aceptarEnvido();
            mesaTester.resolverEnvido();
            Assert.assertEquals(30,equipoMano.getPuntaje());
    }

    @Test
    public void testFaltaEnvidoLos2EquiposEnLasMalasConVentajaDeAlgunEquipo(){
        equipoPie.sumarPuntos(2);

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new FaltaEnvido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(30,equipoMano.getPuntaje());
    }

    @Test
    public void testFaltaEnvidoUnEquipoEnLasBuenasOtroEnLasMalasGanaEquipoEnLasMalas(){
        equipoPie.sumarPuntos(18);

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new FaltaEnvido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(12,equipoMano.getPuntaje());
    }

    @Test
    public void testRealEnvidoYFaltaEnvidoUnEquipoEnLasBuenasOtroEnLasMalasGanaEquipoEnLasMalas(){
        equipoPie.sumarPuntos(18);

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new RealEnvido());
        jugadorPie.aceptarEnvido();
        jugadorPie.cantarEnvido(new FaltaEnvido());
        jugadorMano.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(12,equipoMano.getPuntaje());
    }

    @Test
    public void testFaltaEnvidoUnEquipoEnLasBuenasOtroEnLasMalasGanaEquipoEnLasBuenas(){
        equipoPie.sumarPuntos(18);

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(4,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(7,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new FaltaEnvido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(30,equipoPie.getPuntaje());
    }

    @Test
    public void testFaltaEnvidoLos2EquiposEnLasBuenasGanaEquipoConVentaja(){
        equipoPie.sumarPuntos(18);
        equipoMano.sumarPuntos(16);

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(4,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(7,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new FaltaEnvido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(32,equipoPie.getPuntaje());
    }

    @Test
    public void testFaltaEnvidoLos2EquiposEnLasBuenasGanaEquipoEnDesventaja(){
        equipoPie.sumarPuntos(18);
        equipoMano.sumarPuntos(16);

        mesaTester.nuevaRonda();
        jugadorMano.levantarCarta(new Carta(7,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(6,Palo.BASTO));
        jugadorMano.levantarCarta(new Carta(5,Palo.BASTO));
        jugadorPie.levantarCarta(new Carta(6,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(5,Palo.ESPADA));
        jugadorPie.levantarCarta(new Carta(4,Palo.ESPADA));
        jugadorMano.cantarEnvido(new FaltaEnvido());
        jugadorPie.aceptarEnvido();
        mesaTester.resolverEnvido();
        Assert.assertEquals(28,equipoMano.getPuntaje());
    }


}
