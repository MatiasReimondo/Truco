package truco.vista2.clasesGraficas;

import truco.modelo.Truco;
import truco.modelo.envido.EnvidoTerminado;
import truco.modelo.estadosTruco.RetrucoCantado;
import truco.modelo.estadosTruco.RetrucoQuerido;
import truco.modelo.estadosTruco.TrucoCantado;
import truco.modelo.estadosTruco.ValeCuatroCantado;
import truco.vista2.Programa;
import truco.vista2.botoneras.BotoneraPostEnvido;
import truco.vista2.botoneras.BotoneraRespuestaEnvido;

public class IA_Grafica {

    private Truco truco;
    private Programa interfaz;

    public IA_Grafica(Truco truco,Programa interfaz){
        this.truco=truco;
        this.interfaz=interfaz;
    }

    private void mostrarIAJugoCarta(){

        interfaz.getDisplayMesa().encontrarSlot(truco.getMesa().getJugadorIA()).getChildren().clear();
        interfaz.getDisplayMesa().encontrarSlot(truco.getMesa().getJugadorIA()).getChildren().add(new CartaGrafica(truco.getMesa().getRonda().getManoEnJuego().get(truco.getMesa().getJugadorIA()),interfaz.getDisplayMesa()));
        interfaz.getHistorial().jugadorJugoCarta(truco.getMesa().getJugadorIA(),truco.getMesa().getRonda().getManoEnJuego().get(truco.getMesa().getJugadorIA()));
    }

    private void mostrarIACantoEnvido(){
        interfaz.getHistorial().jugadorCantoEnvido(truco.getMesa().getJugadorIA(),truco.getMesa().getRonda().getEnvido().getEnvidoCantado());
        interfaz.getPanelIzquierdo().getChildren().clear();
        interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(truco.getMesa(),interfaz));
    }

    public void mostrarAccionDeLaIA(){

        if(truco.getMesa().getRonda().getManoEnJuego().containsKey(truco.getMesa().getJugadorIA())) {
            mostrarIAJugoCarta();
            return;
        }
        if(truco.getMesa().getRonda().getEnvido().getEnvidoCantado()!=null && !truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoTerminado.class)){
            mostrarIACantoEnvido();
            return;
        }
        if(truco.getMesa().getRonda().getTruco().getClass().equals(TrucoCantado.class))
            interfaz.getHistorial().jugadorCantoTruco(truco.getMesa().getJugadorIA());

        if(truco.getMesa().getRonda().getTruco().getClass().equals(RetrucoCantado.class))
            interfaz.getHistorial().jugadorCantoRetruco(truco.getMesa().getJugadorIA());

         if(truco.getMesa().getRonda().getTruco().getClass().equals(ValeCuatroCantado.class))
            interfaz.getHistorial().jugadorCantoValeCuatro(truco.getMesa().getJugadorIA());
        }

}
