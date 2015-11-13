package truco.modelo;


import truco.modelo.enumerables.Palo;

import java.util.Hashtable;

@SuppressWarnings("ALL")
public class Carta {

    private Palo palo;
    private int numero;
    private int puntosEnvido;
    private static Hashtable<Carta,Integer> tablaFuerza;

    public Carta(int unNumero, Palo unPalo){

        this.palo = unPalo;
        this.numero= unNumero;

        if(numero>9)
            puntosEnvido=0;
        else puntosEnvido=numero;

    }

    public int getFuerza(){
        if(tablaFuerza==null)
            armarTablaDeFuerza();

        return tablaFuerza.get(this);
    }

    public Palo getPalo() {
        return this.palo;
    }

    public int getNumero() {
        return this.numero;
    }

    public int getValorEnvido(){
        return this.puntosEnvido;
    }

    private void armarTablaDeFuerza(){

        tablaFuerza=new Hashtable<>();

        Carta AsDeEspada=new Carta(1, Palo.ESPADA);
        tablaFuerza.put(AsDeEspada, 14);

        Carta AsDeBasto=new Carta(1,Palo.BASTO);
        tablaFuerza.put(AsDeBasto, 13);

        Carta SieteDeEspada=new Carta(7,Palo.ESPADA);
        tablaFuerza.put(SieteDeEspada, 12);

        Carta SieteDeOro=new Carta(7,Palo.ORO);
        tablaFuerza.put(SieteDeOro, 11);

        Carta TresGenerico=new Carta(3,Palo.ORO);
        tablaFuerza.put(TresGenerico, 10);

        Carta DosGenerico=new Carta(2,Palo.ESPADA);
        tablaFuerza.put(DosGenerico, 9);

        Carta AsDeCopas=new Carta(1,Palo.COPA);
        tablaFuerza.put(AsDeCopas, 8);

        Carta AsDeOro=new Carta(1,Palo.ORO);
        tablaFuerza.put(AsDeOro, 8);

        Carta DoceGenerico=new Carta(12,Palo.ESPADA);
        tablaFuerza.put(DoceGenerico, 7);

        Carta OnceGenerico=new Carta(11,Palo.ESPADA);
        tablaFuerza.put(OnceGenerico, 6);

        Carta DiezGenerico=new Carta(10,Palo.ESPADA);
        tablaFuerza.put(DiezGenerico, 5);

        Carta SieteDeCopas=new Carta(7,Palo.COPA);
        tablaFuerza.put(SieteDeCopas, 4);

        Carta SeisGenerico=new Carta(6,Palo.COPA);
        tablaFuerza.put(SeisGenerico, 3);

        Carta CincoGenerico=new Carta(5,Palo.COPA);
        tablaFuerza.put(CincoGenerico, 2);

        Carta CuatroGenerico=new Carta(4,Palo.COPA);
        tablaFuerza.put(CuatroGenerico, 1);

    }

    @Override
    public int hashCode() {
        int result = palo != null ? palo.hashCode() : 0;
        result = 31 * result + numero;
        result = 31 * result + puntosEnvido;
        return result;
    }

    @Override
    public boolean equals(Object objeto) {
        if(objeto==null || this==null)
            return false;

        if (this.getClass() != objeto.getClass())
            return false;

        final Carta carta=(Carta) objeto;

        System.out.println("Numero:" + carta.getNumero() + "  Palo:" + carta.getPalo());
        System.out.println("Numero:" + this.getNumero() + "  Palo:" + this.getPalo());
        System.out.println();

        if(carta.getNumero()>1 && carta.getNumero()<7 || this.getNumero()>1 && this.getNumero()<7 || carta.getNumero()>9 || this.getNumero()>9)
            return this.getNumero()==carta.getNumero();

        return (this.getNumero()==carta.getNumero() && this.getPalo().equals(carta.getPalo()));
    }

}
