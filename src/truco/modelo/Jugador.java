package truco.modelo;
import truco.modelo.envido.Envido;
import truco.modelo.envido.EnvidoNoCantado;
import truco.modelo.excepciones.*;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    /**ATRIBUTOS**/
    private String nombre;
    private List<Carta> mano;
    private Mesa mesa;
    private Equipo equipo;
    private static final int MAXIMO_CARTAS= 3;

    /**CONSTRUCTOR**/
    public Jugador(){}
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
            throw new ManoExcedidaEnCartasException();
        if(this.mesa.getMazo().getCartas().size()==0)
            throw new MazoSinCartasException();
        this.mano.add(this.mesa.getMazo().getCartas().removeFirst());
    }

    public void jugarCarta(int numero, Palo palo) {

        mesa.getArbitro().jugadorPuedeAccionar(this);
        for(Carta carta: mano)
            if(carta.getNumero()==numero && carta.getPalo().equals(palo)){
                mesa.getRonda().agregarCarta(this,carta);
                mesa.siguienteJugador();
                return;
            }
      throw new CartaNoEncontradaException();
    }

    public boolean tieneFlor() {
        return mano.size() >= 3 && mano.get(0).getPalo().equals(mano.get(1).getPalo()) && mano.get(0).getPalo().equals(mano.get(2).getPalo());
    }

    public void irseAlMazo(){
        if(mesa.getRonda().seEstaJugandoLaPrimera() && mesa.getRonda().getTantoPendiente().getClass().equals(EnvidoNoCantado.class) && mesa.getRonda().getFlor()==null){
            mesa.siguienteJugador();
            mesa.getJugadorActivo().getEquipo().sumarPuntos(2);
            mesa.jugadorAnterior();
            mesa.getRonda().terminar();
        }
        else{
            mesa.siguienteJugador();
            mesa.getJugadorActivo().getEquipo().sumarPuntos(1);
            mesa.jugadorAnterior();
            mesa.getRonda().terminar();
        }
    }

    /**ENVIDO**/
    public boolean quiereMostrarEnvido() {
        return true;
    }

    public void cantarEnvido(Envido envido){
        mesa.getArbitro().jugadorPuedeCantarTanto(this);
        if(mesa.getJugadorEnEspera()==null) {
            mesa.siguienteJugador();
            mesa.setJugadorEnEspera(this);
            mesa.getRonda().subirApuestaDelEnvido(envido);
            return;
        }
        if(mesa.getJugadorEnEspera().equals(this))
            mesa.siguienteJugador();
        else
            mesa.jugadorAnterior();

        mesa.getRonda().subirApuestaDelEnvido(envido);
    }

    public void quieroEnvido(){
        aceptarEnvido();
        mesa.setJugadorActivo(mesa.getJugadorEnEspera());
        mesa.setJugadorEnEspera(null);
    }
    public void aceptarEnvido(){
        mesa.getRonda().cambiarTantoEnJuego();
    }

    public void noQuieroEnvido(){
        mesa.getEquipoOponente().sumarPuntos(mesa.getRonda().getTantoEnJuego().getPuntos(this.getEquipo(), mesa.getJugadorActivo().getEquipo()));
        mesa.setJugadorActivo(mesa.getJugadorEnEspera());
        mesa.setJugadorEnEspera(null);
    }

    /**TRUCO**/
    public void cantarTruco(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getRonda().setTrucoEnJuego(mesa.getRonda().getTruco().cantarTruco());
        mesa.siguienteJugador();
    }

    public void cantarRetruco(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getRonda().setTrucoEnJuego(mesa.getRonda().getTruco().cantarRetruco());
        mesa.jugadorAnterior();
    }

    public void cantarValeCuatro(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getRonda().setTrucoEnJuego(mesa.getRonda().getTruco().cantarValecuatro());
        mesa.jugadorAnterior();
    }

    public void quieroTruco() {
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getRonda().setTrucoEnJuego(mesa.getRonda().getTruco().quiero());
    }

    public void noQuieroTruco(){
        mesa.siguienteJugador();
        mesa.getJugadorActivo().getEquipo().sumarPuntos(mesa.getRonda().getTruco().getPuntaje());
        mesa.jugadorAnterior();
        mesa.getRonda().terminar();
    }

    /**FLOR**/
    public void cantarFlor() {
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getArbitro().seJuegaConFlor();
        mesa.getArbitro().jugadorPuedeCantarFlor();
        if(!this.tieneFlor()) throw new JugadorNoTieneFlorException();

        mesa.getRonda().activarFlor();

        mesa.siguienteJugador();
        if(!mesa.getJugadorActivo().tieneFlor()){
            mesa.jugadorAnterior();
            mesa.getJugadorActivo().getEquipo().sumarPuntos(mesa.getRonda().getFlor().getPuntos());
        }

    }

    public void cantarContraFlor(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getArbitro().seJuegaConFlor();
        mesa.getArbitro().jugadorPuedeCantarContraflor();
        mesa.getRonda().getFlor().contraflor();
        mesa.siguienteJugador();
    }

    public void cantarContraflorAlResto(){
        mesa.getArbitro().jugadorPuedeAccionar(this);
        mesa.getArbitro().seJuegaConFlor();
        mesa.getArbitro().jugadorPuedeCantarContraflor();
        mesa.getRonda().getFlor().contraflorAlResto();
        mesa.siguienteJugador();
    }

    public void quieroFlor(){
        if(!this.tieneFlor())
            throw new JugadorNoTieneFlorException();
    }

    /**AUXILIARES**/
    public void levantarCarta(Carta unaCarta) throws ManoExcedidaEnCartasException {
        if(this.mano.size() >=  MAXIMO_CARTAS)
            throw new ManoExcedidaEnCartasException();
        this.mano.add(unaCarta);
    }

    public int getFuerzaTotal(){
        int suma=0;
        for(Carta carta:mano)
            suma=suma+carta.getFuerza();
        return suma;
    }

    private int sumarEnvido(Carta carta1, Carta carta2){
        if(carta1.getPalo().equals(carta2.getPalo()))
            return (carta1.getValorEnvido()+carta2.getValorEnvido()+20);
        return Math.max(carta1.getValorEnvido(),carta2.getValorEnvido());
    }



}
