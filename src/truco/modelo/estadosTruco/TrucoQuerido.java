package truco.modelo.estadosTruco;
import truco.modelo.Mesa;
import truco.modelo.excepciones.NoSePuedeCantarAhoraException;

/*********************************************************************************
 * **************************  TrucoQuerido  *************************************
 *********************************************************************************/
public class TrucoQuerido implements EstadoTruco {

    /********************** Metodos de la clase ***********************************/
    @Override
    public int getPuntaje() {
        return 2;
    }
/*
    @Override
    public EstadoTruco avanzarEstado(Mesa mesa ) {

        //si no son iguales se lanza una excepción
        if ( !mesa.getRonda().getTruco().getClass().equals(TrucoQuerido.class) ){
            throw new NoSePuedeCantarAhoraException();
        }
        return new RetrucoCantado();
    }
*/
    @Override
    public EstadoTruco cantarTruco() {
        throw new NoSePuedeCantarAhoraException();
    }

    @Override
    public EstadoTruco cantarRetruco() {
        return new RetrucoCantado();
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
