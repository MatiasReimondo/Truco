package truco.modelo;

import truco.modelo.envido.Envido;
import truco.modelo.flor.Flor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("ALL")
public class Ronda {

    private HashMap<Jugador,Carta> manoActual;
    private Mesa mesa;
    boolean primera;
    private Vector<Equipo> resultados;

    private Envido tantoActivo;
    private Flor florActiva;

    /**CONSTRUCTOR**/
    public Ronda(){
        manoActual= new HashMap<Jugador,Carta>();
        primera=true;
        resultados=new Vector<>();
    }

    public HashMap<Jugador,Carta> getManoActual(){
        return manoActual;
    }

    public Flor getFlorActiva(){
        return florActiva;
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

    public Envido getTantoActivo() {
        return tantoActivo;
    }

    public void activarTanto(Envido envido) {
        if(tantoActivo==null)
            tantoActivo=envido;
        else tantoActivo.setSubEnvido(envido);
    }
    public void activarFlor(Flor flor) {
        if(florActiva==null)
            florActiva=flor;
        else florActiva.setContraFlor(flor);
    }

    public Vector<Equipo> getResultados() {
        return resultados;
    }

    public boolean termino(){
        return resultados.size()==2;
    }

    public void agregarResultado(Equipo equipoGanador) {
        resultados.add(equipoGanador);
    }
}
