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
import truco.modelo.envido.EnvidoTerminado;
import truco.modelo.estadosTruco.RetrucoCantado;
import truco.modelo.estadosTruco.TrucoCantado;
import truco.vistaFinal.Programa;


public class BotoneraRespuestaTruco extends StackPane {

    private Programa interfaz;
    private Mesa mesa;
    Button botonQuiero=new Button("QUIERO");
    private Button botonEnvido=new Button("ENVIDO");
    private Button botonRetruco=new Button("RETRUCO");
    private Button botonValeCuatro=new Button("VALE CUATRO");
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

        vBox.getChildren().addAll(new Label("ACCIONES:"), botonQuiero,botonNoQuiero);


        if(mesa.getRonda().seEstaJugandoLaPrimera() && !mesa.getRonda().getEnvido().getClass().equals(EnvidoTerminado.class))
            vBox.getChildren().addAll(botonEnvido);
        if(mesa.getRonda().getTruco().getClass().equals(RetrucoCantado.class))
            vBox.getChildren().addAll(botonValeCuatro);
        if(mesa.getRonda().getTruco().getClass().equals(TrucoCantado.class))
            vBox.getChildren().addAll(botonRetruco);


        getChildren().addAll(vBox);

        setBotonQuiero();
        setBotonNoQuiero();
        setBotonRetruco();
        setBotonValeCuatro();

    }

    private void setBotonQuiero(){
        botonQuiero.setOnAction(e->{
            interfaz.getHistorial().jugadorQuisoTruco(mesa.getJugadorActivo(),mesa.getRonda().getTruco());
            mesa.getJugadorActivo().quieroTruco();
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraTrucoQuerido(mesa,interfaz));

            interfaz.getControlIA().accionarGrafico();
            interfaz.reload_PanelDerecho();
        });
    }

    private void setBotonNoQuiero(){
        botonNoQuiero.setOnAction(e->{
            mesa.getJugadorActivo().noQuieroTruco();
            interfaz.nuevaRondaGrafica();
            interfaz.reload_PanelDerecho();
        });
    }

    private void setBotonRetruco(){
        botonRetruco.setOnAction(e->{
            if(!mesa.getJugadorActivo().tieneElQuiero())
                showErrorNoTieneElQuiero();
            else {
                mesa.getJugadorActivo().aceptarTruco();
                interfaz.getHistorial().jugadorCantoRetruco(mesa.getJugadorActivo());
                mesa.getJugadorActivo().cantarRetruco();

                interfaz.getControlIA().accionarGrafico();
                if(!mesa.IA_Activada()) {
                    interfaz.getPanelIzquierdo().getChildren().clear();
                    interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(mesa, interfaz));
                }
                interfaz.reload_PanelDerecho();
            }
        });
    }

    private void setBotonValeCuatro(){
        botonValeCuatro.setOnAction(e -> {
            if(!mesa.getJugadorActivo().tieneElQuiero())
                showErrorNoTieneElQuiero();
            else {
                interfaz.getHistorial().jugadorCantoValeCuatro(mesa.getJugadorActivo());
                mesa.getJugadorActivo().aceptarTruco();
                mesa.getJugadorActivo().cantarValeCuatro();
                interfaz.getControlIA().accionarGrafico();
                if(!mesa.IA_Activada()) {
                    interfaz.getPanelIzquierdo().getChildren().clear();
                    interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(mesa, interfaz));
                }
                interfaz.reload_PanelDerecho();
            }
        });
    }

    private void showErrorNoTieneElQuiero(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText("          No tiene el Quiero");
        alert.getDialogPane().setPrefSize(250,50);
        alert.show();
    }
}
