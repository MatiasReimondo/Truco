package truco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private List<Carta> manoDeCartas=new ArrayList<>();
    static final int MAXIMO_CARTAS= 3;

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
