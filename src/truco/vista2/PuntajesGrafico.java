package truco.vista2;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import truco.modelo.Mesa;
import truco.modelo.Truco;


public class PuntajesGrafico extends Parent {

    private Truco truco;
    public PuntajesGrafico(Truco truco,Programa2 interfaz){

        this.truco=truco;
        Rectangle background=new Rectangle(100,50);
        background.setArcHeight(20);
        background.setArcWidth(20);
        background.setFill(Color.WHITE);
        Text text=new Text("EQUIPO 1: "+this.truco.getEquipo("E1").getPuntaje()+"\n"+"EQUIPO 2: "+this.truco.getEquipo("E2").getPuntaje());
        text.setWrappingWidth(90);
        text.setTextAlignment(TextAlignment.CENTER);
        StackPane pane=new StackPane();
        pane.getChildren().addAll(background,text);
        this.getChildren().addAll(pane);


    }
}
