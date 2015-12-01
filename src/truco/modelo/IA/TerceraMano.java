package truco.modelo.IA;

import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.*;
import truco.modelo.excepciones.JugadorNoHabilitadoParaCantarTanto;

import java.util.Random;

public class TerceraMano extends Comportamiento {

    private Mesa mesa;
    private Jugador IA;

    @Override
    public void setMesa(Mesa mesa){
        this.mesa=mesa;
    }

    @Override
    public void setJugador(Jugador IA){
        this.IA=IA;
    }


    @Override
    public Comportamiento siguienteEtapa(){
        return null;
    }

    @Override
    public Comportamiento volverAlComienzo(){
        PrimeraMano primeraMano=new PrimeraMano();
        primeraMano.setJugador(IA);
        primeraMano.setMesa(mesa);
        return primeraMano;
    }
    @Override
    public void comportamientoEnvido() {
        throw new JugadorNoHabilitadoParaCantarTanto();
    }

    @Override
    public void comportamientoTruco(){
        Random r=new Random();

        if( mesa.getRonda().getTruco().getClass().equals(TrucoCantado.class) || mesa.getRonda().getTruco().getClass().equals(RetrucoCantado.class) || (mesa.getRonda().getTruco().getClass().equals(ValeCuatroCantado.class) && r.nextInt(10)<2)) {
            IA.aceptarTruco();
        }
        if(mesa.getRonda().getTruco().getClass().equals(ValeCuatroCantado.class) || r.nextInt(10)<3)     //60% de querer el Vale Cuatro.
            IA.aceptarTruco();

        if(mesa.getRonda().getTruco().getClass().equals(TrucoNoCantado.class))
            IA.cantarTruco();

        if(mesa.getRonda().getTruco().getClass().equals(TrucoQuerido.class))
            IA.cantarRetruco();

        if(mesa.getRonda().getTruco().getClass().equals(RetrucoQuerido.class) && r.nextInt(10)<2)        //70% de cantar Vale Cuatro.
            IA.cantarValeCuatro();

    }

    @Override
    public void comportamientoNormal(){
        if(mesa.getRonda().getResultados().contains(IA.getEquipo()))
            comportamientoTruco();
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
