package truco.vista2.botoneras;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Mesa;
import truco.modelo.envido.EnvidoTerminado;
import truco.modelo.estadosTruco.*;
import truco.vista2.Programa;

import java.util.Collection;


public class BotoneraTrucoQuerido extends StackPane{

    private Programa interfaz;
    private Mesa mesa;
    private Button botonRetruco=new Button("RETRUCO");
    private Button botonNoQuiero=new Button("NO QUIERO");
    private Button botonValeCuatro=new Button("VALE CUATRO");
    private Button botonIrseAlMazo=new Button("IRSE AL MAZO");

    public BotoneraTrucoQuerido(Mesa mesa,Programa interfaz){

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

        vBox.getChildren().addAll(new Label("ACCIONES:"));

        if(mesa.getRonda().getTruco().getClass().equals(TrucoQuerido.class))
            vBox.getChildren().add(botonRetruco);
        if(mesa.getRonda().getTruco().getClass().equals(RetrucoQuerido.class) || mesa.getRonda().getTruco().getClass().equals(RetrucoCantado.class))
            vBox.getChildren().addAll(botonValeCuatro);

        if(mesa.getRonda().getTruco().getClass().equals(TrucoCantado.class) || mesa.getRonda().getTruco().getClass().equals(RetrucoCantado.class) || mesa.getRonda().getTruco().getClass().equals(ValeCuatroCantado.class))
        vBox.getChildren().addAll(botonNoQuiero);

        vBox.getChildren().addAll(botonIrseAlMazo);

        getChildren().addAll(vBox);

        setBotonNoQuiero();
        setBotonRetruco();
        setBotonValeCuatro();
        setBotonIrseAlMazo();

    }

    private void setBotonNoQuiero(){
        botonNoQuiero.setOnAction(e->{
            mesa.getJugadorActivo().noQuieroTruco();
            interfaz.nuevaRondaGrafica();
            interfaz.reloadPanelDerecho();
        });
    }

    private void setBotonRetruco(){
        botonRetruco.setOnAction(e->{
            if(!mesa.getJugadorActivo().tieneElQuiero())
                showErrorNoTieneElQuiero();
            else {
                mesa.getJugadorActivo().cantarRetruco();
                interfaz.getPanelIzquierdo().getChildren().clear();
                interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(mesa, interfaz));
                interfaz.reloadPanelDerecho();
            }
        });
    }

    private void setBotonValeCuatro(){
        botonValeCuatro.setOnAction(e -> {
                if(!mesa.getJugadorActivo().tieneElQuiero())
                    showErrorNoTieneElQuiero();
                else {
                    mesa.getJugadorActivo().aceptarTruco();
                    mesa.getJugadorActivo().cantarRetruco();
                    interfaz.getPanelIzquierdo().getChildren().clear();
                    interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(mesa, interfaz));
                    interfaz.reloadPanelDerecho();
                }
        });
    }

    void setBotonIrseAlMazo(){
        botonIrseAlMazo.setOnAction(e->{
            mesa.getJugadorActivo().irseAlMazo();
            interfaz.nuevaRondaGrafica();
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

