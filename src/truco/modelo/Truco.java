package truco.modelo;

import truco.modelo.IA.Comportamiento;
import truco.modelo.IA.PreEnvido;
import truco.modelo.excepciones.EquipoInexistenteException;
import truco.modelo.excepciones.EquipoPreExistenteException;
import truco.modelo.excepciones.JugadorInexistenteException;
import truco.modelo.excepciones.JugadorPreExistenteException;

import java.util.ArrayList;
import java.util.List;

public class Truco {

    private List<Equipo> equipos;

    private Equipo equipo1;
    private Equipo equipo2;

    private List<Jugador> jugadores;
    private Mesa mesa;

    /**CONSTRUCTOR**/
    public Truco(){
        equipos=new ArrayList<>();
        jugadores =new ArrayList<>();
        mesa=new Mesa();
        equipo1=new Equipo();
        equipo1.setNombre("EQUIPO 1");
        equipo2=new Equipo();
        equipo2.setNombre("EQUIPO 2");

    }

    /**GETTERS**/
    public Jugador getJugador(String nombreJugador){
        for(Jugador jugador: jugadores)
            if (jugador.getNombre().equals(nombreJugador))
                return jugador;
        throw new JugadorInexistenteException();
    }

    public Mesa getMesa(){
        return mesa;
    }

    public Equipo getEquipo(String nombreEquipo){
        for(Equipo equipo:equipos)
            if(equipo.getNombre().equals(nombreEquipo))
                return equipo;
        throw new EquipoInexistenteException();
    }

    public Equipo getEquipo1(){
        return equipo1;
    }

    public Equipo getEquipo2(){
        return equipo2;
    }

    /**ACCIONES**/

    public void nuevoJuego2Jugadores(){

        jugadores.clear();
        Jugador j1=new Jugador("Jugador 1");
        Jugador j2=new Jugador("Jugador 2");

        jugadores.add(j1);
        jugadores.add(j2);

        j1.setEquipo(equipo1);
        j2.setEquipo(equipo2);

        equipo1.agregarIntegrante(j1);
        equipo2.agregarIntegrante(j2);

    }

    public void nuevoJuego4Jugadores(){

        jugadores.clear();

        Jugador j1=new Jugador("Jugador 1");
        Jugador j2=new Jugador("Jugador 2");
        Jugador j3=new Jugador("Jugador 3");
        Jugador j4=new Jugador("Jugador 4");

        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);
        jugadores.add(j4);

        j1.setEquipo(equipo1);
        j2.setEquipo(equipo2);
        j3.setEquipo(equipo1);
        j4.setEquipo(equipo2);

        equipo1.agregarIntegrante(j1);
        equipo2.agregarIntegrante(j2);
        equipo1.agregarIntegrante(j3);
        equipo2.agregarIntegrante(j4);

    }

    public void nuevoJuego6Jugadores(){

        jugadores.clear();

        Jugador j1=new Jugador("Jugador 1");
        Jugador j2=new Jugador("Jugador 2");
        Jugador j3=new Jugador("Jugador 3");
        Jugador j4=new Jugador("Jugador 4");
        Jugador j5=new Jugador("Jugador 5");
        Jugador j6=new Jugador("Jugador 6");

        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);
        jugadores.add(j4);
        jugadores.add(j5);
        jugadores.add(j6);

        j1.setEquipo(equipo1);
        j2.setEquipo(equipo2);
        j3.setEquipo(equipo1);
        j4.setEquipo(equipo2);
        j5.setEquipo(equipo1);
        j6.setEquipo(equipo2);

        equipo1.agregarIntegrante(j1);
        equipo2.agregarIntegrante(j2);
        equipo1.agregarIntegrante(j3);
        equipo2.agregarIntegrante(j4);
        equipo1.agregarIntegrante(j5);
        equipo2.agregarIntegrante(j6);
    }

    public void empezarJuego(){
        mesa.setJugadores(jugadores);
        for(Jugador jugador:jugadores)
            jugador.setMesa(mesa);
    }

    public void nuevoEquipo(String nombreEquipo){
        try{getEquipo(nombreEquipo);} catch (EquipoInexistenteException e) {
            Equipo nuevoEquipo = new Equipo(nombreEquipo);
            equipos.add(nuevoEquipo);
            return;
        }
        throw new EquipoPreExistenteException();
    }

    public void nuevoJugador(String nombreJugador,String equipo){

        this.getEquipo(equipo);
        try{this.getJugador(nombreJugador);} catch (JugadorInexistenteException e) {
            Jugador nuevoJugador = new Jugador(nombreJugador);
            nuevoJugador.setEquipo(this.getEquipo(equipo));
            jugadores.add(nuevoJugador);

            return;
        }
        throw new JugadorPreExistenteException();
    }

    public void seJuegaConFlor(){
        mesa.getArbitro().florHabilitada();
    }

    public void seJuegaSinFlor(){
        mesa.getArbitro().florDeshabilitada();
    }

    public boolean terminado() {
        for(Equipo equipo:equipos)
            if(equipo.getPuntaje()>=30)
                return true;
        return (equipo1.getPuntaje()>=30 || equipo2.getPuntaje()>=30);

    }

    public void jugadorVsIA(){
        jugadores.clear();
        Jugador jugadorHumano=new Jugador("Jugador Humano");
        jugadorHumano.setEquipo(equipo1);
        Jugador jugador=new Jugador("Robotruco");
        jugador.setEquipo(equipo2);
        jugadores.add(jugadorHumano);
        jugadores.add(jugador);

        Comportamiento comportamiento=new PreEnvido();
        comportamiento.setMesa(this.mesa);
        comportamiento.setJugador(jugador);
        mesa.setIA(comportamiento);
        mesa.setJugadorIA(jugador);
    }

    public List<Jugador> getJugadores (){
        return this.jugadores;
    }

}

