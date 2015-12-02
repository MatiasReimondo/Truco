package truco.modelo.IA;

import truco.modelo.Jugador;
import truco.modelo.Mesa;

public interface Comportamiento {

     Comportamiento reiniciar();

     void setMesa(Mesa mesa);

     void setJugador(Jugador IA);

     void accionar();

}