package truco.modelo;


import java.util.LinkedList;

public class Jugador {
    private LinkedList<Carta> manoDeCartas=new LinkedList<>();
    static final int MAXIMO_CARTAS= 3;

    public LinkedList<Carta> getManoDeCartas(){
        return manoDeCartas;
    }

    public void recibirCarta(Carta unaCarta){
        if (manoDeCartas.size()<MAXIMO_CARTAS) {
            manoDeCartas.add(unaCarta);
        }
        else{
            try {
                throw new DemasiadasCartasException();
            } catch (DemasiadasCartasException e) {
                e.printStackTrace();
            }
        }
    }

}
