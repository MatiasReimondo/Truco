package truco.modelo.estadosTruco;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

/*********************************************************************************
 * *************************  ValeCuatroQuerido  *********************************
 *********************************************************************************/
public class ValeCuatroQuerido implements EstadoTruco {

    /********************** Metodos de la clase ***********************************/
    @Override
    public int getPuntaje() {
        return 4;
    }

    /*
    @Override
    public EstadoTruco avanzarEstado(Mesa mesa) {
        return null;
    }
    */
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
        throw new NoSePuedeCantarAhoraException();
    }
}
