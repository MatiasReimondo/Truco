package truco.modelo;
import truco.modelo.envido.Envido;
import truco.modelo.excepciones.*;
import truco.modelo.flor.Flor;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    /**ATRIBUTOS**/
    private String nombre;
    private List<Carta> mano;
    private Mesa mesa;
    private Equipo equipo;
    static final int MAXIMO_CARTAS= 3;

    /**CONSTRUCTOR**/
    public Jugador(String unNombre){
        this.nombre =unNombre;
        this.mano =new ArrayList<>();
    }

    /**SETTERS**/
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setEquipo(Equipo equipo){
        this.equipo=equipo;
    }

    public void setMesa(Mesa mesa){
        this.mesa=mesa;
    }

    /**GETTERS**/
    public String getNombre(){
        return this.nombre;
    }

    public Equipo getEquipo(){
        return this.equipo;
    }

    public List<Carta> getMano(){
        return this.mano;
    }

    public int getEnvido() {

        int envidoMax=sumarEnvido(mano.get(0), mano.get(1));

        if (sumarEnvido(mano.get(1), mano.get(2))>envidoMax)
            envidoMax=sumarEnvido(mano.get(1), mano.get(2));

        if(sumarEnvido(mano.get(0), mano.get(2))>envidoMax)
            envidoMax=sumarEnvido(mano.get(0), mano.get(2));

        return envidoMax;
    }

    public int getFlor(){
        return 20 + mano.get(0).getValorEnvido() + mano.get(1).getValorEnvido() + mano.get(2).getValorEnvido();
    }

    /**ACCIONES**/
    public void robarCartaDelMazo(){
        if(this.mano.size() >= MAXIMO_CARTAS)
            throw new LimiteDeCartasEnLaManoExcedidoException();
        if(this.mesa.getMazo().getCartas().size()==0)
            throw new MazoSinCartasException();
        this.mano.add(this.mesa.getMazo().getCartas().removeFirst());
    }

    public void jugarCarta(int numero, Palo palo) {

        mesa.getJuez().jugadorPuedeAccionar(this);
        for(Carta carta: mano)
            if(carta.getNumero()==numero && carta.getPalo().equals(palo)){
                mesa.getRonda().agregarCarta(this,carta);
                mano.remove(carta);
                mesa.siguienteJugador();
                return;
            }
      throw new CartaNoEstaEnLaManoException();
    }

    public boolean tieneFlor(){
        return mano.get(0).getPalo().equals(mano.get(1).getPalo()) && mano.get(0).getPalo().equals(mano.get(2).getPalo());
    }

    public boolean quiereMostrarEnvido() {
        return true;
    }

    public void cantarEnvido(Envido envido){
        if(!mesa.getJuez().jugadorPuedeCantarTanto(this))
            throw new JugadorNoHabilitadoParaCantarTanto();
        mesa.getRonda().subirApuestaDelEnvido(envido);
        mesa.siguienteJugador();
    }

    public void quieroEnvido(){
        mesa.getRonda().cambiarTantoEnJuego();
    }

    public void noQuieroEnvido(){
        mesa.siguienteJugador();
        mesa.getJugadorActivo().getEquipo().sumarPuntos(mesa.getRonda().getTantoEnJuego().getPuntos(this.getEquipo(),mesa.getJugadorActivo().getEquipo()));
        mesa.jugadorAnterior();
    }

    public void cantarTruco(){
        mesa.getJuez().jugadorPuedeAccionar(this);
        mesa.setEstadoTruco(mesa.getEstadoTruco().cantarTruco());
        mesa.siguienteJugador();
    }

    public void cantarRetruco(){
        mesa.getJuez().jugadorPuedeAccionar(this);
        mesa.setEstadoTruco(mesa.getEstadoTruco().cantarRetruco());
        mesa.siguienteJugador();
    }

    public void cantarValeCuatro(){
        mesa.getJuez().jugadorPuedeAccionar(this);
        mesa.setEstadoTruco(mesa.getEstadoTruco().cantarValecuatro());
        mesa.siguienteJugador();
    }

    public void quieroTruco() {
        mesa.getJuez().jugadorPuedeAccionar(this);
        mesa.setEstadoTruco(mesa.getEstadoTruco().quiero());
    }

    public void noQuieroTruco(){
        this.mesa.noQuiero(this.getEquipo());
    }

    public void cantarFlor(Flor flor) {
        if (mesa.getConFlor().equals(false)) {
            throw new NoSeJuegaConFlorException();
        }
        if (this.tieneFlor() == false) {
            throw new ElJugadorNoTieneFlorException();
        } else {
            mesa.getRonda().activarFlor(flor);

        }
    }

    /**AUXILIARES**/
    public void levantarCarta(Carta unaCarta) throws LimiteDeCartasEnLaManoExcedidoException {
        if(this.mano.size() >=  MAXIMO_CARTAS)
            throw new LimiteDeCartasEnLaManoExcedidoException();
        this.mano.add(unaCarta);
    }

    private int sumarEnvido(Carta carta1, Carta carta2){
        if(carta1.getPalo().equals(carta2.getPalo()))
            return (carta1.getValorEnvido()+carta2.getValorEnvido()+20);
        return Math.max(carta1.getValorEnvido(),carta2.getValorEnvido());
    }



}
