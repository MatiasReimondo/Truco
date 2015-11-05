package truco.modelo;


public class Carta {

    private Palo  palo;
    private Numero numero;
    public int fuerza;


    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getFuerza(){

        return fuerza;
    }

    public void setFuerza(int fuerza) {

        this.fuerza = fuerza;
    }


    public Numero getNumero() {

        return numero;
    }

    public void setNumero(Numero numero) {

        this.numero = numero;
    }

    public Carta(Numero unNumero, Palo unPalo){

        this.palo = unPalo;
        this.numero= unNumero;
        determinarFuerza();


    }

    private void determinarFuerza(){

        if (numero.equals(Numero.UNO)&& palo.equals(Palo.ESPADA)  ){fuerza= 14; }

        if (numero.equals(Numero.UNO)&& palo.equals(Palo.BASTO)  ){fuerza= 13; }

        if (numero.equals(Numero.SIETE)&& palo.equals(Palo.ESPADA)  ){fuerza= 12; }

        if (numero.equals(Numero.SIETE)&& palo.equals(Palo.ORO)  ){fuerza= 11; }

        if (numero.equals(Numero.TRES)){fuerza= 10; }

        if (numero.equals(Numero.DOS) ){fuerza= 9; }

        if (numero.equals(Numero.UNO)){fuerza= 8; }

        if (numero.equals(Numero.DOCE) ){fuerza= 7; }

        if (numero.equals(Numero.ONCE) ){fuerza= 6; }

        if (numero.equals(Numero.DIEZ) ){fuerza= 5; }

        if (numero.equals(Numero.SIETE) ){fuerza= 4; }

        if (numero.equals(Numero.SEIS)){fuerza= 3; }

        if (numero.equals(Numero.CINCO)){fuerza= 2; }

        if (numero.equals(Numero.CUATRO)){fuerza= 1; }

    }






}
