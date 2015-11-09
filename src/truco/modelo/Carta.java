package truco.modelo;


import truco.modelo.enumerables.Numero;
import truco.modelo.enumerables.Palo;

@SuppressWarnings("ALL")
public class Carta {

    private Palo palo;
    private Numero numero;
    private int fuerza;
    private int puntosEnvido;

    public Carta(Numero unNumero, Palo unPalo){

        this.palo = unPalo;
        this.numero= unNumero;
        this.determinarFuerza();
        this.determinarPuntosEnvido();
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
        return this.palo;
    }

    public Numero getNumero() {

        return this.numero;
    }

    public int getPuntosEnvido(){
        return this.puntosEnvido;
    }

    public int getFuerza(){

        return this.fuerza;
    }

    public boolean Comparar(Carta carta) {
        return ( carta.getPalo().equals(this.palo) && carta.getNumero().equals(this.numero) );
    }

    /**************************** Métodos privados ***********************************/

    private void determinarFuerza(){

        if (this.numero.equals(Numero.UNO) && this.palo.equals(Palo.ESPADA)  ){this.fuerza= 14;}

        else if (this.numero.equals(Numero.UNO)&& this.palo.equals(Palo.BASTO)  ){this.fuerza= 13; }

        else if (this.numero.equals(Numero.SIETE)&& this.palo.equals(Palo.ESPADA)  ){this.fuerza= 12;}

        else if (this.numero.equals(Numero.SIETE)&& this.palo.equals(Palo.ORO)  ){this.fuerza= 11; }

        else if (this.numero.equals(Numero.TRES)){this.fuerza= 10; }

        else if (this.numero.equals(Numero.DOS) ){this.fuerza= 9; }

        else if (this.numero.equals(Numero.UNO)){this.fuerza= 8; }

        else if (this.numero.equals(Numero.DOCE) ){this.fuerza= 7;}

        else if (this.numero.equals(Numero.ONCE) ){this.fuerza= 6;}

        else if (this.numero.equals(Numero.DIEZ) ){this.fuerza= 5; }

        else if (this.numero.equals(Numero.SIETE) ){this.fuerza= 4; }

        else if (this.numero.equals(Numero.SEIS)){this.fuerza= 3; }

        else if (this.numero.equals(Numero.CINCO)){this.fuerza= 2;}

        else if (numero.equals(Numero.CUATRO)){ this.fuerza= 1; }

    }

    private void determinarPuntosEnvido(){

        if (this.numero.equals(Numero.TRES)){this.puntosEnvido= 3;}

        else if (this.numero.equals(Numero.DOS) ){this.puntosEnvido= 2;}

        else if (this.numero.equals(Numero.UNO)){this.puntosEnvido= 1; }

        else if (this.numero.equals(Numero.DOCE) ){this.puntosEnvido= 0;}

        else if (this.numero.equals(Numero.ONCE) ){this.puntosEnvido= 0;}

        else if (this.numero.equals(Numero.DIEZ) ){this.puntosEnvido= 0;}

        else if (this.numero.equals(Numero.SIETE) ){this.puntosEnvido= 7;}

        else if (this.numero.equals(Numero.SEIS)){this.puntosEnvido= 6;}

        else if (this.numero.equals(Numero.CINCO)){this.puntosEnvido= 5;}

        else if (this.numero.equals(Numero.CUATRO)){this.puntosEnvido= 4;}
    }
}
