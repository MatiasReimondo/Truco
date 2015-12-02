package truco.modelo;


import truco.modelo.estadosTruco.TrucoCantado;
import truco.modelo.excepciones.*;

import java.util.List;

public class Arbitro {

    private Mesa mesa;
    private boolean flor;

    public Arbitro(){}

    public void setMesa(Mesa mesa){
        this.mesa=mesa;
    }

    public boolean getFlor(){
        return flor;
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

    public void jugadorPuedeCantarTanto(Jugador jugador){
        if(mesa.getRonda().seEstaJugandoLaPrimera() && mesa.getRonda().getTruco().getClass().equals(TrucoCantado.class))
            return;
        if(jugadorEsPie(jugador) && mesa.getRonda().seEstaJugandoLaPrimera())
            return;
        if(mesa.getNroJugadores()==2 && mesa.getRonda().seEstaJugandoLaPrimera())
            return;
        throw new JugadorNoHabilitadoParaCantarTanto();
    }

    public void jugadorPuedeAccionar(Jugador jugador){
        if(mesa.getRonda().termino())
            throw new RondaTerminadaException();
        if(!jugador.equals(mesa.getJugadorActivo()))
            throw new NoEsTurnoDelJugadorException();
    }

    public void jugadorPuedeCantarFlor(){
        if(!mesa.getRonda().seEstaJugandoLaPrimera())
            throw new FlorSeCantaEnLaPrimeraException();
        if(mesa.getRonda().getFlor()!=null)
            throw new FlorYaCantadaException();
    }

    public void jugadorPuedeCantarContraflor(){
        if(!mesa.getRonda().seEstaJugandoLaPrimera())
            throw new FlorSeCantaEnLaPrimeraException();
        if(mesa.getRonda().getFlor()==null)
            throw new FlorNoCantadaException();
    }

    public void florHabilitada(){
        flor=true;
    }

    public void florDeshabilitada(){flor= false;}

    public void seJuegaConFlor(){
        if(!flor)
            throw new FlorNoHabilitadaException();
    }
}
