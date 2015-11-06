package truco.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

