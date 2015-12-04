package truco.vista2.clasesGraficas;

import javafx.scene.control.TextArea;
import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Truco;
import truco.modelo.envido.Envido;
import truco.modelo.estadosTruco.EstadoTruco;
import truco.modelo.estadosTruco.RetrucoCantado;
import truco.modelo.estadosTruco.TrucoCantado;
import truco.modelo.estadosTruco.ValeCuatroCantado;

public class Historial extends TextArea {

    private int contadorAccion =1;
    private int contadorRonda=1;

    public Historial(){

        this.setPrefSize(200,200);
        this.appendText("- Ronda "+contadorRonda+ "\n");
    }

    public void jugadorJugoCarta(Jugador jugador, Carta carta){
        this.appendText(contadorAccion +". "+jugador.getNombre()+"  jugo  "+carta.getNumero()+" de "+carta.getPalo()+".\n");
        contadorAccion++;
    }

    public void jugadorCantoEnvido(Jugador jugador,Envido envido){
        this.appendText(contadorAccion +". "+jugador.getNombre()+"  canto "+envido.getClass().getSimpleName()+".\n");
        contadorAccion++;
    }

    public void reset(){
        contadorAccion =1;
        contadorRonda++;
        this.appendText("- Ronda "+contadorRonda+ "\n");
    }

    public void jugadorCantoTruco(Jugador jugador) {
        this.appendText(contadorAccion +". "+jugador.getNombre()+" canto Truco.\n");
        contadorAccion++;
    }

    public void jugadorCantoRetruco(Jugador jugador) {
        this.appendText(contadorAccion +". "+jugador.getNombre()+" canto Retruco.\n");
        contadorAccion++;
    }

    public void jugadorCantoValeCuatro(Jugador jugador) {
        this.appendText(contadorAccion +". "+jugador.getNombre()+" canto Vale Cuatro.\n");
        contadorAccion++;
    }

    public void jugadorSeFueAlMazo(Jugador jugador) {
        this.appendText(contadorAccion +". "+jugador.getNombre()+" se fue al mazo.\n");
        contadorAccion++;
    }

    public void jugadorQuisoTruco(Jugador jugador,EstadoTruco truco){

        if(truco.getClass().equals(TrucoCantado.class))
            this.appendText(contadorAccion+". "+jugador.getNombre()+" quiso el Truco.\n");
        if(truco.getClass().equals(RetrucoCantado.class))
            this.appendText(contadorAccion+". "+jugador.getNombre()+" quiso el Retruco.\n");
        if(truco.getClass().equals(ValeCuatroCantado.class))
            this.appendText(contadorAccion+". "+jugador.getNombre()+" quiso el Vale Cuatro.\n");
        contadorAccion++;
    }

    public void jugadorQuisoEnvido(Jugador jugador,Envido envido){
        this.appendText(contadorAccion+". "+jugador.getNombre()+" quiso el "+envido.getClass().getSimpleName()+".\n");
        contadorAccion++;
    }

    public void jugadorNoQuisoEnvido(Jugador jugador,Envido envido) {
        this.appendText(contadorAccion+". "+jugador.getNombre()+" no quiso el "+envido.getClass().getSimpleName()+".\n");
        contadorAccion++;
    }

    public void jugadorNoQuisoTruco(Jugador jugador, EstadoTruco truco) {
        this.appendText(contadorAccion+". "+jugador.getNombre()+" no quiso el "+truco.getClass().getSimpleName()+".\n");
        contadorAccion++;
    }

    public void envidoResuelto() {
        this.appendText(contadorAccion+". Se resolvio el tanto en juego.\n");
        contadorAccion++;
    }

    public void jugadorCantoFlor(Jugador jugador) {
        this.appendText(contadorAccion+". "+jugador.getNombre()+" canto Flor.\n");
        contadorAccion++;
    }
}
