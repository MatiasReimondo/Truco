package truco.modelo.estadosTruco;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

/*********************************************************************************
 * **************************  TrucoQuerido  *************************************
 *********************************************************************************/
public class TrucoQuerido implements EstadoTruco {

    /********************** M�todos de la clase ***********************************/
    @Override
    public int getPuntaje() {
        return 2;
    }

    @Override
    public EstadoTruco avanzarEstado(Mesa mesa ) {

        //si no son iguales se lanza una excepci�n
        if ( !mesa.getEstadoTruco().getClass().equals(new TrucoQuerido().getClass()) ){
            throw new NoSePuedeCantarAhoraException();
        }
        return new RetrucoCantado();
    }
}
