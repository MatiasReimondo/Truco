package truco.modelo;

import truco.modelo.estadosTruco.EstadoTruco;
import truco.modelo.estadosTruco.TrucoNoCantado;
import truco.modelo.excepciones.ListaJugadoresVaciaException;

import java.util.*;

public class Mesa {

    private Mazo mazo;
    private Ronda ronda;
    private List<Jugador> jugadores;
    private int nroJugadores;
    private Boolean conFlor= false;
    private Iterator<Jugador> iterJugadorActivo;
    private Jugador jugadorActivo;
    private EstadoTruco estadoTruco;
    private Juez juez;

    /**CONSTRUCTOR**/
    public Mesa() {
        mazo = new Mazo();
        juez=new Juez();
        juez.setMesa(this);
    }

    /**SETTERS**/
    public void setJugadores(List<Jugador> listaJugadores) {

        juez.evaluarListaDeJugadores(listaJugadores);

        this.jugadores =listaJugadores;
        nroJugadores =listaJugadores.size();

        iterJugadorActivo = jugadores.iterator();
        iterJugadorActivo.next();
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
        Equipo equipoGanador=null;
        Equipo equipoPerdedor=null;
        for(int i=0;i< jugadores.size();i++){
            if(jugadorActivo.quiereMostrarEnvido())                      //Pregunta al jugador si quiere cantar su envido
                if(jugadorActivo.getEnvido()>envidoMax) {                //Evalua si el envido del jugador es mayor al maximo actual
                    if (jugadorActivo.getEquipo() != equipoGanador)      //Si el envido es mayor, verifica si el equipo ganador actual es el mismo que el que pertenece el jugador
                        equipoPerdedor = equipoGanador;                  //Si la linea previa es verdadera, el viejo equipo ganador ahora es el nuevo equipo perdedor
                    equipoGanador = jugadorActivo.getEquipo();
                }
            jugadorActivo=this.siguienteJugador(iterJugadorActivo);
        }
        if(equipoGanador!=null && equipoPerdedor!=null) //Esta linea solo está para que Java no reclame que los equipos pueden ser NULL.
            equipoGanador.sumarPuntos(this.ronda.getTantoActivo().getPuntos(equipoGanador, equipoPerdedor));
    }

    public void resolverTruco(){}

    public List<Jugador> resolverMano() {
        int maxFuerza = 0;
        int fuerzaEmpate = 0;
        Jugador jugadorMax = null;
        Jugador jugadorEmpate = null;
        List<Jugador> ganadores = new LinkedList<>();


        for (Map.Entry<Jugador, Carta> item : ronda.getManoActual().entrySet() ) {
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

    public void nuevaRonda(){
        ronda =new Ronda();
        mazo=new Mazo();
        mazo.mezclar();
        estadoTruco= new TrucoNoCantado();
        actualizarJugadorManoPie();
        jugadorActivo=this.getJugadorMano();
    }

    /**AUXILIARES**/
    public void actualizarJugadorManoPie(){
        if(jugadores.isEmpty()) throw new ListaJugadoresVaciaException();
        jugadores.add(jugadores.remove(0));
    }

    private Jugador siguienteJugador(Iterator<Jugador> iter){
        if (iter.hasNext()){
           return iter.next();
        }
        iter = jugadores.iterator();
        return iter.next();
    }

    public void setSeJuegaConFlor(){
        conFlor= true;
    }


}
