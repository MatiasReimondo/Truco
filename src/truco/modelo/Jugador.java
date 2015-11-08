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
        manoDeCartas=new ArrayList<>();
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public List<Carta> getMano(){
        return manoDeCartas;
    }

    public void robarCarta(Carta unaCarta){
        if (manoDeCartas.size()<MAXIMO_CARTAS)
            manoDeCartas.add(unaCarta);
        else
            throw new LimiteDeCartasExcedidoException();
    }

    public Carta jugarCarta(Numero numero,Palo palo){
        for(Carta carta:manoDeCartas)
            if(carta.getPalo().equals(palo) && carta.getNumero().equals(numero)) {
                Carta cartaJugada = carta;
                manoDeCartas.remove(carta);
                return cartaJugada;
            }
        throw new CartaNoEstaEnLaManoException();
    }

    public int getEnvido() {
        Boolean hayEnvido = false;
        int puntajeEnvido1 = 0;
        int puntajeEnvido2 = 0;
        int puntajeEnvido3 = 0;

        if (manoDeCartas.get(0).getPalo().equals(manoDeCartas.get(1).getPalo())) {
            puntajeEnvido1 = 20 + manoDeCartas.get(0).getPuntosEnvido() + manoDeCartas.get(1).getPuntosEnvido();
        }
        if (manoDeCartas.get(0).getPalo().equals(manoDeCartas.get(2).getPalo())) {
            puntajeEnvido2 = 20 + manoDeCartas.get(0).getPuntosEnvido() + manoDeCartas.get(2).getPuntosEnvido();
        }
        if (manoDeCartas.get(1).getPalo().equals(manoDeCartas.get(2).getPalo())) {
            puntajeEnvido3 = 20 + manoDeCartas.get(1).getPuntosEnvido() + manoDeCartas.get(2).getPuntosEnvido();
        }

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



}
