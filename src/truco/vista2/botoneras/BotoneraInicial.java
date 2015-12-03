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
import truco.modelo.envido.Envido;
import truco.modelo.envido.FaltaEnvido;
import truco.modelo.envido.RealEnvido;
import truco.vista2.Programa;


public class BotoneraInicial extends StackPane {

    private Programa interfaz;
    private Mesa mesa;
    private Button botonEnvido=new Button("ENVIDO");
    private Button botonRealEnvido=new Button("REAL ENVIDO");
    private Button botonFaltaEnvido=new Button("FALTA ENVIDO");
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


        vBox.getChildren().addAll(new Label("ACCIONES:"),botonEnvido,botonRealEnvido,botonFaltaEnvido,botonTruco,botonIrseAlMazo);
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
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(mesa,interfaz));
            interfaz.reloadPanelDerecho();
        });
    }

    void setBotonEnvido() {
        botonEnvido.setOnAction(e -> {
            mesa.getJugadorActivo().cantarEnvido(new Envido());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(mesa, interfaz));
            interfaz.reloadPanelDerecho();
        });
    }

    void setBotonRealEnvido(){
        botonRealEnvido.setOnAction(e -> {
            mesa.getJugadorActivo().cantarEnvido(new RealEnvido());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(mesa, interfaz));
            interfaz.reloadPanelDerecho();
        });
    }

    void setBotonFaltaEnvido(){
        botonFaltaEnvido.setOnAction(e -> {
            mesa.getJugadorActivo().cantarEnvido(new FaltaEnvido());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(mesa, interfaz));
            interfaz.reloadPanelDerecho();
        });
    }

    void setBotonIrseAlMazo(){
        botonIrseAlMazo.setOnAction(e->{
            mesa.getJugadorActivo().irseAlMazo();
            interfaz.nuevaRondaGrafica();
        });
    }


}
