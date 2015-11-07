package truco.modelo;

import java.util.HashMap;

public class Equipo extends Jugador {

    private int puntaje;
    private HashMap<String,Jugador> integrantes;

    public Equipo(){
        integrantes=new HashMap<>();
    }

    public HashMap<String,Jugador> getIntegrantes(){
        return integrantes;
    }

    public int getPuntaje(){
        return puntaje;
    }

    public void agregarIntegrante(Jugador jugador){
        integrantes.put(jugador.getNombre(),jugador);
    }

    public boolean jugadorPertenece(String nombreJugador){
        return integrantes.containsKey(nombreJugador);
    }

    public void sumarPuntos(int puntos){
        puntaje+=puntos;
    }
}
