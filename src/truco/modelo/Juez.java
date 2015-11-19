package truco.modelo;


import truco.modelo.excepciones.CantidadDeJugadoresDebeSerNumeroParException;
import truco.modelo.excepciones.CantidadDeJugadoresInsuficienteException;
import truco.modelo.excepciones.ListaJugadoresVaciaException;
import truco.modelo.excepciones.MaximoDeJugadoresExcedidoException;

import java.util.List;

public class Juez {

    private Mesa mesa;

    public Juez(){}

    public void setMesa(Mesa mesa){
        this.mesa=mesa;
    }

    public void evaluarListaDeJugadores(List<Jugador> jugadores){

        if(jugadores.isEmpty()) throw new ListaJugadoresVaciaException();
        if(jugadores.size()<2) throw new CantidadDeJugadoresInsuficienteException();
        if(jugadores.size()%2!=0) throw new CantidadDeJugadoresDebeSerNumeroParException();
        if(jugadores.size()>6) throw new MaximoDeJugadoresExcedidoException();
    }

    public boolean jugadorEsMano(Jugador jugador){
        return mesa.getJugadorMano().getNombre().equals(jugador.getNombre());
    }

    public boolean jugadorEsPie(Jugador jugador){
        switch (mesa.getNroJugadores()){
            case 2: return jugador.equals(mesa.getJugadores().get(1));
            case 4: return (jugador.equals(mesa.getJugadores().get(2)) || jugador.equals(mesa.getJugadores().get(3)));
            case 6: return (jugador.equals(mesa.getJugadores().get(4)) || jugador.equals(mesa.getJugadores().get(5)));
        }
        return false;
    }

    public boolean esTurnoDelJugador(Jugador jugador){
        return jugador.equals(mesa.getJugadorActivo());
    }

    public boolean jugadorPuedeCantarTanto(Jugador jugador){
        if(mesa.getJuez().jugadorEsPie(mesa.getJugadorActivo()) && mesa.getRonda().seEstaJugandoLaPrimera())
            return true;
        if(mesa.getNroJugadores()==2 && mesa.getRonda().seEstaJugandoLaPrimera())
            return true;
        return false;
    }

}
