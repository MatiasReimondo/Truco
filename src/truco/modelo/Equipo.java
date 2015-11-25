package truco.modelo;
import java.util.Map;
import java.util.HashMap;

public class Equipo {

    private int puntaje;
    private String nombre;
    private final Map<String,Jugador> integrantes;

    /**CONSTRUCTOR**/
    public Equipo(String nombreDelEquipo){
        this.nombre= nombreDelEquipo;
        this.integrantes=new HashMap<>();
    }

    /**SETTERS**/
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    /**GETTERS**/
    public Map<String,Jugador> getIntegrantes(){
        return this.integrantes;
    }

    public String getNombre(){
        return nombre;
    }
    public int getPuntaje(){
        return this.puntaje;
    }

    /**ACCIONES**/
    public void agregarIntegrante(Jugador jugador){
        this.integrantes.put(jugador.getNombre(),jugador);
    }

    public boolean jugadorPertenece(String nombreJugador){
        return this.integrantes.containsKey(nombreJugador);
    }

    public void sumarPuntos(int puntos){
        this.puntaje+=puntos;
    }

    public void resetPuntos(){
        puntaje=0;
    }
}
