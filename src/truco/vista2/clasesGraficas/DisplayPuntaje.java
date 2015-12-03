package truco.vista2.clasesGraficas;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import truco.modelo.Truco;
import truco.vista2.Programa;


public class DisplayPuntaje extends Parent {

    private Truco truco;
    public DisplayPuntaje(Truco truco){

        this.truco=truco;
        Rectangle background=new Rectangle(160,70);
        background.setArcHeight(20);
        background.setArcWidth(20);
        background.setFill(Color.WHITE);
        Text text=new Text("PUNTAJES \n EQUIPO 1: "+this.truco.getEquipo1().getPuntaje()+"\n"+"EQUIPO 2: "+this.truco.getEquipo2().getPuntaje());
        text.setWrappingWidth(90);
        text.setTextAlignment(TextAlignment.CENTER);
        StackPane pane=new StackPane();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.getChildren().addAll(background,text);
        this.getChildren().addAll(pane);
        if(this.truco.terminado())
            displayEquipoGanador();
    }

    private void displayEquipoGanador(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(" Juego Terminado\n Resultados: "+truco.getEquipo1().getNombre()+": "+truco.getEquipo1().getPuntaje()+" "+truco.getEquipo2().getNombre()+": "+truco.getEquipo2().getPuntaje());
        alert.getDialogPane().setPrefSize(350,50);
        alert.show();
    }
}
