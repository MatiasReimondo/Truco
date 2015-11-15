package truco.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ALL")
public class Ronda {

    private HashMap<String,Integer> puntos;
    private HashMap<Jugador,Carta> primera;
    private HashMap<Jugador,Carta> segunda;
    private HashMap<Jugador,Carta> tercera;
    private HashMap<Jugador,Carta> manoActual;
    private HashMap<Jugador,Integer> tantosActuales;

    public Ronda(){
        primera=new HashMap<>();
        segunda=new HashMap<>();
        tercera=new HashMap<>();
        manoActual=primera;
    }

    public HashMap<Jugador,Carta> getPrimera(){
        return primera;
    }

    public HashMap<Jugador,Carta> getSegunda(){
        return segunda;
    }

    public HashMap<Jugador,Carta> getTercera(){
        return tercera;
    }

    public HashMap<Jugador,Carta> getManoActual(){
        return manoActual;
    }

    public HashMap<Jugador,Integer> getTantosActuales(){
        return tantosActuales;
    }

    public void siguienteMano(){
    }

    public void agregarCarta(Jugador jugador,Carta carta) {
        manoActual.put(jugador,carta);
    }
}
