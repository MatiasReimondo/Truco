package truco.vista.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

/*********************************************************************************
 *************************** BotonSalirEventHandler ******************************
 *********************************************************************************/
public class BotonSalirEventHandler implements EventHandler<ActionEvent> {

    private VBox contenedorVertical;

    public BotonSalirEventHandler( VBox contenedorVertical){
        this.contenedorVertical = contenedorVertical;
    }

    @Override
    public void handle(ActionEvent event) {
        this.contenedorVertical.getChildren().clear();
    }
}
