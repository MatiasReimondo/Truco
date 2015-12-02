package truco.vista2.botoneras;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Mesa;
import truco.modelo.envido.Envido;
import truco.modelo.envido.EnvidoNoCantado;
import truco.modelo.envido.FaltaEnvido;
import truco.modelo.envido.RealEnvido;

public class BotoneraRespuestaEnvido extends StackPane{

    private StackPane stackIzquierdo;
    Button botonQuiero=new Button("QUIERO");
    private Button botonEnvido=new Button("ENVIDO");
    private Button botonRealEnvido=new Button("REAL ENVIDO");
    private Button botonFaltaEnvido=new Button("FALTA ENVIDO");
    private Button botonIrseAlMazo=new Button("IRSE AL MAZO");

    public BotoneraRespuestaEnvido(Mesa mesa,StackPane stackIzquierdo){

        Rectangle rectangle=new Rectangle(150,550);

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

        if(!mesa.getRonda().getEnvido().getClass().equals(EnvidoNoCantado.class))
            vBox.getChildren().addAll(botonQuiero);
        if(mesa.getRonda().getEnvido().getEnvidoCantado()!= null && mesa.getRonda().getEnvido().getEnvidoCantado().getClass().equals(Envido.class) && mesa.getRonda().getEnvido().getClass().equals(EnvidoNoCantado.class)) {
            vBox.getChildren().addAll(botonEnvido);
        }

        if(mesa.getRonda().getEnvido().getEnvidoCantado()!= null &&!mesa.getRonda().getEnvido().getClass().equals(RealEnvido.class) && !mesa.getRonda().getEnvido().getEnvidoCantado().getClass().equals(RealEnvido.class))
            vBox.getChildren().addAll(botonRealEnvido);

        if(mesa.getRonda().getEnvido().getEnvidoCantado()!= null &&!mesa.getRonda().getEnvido().getClass().equals(FaltaEnvido.class) && !mesa.getRonda().getEnvido().getEnvidoCantado().getClass().equals(FaltaEnvido.class))
            vBox.getChildren().addAll(botonFaltaEnvido);

        vBox.getChildren().addAll(botonIrseAlMazo);
        this.getChildren().addAll(vBox);

    }

}
