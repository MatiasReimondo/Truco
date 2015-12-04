package truco.vista.controladores.excepcionesVista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by shaun on 03/12/2015.
 */
public class VentanaException {

    public static void display(String titulo, String mensaje){
        Stage ventana=new Stage();

        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle(titulo);
        ventana.setMinWidth(500);
        Label etiqueta= new Label();
        etiqueta.setText(mensaje);

        VBox contenedorVentana = new VBox(10);
        contenedorVentana.getChildren().addAll(etiqueta);
        contenedorVentana.setAlignment(Pos.CENTER);

        Scene scene= new Scene(contenedorVentana);
        ventana.setScene(scene);
        ventana.showAndWait();

    }
}
