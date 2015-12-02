package truco.vista2.botoneras;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Mesa;
import truco.modelo.envido.EnvidoTerminado;


public class BotoneraRespuestaTruco extends StackPane {

    private StackPane stackIzquierdo;
    private Mesa mesa;
    Button botonQuiero=new Button("QUIERO");
    private Button botonEnvido=new Button("ENVIDO");
    private Button botonRetruco=new Button("RETRUCO");
    private Button botonNoQuiero=new Button("NO QUIERO");

    public BotoneraRespuestaTruco(Mesa mesa,StackPane stackIzquierdo){

        Rectangle rectangle=new Rectangle(150,550);

        this.mesa=mesa;
        this.stackIzquierdo=stackIzquierdo;
        this.setHeight(550);
        this.setWidth(150);
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);
        rectangle.setFill(Color.GREEN);
        this.getChildren().addAll(rectangle);

        VBox vBox=new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(botonQuiero);

        if(mesa.getRonda().seEstaJugandoLaPrimera() && !mesa.getRonda().getEnvido().getClass().equals(EnvidoTerminado.class))
            vBox.getChildren().addAll(botonEnvido);

        vBox.getChildren().addAll(botonRetruco);
        vBox.getChildren().addAll(botonNoQuiero);

        setBotonQuiero();
        setBotonNoQuiero();
        setBotonRetruco();

    }

    private void setBotonQuiero(){
        botonQuiero.setOnAction(e->{
            mesa.getJugadorActivo().quieroTruco();
            stackIzquierdo.getChildren().clear();
            stackIzquierdo.getChildren().addAll(new BotoneraQuieroTruco());
        });
    }

    private void setBotonRetruco(){
        botonRetruco.setOnAction(e->{
            mesa.getJugadorActivo().aceptarTruco();
            mesa.getJugadorActivo().cantarRetruco();
            stackIzquierdo.getChildren().clear();
            stackIzquierdo.getChildren().addAll(new BotoneraRespuestaRetruco(mesa,stackIzquierdo));
        });
    }

    private void setBotonNoQuiero(){
        botonNoQuiero.setOnAction(e->{
            mesa.getJugadorActivo().noQuieroTruco();
        });
    }
}
