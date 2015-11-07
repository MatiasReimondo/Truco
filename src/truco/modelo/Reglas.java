package truco.modelo;

import java.util.*;

/**
 * Created by Eze Cruz Avila on 07/11/2015.
 */
public class Reglas {

    public Jugador resolverMano(HashMap<Jugador,Carta> mano){
        int maxFuerza=0;
        Jugador jugadorMax=null;
        Set<HashMap.Entry<Jugador,Carta>> set=mano.entrySet();

        for(HashMap.Entry<Jugador,Carta> item: set)
            if(item.getValue().getFuerza()>maxFuerza){
                maxFuerza=item.getValue().getFuerza();
                jugadorMax=item.getKey();
            }
        return jugadorMax;
    }
}
