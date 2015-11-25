package truco.modelo;

import truco.modelo.envido.Envido;
import truco.modelo.envido.EnvidoNoCantado;
import truco.modelo.flor.Flor;

import java.util.HashMap;
import java.util.Vector;

public class Ronda {

    private HashMap<Jugador,Carta> manoActual;
    private boolean primera;
    private boolean terminada;
    private Vector<Equipo> resultados;

    private Envido tanto;
    private Flor florActiva;

    /**CONSTRUCTOR**/
    public Ronda(){
        manoActual= new HashMap<Jugador,Carta>();
        primera=true;
        resultados=new Vector<>();
        tanto=new EnvidoNoCantado();
    }

    /**GETTERS**/
    public HashMap<Jugador,Carta> getManoActual(){
        return manoActual;
    }

    public Flor getFlorActiva(){
        return florActiva;
    }

    public Envido getTanto() {
        return tanto;
    }

    public Vector<Equipo> getResultados() {
        return resultados;
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
        tanto.anidarEnvido(envido);
    }

    public void activarFlor(Flor flor) {
        if(florActiva==null)
            florActiva=flor;
        else florActiva.setContraFlor(flor);
    }

    public void terminar(){
        terminada=true;
    }

    public boolean termino(){
        return terminada;
    }

    public void resultadoManoActual(Equipo equipoGanador) {
        resultados.add(equipoGanador);
    }
}
