package truco.modelo.IA;

import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.RetrucoCantado;

import java.util.Random;

public class EsperandoRespuestaTruco extends PreEnvido {

    private Mesa mesa;
    private Jugador robot;

    @Override
    public void setJugador(Jugador jugador){
        robot=jugador;
    }

    @Override
    public void setMesa(Mesa mesa){
        this.mesa=mesa;
    }
    
    public void accionar(){
        Random random=new Random();

        if( mesa.getRonda().getTruco().getClass().equals(RetrucoCantado.class))
            if(mesa.getRonda().getResultados().contains(robot.getEquipo()) && random.nextInt(10)<8) {
                robot.aceptarTruco();
                robot.cantarValeCuatro();
                mesa.setIA(new EsperandoRespuestaRetruco());
                mesa.setJugadorIA(robot);
                return;
            }

        if(mesa.getRonda().getTruco().getClass().equals(truco.modelo.estadosTruco.TrucoQuerido.class)){
            mesa.setIA(new TrucoQuerido());
            mesa.setJugadorIA(robot);
            mesa.getIA().accionar();
        }
    }
}
