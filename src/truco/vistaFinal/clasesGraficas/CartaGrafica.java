package truco.vistaFinal.clasesGraficas;

import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import truco.modelo.Carta;
import truco.modelo.excepciones.RondaTerminadaException;

public class CartaGrafica extends Parent {

    private Carta carta;

    public CartaGrafica(Carta carta,DisplayMesa displayMesa){

        this.carta=carta;

        Rectangle background=new Rectangle(80,120);
        background.setArcHeight(20);
        background.setArcWidth(20);
        background.setFill(Color.GREENYELLOW);
        Text text=new Text(String.valueOf(carta.getNumero())+"\n"+" DE "+"\n"+carta.getPalo());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(90);
        getChildren().add(new StackPane(background, text));

        setOnMouseClicked(e ->{ try{
            displayMesa.jugarCartaGrafica(this);} catch (RondaTerminadaException b)
        {showErrorRondaTerminada();}});

    }

    public Carta getCarta(){
        return carta;
    }

    private void showErrorRondaTerminada(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText("          La ronda ya termino");
        alert.getDialogPane().setPrefSize(250,50);
        alert.show();
    }

}
