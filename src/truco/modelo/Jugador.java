package truco.modelo;

import truco.modelo.excepciones.DemasiadasCartasException;

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
        if (manoDeCartas.size()<MAXIMO_CARTAS) {
            manoDeCartas.add(unaCarta);
        }
        else {
            throw new DemasiadasCartasException();
        }

    }

}
