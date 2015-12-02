package truco.vista.controladores.truco;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Truco;
import truco.vista.controladores.BotonCartaElegidaEquipo1EventHandler;
import truco.vista.controladores.TextoCartaElegidaEventHandler;

import java.util.List;


public class BotonQuieroTrucoEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorDeEstados;
    private VBox contenedorDeCartas;
    private VBox contenedorDePuntos;
    private VBox contenedorVertical;
    private HBox contenedorPrincipal;
    private VBox contenedorDeEquipo1;
    private VBox contenedorDeEquipo2;
    private HBox contenedorDeEquipos;


    public BotonQuieroTrucoEventHandler(Truco juego, VBox contenedorDeEstados, VBox contenedorDeCartas,VBox contenedorDePuntos, VBox contenedorVertical) {

        this.juego = juego;
        this.contenedorDeEstados = contenedorDeEstados;
        this.contenedorDeCartas = contenedorDeCartas;
        this.contenedorDePuntos=contenedorDePuntos;
        this.contenedorVertical=contenedorVertical;
        this.contenedorDeEquipo1=new VBox();
        this.contenedorDeEquipo2=new VBox();
        this.contenedorDeEquipos =(HBox)contenedorVertical.getChildren().get(0);


    }


    @Override
    public void handle(ActionEvent event) {

        this.juego.getMesa().getJugadorActivo().quieroTruco();


        this.graficarContenedorDeEstados();
        this.graficarContenedorDeEquipos();
        this.graficarContenedorDeCartas();
        this.graficarContenedorDePuntos();

        this.contenedorPrincipal= new HBox(this.contenedorDeEstados, this.contenedorDeEquipos, this.contenedorDeCartas,this.contenedorDePuntos);
        this.contenedorPrincipal.setSpacing(90);
        this.contenedorPrincipal.setPadding(new Insets(30));
        this.contenedorVertical.getChildren().addAll(this.contenedorPrincipal);

        this.graficarCartaElegidaEnLaMesa();
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


        Button botonReTruco = new Button("RE TRUCO");
        BotonReTrucoEventHandler botonReTrucoEventHandler = new BotonReTrucoEventHandler(this.juego, this.contenedorDeEstados, this.contenedorDeCartas, this.contenedorDePuntos,this.contenedorVertical);
        botonReTruco.setOnAction(botonReTrucoEventHandler);


        this.contenedorDeEstados.getChildren().add(botonReTruco);

        this.contenedorDeEstados.getChildren().add(new Label("-----------"));

        this.contenedorDeEstados.setSpacing(5);
    }

    private void graficarContenedorDePuntos() {
        this.contenedorDePuntos.getChildren().clear();

        String puntajeEquipo1= Integer.toString(this.juego.getEquipo("Equipo-1").getPuntaje());
        String puntajeEquipo2= Integer.toString(this.juego.getEquipo("Equipo-2").getPuntaje());

        this.contenedorDePuntos.getChildren().addAll(new Label("PUNTAJE:"), new Label("Equipo1 = "+puntajeEquipo1), new Label("Equipo2 = "+puntajeEquipo2));

        this.contenedorDePuntos.setSpacing(5);
    }


    private void graficarContenedorDeEquipos(){

        contenedorDeEquipos.getChildren().clear();

        Label etiquetaEquipo1 = new Label("Equipo1");
        this.contenedorDeEquipo1.getChildren().add(etiquetaEquipo1);
        for (Jugador unJugador: this.juego.getJugadores()){
            if (this.juego.getEquipo("Equipo-1").equals(unJugador.getEquipo())) {
                this.contenedorDeEquipo1.getChildren().add(new Label(unJugador.getNombre()));
            }
        }
        this.contenedorDeEquipo1.setSpacing(20);

        Label etiquetaEquipo2 = new Label("Equipo2");
        this.contenedorDeEquipo2.getChildren().add(etiquetaEquipo2);
        for (Jugador unJugador: this.juego.getJugadores()){
            if (this.juego.getEquipo("Equipo-2").equals(unJugador.getEquipo())) {
                this.contenedorDeEquipo2.getChildren().add(new Label(unJugador.getNombre()));
            }
        }
        this.contenedorDeEquipo2.setSpacing(20);

        //Se apilan los contenedores de cada equipo horizontalmente
        this.contenedorDeEquipos.getChildren().addAll(this.contenedorDeEquipo1, this.contenedorDeEquipo2);
        this.contenedorDeEquipos.setSpacing(60);
    }

    private void graficarCartaElegidaEnLaMesa(){

        int posicion;
        for( posicion =1; posicion < this.juego.getMesa().getJugadorActivo().getMano().size()+1; posicion++) {
            Button unaCarta = (Button) this.contenedorDeCartas.getChildren().get(posicion);
            BotonCartaElegidaEquipo1EventHandler botonCartaElegidaEquipo1EventHandler = new BotonCartaElegidaEquipo1EventHandler(this.contenedorPrincipal,this.juego,unaCarta,this.contenedorVertical);
            unaCarta.setOnAction(botonCartaElegidaEquipo1EventHandler);
            TextoCartaElegidaEventHandler textoCartaElegidaEventHandler = new TextoCartaElegidaEventHandler(unaCarta, this.contenedorDeCartas);
        }
    }
}

