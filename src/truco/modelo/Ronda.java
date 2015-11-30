package truco.modelo;

import truco.modelo.envido.Envido;
import truco.modelo.envido.EnvidoNoCantado;
import truco.modelo.estadosTruco.EstadoTruco;
import truco.modelo.estadosTruco.TrucoNoCantado;

import java.util.HashMap;
import java.util.Vector;

public class Ronda {

    private HashMap<Jugador,Carta> manoActual;
    private boolean primera;
    private boolean terminada;
    private final Vector<Equipo> resultados;
    private final Mesa mesa;

    private Envido envido;
    private Flor flor;
    private EstadoTruco truco;

    /**CONSTRUCTOR**/
    public Ronda(Mesa mesa){
        manoActual= new HashMap<>();
        primera=true;
        resultados=new Vector<>();
        envido =new EnvidoNoCantado();
        this.mesa=mesa;
        truco =new TrucoNoCantado();
    }

    /**GETTERS**/
    public HashMap<Jugador,Carta> getManoEnJuego(){
        return manoActual;
    }

    public Flor getFlor(){
        return flor;
    }

    public Envido getEnvido() {
        return envido;
    }

    public Vector<Equipo> getResultados() {
        return resultados;
    }

    public EstadoTruco getTruco(){
        return truco;
    }

    /**SETTERS**/
    public void setTrucoEnJuego(EstadoTruco estado){
        truco =estado;
    }

    /**ACCIONES**/
    public void avanzarALaSiguienteMano(){
        manoActual=new HashMap<>();
        this.primera=false;
    }

    public boolean seEstaJugandoLaPrimera(){
        return primera;
    }

    public void cambiarEnvido(){
        envido=envido.cambiarEnvido();
    }

    public void agregarCarta(Jugador jugador,Carta carta) {
        manoActual.put(jugador,carta);
    }

    public void activarFlor() {
        flor =new Flor();
    }

    public void terminar(){
        terminada=true;
        mesa.actualizarJugadorMano();
    }

    public boolean termino(){
        return terminada;
    }

    public void resultadoMano(Equipo equipoGanador) {
        resultados.add(equipoGanador);
    }
}
