package truco.modelo.IA;

import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.envido.*;
import truco.modelo.estadosTruco.TrucoCantado;
import truco.modelo.estadosTruco.TrucoNoCantado;

import java.util.Random;

public class PreEnvido implements Comportamiento {

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

    public void comportamientoEnvido(){
        Random r=new Random();

        if(mesa.getArbitro().jugadorEsPie(robot) && enJuego(EnvidoNoCantado.class) && mesa.getRonda().getEnvido().getEnvidoCantado()==null && mesa.getRonda().seEstaJugandoLaPrimera())
            if (robot.getEnvido()>=24 && r.nextInt(10)<8) {                            //Caso B: robot es pie, decide si cantar envido en caso de que no se haya cantado previamente.
                robot.cantarEnvido(new Envido());
                return;
            }
        if(mesa.getArbitro().jugadorEsMano(robot))
            if (robot.getEnvido()>=27 && r.nextInt(10) > 2) {                                       //Caso A: robot es mano, decide si cantar o no el envido.
                robot.cantarEnvido(new Envido());
                return;
            }

       if(seCanto( Envido.class) && !enJuego(Envido.class)) {                           //Caso C: El juegador canto Envido, robot decide si acepta el envido.
           if (robot.getEnvido() >= 26 && r.nextInt(10) > 1) {
               if (robot.getEnvido() >= 28 && r.nextInt(10) > 3) {
                   robot.aceptarEnvido();                                                  //Caso C.1: Si robot tiene al menos 28 de envido, puede subir la apuesta a Envido Envido.
                   robot.cantarEnvido(new Envido());
                   return;
               }
               if (robot.getEnvido() >= 29 && r.nextInt(10) > 4) {                         //Caso C.2: Si robot tiene al menos 29 de envido, puede subir la apuesta a Envido Real Envido.
                   robot.aceptarEnvido();
                   robot.cantarEnvido(new RealEnvido());
                   return;
               }
               robot.quieroEnvido();                                                      //Caso C.3: Si robot, no sube la puesta, muy probablemente solo acepte el envido.
               //
               return;
           } else {
               System.out.print(robot.getEnvido());
               robot.noQuieroEnvido();                                                         //Caso D: Si robot tiene menos de 27 de envido y el jugador canto envido, robot no acepta.
               return;
           }
       }
        if(seCanto(Envido.class) && enJuego(Envido.class)){                             //Caso E: Se quizo el Envido y la apuesta ahora es Envido Envido, robot decide si acepta
            if (robot.getEnvido() >= 29 && r.nextInt(10) > 2) {
                if (robot.getEnvido() >=31  && r.nextInt(10) > 3) {
                    robot.aceptarEnvido();                                                 //Caso E.1: Si robot tiene al menos 31 de tanto, tiene 60% de probabilidad de subir la apuesta a RealEnvido.
                    robot.cantarEnvido(new RealEnvido());
                    return;
                }
                if (robot.getEnvido() >= 32 && r.nextInt(10) > 2) {
                    robot.aceptarEnvido();                                                 //Caso E.2: Si robot tiene al menos 32 de tanto, tiene 70% de subir la apuesta a Falta Envido.
                    robot.cantarEnvido(new FaltaEnvido());
                    return;
                }
                robot.quieroEnvido();                                                     //Caso E.3: robot solo acepta el Envido Envido;
                
                return;
            }
            else {                                                                      //Caso E.4: robot no acepta el Envido Envido;
                robot.noQuieroEnvido();
                return;
            }
        }
        if(seCanto(RealEnvido.class) && enJuego(Envido.class)){                         //Caso F: Esta en juego el Envido y el jugador canto Real Envido.
            if (robot.getEnvido() >= 30 && r.nextInt(10) > 2) {
                if (robot.getEnvido() >= 32 && r.nextInt(10) > 2) {
                    robot.aceptarEnvido();                                                 //Caso F.1: Si robot tiene al menos 32 de tanto, tiene 70% de subir la apuesta a Falta Envido.
                    robot.cantarEnvido(new FaltaEnvido());
                    return;
                }
                robot.quieroEnvido();                                                     //Caso F.2: robot acepta la apuesta.
                
                return;
            }
            else {
                robot.noQuieroEnvido();                                                    //Caso F.3: robot no acepta la apuesta.
                return;
            }
        }
        if(seCanto(FaltaEnvido.class))
            if(robot.getEnvido()>=31 && r.nextInt(10)!=0){                                 //Caso G: Si robot tiene al menos 31 de tanto, tiene 10% de probabilidad de no querer el Falta Envido.
                robot.quieroEnvido();
                
            }
    }

    @Override
    public void accionar(){
        if(mesa.getRonda().seEstaJugandoLaPrimera())
            comportamientoEnvido();

        if(mesa.getRonda().getEnvido().getClass().equals(EnvidoTerminado.class) || !mesa.getRonda().seEstaJugandoLaPrimera()){
            mesa.setIA(new PostEnvido());
            mesa.setJugadorIA(robot);
            //mesa.getIA().accionar();
            return;
        }
        if(mesa.getJugadorActivo().equals(robot)) {
            if (mesa.getRonda().getTruco().getClass().equals(TrucoCantado.class))
                if (robot.getFuerzaTotal() >= 25) {
                    robot.aceptarTruco();
                    mesa.setIA(new TrucoQuerido());
                    mesa.setJugadorIA(robot);
                    mesa.getIA().accionar();
                    return;
                } else {
                    robot.noQuieroTruco();
                    return;
                }

            if (mesa.getRonda().getTruco().getClass().equals(TrucoNoCantado.class))
                if (robot.getFuerzaTotal() >= 32) {
                    robot.cantarTruco();
                    mesa.setIA(new EsperandoRespuestaTruco());
                    mesa.setJugadorIA(robot);
                }

            jugarCartaMasFuerte(robot);
        }
    }

    public void jugarCartaMasFuerte(Jugador robot){
        Carta cartaMax=robot.getMano().get(0);
        for(Carta carta:robot.getMano())
            if(carta.getFuerza()>cartaMax.getFuerza())
                cartaMax=carta;
        robot.jugarCarta(cartaMax.getNumero(),cartaMax.getPalo());
    }

    private boolean enJuego(Class envido){
        return mesa.getRonda().getEnvido().getClass().equals(envido);
    }

    private boolean seCanto(Class envido){
        return (mesa.getRonda().getEnvido().getEnvidoCantado()!=null && mesa.getRonda().getEnvido().getEnvidoCantado().getClass().equals(envido));
    }

}
