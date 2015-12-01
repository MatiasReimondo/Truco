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
    public Comportamiento volverAlComienzo(){
        PrimeraMano primeraMano=new PrimeraMano();
        primeraMano.setJugador(IA);
        primeraMano.setMesa(mesa);
        return primeraMano;
    }

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

        SegundaMano segundaMano=new SegundaMano();
        segundaMano.setMesa(this.mesa);
        segundaMano.setJugador(this.IA);
        return  segundaMano;
    }

    @Override
    public void comportamientoEnvido(){
        Random r=new Random();

        if(mesa.getArbitro().jugadorEsMano(IA))
            if(r.nextInt(IA.getEnvido()-20)>4)  {                                       //Caso A: IA es mano, decide si cantar o no el envido.
                IA.cantarEnvido(new Envido());
                System.out.println("hola");
                return;
            }

        if(mesa.getArbitro().jugadorEsPie(IA) && enJuego(EnvidoNoCantado.class) && mesa.getRonda().getEnvido().getEnvidoCantado()==null && mesa.getRonda().seEstaJugandoLaPrimera())
            if (mesa.getArbitro().jugadorEsPie(IA))
                if (IA.getEnvido()>=24 && r.nextInt(10)<8) {                            //Caso B: IA es pie, decide si cantar envido en caso de que no se haya cantado previamente.
                    IA.cantarEnvido(new Envido());
                    return;
                }

       if(seCanto( Envido.class) && !enJuego(Envido.class)) {                           //Caso C: El juegador canto Envido, IA decide si acepta el envido.
           if (IA.getEnvido() >= 27 && r.nextInt(10) > 1) {
               if (IA.getEnvido() >= 28 && r.nextInt(10) > 3) {
                   IA.aceptarEnvido();                                                  //Caso C.1: Si IA tiene al menos 28 de envido, puede subir la apuesta a Envido Envido.
                   IA.cantarEnvido(new Envido());
                   return;
               }
               if (IA.getEnvido() >= 29 && r.nextInt(10) > 4) {                         //Caso C.2: Si IA tiene al menos 29 de envido, puede subir la apuesta a Envido Real Envido.
                   IA.aceptarEnvido();
                   IA.cantarEnvido(new RealEnvido());
                   return;
               }
               IA.aceptarEnvido();                                                      //Caso C.3: Si IA, no sube la puesta, muy probablemente solo acepte el envido.
               return;
           }
       }
       else {
           IA.noQuieroEnvido();                                                         //Caso D: Si IA tiene menos de 27 de envido y el jugador canto envido, IA no acepta.
           return;
       }

        if(seCanto(Envido.class) && enJuego(Envido.class)){                             //Caso E: Se quizo el Envido y la apuesta ahora es Envido Envido, IA decide si acepta
            if (IA.getEnvido() >= 29 && r.nextInt(10) > 2) {
                if (IA.getEnvido() >=31  && r.nextInt(10) > 3) {
                    IA.aceptarEnvido();                                                 //Caso E.1: Si IA tiene al menos 31 de tanto, tiene 60% de probabilidad de subir la apuesta a RealEnvido.
                    IA.cantarEnvido(new RealEnvido());
                    return;
                }
                if (IA.getEnvido() >= 32 && r.nextInt(10) > 2) {
                    IA.aceptarEnvido();                                                 //Caso E.2: Si IA tiene al menos 32 de tanto, tiene 70% de subir la apuesta a Falta Envido.
                    IA.cantarEnvido(new FaltaEnvido());
                    return;
                }
                IA.aceptarEnvido();                                                     //Caso E.3: IA solo acepta el Envido Envido;
                return;
            }
            else {                                                                      //Caso E.4: IA no acepta el Envido Envido;
                IA.noQuieroEnvido();
                return;
            }
        }
        if(seCanto(RealEnvido.class) && enJuego(Envido.class)){                         //Caso F: Esta en juego el Envido y el jugador canto Real Envido.
            if (IA.getEnvido() >= 30 && r.nextInt(10) > 2) {
                if (IA.getEnvido() >= 32 && r.nextInt(10) > 2) {
                    IA.aceptarEnvido();                                                 //Caso F.1: Si IA tiene al menos 32 de tanto, tiene 70% de subir la apuesta a Falta Envido.
                    IA.cantarEnvido(new FaltaEnvido());
                    return;
                }
                IA.aceptarEnvido();                                                     //Caso F.2: IA acepta la apuesta.
                return;
            }
            else {
                IA.noQuieroEnvido();                                                    //Caso F.3: IA no acepta la apuesta.
                return;
            }
        }
        if(seCanto(FaltaEnvido.class))
            if(IA.getEnvido()>=31 && r.nextInt(10)!=0){                                 //Caso G: Si IA tiene al menos 31 de tanto, tiene 10% de probabilidad de no querer el Falta Envido.
                IA.aceptarEnvido();
                return;
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

    private boolean enJuego(Class envido){
        return mesa.getRonda().getEnvido().getClass().equals(envido);
    }

    private boolean seCanto(Class envido){
        return (mesa.getRonda().getEnvido().getEnvidoCantado()!=null && mesa.getRonda().getEnvido().getEnvidoCantado().getClass().equals(envido));
    }
}
