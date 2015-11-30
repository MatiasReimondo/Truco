package truco.vista.controladores.configuracion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import truco.modelo.Truco;


public class BotonFlorSiEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;


    public BotonFlorSiEventHandler(Truco juego){

        this.juego = juego;

    }

    @Override
    public void handle(ActionEvent event) {

        this.juego.seJuegaConFlor();
    }



}
