package truco.modelo.estadosTruco;
import truco.modelo.Mesa;

/*********************************************************************************
 * ***************************  EstadoTruco  *************************************
 *********************************************************************************/
public interface EstadoTruco {

     /********************** Métodos de la clase ***********************************/
     int getPuntaje();

     EstadoTruco avanzarEstado(Mesa mesa);


}
