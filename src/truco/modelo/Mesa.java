package truco.modelo;

import truco.modelo.excepciones.ListaJugadoresVaciaException;

import java.util.*;


public class Mesa {

    private Mazo mazo;
    private Ronda rondaActual;
    private List<Ronda> historial;
    private List<Jugador> listaJugadores;
    private Boolean conFlor= false;

    private Iterator<Jugador> iterMano;
    private Iterator<Jugador> iterPie;

    private Jugador jugadorMano;
    private Jugador jugadorPie;
    private Jugador jugadorActivo;

    /**CONSTRUCTOR**/
    public Mesa() {
        mazo = new Mazo();
        historial=new ArrayList<>();

    }

    /**SETTERS**/
    public void setJugadores(List<Jugador> listaJugadores) {
        if(listaJugadores.isEmpty()) throw new ListaJugadoresVaciaException();

        this.listaJugadores=listaJugadores;

        iterMano =this.listaJugadores.iterator();
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

    public Jugador getJugadorMano(){
        return jugadorMano;
    }

    public Jugador getJugadorPie(){
        return jugadorPie;
    }

    public Ronda getRondaActual(){
        return rondaActual;
    }

    public Boolean getConFlor(){
        return conFlor;
    }

    /**ACCIONES**/
    public boolean jugadorEsPie(Jugador jugador){
        return jugador.getNombre().equals(jugadorPie.getNombre());
    }

    public boolean jugadorEsMano(Jugador jugador){
        return jugador.getNombre().equals(jugadorMano.getNombre());
    }

    public boolean esTurnoDelJugador(Jugador jugador){
        return jugador==jugadorActivo;
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
            for(int j=0;j<listaJugadores.size();j++)
                this.siguienteJugadorMano().robarCartaDelMazo();

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

    public void resolverEnvido(){

        int envidoMax=0;
        Equipo equipoGanador=null;
        Equipo equipoPerdedor=null;
        for(int i=0;i<listaJugadores.size();i++){
            if(jugadorActivo.quiereMostrarEnvido())
                if(jugadorActivo.getEnvido()>envidoMax) {
                    if (jugadorActivo.getEquipo() != equipoGanador)
                        equipoPerdedor = equipoGanador;
                    equipoGanador = jugadorActivo.getEquipo();
                }
            jugadorActivo=this.siguienteJugadorMano();
        }
        if(equipoGanador!=null && equipoPerdedor!=null) //Esta linea solo está para que Java no reclame que los equipos pueden ser NULL.
         equipoGanador.sumarPuntos(this.rondaActual.getTantoActivo().getPuntos(equipoGanador,equipoPerdedor));
    }

    public void resolverTruco(){}

    public void agregarCarta(Carta carta, Jugador unJugador) {
        rondaActual.agregarCarta(unJugador,carta);

    }

    public void actualizarJugadorManoPie(){
        if(listaJugadores.isEmpty()) throw new ListaJugadoresVaciaException();

        jugadorMano=this.siguienteJugadorMano();
        //jugadorPie=this.siguienteJugador(iterPie);
    }

    public void nuevaRonda(){
        historial.add(rondaActual);
        rondaActual=new Ronda();

        mazo=new Mazo();
        actualizarJugadorManoPie();
    }

    /**AUXILIARES**/
    private Jugador siguienteJugadorMano(){
        if (this.iterMano.hasNext()){
            return this.iterMano.next();
        }
        this.iterMano = this.listaJugadores.iterator();
        return this.iterMano.next();
       /* if(iterator.hasNext()){
            return iterator.next();}

        iterator =this.listaJugadores.iterator();
        return iterator.next();*/
    }
    public boolean jugadorPuedeCantarTanto(Jugador jugador) {
        if(jugadorEsPie(jugadorActivo) && rondaActual.getTantoActivo()==null)
            return true;
        if(!jugadorEsPie(jugadorActivo) && rondaActual.getTantoActivo()!=null)
            return true;
        return false;
    }

    public void setSeJuegaConFlor(){
        conFlor= true;
    }
}
