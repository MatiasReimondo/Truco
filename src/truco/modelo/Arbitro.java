package truco.modelo;


import truco.modelo.excepciones.*;

import java.util.List;

public class Arbitro {

    private Mesa mesa;
    private boolean flor;

    public Arbitro(){}

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

    public void jugadorPuedeCantarTanto(Jugador jugador){
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

    public void jugadorPuedeCantarFlor(Jugador jugador){
        if(!mesa.getRonda().seEstaJugandoLaPrimera())
            throw new FlorSoloSeCantaEnLaPrimeraException();
        if(mesa.getRonda().getFlorEnJuego()!=null)
            throw new SoloSePuedeCantarFlorUnaVezException();
        if(!mesa.getJugadorActivo().equals(jugador))
            throw new NoEsTurnoDelJugadorException();
    }

    public void jugadorPuedeCantarContraflor(){
        if(!mesa.getRonda().seEstaJugandoLaPrimera())
            throw new FlorSoloSeCantaEnLaPrimeraException();
        if(mesa.getRonda().getFlorEnJuego()==null)
            throw new ContraFlorSoloSePuedeCantarDespuesDeFlorException();
    }

    public void florHabilitada(){
        flor=true;
    }

    public void seJuegaConFlor(){
        if(!flor)
            throw new NoSeJuegaConFlorException();
    }
}
