package truco.modelo;

import truco.modelo.excepciones.EquipoInexistenteException;
import truco.modelo.excepciones.EquipoExistenteException;
import truco.modelo.excepciones.JugadorInexistenteException;
import truco.modelo.excepciones.JugadorExistenteException;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Truco {

    private Mazo mazo;
    private List<Equipo> equipos;
    private CircularList<Jugador> jugadoresActivos;
    private Master master;

    public Truco(){
        mazo=new Mazo();
        equipos=new ArrayList<>();
        jugadoresActivos =new CircularList<>();
        master =new Master();
        master.setJugadores(jugadoresActivos);

    }

    public Mazo getMazo(){
        return mazo;
    }

    public Jugador getJugador(String nombreJugador){
        for(Equipo equipo:equipos)
            if (equipo.getIntegrantes().containsKey(nombreJugador))
                return equipo.getIntegrantes().get(nombreJugador);
        throw new JugadorInexistenteException();
    }

    public Equipo getEquipo(String nombreEquipo){
        for(Equipo equipo:equipos)
            if(equipo.getNombre().equals(nombreEquipo))
                return equipo;
        throw new EquipoInexistenteException();
    }

    public void nuevoEquipo(String nombreEquipo){
        try{getEquipo(nombreEquipo);} catch (EquipoInexistenteException e) {
            Equipo nuevoEquipo = new Equipo();
            nuevoEquipo.setNombre(nombreEquipo);
            equipos.add(nuevoEquipo);
            return;
        }
        throw new EquipoExistenteException();
    }

    public void nuevoJugador(String nombreJugador,String equipo){

        try{getJugador(nombreJugador);} catch (JugadorInexistenteException e) {
            Jugador nuevoJugador = new Jugador();
            nuevoJugador.setNombre(nombreJugador);
            return;
        }
        throw new JugadorExistenteException();
    }

    public void nuevaRonda(){
        master.actualizarJugadorManoPie();
        mazo.mezclar();


    }

}

