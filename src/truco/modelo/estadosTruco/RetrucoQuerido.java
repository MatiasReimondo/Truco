package truco.modelo.estadosTruco;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

/*********************************************************************************
 * **************************  RetrucoQuerido  ***********************************
 *********************************************************************************/
public class RetrucoQuerido implements EstadoTruco {

    /********************** Metodos de la clase ***********************************/
    @Override
    public int getPuntaje() {
        return 3;
    }

    @Override
    public EstadoTruco avanzarEstado(Mesa mesa) {

        //Si no son iguales,se lanza una excepcion
        if ( !mesa.getRonda().getTrucoEnJuego().getClass().equals(new RetrucoQuerido().getClass()) ) {
            throw new NoSePuedeCantarAhoraException();
        }
        return new ValeCuatroCantado();
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
        return new ValeCuatroCantado();
    }

    @Override
    public EstadoTruco quiero() {
        throw new NoSePuedeCantarAhoraException();
    }
}
