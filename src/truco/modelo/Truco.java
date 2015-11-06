package truco.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Echemon on 06/11/2015.
 */
public class Truco {

    private HashMap<String,Jugador> jugadores;
    private Mazo mazo;

    public void Truco(){
        jugadores=new HashMap<>();
    }

    public Jugador getJugador(String nombreJugador){
        Jugador jugadorBuscado=jugadores.get(nombreJugador);
        if(jugadorBuscado==null)
            throw new JugadorInexistenteException();

        return jugadorBuscado;
    }

    public void resolverMano(){

    }
}

