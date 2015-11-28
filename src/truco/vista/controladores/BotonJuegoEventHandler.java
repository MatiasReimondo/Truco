package truco.vista.controladores;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import truco.modelo.Truco;

/*********************************************************************************
 ************************ BotonJuegoEventHandler ********************************
 *********************************************************************************/
public class BotonJuegoEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorVertical;
    private Button botonNuevoJuego;
    private Button botonSalirJuego;

    /*********** Metodos de la clase **********/
    public BotonJuegoEventHandler( VBox contenedorVertical, Truco juego){

        this.juego = juego;
        this.contenedorVertical = contenedorVertical;
        this.botonNuevoJuego = new Button("Nuevo");
        this.botonSalirJuego = new Button("Salir");
    }

    @Override
    public void handle(ActionEvent event) {

        //Se limpia el contenedor
        this.contenedorVertical.getChildren().clear();

        this.contenedorVertical.getChildren().add(this.botonNuevoJuego);
        this.contenedorVertical.getChildren().add(this.botonSalirJuego);

        GraficadorDeNuevoJuegoEventHandler graficadorDeNuevoJuegoEventHandler = new GraficadorDeNuevoJuegoEventHandler(this.contenedorVertical, this.juego);
        this.botonNuevoJuego.setOnAction(graficadorDeNuevoJuegoEventHandler);
        TextoNuevoJuegoEventHandler textoNuevoJuegoEventHandler = new TextoNuevoJuegoEventHandler(this.botonNuevoJuego);
    }
}
