package truco.modelo.estadosTruco;
import truco.modelo.Mesa;

/*********************************************************************************
 * ***************************  EstadoTruco  *************************************
 *********************************************************************************/
public interface EstadoTruco {

     /********************** Metodos de la clase ***********************************/
     int getPuntaje();

/*     EstadoTruco avanzarEstado(Mesa mesa);*/

     EstadoTruco cantarTruco();

     EstadoTruco cantarRetruco();

     EstadoTruco cantarValecuatro();

     EstadoTruco quiero();


}
