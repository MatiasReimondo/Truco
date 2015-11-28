package truco.vista.controladores;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

/*********************************************************************************
 ************************ BotonInformacionEventHandler ********************************
 *********************************************************************************/
public class BotonInformacionEventHandler implements EventHandler<ActionEvent> {

    private VBox contenedorVertical;

    /******* Metodos de la clase ********/
    public BotonInformacionEventHandler (VBox contenedor){
        this.contenedorVertical = contenedor;
    }

    @Override
    public void handle(ActionEvent event) {
        mostrarEtiquetasDeInformacion();
    }

    /****** Metodos privado **********/

    private void mostrarEtiquetasDeInformacion() {
        //Se limpia el contenedor
        this.contenedorVertical.getChildren().clear();

        //creacion de las etiquetas que contiene la informacion del grupo
        this.contenedorVertical.getChildren().add( new Label("  7507.- Algoritmos y programacion 3 : Tp final 'Truco'") );
        this.contenedorVertical.getChildren().add( new Label("          GRUPO NRO 2 "));
        this.contenedorVertical.getChildren().add( new Label("  Integrantes:            Padron nro:"));
        this.contenedorVertical.getChildren().add( new Label("  Ezequiel Cruz Avila     "));
        this.contenedorVertical.getChildren().add( new Label("  Matias Reimondo         "));
        this.contenedorVertical.getChildren().add( new Label("  Jessica Aguila Visitacion  "));
    }
}
