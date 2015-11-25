package truco.modelo;
import truco.modelo.estadosTruco.EstadoTruco;
import truco.modelo.estadosTruco.TrucoNoCantado;
import truco.modelo.excepciones.ListaJugadoresVaciaException;

import java.util.*;

/*********************************************************************************
 * *******************************  Mesa  ****************************************
 * *********************************************************************************/
public class Mesa {

    /*********************** Atributos de la clase ********************************/
    private Mazo mazo;
    private Ronda ronda;
    private List<Jugador> jugadores;
    private int nroJugadores;
    private Boolean conFlor= false;
    private ListIterator<Jugador> iterJugadorActivo;
    private Jugador jugadorActivo;
    private EstadoTruco estadoTruco;
    private Juez juez;

    /********************** Métodos de la clase ***********************************/
    /**CONSTRUCTOR**/
    public Mesa() {
        mazo = new Mazo();
        juez=new Juez();
        juez.setMesa(this);
        ronda=new Ronda();
    }

    /**SETTERS**/
    public void setJugadores(List<Jugador> listaJugadores) {

        juez.evaluarListaDeJugadores(listaJugadores);

        this.jugadores =listaJugadores;
        nroJugadores =listaJugadores.size();
    }

    public void setEstadoTruco(EstadoTruco estadoTruco) {
        this.estadoTruco = estadoTruco;
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

    public Juez getJuez() {
        return juez;
    }

    public Boolean getConFlor(){
        return conFlor;
    }

    public EstadoTruco getEstadoTruco() {
        return estadoTruco;
    }

    /**ACCIONES**/
    public void repartirCartas(){
        for(Jugador jugador: jugadores)
            for(int i=0;i<3;i++)
                jugador.robarCartaDelMazo();
    }

    public void resolverEnvido(){

        int envidoMax=0;
        this.jugadorActivo=this.getJugadorMano();
        Equipo equipoGanador=this.getJugadorMano().getEquipo();
        Equipo equipoPerdedor=jugadores.get(nroJugadores-1).getEquipo();

        for(int i=0;i<nroJugadores;i++) {
            if (jugadorActivo.quiereMostrarEnvido())
                if (jugadorActivo.getEnvido() > envidoMax) {
                    envidoMax = jugadorActivo.getEnvido();
                    if (jugadorActivo.getEquipo() != equipoGanador) {
                        equipoPerdedor = equipoGanador;
                        equipoGanador = jugadorActivo.getEquipo();
                    }
            }
            else if(jugadorActivo.getEnvido()==envidoMax)
                    if(jugadorActivo.getEquipo().equals(this.getJugadorMano().getEquipo()) && !equipoGanador.equals(this.getJugadorMano().getEquipo())) {
                        equipoPerdedor = equipoGanador;
                        equipoGanador = jugadorActivo.getEquipo();
                    }
            this.proximoTurno();
        }
            equipoGanador.sumarPuntos(this.ronda.getTantoActivo().getPuntos(equipoGanador, equipoPerdedor));
    }

    public void resolverTruco(){}

    public void resolverMano() {

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
        ronda =new Ronda();
        mazo=new Mazo();
        mazo.mezclar();
        estadoTruco= new TrucoNoCantado();
        //actualizarJugadorMano();
        jugadorActivo=this.jugadores.get(0);
        iterJugadorActivo= jugadores.listIterator(1);

        for(Jugador jugador:jugadores)
            jugador.getMano().clear();
    }

    public void proximoTurno(){
        if(iterJugadorActivo.hasNext())
            jugadorActivo= iterJugadorActivo.next();
        else{
            iterJugadorActivo=jugadores.listIterator();
            jugadorActivo= iterJugadorActivo.next();
        }
    }

    /**AUXILIARES**/
    public void actualizarJugadorMano(){
        if(jugadores.isEmpty()) throw new ListaJugadoresVaciaException();
        jugadores.add(jugadores.remove(0));
    }

    private void evaluarMano(Equipo equipo){
        switch (ronda.getResultados().size()){
            case 0: ronda.resultadoManoActual(equipo); return;
            case 1: {
                if (ronda.getResultados().contains(null) || ronda.getResultados().contains(equipo))
                    if (equipo != null) {
                        equipo.sumarPuntos(this.estadoTruco.getPuntaje());
                        ronda.terminar();
                        return;
                    }
                ronda.resultadoManoActual(equipo);
                return;
            }
            case 2: if(equipo==null) this.getJugadorMano().getEquipo().sumarPuntos(this.estadoTruco.getPuntaje());
                    else equipo.sumarPuntos(this.estadoTruco.getPuntaje());
        }
    }

    public void setSeJuegaConFlor(){
        conFlor= true;
    }

    public void cambiarEstadoTruco() {
        this.estadoTruco = this.estadoTruco.avanzarEstado(this);
    }
}
