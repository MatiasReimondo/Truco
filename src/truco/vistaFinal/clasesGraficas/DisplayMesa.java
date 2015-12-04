package truco.vistaFinal.clasesGraficas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.RetrucoCantado;
import truco.modelo.estadosTruco.TrucoCantado;
import truco.modelo.estadosTruco.TrucoNoCantado;
import truco.modelo.estadosTruco.ValeCuatroCantado;
import truco.vistaFinal.Programa;
import truco.vistaFinal.botoneras.BotoneraPostEnvido;

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

    public SlotJugador encontrarSlot(Jugador jugador){
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

         if(seCantoAlgo()){
            showErrorResponderApuesta();
            return;
         }

        this.encontrarSlot(mesa.getJugadorActivo()).getChildren().clear();
        this.encontrarSlot(mesa.getJugadorActivo()).getChildren().addAll(cartaGrafica, new Label(mesa.getJugadorActivo().getNombre()));

        interfaz.getHistorial().jugadorJugoCarta(mesa.getJugadorActivo(), cartaGrafica.getCarta());

        mesa.getJugadorActivo().jugarCarta(cartaGrafica.getCarta().getNumero(), cartaGrafica.getCarta().getPalo());

        if(mesa.getRonda().getManoEnJuego().size()==mesa.getNroJugadores())
            mesa.resolverMano();

        interfaz.getControlIA().accionarGrafico();

        interfaz.reload_PanelDerecho();

        actualizarBotonera();

        if(mesa.getRonda().termino()) {
            interfaz.finalDeRonda();
        }
        else
            interfaz.actualizarManoGrafica();

    }

    private boolean seCantoAlgo(){
        return (mesa.getRonda().getTruco().getClass().equals(TrucoCantado.class) ||
                mesa.getRonda().getTruco().getClass().equals(RetrucoCantado.class) ||
                mesa.getRonda().getTruco().getClass().equals(ValeCuatroCantado.class));
    }



    private void actualizarBotonera(){
        if(mesa.getRonda().getResultados().size()==1 && mesa.getRonda().getTruco().getClass().equals(TrucoNoCantado.class)) {
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraPostEnvido(mesa,interfaz));
        }
    }

    private void showErrorResponderApuesta(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText("Debe responder la apuesta antes de jugar");
        alert.getDialogPane().setPrefSize(250,50);
        alert.show();
    }
}
