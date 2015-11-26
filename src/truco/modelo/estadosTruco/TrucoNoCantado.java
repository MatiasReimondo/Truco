package truco.modelo.estadosTruco;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

/*********************************************************************************
 * **************************  TrucoNoCantado  ***********************************
 *********************************************************************************/
public class TrucoNoCantado implements EstadoTruco {

    /********************** Metodos de la clase ***********************************/
    @Override
    public int getPuntaje() {
        return 1;
    }

    @Override
    public EstadoTruco avanzarEstado(Mesa mesa) {

        //Si no son iguales se lanza una excepcion
        if ( !mesa.getEstadoTruco().getClass().equals(new TrucoNoCantado().getClass()) ) {
            throw new NoSePuedeCantarAhoraException();
        }
        return new TrucoCantado();
    }

    @Override
    public EstadoTruco cantarTruco() {
        return new TrucoCantado();
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
        throw new NoSePuedeCantarAhoraException();
    }
}
