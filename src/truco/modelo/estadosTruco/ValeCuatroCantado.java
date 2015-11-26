package truco.modelo.estadosTruco;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

/*********************************************************************************
 * ************************  ValeCuatroCantado  **********************************
 *********************************************************************************/
public class ValeCuatroCantado implements EstadoTruco {

    /********************** Metodos de la clase ***********************************/
    @Override
    public int getPuntaje() {
        return 3;
    }

    @Override
    public EstadoTruco avanzarEstado(Mesa mesa) {

        //Si no son iguales,se lanza una excepcion
        if ( !mesa.getRonda().getTrucoEnJuego().getClass().equals( ValeCuatroCantado.class) ) {
            throw new NoSePuedeCantarAhoraException();
        }
        return new ValeCuatroQuerido();
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
        return new ValeCuatroQuerido();
    }
}
