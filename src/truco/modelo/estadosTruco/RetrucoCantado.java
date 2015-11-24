package truco.modelo.estadosTruco;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

public class RetrucoCantado implements EstadoTruco {
    @Override
    public int getPuntaje() {
        return 2;
    }

    @Override
    public EstadoTruco avanzarEstado(Mesa mesa) {
        //Si no son iguales,se lanza una excepcion
        if ( !mesa.getEstadoTruco().getClass().equals(new RetrucoCantado().getClass()) ) {
            throw new NoSePuedeCantarAhoraException();
        }
        return new RetrucoQuerido();
    }
}
