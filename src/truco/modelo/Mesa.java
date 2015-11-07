package truco.modelo;

import truco.modelo.excepciones.CartaNoEstaEnLaManoException;
import truco.modelo.excepciones.NoEsTurnoDelJugadorException;

public class Mesa {

    private Ronda rondaActual;

    public Mesa(){
        rondaActual=new Ronda();
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

    }
}
