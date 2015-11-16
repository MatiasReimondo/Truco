package truco.modelo;

import truco.modelo.envido.Envido;
import truco.modelo.flor.Flor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ALL")
public class Ronda {

    private HashMap<String,Integer> puntos;
    private List<HashMap<Jugador,Carta>> manos;
    private HashMap<Jugador,Carta> manoActual;
    private HashMap<Jugador,Integer> tantosActuales;
    private Mesa mesa;

    private Envido tantoActivo;
    private Flor florActiva;

    /**CONSTRUCTOR**/
    public Ronda(){
        manos =new ArrayList<>(3);
        manoActual= new HashMap<Jugador,Carta>();

    }

    /**GETTERS**/
    public HashMap<Jugador,Carta> getPrimera(){
        return manos.get(0);
    }

    public HashMap<Jugador,Carta> getSegunda(){
        return manos.get(1);
    }

    public HashMap<Jugador,Carta> getTercera(){
        return manos.get(2);
    }

    public HashMap<Jugador,Carta> getManoActual(){
        return manoActual;
    }

    public HashMap<Jugador,Integer> getTantosActuales(){
        return tantosActuales;
    }

    public Flor getFlorActiva(){
        return florActiva;
    }

    /**ACCIONES**/
    public void siguienteMano(){
        manos.add(manoActual);
        HashMap<Jugador,Carta> nuevaMano=new HashMap<>();
        manoActual=nuevaMano;
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
}
