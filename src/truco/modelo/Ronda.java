package truco.modelo;

import truco.modelo.enumerables.Modificador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Ronda {

    private HashMap<String,Integer> puntos;
    private LinkedList<HashMap<Jugador,Carta>> manos;

    public Ronda(){
        manos=new LinkedList<>();
    }

    public HashMap<Jugador,Carta> getPrimera(){
        return manos.get(0);
    }

    public HashMap<Jugador,Carta> getSegunda(){
        return manos.get(1);
    }

    public HashMap<Jugador,Carta> getTercera(){
        return manos.get(2);
    }


    public void agregarCarta(Carta carta) {
    }
}
