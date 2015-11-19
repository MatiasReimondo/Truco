package truco.modelo.estadosTruco;


public class ValeCuatroQuerido implements EstadoTruco {
    @Override
    public int devolverPuntaje() {
        return 4;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        return null;
    }
}
