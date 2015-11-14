package truco.modelo;


import truco.modelo.excepciones.NumeroCartaExcedeElRangoException;

import java.util.Hashtable;

@SuppressWarnings("ALL")
public class Carta {

    private Palo palo;
    private int numero;
    private Hashtable<Carta,Integer> tablaFuerza;

    public Carta(int unNumero, Palo unPalo){
        if(numero<0 || numero>12)
            throw new NumeroCartaExcedeElRangoException();

        this.palo = unPalo;
        this.numero= unNumero;
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
        if(numero>9)
           return 0;
        return numero;
    }

    private void armarTablaDeFuerza() {

        tablaFuerza = new Hashtable<>();

        Carta AsDeEspada = new Carta(1, Palo.ESPADA);
        tablaFuerza.put(AsDeEspada, 14);

        Carta AsDeBasto = new Carta(1, Palo.BASTO);
        tablaFuerza.put(AsDeBasto, 13);

        Carta SieteDeEspada = new Carta(7, Palo.ESPADA);
        tablaFuerza.put(SieteDeEspada, 12);

        Carta SieteDeOro = new Carta(7, Palo.ORO);
        tablaFuerza.put(SieteDeOro, 11);

        //Los tres
        Carta TresDeOro = new Carta(3, Palo.ORO);
        tablaFuerza.put(TresDeOro, 10);

        Carta TresDeBasto = new Carta(3, Palo.BASTO);
        tablaFuerza.put(TresDeBasto, 10);

        Carta TresDeEspada = new Carta(3, Palo.ESPADA);
        tablaFuerza.put(TresDeEspada, 10);

        Carta TresDeCopa = new Carta(3, Palo.COPA);
        tablaFuerza.put(TresDeCopa, 10);

        //Los dos
        Carta DosDeOro = new Carta(2, Palo.ORO);
        tablaFuerza.put(DosDeOro, 9);

        Carta DosDeBasto = new Carta(2, Palo.BASTO);
        tablaFuerza.put(DosDeBasto, 9);

        Carta DosDeEspada = new Carta(2, Palo.ESPADA);
        tablaFuerza.put(DosDeEspada, 9);

        Carta DosDeCopa = new Carta(2, Palo.COPA);
        tablaFuerza.put(DosDeCopa, 9);


        //Ases
        Carta AsDeCopas = new Carta(1, Palo.COPA);
        tablaFuerza.put(AsDeCopas, 8);

        Carta AsDeOro = new Carta(1, Palo.ORO);
        tablaFuerza.put(AsDeOro, 8);

        //Doces
        Carta DoceDeEspada = new Carta(12, Palo.ESPADA);
        tablaFuerza.put(DoceDeEspada, 7);

        Carta DoceDeBasto = new Carta(12, Palo.BASTO);
        tablaFuerza.put(DoceDeBasto, 7);

        Carta DoceDeOro = new Carta(12, Palo.ORO);
        tablaFuerza.put(DoceDeOro, 7);

        Carta DoceDeCopa = new Carta(12, Palo.COPA);
        tablaFuerza.put(DoceDeCopa, 7);

        //Onces
        Carta OnceDeEspada = new Carta(11, Palo.ESPADA);
        tablaFuerza.put(OnceDeEspada, 6);

        Carta OnceDeBasto = new Carta(11, Palo.BASTO);
        tablaFuerza.put(OnceDeBasto, 6);

        Carta OnceDeOro = new Carta(11, Palo.ORO);
        tablaFuerza.put(OnceDeOro, 6);

        Carta OnceDeCopa = new Carta(11, Palo.COPA);
        tablaFuerza.put(OnceDeCopa, 6);

        //Diez
        Carta DiezDeEspada = new Carta(10, Palo.ESPADA);
        tablaFuerza.put(DiezDeEspada, 5);

        Carta DiezDeBasto = new Carta(10, Palo.BASTO);
        tablaFuerza.put(DiezDeBasto, 5);

        Carta DiezDeOro = new Carta(10, Palo.ORO);
        tablaFuerza.put(DiezDeOro, 5);

        Carta DiezDeCopa = new Carta(10, Palo.COPA);
        tablaFuerza.put(DiezDeCopa, 5);

        //Siestes
        Carta SieteDeBasto = new Carta(7, Palo.BASTO);
        tablaFuerza.put(SieteDeBasto, 4);

        Carta SieteDeCopas = new Carta(7, Palo.COPA);
        tablaFuerza.put(SieteDeCopas, 4);

        //Seis
        Carta SeisDeEspada = new Carta(6, Palo.ESPADA);
        tablaFuerza.put(SeisDeEspada, 3);

        Carta SeisDeBasto = new Carta(6, Palo.BASTO);
        tablaFuerza.put(SeisDeBasto, 3);

        Carta SeisDeCopa = new Carta(6, Palo.COPA);
        tablaFuerza.put(SeisDeCopa, 3);

        Carta SeisDeOro = new Carta(6, Palo.ORO);
        tablaFuerza.put(SeisDeOro, 3);

        //Cinco
        Carta CincoDeEspada = new Carta(5, Palo.ESPADA);
        tablaFuerza.put(CincoDeEspada, 2);

        Carta CincoDeBasto = new Carta(5, Palo.BASTO);
        tablaFuerza.put(CincoDeBasto, 2);

        Carta CincoDeCopa = new Carta(5, Palo.COPA);
        tablaFuerza.put(CincoDeCopa, 2);

        Carta CincoDeOro = new Carta(5, Palo.ORO);
        tablaFuerza.put(CincoDeOro, 2);

        //Cuatro
        Carta CuatroDeEspada = new Carta(4, Palo.ESPADA);
        tablaFuerza.put(CuatroDeEspada, 1);

        Carta CuatroDeBasto = new Carta(4, Palo.BASTO);
        tablaFuerza.put(CuatroDeBasto, 1);

        Carta CuatroDeCopa = new Carta(4, Palo.COPA);
        tablaFuerza.put(CuatroDeCopa, 1);

        Carta CuatroDeOro = new Carta(4, Palo.ORO);
        tablaFuerza.put(CuatroDeOro, 1);
    }

    @Override
    public int hashCode() {
        int result = palo != null ? palo.hashCode() : 0;
        result = 31 * result + numero;
        return result;
    }

    @Override
    public boolean equals(Object objeto) {
        if(objeto==null || this==null)
            return false;

        if (this.getClass() != objeto.getClass())
            return false;

        final Carta carta=(Carta) objeto;

        if(carta.getNumero()>1 && carta.getNumero()<7 || this.getNumero()>1 && this.getNumero()<7 || carta.getNumero()>9 || this.getNumero()>9)
            return this.getNumero()==carta.getNumero();

        return (this.getNumero()==carta.getNumero() && this.getPalo().equals(carta.getPalo()));
    }

}
