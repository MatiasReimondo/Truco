package truco.modelo.IA;

import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.envido.Envido;
import truco.modelo.envido.EnvidoNoCantado;
import truco.modelo.envido.FaltaEnvido;
import truco.modelo.envido.RealEnvido;
import truco.modelo.estadosTruco.TrucoCantado;

import java.util.Random;

public class PrimeraMano extends Comportamiento {

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
    public Comportamiento siguienteComportamiento(){

        SegundaMano segundaMano=new SegundaMano();
        segundaMano.setMesa(this.mesa);
        segundaMano.setJugador(this.IA);
        return  segundaMano;
    }

    @Override
    public void comportamientoEnvido(){
        Random r=new Random();

        if(seCanto(mesa,EnvidoNoCantado.class)) {
            if (mesa.getArbitro().jugadorEsPie(IA))                         //Si IA es pie, tiene al menos 24 de tanto y el jugador no canta envido, tiene 70% de probabilidades de cantarlo.
                if (IA.getEnvido()>=24 && r.nextInt(10)<7)
                    IA.cantarEnvido(new Envido());

            if(mesa.getArbitro().jugadorEsMano(IA))
                if(r.nextInt(IA.getEnvido()-20)>4)                          //Si IA es mano tiene 64% de probabilidad de cantar Envido.
                    IA.cantarEnvido(new Envido());
        }

       if(seCanto(mesa, Envido.class) && !enJuego(mesa,Envido.class)) {     //Si el jugador canta envido...
           if (IA.getEnvido() >= 27 && r.nextInt(10) > 1) {                 //Si IA tiene al menos 27 de envido, tiene un 20% de probabilidad de no quererlo.
               if (IA.getEnvido() >= 28 && r.nextInt(10) > 3) {             //Si IA tiene al menos 28 de tanto, tiene 60% de probabilidad de volver a cantar Envido.
                   IA.aceptarEnvido();
                   IA.cantarEnvido(new Envido());
               }
               if (IA.getEnvido() >= 29 && r.nextInt(10) > 4) {             //Si IA tiene al menos 29 de tanto, tiene 50% de subir la apuesta a Real Envido.
                   IA.aceptarEnvido();
                   IA.cantarEnvido(new RealEnvido());
               }
               IA.aceptarEnvido();
           }
       }
       else
           IA.noQuieroEnvido();

        if(seCanto(mesa,Envido.class) && enJuego(mesa,Envido.class)){
            if (IA.getEnvido() >= 29 && r.nextInt(10) > 2) {                 //Si IA tiene al menos 29 de envido, tiene un 30% de probabilidad de no quererlo.
                if (IA.getEnvido() >=31  && r.nextInt(10) > 3) {             //Si IA tiene al menos 31 de tanto, tiene 60% de probabilidad de subir la apuesta a RealEnvido.
                    IA.aceptarEnvido();
                    IA.cantarEnvido(new RealEnvido());
                }
                if (IA.getEnvido() >= 32 && r.nextInt(10) > 2) {             //Si IA tiene al menos 32 de tanto, tiene 70% de subir la apuesta a Falta Envido.
                    IA.aceptarEnvido();
                    IA.cantarEnvido(new FaltaEnvido());
                }
                IA.aceptarEnvido();
            }
            else
                IA.noQuieroEnvido();
        }
        if(seCanto(mesa,RealEnvido.class) && enJuego(mesa,Envido.class)){
            if (IA.getEnvido() >= 30 && r.nextInt(10) > 2) {                 //Si IA tiene al menos 29 de envido, tiene un 30% de probabilidad de no quererlo.
                if (IA.getEnvido() >= 32 && r.nextInt(10) > 2) {             //Si IA tiene al menos 32 de tanto, tiene 70% de subir la apuesta a Falta Envido.
                    IA.aceptarEnvido();
                    IA.cantarEnvido(new FaltaEnvido());
                }
                IA.aceptarEnvido();
            }
            else
                IA.noQuieroEnvido();
        }
        if(seCanto(mesa,FaltaEnvido.class))
            if(IA.getEnvido()>=31 && r.nextInt(10)!=0){                       //Si IA tiene al menos 31 de tanto, tiene 10% de probabilidad de no querer el Falta Envido.
                IA.aceptarEnvido();
            }
    }

    public void comportamientoNormal(){
        comportamientoEnvido();
        if(mesa.getRonda().getTruco().getClass().equals(TrucoCantado.class))
            comportamientoTruco();
        else
            jugarCartaMasFuerte(IA);            //IA siempre intenta ganar la primera.
    }

    @Override
    public void comportamientoTruco(){
            if(IA.getFuerzaTotal()>=25)
                IA.aceptarTruco();
            else
                IA.noQuieroTruco();
    }

    private void jugarCartaMasFuerte(Jugador IA){
        Carta cartaMax=IA.getMano().get(0);
        for(Carta carta:IA.getMano())
            if(carta.getFuerza()>cartaMax.getFuerza())
                cartaMax=carta;
        IA.jugarCarta(cartaMax.getNumero(),cartaMax.getPalo());

    }

    private boolean enJuego(Mesa mesa,Class envido){
        return mesa.getRonda().getEnvido().getClass().equals(envido);
    }

    private boolean seCanto(Mesa mesa, Class envido){
        return mesa.getRonda().getEnvido().getEnvidoCantado().getClass().equals(envido);
    }
}
