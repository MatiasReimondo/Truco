package truco.vista.controladores;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/*********************************************************************************
 **********************  TextoInformacionEventHandler  ***************************
 *********************************************************************************/
public class TextoInformacionEventHandler implements EventHandler<KeyEvent> {

    //Atributo de la clase
    private Button botonInformacion;

    /******** Metodos de la clase **********/
    public TextoInformacionEventHandler(Button botonInformacion) {
        this.botonInformacion = botonInformacion;
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            Event.fireEvent(this.botonInformacion, new ActionEvent());
        }
    }
}
