package truco.modelo;


import truco.modelo.enumerables.Palo;

@SuppressWarnings("ALL")
public class Carta {

    private Palo palo;
    private int numero;
    private int puntosEnvido;
    private static TablaFuerzaCarta tablaFuerza=new TablaFuerzaCarta();

    public Carta(int unNumero, Palo unPalo){

        this.palo = unPalo;
        this.numero= unNumero;
    }

    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    public void setNumero(int numero) {

        this.numero = numero;
    }

    public Palo getPalo() {
        return this.palo;
    }

    public int getNumero() {
        return this.numero;
    }

    public int getPuntosEnvido(){
        return this.puntosEnvido;
    }

    public int getFuerza(){

        return tablaFuerza.getTabla().get(this);
    }

    @Override
    public boolean equals(Object objeto) {
        if(objeto==null)
            return false;

        if (this.getClass() != objeto.getClass())
            return false;

        final Carta carta=(Carta) objeto;
        if(this.getNumero()<7 && this.getNumero()>1 || this.getNumero()>9)
            return this.getNumero()==carta.getNumero();

        return (this.getNumero()==carta.getNumero() && this.getPalo().equals(carta.getPalo()));
    }

}
