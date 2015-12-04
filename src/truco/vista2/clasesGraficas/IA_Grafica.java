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
import truco.vista2.botoneras.BotoneraRespuestaTruco;

public class IA_Grafica {

    private Truco truco;
    private Programa interfaz;

    public IA_Grafica(Truco truco,Programa interfaz){
        this.truco=truco;
        this.interfaz=interfaz;
    }

    public void comportamientoIA(){
        if(truco.getMesa().IA_Activada()) {
            if (truco.getMesa().getRonda().getManoEnJuego().size() == truco.getMesa().getNroJugadores())
                truco.getMesa().resolverMano();
            while (esTurnoDeLaIA() && !truco.getMesa().getRonda().termino()) {
                truco.getMesa().getIA().accionar();
                interfaz.getControlIA().mostrarAccionDeLaIA();
                if (truco.getMesa().getRonda().getManoEnJuego().size() == truco.getMesa().getNroJugadores())
                    truco.getMesa().resolverMano();
            }
        }
    }

    private boolean esTurnoDeLaIA(){
        return truco.getMesa().getJugadorActivo().equals(truco.getMesa().getJugadorIA());
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
        }

        if(truco.getMesa().getRonda().getEnvido().getEnvidoCantado()!=null && !truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoTerminado.class)){
            mostrarIACantoEnvido();
        }

        if(truco.getMesa().getRonda().getTruco().getClass().equals(TrucoCantado.class)) {
            interfaz.getHistorial().jugadorCantoTruco(truco.getMesa().getJugadorIA());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(truco.getMesa(),interfaz));
        }

        if(truco.getMesa().getRonda().getTruco().getClass().equals(RetrucoCantado.class)) {
            interfaz.getHistorial().jugadorCantoRetruco(truco.getMesa().getJugadorIA());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(truco.getMesa(),interfaz));
        }

         if(truco.getMesa().getRonda().getTruco().getClass().equals(ValeCuatroCantado.class)) {
             interfaz.getHistorial().jugadorCantoValeCuatro(truco.getMesa().getJugadorIA());
             interfaz.getPanelIzquierdo().getChildren().clear();
             interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(truco.getMesa(),interfaz));
         }
        }

}
