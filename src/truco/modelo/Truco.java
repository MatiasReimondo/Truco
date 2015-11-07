package truco.modelo;

import truco.modelo.excepciones.EquipoInexistenteException;
import truco.modelo.excepciones.EquipoYaExisteException;
import truco.modelo.excepciones.JugadorInexistenteException;
import truco.modelo.excepciones.JugadorYaExisteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Truco {

    private Mazo mazo;
    private List<Equipo> equipos;

    public void Truco(){
        mazo=new Mazo();
        equipos=new ArrayList<>();
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
        try{getEquipo(nombreEquipo)} catch (EquipoInexistenteException e) {
            Equipo nuevoEquipo = new Equipo();
            nuevoEquipo.setNombre(nombreEquipo);
            equipos.add(nuevoEquipo);
            return;
        }
        throw new EquipoYaExisteException();
    }

    public void nuevoJugador(String nombreJugador,String equipo){

        try{getJugador(nombreJugador)} catch (JugadorInexistenteException e) {
            Jugador nuevoJugador = new Jugador();
            nuevoJugador.setNombre(nombreJugador);
            return;
        }
        throw new JugadorYaExisteException();
    }



    public void resolverMano(){
    }


}

