package truco.modelo.IA;

import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.ValeCuatroCantado;

import java.util.Random;

public class RetrucoQuerido implements ComportamientoTrucoQuerido {

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

    public void accionar(){
        Random random=new Random();

        if(mesa.getRonda().getTruco().getClass().equals(ValeCuatroCantado.class)) {
            if (mesa.getRonda().getResultados().contains(robot.getEquipo()) && random.nextInt(10) < 7) {
                robot.quieroTruco();
                mesa.setIA(new ValeCuatroQuerido());
                mesa.setJugadorIA(robot);
                return;
            }
        }
        else {
            robot.noQuieroTruco();
            return;
        }
        if(robot.getFuerzaTotal()>=20 && random.nextInt(10)<6){
            mesa.setIA(new EsperandoRespuestaValeCuatro());
            mesa.setJugadorIA(robot);
            return;
        }
        jugarCartaMasFuerte(robot);
    }

    @Override
    public void jugarCartaMasFuerte(Jugador robot){
        Carta cartaMax=robot.getMano().get(0);
        for(Carta carta:robot.getMano())
            if(carta.getFuerza()>cartaMax.getFuerza())
                cartaMax=carta;
        robot.jugarCarta(cartaMax.getNumero(),cartaMax.getPalo());
    }
}
