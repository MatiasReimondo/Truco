package truco.vista.controladores.configuracion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import truco.modelo.Equipo;
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


        for(int i=0;i<this.contenedorPrincipal.getChildren().size();i++){
            CheckBox checkBoxAuxiliar=(CheckBox)this.contenedorPrincipal.getChildren().get(i);
            if(checkBoxAuxiliar.isSelected()) {
                Equipo equipoAI= this.juego.getJugadores().get(i).getEquipo();
                this.juego.getJugadores().remove(this.juego.getJugador(((CheckBox) this.contenedorPrincipal.getChildren().get(i)).getText()));
                this.juego.jugadorVsIA();
                this.juego.getJugadores().get(i).setEquipo(equipoAI);


            }
        }

    }

}