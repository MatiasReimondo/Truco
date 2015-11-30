package truco.vista.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import truco.modelo.Carta;
import javafx.scene.control.Label;
import truco.modelo.Jugador;
import truco.modelo.Truco;
import truco.vista.controladores.envido.BotonEnvidoEventHandler;
import truco.vista.controladores.envido.BotonFaltaEnvidoEventHandler;
import truco.vista.controladores.envido.BotonRealEnvidoEventHandler;

import java.util.List;

/*********************************************************************************
 *****************  GraficadorDeNuevoJuegoEventHandler  *********************************
 *********************************************************************************/
public class GraficadorDeNuevoJuegoEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorVertical;

    private VBox contenedorDeEstados;
    private VBox contenedorDeEquipo1;
    private VBox contenedorDeEquipo2;

    private HBox contenedorDeEquipos;
    private VBox contenedorDeCartas;

    /********** Metodos de la clase **************/
    public GraficadorDeNuevoJuegoEventHandler(VBox contenedorVertical, Truco juego){

        //Contenedor principal donde estan todos los equipos y cartas.
        this.contenedorVertical = contenedorVertical;

        this.contenedorDeEstados = new VBox();
        this.contenedorDeCartas = new VBox();

        this.contenedorDeEquipos = new HBox();
        this.contenedorDeEquipo1 = new VBox();
        this.contenedorDeEquipo2 = new VBox();
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {

        //Se limpia el contenedor
        this.contenedorVertical.getChildren().clear();

        this.graficarContenedorDeEstados();
        this.graficarContenedorDeEquipos();
        this.graficarContenedorDeCartas();

        HBox contenedorDeCartasYEquipos = new HBox(this.contenedorDeEstados, this.contenedorDeEquipos, this.contenedorDeCartas);
        contenedorDeCartasYEquipos.setSpacing(90);
        contenedorDeCartasYEquipos.setPadding( new Insets(30));

        this.contenedorVertical.getChildren().addAll( contenedorDeCartasYEquipos);

    }

    /************** Metodos privados **************/

    //Grafica botoneras de cada una de las cartas que tiene un determinado  jugador
    private void graficarContenedorDeCartas() {

        String nombreDeCarta;
        List<Carta> cartas = this.juego.getMesa().getJugadorActivo().getMano();
        this.contenedorDeCartas.getChildren().add( new Label("Cartas del jugador") );

        for (Carta unaCarta: cartas){
            nombreDeCarta = Integer.toString(unaCarta.getNumero()) +" " + unaCarta.getPalo().toString();
            this.contenedorDeCartas.getChildren().add(new Button(nombreDeCarta));
        }

        this.contenedorDeCartas.setSpacing(10);
    }

    private void graficarContenedorDeEquipos(){

        Label etiquetaEquipo1 = new Label("Equipo1");
        this.contenedorDeEquipo1.getChildren().add(etiquetaEquipo1);
        for (Jugador unJugador: this.juego.getJugadores()){
           this.contenedorDeEquipo1.getChildren().add( new Label(unJugador.getNombre()) );
        }
        this.contenedorDeEquipo1.setSpacing(50);


        Label etiquetaEquipo2 = new Label("Equipo2");
        this.contenedorDeEquipo2.getChildren().add(etiquetaEquipo2);
        for (Jugador unJugador: this.juego.getJugadores()){
            this.contenedorDeEquipo2.getChildren().add( new Label(unJugador.getNombre()) );
        }
        this.contenedorDeEquipo2.setSpacing(50);

        //Se apilan los contenedores de cada equipo horizontalmente
        this.contenedorDeEquipos.getChildren().addAll(this.contenedorDeEquipo1, this.contenedorDeEquipo2);
        this.contenedorDeEquipos.setSpacing(60);
    }

    private void graficarContenedorDeEstados(){

        //Barra de estado de la ronda del juego,se puede separar las etiquetas
        this.contenedorDeEstados.getChildren().addAll(new Label("TURNO:"), new Label("MANO:"), new Label("-----------"));

        //Botones de estados de las primeras opciones activas del juego

        Button botonEnvido = new Button("ENVIDO");
        BotonEnvidoEventHandler botonEnvidoEventHandler = new BotonEnvidoEventHandler(this.juego,this.contenedorDeEstados, this.contenedorDeCartas);
        botonEnvido.setOnAction(botonEnvidoEventHandler);

        Button botonRealEnvido = new Button("REAL ENVIDO");
        BotonRealEnvidoEventHandler botonRealEnvidoEventHandler = new BotonRealEnvidoEventHandler(this.juego,this.contenedorDeEstados, this.contenedorDeCartas);
        botonRealEnvido.setOnAction(botonRealEnvidoEventHandler);


        Button botonFaltaEnvido = new Button("FALTA ENVIDO");
        BotonFaltaEnvidoEventHandler botonFaltaEnvidoEventHandler = new BotonFaltaEnvidoEventHandler(this.juego,this.contenedorDeEstados, this.contenedorDeCartas);
        botonFaltaEnvido.setOnAction(botonFaltaEnvidoEventHandler);


        this.contenedorDeEstados.getChildren().add( botonEnvido );
        this.contenedorDeEstados.getChildren().add( botonRealEnvido );
        this.contenedorDeEstados.getChildren().add( botonFaltaEnvido );
        this.contenedorDeEstados.getChildren().add( new Button("TRUCO") );
        this.contenedorDeEstados.getChildren().add( new Button("FLOR") );

        this.contenedorDeEstados.getChildren().add( new Label("-----------") );

        //barra del estado final del juego,por ahora vacia
        this.contenedorDeEstados.getChildren().addAll(new Label("PUNTAJE:"), new Label("Equipo1 = "), new Label("Equipo2 = "));

        this.contenedorDeEstados.setSpacing(5);
    }
}