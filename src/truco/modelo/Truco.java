package truco.modelo;

import truco.modelo.excepciones.EquipoInexistenteException;
import truco.modelo.excepciones.EquipoPreExistenteException;
import truco.modelo.excepciones.JugadorInexistenteException;
import truco.modelo.excepciones.JugadorPreExistenteException;

import java.util.ArrayList;
import java.util.List;

public class Truco {

    private List<Equipo> equipos;
    private List<Jugador> jugadores;
    private Mesa mesa;

    /**CONSTRUCTOR**/
    public Truco(){
        equipos=new ArrayList<>();
        jugadores =new ArrayList<>();
        mesa=new Mesa();

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

    /**ACCIONES**/
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

    public boolean terminado() {
        for(Equipo equipo:equipos)
            if(equipo.getPuntaje()>=30)
                return true;
        return false;
    }
}

