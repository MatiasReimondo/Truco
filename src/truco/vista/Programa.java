package truco.vista;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import truco.modelo.Carta;
import truco.modelo.Palo;
import truco.modelo.Truco;
import truco.vista.controladores.*;

/*********************************************************************************
 ********************************* Programa **************************************
 *********************************************************************************/
public class Programa extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("******* TRUCO ******");
        Truco juegoTruco = this.crearModelo();
        VBox contenedorVertical = new VBox();

        //boton que muestra la informacion del grupo
        Button botonInformacion = new Button("Acerca de");
        this.agregarInformacionAContenedor(botonInformacion, contenedorVertical);

        //Boton del juego
        Button botonJuego = new Button("Jugar");
        this.agregarBotonesDelJuegoAContenedor(botonJuego, contenedorVertical, juegoTruco);

        Button botonSalir = new Button("Salir");
        this.salir(botonSalir, contenedorVertical);

        HBox contenedorHorizontalDeOpciones = new HBox(botonJuego, botonInformacion, botonSalir);
        contenedorHorizontalDeOpciones.setSpacing(30);

        /****** Contenedor principal de la aplicacion *******/
        VBox contenedorPrincipal = new VBox(contenedorHorizontalDeOpciones,contenedorVertical);
        contenedorPrincipal.setSpacing(20);
        contenedorPrincipal.setPadding(new Insets(5));

        Scene scene = new Scene(contenedorPrincipal,800,400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /************ Metodos privados ***************/
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

    private void agregarInformacionAContenedor(Button botonInformacion, VBox contenedorInformacion) {

        BotonInformacionEventHandler botonInformacionEventHandler = new BotonInformacionEventHandler(contenedorInformacion);
        botonInformacion.setOnAction(botonInformacionEventHandler);
        TextoInformacionEventHandler textoInformacionEventHandler = new TextoInformacionEventHandler(botonInformacion);
    }

    private void agregarBotonesDelJuegoAContenedor(Button botonJuego, VBox contenedorDeBotones, Truco juego) {

        BotonJuegoEventHandler botonJuegoEventHandler = new BotonJuegoEventHandler(contenedorDeBotones, juego);
        botonJuego.setOnAction(botonJuegoEventHandler);
        TextoJuegoEventHandler textoJuegoEventHandler = new TextoJuegoEventHandler(botonJuego);
        contenedorDeBotones.setSpacing(5);
    }

    private void salir(Button botonSalir, VBox contenedorVertical) {

        BotonSalirEventHandler botonSalirEventHandler = new BotonSalirEventHandler(contenedorVertical);
        botonSalir.setOnAction(botonSalirEventHandler);
        TextoSalirDelJuegoEventHandler textoSalirEventHandler = new TextoSalirDelJuegoEventHandler(botonSalir);
    }

}
