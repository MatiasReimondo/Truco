package fiuba.algo3.modelo;


import java.util.LinkedList;

public class Mazo {

    private LinkedList<Carta> mazoDeCartas= new LinkedList<Carta>();

    public LinkedList<Carta> getMazoDeCartas() {
        return mazoDeCartas;
    }

    public void setMazoDeCartas(LinkedList<Carta> mazoDeCartas) {
        this.mazoDeCartas = mazoDeCartas;
    }


    public Mazo(){
        for (Numero numero: Numero.values()) {
            for(Palo palo: Palo.values()){
                Carta nuevaCarta= new Carta(numero,palo);
                mazoDeCartas.add(nuevaCarta);


            }

        }

    }

}