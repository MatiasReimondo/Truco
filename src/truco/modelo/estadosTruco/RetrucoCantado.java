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
        if ( !mesa.getRonda().getTrucoEnJuego().getClass().equals(RetrucoCantado.class) ) {
            throw new NoSePuedeCantarAhoraException();
        }
        return new RetrucoQuerido();
    }

    @Override
    public EstadoTruco cantarTruco() {
        throw new NoSePuedeCantarAhoraException();
    }

    @Override
    public EstadoTruco cantarRetruco() {
        throw new NoSePuedeCantarAhoraException();
    }

    @Override
    public EstadoTruco cantarValecuatro() {
        throw new NoSePuedeCantarAhoraException();
    }

    @Override
    public EstadoTruco quiero() {
        return new RetrucoQuerido();
    }
}
