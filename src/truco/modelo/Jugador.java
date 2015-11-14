package truco.modelo;

import truco.modelo.excepciones.CartaNoEstaEnLaManoException;
import truco.modelo.excepciones.LimiteDeCartasExcedidoException;

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
    public Jugador(){;}

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
    public void robarCarta(Carta unaCarta) throws LimiteDeCartasExcedidoException{
        if(this.mano.size() >= MAXIMO_CARTAS){
            throw new LimiteDeCartasExcedidoException();
        }
        //Se agrega una carta
        this.mano.add(unaCarta);
    }

    public void jugarCarta(int numero, Palo palo) {

        for(Carta carta:mano)
            if(carta.getNumero()==numero && carta.getPalo().equals(palo)) {
                mesa.agregarCarta(carta);
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

    /**AUXILIARES**/
    private int sumarEnvido(Carta carta1, Carta carta2){
        if(carta1.getPalo().equals(carta2.getPalo()))
            return (carta1.getValorEnvido()+carta2.getValorEnvido()+20);
        return Math.max(carta1.getValorEnvido(),carta2.getValorEnvido());
    }




}
