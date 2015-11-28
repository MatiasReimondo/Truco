package truco.vista.controladores;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/*********************************************************************************
 **********************  TextoNuevoJuegoEventHandler  ***************************
 *********************************************************************************/
public class TextoNuevoJuegoEventHandler implements EventHandler<KeyEvent> {

    //Atributo de la clase
    private Button botonNuevoJuego;

    /******** Metodos de la clase **********/
    public TextoNuevoJuegoEventHandler(Button botonNuevoJuego) {
        this.botonNuevoJuego = botonNuevoJuego;
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            Event.fireEvent(this.botonNuevoJuego, new ActionEvent());
        }
    }
}
