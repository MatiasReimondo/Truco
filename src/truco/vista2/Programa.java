package truco.vista2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import truco.modelo.Truco;
import truco.modelo.excepciones.ListaJugadoresVaciaException;
import truco.vista2.botoneras.BotoneraInicial;
import truco.vista2.clasesGraficas.DisplayMesa;
import truco.vista2.clasesGraficas.DisplayTurnoJugador;
import truco.vista2.clasesGraficas.DisplayMano;
import truco.vista2.clasesGraficas.DisplayPuntaje;

public class Programa extends Application {

    private Truco truco;
    private DisplayMesa displayMesa;

    private VBox boxCentral=new VBox();
    private StackPane panelCentral=new StackPane();
    private StackPane panelIzquierdo=new StackPane();
    private StackPane panelDerecho =new StackPane();

    public static void main(String[] args) {
        launch(args);
    }

    public void actualizarPuntajeGrafico() {
        panelDerecho.getChildren().clear();
        panelDerecho.getChildren().addAll(new DisplayPuntaje(truco,this));
    }

    public void actualizarManoGrafica() {
        boxCentral.getChildren().remove(1);
        boxCentral.getChildren().add(new DisplayMano(truco.getMesa(),this));
    }

    public void nuevaRondaGrafica(){
        truco.getMesa().nuevaRonda();
        truco.getMesa().repartirCartas();
/*
        panelIzquierdo.getChildren().clear();
        panelIzquierdo.getChildren().addAll(new BotoneraInicial(truco.getMesa(),this));
*/
        reloadPanelIzquierdo();
        reloadPanelCentral();
        reloadPanelDerecho();

        actualizarPuntajeGrafico();
    }

    /*** GETTERS ***/
    public StackPane getPanelIzquierdo(){
        return panelIzquierdo;
    }

    public StackPane getPanelDerecho(){
        return panelDerecho;
    }

    public DisplayMesa getDisplayMesa() {
        return displayMesa;
    }
    /*** RELOAD SCREEN ***/
    public void reloadPanelCentral(){
        boxCentral.getChildren().clear();
        displayMesa =new DisplayMesa(truco.getMesa(),this);
        DisplayMano displayMano =new DisplayMano(truco.getMesa(),this);

        panelCentral.getChildren().clear();
        boxCentral.getChildren().addAll(displayMesa, displayMano);
        panelCentral.getChildren().addAll(boxCentral);

    }

    public void reloadPanelDerecho(){
        panelDerecho.getChildren().clear();
        StackPane pane=new StackPane();
        pane.getChildren().addAll(new DisplayTurnoJugador(truco.getMesa(),this));
        pane.setAlignment(Pos.BOTTOM_CENTER);
        panelDerecho.getChildren().addAll(new DisplayPuntaje(truco,this),pane);
    }

    public void reloadPanelIzquierdo(){
        panelIzquierdo.getChildren().clear();
        panelIzquierdo.getChildren().addAll(new BotoneraInicial(truco.getMesa(),this));
        panelIzquierdo.setAlignment(Pos.BOTTOM_LEFT);
    }

    private void displayConfiguracion(){
        Stage stage=new Stage();
        stage.setTitle("TRUCO : Configuracion");
        HBox triBox=new HBox();
        triBox.setPrefSize(350,100);

        //SE JUEGA O NO CON FLOR
        StackPane panelIzquierdo=new StackPane();
        Label textoFlor=new Label("¿ Jugar con Flor ?");
        HBox botonera=new HBox();
        botonera.setPadding(new Insets(5,5,5,5));
        javafx.scene.control.Button botonSI= new javafx.scene.control.Button("SI");
        javafx.scene.control.Button botonNO=new javafx.scene.control.Button("NO");
        botonera.setSpacing(20);
        botonera.getChildren().addAll(botonSI,botonNO);
        VBox vBox=new VBox();
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(textoFlor,botonera);
        vBox.setAlignment(Pos.CENTER);
        panelIzquierdo.getChildren().addAll(vBox);

        //CANTIDAD DE JUGADORES
        StackPane panelCentral=new StackPane();
        Label textoJugadores=new Label("Modo de Juego");
        textoJugadores.setAlignment(Pos.CENTER);
        HBox botonera1=new HBox();
        HBox botonera2=new HBox();
        botonera2.setPadding(new Insets(5,5,5,5));
        botonera1.setPadding(new Insets(5,5,5,5));
        javafx.scene.control.Button botonVsIA= new javafx.scene.control.Button("1 vs IA");
        javafx.scene.control.Button boton1v1=new javafx.scene.control.Button("1 vs 1");
        javafx.scene.control.Button boton2v2= new javafx.scene.control.Button("2 vs 2");
        javafx.scene.control.Button boton3vs3=new javafx.scene.control.Button("3 vs 3");
        botonera2.setSpacing(10);
        botonera1.setSpacing(10);
        botonera2.getChildren().addAll(botonVsIA,boton1v1);
        botonera1.getChildren().addAll(boton2v2,boton3vs3);
        VBox box=new VBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(textoJugadores,botonera2,botonera1);
        panelCentral.getChildren().addAll(box);

        //COMENZAR
        StackPane panelDerecho=new StackPane();
        javafx.scene.control.Button botonComenzar=new javafx.scene.control.Button(" COMENZAR\n  PARTIDA");
        botonComenzar.setAlignment(Pos.CENTER_RIGHT);
        panelDerecho.setAlignment(Pos.CENTER_RIGHT);
        panelDerecho.setPadding(new Insets(10,10,10,10));
        panelDerecho.getChildren().addAll(botonComenzar);

        triBox.getChildren().addAll(panelIzquierdo,panelCentral,panelDerecho);
        Scene scene=new Scene(triBox);
        stage.setScene(scene);

        botonSI.setOnAction(e->truco.seJuegaConFlor());
        botonNO.setOnAction(e->truco.seJuegaSinFlor());
        boton1v1.setOnAction(e->truco.nuevoJuego2Jugadores());
        boton2v2.setOnAction(e->truco.nuevoJuego4Jugadores());
        boton3vs3.setOnAction(e->truco.nuevoJuego6Jugadores());
        botonVsIA.setOnAction(e->truco.jugadorVsIA());

        setBotonComenzar(botonComenzar, stage);


        stage.show();
    }

    private void displayMainwindow(){

        Stage stage=new Stage();
        stage.setHeight(500);
        stage.setWidth(800);
        stage.setResizable(false);
        stage.setTitle("TRUCO");

        truco.empezarJuego();
        truco.getMesa().nuevaRonda();
        truco.getMesa().repartirCartas();
        //displayMesa=new DisplayMesa(truco.getMesa(),this);


        //BASE Y FONDO
        Pane base=new Pane();
        base.setPrefSize(800,500);

        Region fondo=new Region();
        fondo.setPrefSize(800,500);
        fondo.setStyle("-fx-background-color: rgba(0, 0, 0, 1)");

        HBox baseLayout=new HBox(5);
        baseLayout.setPadding(new Insets(5,5,5,5));

        //ZONA IZQUIERDA
        reloadPanelIzquierdo();

        //ZONA CENTRO
        reloadPanelCentral();

        //ZONA DERECHA
        panelDerecho.getChildren().addAll(new DisplayPuntaje(truco,this));
        panelDerecho.setAlignment(Pos.TOP_CENTER);
        reloadPanelDerecho();

        //ZONA MANO DEL JUGADOR ACTIVO
        baseLayout.getChildren().addAll(panelIzquierdo,panelCentral, panelDerecho);
        base.getChildren().addAll(fondo, baseLayout);

        stage.setScene(new Scene(base));
        stage.show();
    }

    private void setBotonComenzar(Button boton,Stage stage){
        boton.setOnAction(e -> {
            try { displayMainwindow();  }
            catch (ListaJugadoresVaciaException b) {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText("Debe elegir alguna modalidad de juego antes de comenzar");
                displayConfiguracion();
                alert.show();
                stage.close();
            }stage.close();});
    }

    @Override
    public void start(Stage primaryStage) {
        truco=new Truco();
        displayConfiguracion();

    }


}
