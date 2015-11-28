package truco.vista.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import truco.modelo.Carta;
import javafx.scene.control.Label;
import truco.modelo.Jugador;
import truco.modelo.Truco;

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
    private HBox contenedorDeCartas;

    /********** Metodos de la clase **************/
    public GraficadorDeNuevoJuegoEventHandler(VBox contenedorVertical, Truco juego){

        this.contenedorVertical = contenedorVertical;
        this.contenedorDeCartas = new HBox();
        this.contenedorDeEquipos = new HBox();
        this.contenedorDeEstados = new VBox();
        this.contenedorDeEquipo1 = new VBox();
        this.contenedorDeEquipo2 = new VBox();
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {
        //Se limpia el contenedor
        this.contenedorVertical.getChildren().clear();

        this.graficarContenedorDeCartas();
        this.graficarContenedorDeEquipos();

        this.contenedorVertical.getChildren().addAll(this.contenedorDeEquipos, this.contenedorDeCartas);
        this.contenedorVertical.setSpacing(50);
    }


    /************** Metodos privados **************/

    //Grafica botoneras de cada una de las cartas que tiene un determinado  jugador
    private void graficarContenedorDeCartas() {

        String nombreDeCarta;
        List<Carta> cartas = this.juego.getCartasDelJugador("J1");

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
        this.contenedorDeEquipo1.setSpacing(10);


        Label etiquetaEquipo2 = new Label("Equipo2");
        this.contenedorDeEquipo2.getChildren().add(etiquetaEquipo2);
        for (Jugador unJugador: this.juego.getJugadores()){
            this.contenedorDeEquipo2.getChildren().add( new Label(unJugador.getNombre()) );
        }
        this.contenedorDeEquipo2.setSpacing(10);

        //Se apilan los contenedores de cada equipo horizontalmente
        this.contenedorDeEquipos.getChildren().addAll(this.contenedorDeEquipo1, this.contenedorDeEquipo2);
        this.contenedorDeEquipos.setSpacing(30);
    }
}
