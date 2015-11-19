package truco.modelo;

import truco.modelo.estadosTruco.EstadoTruco;
import truco.modelo.estadosTruco.TrucoNoCantado;
import truco.modelo.excepciones.CantidadDeJugadoresInsuficienteException;
import truco.modelo.excepciones.ListaJugadoresVaciaException;
import truco.modelo.excepciones.MaximoDeJugadoresExcedidoException;

import java.util.*;


public class Mesa {

    private Mazo mazo;
    private Ronda rondaActual;
    private List<Jugador> listaJugadores;
    private int nJugadores;
    private Boolean conFlor= false;
    private Iterator<Jugador> iterMano;
    private Jugador jugadorActivo;
    private EstadoTruco estadoTruco;

    /**CONSTRUCTOR**/
    public Mesa() {
        mazo = new Mazo();
    }

    /**SETTERS**/
    public void setJugadores(List<Jugador> listaJugadores) {
        if(listaJugadores.isEmpty()) throw new ListaJugadoresVaciaException();
        if(listaJugadores.size()<2) throw new CantidadDeJugadoresInsuficienteException();
        if(listaJugadores.size()>6) throw new MaximoDeJugadoresExcedidoException();

        this.listaJugadores=listaJugadores;
        nJugadores=listaJugadores.size();

        iterMano =listaJugadores.iterator();
        iterMano.next();

    }

    public void setEstadoTruco(EstadoTruco estadoTruco) {
        this.estadoTruco = estadoTruco;
    }

    /**GETTERS**/
    public Mazo getMazo(){
        return mazo;
    }

    public List<Jugador> getJugadores() {
        return listaJugadores;
    }

    public Jugador getJugadorMano(){
        return listaJugadores.get(0);
    }

    public Ronda getRondaActual(){
        return rondaActual;
    }

    public Boolean getConFlor(){
        return conFlor;
    }

    public EstadoTruco getEstadoTruco() {
        return estadoTruco;
    }

    /**ACCIONES**/
    public void repartirCartas(){
        for(Jugador jugador:listaJugadores)
            for(int i=0;i<3;i++)
                jugador.robarCartaDelMazo();
    }

    public void resolverEnvido(){

        int envidoMax=0;
        Equipo equipoGanador=null;
        Equipo equipoPerdedor=null;
        for(int i=0;i<listaJugadores.size();i++){
            if(jugadorActivo.quiereMostrarEnvido())                      //Pregunta al jugador si quiere cantar su envido
                if(jugadorActivo.getEnvido()>envidoMax) {                //Evalua si el envido del jugador es mayor al maximo actual
                    if (jugadorActivo.getEquipo() != equipoGanador)      //Si el envido es mayor, verifica si el equipo ganador actual es el mismo que el que pertenece el jugador
                        equipoPerdedor = equipoGanador;                  //Si la linea previa es verdadera, el viejo equipo ganador ahora es el nuevo equipo perdedor
                    equipoGanador = jugadorActivo.getEquipo();
                }
            jugadorActivo=this.siguienteJugador(iterMano);
        }
        if(equipoGanador!=null && equipoPerdedor!=null) //Esta linea solo está para que Java no reclame que los equipos pueden ser NULL.
            equipoGanador.sumarPuntos(this.rondaActual.getTantoActivo().getPuntos(equipoGanador,equipoPerdedor));
    }

    public void resolverTruco(){}

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

    public void nuevaRonda(){
        rondaActual=new Ronda();
        mazo=new Mazo();
        mazo.mezclar();
        estadoTruco= new TrucoNoCantado();
        actualizarJugadorManoPie();
    }

    /**VALIDACIONES**/
    public boolean jugadorEsPie(Jugador jugador){
        switch (nJugadores){
            case 2: return jugador.equals(listaJugadores.get(1));
            case 4: return (jugador.equals(listaJugadores.get(2)) || jugador.equals(listaJugadores.get(3)));
            case 6: return (jugador.equals(listaJugadores.get(4)) || jugador.equals(listaJugadores.get(5)));
        }
        return false;
    }

    public boolean jugadorEsMano(Jugador jugador){
        return jugador.equals(listaJugadores.get(0));
    }

    public boolean esTurnoDelJugador(Jugador jugador){
        return jugador.equals(jugadorActivo);
    }

    public boolean jugadorPuedeCantarTanto(Jugador jugador) {
        if(jugadorEsPie(jugadorActivo) && rondaActual.seEstaJugandoLaPrimera())
            return true;
        if(nJugadores==2 && rondaActual.seEstaJugandoLaPrimera())
            return true;
        return false;
    }

    /**AUXILIARES**/
    public void actualizarJugadorManoPie(){
        if(listaJugadores.isEmpty()) throw new ListaJugadoresVaciaException();
        listaJugadores.add(listaJugadores.remove(0));
    }

    private Jugador siguienteJugador(Iterator<Jugador> iter){
        if (iter.hasNext()){
           return iter.next();
        }
        iter = listaJugadores.iterator();
        return iter.next();
    }

    public void setSeJuegaConFlor(){
        conFlor= true;
    }
}
