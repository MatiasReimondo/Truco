package truco.vista.controladores.truco;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import truco.modelo.Carta;
import truco.modelo.Truco;

import java.util.List;


public class BotonQuieroReTrucoEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorDeEstados;
    private VBox contenedorDeCartas;


    public BotonQuieroReTrucoEventHandler(Truco juego, VBox contenedorDeEstados, VBox contenedorDeCartas) {

        this.juego = juego;
        this.contenedorDeEstados = contenedorDeEstados;
        this.contenedorDeCartas = contenedorDeCartas;

    }


    @Override
    public void handle(ActionEvent event) {

        this.juego.getMesa().getJugadorActivo().quieroTruco();

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


        Button botonValeCuatro = new Button("VALE CUATRO");
        BotonValeCuatroEventHandler botonValeCuatroEventHandler = new BotonValeCuatroEventHandler(this.juego, this.contenedorDeEstados, this.contenedorDeCartas);
        botonValeCuatro.setOnAction(botonValeCuatroEventHandler);

        this.contenedorDeEstados.getChildren().add(botonValeCuatro);

        this.contenedorDeEstados.getChildren().add(new Label("-----------"));

        String puntajeEquipo1 = Integer.toString(this.juego.getEquipo("Equipo-1").getPuntaje());
        String puntajeEquipo2 = Integer.toString(this.juego.getEquipo("Equipo-2").getPuntaje());

        this.contenedorDeEstados.getChildren().addAll(new Label("PUNTAJE:"), new Label("Equipo1 = " + puntajeEquipo1), new Label("Equipo2 = " + puntajeEquipo2));

        this.contenedorDeEstados.setSpacing(5);
    }
}
