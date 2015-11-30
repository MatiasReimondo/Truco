package truco.vista.controladores;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/*********************************************************************************
 *****************  BotonCartaElegidaEventHandler  *******************************
 *********************************************************************************/
public class BotonCartaElegidaEventHandler implements EventHandler<ActionEvent> {

    /******* Atributos de la clase ***********/
    private VBox contenedorDeEquipo1;
    private List<String> nombreDeJugadoresDelEquipo;
    private String jugadorActivo;
    private VBox contenedorDeCartas;
    private Button cartaElegida;

    /*********** Metodos de la clase ************/
    public BotonCartaElegidaEventHandler(VBox contenedor, VBox contenedorDeEquipo1,String jugadorActivo, Button boton){

        this.contenedorDeEquipo1 = contenedorDeEquipo1;
        this.nombreDeJugadoresDelEquipo = this.nombreDeJugadores();
        this.jugadorActivo = jugadorActivo;
        this.cartaElegida = boton;
        this.contenedorDeCartas = contenedor;
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

        for (String elemento: this.nombreDeJugadoresDelEquipo) {

            this.contenedorDeEquipo1.getChildren().add(new Label(elemento));

            if (this.jugadorActivo.equals(elemento)){
                this.contenedorDeEquipo1.getChildren().add(new Label(this.cartaElegida.getText()));
                this.contenedorDeCartas.getChildren().remove(this.cartaElegida);
            }
        }
    }
}
