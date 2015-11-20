package truco.modelo.estadosTruco;


public class TrucoCantado implements EstadoTruco {

    @Override
    public int getPuntaje(){
        return 1;

    }

    @Override
    public EstadoTruco avanzarEstado() {
        return new TrucoQuerido();
    }
}
