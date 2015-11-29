package truco.vista.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import truco.modelo.Carta;
import truco.modelo.Truco;
import truco.modelo.envido.Envido;

import java.util.List;


public class BotonEnvidoEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorDeEstados;
    private VBox contenedorDeCartas;


    public BotonEnvidoEventHandler(Truco juego, VBox contenedorDeEstados, VBox contenedorDeCartas){

        this.juego = juego;
        this.contenedorDeEstados= contenedorDeEstados;
        this.contenedorDeCartas= contenedorDeCartas;

    }


    @Override
    public void handle(ActionEvent event) {

        this.juego.getJugador("J1").cantarEnvido(new Envido());

        graficarContenedorDeCartas();

        this.contenedorDeEstados.getChildren().clear();


        this.contenedorDeEstados.getChildren().add(new Button("QUIERO"));
        this.contenedorDeEstados.getChildren().add( new Button("ENVIDO") );
        this.contenedorDeEstados.getChildren().add( new Button("REAL ENVIDO") );
        this.contenedorDeEstados.getChildren().add( new Button("FALTA ENVIDO") );


        this.contenedorDeEstados.getChildren().add( new Label("-----------") );

        this.contenedorDeEstados.getChildren().addAll(new Label("PUNTAJE:"), new Label("Equipo1 = "), new Label("Equipo2 = "));

        this.contenedorDeEstados.setSpacing(5);

    }

    private void graficarContenedorDeCartas() {

        this.contenedorDeCartas.getChildren().clear();

        String nombreDeCarta;
        List<Carta> cartas = this.juego.getCartasDelJugador("J2");
        this.contenedorDeCartas.getChildren().add( new Label("Cartas de J2") );

        for (Carta unaCarta: cartas){
            nombreDeCarta = Integer.toString(unaCarta.getNumero()) +" " + unaCarta.getPalo().toString();
            this.contenedorDeCartas.getChildren().add(new Button(nombreDeCarta));
        }

        this.contenedorDeCartas.setSpacing(10);
    }



}
