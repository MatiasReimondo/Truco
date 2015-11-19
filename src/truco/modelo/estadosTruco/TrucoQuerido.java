package truco.modelo.estadosTruco;


public class TrucoQuerido implements EstadoTruco {
    @Override
    public int devolverPuntaje() {
        return 2;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        RetrucoCantado retrucoCantado= new RetrucoCantado();
        return retrucoCantado;
    }
}
