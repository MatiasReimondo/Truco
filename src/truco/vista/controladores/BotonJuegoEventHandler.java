package truco.vista.controladores;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import truco.modelo.Carta;
import truco.modelo.Palo;
import truco.modelo.Truco;
import truco.vista.controladores.configuracion.GraficadorDeConfiguracionEventHandler;

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

        this.juego=new Truco();
        //Se limpia el contenedor
        this.contenedorVertical.getChildren().clear();

        this.contenedorVertical.getChildren().add(this.botonNuevoJuego);
        this.contenedorVertical.getChildren().add(this.botonSalirJuego);

        GraficadorDeConfiguracionEventHandler graficadorDeConfiguracionEventHandler = new GraficadorDeConfiguracionEventHandler(this.contenedorVertical, this.juego);
        this.botonNuevoJuego.setOnAction(graficadorDeConfiguracionEventHandler);


    }


    private Truco crearModelo(){

        Truco nuevoJuego = new Truco();
        nuevoJuego.nuevoEquipo("Equipo-1");
        nuevoJuego.nuevoEquipo("Equipo-2");

        nuevoJuego.nuevoJugador("J1","Equipo-1");
        nuevoJuego.nuevoJugador("J2", "Equipo-2");

        nuevoJuego.empezarJuego();
        nuevoJuego.getMesa().nuevaRonda();

        nuevoJuego.getJugador("J1").levantarCarta(new Carta(5, Palo.ESPADA));
        nuevoJuego.getJugador("J1").levantarCarta(new Carta(7, Palo.ESPADA));
        nuevoJuego.getJugador("J1").levantarCarta(new Carta(10, Palo.COPA));

        nuevoJuego.getJugador("J2").levantarCarta(new Carta(4, Palo.BASTO));
        nuevoJuego.getJugador("J2").levantarCarta(new Carta(7, Palo.BASTO));
        nuevoJuego.getJugador("J2").levantarCarta(new Carta(10, Palo.BASTO));


        return nuevoJuego;
    }
}
