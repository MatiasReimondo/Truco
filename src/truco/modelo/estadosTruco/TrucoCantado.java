package truco.modelo.estadosTruco;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

/*********************************************************************************
 * **************************  TrucoCantado  ***********************************
 *********************************************************************************/
public class TrucoCantado implements EstadoTruco {

    /********************** Metodos de la clase ***********************************/
    @Override
    public int getPuntaje(){
        return 1;
    }
/*
    @Override
    public EstadoTruco avanzarEstado(Mesa mesa) {

        //Si no son iguales,se lanza una excepcion
        if ( !mesa.getRonda().getTruco().getClass().equals(TrucoCantado.class) ) {
            throw new NoSePuedeCantarAhoraException();
        }
        return new TrucoQuerido();
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
        return new TrucoQuerido();
    }
}
