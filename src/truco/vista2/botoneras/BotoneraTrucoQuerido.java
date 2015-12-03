package truco.vista2.botoneras;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Mesa;
import truco.modelo.envido.EnvidoTerminado;
import truco.vista2.Programa;

import java.util.Collection;


public class BotoneraTrucoQuerido extends StackPane{

    private Programa interfaz;
    private Mesa mesa;
    private Button botonRetruco=new Button("RETRUCO");
    private Button botonNoQuiero=new Button("NO QUIERO");

    public BotoneraTrucoQuerido(Mesa mesa,Programa interfaz){

        Rectangle rectangle=new Rectangle(150,350);

        this.mesa=mesa;
        this.interfaz=interfaz;
        this.setHeight(350);
        this.setWidth(150);
        this.setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(5, 5, 5, 5));
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);
        rectangle.setFill(Color.RED);
        this.getChildren().addAll(rectangle);

        VBox vBox=new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(new Label("ACCIONES:"),botonRetruco);
        vBox.getChildren().addAll(botonNoQuiero);

        getChildren().addAll(vBox);
        setBotonNoQuiero();
        setBotonRetruco();

    }

    private void setBotonRetruco(){
        botonRetruco.setOnAction(e->{
            if(!mesa.getJugadorActivo().tieneElQuiero())

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

