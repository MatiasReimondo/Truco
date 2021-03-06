package truco.vistaFinal.clasesGraficas;

import truco.modelo.Truco;
import truco.modelo.envido.*;
import truco.modelo.estadosTruco.*;
import truco.vistaFinal.Programa;
import truco.vistaFinal.botoneras.BotoneraPostEnvido;
import truco.vistaFinal.botoneras.BotoneraRespuestaEnvido;
import truco.vistaFinal.botoneras.BotoneraRespuestaTruco;
import truco.vistaFinal.botoneras.BotoneraTrucoQuerido;

public class IA_Grafica {

    private Truco truco;
    private Programa interfaz;

    public IA_Grafica(Truco truco,Programa interfaz){
        this.truco=truco;
        this.interfaz=interfaz;
    }

    public void accionarGrafico(){
        if(truco.getMesa().IA_Activada() && esTurnoDeLaIA()) {

            while (esTurnoDeLaIA()) {

                truco.getMesa().getIA().accionar();
                interfaz.getControlIA().mostrarAcciones();

                if (truco.getMesa().getRonda().getManoEnJuego().size() == truco.getMesa().getNroJugadores())
                    truco.getMesa().resolverMano();

                if(truco.getMesa().getRonda().termino()) {
                    interfaz.finalDeRonda();
                    break;
                }
            }
        }
    }

    private boolean esTurnoDeLaIA(){
        return truco.getMesa().getJugadorActivo().equals(truco.getMesa().getJugadorIA());
    }

    public void mostrarAcciones(){
        comportamientoJugarCartaGrafico();
        comportamientoEnvidoGrafico();
        comportamientoTrucoGrafico();
    }

    private void comportamientoTrucoGrafico(){

        // CUANDO LA IA NO QUIERE EL TRUCO, RETRUCO, O VALE CUATRO
        if((truco.getMesa().getRonda().getTruco().getClass().equals(TrucoCantado.class) || truco.getMesa().getRonda().getTruco().getClass().equals(RetrucoCantado.class) || truco.getMesa().getRonda().getTruco().getClass().equals(ValeCuatroCantado.class))&& truco.getMesa().getRonda().termino()) {
            interfaz.getHistorial().jugadorNoQuisoTruco(truco.getMesa().getJugadorIA(), truco.getMesa().getRonda().getTruco());
            interfaz.nuevaRondaGrafica();
            return;
        }

        // CUANDO LA IA QUIERE EL TRUCO
        if(truco.getMesa().getRonda().getTruco().getClass().equals(TrucoQuerido.class) && truco.getMesa().getJugadorIA().tieneElQuiero()){
            interfaz.getHistorial().jugadorQuisoTruco(truco.getMesa().getJugadorIA(),truco.getMesa().getRonda().getTruco());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().add(new BotoneraTrucoQuerido(truco.getMesa(),interfaz));
            System.out.print(2);
            return;
        }

        // CUANDO LA IA QUIERE EL RETRUCO
        if(truco.getMesa().getRonda().getTruco().getClass().equals(RetrucoQuerido.class) && truco.getMesa().getJugadorIA().tieneElQuiero()){
            interfaz.getHistorial().jugadorQuisoTruco(truco.getMesa().getJugadorIA(),truco.getMesa().getRonda().getTruco());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().add(new BotoneraTrucoQuerido(truco.getMesa(),interfaz));
            return;
        }

        // CUANDO LA IA QUIERE EL RETRUCO
        if(truco.getMesa().getRonda().getTruco().getClass().equals(ValeCuatroQuerido.class) && truco.getMesa().getJugadorIA().tieneElQuiero()){
            interfaz.getHistorial().jugadorQuisoTruco(truco.getMesa().getJugadorIA(),truco.getMesa().getRonda().getTruco());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().add(new BotoneraTrucoQuerido(truco.getMesa(),interfaz));
            return;
        }

        // CUANDO LA IA CANTA VALE CUATRO
        if(truco.getMesa().getRonda().getTruco().getClass().equals(ValeCuatroCantado.class)) {
            interfaz.getHistorial().jugadorCantoValeCuatro(truco.getMesa().getJugadorIA());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(truco.getMesa(),interfaz));
            return;
        }
        // CUANDO LA IA CANTA RETRUCO
        if(truco.getMesa().getRonda().getTruco().getClass().equals(RetrucoCantado.class)) {
            interfaz.getHistorial().jugadorCantoRetruco(truco.getMesa().getJugadorIA());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(truco.getMesa(),interfaz));
            return;
        }

        // CUANDO LA IA CANTA TRUCO
        if(truco.getMesa().getRonda().getTruco().getClass().equals(TrucoCantado.class)) {
            interfaz.getHistorial().jugadorCantoTruco(truco.getMesa().getJugadorIA());
            interfaz.getPanelIzquierdo().getChildren().clear();
            interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaTruco(truco.getMesa(),interfaz));
        }
    }

    private void comportamientoEnvidoGrafico(){
        if(truco.getMesa().getRonda().seEstaJugandoLaPrimera()) {
            // CUANDO LA IA NO QUIERE EL TANTO
            if (truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoTerminado.class) && truco.getMesa().getRonda().getManoEnJuego().size() <= 0 && truco.getMesa().getRonda().seEstaJugandoLaPrimera()) {
                interfaz.getHistorial().jugadorNoQuisoEnvido(truco.getMesa().getJugadorIA(), new Envido());
                interfaz.getPanelIzquierdo().getChildren().clear();
                interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraPostEnvido(truco.getMesa(), interfaz));
            }

            // CUANDO LA IA CANTA EL ENVIDO SIN TANTO PREVIO
            if(truco.getMesa().getRonda().getEnvido().getEnvidoCantado()!=null && !truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoTerminado.class) && truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoNoCantado.class)){
                IAcantoEnvido();
                return;
            }


            // CUANDO LA IA QUIERE O SUBE LA APUESTA DEL TANTO
            if (!truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoNoCantado.class) && !truco.getMesa().getRonda().getEnvido().getClass().equals(EnvidoTerminado.class)) {
                //  CUANDO LA IA ACEPTA EL ENVIDO
                if (truco.getMesa().getRonda().getEnvido().getClass().equals(Envido.class)) {
                    // CUANDO LA IA SUBE LA APUESTA DESPUES DEL ENVIDO
                    if (truco.getMesa().getRonda().getEnvido().getEnvidoCantado() != null) {
                        // CUANDO LA IA SUBE A ENVIDO ENVIDO
                        if (truco.getMesa().getRonda().getEnvido().getEnvidoCantado().getClass().equals(Envido.class))
                            showApuestaEnvidoAumentada();
                        // CUANDO LA IA SUBE A ENVIDO REAL ENVIDO
                        if (truco.getMesa().getRonda().getEnvido().getEnvidoCantado().getClass().equals(RealEnvido.class))
                            showApuestaEnvidoAumentada();
                        // CUANDO LA IA SUBE A ENVIDO FALTA ENVIDO
                        if (truco.getMesa().getRonda().getEnvido().getEnvidoCantado().getClass().equals(FaltaEnvido.class))
                            showApuestaEnvidoAumentada();
                    }
                    // CUANDO LA IA QUIERE SOLAMENTE EL ENVIDO
                    else
                        showEnvidoQuerido();
                }
                // CUANDO LA IA ACEPTA EL REAL ENVIDO
                if (truco.getMesa().getRonda().getEnvido().getClass().equals(RealEnvido.class))
                    //CUANDO LA IA SUBE LA APUESTA DEL REAL ENVIDO
                    if (truco.getMesa().getRonda().getEnvido().getEnvidoCantado() != null) {
                        //CUANDO LA IA SUBE A REAL ENVIDO FALTA ENVIDO
                        if (truco.getMesa().getRonda().getEnvido().getEnvidoCantado().getClass().equals(FaltaEnvido.class))
                            showApuestaEnvidoAumentada();
                    }
                    // CUANDO LA IA QUIERE SOLAMENTE EL REAL ENVIDO
                    else
                        showEnvidoQuerido();
                // CUANDO LA IA ACEPTA EL FALTA ENVIDO
                if (truco.getMesa().getRonda().getEnvido().getClass().equals(FaltaEnvido.class))
                    showEnvidoQuerido();
            }
        }
    }

    private void IAcantoEnvido() {
        interfaz.getHistorial().jugadorCantoEnvido(truco.getMesa().getJugadorIA(),truco.getMesa().getRonda().getEnvido().getEnvidoCantado());
        interfaz.getPanelIzquierdo().getChildren().clear();
        interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(truco.getMesa(),interfaz));
    }

    private void comportamientoJugarCartaGrafico(){

        if(truco.getMesa().getRonda().getManoEnJuego().containsKey(truco.getMesa().getJugadorIA())) {
            interfaz.getDisplayMesa().encontrarSlot(truco.getMesa().getJugadorIA()).getChildren().clear();
            interfaz.getDisplayMesa().encontrarSlot(truco.getMesa().getJugadorIA()).getChildren().add(new CartaGrafica(truco.getMesa().getRonda().getManoEnJuego().get(truco.getMesa().getJugadorIA()), interfaz.getDisplayMesa()));
            interfaz.getHistorial().jugadorJugoCarta(truco.getMesa().getJugadorIA(), truco.getMesa().getRonda().getManoEnJuego().get(truco.getMesa().getJugadorIA()));
        }
        if(!truco.getMesa().getRonda().termino()){
            interfaz.actualizarManoGrafica();
            interfaz.reload_PanelDerecho();
        }
    }

    private void showApuestaEnvidoAumentada(){
        interfaz.getHistorial().jugadorCantoEnvido(truco.getMesa().getJugadorIA(),truco.getMesa().getRonda().getEnvido().getEnvidoCantado());
        interfaz.getPanelIzquierdo().getChildren().clear();
        interfaz.getPanelIzquierdo().getChildren().addAll(new BotoneraRespuestaEnvido(truco.getMesa(),interfaz));
    }

    private void showEnvidoQuerido(){
        interfaz.getHistorial().jugadorQuisoEnvido(truco.getMesa().getJugadorIA(), truco.getMesa().getRonda().getEnvido());
        interfaz.getPanelIzquierdo().getChildren().clear();
        interfaz.getPanelIzquierdo().getChildren().add(new BotoneraPostEnvido(truco.getMesa(),interfaz));

    }

}
