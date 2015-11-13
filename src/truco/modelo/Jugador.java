package truco.modelo;

import truco.modelo.enumerables.Palo;
import truco.modelo.excepciones.CartaNoEstaEnLaManoException;
import truco.modelo.excepciones.LimiteDeCartasExcedidoException;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Jugador {

    private String nombre;
    private List<Carta> manoDeCartas;
    private Mesa mesa;
    private Equipo equipo;
    static final int MAXIMO_CARTAS= 3;

    public Jugador(){
        this.manoDeCartas=new ArrayList<>();
    }

    public Jugador(String unNombre, Equipo unEquipo){
        this.nombre =unNombre;
        this.equipo= unEquipo;
        this.manoDeCartas=new ArrayList<>();
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setEquipo(Equipo equipo){
        this.equipo=equipo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Equipo getEquipo(){
        return equipo;
    }
    public List<Carta> getMano(){
        return this.manoDeCartas;
    }

    public void robarCarta(Carta unaCarta) throws LimiteDeCartasExcedidoException{
        if(this.manoDeCartas.size() >= MAXIMO_CARTAS){
            throw new LimiteDeCartasExcedidoException();
        }
        //Se agrega una carta
        this.manoDeCartas.add(unaCarta);
    }

    public Carta jugarCarta(int numero,Palo palo) {

        for(Carta carta: this.manoDeCartas)
            if ( carta.equals(new Carta(numero, palo))){
                Carta cartaJugada = carta;
                this.manoDeCartas.remove(carta);
                return cartaJugada;
            }

        throw new CartaNoEstaEnLaManoException();
    }

    private int sumarEnvido(Carta carta1, Carta carta2){
        if(carta1.getPalo().equals(carta2.getPalo()))
            return (carta1.getValorEnvido()+carta2.getValorEnvido()+20);
        return Math.max(carta1.getValorEnvido(),carta2.getValorEnvido());
    }

    public int getEnvido() {

        int envidoMax=sumarEnvido(manoDeCartas.get(0),manoDeCartas.get(1));

        if (sumarEnvido(manoDeCartas.get(1),manoDeCartas.get(2))>envidoMax)
            envidoMax=sumarEnvido(manoDeCartas.get(1),manoDeCartas.get(2));

        if(sumarEnvido(manoDeCartas.get(0),manoDeCartas.get(2))>envidoMax)
            envidoMax=sumarEnvido(manoDeCartas.get(0),manoDeCartas.get(2));

        return envidoMax;
    }

    public boolean tieneFlor(){
        return manoDeCartas.get(0).getPalo().equals(manoDeCartas.get(1).getPalo()) && manoDeCartas.get(0).getPalo().equals(manoDeCartas.get(2).getPalo());
    }

    public int getFlor(){
        return 20 + manoDeCartas.get(0).getValorEnvido() + manoDeCartas.get(1).getValorEnvido() + manoDeCartas.get(2).getValorEnvido();
    }





}
