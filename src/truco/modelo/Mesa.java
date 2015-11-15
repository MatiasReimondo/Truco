package truco.modelo;

import truco.modelo.excepciones.ListaJugadoresVaciaException;

import java.util.*;


public class Mesa {

    private Mazo mazo;
    private Ronda rondaActual;
    private List<Ronda> historial;
    private List<Jugador> listaJugadores;
    private Iterator<Jugador> iterMano;
    private Iterator<Jugador> iterPie;
    private Jugador jugadorMano;
    private Jugador jugadorPie;


    /**CONSTRUCTOR**/
    public Mesa() {
        mazo = new Mazo();
        historial=new ArrayList<>();
    }

    /**SETTERS**/
    public void setJugadores(List<Jugador> listaJugadores) {
        if(listaJugadores.isEmpty()) throw new ListaJugadoresVaciaException();

        this.listaJugadores=listaJugadores;

        iterMano=this.listaJugadores.iterator();
        iterPie=this.listaJugadores.iterator();

        while(iterPie.hasNext())
            iterPie.next();

        jugadorMano=this.listaJugadores.get(0);
        jugadorPie=this.listaJugadores.get(listaJugadores.size()-1);
    }

    /**GETTERS**/
    public Mazo getMazo(){
        return mazo;
    }

    public List<Jugador> getJugadores() {
        return listaJugadores;
    }



    /**ACCIONES**/
    public Jugador getJugadorMano(){
        return jugadorMano;
    }

    public Jugador getJugadorPie(){
        return jugadorPie;
    }

    public boolean jugadorEsPie(Jugador jugador){
        return jugador.getNombre().equals(jugadorPie.getNombre());
    }

    public boolean jugadorEsMano(Jugador jugador){
        return jugador.getNombre().equals(jugadorMano.getNombre());
    }
    public boolean esTurnoDelJugador(Jugador jugador){
        return true;
    }

    public List<Jugador> resolverMano() {
        int maxFuerza = 0;
        int fuerzaEmpate = 0;
        Jugador jugadorMax = null;
        Jugador jugadorEmpate = null;
        List<Jugador> ganadores = new LinkedList<>();


        for (Map.Entry<Jugador, Carta> item : rondaActual.getManoActual().entrySet() ) {
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

    public void repartirCartas(){
        for(int i=0;i<3;i++)
            for(int j=0;j<listaJugadores.size();j++) {
                if(iterMano.hasNext())
                    iterMano.next().robarCartaDelMazo();
                else{
                    iterMano=listaJugadores.iterator();
                    iterMano.next().robarCartaDelMazo();
                }
            }
    }

    public void resolverEnvido(Ronda rondaActual, Jugador jugadorMano) {
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

        jugadorMax.getEquipo().sumarPuntos(2);


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
    public void resolverTruco(){}

    public void agregarCarta(Carta carta, Jugador unJugador) {
        rondaActual.agregarCarta(unJugador,carta);


    }

    public void actualizarJugadorManoPie(){
        if(listaJugadores.isEmpty()) throw new ListaJugadoresVaciaException();

        if(iterMano.hasNext())
            jugadorMano=iterMano.next();
        else iterMano=listaJugadores.iterator();

        if(iterPie.hasNext())
            jugadorPie=iterPie.next();
        else iterPie=listaJugadores.iterator();
    }

    public void nuevaRonda(){
        historial.add(rondaActual);
        rondaActual=new Ronda();

        mazo=new Mazo();
        actualizarJugadorManoPie();
    }
}
