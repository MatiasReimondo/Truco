package truco.modelo.IA;


import truco.modelo.Jugador;
import truco.modelo.Mesa;
import truco.modelo.estadosTruco.ValeCuatroQuerido;


public class EsperandoRespuestaValeCuatro extends PreEnvido {

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
        if(mesa.getRonda().getTruco().getClass().equals(ValeCuatroQuerido.class)){
            mesa.setIA(new TrucoQuerido());
            mesa.setJugadorIA(robot);
            mesa.getIA().accionar();
        }
    }

}
