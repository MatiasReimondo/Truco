package truco.vistaFinal.botoneras;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Mesa;
import truco.vistaFinal.Programa;

public class BotoneraPostEnvido extends StackPane {

    private Programa interfaz;
    private Mesa mesa;
    private Button botonTruco=new Button("TRUCO");
    private Button botonIrseAlMazo=new Button("IRSE AL MAZO");

    public BotoneraPostEnvido(Mesa mesa,Programa interfaz){

        this.mesa=mesa;
        this.interfaz=interfaz;

        Rectangle rectangle=new Rectangle(150,350);
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

        vBox.getChildren().addAll(new Label("ACCIONES:"),botonTruco,botonIrseAlMazo);
        getChildren().addAll(vBox);

        setBotonIrseAlMazo();
        setBotonTruco();

    }

    private void setBotonTruco(){
        botonTruco.setOnAction(e->{
            interfaz.getHistorial().jugadorCantoTruco(mesa.getJugadorActivo());
            mesa.getJugadorActivo().cantarTruco();
            interfaz.getControlIA().accionarGrafico();

            if(!mesa.IA_Activada()) {
                interfaz.getPanelIzquierdo().getChildren().clear();
                interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(mesa, interfaz));
            }
            interfaz.reload_PanelDerecho();
        });
    }

    private void setBotonIrseAlMazo(){
        botonIrseAlMazo.setOnAction(e->{
            interfaz.getHistorial().jugadorSeFueAlMazo(mesa.getJugadorActivo());
            mesa.getJugadorActivo().irseAlMazo();
            interfaz.nuevaRondaGrafica();

        });
    }
}
