package truco.modelo.flor;

import truco.modelo.Equipo;
import truco.modelo.excepciones.SoloSePuedeCantarFlorUnaVez;


public class Flor {
    private Flor contraFlor;
    private int puntos;

    public Flor(){
        puntos=3;
    }

    public void setContraFlor(Flor contraFlor){
        if(contraFlor==null) {
            this.contraFlor = contraFlor;
        }else {
            throw new SoloSePuedeCantarFlorUnaVez();
        }
    }

    public void subirApuesta(Flor flor){
        contraFlor=flor;
    }

    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(contraFlor==null)
            return this.puntos;
        return this.puntos+contraFlor.getPuntos(equipoGanador,equipoPerdedor);
    }

}
