package truco.modelo;
import truco.modelo.envido.Envido;
import truco.modelo.excepciones.*;

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

        mesa.getArbitro().jugadorPuedeAccionar(this);
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

    /**ENVIDO**/
    public boolean quiereMostrarEnvido() {
        return true;
    }

    public void cantarEnvido(Envido envido){
        mesa.getArbitro().jugadorPuedeCantarTanto(this);
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

    /**TRUCO**/
    public void cantarTruco(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getRonda().setTrucoEnJuego(mesa.getRonda().getTrucoEnJuego().cantarTruco());
        mesa.siguienteJugador();
    }

    public void cantarRetruco(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getRonda().setTrucoEnJuego(mesa.getRonda().getTrucoEnJuego().cantarRetruco());
        mesa.siguienteJugador();
    }

    public void cantarValeCuatro(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getRonda().setTrucoEnJuego(mesa.getRonda().getTrucoEnJuego().cantarValecuatro());
        mesa.siguienteJugador();
    }

    public void quieroTruco() {
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getRonda().setTrucoEnJuego(mesa.getRonda().getTrucoEnJuego().quiero());
    }

    public void noQuieroTruco(){
        this.mesa.noQuiero(this.getEquipo());
    }

    /**FLOR**/
    public void cantarFlor() {
        if(!this.tieneFlor()) throw new ElJugadorNoTieneFlorException();
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getArbitro().seJuegaConFlor();
        mesa.getArbitro().jugadorPuedeCantarFlor(this);
        mesa.getRonda().activarFlor();

        mesa.siguienteJugador();
        if(!mesa.getJugadorActivo().tieneFlor()){
            mesa.jugadorAnterior();
            mesa.getJugadorActivo().getEquipo().sumarPuntos(mesa.getRonda().getFlorEnJuego().getPuntos());
        }
        mesa.siguienteJugador();

    }

    public void cantarContraFlor(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getArbitro().seJuegaConFlor();
        mesa.getArbitro().jugadorPuedeCantarContraflor(this);
        mesa.getRonda().getFlorEnJuego().contraflor();
        mesa.siguienteJugador();
    }

    public void cantarContraflorAlResto(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getArbitro().seJuegaConFlor();
        mesa.getArbitro().jugadorPuedeCantarContraflor(this);
        mesa.getRonda().getFlorEnJuego().contraflorAlResto();
        mesa.siguienteJugador();
    }

    public void quieroFlor(){
        if(!this.tieneFlor())
            throw new NoSePuedeQuererSinTenerFlorException();
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
