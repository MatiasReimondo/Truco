package fiuba.algo3.modelo;


public class Carta {



    private String  palo;
    private int numero;
    public int fuerza;


    public void setPalo(String palo) {
        this.palo = palo;
    }

    public String getPalo() {
        return palo;
    }

    public int getFuerza(){
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }




}
