package truco.vista.controladores.envido;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import truco.modelo.Carta;
import truco.modelo.Truco;
import truco.modelo.envido.FaltaEnvido;

import java.util.List;


public class BotonFaltaEnvidoEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorDeEstados;
    private VBox contenedorDeCartas;
    private VBox contenedorDePuntos;
    private VBox contenedorVertical;


    public BotonFaltaEnvidoEventHandler(Truco juego, VBox contenedorDeEstados, VBox contenedorDeCartas, VBox contenedorDePuntos, VBox contenedorVertical){

        this.juego = juego;
        this.contenedorDeEstados= contenedorDeEstados;
        this.contenedorDeCartas= contenedorDeCartas;
        this.contenedorDePuntos=contenedorDePuntos;
        this.contenedorVertical=contenedorVertical;


    }


    @Override
    public void handle(ActionEvent event) {

        this.juego.getMesa().getJugadorActivo().cantarEnvido(new FaltaEnvido());

        graficarContenedorDeCartas();
        graficarContenedorDePuntos();
        graficarContenedorDeEstados();
    }

    private void graficarContenedorDeCartas() {

        this.contenedorDeCartas.getChildren().clear();

        String nombreDeCarta;
        List<Carta> cartas = this.juego.getMesa().getJugadorActivo().getMano();
        this.contenedorDeCartas.getChildren().add( new Label("Cartas de jugador") );

        for (Carta unaCarta: cartas){
            nombreDeCarta = Integer.toString(unaCarta.getNumero()) +" " + unaCarta.getPalo().toString();
            this.contenedorDeCartas.getChildren().add(new Button(nombreDeCarta));
        }

        this.contenedorDeCartas.setSpacing(10);
    }

    private void graficarContenedorDeEstados(){
        this.contenedorDeEstados.getChildren().clear();

        Button botonQuiero = new Button("QUIERO");
        BotonQuieroTantoEventHandler botonQuieroTantoEventHandler = new BotonQuieroTantoEventHandler(this.juego,this.contenedorDeEstados, this.contenedorDeCartas,this.contenedorDePuntos,this.contenedorVertical);
        botonQuiero.setOnAction(botonQuieroTantoEventHandler);

        Button botonNoQuiero = new Button("NO QUIERO");
        BotonNoQuieroTantoEventHandler botonNoQuieroTantoEventHandler = new BotonNoQuieroTantoEventHandler(this.juego,this.contenedorDeEstados, this.contenedorDeCartas,this.contenedorDePuntos,this.contenedorVertical);
        botonNoQuiero.setOnAction(botonNoQuieroTantoEventHandler);


        this.contenedorDeEstados.getChildren().add(botonQuiero);
        this.contenedorDeEstados.getChildren().add(botonNoQuiero);


        this.contenedorDeEstados.getChildren().add( new Label("-----------") );


        this.contenedorDeEstados.setSpacing(5);
    }

    private void graficarContenedorDePuntos() {
        this.contenedorDePuntos.getChildren().clear();

        String puntajeEquipo1= Integer.toString(this.juego.getEquipo("Equipo-1").getPuntaje());
        String puntajeEquipo2= Integer.toString(this.juego.getEquipo("Equipo-2").getPuntaje());

        this.contenedorDePuntos.getChildren().addAll(new Label("PUNTAJE:"), new Label("Equipo1 = "+puntajeEquipo1), new Label("Equipo2 = "+puntajeEquipo2));

        this.contenedorDePuntos.setSpacing(5);
    }




}