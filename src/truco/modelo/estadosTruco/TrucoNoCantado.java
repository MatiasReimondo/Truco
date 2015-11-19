package truco.modelo.estadosTruco;

/**
 * Created by shaun on 18/11/2015.
 */
public class TrucoNoCantado implements EstadoTruco {

    @Override
    public int devolverPuntaje() {
        return 1;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        TrucoCantado trucoCantado = new TrucoCantado();
        return trucoCantado;
    }
}
