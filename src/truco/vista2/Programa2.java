package truco.vista2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import truco.modelo.Truco;

public class Programa2 extends Application {

    private Truco truco;
    private MesaGrafica mesaGrafica;
    private ManoGrafica manoGrafica;

    public static void main(String[] args) {
        launch(args);
    }

    private void setup2Jugadores(){
        truco=new Truco();
        truco.nuevoEquipo("E1");
        truco.nuevoEquipo("E2");
        truco.nuevoJugador("J1","E1");
        truco.nuevoJugador("J2","E2");
        truco.empezarJuego();
        mesaGrafica=new MesaGrafica(truco.getMesa(),this);
        mesaGrafica.linkearJugadores(truco.getMesa().getJugadores());
        truco.getMesa().nuevaRonda();
        truco.getMesa().repartirCartas();
        manoGrafica=new ManoGrafica(truco.getMesa(),mesaGrafica);
    }


    private Parent armarDisplay(){

        this.setup2Jugadores();


        //BASE Y FONDO
        Pane base=new Pane();
        base.setPrefSize(800,600);

        Region fondo=new Region();
        fondo.setPrefSize(800,600);
        fondo.setStyle("-fx-background-color: rgba(0, 0, 0, 1)");

        HBox baseLayout=new HBox(5);
        baseLayout.setPadding(new Insets(5,5,5,5));

        //ZONA IZQUIERDA
        StackPane stackIzquierdo=new StackPane(new Botonera());
        stackIzquierdo.setAlignment(Pos.CENTER_LEFT);

        //ZONA CENTRO
        StackPane stackCentral=new StackPane();
        stackCentral.setAlignment(Pos.CENTER);
        stackCentral.getChildren().addAll(this.mesaGrafica,new ManoGrafica(truco.getMesa(),this.mesaGrafica));

        //ZONA MANO DEL JUGADOR ACTIVO
        baseLayout.getChildren().addAll(stackIzquierdo,stackCentral);
        base.getChildren().addAll(fondo, baseLayout);

        return base;
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene=new Scene(this.armarDisplay());
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);
        primaryStage.setTitle("TRUCO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public ManoGrafica getManoGrafica() {
        return manoGrafica;
    }
}
