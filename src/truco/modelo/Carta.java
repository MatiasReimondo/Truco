package truco.modelo;


import truco.modelo.enumerables.Numero;
import truco.modelo.enumerables.Palo;

public class Carta {

    private Palo palo;
    private Numero numero;
    public int fuerza;

    public Carta(Numero unNumero, Palo unPalo){

        this.palo = unPalo;
        this.numero= unNumero;
        determinarFuerza();
    }

    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    public void setFuerza(int fuerza) {

        this.fuerza = fuerza;
    }

    public void setNumero(Numero numero) {

        this.numero = numero;
    }

    public Palo getPalo() {
        return palo;
    }

    public Numero getNumero() {

        return numero;
    }

    public int getFuerza(){

        return fuerza;
    }


    private void determinarFuerza(){

        if (numero.equals(Numero.UNO)&& palo.equals(Palo.ESPADA)  ){fuerza= 14; return;}

        if (numero.equals(Numero.UNO)&& palo.equals(Palo.BASTO)  ){fuerza= 13; return;}

        if (numero.equals(Numero.SIETE)&& palo.equals(Palo.ESPADA)  ){fuerza= 12; return;}

        if (numero.equals(Numero.SIETE)&& palo.equals(Palo.ORO)  ){fuerza= 11; return;}

        if (numero.equals(Numero.TRES)){fuerza= 10; return;}

        if (numero.equals(Numero.DOS) ){fuerza= 9; return;}

        if (numero.equals(Numero.UNO)){fuerza= 8; return;}

        if (numero.equals(Numero.DOCE) ){fuerza= 7; return;}

        if (numero.equals(Numero.ONCE) ){fuerza= 6; return;}

        if (numero.equals(Numero.DIEZ) ){fuerza= 5; return;}

        if (numero.equals(Numero.SIETE) ){fuerza= 4; return;}

        if (numero.equals(Numero.SEIS)){fuerza= 3; return;}

        if (numero.equals(Numero.CINCO)){fuerza= 2; return;}

        if (numero.equals(Numero.CUATRO)){fuerza= 1; return;}

    }






}
