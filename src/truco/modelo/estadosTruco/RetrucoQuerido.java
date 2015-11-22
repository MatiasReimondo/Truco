package truco.modelo.estadosTruco;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

/*********************************************************************************
 * **************************  RetrucoQuerido  ***********************************
 *********************************************************************************/
public class RetrucoQuerido implements EstadoTruco {

    /********************** M�todos de la clase ***********************************/
    @Override
    public int getPuntaje() {
        return 3;
    }

    @Override
    public EstadoTruco avanzarEstado(Mesa mesa) {

        //Si no son iguales,se lanza una excepcion
        if ( !mesa.getEstadoTruco().getClass().equals(new RetrucoQuerido().getClass()) ) {
            throw new NoSePuedeCantarAhoraException();
        }
        return new ValeCuatroCantado();
    }
}
