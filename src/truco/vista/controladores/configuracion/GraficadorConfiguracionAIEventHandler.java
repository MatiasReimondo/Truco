package truco.vista.controladores.configuracion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import truco.modelo.Truco;
import truco.vista.controladores.GraficadorDeNuevoJuegoEventHandler;

public class GraficadorConfiguracionAIEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorPrincipal;
    private HBox contenedorSegundario=new HBox();


    public GraficadorConfiguracionAIEventHandler(VBox contenedorPrincipal, Truco juego) {
        this.contenedorPrincipal = contenedorPrincipal;
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {

        //Se limpia el contenedor
        this.contenedorPrincipal.getChildren().clear();

        this.graficarConfiguracionAI();
        this.graficarComenzarJuego();

        contenedorSegundario.setSpacing(90);
        contenedorSegundario.setPadding( new Insets(30));

        this.contenedorPrincipal.getChildren().add(contenedorSegundario);

    }

    public void graficarConfiguracionAI(){
        VBox colecionCheckbox= new VBox();
        for(int i=0; i<this.juego.getJugadores().size();i++){
            CheckBox jugadorAI= new CheckBox(this.juego.getJugadores().get(i).getNombre());
            colecionCheckbox.getChildren().add(jugadorAI);
        }
        Button setearAI= new Button("JUGADORES CONTROLADOS POR PC");
        SetearAIEventHandler setearAIEventHandler = new SetearAIEventHandler(colecionCheckbox, this.juego);
        setearAI.setOnAction(setearAIEventHandler);

        contenedorPrincipal.getChildren().add(colecionCheckbox);
        contenedorPrincipal.getChildren().add(setearAI);
    }

    private void graficarComenzarJuego() {

        Button botonComenzar = new Button("Empezar Partida");
        GraficadorDeNuevoJuegoEventHandler graficadorDeNuevoJuegoEventHandler = new GraficadorDeNuevoJuegoEventHandler(this.contenedorPrincipal, this.juego);
        botonComenzar.setOnAction(graficadorDeNuevoJuegoEventHandler);

        contenedorSegundario.getChildren().add(new HBox(botonComenzar));
    }


}




