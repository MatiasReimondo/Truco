package truco.vista2;

import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import truco.modelo.Carta;
import truco.modelo.Mesa;

public class CartaGrafica extends Parent {

    private MesaGrafica mesaGrafica;
    private Carta carta;
    private Button boton=new Button("Jugar");
    private Mesa mesa;

    public CartaGrafica(Carta carta,Mesa mesa,MesaGrafica mesaGrafica){

        this.carta=carta;
        this.mesa=mesa;
        this.mesaGrafica=mesaGrafica;

        Rectangle background=new Rectangle(100,140);
        background.setArcHeight(20);
        background.setArcWidth(20);
        background.setFill(Color.YELLOW);
        Text text=new Text(String.valueOf(carta.getNumero())+"\n"+" DE "+"\n"+carta.getPalo());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(90);
        getChildren().add(new StackPane(background, text));

        boton.setOnAction(e->mesaGrafica.jugarCartaGrafica(this));
        getChildren().add(boton);

    }

    public Carta getCarta(){
        return carta;
    }


}
