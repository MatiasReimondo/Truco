package truco.modelo;


import truco.modelo.excepciones.FaltanCartasException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Mazo {

    private LinkedList<Carta> mazoDeCartas;
    static final int MAXIMO_CARTAS= 3;
    static final int TAMANIO_MAZO= 40;

    public Mazo(){
        mazoDeCartas=new LinkedList<>();
        for (Numero numero: Numero.values()) {
            for(Palo palo: Palo.values()){
                Carta nuevaCarta= new Carta(numero,palo);
                mazoDeCartas.add(nuevaCarta);
            }
        }
    }

    public LinkedList<Carta> getCartas(){
        return mazoDeCartas;
    }
    public void mezclarMazo(){
        Collections.shuffle(mazoDeCartas);

    }

    public void repartirCartas(List<Jugador> jugadores){
        for (int i = 0; i <MAXIMO_CARTAS ; i++) {
            for (Jugador jugador: jugadores){
                jugador.robarCarta(mazoDeCartas.removeFirst());
            }

        }
    }
    public void juntarMazo(LinkedList<Carta> cartasMesa)  {
        if(cartasMesa.size()+mazoDeCartas.size()==TAMANIO_MAZO){
            mazoDeCartas.addAll(cartasMesa);
        }
        else {
            throw new FaltanCartasException();
        }

    }



}