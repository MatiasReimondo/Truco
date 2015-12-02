package truco.vista2.botoneras;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Mesa;
import truco.modelo.envido.Envido;
import truco.modelo.envido.FaltaEnvido;
import truco.modelo.envido.RealEnvido;
import truco.vista2.Programa2;


public class BotoneraInicial extends StackPane {

    private StackPane stackIzquierdo;
    private Mesa mesa;
    private Button botonEnvido=new Button("ENVIDO");
    private Button botonRealEnvido=new Button("REAL ENVIDO");
    private Button botonFaltaEnvido=new Button("FALTA ENVIDO");
    private Button botonTruco=new Button("TRUCO");
    private Button botonIrseAlMazo=new Button("IRSE AL MAZO");

    public BotoneraInicial(){}
    public BotoneraInicial(Mesa mesa,Programa2 interfaz){

        Rectangle rectangle=new Rectangle(150,550);

        stackIzquierdo=interfaz.getStackIzquierdo();
        this.mesa=mesa;
        this.setHeight(550);
        this.setWidth(150);
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);
        rectangle.setFill(Color.GREEN);
        this.getChildren().addAll(rectangle);

        VBox vBox=new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);


        vBox.getChildren().addAll(botonEnvido,botonRealEnvido,botonFaltaEnvido,botonTruco,botonIrseAlMazo);
        this.getChildren().addAll(vBox);

        setBotonEnvido();
        setBotonFaltaEnvido();
        setBotonRealEnvido();
        setBotonIrseAlMazo();
        setBotonTruco();

    }

    private void setBotonTruco() {
        botonTruco.setOnAction(e->{
            mesa.getJugadorActivo().cantarTruco();
            stackIzquierdo.getChildren().clear();
            stackIzquierdo.getChildren().addAll(new BotoneraRespuestaTruco(mesa,stackIzquierdo));
        });
    }

    void setBotonEnvido() {
        botonEnvido.setOnAction(e -> {
            mesa.getJugadorActivo().cantarEnvido(new Envido());
            stackIzquierdo.getChildren().clear();
            stackIzquierdo.getChildren().addAll(new BotoneraRespuestaEnvido(mesa, stackIzquierdo));
        });
    }

    void setBotonRealEnvido(){
        botonRealEnvido.setOnAction(e -> {
            mesa.getJugadorActivo().cantarEnvido(new RealEnvido());
            stackIzquierdo.getChildren().clear();
            stackIzquierdo.getChildren().addAll(new BotoneraRespuestaEnvido(mesa, stackIzquierdo));
        });
    }

    void setBotonFaltaEnvido(){
        botonFaltaEnvido.setOnAction(e -> {
            mesa.getJugadorActivo().cantarEnvido(new FaltaEnvido());
            stackIzquierdo.getChildren().clear();
            stackIzquierdo.getChildren().addAll(new BotoneraRespuestaEnvido(mesa, stackIzquierdo));
        });
    }

    void setBotonIrseAlMazo(){
        botonIrseAlMazo.setOnAction(e->{
            mesa.getJugadorActivo().irseAlMazo();
        });
    }


}
