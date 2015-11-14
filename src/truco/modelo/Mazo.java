package truco.modelo;

import java.util.Collections;
import java.util.LinkedList;



@SuppressWarnings("ALL")
public class Mazo {

    private LinkedList<Carta> mazoDeCartas;
    static final int MAXIMO_CARTAS= 3;
    static final int TAMANIO_MAZO= 40;

    public Mazo(){
        mazoDeCartas=new LinkedList<>();
        for (int numero=1;numero<8;numero++)
            for(Palo palo: Palo.values()){
                Carta nuevaCarta= new Carta(numero,palo);
                mazoDeCartas.add(nuevaCarta);
            }
        for (int numero=10;numero<13;numero++)
            for(Palo palo: Palo.values()){
                Carta nuevaCarta= new Carta(numero,palo);
                mazoDeCartas.add(nuevaCarta);
            }
    }

    public LinkedList<Carta> getCartas(){
        return mazoDeCartas;
    }

    public int cantidadDeCartas(){
        return mazoDeCartas.size();
    }
    public void mezclar(){
        Collections.shuffle(mazoDeCartas);

    }



}