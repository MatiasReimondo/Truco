package truco.modelo.IA;

import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.*;
import truco.modelo.excepciones.JugadorNoHabilitadoParaCantarTanto;

import java.util.Random;

public class TerceraMano implements Comportamiento {


    @Override
    public Comportamiento avanzarALaSiguienteMano(Mesa mesa){
        return null;
    }

    @Override
    public void comportamientoEnvido(Mesa mesa, Jugador IA) {
        throw new JugadorNoHabilitadoParaCantarTanto();
    }

    @Override
    public void comportamientoTruco(Mesa mesa,Jugador IA){
        Random r=new Random();

        if( mesa.getRonda().getTrucoEnJuego().getClass().equals(TrucoCantado.class) || mesa.getRonda().getTrucoEnJuego().getClass().equals(RetrucoCantado.class) || (mesa.getRonda().getTrucoEnJuego().getClass().equals(ValeCuatroCantado.class) && r.nextInt(10)<2)) {
            IA.quieroTruco();
        }
        if(mesa.getRonda().getTrucoEnJuego().getClass().equals(ValeCuatroCantado.class) || r.nextInt(10)<3)     //60% de querer el Vale Cuatro.
            IA.quieroTruco();

        if(mesa.getRonda().getTrucoEnJuego().getClass().equals(TrucoNoCantado.class))
            IA.cantarTruco();

        if(mesa.getRonda().getTrucoEnJuego().getClass().equals(TrucoQuerido.class))
            IA.cantarRetruco();

        if(mesa.getRonda().getTrucoEnJuego().getClass().equals(RetrucoQuerido.class) && r.nextInt(10)<2)        //70% de cantar Vale Cuatro.
            IA.cantarValeCuatro();

    }

    @Override
    public void comportamientoNormal(Mesa mesa,Jugador IA){
        if(mesa.getRonda().getResultados().contains(IA.getEquipo()))
            comportamientoTruco(mesa,IA);
        else
            jugarCartaMasFuerte(IA);
    }

    private void jugarCartaMasFuerte(Jugador IA){
        Carta cartaMax=IA.getMano().get(0);
        for(Carta carta:IA.getMano())
            if(carta.getFuerza()>cartaMax.getFuerza())
                cartaMax=carta;
        IA.jugarCarta(cartaMax.getNumero(),cartaMax.getPalo());

    }

}
