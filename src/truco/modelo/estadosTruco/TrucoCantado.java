package truco.modelo.estadosTruco;


public class TrucoCantado implements EstadoTruco {

    @Override
    public int devolverPuntaje(){
        return 1;

    }

    @Override
    public EstadoTruco avanzarEstado() {
        return new TrucoQuerido();
    }
}
