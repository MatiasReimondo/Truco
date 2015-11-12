package truco.modelo;

import truco.modelo.enumerables.Palo;

import java.util.Hashtable;

public class TablaFuerzaCarta {

    private Hashtable<Carta,Integer> tabla;

    public TablaFuerzaCarta(){

        tabla=new Hashtable<>();

        Carta AsDeEspada=new Carta(1, Palo.ESPADA);
        tabla.put(AsDeEspada, 14);

        Carta AsDeBasto=new Carta(1,Palo.BASTO);
        tabla.put(AsDeBasto, 13);

        Carta SieteDeEspada=new Carta(7,Palo.ESPADA);
        tabla.put(SieteDeEspada, 12);

        Carta SieteDeOro=new Carta(7,Palo.ORO);
        tabla.put(SieteDeOro, 11);

        Carta TresGenerico=new Carta(3,Palo.ORO);
        tabla.put(TresGenerico, 10);

        Carta DosGenerico=new Carta(2,Palo.ESPADA);
        tabla.put(DosGenerico, 9);

        Carta AsDeCopas=new Carta(1,Palo.COPA);
        tabla.put(AsDeCopas, 8);

        Carta AsDeOro=new Carta(1,Palo.ORO);
        tabla.put(AsDeOro, 8);

        Carta DoceGenerico=new Carta(12,Palo.ESPADA);
        tabla.put(DoceGenerico, 7);

        Carta OnceGenerico=new Carta(11,Palo.ESPADA);
        tabla.put(OnceGenerico, 6);

        Carta DiezGenerico=new Carta(10,Palo.ESPADA);
        tabla.put(DiezGenerico, 5);

        Carta SieteDeCopas=new Carta(7,Palo.COPA);
        tabla.put(SieteDeCopas, 4);

        Carta SeisGenerico=new Carta(6,Palo.COPA);
        tabla.put(SeisGenerico, 3);

        Carta CincoGenerico=new Carta(5,Palo.COPA);
        tabla.put(CincoGenerico, 2);

        Carta CuatroGenerico=new Carta(4,Palo.COPA);
        tabla.put(CuatroGenerico, 1);
    }

    public Hashtable<Carta,Integer> getTabla(){
        return tabla;
    }
}
