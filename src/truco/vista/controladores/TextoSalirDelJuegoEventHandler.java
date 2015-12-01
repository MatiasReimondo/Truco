package truco.vista.controladores;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/*********************************************************************************
 *****************  TextoSalirDelJuegoEventHandler  *****************************
 *********************************************************************************/
public class TextoSalirDelJuegoEventHandler implements EventHandler<KeyEvent> {
    //Atributo de la clase
    private Button botonSalir;

    /******** Metodos de la clase **********/

    public TextoSalirDelJuegoEventHandler(Button botonSalir) {
        this.botonSalir = botonSalir;
        }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
        Event.fireEvent(this.botonSalir, new ActionEvent());
        }
    }
}
