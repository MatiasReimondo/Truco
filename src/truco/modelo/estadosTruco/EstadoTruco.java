package truco.modelo.estadosTruco;
import truco.modelo.Mesa;

/*********************************************************************************
 * ***************************  EstadoTruco  *************************************
 *********************************************************************************/
public interface EstadoTruco {

     /********************** M�todos de la clase ***********************************/
     int getPuntaje();

     EstadoTruco avanzarEstado(Mesa mesa);


}
