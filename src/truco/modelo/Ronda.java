package truco.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ALL")
public class Ronda {

    private HashMap<String,Integer> puntos;
    private List<HashMap<Jugador,Carta>> listaManos;
    private HashMap<Jugador,Carta> manoActual;

    public Ronda(){
        listaManos =new ArrayList<>();
        manoActual=new HashMap<>();
    }

    public HashMap<Jugador,Carta> getPrimera(){
        return listaManos.get(0);
    }

    public HashMap<Jugador,Carta> getSegunda(){
        return listaManos.get(1);
    }

    public HashMap<Jugador,Carta> getTercera(){
        return listaManos.get(2);
    }

    public HashMap<Jugador,Carta> getManoActual(){
        return manoActual;
    }

    public void siguienteMano(){
        listaManos.add(manoActual);
        HashMap<Jugador,Carta> nuevaMano=new HashMap<>();
        manoActual=nuevaMano;
    }

    public void agregarCarta(Jugador jugador,Carta carta) {
        manoActual.put(jugador,carta);
    }
}
