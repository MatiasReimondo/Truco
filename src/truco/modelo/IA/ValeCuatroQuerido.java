package truco.modelo.IA;

import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.RetrucoCantado;

import java.util.Random;

public class ValeCuatroQuerido implements ComportamientoTrucoQuerido {

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
