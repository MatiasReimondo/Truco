package truco.modelo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private List<Carta> mazoDeCartas;

    public Mazo(){
        mazoDeCartas=new ArrayList<>();
        for (Numero numero: Numero.values()) {
            for(Palo palo: Palo.values()){
                Carta nuevaCarta= new Carta(numero,palo);
                mazoDeCartas.add(nuevaCarta);
            }
        }
    }

    public List<Carta> getCartas(){
        return mazoDeCartas;
    }
    public void mezclarMazo(){
        Collections.shuffle(mazoDeCartas);

    }

}