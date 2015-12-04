package truco.modelo.IA;

import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.RetrucoCantado;

import java.util.Random;

public class TrucoQuerido implements ComportamientoTrucoQuerido {

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
    public void accionar(){
        Random random=new Random();

        if(mesa.getRonda().getTruco().getClass().equals(RetrucoCantado.class))
            if(mesa.getRonda().getResultados().contains(robot.getEquipo())){
               if(random.nextInt(10)<8){
                   robot.quieroTruco();
                   mesa.setIA(new RetrucoQuerido());
                   mesa.setJugadorIA(robot);
                   return;
               }
                if(random.nextInt(10)<5){
                    robot.aceptarTruco();
                    robot.cantarValeCuatro();
                    mesa.setIA(new EsperandoRespuestaValeCuatro());
                    mesa.setJugadorIA(robot);
                }
            }
        else {
                robot.noQuieroTruco();
                return;
            }

        jugarCartaMasFuerte(robot);
    }

    @Override
    public void jugarCartaMasFuerte(Jugador robot){
        Carta cartaMax=robot.getMano().get(0);
        for(Carta carta:robot.getMano())
            if(carta.getFuerza()>cartaMax.getFuerza()) {
                cartaMax = carta;
            }
        robot.jugarCarta(cartaMax.getNumero(),cartaMax.getPalo());
    }
}
