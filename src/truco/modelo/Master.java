package truco.modelo;

import truco.modelo.excepciones.CartaNoEstaEnLaManoException;
import truco.modelo.excepciones.ListaJugadoresVaciaException;
import truco.modelo.excepciones.NoEsTurnoDelJugadorException;

import java.util.*;


@SuppressWarnings("ALL")
public class Master {

    private Ronda rondaActual;
    private List<Ronda> historial;
    private CircularList<Jugador> listaJugadores;
    private Iterator<Jugador> iterMano;
    private Iterator<Jugador> iterPie;
    private Jugador jugadorMano;
    private Jugador jugadorPie;

    public Master(){
        rondaActual=new Ronda();
        historial=new ArrayList<>();
    }

    public void setJugadores(CircularList<Jugador> jugadoresActivos) {
        listaJugadores=jugadoresActivos;
        if(listaJugadores.isEmpty()) throw new ListaJugadoresVaciaException();

        iterMano=listaJugadores.iterator();
        iterPie=listaJugadores.iterator();
        for(int i=1;i<listaJugadores.size();i++)
            jugadorPie=iterPie.next();
    }

    public void actualizarJugadorManoPie(){
        if(listaJugadores.isEmpty()) throw new ListaJugadoresVaciaException();
            jugadorMano=iterMano.next();
            jugadorPie=iterPie.next();
    }

    public boolean esPie(Jugador jugador){
        return true;
    }

    public boolean esTurnoDelJugador(Jugador jugador){
        return true;
    }

    public void jugarCarta(Jugador jugador,Carta carta){
        if(!esTurnoDelJugador(jugador))
            throw new NoEsTurnoDelJugadorException();

        if(!jugador.getMano().contains(carta))
            throw new CartaNoEstaEnLaManoException();

        rondaActual.agregarCarta(jugador,carta);
        if(esPie(jugador))
            resolverMano();
    }

    public void siguienteRonda(){
        historial.add(rondaActual);
        rondaActual=new Ronda();
        actualizarJugadorManoPie();
    }

    public Jugador resolverMano(){
        int maxFuerza=0;
        Jugador jugadorMax=null;
        Set<Map.Entry<Jugador,Carta>> set=rondaActual.getManoActual().entrySet();

        for(Map.Entry<Jugador,Carta> item: set)
            if(item.getValue().getFuerza()>maxFuerza){
                maxFuerza=item.getValue().getFuerza();
                jugadorMax=item.getKey();
            }
        return jugadorMax;
    }


    public CircularList<Jugador> getJugadores() {
        return listaJugadores;
    }
}
