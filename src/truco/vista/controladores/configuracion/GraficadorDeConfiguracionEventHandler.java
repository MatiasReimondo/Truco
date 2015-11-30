package truco.vista.controladores.configuracion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import truco.modelo.Truco;
import truco.vista.controladores.GraficadorDeNuevoJuegoEventHandler;
import truco.vista.controladores.TextoNuevoJuegoEventHandler;


/**
 * Created by shaun on 29/11/2015.
 */
public class GraficadorDeConfiguracionEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorVertical;

    private VBox contenedorConfiguracionFlor;
    private VBox contenedorConfiguracionJugadores;
    private HBox contenedorComenzarJuego;




    public GraficadorDeConfiguracionEventHandler(VBox contenedorVertical, Truco juego) {

        this.contenedorVertical = contenedorVertical;
        this.juego = juego;

        this.contenedorConfiguracionFlor= new VBox();
        this.contenedorConfiguracionJugadores= new VBox();
        this.contenedorComenzarJuego= new HBox();

    }


    @Override
    public void handle(ActionEvent event) {

        //Se limpia el contenedor
        this.contenedorVertical.getChildren().clear();

        this.graficarConfiguracionFlor();
        this.graficarContenedorJugadores();
        this.graficarComenzarJuego();

        HBox contenedorDeConfiguraciones = new HBox(this.contenedorConfiguracionFlor, this.contenedorConfiguracionJugadores, this.contenedorComenzarJuego);
        contenedorDeConfiguraciones.setSpacing(90);
        contenedorDeConfiguraciones.setPadding( new Insets(30));

        this.contenedorVertical.getChildren().addAll( contenedorDeConfiguraciones);

    }

    private void graficarComenzarJuego() {

        Button comenzarElJuego = new Button("Comenzar juego");
        GraficadorDeNuevoJuegoEventHandler graficadorDeNuevoJuegoEventHandler = new GraficadorDeNuevoJuegoEventHandler(this.contenedorVertical, this.juego);
        comenzarElJuego.setOnAction(graficadorDeNuevoJuegoEventHandler);
        TextoNuevoJuegoEventHandler textoNuevoJuegoEventHandler = new TextoNuevoJuegoEventHandler(comenzarElJuego);

        this.contenedorComenzarJuego.getChildren().add(comenzarElJuego);


    }

    private void graficarContenedorJugadores() {

        Label cantidadDeJugadores = new Label("CantidadDeJugadores");
        this.contenedorConfiguracionJugadores.getChildren().add(cantidadDeJugadores);

        Button botonDosJugadores = new Button("Dos Jugadores");
        BotonDosJugadoresEventHandler botonDosJugadoresEventHandler = new BotonDosJugadoresEventHandler(this.juego);
        botonDosJugadores.setOnAction(botonDosJugadoresEventHandler);

        Button botonCuatroJugadores = new Button("Cuatro Jugadores");
        BotonCuatroJugadoresEventHandler botonCuatroJugadoresEventHandler = new BotonCuatroJugadoresEventHandler(this.juego);
        botonCuatroJugadores.setOnAction(botonCuatroJugadoresEventHandler);

        Button botonSeisJugadores = new Button("Seis Jugadores");
        BotonSeisJugadoresEventHandler botonSeisJugadoresEventHandler = new BotonSeisJugadoresEventHandler(this.juego);
        botonSeisJugadores.setOnAction(botonSeisJugadoresEventHandler);


        VBox opcionesCantidad= new VBox();


        opcionesCantidad.getChildren().add(botonDosJugadores);
        opcionesCantidad.getChildren().add(botonCuatroJugadores);
        opcionesCantidad.getChildren().add(botonSeisJugadores);

        this.contenedorConfiguracionJugadores.getChildren().addAll(opcionesCantidad);


    }

    private void graficarConfiguracionFlor() {

        Label jugarConFlor = new Label("Jugar con Flor?");
        this.contenedorConfiguracionFlor.getChildren().add(jugarConFlor);

        Button botonFlorSi = new Button("SI");
        BotonFlorSiEventHandler botonFlorSiEventHandler = new BotonFlorSiEventHandler(this.juego);
        botonFlorSi.setOnAction(botonFlorSiEventHandler);

        Button botonFlorNo = new Button("NO");
        BotonFlorNoEventHandler botonFlorNoEventHandler = new BotonFlorNoEventHandler(this.juego);
        botonFlorNo.setOnAction(botonFlorNoEventHandler);

        this.contenedorConfiguracionFlor.getChildren().add(botonFlorSi);
        this.contenedorConfiguracionFlor.getChildren().add(botonFlorNo);



    }
}


