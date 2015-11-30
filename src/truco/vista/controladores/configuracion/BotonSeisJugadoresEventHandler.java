package truco.vista.controladores.configuracion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import truco.modelo.Carta;
import truco.modelo.Palo;
import truco.modelo.Truco;


public class BotonSeisJugadoresEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;


    public BotonSeisJugadoresEventHandler(Truco juego) {

        this.juego = juego;

    }

    @Override
    public void handle(ActionEvent event) {

        crearModeloSeisJugadores(this.juego);
    }


    private Truco crearModeloSeisJugadores(Truco nuevoJuego) {


        nuevoJuego.nuevoEquipo("Equipo-1");
        nuevoJuego.nuevoEquipo("Equipo-2");

        nuevoJuego.nuevoJugador("J1", "Equipo-1");
        nuevoJuego.nuevoJugador("J2", "Equipo-2");
        nuevoJuego.nuevoJugador("J3", "Equipo-1");
        nuevoJuego.nuevoJugador("J4", "Equipo-2");
        nuevoJuego.nuevoJugador("J5", "Equipo-1");
        nuevoJuego.nuevoJugador("J6", "Equipo-2");

        nuevoJuego.empezarJuego();
        nuevoJuego.getMesa().nuevaRonda();

        nuevoJuego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        nuevoJuego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        nuevoJuego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        nuevoJuego.getJugador("J2").levantarCarta(new Carta(4, Palo.BASTO));
        nuevoJuego.getJugador("J2").levantarCarta(new Carta(7, Palo.BASTO));
        nuevoJuego.getJugador("J2").levantarCarta(new Carta(10, Palo.BASTO));


        return nuevoJuego;
    }
}