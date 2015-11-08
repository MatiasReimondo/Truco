package truco.modelo;
import java.util.Map;
import java.util.HashMap;

public class Equipo extends Jugador {

    private int puntaje;
    private Map<String,Jugador> integrantes;

    public Equipo(){
        this.integrantes=new HashMap<String,Jugador>();
    }

    public Map<String,Jugador> getIntegrantes(){
        return this.integrantes;
    }

    public int getPuntaje(){
        return this.puntaje;
    }

    public void agregarIntegrante(Jugador jugador){
        this.integrantes.put(jugador.getNombre(),jugador);
    }

    public boolean jugadorPertenece(String nombreJugador){
        return this.integrantes.containsKey(nombreJugador);
    }

    public void sumarPuntos(int puntos){
        this.puntaje+=puntos;
    }
}
