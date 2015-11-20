package truco.modelo.estadosTruco;

/**
 * Created by shaun on 18/11/2015.
 */
public class TrucoNoCantado implements EstadoTruco {

    @Override
    public int getPuntaje() {
        return 1;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        return new TrucoCantado();
    }
}
