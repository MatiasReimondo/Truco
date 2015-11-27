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

    private Envido tantoEnJuego;
    private Envido tantoPendiente;
    private Flor florEnJuego;
    private EstadoTruco trucoEnJuego;

    /**CONSTRUCTOR**/
    public Ronda(Mesa mesa){
        manoActual= new HashMap<>();
        primera=true;
        resultados=new Vector<>();
        tantoEnJuego =new EnvidoNoCantado();
        tantoPendiente=new EnvidoNoCantado();
        this.mesa=mesa;
        trucoEnJuego=new TrucoNoCantado();
    }

    /**GETTERS**/
    public HashMap<Jugador,Carta> getManoActual(){
        return manoActual;
    }

    public Flor getFlorEnJuego(){
        return florEnJuego;
    }

    public Envido getTantoEnJuego() {
        return tantoEnJuego;
    }

    public Vector<Equipo> getResultados() {
        return resultados;
    }

    public EstadoTruco getTrucoEnJuego(){
        return trucoEnJuego;
    }

    public Envido getTantoPendiente(){
        return tantoPendiente;
    }

    /**SETTERS**/
    public void setTrucoEnJuego(EstadoTruco estado){
        trucoEnJuego=estado;
    }

    /**ACCIONES**/
    public void avanzarALaSiguienteMano(){
        manoActual=new HashMap<>();
        this.primera=false;
    }

    public boolean seEstaJugandoLaPrimera(){
        return primera;
    }

    public void agregarCarta(Jugador jugador,Carta carta) {
        manoActual.put(jugador,carta);
    }

    public void subirApuestaDelEnvido(Envido envido) {
        tantoPendiente=envido;
    }

    public void cambiarTantoEnJuego(){
         tantoEnJuego.anidarEnvido(tantoPendiente);
    }

    public void activarFlor() {
        florEnJuego =new Flor();
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
