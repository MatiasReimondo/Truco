package truco.vista2.clasesGraficas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import truco.modelo.Carta;
import truco.modelo.Mesa;
import truco.vista2.Programa;

public class DisplayMano extends StackPane {

    private Mesa mesa;
    private HBox mano;

    public DisplayMano(Mesa mesa, Programa interfaz){

        this.mesa=mesa;
        mano=new HBox();
        mano.setSpacing(20);

        setPadding(new Insets(5,5,5,5));

        this.setAlignment(Pos.BOTTOM_CENTER);
        mano.setAlignment(Pos.BOTTOM_CENTER);

        for(Carta carta: this.mesa.getJugadorActivo().getMano())
           mano.getChildren().add(new CartaGrafica(carta,interfaz.getDisplayMesa()));

        this.getChildren().addAll(mano);
    }

}
