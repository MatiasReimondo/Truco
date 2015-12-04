package truco.modelo.IA;

import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.TrucoCantado;
import truco.modelo.estadosTruco.TrucoNoCantado;


public class PostEnvido implements Comportamiento {

    private Mesa mesa;
    private Jugador robot;

    @Override
    public Comportamiento reiniciar(){
        PreEnvido IAEstadoInicial =new PreEnvido();
        IAEstadoInicial.setJugador(robot);
        IAEstadoInicial.setMesa(mesa);
        return IAEstadoInicial;
    }

    @Override
    public void setMesa(Mesa mesa){
        this.mesa=mesa;
    }

    @Override
    public void setJugador(Jugador robot){
        this.robot=robot;
    }

    @Override
    public void accionar() {
        if (mesa.getRonda().getTruco().getClass().equals(TrucoNoCantado.class))
            if (robot.getFuerzaTotal() >= 28 || mesa.getRonda().getResultados().contains(robot.getEquipo())) {
                robot.cantarTruco();
                mesa.setIA(new EsperandoRespuestaTruco());
                mesa.setJugadorIA(robot);
                return;
            }

        if (mesa.getRonda().getTruco().getClass().equals(TrucoCantado.class)) {
            if (robot.getFuerzaTotal() >= 22 && robot.getMano().size()==3 || robot.getFuerzaTotal()>=14 && robot.getMano().size()==2) {
                robot.quieroTruco();
                mesa.setIA(new TrucoQuerido());
                mesa.setJugadorIA(robot);
                //mesa.getIA().accionar();
                return;
            }
            else {
                robot.noQuieroTruco();
                return;
            }
    }
        jugarCartaMasFuerte(robot);
    }

    public void jugarCartaMasFuerte(Jugador robot){
        Carta cartaMax=robot.getMano().get(0);
        for(Carta carta:robot.getMano())
            if(carta.getFuerza()>cartaMax.getFuerza())
                cartaMax=carta;
        robot.jugarCarta(cartaMax.getNumero(),cartaMax.getPalo());
    }
}
