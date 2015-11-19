package truco.modelo.estadosTruco;


public class ValeCuatroCantado implements EstadoTruco {
    @Override
    public int devolverPuntaje() {
        return 3;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        ValeCuatroQuerido valeCuatroQuerido= new ValeCuatroQuerido();
        return valeCuatroQuerido;
    }
}
