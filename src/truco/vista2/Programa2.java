package truco.vista2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import truco.modelo.Truco;
import truco.vista2.botoneras.BotoneraInicial;

public class Programa2 extends Application {

    private Truco truco;
    private MesaGrafica mesaGrafica;
    private StackPane stackCentral;
    private StackPane stackIzquierdo;
    private StackPane stackDerecho;

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
    }
    public void actualizarPuntaje() {
        stackDerecho.getChildren().clear();
        stackDerecho.getChildren().addAll(new PuntajesGrafico(truco,this));
    }

    public void actualizarManoGrafica() {
        stackCentral.getChildren().addAll(new ManoGrafica(truco.getMesa(),mesaGrafica));
    }

    public void actualizarNuevaRonda(){
        truco.getMesa().nuevaRonda();
        truco.getMesa().repartirCartas();
        stackIzquierdo.getChildren().clear();
        stackIzquierdo.getChildren().addAll(new BotoneraInicial(truco.getMesa(),this));
        stackCentral.getChildren().clear();
        mesaGrafica=new MesaGrafica(truco.getMesa(),this);
        stackCentral.getChildren().addAll(mesaGrafica);
        actualizarManoGrafica();
        this.actualizarPuntaje();
    }
    public StackPane getStackCentral(){
        return stackCentral;
    }

    public StackPane getStackIzquierdo(){
        return stackIzquierdo;
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
        stackIzquierdo=new StackPane();
        stackIzquierdo.getChildren().addAll(new BotoneraInicial(truco.getMesa(),this));
        stackIzquierdo.setAlignment(Pos.BOTTOM_LEFT);

        //ZONA CENTRO
        stackCentral=new StackPane();
        stackCentral.setAlignment(Pos.CENTER);
        stackCentral.getChildren().addAll(this.mesaGrafica,new ManoGrafica(truco.getMesa(),this.mesaGrafica));

        //ZONA DERECHA
        stackDerecho=new StackPane();
        stackDerecho.getChildren().addAll(new PuntajesGrafico(truco,this));
        stackDerecho.setAlignment(Pos.TOP_RIGHT);

        //ZONA MANO DEL JUGADOR ACTIVO
        baseLayout.getChildren().addAll(stackIzquierdo,stackCentral,stackDerecho);
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



}
