package truco.vista.controladores;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/*********************************************************************************
 *****************  TextoCartaElegidaEventHandler  *******************************
 *********************************************************************************/
public class TextoCartaElegidaEventHandler implements EventHandler<KeyEvent> {
    //Atributo de la clase
    private Button botonCartaElegida;
    private VBox contenedor;

    /******** Metodos de la clase **********/

    public TextoCartaElegidaEventHandler(Button botonCartaElegida, VBox contenedor) {
        this.botonCartaElegida = botonCartaElegida;
        this.contenedor =  contenedor;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Event.fireEvent(this.botonCartaElegida, new ActionEvent());
        }

    }
}


