package truco.modelo.estadosTruco;


public class RetrucoQuerido implements EstadoTruco {
    @Override
    public int devolverPuntaje() {
        return 3;
    }

    @Override
    public EstadoTruco avanzarEstado() {
        ValeCuatroCantado valeCuatroCantado= new ValeCuatroCantado();
        return valeCuatroCantado;
    }
}
