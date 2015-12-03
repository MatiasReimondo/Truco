package truco.vista.controladores.configuracion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import truco.modelo.Truco;

public class GraficadorDeConfiguracionEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorPrincipal;
    private HBox contenedorSegundario=new HBox();

    public GraficadorDeConfiguracionEventHandler(VBox contenedorPrincipal, Truco juego) {
        this.contenedorPrincipal = contenedorPrincipal;
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {

        //Se limpia el contenedor
        this.contenedorPrincipal.getChildren().clear();

        this.graficarConfiguracionFlor();
        this.graficarContenedorJugadores();
        this.graficarConfiguracionAI();

        contenedorSegundario.setSpacing(90);
        contenedorSegundario.setPadding( new Insets(30));

        this.contenedorPrincipal.getChildren().add(contenedorSegundario);

    }

    private void graficarConfiguracionAI() {

        Button configurarAI = new Button("JUGADORES PC");

        GraficadorConfiguracionAIEventHandler graficadorConfiguracionAIEventHandler = new GraficadorConfiguracionAIEventHandler(this.contenedorPrincipal, this.juego);
        configurarAI.setOnAction(graficadorConfiguracionAIEventHandler);

        contenedorSegundario.getChildren().add(new HBox(configurarAI));
    }

    private void graficarContenedorJugadores() {

        Label cantidadDeJugadores = new Label("Cantidad De Jugadores");

        TextField textField=new TextField();
        textField.setPromptText("2 - 4 - 6");
        textField.setAlignment(Pos.CENTER);
        Button botonJugadores=new Button("Aceptar");
        setBotonCantidadJugadores(botonJugadores,textField);

        VBox opcionesCantidad= new VBox(cantidadDeJugadores,botonJugadores, textField);
        opcionesCantidad.setSpacing(10);


        HBox hBox=new HBox(opcionesCantidad);
        hBox.setSpacing(10);
        contenedorSegundario.getChildren().add(hBox);
    }

    private void graficarConfiguracionFlor() {

        Label jugarConFlor = new Label("Jugar con Flor?");

        Button botonFlorSi = new Button("SI");
        setBotonFlorSi(botonFlorSi);

        Button botonFlorNo = new Button("NO");
        setBotonFLorNo(botonFlorNo);
        HBox hBox=new HBox(botonFlorSi, botonFlorNo);
        VBox vBox=new VBox(jugarConFlor,hBox);
        hBox.setSpacing(10);
        contenedorSegundario.getChildren().add(vBox);

    }

    private void setBotonFlorSi(Button button){
        button.setOnAction(e-> juego.seJuegaConFlor());
    }

    private void setBotonFLorNo(Button buttonFlorNo){
        buttonFlorNo.setOnAction(e->juego.seJuegaSinFlor());
    }

    private void setBotonCantidadJugadores(Button boton,TextField textField){
        boton.setOnAction(e->{

            juego.nuevoEquipo("Equipo-1");
            juego.nuevoEquipo("Equipo-2");

            switch (Integer.parseInt(textField.getText().intern())){
                case 2:{
                    juego.nuevoJugador("J1","Equipo-1");
                    juego.nuevoJugador("J2", "Equipo-2");
                    break;
                }
                case 4:{
                    juego.nuevoJugador("J1", "Equipo-1");
                    juego.nuevoJugador("J2", "Equipo-2");
                    juego.nuevoJugador("J3", "Equipo-1");
                    juego.nuevoJugador("J4", "Equipo-2");
                    break;
                }
                case 6:{
                    juego.nuevoJugador("J1", "Equipo-1");
                    juego.nuevoJugador("J2", "Equipo-2");
                    juego.nuevoJugador("J3", "Equipo-1");
                    juego.nuevoJugador("J4", "Equipo-2");
                    juego.nuevoJugador("J5", "Equipo-1");
                    juego.nuevoJugador("J6", "Equipo-2");
                    break;
                }
            }
            juego.empezarJuego();
        });

    }
}


