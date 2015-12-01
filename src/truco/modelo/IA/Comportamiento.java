package truco.modelo.IA;

import truco.modelo.Jugador;
import truco.modelo.Mesa;

public abstract class Comportamiento {

     public abstract Comportamiento siguienteEtapa();

     public abstract Comportamiento volverAlComienzo();

     public abstract void setMesa(Mesa mesa);

     public abstract void setJugador(Jugador IA);

     public abstract void comportamientoNormal();

     public abstract void comportamientoEnvido();

     public abstract void comportamientoTruco();

}