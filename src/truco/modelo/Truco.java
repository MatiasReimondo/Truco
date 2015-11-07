package truco.modelo;

import java.util.HashMap;

public class Truco {

    private Mazo mazo;
    private HashMap<String,Equipo> tablaEquipos;

    public void Truco(){
        mazo=new Mazo();
        tablaEquipos=new HashMap<>();
    }

    public void nuevoEquipo(String nombreEquipo){
        Equipo nuevoEquipo=new Equipo();
        nuevoEquipo.setNombre(nombreEquipo);
        tablaEquipos.put(nombreEquipo,nuevoEquipo);
    }

    public Jugador getJugador(String nombreJugador){
        if(equipoA.getIntegrantes().containsKey(nombreJugador))
            return equipoA.getIntegrantes().get(nombreJugador);
        if(equipoB.getIntegrantes().containsKey(nombreJugador))
            return equipoB.getIntegrantes().get(nombreJugador);
        throw new JugadorInexistenteException();
    }

    public void nuevoJugador(String nombreJugador,String equipo){
        Jugador nuevoJugador=new Jugador();
        nuevoJugador.setNombre(nombreJugador);
    }

    public void resolverMano(){
    }


}

