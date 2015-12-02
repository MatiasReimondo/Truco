package truco.vista.controladores.configuracion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import truco.modelo.Carta;
import truco.modelo.Palo;
import truco.modelo.Truco;


public class BotonDosJugadoresEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;


    public BotonDosJugadoresEventHandler(Truco juego){

        this.juego = juego;

    }

    @Override
    public void handle(ActionEvent event) {

        crearModeloDosJugadores(this.juego);
    }


    private Truco crearModeloDosJugadores(Truco nuevoJuego){


        nuevoJuego.nuevoEquipo("Equipo-1");
        nuevoJuego.nuevoEquipo("Equipo-2");

        nuevoJuego.nuevoJugador("J1","Equipo-1");
        nuevoJuego.nuevoJugador("J2", "Equipo-2");

        nuevoJuego.empezarJuego();
        nuevoJuego.getMesa().nuevaRonda();

        nuevoJuego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        nuevoJuego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        nuevoJuego.getJugador("J1").levantarCarta(new Carta(10, Palo.ESPADA));

        nuevoJuego.getJugador("J2").levantarCarta(new Carta(4, Palo.BASTO));
        nuevoJuego.getJugador("J2").levantarCarta(new Carta(7, Palo.BASTO));
        nuevoJuego.getJugador("J2").levantarCarta(new Carta(10, Palo.COPA));


        return nuevoJuego;
    }



}
