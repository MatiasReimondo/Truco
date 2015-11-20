package truco.modelo.estadosTruco;


public class RetrucoQuerido implements EstadoTruco {
    @Override
    public int getPuntaje() {
        return 3;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        return new ValeCuatroCantado();
    }
}
