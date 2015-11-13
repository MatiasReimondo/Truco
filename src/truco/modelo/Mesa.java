package truco.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Mesa {


    public Mesa(){;}

    public List<Jugador> resolverMano(Ronda rondaActual) {
        int maxFuerza = 0;
        int fuerzaEmpate = 0;
        Jugador jugadorMax = null;
        Jugador jugadorEmpate = null;
        List<Jugador> ganadores = new LinkedList<Jugador>();

        Set<Map.Entry<Jugador, Carta>> set = rondaActual.getManoActual().entrySet();

        for (Map.Entry<Jugador, Carta> item : set) {
            if (item.getValue().getFuerza() > maxFuerza) {
                maxFuerza = item.getValue().getFuerza();
                jugadorMax = item.getKey();
            } else if (item.getValue().getFuerza() == maxFuerza) {
                if(!item.getKey().getEquipo().getNombre().equals(jugadorMax.getEquipo().getNombre()))
                fuerzaEmpate = item.getValue().getFuerza();
                jugadorEmpate = item.getKey();
            }
        }
        ganadores.add(jugadorMax);
        if (maxFuerza==fuerzaEmpate){
            ganadores.add(jugadorEmpate);
        }
        return ganadores;


    }


    public void resolverEnvido(){;}

    public void resolverTruco(){;}
}
