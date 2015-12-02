package truco.modelo;
import truco.modelo.IA.Comportamiento;
import truco.modelo.envido.EnvidoNoCantado;
import truco.modelo.excepciones.*;

import java.util.*;

public class Mesa {

    /**ATRIBUTOS**/
    private Mazo mazo;
    private Ronda ronda;
    private List<Jugador> jugadores;
    private int nroJugadores;
    private Jugador jugadorActivo;
    private Jugador jugadorEnEspera;
    private final Arbitro arbitro;
    private int posicionador;
    private Comportamiento IA;
    private boolean jugadorVsIA;

    /**CONSTRUCTOR**/
    public Mesa() {
        mazo = new Mazo();
        arbitro =new Arbitro();
        arbitro.setMesa(this);
        posicionador=0;
    }

    /**SETTERS**/
    public void setJugadores(List<Jugador> listaJugadores) {

        arbitro.evaluarListaDeJugadores(listaJugadores);

        this.jugadores =listaJugadores;
        nroJugadores =listaJugadores.size();
    }

    public void setJugadorEnEspera(Jugador jugador){
        jugadorEnEspera=jugador;
    }

    public void setJugadorActivo(Jugador jugadorActivo){
        while(this.jugadorActivo!=jugadorActivo)
            this.siguienteJugador();
    }

    public void setIA(Comportamiento comportamiento){
        IA=comportamiento;
        IA.setMesa(this);
        jugadorVsIA=true;
    }

    public void setJugadorIA(Jugador jugador){
        IA.setJugador(jugador);
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

    public Jugador getJugadorEnEspera(){
        return jugadorEnEspera;
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

    public Comportamiento getIA(){
        return IA;
    }

    /**ACCIONES**/
    public void repartirCartas(){
        for(Jugador jugador: jugadores)
            for(int i=0;i<3;i++)
                jugador.robarCartaDelMazo();
    }

    public void resolverEnvido(){

        if(ronda.termino())
            throw new RondaTerminadaException();
        if(ronda.getEnvido().getClass().equals(EnvidoNoCantado.class))
            throw new EnvidoNoCantadoException();
        int envidoMax=0;
        Jugador auxiliar=jugadorActivo;
        Equipo equipoGanador=this.getJugadorMano().getEquipo();
        Equipo equipoPerdedor=jugadores.get(nroJugadores-1).getEquipo();

        for(Jugador jugador:jugadores){
            jugadorActivo=jugador;
            if (jugadorActivo.quiereMostrarEnvido())
                if (jugadorActivo.getEnvido() > envidoMax) {
                    envidoMax = jugadorActivo.getEnvido();
                    if (jugadorActivo.getEquipo() != equipoGanador) {
                        equipoPerdedor = equipoGanador;
                        equipoGanador = jugadorActivo.getEquipo();
                    }
            }
        }
        equipoGanador.sumarPuntos(this.ronda.getEnvido().getPuntos(equipoGanador, equipoPerdedor));
        jugadorActivo=auxiliar;
        ronda.envidoTerminado();

    }

    public void resolverFlor(){

        if(ronda.termino())
            throw new RondaTerminadaException();
        if(ronda.getFlor()==null)
            throw new FlorNoCantadaException();
        int florMax=0;
        Jugador auxiliar=jugadorActivo;
        this.jugadorActivo=this.getJugadorMano();
        Equipo equipoGanador=this.getJugadorMano().getEquipo();

        for(Jugador jugador:jugadores){
            jugadorActivo=jugador;
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
        jugadorActivo=auxiliar;
        equipoGanador.sumarPuntos(ronda.getFlor().getPuntos());
    }

    public void resolverMano() {
        if(ronda.termino())
            throw new RondaTerminadaException();

        if(ronda.getManoEnJuego().size()<nroJugadores)
            throw new CartasInsuficientesEnLaMesaException();
        int maxFza = 0;
        Equipo equipoGanador=null;
        Jugador jugadorGanador=null;

        for (Map.Entry<Jugador, Carta> parJugadorCarta : ronda.getManoEnJuego().entrySet() )
            if (parJugadorCarta.getValue().getFuerza() > maxFza) {
                maxFza = parJugadorCarta.getValue().getFuerza();
                equipoGanador = parJugadorCarta.getKey().getEquipo();
                jugadorGanador=parJugadorCarta.getKey();
            }
             else if (parJugadorCarta.getValue().getFuerza() == maxFza)
                if(!parJugadorCarta.getKey().getEquipo().equals(equipoGanador)) {
                    equipoGanador = null;
                    jugadorGanador=null;
                }

        if(jugadorGanador!=null)
            this.setJugadorActivo(jugadorGanador);

        this.evaluarMano(equipoGanador);

        if(!ronda.termino())
            ronda.avanzarALaSiguienteMano();
    }

    public void nuevaRonda(){
        ronda =new Ronda(this);
        mazo=new Mazo();
        mazo.mezclar();
        jugadorActivo=this.jugadores.get(0);
        posicionador=0;
        jugadorEnEspera=null;

        if(jugadorVsIA)
            IA=IA.reiniciar();

        jugadores.forEach(truco.modelo.Jugador::dejarCartas);
    }

    /**AUXILIARES**/

    public Equipo getEquipoOponente(){
        siguienteJugador();
        Equipo equipo=jugadorActivo.getEquipo();
        jugadorAnterior();
        return equipo;

    }

    public void siguienteJugador(){

        if(posicionador==nroJugadores-1)
            posicionador=-1;
        jugadorActivo=jugadores.get(++posicionador);

    }

    public void jugadorAnterior() {

        if(posicionador==0)
            posicionador=nroJugadores;
        jugadorActivo=jugadores.get(--posicionador);
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
                        equipo.sumarPuntos(ronda.getTruco().getPuntaje());
                        ronda.terminar();
                        return;
                    }
                ronda.resultadoMano(equipo);
                return;
            }
            case 2: {if(equipo==null) this.getJugadorMano().getEquipo().sumarPuntos(ronda.getTruco().getPuntaje());
                    else equipo.sumarPuntos(ronda.getTruco().getPuntaje());
                    ronda.terminar();
            }
        }
    }

}
