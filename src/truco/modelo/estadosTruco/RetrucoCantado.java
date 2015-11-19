package truco.modelo.estadosTruco;

/**
 * Created by shaun on 18/11/2015.
 */
public class RetrucoCantado implements EstadoTruco {
    @Override
    public int devolverPuntaje() {
        return 2;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        RetrucoQuerido retrucoQuerido= new RetrucoQuerido();
        return retrucoQuerido;
    }
}
