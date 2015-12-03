package truco.vista2.clasesGraficas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.vista2.Programa;
import truco.vista2.botoneras.BotoneraPostEnvido;

import java.util.List;

public class DisplayMesa extends StackPane {

    private Mesa mesa;
    private Programa interfaz;
    private SlotJugador slotJugador1=new SlotJugador();
    private SlotJugador slotJugador2=new SlotJugador();
    private SlotJugador slotJugador3=new SlotJugador();
    private SlotJugador slotJugador4=new SlotJugador();
    private SlotJugador slotJugador5=new SlotJugador();
    private SlotJugador slotJugador6=new SlotJugador();

    public DisplayMesa(Mesa mesa, Programa interfaz){

        this.interfaz=interfaz;
        this.mesa=mesa;

        this.setWidth(400);
        this.setHeight(300);
        this.setPadding(new Insets(5,5,5,5));

        Rectangle fondo=new Rectangle(400,300);
        fondo.setArcHeight(30);
        fondo.setArcWidth(30);
        fondo.setFill(Color.DARKGREEN);

        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(fondo);

        linkearJugadores(mesa.getJugadores());
    }

    public void linkearJugadores(List<Jugador> jugadores){

        switch (jugadores.size()){
            case 2:{
                slotJugador1.setJugadorRelacionado(jugadores.get(0)); slotJugador1.setAlignment(Pos.BOTTOM_CENTER); this.getChildren().add(slotJugador1);
                slotJugador2.setJugadorRelacionado(jugadores.get(1)); slotJugador2.setAlignment(Pos.TOP_CENTER);    this.getChildren().add(slotJugador2);
                break;
            }
            case 4:{
                slotJugador1.setJugadorRelacionado(jugadores.get(0)); slotJugador1.setAlignment(Pos.BOTTOM_LEFT);this.getChildren().add(slotJugador1);
                slotJugador2.setJugadorRelacionado(jugadores.get(1)); slotJugador2.setAlignment(Pos.BOTTOM_RIGHT);this.getChildren().add(slotJugador2);
                slotJugador3.setJugadorRelacionado(jugadores.get(2)); slotJugador3.setAlignment(Pos.TOP_RIGHT);this.getChildren().add(slotJugador3);
                slotJugador4.setJugadorRelacionado(jugadores.get(3)); slotJugador4.setAlignment(Pos.TOP_LEFT);this.getChildren().add(slotJugador4);
                break;
            }
            case 6:{
                slotJugador1.setJugadorRelacionado(jugadores.get(0));slotJugador1.setAlignment(Pos.BOTTOM_LEFT);this.getChildren().add(slotJugador1);
                slotJugador2.setJugadorRelacionado(jugadores.get(1));slotJugador2.setAlignment(Pos.BOTTOM_CENTER);this.getChildren().add(slotJugador2);
                slotJugador3.setJugadorRelacionado(jugadores.get(2));slotJugador3.setAlignment(Pos.BOTTOM_RIGHT);this.getChildren().add(slotJugador3);
                slotJugador4.setJugadorRelacionado(jugadores.get(3));slotJugador4.setAlignment(Pos.TOP_RIGHT);this.getChildren().add(slotJugador4);
                slotJugador5.setJugadorRelacionado(jugadores.get(4));slotJugador5.setAlignment(Pos.TOP_CENTER);this.getChildren().add(slotJugador5);
                slotJugador6.setJugadorRelacionado(jugadores.get(5));slotJugador6.setAlignment(Pos.TOP_LEFT);this.getChildren().add(slotJugador6);
            }
        }
    }

    private SlotJugador encontrarSlot(Jugador jugador){
        if(slotJugador1.geJugador().equals(jugador))
            return slotJugador1;
        if(slotJugador2.geJugador().equals(jugador))
            return slotJugador2;
        if(slotJugador3.geJugador().equals(jugador))
            return slotJugador3;
        if(slotJugador4.geJugador().equals(jugador))
            return slotJugador4;
        if(slotJugador5.geJugador().equals(jugador))
            return slotJugador5;
        return slotJugador6;
    }

    public void jugarCartaGrafica(CartaGrafica cartaGrafica) {

        this.encontrarSlot(mesa.getJugadorActivo()).getChildren().clear();
        this.encontrarSlot(mesa.getJugadorActivo()).getChildren().add(cartaGrafica);


        mesa.getJugadorActivo().jugarCarta(cartaGrafica.getCarta().getNumero(), cartaGrafica.getCarta().getPalo());

        interfaz.reloadPanelDerecho();

        if(mesa.getRonda().getManoEnJuego().size()==mesa.getNroJugadores())
            mesa.resolverMano();

        if(!mesa.getRonda().seEstaJugandoLaPrimera()) {
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraPostEnvido(mesa,interfaz));
        }
        if(mesa.getRonda().termino()) {
            interfaz.reloadPanelDerecho();
            Button botonRonda=new Button("SIGUIENTE RONDA");
            botonRonda.setOnAction(e->interfaz.nuevaRondaGrafica());
            botonRonda.setAlignment(Pos.BOTTOM_CENTER);
            interfaz.getPanelDerecho().getChildren().addAll(new StackPane(botonRonda));
        }
        else
            interfaz.actualizarManoGrafica();

    }


}
