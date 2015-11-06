package truco.modelo;

import java.util.HashMap;
import java.util.LinkedList;

public class Ronda {

    private HashMap<String,Integer> puntos;
    private LinkedList<HashMap<Jugador,Carta>> manos;


    public HashMap<Jugador,Carta> getPrimera(){
        return manos.get(0);
    }

    public HashMap<Jugador,Carta> getSegunda(){
        return manos.get(1);
    }

    public HashMap<Jugador,Carta> getTercera(){
        return manos.get(2);
    }
}
