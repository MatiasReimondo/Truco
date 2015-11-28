package truco.modelo.IA;

import truco.modelo.Jugador;
import truco.modelo.Mesa;

public interface Comportamiento {

     Comportamiento avanzarALaSiguienteMano(Mesa mesa);

     void comportamientoNormal(Mesa mesa,Jugador IA);

     void comportamientoEnvido(Mesa mesa,Jugador IA);

     void comportamientoTruco(Mesa mesa,Jugador IA);

}