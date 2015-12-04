package truco.vistaFinal.botoneras;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Mesa;
import truco.modelo.envido.Envido;
import truco.modelo.envido.FaltaEnvido;
import truco.modelo.envido.RealEnvido;
import truco.modelo.excepciones.JugadorNoHabilitadoParaCantarTanto;
import truco.modelo.excepciones.JugadorNoTieneFlorException;
import truco.vistaFinal.Programa;


public class BotoneraInicial extends StackPane {

    private Programa interfaz;
    private Mesa mesa;
    private Button botonEnvido=new Button("ENVIDO");
    private Button botonRealEnvido=new Button("REAL ENVIDO");
    private Button botonFaltaEnvido=new Button("FALTA ENVIDO");
    private Button botonFlor=new Button("FLOR");
    private Button botonTruco=new Button("TRUCO");
    private Button botonIrseAlMazo=new Button("IRSE AL MAZO");

    public BotoneraInicial(Mesa mesa,Programa interfaz){

        Rectangle rectangle=new Rectangle(150,350);

        this.interfaz=interfaz;
        this.mesa=mesa;

        this.setHeight(350);
        this.setWidth(150);
        setPadding(new Insets(5,5,5,5));
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);
        rectangle.setFill(Color.RED);
        this.getChildren().addAll(rectangle);

        VBox vBox=new VBox();
        vBox.setSpacing(30);
        vBox.setAlignment(Pos.CENTER);


        vBox.getChildren().addAll(new Label("ACCIONES:"),botonEnvido,botonRealEnvido,botonFaltaEnvido);

        if(mesa.getArbitro().florEncendida())
            vBox.getChildren().addAll(botonFlor);

        vBox.getChildren().addAll(botonTruco,botonIrseAlMazo);

        this.getChildren().addAll(vBox);

        setBotonEnvido(botonEnvido,new Envido());
        setBotonEnvido(botonRealEnvido,new RealEnvido());
        setBotonEnvido(botonFaltaEnvido,new FaltaEnvido());
        setBotonIrseAlMazo();
        setBotonTruco();
        setBotonFlor();

    }

    private void setBotonFlor() {
        botonFlor.setOnAction(e->{
            interfaz.getHistorial().jugadorCantoFlor(mesa.getJugadorActivo());
            try{mesa.getJugadorActivo().cantarFlor();
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(mesa, interfaz));
            interfaz.reload_PanelDerecho();}
            catch (JugadorNoTieneFlorException b) {
                displayErrorNoTieneFlor();
            }
        });
    }

    private void setBotonTruco() {
        botonTruco.setOnAction(e->{
            interfaz.getHistorial().jugadorCantoTruco(mesa.getJugadorActivo());
            mesa.getJugadorActivo().cantarTruco();
            interfaz.getControlIA().accionarGrafico();

            if(mesa.IA_Activada()) {
                interfaz.getPanelIzquierdo().getChildren().clear();
                interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(mesa, interfaz));
            }

            interfaz.reload_PanelDerecho();
        });
    }

    private void setBotonEnvido(Button boton,Envido envido) {
        boton.setOnAction(e -> {
            try {
                mesa.getArbitro().jugadorPuedeCantarTanto(mesa.getJugadorActivo());
                interfaz.getHistorial().jugadorCantoEnvido(mesa.getJugadorActivo(),envido);
                mesa.getJugadorActivo().cantarEnvido(envido);

                interfaz.getControlIA().accionarGrafico();

                if(!mesa.IA_Activada()) {
                    interfaz.getPanelIzquierdo().getChildren().clear();
                    interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(mesa, interfaz));
                }

                interfaz.reload_PanelDerecho();
            } catch (JugadorNoHabilitadoParaCantarTanto b)
            {
                diplayErrorJugadorNoEnvido();
            }
        });
    }

    private void setBotonIrseAlMazo(){
        botonIrseAlMazo.setOnAction(e -> {
            interfaz.getHistorial().jugadorSeFueAlMazo(mesa.getJugadorActivo());
            mesa.getJugadorActivo().irseAlMazo();
            interfaz.nuevaRondaGrafica();
        });
    }

    private void diplayErrorJugadorNoEnvido(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(" Solo el pie canta el tanto");
        alert.getDialogPane().setPrefSize(250,50);
        alert.show();
    }

    private void displayErrorNoTieneFlor(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText("     No tiene Flor");
        alert.getDialogPane().setPrefSize(200,30);
        alert.show();

    }

}
