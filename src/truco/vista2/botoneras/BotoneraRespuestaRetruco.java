package truco.vista2.botoneras;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Mesa;

/**
 * Created by Eze Cruz Avila on 02/12/2015.
 */
public class BotoneraRespuestaRetruco extends StackPane{
    private StackPane stackIzquierdo;
    private Mesa mesa;
    Button botonQuiero=new Button("QUIERO");
    private Button botonValeCuatro =new Button("VALE CUATRO");
    private Button botonNoQuiero=new Button("NO QUIERO");

    public BotoneraRespuestaRetruco(Mesa mesa,StackPane stackIzquierdo){

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
        vBox.getChildren().addAll(botonValeCuatro);
        vBox.getChildren().addAll(botonNoQuiero);

        setBotonNoQuiero();
        setBotonQuiero();
        setBotonvaleCuatro();
    }

    private void setBotonQuiero(){
        botonQuiero.setOnAction(e->{
            mesa.getJugadorActivo().quieroTruco();
            stackIzquierdo.getChildren().clear();
            stackIzquierdo.getChildren().addAll(new BotoneraQuieroTruco());
        });
    }

    private void setBotonvaleCuatro(){
        botonValeCuatro.setOnAction(e -> {
            mesa.getJugadorActivo().aceptarTruco();
            mesa.getJugadorActivo().cantarRetruco();
            stackIzquierdo.getChildren().clear();
            stackIzquierdo.getChildren().addAll(new BotoneraRespuestaRetruco(mesa, stackIzquierdo));
        });
    }

    private void setBotonNoQuiero(){
        botonNoQuiero.setOnAction(e->{
            mesa.getJugadorActivo().noQuieroTruco();
        });
    }
}
