package truco.modelo.estadosTruco;
import truco.modelo.Mesa;

/*********************************************************************************
 * *************************  ValeCuatroQuerido  *********************************
 *********************************************************************************/
public class ValeCuatroQuerido implements EstadoTruco {

    /********************** Metodos de la clase ***********************************/
    @Override
    public int getPuntaje() {
        return 4;
    }

    @Override
    public EstadoTruco avanzarEstado(Mesa mesa) {
        return null;
    }
}
