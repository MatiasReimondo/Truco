package truco.vista2.clasesGraficas;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import truco.modelo.Mesa;
import truco.vista2.Programa;

public class DisplayTurnoJugador extends Parent {

    public DisplayTurnoJugador(Mesa mesa,Programa interfaz){

        Rectangle background=new Rectangle(150,60);
        background.setArcHeight(20);
        background.setArcWidth(20);

        background.setFill(Color.BLUE);
        Text text=new Text("TURNO: \n"+mesa.getJugadorActivo().getNombre());
        text.setWrappingWidth(90);
        text.setTextAlignment(TextAlignment.CENTER);
        StackPane pane=new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(background,text);
        this.getChildren().addAll(pane);


    }
}
