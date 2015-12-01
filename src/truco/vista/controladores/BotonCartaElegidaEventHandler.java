package truco.vista.controladores;
import truco.modelo.Carta;
import truco.modelo.Palo;
import truco.modelo.Truco;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    /*********** Metodos de la clase ************/
    public BotonCartaElegidaEventHandler(HBox contenedor, Truco juego, Button botonCarta){

        //obtengo el contenedor de equipo1 que esta apilado en el contenedor de equipos.
        this.contenedorDeEquipo1 = (VBox) ((HBox) contenedor.getChildren().get(1)).getChildren().get(0);
        this.nombreDeJugadoresDelEquipo = this.nombreDeJugadores();
        this.cartaElegida = botonCarta;
        this.contenedorDeCartas = (VBox) contenedor.getChildren().get(2);
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {

        this.contenedorDeEquipo1.getChildren().clear();
        graficarJugadorConCartaElegida();
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

    /* Cada vez que el jugador en turno selecciona una nueva carta se pasa a graficar en la mesa. */
    private void graficarJugadorConCartaElegida() {

        List<Carta> cartas = this.juego.getMesa().getJugadorActivo().getMano();
        for (String elemento: this.nombreDeJugadoresDelEquipo) {

            this.contenedorDeEquipo1.getChildren().add(new Label(elemento));

            if (this.juego.getMesa().getJugadorActivo().getNombre().equals(elemento)){
                String carta = this.cartaElegida.getText();
                this.contenedorDeEquipo1.getChildren().add(new Label(carta));
                this.juego.getMesa().getJugadorActivo().jugarCarta(Integer.parseInt(carta.substring(0,1)), Palo.valueOf(carta.substring(2)));
                this.contenedorDeCartas.getChildren().remove(this.cartaElegida);
            }
        }
    }
}
