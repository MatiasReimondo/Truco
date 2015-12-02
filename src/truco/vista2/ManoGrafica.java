package truco.vista2;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import truco.modelo.Carta;
import truco.modelo.Mesa;

public class ManoGrafica extends HBox {

    private Mesa mesa;
    private MesaGrafica mesaGrafica;

    public ManoGrafica(Mesa mesa,MesaGrafica mesaGrafica){

        this.mesa=mesa;
        this.mesaGrafica=mesaGrafica;

        this.setSpacing(15);
        this.setAlignment(Pos.BOTTOM_CENTER);

        for(Carta carta: this.mesa.getJugadorActivo().getMano())
           this.getChildren().add(new CartaGrafica(carta,mesa,mesaGrafica));

    }

    public void renovar(){

        this.getChildren().clear();
        for(Carta carta: this.mesa.getJugadorActivo().getMano())
            this.getChildren().add(new CartaGrafica(carta,mesa,mesaGrafica));

    }


}
