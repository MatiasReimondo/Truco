package truco.modelo.estadosTruco;


public class ValeCuatroQuerido implements EstadoTruco {
    @Override
    public int getPuntaje() {
        return 4;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        return null;
    }
}
