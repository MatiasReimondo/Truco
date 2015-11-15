package truco.modelo;

import truco.modelo.excepciones.EquipoInexistenteException;
import truco.modelo.excepciones.EquipoExistenteException;
import truco.modelo.excepciones.JugadorInexistenteException;
import truco.modelo.excepciones.JugadorExistenteException;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Truco {

    private List<Equipo> equipos;
    private List<Jugador> jugadoresActivos;
    private Mesa mesa;

    public Truco(){
        equipos=new ArrayList<>();
        jugadoresActivos =new ArrayList<>();

    }

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

    public void nuevoEquipo(String nombreEquipo){
        try{getEquipo(nombreEquipo);} catch (EquipoInexistenteException e) {
            Equipo nuevoEquipo = new Equipo(nombreEquipo);
            equipos.add(nuevoEquipo);
            return;
        }
        throw new EquipoExistenteException();
    }

    public void nuevoJugador(String nombreJugador,String equipo){

        this.getEquipo(equipo);
        try{this.getJugador(nombreJugador);} catch (JugadorInexistenteException e) {
            System.out.println("PASO POR ACA");
            Jugador nuevoJugador = new Jugador(nombreJugador);
            nuevoJugador.setNombre(nombreJugador);
            jugadoresActivos.add(nuevoJugador);

            return;
        }
        throw new JugadorExistenteException();
    }

}

