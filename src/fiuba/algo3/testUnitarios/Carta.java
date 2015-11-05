package fiuba.algo3.testUnitarios;


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
        this.fuerza= this.determinarFuerza(unNumero, unPalo);


    }

    public int determinarFuerza( Numero unNumero, Palo unPalo){
        numero = unNumero;
        palo = unPalo;

        if (unNumero.equals(Numero.UNO)&& unPalo.equals(Palo.ESPADA)  ){return 14; }

        if (unNumero.equals(Numero.UNO)&& unPalo.equals(Palo.BASTO)  ){return 13; }

        if (unNumero.equals(Numero.SIETE)&& unPalo.equals(Palo.ESPADA)  ){return 12; }

        if (unNumero.equals(Numero.SIETE)&& unPalo.equals(Palo.ORO)  ){return 11; }

        if (unNumero.equals(Numero.TRES)){return 10; }

        if (unNumero.equals(Numero.DOS) ){return 9; }

        if (unNumero.equals(Numero.UNO)){return 8; }

        if (unNumero.equals(Numero.DOCE) ){return 7; }

        if (unNumero.equals(Numero.ONCE) ){return 6; }

        if (unNumero.equals(Numero.DIEZ) ){return 5; }

        if (unNumero.equals(Numero.SIETE) ){return 4; }

        if (unNumero.equals(Numero.SEIS)){return 3; }

        if (unNumero.equals(Numero.CINCO)){return 2; }

        if (unNumero.equals(Numero.CUATRO)){return 1; }

        else return 0;

    }






}
