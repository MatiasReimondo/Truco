package truco.vista.controladores;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/*********************************************************************************
 **********************  TextoJuegoEventHandler  ***************************
 *********************************************************************************/
public class TextoJuegoEventHandler implements EventHandler<KeyEvent> {

    //Atributo de la clase
    private Button botonJuego;

    /******** Metodos de la clase **********/
    public TextoJuegoEventHandler(Button botonJuego) {
        this.botonJuego = botonJuego;
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            Event.fireEvent(this.botonJuego, new ActionEvent());
        }
    }
}
