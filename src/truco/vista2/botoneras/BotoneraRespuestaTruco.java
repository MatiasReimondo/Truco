package truco.vista2.botoneras;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Mesa;
import truco.modelo.envido.EnvidoTerminado;
import truco.vista2.Programa;


public class BotoneraRespuestaTruco extends StackPane {

    private Programa interfaz;
    private Mesa mesa;
    Button botonQuiero=new Button("QUIERO");
    private Button botonEnvido=new Button("ENVIDO");
    private Button botonRetruco=new Button("RETRUCO");
    private Button botonNoQuiero=new Button("NO QUIERO");

    public BotoneraRespuestaTruco(Mesa mesa,Programa interfaz){

        Rectangle rectangle=new Rectangle(150,350);

        this.mesa=mesa;
        this.interfaz=interfaz;
        this.setHeight(350);
        this.setWidth(150);
        setPadding(new Insets(5, 5, 5, 5));
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);
        rectangle.setFill(Color.RED);
        this.getChildren().addAll(rectangle);

        VBox vBox=new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(new Label("ACCIONES:"), botonQuiero);

        if(mesa.getRonda().seEstaJugandoLaPrimera() && !mesa.getRonda().getEnvido().getClass().equals(EnvidoTerminado.class))
            vBox.getChildren().addAll(botonEnvido);

        vBox.getChildren().addAll(botonRetruco);
        vBox.getChildren().addAll(botonNoQuiero);

        getChildren().addAll(vBox);
        setBotonQuiero();
        setBotonNoQuiero();
        setBotonRetruco();

    }

    private void setBotonQuiero(){
        botonQuiero.setOnAction(e->{
            mesa.getJugadorActivo().quieroTruco();
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraTrucoQuerido(mesa,interfaz));
            interfaz.reloadPanelDerecho();
        });
    }

    private void setBotonRetruco(){
        botonRetruco.setOnAction(e->{
            mesa.getJugadorActivo().aceptarTruco();
            mesa.getJugadorActivo().cantarRetruco();
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaRetruco(mesa,interfaz));
            interfaz.reloadPanelDerecho();
        });
    }

    private void setBotonNoQuiero(){
        botonNoQuiero.setOnAction(e->{
            mesa.getJugadorActivo().noQuieroTruco();
            interfaz.nuevaRondaGrafica();
            interfaz.reloadPanelDerecho();
        });
    }
}
