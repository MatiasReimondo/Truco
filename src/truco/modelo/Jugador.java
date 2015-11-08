package truco.modelo;

import truco.modelo.enumerables.Numero;
import truco.modelo.enumerables.Palo;
import truco.modelo.excepciones.CartaNoEstaEnLaManoException;
import truco.modelo.excepciones.LimiteDeCartasExcedidoException;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String nombre;
    private List<Carta> manoDeCartas;
    static final int MAXIMO_CARTAS= 3;

    public Jugador(){
        this.manoDeCartas=new ArrayList<Carta>();
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){
        return this.nombre;
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
        
       /* if (this.manoDeCartas.size()<MAXIMO_CARTAS)
            this.manoDeCartas.add(unaCarta);
        else
            throw new LimiteDeCartasExcedidoException();*/
    }

    public Carta jugarCarta(Numero numero,Palo palo) throws CartaNoEstaEnLaManoException {
        for(Carta carta: this.manoDeCartas)
            if(carta.getPalo().equals(palo) && carta.getNumero().equals(numero)) {
                Carta cartaJugada = carta;
                this.manoDeCartas.remove(carta);
                return cartaJugada;
            }
        throw new CartaNoEstaEnLaManoException();
    }

    public int getEnvido() {

        int puntajeEnvido1 = 0;
        int puntajeEnvido2 = 0;
        int puntajeEnvido3 = 0;

        if (this.manoDeCartas.get(0).getPalo().equals(this.manoDeCartas.get(1).getPalo())) {
            puntajeEnvido1 = 20 + this.manoDeCartas.get(0).getPuntosEnvido() + this.manoDeCartas.get(1).getPuntosEnvido();
        }
        if (this.manoDeCartas.get(0).getPalo().equals(this.manoDeCartas.get(2).getPalo())) {
            puntajeEnvido2 = 20 + this.manoDeCartas.get(0).getPuntosEnvido() + this.manoDeCartas.get(2).getPuntosEnvido();
        }
        if (manoDeCartas.get(1).getPalo().equals(manoDeCartas.get(2).getPalo())) {
            puntajeEnvido3 = 20 + this.manoDeCartas.get(1).getPuntosEnvido() + this.manoDeCartas.get(2).getPuntosEnvido();
        }
        return devolverPuntajeMasAlto(puntajeEnvido1,puntajeEnvido2,puntajeEnvido3);

    }

    private int devolverPuntajeMasAlto(int puntajeEnvido1, int puntajeEnvido2, int puntajeEnvido3){
        if (puntajeEnvido1 > puntajeEnvido2) {
            if (puntajeEnvido1 > puntajeEnvido3) {
                return puntajeEnvido1;
            } else {
                return puntajeEnvido3;
            }
        }
        if (puntajeEnvido2 > puntajeEnvido3) {
            return puntajeEnvido2;
        } else {
            return puntajeEnvido3;
        }
    }

    public boolean hayFlor(){
        if( manoDeCartas.get(0).getPalo().equals(manoDeCartas.get(1).getPalo()) && manoDeCartas.get(0).getPalo().equals(manoDeCartas.get(2).getPalo())){
            return true;
        }
        else{
            return false;
        }

    }

    public int getFlor(){
        int valorDeFlor;
        valorDeFlor= 20 + manoDeCartas.get(0).getPuntosEnvido() + manoDeCartas.get(1).getPuntosEnvido() + manoDeCartas.get(2).getPuntosEnvido();
        return valorDeFlor;
    }





}
