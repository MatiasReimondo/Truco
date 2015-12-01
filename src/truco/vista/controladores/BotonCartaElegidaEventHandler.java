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
public class BotonCartaElegidaEventHandler implements EventHandler<ActionEvent> {

    /******* Atributos de la clase ***********/
    private VBox contenedorDeEquipo1;
    private List<String> nombreDeJugadoresDelEquipo;
    private VBox contenedorDeCartas;
    private Button cartaElegida;
    private Truco juego;
    private HBox contenedorPrincipal;
    //private VBox cartasJ1;
    //private VBox cartasJ2;

    /*********** Metodos de la clase ************/
    public BotonCartaElegidaEventHandler(HBox contenedor, Truco juego, Button botonCarta){

        //obtengo el contenedor de equipo1 que esta apilado en el contenedor de equipos.
        this.contenedorDeEquipo1 = (VBox) ((HBox) contenedor.getChildren().get(1)).getChildren().get(0);
        this.nombreDeJugadoresDelEquipo = this.nombreDeJugadores();
        this.cartaElegida = botonCarta;
        this.contenedorDeCartas = (VBox) contenedor.getChildren().get(2);
        //this.cartasJ1 = new VBox();
        //this.cartasJ2 = new VBox();
        this.juego = juego;
        this.contenedorPrincipal=contenedor;
    }

    @Override
    public void handle(ActionEvent event) {

        this.contenedorDeEquipo1.getChildren().clear();
        graficarJugadorConCartaElegida();
        graficarContenedorDeCartas();
        //this.graficarContenedorDeCartasJ2();
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
        //this.contenedorDeCartas = this.cartasJ1;
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
                //this.cartasJ1= this.contenedorDeCartas;
            }
        }
    }

    private void graficarCartaElegidaJugador2EnLaMesa(){

        //Label etiquetaNombre = (Label) this.contenedorDeCartas.getChildren().get(0);
        int posicion;
        for (posicion=1;posicion<(this.juego.getMesa().getJugadorActivo().getMano().size()+1); posicion++) {
            //
            Button unaCarta = (Button) this.contenedorDeCartas.getChildren().get(posicion);
            /*Button unaCarta = (Button) this.cartasJ2.getChildren().get(posicion);
            BotonCartaElegidaEventHandler botonCartaElegidaJugador2EventHandler = new BotonCartaElegidaEventHandler(this.contenedorPrincipal,this.juego,unaCarta);
            unaCarta.setOnAction(botonCartaElegidaJugador2EventHandler);
            TextoCartaElegidaEventHandler textoCartaElegidaEventHandler = new TextoCartaElegidaEventHandler(unaCarta, this.cartasJ2);*/

            BotonCartaElegidaJugador2EventHandler botonCartaElegidaJugador2EventHandler = new BotonCartaElegidaJugador2EventHandler(this.contenedorPrincipal,this.juego,unaCarta);
            unaCarta.setOnAction(botonCartaElegidaJugador2EventHandler);
            TextoCartaElegidaEventHandler textoCartaElegidaEventHandler = new TextoCartaElegidaEventHandler(unaCarta, this.contenedorDeCartas);
        }
    }
   /* private void graficarContenedorDeCartasJ2() {
        String nombreDeCarta;
        List<Carta> cartas = this.juego.getMesa().getJugadorActivo().getMano();
        this.cartasJ2.getChildren().add(new Label(this.juego.getMesa().getJugadorActivo().getNombre()));

        for (Carta unaCarta: cartas){
            nombreDeCarta = Integer.toString(unaCarta.getNumero())+" "+ unaCarta.getPalo().toString();
            this.cartasJ2.getChildren().add(new Button(nombreDeCarta));
        }

        this.cartasJ2.setSpacing(10);
    }*/

}