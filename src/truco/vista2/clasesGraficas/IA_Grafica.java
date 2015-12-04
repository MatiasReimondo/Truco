package truco.vista2.clasesGraficas;

import truco.modelo.Truco;
import truco.modelo.envido.Envido;
import truco.modelo.envido.EnvidoNoCantado;
import truco.modelo.envido.EnvidoTerminado;
import truco.modelo.estadosTruco.RetrucoCantado;
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
        //CUANDO LA IA QUIERE EL ENVIDO
        if(truco.getMesa().getRonda().getEnvido().getClass().equals(Envido.class)){
            interfaz.getHistorial().jugadorQuisoEnvido(truco.getMesa().getJugadorIA(),new Envido());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraPostEnvido(truco.getMesa(),interfaz));
        }


        // CUANDO LA IA NO QUIERE EL ENVIDO
        if(truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoTerminado.class) && truco.getMesa().getRonda().getManoEnJuego().size()<=0 && truco.getMesa().getRonda().seEstaJugandoLaPrimera()) {
            interfaz.getHistorial().jugadorNoQuisoEnvido(truco.getMesa().getJugadorIA(), new Envido());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraPostEnvido(truco.getMesa(),interfaz));
        }

        // CUANDO LA IA SOLO QUIERE EL ENVIDO
        if(truco.getMesa().getRonda().getEnvido().getClass().equals(Envido.class) && truco.getMesa().getRonda().getEnvido().getEnvidoCantado()==null){
            interfaz.getHistorial().jugadorQuisoEnvido(truco.getMesa().getJugadorIA(), truco.getMesa().getRonda().getEnvido());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().add(new BotoneraPostEnvido(truco.getMesa(),interfaz));
        }

        // CUANDO LA IA QUIERE O SUBE LA APUESTA DEL ENVIDO
        if(!truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoNoCantado.class) && !truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoTerminado.class)){
            return;

        }
        // CUANDO LA IA QUIERE EL ENVIDO Y CANTA ENVIDO
        if(truco.getMesa().getRonda().getEnvido().getClass().equals(Envido.class) && truco.getMesa().getRonda().getEnvido().getEnvidoCantado()!=null && truco.getMesa().getRonda().getEnvido().getEnvidoCantado().getClass().equals(Envido.class))
        {
            interfaz.getHistorial().jugadorCantoEnvido(truco.getMesa().getJugadorIA(),truco.getMesa().getRonda().getEnvido().getEnvidoCantado());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(truco.getMesa(),interfaz));
        }
        // CUANDO LA IA CANTA ALGUN ENVIDO SIN QUE HAYA OTRO CANTADO PREVIAMENTE
        if(truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoNoCantado.class) && truco.getMesa().getRonda().getEnvido().getEnvidoCantado()!=null){
            interfaz.getHistorial().jugadorCantoEnvido(truco.getMesa().getJugadorIA(),truco.getMesa().getRonda().getEnvido().getEnvidoCantado());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(truco.getMesa(),interfaz));
        }

        // CUANDO LA IA CANTA TRUCO
        if(truco.getMesa().getRonda().getTruco().getClass().equals(TrucoCantado.class)) {
            interfaz.getHistorial().jugadorCantoTruco(truco.getMesa().getJugadorIA());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(truco.getMesa(),interfaz));
        }

        // CUANDO LA IA CANTA RETRUCO
        if(truco.getMesa().getRonda().getTruco().getClass().equals(RetrucoCantado.class)) {
            interfaz.getHistorial().jugadorCantoRetruco(truco.getMesa().getJugadorIA());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(truco.getMesa(),interfaz));
        }

        // CUANDO LA IA CANTA VALE CUATRO
         if(truco.getMesa().getRonda().getTruco().getClass().equals(ValeCuatroCantado.class)) {
             interfaz.getHistorial().jugadorCantoValeCuatro(truco.getMesa().getJugadorIA());
             interfaz.getPanelIzquierdo().getChildren().clear();
             interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(truco.getMesa(),interfaz));
         }
        }

}
