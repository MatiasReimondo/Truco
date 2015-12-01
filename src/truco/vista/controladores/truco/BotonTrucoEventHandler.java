package truco.vista.controladores.truco;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import truco.modelo.Carta;
import truco.modelo.Truco;
import truco.vista.controladores.envido.BotonEnvidoEventHandler;
import truco.vista.controladores.envido.BotonFaltaEnvidoEventHandler;
import truco.vista.controladores.envido.BotonRealEnvidoEventHandler;


import java.util.List;


public class BotonTrucoEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorDeEstados;
    private VBox contenedorDeCartas;


    public BotonTrucoEventHandler(Truco juego, VBox contenedorDeEstados, VBox contenedorDeCartas) {

        this.juego = juego;
        this.contenedorDeEstados = contenedorDeEstados;
        this.contenedorDeCartas = contenedorDeCartas;

    }


    @Override
    public void handle(ActionEvent event) {

        this.juego.getMesa().getJugadorActivo().cantarTruco();

        graficarContenedorDeCartas();
        graficarContenedorDeEstados();
    }

    private void graficarContenedorDeCartas() {

        this.contenedorDeCartas.getChildren().clear();

        String nombreDeCarta;
        List<Carta> cartas = this.juego.getMesa().getJugadorActivo().getMano();
        this.contenedorDeCartas.getChildren().add(new Label("Cartas de jugador"));

        for (Carta unaCarta : cartas) {
            nombreDeCarta = Integer.toString(unaCarta.getNumero()) + " " + unaCarta.getPalo().toString();
            this.contenedorDeCartas.getChildren().add(new Button(nombreDeCarta));
        }

        this.contenedorDeCartas.setSpacing(10);
    }

    private void graficarContenedorDeEstados() {
        this.contenedorDeEstados.getChildren().clear();

        Button botonQuiero = new Button("QUIERO");
        BotonQuieroTrucoEventHandler botonQuieroTrucoEventHandler = new BotonQuieroTrucoEventHandler(this.juego, this.contenedorDeEstados, this.contenedorDeCartas);
        botonQuiero.setOnAction(botonQuieroTrucoEventHandler);

        Button botonNoQuiero = new Button("NO QUIERO");
        BotonNoQuieroTrucoEventHandler botonNoQuieroTrucoEventHandler = new BotonNoQuieroTrucoEventHandler(this.juego, this.contenedorDeEstados, this.contenedorDeCartas);
        botonNoQuiero.setOnAction(botonNoQuieroTrucoEventHandler);

        Button botonReTruco = new Button("RE TRUCO");
        BotonReTrucoEventHandler botonReTrucoEventHandler = new BotonReTrucoEventHandler(this.juego, this.contenedorDeEstados, this.contenedorDeCartas);
        botonReTruco.setOnAction(botonReTrucoEventHandler);


        Button botonEnvido = new Button("ENVIDO");
        BotonEnvidoEventHandler botonEnvidoEventHandler = new BotonEnvidoEventHandler(this.juego, this.contenedorDeEstados, this.contenedorDeCartas);
        botonEnvido.setOnAction(botonEnvidoEventHandler);


        Button botonRealEnvido = new Button("REAL ENVIDO");
        BotonRealEnvidoEventHandler botonRealEnvidoEventHandler = new BotonRealEnvidoEventHandler(this.juego, this.contenedorDeEstados, this.contenedorDeCartas);
        botonRealEnvido.setOnAction(botonRealEnvidoEventHandler);

        Button botonFaltaEnvido = new Button("FALTA ENVIDO");
        BotonFaltaEnvidoEventHandler botonFaltaEnvidoEventHandler = new BotonFaltaEnvidoEventHandler(this.juego, this.contenedorDeEstados, this.contenedorDeCartas);
        botonFaltaEnvido.setOnAction(botonFaltaEnvidoEventHandler);


        this.contenedorDeEstados.getChildren().add(botonQuiero);
        this.contenedorDeEstados.getChildren().add(botonNoQuiero);
        this.contenedorDeEstados.getChildren().add(botonReTruco);
        this.contenedorDeEstados.getChildren().add(botonEnvido);
        this.contenedorDeEstados.getChildren().add(botonRealEnvido);
        this.contenedorDeEstados.getChildren().add(botonFaltaEnvido);


        this.contenedorDeEstados.getChildren().add(new Label("-----------"));

        String puntajeEquipo1 = Integer.toString(this.juego.getEquipo("Equipo-1").getPuntaje());
        String puntajeEquipo2 = Integer.toString(this.juego.getEquipo("Equipo-2").getPuntaje());

        this.contenedorDeEstados.getChildren().addAll(new Label("PUNTAJE:"), new Label("Equipo1 = " + puntajeEquipo1), new Label("Equipo2 = " + puntajeEquipo2));

        this.contenedorDeEstados.setSpacing(5);
    }
}


