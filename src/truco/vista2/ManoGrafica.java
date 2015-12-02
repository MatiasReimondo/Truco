package truco.vista2;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import truco.modelo.Carta;
import truco.modelo.Mesa;

public class ManoGrafica extends StackPane {

    private Mesa mesa;
    private MesaGrafica mesaGrafica;
    private HBox mano;

    public ManoGrafica(Mesa mesa,MesaGrafica mesaGrafica){

        this.mesa=mesa;
        this.mesaGrafica=mesaGrafica;
        mano=new HBox();
        mano.setSpacing(20);

        this.setAlignment(Pos.BOTTOM_CENTER);
        mano.setAlignment(Pos.BOTTOM_CENTER);

        for(Carta carta: this.mesa.getJugadorActivo().getMano())
           mano.getChildren().add(new CartaGrafica(carta,mesa,mesaGrafica));

        this.getChildren().addAll(mano);
    }

}
