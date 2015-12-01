package truco.modelo.IA;

import truco.modelo.Jugador;
import truco.modelo.Mesa;

public abstract class Comportamiento {

     abstract Comportamiento siguienteComportamiento();

     abstract void setMesa(Mesa mesa);

     abstract void setJugador(Jugador IA);

     abstract void comportamientoNormal();

     abstract void comportamientoEnvido();

     abstract void comportamientoTruco();

}