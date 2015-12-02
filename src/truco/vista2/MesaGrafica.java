package truco.vista2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Jugador;
import truco.modelo.Mesa;

import java.util.List;

public class MesaGrafica extends StackPane {

    private  ManoGrafica manoGrafica;
    private Mesa mesa;
    private Programa2 interfaz;
    private SlotJugador slotJugador1=new SlotJugador();
    private SlotJugador slotJugador2=new SlotJugador();
    private SlotJugador slotJugador3=new SlotJugador();
    private SlotJugador slotJugador4=new SlotJugador();
    private SlotJugador slotJugador5=new SlotJugador();
    private SlotJugador slotJugador6=new SlotJugador();

    public MesaGrafica(Mesa mesa, Programa2 interfaz){

        this.manoGrafica=manoGrafica;
        this.interfaz=interfaz;
        this.mesa=mesa;

        this.setWidth(400);
        this.setHeight(200);
        this.setPadding(new Insets(5,5,5,5));

        Rectangle fondo=new Rectangle(350,350);
        fondo.setFill(Color.RED);
        fondo.setArcHeight(30);
        fondo.setArcWidth(30);

        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(fondo);

        slotJugador1.setAlignment(Pos.TOP_LEFT);
        slotJugador2.setAlignment(Pos.TOP_CENTER);
        slotJugador3.setAlignment(Pos.TOP_RIGHT);
        slotJugador4.setAlignment(Pos.BOTTOM_LEFT);
        slotJugador5.setAlignment(Pos.BOTTOM_CENTER);
        slotJugador6.setAlignment(Pos.BOTTOM_RIGHT);

        this.getChildren().addAll(slotJugador1,slotJugador2,slotJugador3,slotJugador4,slotJugador5,slotJugador6);
    }

    public void linkearJugadores(List<Jugador> jugadores){

        switch (jugadores.size()){
            case 2:{
                slotJugador1.setJugadorRelacionado(jugadores.get(0));
                slotJugador2.setJugadorRelacionado(jugadores.get(1));
                break;
            }
            case 4:{
                slotJugador1.setJugadorRelacionado(jugadores.get(0));
                slotJugador2.setJugadorRelacionado(jugadores.get(1));
                slotJugador3.setJugadorRelacionado(jugadores.get(2));
                slotJugador4.setJugadorRelacionado(jugadores.get(3));
                break;
            }
            case 6:{
                slotJugador1.setJugadorRelacionado(jugadores.get(0));
                slotJugador2.setJugadorRelacionado(jugadores.get(1));
                slotJugador3.setJugadorRelacionado(jugadores.get(2));
                slotJugador4.setJugadorRelacionado(jugadores.get(3));
                slotJugador5.setJugadorRelacionado(jugadores.get(4));
                slotJugador6.setJugadorRelacionado(jugadores.get(5));
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

        if(this.encontrarSlot(mesa.getJugadorActivo()).getChildren()==null)
            this.encontrarSlot(mesa.getJugadorActivo()).getChildren().add(cartaGrafica);
        else {
            this.encontrarSlot(mesa.getJugadorActivo()).getChildren().clear();
            this.encontrarSlot(mesa.getJugadorActivo()).getChildren().add(cartaGrafica);
        }

        mesa.getJugadorActivo().jugarCarta(cartaGrafica.getCarta().getNumero(), cartaGrafica.getCarta().getPalo());
        if(mesa.getRonda().getManoEnJuego().size()==mesa.getNroJugadores())
            mesa.resolverMano();
        interfaz.getManoGrafica().renovar();

        }


}
