package truco.modelo;

import truco.modelo.excepciones.EquipoInexistenteException;
import truco.modelo.excepciones.EquipoPreExistenteException;
import truco.modelo.excepciones.JugadorInexistenteException;
import truco.modelo.excepciones.JugadorPreExistenteException;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Truco {

    private List<Equipo> equipos;
    private List<Jugador> jugadoresActivos;
    private Mesa mesa;
    private boolean flor;

    /**CONSTRUCTOR**/
    public Truco(){
        equipos=new ArrayList<>();
        jugadoresActivos =new ArrayList<>();

    }

    /**GETTERS**/
    public Jugador getJugador(String nombreJugador){
        for(Jugador jugador:jugadoresActivos)
            if (jugador.getNombre().equals(nombreJugador))
                return jugador;
        throw new JugadorInexistenteException();
    }

    public Equipo getEquipo(String nombreEquipo){
        for(Equipo equipo:equipos)
            if(equipo.getNombre().equals(nombreEquipo))
                return equipo;
        throw new EquipoInexistenteException();
    }

    /**ACCIONES**/
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
            nuevoJugador.setNombre(nombreJugador);
            jugadoresActivos.add(nuevoJugador);

            return;
        }
        throw new JugadorPreExistenteException();
    }

    public void seJuegaConFlor(){
        flor=true;
    }

}

