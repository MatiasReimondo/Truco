package truco.modelo.IA;

import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.ValeCuatroCantado;

import java.util.Random;


public class EsperandoRespuestaRetruco extends PreEnvido {

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

    @Override
    public void accionar(){
        Random random=new Random();

        if( mesa.getRonda().getTruco().getClass().equals(ValeCuatroCantado.class))
            if(mesa.getRonda().getResultados().contains(robot.getEquipo()) && random.nextInt(10)<7) {
                robot.quieroTruco();
                mesa.setIA(new ValeCuatroQuerido());
                mesa.setJugadorIA(robot);
                return;
            }

        if(mesa.getRonda().getTruco().getClass().equals(truco.modelo.estadosTruco.ValeCuatroQuerido.class)) {
            mesa.setIA(new ValeCuatroQuerido());
            mesa.setJugadorIA(robot);
            mesa.getIA().accionar();
        }
    }
}
