package truco.vista2.clasesGraficas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import truco.modelo.Jugador;

public class SlotJugador extends StackPane {

    private Jugador jugadorRelacionado;

    public SlotJugador(){

        Rectangle bg=new Rectangle(80,120);
        bg.setFill(Color.WHITESMOKE);
        setPadding(new Insets(5,5,5,5));
        bg.setArcWidth(30);
        bg.setArcHeight(30);
        this.setWidth(110);
        this.setHeight(130);
        this.setStyle("-fx-border-color: rgba(0, 0, 0, 1)");
        this.getChildren().addAll(bg);
    }

    public void setJugadorRelacionado(Jugador jugador){

        Label etiqueta=new Label(jugador.getNombre());
        etiqueta.setAlignment(Pos.BOTTOM_CENTER);
        getChildren().addAll(etiqueta);
        jugadorRelacionado=jugador;
    }


    public Jugador geJugador() {
        return jugadorRelacionado;
    }
}
