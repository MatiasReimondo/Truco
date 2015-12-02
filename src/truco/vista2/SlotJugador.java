package truco.vista2;

import javafx.scene.layout.StackPane;
import truco.modelo.Jugador;

public class SlotJugador extends StackPane {

    private Jugador jugadorRelacionado;

    public SlotJugador(){
        this.setWidth(110);
        this.setHeight(130);
        this.setStyle("-fx-border-color: rgba(0, 0, 0, 1)");
    }

    public void setJugadorRelacionado(Jugador jugador){
        jugadorRelacionado=jugador;
    }


    public Jugador geJugador() {
        return jugadorRelacionado;
    }
}
