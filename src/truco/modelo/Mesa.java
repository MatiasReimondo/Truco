package truco.modelo;
import truco.modelo.excepciones.ListaJugadoresVaciaException;
import truco.modelo.excepciones.RondaTerminadaException;

import java.util.*;

public class Mesa {

    /**ATRIBUTOS**/
    private Mazo mazo;
    private Ronda ronda;
    private List<Jugador> jugadores;
    private int nroJugadores;
    private ListIterator<Jugador> iterJugadorActivo;
    private Jugador jugadorActivo;
    private final Arbitro arbitro;

    /**CONSTRUCTOR**/
    public Mesa() {
        mazo = new Mazo();
        arbitro =new Arbitro();
        arbitro.setMesa(this);
    }

    /**SETTERS**/
    public void setJugadores(List<Jugador> listaJugadores) {

        arbitro.evaluarListaDeJugadores(listaJugadores);

        this.jugadores =listaJugadores;
        nroJugadores =listaJugadores.size();
        iterJugadorActivo=jugadores.listIterator();
    }

    /**GETTERS**/
    public Mazo getMazo(){
        return mazo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador getJugadorMano(){
        return jugadores.get(0);
    }

    public Ronda getRonda(){
        return ronda;
    }

    public Jugador getJugadorActivo() {
        return jugadorActivo;
    }

    public int getNroJugadores() {
        return nroJugadores;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }


    /**ACCIONES**/

    public void repartirCartas(){
        for(Jugador jugador: jugadores)
            for(int i=0;i<3;i++)
                jugador.robarCartaDelMazo();
    }

    public void resolverEnvido(){

        int envidoMax=0;
        Jugador auxiliar=jugadorActivo;
        Equipo equipoGanador=this.getJugadorMano().getEquipo();
        Equipo equipoPerdedor=jugadores.get(nroJugadores-1).getEquipo();

        for(int i=0;i<nroJugadores;i++) {
            jugadorActivo=jugadores.get(i);
            if (jugadorActivo.quiereMostrarEnvido())
                if (jugadorActivo.getEnvido() > envidoMax) {
                    envidoMax = jugadorActivo.getEnvido();
                    if (jugadorActivo.getEquipo() != equipoGanador) {
                        equipoPerdedor = equipoGanador;
                        equipoGanador = jugadorActivo.getEquipo();
                    }
            }
        }
            equipoGanador.sumarPuntos(this.ronda.getTantoEnJuego().getPuntos(equipoGanador, equipoPerdedor));
        jugadorActivo=auxiliar;

    }

    public void resolverFlor(){

        int florMax=0;
        this.jugadorActivo=this.getJugadorMano();
        resetJugadorActivo();
        Equipo equipoGanador=this.getJugadorMano().getEquipo();

        for(int i=0;i<nroJugadores;i++) {
            if (jugadorActivo.quiereMostrarEnvido())
                if (jugadorActivo.getFlor() > florMax) {
                    florMax = jugadorActivo.getEnvido();
                    if (jugadorActivo.getEquipo() != equipoGanador)
                        equipoGanador = jugadorActivo.getEquipo();
                }
                else if(jugadorActivo.getFlor()==florMax)
                    if(jugadorActivo.getEquipo().equals(this.getJugadorMano().getEquipo()) && !equipoGanador.equals(this.getJugadorMano().getEquipo()))
                        equipoGanador = jugadorActivo.getEquipo();
            this.siguienteJugador();
        }
        equipoGanador.sumarPuntos(ronda.getFlorEnJuego().getPuntos());
    }

    public void resolverMano() {
        if(ronda.termino())
            throw new RondaTerminadaException();

        int maxFza = 0;
        Equipo equipoGanador=null;

        for (Map.Entry<Jugador, Carta> parJugadorCarta : ronda.getManoActual().entrySet() )
            if (parJugadorCarta.getValue().getFuerza() > maxFza) {
                maxFza = parJugadorCarta.getValue().getFuerza();
                equipoGanador = parJugadorCarta.getKey().getEquipo();
            }
             else if (parJugadorCarta.getValue().getFuerza() == maxFza)
                if(!parJugadorCarta.getKey().getEquipo().equals(equipoGanador))
                    equipoGanador=null;

        this.evaluarMano(equipoGanador);
        ronda.avanzarALaSiguienteMano();
    }

    public void nuevaRonda(){
        ronda =new Ronda(this);
        mazo=new Mazo();
        mazo.mezclar();
        jugadorActivo=this.jugadores.get(0);
        iterJugadorActivo= jugadores.listIterator(1);

        for(Jugador jugador:jugadores)
            jugador.getMano().clear();
    }

    /**AUXILIARES**/
    public void siguienteJugador(){
        if(iterJugadorActivo.hasNext())
            jugadorActivo= iterJugadorActivo.next();
        else{
            iterJugadorActivo=jugadores.listIterator();
            jugadorActivo= iterJugadorActivo.next();
        }
    }

    public void jugadorAnterior() {
        if(iterJugadorActivo.hasPrevious()) {
            iterJugadorActivo.previous();
            if (iterJugadorActivo.hasPrevious())
                jugadorActivo = iterJugadorActivo.previous();
            else {
                while (iterJugadorActivo.hasNext())
                    iterJugadorActivo.next();
                iterJugadorActivo.previous();
                jugadorActivo = iterJugadorActivo.previous();
            }
        }
        else {
            while (iterJugadorActivo.hasNext())
                iterJugadorActivo.next();
            iterJugadorActivo.previous();
            jugadorActivo=iterJugadorActivo.previous();
        }
        iterJugadorActivo.next();
    }

    private void resetJugadorActivo(){
        iterJugadorActivo=jugadores.listIterator();
        iterJugadorActivo.next();
    }

    public void actualizarJugadorMano(){
        if(jugadores.isEmpty()) throw new ListaJugadoresVaciaException();
        jugadores.add(jugadores.remove(0));
    }

    private void evaluarMano(Equipo equipo){
        switch (ronda.getResultados().size()){
            case 0: ronda.resultadoMano(equipo); return;
            case 1: {
                if (ronda.getResultados().contains(null) || ronda.getResultados().contains(equipo))
                    if (equipo != null) {
                        equipo.sumarPuntos(ronda.getTrucoEnJuego().getPuntaje());
                        ronda.terminar();
                        return;
                    }
                ronda.resultadoMano(equipo);
                return;
            }
            case 2: {if(equipo==null) this.getJugadorMano().getEquipo().sumarPuntos(ronda.getTrucoEnJuego().getPuntaje());
                    else equipo.sumarPuntos(ronda.getTrucoEnJuego().getPuntaje());
                    ronda.terminar();
            }
        }
    }

}
