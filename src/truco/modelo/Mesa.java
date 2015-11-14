package truco.modelo;

import truco.modelo.excepciones.MazoSinCartasException;

import java.util.*;


public class Mesa {

    private Mazo mazo;
    private Ronda rondaActual;
    private List<Ronda> historial;

    public Mesa() {
        mazo = new Mazo();
        historial=new ArrayList<>();
    }

    public Mazo getMazo(){
        return mazo;
    }

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

    public void repartirCartas(List<Jugador> listaJugadores){

        for(int i=0;i<3;i++)
            for(Jugador jugador:listaJugadores) {
                if (mazo.getCartas().size() == 0) throw new MazoSinCartasException();
                jugador.robarCarta(mazo.getCartas().removeFirst());
            }
    }

    public Jugador resolverEnvido(Ronda rondaActual, Jugador jugadorMano) {
        int maxEnvido = 0;
        Jugador jugadorMax = null;

        Set<Map.Entry<Jugador,Integer>> set = rondaActual.getTantosActuales().entrySet();

        for (Map.Entry<Jugador,Integer> item : set) {
            if (item.getValue() > maxEnvido) {
                maxEnvido = item.getValue();
                jugadorMax = item.getKey();
            } else if (item.getValue()== maxEnvido && item.getKey().getNombre().equals(jugadorMano.getNombre())) {
                jugadorMax = item.getKey();
            }
        }

        return jugadorMax;


    }
/*
    public void resolverEnvido(List<Jugador> listaJugadores){

        Jugador ganador=null;
        int envidoMax=0;
        for(Jugador jugador:listaJugadores){
            if(jugador.getEnvido()>envidoMax)
                ganador=jugador;
        }

        ganador.getEquipo().sumarPuntos();
    }
*/
    public void resolverTruco(){;}

    public void agregarCarta(Carta carta) {
    }

    public void siguienteRonda(){
        historial.add(rondaActual);
        rondaActual=new Ronda();
        actualizarJugadorManoPie();
    }
}
