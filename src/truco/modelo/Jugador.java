package truco.modelo;

import truco.modelo.envido.Envido;
import truco.modelo.estadosTruco.*;
import truco.modelo.excepciones.*;
import truco.modelo.flor.Flor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Jugador {

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
        return equipo;
    }

    public List<Carta> getMano(){
        return this.mano;
    }

    /**ACCIONES**/
    public void levantarCarta(Carta unaCarta) throws LimiteDeCartasEnLaManoExcedidoException {
        if(this.mano.size() >=  MAXIMO_CARTAS)
            throw new LimiteDeCartasEnLaManoExcedidoException();
        this.mano.add(unaCarta);
    }

    public void robarCartaDelMazo(){
        if(this.mano.size() >= MAXIMO_CARTAS)
            throw new LimiteDeCartasEnLaManoExcedidoException();
        if(mesa.getMazo().getCartas().size()==0)
            throw new MazoSinCartasException();
        mano.add(mesa.getMazo().getCartas().removeFirst());
    }

    public void jugarCarta(int numero, Palo palo) {
        for(Carta carta:mano)
            if(carta.getNumero()==numero && carta.getPalo().equals(palo)) {
                mesa.getRonda().agregarCarta(this,carta);
                mano.remove(carta);
                return;
            }
        throw new CartaNoEstaEnLaManoException();

    }

    public int getEnvido() {

        int envidoMax=sumarEnvido(mano.get(0), mano.get(1));

        if (sumarEnvido(mano.get(1), mano.get(2))>envidoMax)
            envidoMax=sumarEnvido(mano.get(1), mano.get(2));

        if(sumarEnvido(mano.get(0), mano.get(2))>envidoMax)
            envidoMax=sumarEnvido(mano.get(0), mano.get(2));

        return envidoMax;
    }

    public boolean tieneFlor(){
        return mano.get(0).getPalo().equals(mano.get(1).getPalo()) && mano.get(0).getPalo().equals(mano.get(2).getPalo());
    }

    public int getFlor(){
        return 20 + mano.get(0).getValorEnvido() + mano.get(1).getValorEnvido() + mano.get(2).getValorEnvido();
    }

    public boolean quiereMostrarEnvido() {
        return true;
    }

    public void cantarTanto(Envido envido){

        if(mesa.getJuez().jugadorPuedeCantarTanto(this)) {
            mesa.getRonda().activarTanto(envido);
        }else{
            throw new JugadorNoHabilitadoParaCantarTanto();
        }
    }
/*
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
*/
    public void cantarTruco(){
        if(mesa.getEstadoTruco().avanzarEstado().getClass().equals(new TrucoCantado().getClass())) {
            mesa.setEstadoTruco(mesa.getEstadoTruco().avanzarEstado());
        }else{
            throw new NoSePuedeCantarAhoraException();
        }
    }

    public void cantarRetruco(){
        if(mesa.getEstadoTruco().avanzarEstado().getClass().equals(new RetrucoCantado().getClass())) {
            mesa.setEstadoTruco(mesa.getEstadoTruco().avanzarEstado());
        }else{
            throw new NoSePuedeCantarAhoraException();
        }
    }

    public void cantarValeCuatro(){
        if(mesa.getEstadoTruco().avanzarEstado().getClass().equals(new ValeCuatroCantado().getClass())) {
            mesa.setEstadoTruco(mesa.getEstadoTruco().avanzarEstado());
        }else{
            throw new NoSePuedeCantarAhoraException();
        }
    }

    public void quiero() {
        if (mesa.getEstadoTruco().avanzarEstado().getClass().equals(new TrucoQuerido().getClass()) ||
                mesa.getEstadoTruco().avanzarEstado().getClass().equals(new RetrucoQuerido().getClass()) ||
                mesa.getEstadoTruco().avanzarEstado().getClass().equals(new ValeCuatroQuerido().getClass())) {
            mesa.setEstadoTruco(mesa.getEstadoTruco().avanzarEstado());
        } else {
            throw new NoSePuedeCantarAhoraException();
        }
    }

    public void noQuiero(){

    }


    /**AUXILIARES**/
    private int sumarEnvido(Carta carta1, Carta carta2){
        if(carta1.getPalo().equals(carta2.getPalo()))
            return (carta1.getValorEnvido()+carta2.getValorEnvido()+20);
        return Math.max(carta1.getValorEnvido(),carta2.getValorEnvido());
    }



}
