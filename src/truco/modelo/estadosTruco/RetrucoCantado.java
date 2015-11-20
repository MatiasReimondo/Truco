package truco.modelo.estadosTruco;

/**
 * Created by shaun on 18/11/2015.
 */
public class RetrucoCantado implements EstadoTruco {
    @Override
    public int getPuntaje() {
        return 2;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        return new RetrucoQuerido();
    }
}
