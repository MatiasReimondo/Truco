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
import truco.modelo.envido.EnvidoNoCantado;
import truco.modelo.envido.FaltaEnvido;
import truco.modelo.envido.RealEnvido;
import truco.vista2.Programa;

public class BotoneraRespuestaEnvido extends StackPane{

    private Mesa mesa;
    private Programa interfaz;
    private Button botonQuiero=new Button("QUIERO");
    private Button botonNoQuiero=new Button("NO QUIERO");
    private Button botonEnvido=new Button("ENVIDO");
    private Button botonRealEnvido=new Button("REAL ENVIDO");
    private Button botonFaltaEnvido=new Button("FALTA ENVIDO");
    private Button botonIrseAlMazo=new Button("IRSE AL MAZO");

    public BotoneraRespuestaEnvido(Mesa mesa,Programa interfaz){

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
        vBox.setSpacing(30);
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(new Label("ACCIONES:"), botonQuiero);

        if(mesa.getRonda().getEnvido().getEnvidoCantado()!= null && mesa.getRonda().getEnvido().getEnvidoCantado().getClass().equals(Envido.class) && mesa.getRonda().getEnvido().getClass().equals(EnvidoNoCantado.class))
            vBox.getChildren().addAll(botonEnvido);

        if(mesa.getRonda().getEnvido().getEnvidoCantado()!= null &&!mesa.getRonda().getEnvido().getClass().equals(RealEnvido.class) && !mesa.getRonda().getEnvido().getEnvidoCantado().getClass().equals(RealEnvido.class))
            vBox.getChildren().addAll(botonRealEnvido);

        if(mesa.getRonda().getEnvido().getEnvidoCantado()!= null &&!mesa.getRonda().getEnvido().getClass().equals(FaltaEnvido.class) && !mesa.getRonda().getEnvido().getEnvidoCantado().getClass().equals(FaltaEnvido.class))
            vBox.getChildren().addAll(botonFaltaEnvido);

        if(mesa.getRonda().getEnvido().getEnvidoCantado()!=null)
            vBox.getChildren().addAll(botonNoQuiero);
        vBox.getChildren().addAll(botonIrseAlMazo);
        this.getChildren().addAll(vBox);

        setBotonQuiero();
        setBotonEnvido(botonEnvido,new Envido());
        setBotonEnvido(botonRealEnvido,new RealEnvido());
        setBotonEnvido(botonFaltaEnvido,new FaltaEnvido());
        setBotonNoQuiero();

    }

    private void setBotonQuiero(){
        botonQuiero.setOnAction(e->{
            interfaz.getHistorial().jugadorQuisoEnvido(mesa.getJugadorActivo(),mesa.getRonda().getEnvido());
            mesa.getJugadorActivo().quieroEnvido();
            mesa.resolverEnvido();
            interfaz.getHistorial().envidoResuelto();
            interfaz.actualizarPuntajeGrafico();

            interfaz.getControlIA().accionarGrafico();
            if(!mesa.IA_Activada()) {
                interfaz.getPanelIzquierdo().getChildren().clear();
                interfaz.getPanelIzquierdo().getChildren().add(new BotoneraPostEnvido(mesa, interfaz));
            }
            interfaz.reload_PanelDerecho();

        });
    }

    private void setBotonEnvido(Button boton,Envido envido){
        boton.setOnAction(e->{
            mesa.getJugadorActivo().aceptarEnvido();
            interfaz.getHistorial().jugadorCantoEnvido(mesa.getJugadorActivo(),envido);
            mesa.getJugadorActivo().cantarEnvido(envido);
            interfaz.getControlIA().accionarGrafico();
            if(!mesa.IA_Activada()) {
                interfaz.getPanelIzquierdo().getChildren().clear();
                interfaz.getPanelIzquierdo().getChildren().add(new BotoneraRespuestaEnvido(mesa, interfaz));
            }
            interfaz.reload_PanelDerecho();
        });
    }


    private void setBotonNoQuiero(){
        botonNoQuiero.setOnAction(e->{
                    interfaz.getHistorial().jugadorNoQuisoEnvido(mesa.getJugadorActivo(),mesa.getRonda().getEnvido().getEnvidoCantado());
                    mesa.getJugadorActivo().noQuieroEnvido();
                    interfaz.getPanelIzquierdo().getChildren().clear();
                    interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraPostEnvido(mesa,interfaz));
                    interfaz.getControlIA().accionarGrafico();
                    interfaz.reload_PanelDerecho();
                }
        );
    }
}
