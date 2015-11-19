package truco.modelo;


import truco.modelo.excepciones.CantidadDeJugadoresDebeSerNumeroParException;
import truco.modelo.excepciones.CantidadDeJugadoresInsuficienteException;
import truco.modelo.excepciones.ListaJugadoresVaciaException;

import java.util.List;

public class Juez {

    private Mesa mesa;

    public Juez(){}

    public void evaluarListaDeJugadores(List<Jugador> jugadores){

        if(jugadores.isEmpty()) throw new ListaJugadoresVaciaException();
        if(jugadores.size()<2) throw new CantidadDeJugadoresInsuficienteException();
        if(jugadores.size()%2!=0) throw new CantidadDeJugadoresDebeSerNumeroParException();
    }

    public boolean jugadorEsPie(Jugador jugador){

        if(mesa.getJugadores().size()==2)
            return mesa.getJugadores().get(1).getNombre().equals(jugador.getNombre());

        return (mesa.getJugadorPieEquipo1().getNombre().equals(jugador.getNombre()) || mesa.getJugadorPieEquipo2().getNombre().equals(jugador.getNombre()));
    }

    public boolean jugadorEsMano(Jugador jugador){

        return mesa.getJugadorMano().getNombre().equals(jugador.getNombre());
    }

    public boolean jugadorPuedeCantarTanto(Jugador jugador){
        return true;
    }

}
