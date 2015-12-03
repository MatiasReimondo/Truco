package truco.vista.controladores.configuracion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import truco.modelo.Truco;


public class SetearAIEventHandler implements EventHandler<ActionEvent> {

    private Truco juego;
    private VBox contenedorPrincipal;


    public SetearAIEventHandler(VBox contenedorPrincipal, Truco juego) {
        this.contenedorPrincipal = contenedorPrincipal;
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent event) {

        //this.juego.getMesa().getIA().accionar();

        for(int i=0;i<this.contenedorPrincipal.getChildren().size();i++){
            CheckBox checkBoxAuxiliar=(CheckBox)this.contenedorPrincipal.getChildren().get(i);
            if(checkBoxAuxiliar.isSelected()) {
                Label etiquetaloca= new Label("ANDA");
               // if(this.juego.getJugador())
                this.juego.getMesa().setJugadorIA(this.juego.getJugador(((CheckBox) this.contenedorPrincipal.getChildren().get(i)).getText()));
                this.contenedorPrincipal.getChildren().add(etiquetaloca);
            }
        }



    }

}
