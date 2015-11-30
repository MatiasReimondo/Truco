package truco.vista.controladores.configuracion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import truco.modelo.Truco;


public class BotonFlorNoEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;


    public BotonFlorNoEventHandler(Truco juego){

        this.juego = juego;

    }

    @Override
    public void handle(ActionEvent event) {

        this.juego.seJuegaSinFlor();
    }



}
