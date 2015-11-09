package truco.modelo;

import truco.modelo.excepciones.CartaNoEstaEnLaManoException;
import truco.modelo.excepciones.ListaJugadoresVaciaException;
import truco.modelo.excepciones.NoEsTurnoDelJugadorException;

import java.util.*;


@SuppressWarnings("ALL")
public class Master {

    private Ronda rondaActual;
    private List<Ronda> historial;
    private List<Jugador> listaJugadores;
    private Iterator<Jugador> iterMano;
    private Iterator<Jugador> iterPie;
    private Jugador jugadorMano;
    private Jugador jugadorPie;

    public Master(){
        rondaActual=new Ronda();
        historial=new ArrayList<>();
    }

    public void setJugadores(List<Jugador> jugadoresActivos) {
        if(jugadoresActivos.isEmpty()) throw new ListaJugadoresVaciaException();

        listaJugadores=jugadoresActivos;

        iterMano=listaJugadores.iterator();
        iterPie=listaJugadores.iterator();
        while(iterPie.hasNext())
            iterPie.next();

        jugadorMano=listaJugadores.get(0);
        jugadorPie=listaJugadores.get(listaJugadores.size()-1);
    }

    public Jugador getJugadorMano(){
        return jugadorMano;
    }

    public Jugador getJugadorPie(){
        return jugadorPie;
    }

    public void actualizarJugadorManoPie(){
        if(listaJugadores.isEmpty()) throw new ListaJugadoresVaciaException();

        if(iterMano.hasNext())
            jugadorMano=iterMano.next();
        else iterMano=listaJugadores.iterator();

        if(iterPie.hasNext())
            jugadorPie=iterPie.next();
        else iterPie=listaJugadores.iterator();
    }

    public boolean jugadorEsPie(Jugador jugador){
       return jugador.getNombre().equals(jugadorPie.getNombre());
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
        if(jugadorEsPie(jugador))
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


    public List<Jugador> getJugadores() {
        return listaJugadores;
    }
}
