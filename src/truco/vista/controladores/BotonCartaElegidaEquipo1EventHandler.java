package truco.vista.controladores;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import truco.modelo.Carta;
import truco.modelo.Palo;
import truco.modelo.Truco;

import java.util.ArrayList;
import java.util.List;

/*********************************************************************************
 *****************  BotonCartaElegidaEventHandler  *******************************
 *********************************************************************************/
public class BotonCartaElegidaEquipo1EventHandler implements EventHandler<ActionEvent> {

    /******* Atributos de la clase ***********/
    private VBox contenedorDeEquipo1;
    private VBox contenedorDePuntos;
    private List<String> nombreDeJugadoresDelEquipo;
    private VBox contenedorDeCartas;
    private Button cartaElegida;
    private Truco juego;
    private HBox contenedorPrincipal;

    /*********** Metodos de la clase ************/
    public BotonCartaElegidaEquipo1EventHandler(HBox contenedor, Truco juego, Button botonCarta){

        //obtengo el contenedor de equipo1 que esta apilado en el contenedor de equipos.
        this.contenedorDeEquipo1 = (VBox) ((HBox) contenedor.getChildren().get(1)).getChildren().get(0);
        this.nombreDeJugadoresDelEquipo = this.nombreDeJugadores();
        this.cartaElegida = botonCarta;
        this.contenedorDeCartas = (VBox) contenedor.getChildren().get(2);
        this.contenedorDePuntos= (VBox) contenedor.getChildren().get(3);
        this.juego = juego;
        this.contenedorPrincipal=contenedor;
    }

    @Override
    public void handle(ActionEvent event) {

        this.contenedorDeEquipo1.getChildren().clear();
        graficarJugadorConCartaElegida();

        if(this.juego.getMesa().getRonda().getManoEnJuego().size()== this.juego.getJugadores().size()){
            this.juego.getMesa().resolverMano();
        }
        graficarContenedorDeCartas();
        graficarContenedorDePuntos();
        graficarCartaElegidaJugador2EnLaMesa();
    }

    /* Devuelve una lista de nombres de todos los jugadores que pertenece al equipo.*/
    private List<String> nombreDeJugadores(){

        List<String> jugadoresDelEquipo = new ArrayList<>();
        int posicion;
        for ( posicion = 0; posicion < this.contenedorDeEquipo1.getChildren().size(); posicion++){
            Label nombreDeJugador = (Label) this.contenedorDeEquipo1.getChildren().get(posicion);
            jugadoresDelEquipo.add(nombreDeJugador.getText());
        }

        return jugadoresDelEquipo;
    }

    private void graficarContenedorDeCartas() {

        //Se muestra en la etiqueta que es el turno del jugador activo, esto es en el contenedor de estados
        VBox contenedorDeEstados = (VBox) this.contenedorPrincipal.getChildren().get(0);
        contenedorDeEstados.getChildren().remove(0);
        contenedorDeEstados.getChildren().add(0, new Label("TURNO:"+this.juego.getMesa().getJugadorActivo().getNombre()));

        this.contenedorDeCartas.getChildren().clear();

        String nombreDeCarta;
        List<Carta> cartas = this.juego.getMesa().getJugadorActivo().getMano();
        this.contenedorDeCartas.getChildren().add(new Label(this.juego.getMesa().getJugadorActivo().getNombre()));

        for (Carta unaCarta: cartas){
            nombreDeCarta = Integer.toString(unaCarta.getNumero())+" "+ unaCarta.getPalo().toString();
            this.contenedorDeCartas.getChildren().add(new Button(nombreDeCarta));
        }

        this.contenedorDeCartas.setSpacing(10);
    }

    private void graficarContenedorDePuntos() {
        this.contenedorDePuntos.getChildren().clear();

        String puntajeEquipo1= Integer.toString(this.juego.getEquipo("Equipo-1").getPuntaje());
        String puntajeEquipo2= Integer.toString(this.juego.getEquipo("Equipo-2").getPuntaje());

        this.contenedorDePuntos.getChildren().addAll(new Label("PUNTAJE:"), new Label("Equipo1 = "+puntajeEquipo1), new Label("Equipo2 = "+puntajeEquipo2));

        this.contenedorDePuntos.setSpacing(5);
    }

    /* Cada vez que el jugador en turno selecciona una nueva carta se pasa a graficar en la mesa. */
    private void graficarJugadorConCartaElegida() {

        List<Carta> cartas = this.juego.getMesa().getJugadorActivo().getMano();
        for (String elemento: this.nombreDeJugadoresDelEquipo) {

            this.contenedorDeEquipo1.getChildren().add(new Label(elemento));

            if (this.juego.getMesa().getJugadorActivo().getNombre().equals(elemento)){
                String carta = this.cartaElegida.getText();
                this.contenedorDeEquipo1.getChildren().add(new Label(carta));
                if ( carta.substring(2,3).equals(" ") ){
                    this.juego.getMesa().getJugadorActivo().jugarCarta(Integer.parseInt(carta.substring(0,2)), Palo.valueOf(carta.substring(3)));
                    this.contenedorDeCartas.getChildren().remove(this.cartaElegida);
                }
                else {
                    this.juego.getMesa().getJugadorActivo().jugarCarta(Integer.parseInt(carta.substring(0,1)), Palo.valueOf(carta.substring(2)));
                    this.contenedorDeCartas.getChildren().remove(this.cartaElegida);
                }
            }
        }
    }

    private void graficarCartaElegidaJugador2EnLaMesa(){

        int posicion;
        for (posicion=1;posicion<(this.juego.getMesa().getJugadorActivo().getMano().size()+1); posicion++) {

            Button unaCarta = (Button) this.contenedorDeCartas.getChildren().get(posicion);
            BotonCartaElegidaEquipo2EventHandler botonCartaElegidaEquipo2EventHandler = new BotonCartaElegidaEquipo2EventHandler(this.contenedorPrincipal,this.juego,unaCarta);
            unaCarta.setOnAction(botonCartaElegidaEquipo2EventHandler);
            TextoCartaElegidaEventHandler textoCartaElegidaEventHandler = new TextoCartaElegidaEventHandler(unaCarta, this.contenedorDeCartas);
        }
    }
}
